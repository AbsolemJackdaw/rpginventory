package rpgNecroPaladin.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPaladinArmor extends ModelBiped {
	ModelRenderer LspoulderDown;
	ModelRenderer helmV1;
	ModelRenderer hemlv4;
	ModelRenderer helmV2;
	ModelRenderer helmV3;
	ModelRenderer plume1;
	ModelRenderer plume2;
	ModelRenderer plume3;
	ModelRenderer plume4;
	ModelRenderer plume5;
	ModelRenderer plume6;
	ModelRenderer plume7;
	ModelRenderer plume8;
	ModelRenderer plume10;
	ModelRenderer plume9;
	ModelRenderer lspoulderMid;
	ModelRenderer lspoulderTop;
	ModelRenderer lspoulderBase;
	ModelRenderer rspoulderMid;
	ModelRenderer RspoulderTop;
	ModelRenderer RspoulderBottom;
	ModelRenderer rspoulderBase;

	public ModelPaladinArmor(float par1) {
		super(par1, 0, 65, 64);

		textureWidth = 65;
		textureHeight = 64;

		LspoulderDown = new ModelRenderer(this, 40, 46);
		LspoulderDown.addBox(4F, 2F, -2.5F, 3, 1, 5);
		LspoulderDown.setRotationPoint(0F, 0F, 0F);
		LspoulderDown.setTextureSize(65, 64);
		LspoulderDown.mirror = true;
		setRotation(LspoulderDown, 0F, 0F, 0.3F);
		helmV1 = new ModelRenderer(this, 0, 32);
		helmV1.addBox(-3.5F, -7F, -4.7F, 1, 6, 1);
		helmV1.setRotationPoint(0F, 0F, 0F);
		helmV1.setTextureSize(65, 64);
		helmV1.mirror = true;
		setRotation(helmV1, 0F, 0F, 0F);
		hemlv4 = new ModelRenderer(this, 0, 32);
		hemlv4.addBox(2.5F, -7F, -4.7F, 1, 6, 1);
		hemlv4.setRotationPoint(0F, 0F, 0F);
		hemlv4.setTextureSize(65, 64);
		hemlv4.mirror = true;
		setRotation(hemlv4, 0F, 0F, 0F);
		helmV2 = new ModelRenderer(this, 0, 32);
		helmV2.addBox(-1.5F, -7F, -4.7F, 1, 6, 1);
		helmV2.setRotationPoint(0F, 0F, 0F);
		helmV2.setTextureSize(65, 64);
		helmV2.mirror = true;
		setRotation(helmV2, 0F, 0F, 0F);
		helmV3 = new ModelRenderer(this, 0, 32);
		helmV3.addBox(0.5F, -7F, -4.7F, 1, 6, 1);
		helmV3.setRotationPoint(0F, 0F, 0F);
		helmV3.setTextureSize(65, 64);
		helmV3.mirror = true;
		setRotation(helmV3, 0F, 0F, 0F);
		plume1 = new ModelRenderer(this, 0, 41);
		plume1.addBox(-0.5F, -9F, 0F, 1, 1, 1);
		plume1.setRotationPoint(0F, 0F, 0F);
		plume1.setTextureSize(65, 64);
		plume1.mirror = true;
		setRotation(plume1, 0F, 0F, 0F);
		plume2 = new ModelRenderer(this, 0, 40);
		plume2.addBox(-0.5F, -10F, -1F, 1, 1, 4);
		plume2.setRotationPoint(0F, 0F, 0F);
		plume2.setTextureSize(65, 64);
		plume2.mirror = true;
		setRotation(plume2, 0F, 0F, 0F);
		plume3 = new ModelRenderer(this, 0, 34);
		plume3.addBox(-0.5F, -11F, -2F, 1, 1, 10);
		plume3.setRotationPoint(0F, 0F, 0F);
		plume3.setTextureSize(65, 64);
		plume3.mirror = true;
		setRotation(plume3, 0F, 0F, 0F);
		plume4 = new ModelRenderer(this, 0, 33);
		plume4.addBox(-0.5F, -12F, -3F, 1, 1, 11);
		plume4.setRotationPoint(0F, 0F, 0F);
		plume4.setTextureSize(65, 64);
		plume4.mirror = true;
		setRotation(plume4, 0F, 0F, 0F);
		plume5 = new ModelRenderer(this, 0, 34);
		plume5.addBox(-0.5F, -13F, -3F, 1, 1, 10);
		plume5.setRotationPoint(0F, 0F, 0F);
		plume5.setTextureSize(65, 64);
		plume5.mirror = true;
		setRotation(plume5, 0F, 0F, 0F);
		plume6 = new ModelRenderer(this, 0, 35);
		plume6.addBox(-0.5F, -14F, -2F, 1, 1, 9);
		plume6.setRotationPoint(0F, 0F, 0F);
		plume6.setTextureSize(65, 64);
		plume6.mirror = true;
		setRotation(plume6, 0F, 0F, 0F);
		plume7 = new ModelRenderer(this, 0, 37);
		plume7.addBox(-0.5F, -15F, -1F, 1, 1, 7);
		plume7.setRotationPoint(0F, 0F, 0F);
		plume7.setTextureSize(65, 64);
		plume7.mirror = true;
		setRotation(plume7, 0F, 0F, 0F);
		plume8 = new ModelRenderer(this, 0, 39);
		plume8.addBox(-0.5F, -16F, 0F, 1, 1, 5);
		plume8.setRotationPoint(0F, 0F, 0F);
		plume8.setTextureSize(65, 64);
		plume8.mirror = true;
		setRotation(plume8, 0F, 0F, 0F);
		plume10 = new ModelRenderer(this, 0, 41);
		plume10.addBox(-0.5F, -10F, 6F, 1, 1, 3);
		plume10.setRotationPoint(0F, 0F, 0F);
		plume10.setTextureSize(65, 64);
		plume10.mirror = true;
		setRotation(plume10, 0F, 0F, 0F);
		plume9 = new ModelRenderer(this, 0, 42);
		plume9.addBox(-0.5F, -9F, 7F, 1, 1, 2);
		plume9.setRotationPoint(0F, 0F, 0F);
		plume9.setTextureSize(65, 64);
		plume9.mirror = true;
		setRotation(plume9, 0F, 0F, 0F);
		lspoulderMid = new ModelRenderer(this, 36, 32);
		lspoulderMid.addBox(1F, -3F, -3F, 4, 8, 6);
		lspoulderMid.setRotationPoint(0F, 0F, 0F);
		lspoulderMid.setTextureSize(65, 64);
		lspoulderMid.mirror = true;
		setRotation(lspoulderMid, 0F, 0F, -0.3F);
		lspoulderTop = new ModelRenderer(this, 42, 52);
		lspoulderTop.addBox(0F, -5F, -2.5F, 2, 3, 5);
		lspoulderTop.setRotationPoint(0F, 0F, 0F);
		lspoulderTop.setTextureSize(65, 64);
		lspoulderTop.mirror = true;
		setRotation(lspoulderTop, 0F, 0F, 0F);
		lspoulderBase = new ModelRenderer(this, 26, 52);
		lspoulderBase.addBox(0F, -3F, -2.5F, 3, 7, 5);
		lspoulderBase.setRotationPoint(0F, 0F, 0F);
		lspoulderBase.setTextureSize(65, 64);
		lspoulderBase.mirror = true;
		setRotation(lspoulderBase, 0F, 0F, 0F);
		rspoulderMid = new ModelRenderer(this, 36, 32);
		rspoulderMid.addBox(1F, -3F, -3F, 4, 8, 6);
		rspoulderMid.setRotationPoint(0F, 0F, 0F);
		rspoulderMid.setTextureSize(65, 64);
		rspoulderMid.mirror = true;
		setRotation(rspoulderMid, 0F, -3.2F, 0.3F);
		RspoulderTop = new ModelRenderer(this, 42, 52);
		RspoulderTop.addBox(-2F, -5F, -2.5F, 2, 3, 5);
		RspoulderTop.setRotationPoint(0F, 0F, 0F);
		RspoulderTop.setTextureSize(65, 64);
		RspoulderTop.mirror = true;
		setRotation(RspoulderTop, 0F, 0F, 0F);
		RspoulderBottom = new ModelRenderer(this, 40, 46);
		RspoulderBottom.addBox(-7F, 2F, -2.5F, 3, 1, 5);
		RspoulderBottom.setRotationPoint(0F, 0F, 0F);
		RspoulderBottom.setTextureSize(65, 64);
		RspoulderBottom.mirror = true;
		setRotation(RspoulderBottom, 0F, 0F, -0.3F);
		rspoulderBase = new ModelRenderer(this, 26, 52);
		rspoulderBase.addBox(-3F, -3F, -2.5F, 3, 7, 5);
		rspoulderBase.setRotationPoint(0F, 0F, 0F);
		rspoulderBase.setTextureSize(65, 64);
		rspoulderBase.mirror = true;
		setRotation(rspoulderBase, 0F, 0F, 0F);

		this.bipedHeadwear.addChild(helmV1);
		this.bipedHeadwear.addChild(helmV2);
		this.bipedHeadwear.addChild(helmV3);
		this.bipedHeadwear.addChild(hemlv4);

		this.bipedHead.addChild(plume1);
		this.bipedHead.addChild(plume2);
		this.bipedHead.addChild(plume3);
		this.bipedHead.addChild(plume4);
		this.bipedHead.addChild(plume5);
		this.bipedHead.addChild(plume6);
		this.bipedHead.addChild(plume7);
		this.bipedHead.addChild(plume8);
		this.bipedHead.addChild(plume9);
		this.bipedHead.addChild(plume10);

		this.bipedLeftArm.addChild(lspoulderBase);
		this.bipedLeftArm.addChild(lspoulderMid);
		this.bipedLeftArm.addChild(lspoulderTop);
		this.bipedLeftArm.addChild(LspoulderDown);

		this.bipedRightArm.addChild(rspoulderBase);
		this.bipedRightArm.addChild(rspoulderMid);
		this.bipedRightArm.addChild(RspoulderTop);
		this.bipedRightArm.addChild(RspoulderBottom);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		// LspoulderDown.render(f5);
		// helmV1.render(f5);
		// hemlv4.render(f5);
		// helmV2.render(f5);
		// helmV3.render(f5);
		// plume1.render(f5);
		// plume2.render(f5);
		// plume3.render(f5);
		// plume4.render(f5);
		// plume5.render(f5);
		// plume6.render(f5);
		// plume7.render(f5);
		// plume8.render(f5);
		// plume10.render(f5);
		// plume9.render(f5);
		// lspoulderMid.render(f5);
		// lspoulderTop.render(f5);
		// lspoulderBase.render(f5);
		// rspoulderMid.render(f5);
		// RspoulderTop.render(f5);
		// RspoulderBottom.render(f5);
		// rspoulderBase.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
