package addonBasic.models.item;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.MainShield;

public class ModelMageBook extends MainShield {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	float f = 0;

	float c = 0;

	public ModelMageBook() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(4F, 2F, -3F, 1, 3, 6);
		Shape1.setRotationPoint(5F, 2F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(3.5F, -1F, -2.5F, 1, 3, 5);
		Shape2.setRotationPoint(5F, 2F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(3.2F, 6F, -2F, 1, 3, 4);
		Shape3.setRotationPoint(5F, 2F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(-1.5F, -1F, 2.5F, 5, 3, 1);
		Shape4.setRotationPoint(5F, 2F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(-2F, 2F, -4F, 6, 3, 1);
		Shape5.setRotationPoint(5F, 2F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 0);
		Shape6.addBox(-1F, 6F, -3.2F, 4, 3, 1);
		Shape6.setRotationPoint(5F, 2F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 0);
		Shape7.addBox(-1F, 6F, 2.2F, 4, 3, 1);
		Shape7.setRotationPoint(5F, 2F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 0);
		Shape8.addBox(-2F, 2F, 3F, 6, 3, 1);
		Shape8.setRotationPoint(5F, 2F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 0);
		Shape9.addBox(-1.5F, -1F, -3.5F, 5, 3, 1);
		Shape9.setRotationPoint(5F, 2F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);

		parts.add(Shape1);
		parts.add(Shape2);
		parts.add(Shape3);
		parts.add(Shape4);
		parts.add(Shape5);
		parts.add(Shape6);
		parts.add(Shape7);
		parts.add(Shape8);
		parts.add(Shape9);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		// Shape2.render(f5);
		// Shape3.render(f5);
		// Shape4.render(f5);
		// Shape5.render(f5);
		// Shape6.render(f5);
		// Shape7.render(f5);
		// Shape8.render(f5);
		// Shape9.render(f5);
	}

	@Override
	public void renderShield(float f5) {

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);

		super.renderShield(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);

		if (f == 0.1) {
			f = -0.1f;
		}
		f += 0.001;

		Shape1.offsetY = MathHelper.sin(f * 6f) * 0.3f;
		Shape2.offsetY = -MathHelper.sin(f * 3f) * 0.3f;
		Shape3.offsetY = MathHelper.sin(f) * 0.1f;
		Shape4.offsetY = -MathHelper.sin(f * 2f) * 0.4f;
		Shape5.offsetY = MathHelper.sin(f * 3f) * 0.2f;
		Shape6.offsetY = -MathHelper.sin(f) * 0.1f;
		Shape7.offsetY = MathHelper.sin(f * 5f) * 0.3f;
		Shape8.offsetY = -MathHelper.sin(f * 7f) * 0.2f;
		Shape9.offsetY = MathHelper.sin(f * 4f) * 0.4f;

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);

		// float time = System.currentTimeMillis()/500;
		// this.Shape1.offsetY = (MathHelper.sin(time*0.3F)*6F)+7;
		// this.Shape2.offsetY = (MathHelper.sin(time*0.2F)*6F)+7;
		// this.Shape3.offsetY = (MathHelper.sin(time*0.4F)*6F)+7;
	}
}
