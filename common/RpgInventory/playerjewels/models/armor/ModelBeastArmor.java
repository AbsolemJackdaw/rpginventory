package RpgInventory.playerjewels.models.armor;

import RpgInventory.item.armor.BonusArmor;
import RpgInventory.mod_RpgInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelBeastArmor extends ModelBiped {
    //fields

    public static ItemStack[] armor = new ItemStack[4];
    boolean beastHelm = false;
    boolean beastSpaulder = false;
    ModelRenderer SpaulderL;
    ModelRenderer SpaulderR;
    ModelRenderer Horn1;
    ModelRenderer Horn2;
    ModelRenderer Horn3;
    ModelRenderer Horn4;

    public ModelBeastArmor(float par1, float par2, int par3, int par4) {
        par3 = 64;
        par4 = 32;
        for (ItemStack is : armor) {
            if (is != null) {
                if (is.getItem() instanceof BonusArmor) {
                    if (is.getItem().itemID == mod_RpgInventory.beastHood.itemID) {
                        beastHelm = true;
                    } else if (is.getItem().itemID == mod_RpgInventory.beastChest.itemID) {
                        beastSpaulder = true;
                    }
                }
                System.out.println(is.getItem());
            }
        }

        textureWidth = par3;
        textureHeight = par4;
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
        SpaulderL = new ModelRenderer(this, 44, 0);
        SpaulderL.addBox(-1F, -3F, -2.5F, 5, 3, 5, par1);
        SpaulderL.setRotationPoint(0F, 0F, 0F);
        SpaulderL.setTextureSize(64, 32);
        SpaulderL.mirror = true;
        setRotation(SpaulderL, 0F, 0F, 0.1745329F);
        SpaulderR = new ModelRenderer(this, 44, 0);
        SpaulderR.addBox(-4F, -3F, -2.5F, 5, 3, 5, par1);
        SpaulderR.setRotationPoint(0F, 0F, 0F);
        SpaulderR.setTextureSize(64, 32);
        SpaulderR.mirror = true;
        setRotation(SpaulderR, 0F, 0F, -0.1745329F);
        Horn1 = new ModelRenderer(this, 32, 0);
        Horn1.addBox(4F, -7F, 1F, 1, 2, 2, par1);
        Horn1.setRotationPoint(0F, 0F, 0F);
        Horn1.setTextureSize(64, 32);
        Horn1.mirror = true;
        setRotation(Horn1, 0F, 0F, 0F);
        Horn2 = new ModelRenderer(this, 32, 4);
        Horn2.addBox(1F, -7F, 4F, 3, 1, 1, par1);
        Horn2.setRotationPoint(0F, -0.5F, 0F);
        Horn2.setTextureSize(64, 32);
        Horn2.mirror = true;
        setRotation(Horn2, 0F, 0.7853982F, 0.1745329F);
        Horn3 = new ModelRenderer(this, 32, 0);
        Horn3.addBox(-5F, -7F, 1F, 1, 2, 2, par1);
        Horn3.setRotationPoint(0F, 0F, 0F);
        Horn3.setTextureSize(64, 32);
        Horn3.mirror = true;
        setRotation(Horn3, 0F, 0F, 0F);
        Horn4 = new ModelRenderer(this, 32, 4);
        Horn4.addBox(-4F, -7F, 4F, 3, 1, 1, par1);
        Horn4.setRotationPoint(0F, -0.5F, 0F);
        Horn4.setTextureSize(64, 32);
        Horn4.mirror = true;
        setRotation(Horn4, 0F, -0.7853982F, -0.1745329F);
        if (beastHelm) {
            this.bipedHead.addChild(Horn1);
            this.bipedHead.addChild(Horn2);
            this.bipedHead.addChild(Horn3);
            this.bipedHead.addChild(Horn4);
        }
        if (beastSpaulder) {
            this.bipedLeftArm.addChild(SpaulderL);
            this.bipedRightArm.addChild(SpaulderR);
        }

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void setShowModel(boolean b) {
        this.bipedBody.showModel = b;
        this.bipedCloak.showModel = b;
        this.bipedEars.showModel = b;
        this.bipedHead.showModel = b;
        this.bipedHeadwear.showModel = b;
        this.bipedLeftArm.showModel = b;
        this.bipedLeftLeg.showModel = b;
        this.bipedRightArm.showModel = b;
        this.bipedRightLeg.showModel = b;
        //		this.Horn1.showModel= b;
        //		this.Horn2.showModel= b;
        //		this.Horn3.showModel= b;
        //		this.Horn4.showModel= b;
        //		this.SpaulderL.showModel= b;
        //		this.SpaulderR.showModel= b;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }
    //  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    //  {
    //    super.setRotationAngles(f, f1, f2, f3, f4, f5);
    //  }
}
