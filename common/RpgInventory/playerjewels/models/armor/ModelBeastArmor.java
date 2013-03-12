package RpgInventory.playerjewels.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBeastArmor extends ModelBiped
{
	//fields

	ModelRenderer SpaulderL;
	ModelRenderer SpaulderR;
	ModelRenderer Horn1;
	ModelRenderer Horn2;
	ModelRenderer Horn3;
	ModelRenderer Horn4;

	
	public ModelBeastArmor()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		SpaulderL = new ModelRenderer(this, 44, 0);
		SpaulderL.addBox(-1F, -3F, -2.5F, 5, 3, 5);
		SpaulderL.setRotationPoint(0F, 0F, 0F);
		SpaulderL.setTextureSize(64, 32);
		SpaulderL.mirror = true;
		setRotation(SpaulderL, 0F, 0F, 0.1745329F);
		SpaulderR = new ModelRenderer(this, 44, 0);
		SpaulderR.addBox(-4F, -3F, -2.5F, 5, 3, 5);
		SpaulderR.setRotationPoint(0F, 0F, 0F);
		SpaulderR.setTextureSize(64, 32);
		SpaulderR.mirror = true;
		setRotation(SpaulderR, 0F, 0F, -0.1745329F);
		Horn1 = new ModelRenderer(this, 32, 0);
		Horn1.addBox(4F, -7F, 1F, 1, 2, 2);
		Horn1.setRotationPoint(0F, 0F, 0F);
		Horn1.setTextureSize(64, 32);
		Horn1.mirror = true;
		setRotation(Horn1, 0F, 0F, 0F);
		Horn2 = new ModelRenderer(this, 32, 4);
		Horn2.addBox(1F, -7F, 4F, 3, 1, 1);
		Horn2.setRotationPoint(0F, -0.5F, 0F);
		Horn2.setTextureSize(64, 32);
		Horn2.mirror = true;
		setRotation(Horn2, 0F, 0.7853982F, 0.1745329F);
		Horn3 = new ModelRenderer(this, 32, 0);
		Horn3.addBox(-5F, -7F, 1F, 1, 2, 2);
		Horn3.setRotationPoint(0F, 0F, 0F);
		Horn3.setTextureSize(64, 32);
		Horn3.mirror = true;
		setRotation(Horn3, 0F, 0F, 0F);
		Horn4 = new ModelRenderer(this, 32, 4);
		Horn4.addBox(-4F, -7F, 4F, 3, 1, 1);
		Horn4.setRotationPoint(0F, -0.5F, 0F);
		Horn4.setTextureSize(64, 32);
		Horn4.mirror = true;
		setRotation(Horn4, 0F, -0.7853982F, -0.1745329F);
		
		this.bipedHead.addChild(Horn1);
		this.bipedHead.addChild(Horn2);
		this.bipedHead.addChild(Horn3);
		this.bipedHead.addChild(Horn4);
		this.bipedLeftArm.addChild(SpaulderL);
		this.bipedRightArm.addChild(SpaulderR);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5,entity);

	}

	public void setShowModel(boolean b)
	{
		this.bipedBody.showModel= b;
		this.bipedCloak.showModel= b;
		this.bipedEars.showModel= b;
		this.bipedHead.showModel= b;
		this.bipedHeadwear.showModel= b;
		this.bipedLeftArm.showModel= b;
		this.bipedLeftLeg.showModel= b;
		this.bipedRightArm.showModel= b;
		this.bipedRightLeg.showModel= b;
//		this.Horn1.showModel= b;
//		this.Horn2.showModel= b;
//		this.Horn3.showModel= b;
//		this.Horn4.showModel= b;
//		this.SpaulderL.showModel= b;
//		this.SpaulderR.showModel= b;
	}
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }
	//  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	//  {
	//    super.setRotationAngles(f, f1, f2, f3, f4, f5);
	//  }

}
