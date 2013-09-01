package rpgInventory.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNecroArmor extends ModelBiped
{
	//fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer ToeTipR;
	ModelRenderer Shape5;
	ModelRenderer ToeTipL;
	ModelRenderer TieTop;
	ModelRenderer TieBottom;

	public ModelNecroArmor(float par1, float par2, int par3, int par4)
	{
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
		Shape1 = new ModelRenderer(this, 0, 32);
		Shape1.addBox(0F, -2F, -2.5F, 5, 1, 5, par1/1.9f);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(65, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0.5235988F);
		Shape2 = new ModelRenderer(this, 0, 32);
		Shape2.addBox(0F, 0F, -2.5F, 5, 1, 5, par1/1.9f);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(65, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0.7872665F);
		Shape3 = new ModelRenderer(this, 0, 41);
		Shape3.addBox(-2.5F, 5F, -2.5F, 5, 2, 5, par1/1.5f);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(65, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 41);
		Shape5.addBox(-2.5F, 5F, -2.5F, 5, 2, 5, par1/1.5f);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(65, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		ToeTipR = new ModelRenderer(this, 0, 38);
		ToeTipR.addBox(-2F, 11F, -4F, 4, 2, 1);
		ToeTipR.setRotationPoint(0F, 0F, 0F);
		ToeTipR.setTextureSize(65, 64);
		ToeTipR.mirror = true;
		setRotation(ToeTipR, 0F, 0F, 0F);
		ToeTipL = new ModelRenderer(this, 0, 38);
		ToeTipL.addBox(-2F, 11F, -4F, 4, 2, 1);
		ToeTipL.setRotationPoint(0F, 0F, 0F);
		ToeTipL.setTextureSize(65, 64);
		ToeTipL.mirror = true;
		setRotation(ToeTipL, 0F, 0F, 0F);
		TieTop = new ModelRenderer(this, 22, 32);
		TieTop.addBox(1F, 1F, -3.5F, 1, 1, 1);
		TieTop.setRotationPoint(0F, 0F, 0F);
		TieTop.setTextureSize(65, 64);
		TieTop.mirror = true;
		setRotation(TieTop, 0F, 0F, 0.7853982F);
		TieBottom = new ModelRenderer(this, 22, 34);
		TieBottom.addBox(-0.5F, 2.5F, -3.4F, 1, 5, 1);
		TieBottom.setRotationPoint(0F, 0F, 0F);
		TieBottom.setTextureSize(65, 64);
		TieBottom.mirror = true;
		setRotation(TieBottom, 0F, 0F, 0F);

		bipedBody.addChild(TieBottom);
		bipedBody.addChild(TieTop);
		bipedRightLeg.addChild(ToeTipR);
		bipedRightLeg.addChild(Shape3);
		bipedLeftLeg.addChild(Shape5);
		bipedLeftLeg.addChild(ToeTipL);
		bipedLeftArm.addChild(Shape1);
		bipedLeftArm.addChild(Shape2);

	}
	public void showBoots(boolean show)
	{
		Shape5.showModel = ToeTipL.showModel = ToeTipR.showModel 
				= Shape3.showModel = show;
	}
	public void showTie(boolean show)
	{
		TieTop.showModel = TieBottom.showModel = show;
	}
	public void showGrasps(boolean show)
	{
		Shape1.showModel = Shape2.showModel = show;
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	//  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	//  {
		//    super.setRotationAngles(f, f1, f2, f3, f4, f5);
		//  }

}
