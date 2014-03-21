package rpgInventory.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBerserkerArmor extends ModelBiped {
	// fields
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

	public ModelBerserkerArmor(float par1) {
		super(par1, 0, 65, 64);

		textureWidth = 65;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 40);
		Shape1.addBox(4F, -8F, 0F, 1, 3, 3, par1 / 2);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(65, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 32);
		Shape2.addBox(5F, -8.5F, 0.5F, 1, 2, 2, par1 / 2);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(65, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 36);
		Shape3.addBox(6F, -10F, 1F, 1, 3, 1, par1 / 2);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(65, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 40);
		Shape4.addBox(-5F, -8F, -1F, 1, 3, 3, par1 / 2);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(65, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 32);
		Shape5.addBox(-6F, -8.5F, -0.5F, 1, 2, 2, par1 / 2);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(65, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 8, 39);
		Shape6.addBox(-1F, -3F, -2.5F, 5, 2, 5, par1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(65, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0.3490659F);
		Shape7 = new ModelRenderer(this, 10, 32);
		Shape7.addBox(0F, -5.5F, -0.5F, 1, 3, 1, par1 / 2);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(65, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0.4014257F);
		Shape8 = new ModelRenderer(this, 14, 32);
		Shape8.addBox(1F, -5.5F, -0.5F, 1, 2, 1, par1 / 2);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(65, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0.8203047F);
		Shape9 = new ModelRenderer(this, 8, 39);
		Shape9.addBox(-4F, -3F, -2.5F, 5, 2, 5, par1);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(65, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, -0.3490659F);
		Shape10 = new ModelRenderer(this, 14, 32);
		Shape10.addBox(-2F, -5.5F, -0.5F, 1, 2, 1, par1 / 2);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(65, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, -0.8203047F);
		Shape11 = new ModelRenderer(this, 10, 32);
		Shape11.addBox(-1F, -5.5F, -0.5F, 1, 3, 1, par1 / 2);
		Shape11.setRotationPoint(0F, 0F, 0F);
		Shape11.setTextureSize(65, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, -0.3490659F);

		bipedRightArm.addChild(Shape11);
		bipedRightArm.addChild(Shape10);
		bipedRightArm.addChild(Shape9);
		bipedLeftArm.addChild(Shape8);
		bipedLeftArm.addChild(Shape7);
		bipedLeftArm.addChild(Shape6);
		bipedHead.addChild(Shape1);
		bipedHead.addChild(Shape2);
		bipedHead.addChild(Shape3);
		bipedHead.addChild(Shape4);
		bipedHead.addChild(Shape5);

	}

	@Deprecated
	public ModelBerserkerArmor(float par1, float par2, int par3, int par4) {
		this(par1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void showHorns(boolean show) {
		Shape1.showModel = Shape2.showModel = Shape3.showModel = Shape4.showModel = Shape5.showModel = show;
	}

	public void showSpaulders(boolean show) {
		Shape6.showModel = Shape7.showModel = Shape8.showModel = Shape9.showModel = Shape10.showModel = Shape11.showModel = show;
	}
}
