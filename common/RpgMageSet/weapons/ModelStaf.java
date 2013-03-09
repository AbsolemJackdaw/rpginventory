// Date: 14/02/2013 23:50:35
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package RpgMageSet.weapons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelStaf extends ModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer FloatingSphere1;
	ModelRenderer FloatingSphere2;
	ModelRenderer FloatingSphere3;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape8;
	ModelRenderer Shape7;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape19;
	ModelRenderer Shape18;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape22;

	public ModelStaf()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0.5F, -7F, -1F, 1, 29, 3);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		FloatingSphere1 = new ModelRenderer(this, 56, 0);
		FloatingSphere1.addBox(-1F, -1F, -1F, 2, 2, 2);
		FloatingSphere1.setRotationPoint(0.5F, -12.5F, 0.5F);
		FloatingSphere1.setTextureSize(64, 32);
		setRotation(FloatingSphere1, 0F, 0F, 0F);
		FloatingSphere2 = new ModelRenderer(this, 56, 0);
		FloatingSphere2.addBox(-1F, -1F, -1F, 2, 2, 2);
		FloatingSphere2.setRotationPoint(0.5F, -12.5F, 0.5F);
		FloatingSphere2.setTextureSize(64, 32);
		setRotation(FloatingSphere2, 0F, 0F, 0F);
		FloatingSphere3 = new ModelRenderer(this, 56, 0);
		FloatingSphere3.addBox(-1F, -1F, -1F, 2, 2, 2);
		FloatingSphere3.setRotationPoint(0.5F, -12.5F, 0.5F);
		FloatingSphere3.setTextureSize(64, 32);
		setRotation(FloatingSphere3, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 11, 5);
		Shape5.addBox(0F, -8F, 2F, 2, 2, 2);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 29, 0);
		Shape6.addBox(0F, -6F, 2F, 2, 1, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 11, 5);
		Shape8.addBox(0F, -11F, 4F, 2, 3, 2);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 29, 0);
		Shape7.addBox(0F, -8F, 1F, 2, 1, 1);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 29, 0);
		Shape9.addBox(0F, -9F, 3F, 2, 1, 1);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 29, 0);
		Shape10.addBox(0F, -8F, 4F, 2, 1, 1);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 20, 0);
		Shape11.addBox(0F, -14F, 6F, 2, 4, 1);
		Shape11.setRotationPoint(0F, 0F, 0F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 12, 0);
		Shape12.addBox(0.5F, -14F, 5F, 1, 3, 1);
		Shape12.setRotationPoint(0F, 0F, 0F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 0);
		Shape13.addBox(1.5F, -7F, 0F, 1, 31, 1);
		Shape13.setRotationPoint(0F, 0F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 0);
		Shape14.addBox(-0.5F, -7F, 0F, 1, 31, 1);
		Shape14.setRotationPoint(0F, 0F, 0F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 0, 0);
		Shape15.addBox(0.5F, 22F, -1F, 1, 2, 3);
		Shape15.setRotationPoint(0F, 0F, 0F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 0, 0);
		Shape16.addBox(0F, -8F, -2F, 2, 1, 2);
		Shape16.setRotationPoint(0F, 0F, 0F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 36, 0);
		Shape17.addBox(0.5F, -9F, -2F, 1, 3, 1);
		Shape17.setRotationPoint(0F, 0F, 0F);
		Shape17.setTextureSize(64, 32);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 0, 0);
		Shape19.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape19.setRotationPoint(0F, 0F, 0F);
		Shape19.setTextureSize(64, 32);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 0, 0);
		Shape18.addBox(0.5F, -9F, -3F, 1, 1, 1);
		Shape18.setRotationPoint(0F, 0F, 0F);
		Shape18.setTextureSize(64, 32);
		Shape18.mirror = true;
		setRotation(Shape18, 0F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 0, 0);
		Shape20.addBox(0.5F, -15F, 6F, 1, 1, 1);
		Shape20.setRotationPoint(0F, 0F, 0F);
		Shape20.setTextureSize(64, 32);
		Shape20.mirror = true;
		setRotation(Shape20, 0F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 0, 0);
		Shape21.addBox(0.5F, -16F, 5F, 1, 1, 1);
		Shape21.setRotationPoint(0F, 0F, 0F);
		Shape21.setTextureSize(64, 32);
		Shape21.mirror = true;
		setRotation(Shape21, 0F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 0, 0);
		Shape22.addBox(0.5F, -11F, -4F, 1, 2, 1);
		Shape22.setRotationPoint(0F, 0F, 0F);
		Shape22.setTextureSize(64, 32);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		Shape1.render(f5);
		
		GL11.glPushMatrix();
        GL11.glScalef(2F , 2F, 2F );
        GL11.glTranslatef(0.0F, 0.355F , -0.015F);
		FloatingSphere1.render(f5);
		FloatingSphere2.render(f5);
		FloatingSphere3.render(f5);
		GL11.glPopMatrix();
		
		Shape5.render(f5);
		Shape6.render(f5);
		Shape8.render(f5);
		Shape7.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape19.render(f5);
		Shape18.render(f5);
		Shape20.render(f5);
		Shape21.render(f5);
		Shape22.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity et)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5,et);
		float x7 = 1f;
		float zc = 0f;
		this.FloatingSphere1.rotateAngleY = (MathHelper.sin(f)*x7)+0F;
		this.FloatingSphere1.rotateAngleX = (MathHelper.sin(f)*10f);

		this.FloatingSphere2.rotateAngleZ = (MathHelper.sin(f)*x7)+0f;
		this.FloatingSphere2.rotateAngleY = (MathHelper.sin(f)*10F);

		this.FloatingSphere3.rotateAngleX = (MathHelper.sin(f)*x7)+0f;
		this.FloatingSphere3.rotateAngleZ = (MathHelper.sin(f)*10f);

	}

}
