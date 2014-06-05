package addonDread.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GrandSword extends ModelBase {
	// fields
	//	ModelRenderer Part1;
	//	ModelRenderer Part2;
	//	ModelRenderer Part3;
	//	ModelRenderer Part4;
	//	ModelRenderer Part5;
	//	ModelRenderer Part6;
	//	ModelRenderer Part8;
	//	ModelRenderer Part13;
	//	ModelRenderer Part14;
	//	ModelRenderer Part15;
	//	ModelRenderer Part16;
	//	ModelRenderer Part17;
	//	ModelRenderer Part18;
	//	ModelRenderer Part19;
	//	ModelRenderer Part20;
	//	ModelRenderer Part21;
	//	ModelRenderer Part22;
	//	ModelRenderer Part23;
	//	ModelRenderer Part24;
	//	ModelRenderer Part25;
	//	ModelRenderer Part26;
	//	ModelRenderer Part27;
	//
	//	public GrandSword() {
	//		textureWidth = 128;
	//		textureHeight = 32;
	//
	//		Part1 = new ModelRenderer(this, 115, 0);
	//		Part1.addBox(-1.5F, -19F, -0.5F, 3, 22, 1);
	//		Part1.setRotationPoint(0F, 0F, 0F);
	//		Part1.setTextureSize(64, 32);
	//		Part1.mirror = true;
	//		setRotation(Part1, 0F, 0F, 0F);
	//		Part2 = new ModelRenderer(this, 105, 0);
	//		Part2.addBox(-1F, -18F, -0.8F, 2, 21, 1);
	//		Part2.setRotationPoint(0F, 0F, 0F);
	//		Part2.setTextureSize(64, 32);
	//		Part2.mirror = true;
	//		setRotation(Part2, 0F, 0F, 0F);
	//		Part3 = new ModelRenderer(this, 90, 0);
	//		Part3.addBox(-2F, -18F, -0.5F, 4, 21, 1);
	//		Part3.setRotationPoint(0F, 0F, 0F);
	//		Part3.setTextureSize(64, 32);
	//		Part3.mirror = true;
	//		setRotation(Part3, 0F, 0F, 0F);
	//		Part4 = new ModelRenderer(this, 80, 0);
	//		Part4.addBox(-1.5F, -17F, -1F, 1, 20, 2);
	//		Part4.setRotationPoint(0F, 0F, 0F);
	//		Part4.setTextureSize(64, 32);
	//		Part4.mirror = true;
	//		setRotation(Part4, 0F, 0F, 0F);
	//		Part5 = new ModelRenderer(this, 81, 27);
	//		Part5.addBox(-0.5F, -19.6F, -0.5F, 1, 1, 1);
	//		Part5.setRotationPoint(0F, 0F, 0F);
	//		Part5.setTextureSize(64, 32);
	//		Part5.mirror = true;
	//		setRotation(Part5, 0F, 0F, 0F);
	//		Part6 = new ModelRenderer(this, 65, 0);
	//		Part6.addBox(-2.5F, -16F, -0.5F, 5, 19, 1);
	//		Part6.setRotationPoint(0F, 0F, 0F);
	//		Part6.setTextureSize(64, 32);
	//		Part6.mirror = true;
	//		setRotation(Part6, 0F, 0F, 0F);
	//		Part8 = new ModelRenderer(this, 80, 0);
	//		Part8.addBox(0.5F, -17F, -1F, 1, 20, 2);
	//		Part8.setRotationPoint(0F, 0F, 0F);
	//		Part8.setTextureSize(64, 32);
	//		Part8.mirror = true;
	//		setRotation(Part8, 0F, 0F, 0F);
	//		Part13 = new ModelRenderer(this, 105, 0);
	//		Part13.addBox(-1F, -18F, -0.2F, 2, 21, 1);
	//		Part13.setRotationPoint(0F, 0F, 0F);
	//		Part13.setTextureSize(64, 32);
	//		Part13.mirror = true;
	//		setRotation(Part13, 0F, 0F, 0F);
	//		Part14 = new ModelRenderer(this, 50, 0);
	//		Part14.addBox(-2F, -16F, -0.8F, 4, 19, 1);
	//		Part14.setRotationPoint(0F, 0F, 0F);
	//		Part14.setTextureSize(64, 32);
	//		Part14.mirror = true;
	//		setRotation(Part14, 0F, 0F, 0F);
	//		Part15 = new ModelRenderer(this, 50, 0);
	//		Part15.addBox(-2F, -16F, -0.2F, 4, 19, 1);
	//		Part15.setRotationPoint(0F, 0F, 0F);
	//		Part15.setTextureSize(64, 32);
	//		Part15.mirror = true;
	//		setRotation(Part15, 0F, 0F, 0F);
	//		Part16 = new ModelRenderer(this, 70, 27);
	//		Part16.addBox(-1F, -19.3F, -0.5F, 2, 1, 1);
	//		Part16.setRotationPoint(0F, 0F, 0F);
	//		Part16.setTextureSize(64, 32);
	//		Part16.mirror = true;
	//		setRotation(Part16, 0F, 0F, 0F);
	//		Part17 = new ModelRenderer(this, 60, 27);
	//		Part17.addBox(-1.8F, -18.6F, -0.5F, 3, 1, 1);
	//		Part17.setRotationPoint(0F, 0F, 0F);
	//		Part17.setTextureSize(64, 32);
	//		Part17.mirror = true;
	//		setRotation(Part17, 0F, 0F, 0F);
	//		Part18 = new ModelRenderer(this, 55, 27);
	//		Part18.addBox(0.8F, -18.6F, -0.5F, 1, 1, 1);
	//		Part18.setRotationPoint(0F, 0F, 0F);
	//		Part18.setTextureSize(64, 32);
	//		Part18.mirror = true;
	//		setRotation(Part18, 0F, 0F, 0F);
	//		Part19 = new ModelRenderer(this, 35, 27);
	//		Part19.addBox(-2.2F, -17.5F, -0.5F, 1, 2, 1);
	//		Part19.setRotationPoint(0F, 0F, 0F);
	//		Part19.setTextureSize(64, 32);
	//		Part19.mirror = true;
	//		setRotation(Part19, 0F, 0F, 0F);
	//		Part20 = new ModelRenderer(this, 35, 27);
	//		Part20.addBox(0F, -17.5F, -0.5F, 1, 2, 1);
	//		Part20.setRotationPoint(1.2F, 0F, 0F);
	//		Part20.setTextureSize(64, 32);
	//		Part20.mirror = true;
	//		setRotation(Part20, 0F, 0F, 0F);
	//		Part21 = new ModelRenderer(this, 45, 27);
	//		Part21.addBox(-1F, -18.6F, -0.6F, 2, 1, 1);
	//		Part21.setRotationPoint(0F, 0F, 0F);
	//		Part21.setTextureSize(64, 32);
	//		Part21.mirror = true;
	//		setRotation(Part21, 0F, 0F, 0F);
	//		Part22 = new ModelRenderer(this, 20, 27);
	//		Part22.addBox(-2F, -17.3F, -0.8F, 4, 2, 1);
	//		Part22.setRotationPoint(0F, 0F, 0F);
	//		Part22.setTextureSize(64, 32);
	//		Part22.mirror = true;
	//		setRotation(Part22, 0F, 0F, 0F);
	//		Part23 = new ModelRenderer(this, 30, 0);
	//		Part23.addBox(-3F, 3F, -1F, 6, 1, 2);
	//		Part23.setRotationPoint(0F, 0F, 0F);
	//		Part23.setTextureSize(64, 32);
	//		Part23.mirror = true;
	//		setRotation(Part23, 0F, 0F, 0F);
	//		Part24 = new ModelRenderer(this, 30, 5);
	//		Part24.addBox(-3.5F, 3F, -0.5F, 7, 1, 1);
	//		Part24.setRotationPoint(0F, 0F, 0F);
	//		Part24.setTextureSize(64, 32);
	//		Part24.mirror = true;
	//		setRotation(Part24, 0F, 0F, 0F);
	//		Part25 = new ModelRenderer(this, 30, 10);
	//		Part25.addBox(-0.5F, 4F, -0.5F, 1, 5, 1);
	//		Part25.setRotationPoint(0F, 0F, 0F);
	//		Part25.setTextureSize(64, 32);
	//		Part25.mirror = true;
	//		setRotation(Part25, 0F, 0F, 0F);
	//		Part26 = new ModelRenderer(this, 30, 10);
	//		Part26.addBox(-0.5F, 4F, -0.5F, 1, 5, 1);
	//		Part26.setRotationPoint(0F, 0F, 0F);
	//		Part26.setTextureSize(64, 32);
	//		Part26.mirror = true;
	//		setRotation(Part26, 0F, 0.4089647F, 0F);
	//		Part27 = new ModelRenderer(this, 30, 10);
	//		Part27.addBox(-0.5F, 4F, -0.5F, 1, 5, 1);
	//		Part27.setRotationPoint(0F, 0F, 0F);
	//		Part27.setTextureSize(64, 32);
	//		Part27.mirror = true;
	//		setRotation(Part27, 0F, 0.9294653F, 0F);
	//	}
	//
	//	@Override
	//	public void render(Entity entity, float f, float f1, float f2, float f3,
	//			float f4, float f5) {
	//		super.render(entity, f, f1, f2, f3, f4, f5);
	//		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	//		Part1.render(f5);
	//		Part2.render(f5);
	//		Part3.render(f5);
	//		Part4.render(f5);
	//		Part5.render(f5);
	//		Part6.render(f5);
	//		Part8.render(f5);
	//		Part13.render(f5);
	//		Part14.render(f5);
	//		Part15.render(f5);
	//		Part16.render(f5);
	//		Part17.render(f5);
	//		Part18.render(f5);
	//		Part19.render(f5);
	//		Part20.render(f5);
	//		Part21.render(f5);
	//		Part22.render(f5);
	//		Part23.render(f5);
	//		Part24.render(f5);
	//		Part25.render(f5);
	//		Part26.render(f5);
	//		Part27.render(f5);
	//	}
	//
	//	private void setRotation(ModelRenderer model, float x, float y, float z) {
	//		model.rotateAngleX = x;
	//		model.rotateAngleY = y;
	//		model.rotateAngleZ = z;
	//	}
	//
	//	@Override
	//	public void setRotationAngles(float f, float f1, float f2, float f3,
	//			float f4, float f5, Entity ent) {
	//		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	//	}


	// fields
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

	public GrandSword() {

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

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		render(f5);
	}

	public void render(float f5) {
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

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
