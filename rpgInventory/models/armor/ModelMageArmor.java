package rpgInventory.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMageArmor extends ModelBiped
{
	boolean mageChest = false;
	boolean mageArms = false;
	boolean mageFeet = false;
	boolean magehead = false;

	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape18;
	ModelRenderer Shape19;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape22;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public ModelMageArmor(float par1, float par2, int par3, int par4)
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
		Shape1 = new ModelRenderer(this, 0, 51);
		Shape1.addBox(-2F, -8F, -6F, 4, 1, 12);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(65, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 59);
		Shape2.addBox(-6F, -8F, -2F, 12, 1, 4);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(65, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 62);
		Shape3.addBox(-4F, -8F, -5F, 8, 1, 1);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(65, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 55);
		Shape4.addBox(4F, -8F, -4F, 1, 1, 8);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(65, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 55);
		Shape5.addBox(-5F, -8F, -4F, 1, 1, 8);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(65, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 62);
		Shape6.addBox(-4F, -8F, 4F, 8, 1, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(65, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 56);
		Shape7.addBox(-3.5F, -9F, -3.5F, 7, 1, 7);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(65, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 56);
		Shape8.addBox(-3F, -11F, -3F, 6, 2, 6);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(65, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 57);
		Shape9.addBox(-2.5F, -13F, -2.5F, 5, 2, 5);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(65, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 58);
		Shape10.addBox(-1.5F, -14F, -2F, 4, 1, 4);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(65, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 58);
		Shape11.addBox(-2F, -15F, -1.5F, 4, 1, 3);
		Shape11.setRotationPoint(0F, 0F, 0F);
		Shape11.setTextureSize(65, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 0, 58);
		Shape12.addBox(-3F, -16F, -1F, 4, 1, 2);
		Shape12.setRotationPoint(0F, 0F, 0F);
		Shape12.setTextureSize(65, 64);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 55);
		Shape13.addBox(-2F, -17F, -0.5F, 2, 1, 1);
		Shape13.setRotationPoint(0F, 0F, 0F);
		Shape13.setTextureSize(65, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 39);
		Shape14.addBox(-1F, -1F, -2.5F, 5, 2, 5,par1);
		Shape14.setRotationPoint(0F, 0F, 0F);
		Shape14.setTextureSize(65, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0.296706F);
		Shape15 = new ModelRenderer(this, 0, 39);
		Shape15.addBox(-4F, -1F, -2.5F, 5, 2, 5,par1);
		Shape15.setRotationPoint(0F, 0F, 0F);
		Shape15.setTextureSize(65, 64);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, -0.296706F);
		Shape16 = new ModelRenderer(this, 47, 50);
		Shape16.addBox(-4F, 9F, -2F, 8, 13, 1);
		Shape16.setRotationPoint(0F, 0F, 0F);
		Shape16.setTextureSize(65, 64);
		Shape16.mirror = true;
		setRotation(Shape16, 0.3316126F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 20, 43);
		Shape17.addBox(-2F, 10F, -3F, 4, 2, 1,par1/2);
		Shape17.setRotationPoint(0F, 0F, 0F);
		Shape17.setTextureSize(65, 64);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 20, 40);
		Shape18.addBox(-1.5F, 9.5F, -4F, 3, 2, 1,par1/2);
		Shape18.setRotationPoint(0F, 0F, 0F);
		Shape18.setTextureSize(65, 64);
		Shape18.mirror = true;
		setRotation(Shape18, 0F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 20, 38);
		Shape19.addBox(-0.5F, 9F, -5F, 1, 1, 1,par1/2);
		Shape19.setRotationPoint(0F, 0F, 0F);
		Shape19.setTextureSize(65, 64);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 20, 43);
		Shape20.addBox(-2F, 10F, -3F, 4, 2, 1,par1/2);
		Shape20.setRotationPoint(0F, 0F, 0F);
		Shape20.setTextureSize(65, 64);
		Shape20.mirror = true;
		setRotation(Shape20, 0F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 20, 40);
		Shape21.addBox(-1F, 9.5F, -4F, 3, 2, 1,par1/2);
		Shape21.setRotationPoint(0F, 0F, 0F);
		Shape21.setTextureSize(65, 64);
		Shape21.mirror = true;
		setRotation(Shape21, 0F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 20, 38);
		Shape22.addBox(0F, 9F, -5F, 1, 1, 1,par1/2);
		Shape22.setRotationPoint(0F, 0F, 0F);
		Shape22.setTextureSize(65, 64);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0F);

		bipedRightLeg.addChild(Shape22);
		bipedRightLeg.addChild(Shape21);
		bipedRightLeg.addChild(Shape20);
		bipedLeftLeg.addChild(Shape19);
		bipedLeftLeg.addChild(Shape18);
		bipedLeftLeg.addChild(Shape17);
		bipedBody.addChild(Shape16);
		bipedLeftArm.addChild(Shape14);
		bipedRightArm.addChild(Shape15);
		bipedHead.addChild(Shape13);
		bipedHead.addChild(Shape12);
		bipedHead.addChild(Shape11);
		bipedHead.addChild(Shape10);
		bipedHead.addChild(Shape9);
		bipedHead.addChild(Shape8);
		bipedHead.addChild(Shape7);
		bipedHead.addChild(Shape6);
		bipedHead.addChild(Shape5);
		bipedHead.addChild(Shape4);
		bipedHead.addChild(Shape3);
		bipedHead.addChild(Shape2);
		bipedHead.addChild(Shape1);
		
		
	}
	public void showMageFeet(boolean show)
	{
		Shape22.showModel = Shape21.showModel = Shape20.showModel 
				= Shape19.showModel = Shape18.showModel = show;
	}
	public void showMageChest(boolean show)
	{
		Shape16.showModel = show;
	}
	public void showMageHat(boolean show)
	{
		Shape1.showModel = Shape2.showModel = Shape3.showModel = Shape4.showModel = Shape5.showModel
				=Shape6.showModel = Shape7.showModel = Shape8.showModel = Shape9.showModel = Shape10.showModel
				=Shape11.showModel = Shape12.showModel = Shape13.showModel = show;
	}
	public void showMageArms(boolean show)
	{
		Shape14.showModel = Shape15.showModel = show;
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bipedHead.render(f5);
		bipedBody.render(f5);
		bipedRightArm.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);
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
