package rpgInventory.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelBeastArmor extends ModelBiped {
    //fields
    boolean beastHelm = false;
    boolean beastSpaulder = false;
    ModelRenderer SpaulderL;
    ModelRenderer SpaulderR;
    ModelRenderer Horn1;
    ModelRenderer Horn2;
    ModelRenderer Horn3;
    ModelRenderer Horn4;

    public ModelBeastArmor(float par1, float par2, int par3, int par4) {
        textureWidth = 65;
        textureHeight = 64;
        this.bipedCloak = new ModelRenderer(this, 0, 0);
        this.bipedEars = new ModelRenderer(this, 24, 0);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
        SpaulderL = new ModelRenderer(this, 0, 38);
        SpaulderL.addBox(0F, -3F, -2.5F, 5, 3, 5, par1);
        SpaulderL.setRotationPoint(0F, 0F, 0F);
        SpaulderL.setTextureSize(65, 64);
        SpaulderL.mirror = true;
        setRotation(SpaulderL, 0F, 0F, 0.1745329F);
        SpaulderR = new ModelRenderer(this, 0, 46);
        SpaulderR.addBox(-5F, -3F, -2.5F, 5, 3, 5, par1);
        SpaulderR.setRotationPoint(0F, 0F, 0F);
        SpaulderR.setTextureSize(65, 64);
        SpaulderR.mirror = true;
        setRotation(SpaulderR, 0F, 0F, -0.1745329F);
        Horn1 = new ModelRenderer(this, 0, 34);
        Horn1.addBox(4F, -7F, 1F, 1, 2, 2,par1/2);
        Horn1.setRotationPoint(0F, 0F, 0F);
        Horn1.setTextureSize(65, 64);
        Horn1.mirror = true;
        setRotation(Horn1, 0F, 0F, 0F);
        Horn2 = new ModelRenderer(this, 0, 32);
        Horn2.addBox(1F, -7F, 4F, 3, 1, 1,par1/2);
        Horn2.setRotationPoint(0F, 0F, 0F);
        Horn2.setTextureSize(65, 64);
        Horn2.mirror = true;
        setRotation(Horn2, 0F, 0.7853982F, 0.1745329F);
        Horn3 = new ModelRenderer(this, 0, 34);
        Horn3.addBox(-5F, -7F, 1F, 1, 2, 2,par1/2);
        Horn3.setRotationPoint(0F, 0F, 0F);
        Horn3.setTextureSize(65, 64);
        Horn3.mirror = true;
        setRotation(Horn3, 0F, 0F, 0F);
        Horn4 = new ModelRenderer(this, 0, 32);
        Horn4.addBox(-4F, -7F, 4F, 3, 1, 1,par1/2);
        Horn4.setRotationPoint(0F, 0F, 0F);
        Horn4.setTextureSize(65, 64);
        Horn4.mirror = true;
        setRotation(Horn4, 0F, -0.7853982F, -0.1745329F);
        this.bipedHead.addChild(Horn1);
        this.bipedHead.addChild(Horn2);
        this.bipedHead.addChild(Horn3);
        this.bipedHead.addChild(Horn4);
        this.bipedLeftArm.addChild(SpaulderL);
        this.bipedRightArm.addChild(SpaulderR);
    }

    public void showBeastHelmet(boolean show){
        Horn1.showModel = Horn2.showModel = Horn3.showModel = Horn4.showModel = show;
    }
    public void showBeastSpaulders(boolean show){
        SpaulderL.showModel = SpaulderR.showModel = show;
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
