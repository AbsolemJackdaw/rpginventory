package rpgInventory.models.weapons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelClaymore extends ModelBase
{
	//fields
	ModelRenderer core;
	ModelRenderer corestrip;
	ModelRenderer corestripR;
	ModelRenderer CoreStripL;
	ModelRenderer corestripD;
	ModelRenderer handle;
	ModelRenderer FlowerBase;
	ModelRenderer flowerDblock1;
	ModelRenderer flowerDblock2;
	ModelRenderer flowerDblock4;
	ModelRenderer flowerDblock3;
	ModelRenderer flowerbaseRight;
	ModelRenderer flowerbaseleft;
	ModelRenderer extra1;
	ModelRenderer extra2;
	ModelRenderer wing1;
	ModelRenderer Swing1b;
	ModelRenderer wing1c;
	ModelRenderer wing2a;
	ModelRenderer wing2b;
	ModelRenderer wing3a;
	ModelRenderer wing3c;
	ModelRenderer Shape8;
	ModelRenderer wing4a;
	ModelRenderer wing4b;
	ModelRenderer swordBase;
	ModelRenderer middleSwordPart;
	ModelRenderer top;

	public ModelClaymore()
	{
		textureWidth = 64;
		textureHeight = 32;

		core = new ModelRenderer(this, 0, 0);
		core.addBox(-2F, -2F, -1F, 4, 4, 3);
		core.setRotationPoint(0F, 0F, 0F);
		core.setTextureSize(64, 32);
		core.mirror = true;
		setRotation(core, 0F, 0F, 0F);
		corestrip = new ModelRenderer(this, 16, 6);
		corestrip.addBox(-2F, -3F, 0F, 4, 1, 1);
		corestrip.setRotationPoint(0F, 0F, 0F);
		corestrip.setTextureSize(64, 32);
		corestrip.mirror = true;
		setRotation(corestrip, 0F, 0F, 0F);
		corestripR = new ModelRenderer(this, 16, 0);
		corestripR.addBox(-3F, -2F, 0F, 1, 4, 1);
		corestripR.setRotationPoint(0F, 0F, 0F);
		corestripR.setTextureSize(64, 32);
		corestripR.mirror = true;
		setRotation(corestripR, 0F, 0F, 0F);
		CoreStripL = new ModelRenderer(this, 16, 0);
		CoreStripL.addBox(2F, -2F, 0F, 1, 4, 1);
		CoreStripL.setRotationPoint(0F, 0F, 0F);
		CoreStripL.setTextureSize(64, 32);
		CoreStripL.mirror = true;
		setRotation(CoreStripL, 0F, 0F, 0F);
		corestripD = new ModelRenderer(this, 16, 6);
		corestripD.addBox(-2F, 2F, 0F, 4, 1, 1);
		corestripD.setRotationPoint(0F, 0F, 0F);
		corestripD.setTextureSize(64, 32);
		corestripD.mirror = true;
		setRotation(corestripD, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 20, 23);
		handle.addBox(0F, -7F, 0F, 2, 8, 1);
		handle.setRotationPoint(-1F, -4F, 0F);
		handle.setTextureSize(64, 32);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
		FlowerBase = new ModelRenderer(this, 1, 22);
		FlowerBase.addBox(-3F, -6F, -0.5F, 6, 7, 2);
		FlowerBase.setRotationPoint(0F, -12F, 0F);
		FlowerBase.setTextureSize(64, 32);
		FlowerBase.mirror = true;
		setRotation(FlowerBase, 0F, 0F, 0F);
		flowerDblock1 = new ModelRenderer(this, 0, 9);
		flowerDblock1.addBox(0F, 0F, 0F, 1, 1, 1);
		flowerDblock1.setRotationPoint(-4F, -11F, 0F);
		flowerDblock1.setTextureSize(64, 32);
		flowerDblock1.mirror = true;
		setRotation(flowerDblock1, 0F, 0F, 0F);
		flowerDblock2 = new ModelRenderer(this, 0, 9);
		flowerDblock2.addBox(0F, 0F, 0F, 1, 1, 1);
		flowerDblock2.setRotationPoint(-5F, -10F, 0F);
		flowerDblock2.setTextureSize(64, 32);
		flowerDblock2.mirror = true;
		setRotation(flowerDblock2, 0F, 0F, 0F);
		flowerDblock4 = new ModelRenderer(this, 0, 9);
		flowerDblock4.addBox(0F, 0F, 0F, 1, 1, 1);
		flowerDblock4.setRotationPoint(3F, -11F, 0F);
		flowerDblock4.setTextureSize(64, 32);
		flowerDblock4.mirror = true;
		setRotation(flowerDblock4, 0F, 0F, 0F);
		flowerDblock3 = new ModelRenderer(this, 0, 9);
		flowerDblock3.addBox(0F, 0F, 0F, 1, 1, 1);
		flowerDblock3.setRotationPoint(4F, -10F, 0F);
		flowerDblock3.setTextureSize(64, 32);
		flowerDblock3.mirror = true;
		setRotation(flowerDblock3, 0F, 0F, 0F);
		flowerbaseRight = new ModelRenderer(this, 0, 12);
		flowerbaseRight.addBox(-6F, -7F, -0.5F, 3, 6, 2);
		flowerbaseRight.setRotationPoint(0F, -11F, 0F);
		flowerbaseRight.setTextureSize(64, 32);
		flowerbaseRight.mirror = true;
		setRotation(flowerbaseRight, 0F, 0F, 0F);
		flowerbaseleft = new ModelRenderer(this, 0, 12);
		flowerbaseleft.addBox(3F, -6F, -0.5F, 3, 6, 2);
		flowerbaseleft.setRotationPoint(0F, -12F, 0F);
		flowerbaseleft.setTextureSize(64, 32);
		flowerbaseleft.mirror = true;
		setRotation(flowerbaseleft, 0F, 0F, 0F);
		extra1 = new ModelRenderer(this, 16, 15);
		extra1.addBox(-5F, 0F, 0F, 2, 1, 1);
		extra1.setRotationPoint(0F, -12F, 0F);
		extra1.setTextureSize(64, 32);
		extra1.mirror = true;
		setRotation(extra1, 0F, 0F, 0F);
		extra2 = new ModelRenderer(this, 16, 15);
		extra2.addBox(3F, 0F, 0F, 2, 1, 1);
		extra2.setRotationPoint(0F, -12F, 0F);
		extra2.setTextureSize(64, 32);
		extra2.mirror = true;
		setRotation(extra2, 0F, 0F, 0F);
		wing1 = new ModelRenderer(this, 0, 9);
		wing1.addBox(-7F, -1F, 0F, 1, 1, 1);
		wing1.setRotationPoint(0F, -12F, 0F);
		wing1.setTextureSize(64, 32);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0F, 0F);
		Swing1b = new ModelRenderer(this, 6, 9);
		Swing1b.addBox(-9F, -2F, 0F, 3, 1, 1);
		Swing1b.setRotationPoint(0F, -12F, 0F);
		Swing1b.setTextureSize(64, 32);
		Swing1b.mirror = true;
		setRotation(Swing1b, 0F, 0F, 0F);
		wing1c = new ModelRenderer(this, 15, 9);
		wing1c.addBox(-11F, -3F, 0F, 2, 1, 1);
		wing1c.setRotationPoint(0F, -12F, 0F);
		wing1c.setTextureSize(64, 32);
		wing1c.mirror = true;
		setRotation(wing1c, 0F, 0F, 0F);
		wing2a = new ModelRenderer(this, 11, 12);
		wing2a.addBox(-7F, -7F, 0F, 1, 4, 1);
		wing2a.setRotationPoint(0F, -12F, 0F);
		wing2a.setTextureSize(64, 32);
		wing2a.mirror = true;
		setRotation(wing2a, 0F, 0F, 0F);
		wing2b = new ModelRenderer(this, 21, 0);
		wing2b.addBox(-8F, -8F, 0F, 1, 2, 1);
		wing2b.setRotationPoint(0F, -12F, 0F);
		wing2b.setTextureSize(64, 32);
		wing2b.mirror = true;
		setRotation(wing2b, 0F, 0F, 0F);
		wing3a = new ModelRenderer(this, 0, 9);
		wing3a.addBox(6F, -1F, 0F, 1, 1, 1);
		wing3a.setRotationPoint(0F, -12F, 0F);
		wing3a.setTextureSize(64, 32);
		wing3a.mirror = true;
		setRotation(wing3a, 0F, 0F, 0F);
		wing3c = new ModelRenderer(this, 6, 9);
		wing3c.addBox(6F, -2F, 0F, 3, 1, 1);
		wing3c.setRotationPoint(0F, -12F, 0F);
		wing3c.setTextureSize(64, 32);
		wing3c.mirror = true;
		setRotation(wing3c, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 15, 9);
		Shape8.addBox(9F, -3F, 0F, 2, 1, 1);
		Shape8.setRotationPoint(0F, -12F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		wing4a = new ModelRenderer(this, 11, 12);
		wing4a.addBox(6F, -7F, 0F, 1, 4, 1);
		wing4a.setRotationPoint(0F, -12F, 0F);
		wing4a.setTextureSize(64, 32);
		wing4a.mirror = true;
		setRotation(wing4a, 0F, 0F, 0F);
		wing4b = new ModelRenderer(this, 21, 0);
		wing4b.addBox(7F, -8F, 0F, 1, 2, 1);
		wing4b.setRotationPoint(0F, -12F, 0F);
		wing4b.setTextureSize(64, 32);
		wing4b.mirror = true;
		setRotation(wing4b, 0F, 0F, 0F);
		swordBase = new ModelRenderer(this, 37, 0);
		swordBase.addBox(-3F, -21F, 0F, 6, 23, 1);
		swordBase.setRotationPoint(0F, -20F, 0F);
		swordBase.setTextureSize(64, 32);
		swordBase.mirror = true;
		setRotation(swordBase, 0F, 0F, 0F);
		middleSwordPart = new ModelRenderer(this, 52, 0);
		middleSwordPart.addBox(-2F, -23F, -0.5F, 4, 25, 2);
		middleSwordPart.setRotationPoint(0F, -20F, 0F);
		middleSwordPart.setTextureSize(64, 32);
		middleSwordPart.mirror = true;
		setRotation(middleSwordPart, 0F, 0F, 0F);
		top = new ModelRenderer(this, 30, 0);
		top.addBox(-1F, -24F, 0F, 2, 1, 1);
		top.setRotationPoint(0F, -20F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		render(f5);
	}
	
	public void render(float f5){
		core.render(f5);
		corestrip.render(f5);
		corestripR.render(f5);
		CoreStripL.render(f5);
		corestripD.render(f5);
		handle.render(f5);
		FlowerBase.render(f5);
		flowerDblock1.render(f5);
		flowerDblock2.render(f5);
		flowerDblock4.render(f5);
		flowerDblock3.render(f5);
		flowerbaseRight.render(f5);
		flowerbaseleft.render(f5);
		extra1.render(f5);
		extra2.render(f5);
		wing1.render(f5);
		Swing1b.render(f5);
		wing1c.render(f5);
		wing2a.render(f5);
		wing2b.render(f5);
		wing3a.render(f5);
		wing3c.render(f5);
		Shape8.render(f5);
		wing4a.render(f5);
		wing4b.render(f5);
		swordBase.render(f5);
		middleSwordPart.render(f5);
		top.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
