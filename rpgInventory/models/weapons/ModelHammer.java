package rpgInventory.models.weapons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHammer extends ModelBase
{
	//fields
	ModelRenderer HammerBase;
	ModelRenderer Deco1;
	ModelRenderer Deco2;
	ModelRenderer Deco3;
	ModelRenderer handle;
	ModelRenderer head;
	ModelRenderer deco4;
	ModelRenderer deco5;
	ModelRenderer Backhead;
	ModelRenderer bheaddeco;
	ModelRenderer HeadDeco;
	ModelRenderer pist1;
	ModelRenderer pist2;
	ModelRenderer pist3;
	ModelRenderer pist4;

	public ModelHammer()
	{
		textureWidth = 64;
		textureHeight = 32;

		HammerBase = new ModelRenderer(this, 6, 18);
		HammerBase.addBox(-0.5F, 0F, -0.5F, 3, 2, 2);
		HammerBase.setRotationPoint(0F, 0F, 0F);
		HammerBase.setTextureSize(64, 32);
		HammerBase.mirror = true;
		setRotation(HammerBase, 0F, 0F, 0F);
		Deco1 = new ModelRenderer(this, 6, 28);
		Deco1.addBox(0.5F, 0F, -1.5F, 1, 2, 1);
		Deco1.setRotationPoint(0F, 0F, 0F);
		Deco1.setTextureSize(64, 32);
		Deco1.mirror = true;
		setRotation(Deco1, 0F, 0F, 0F);
		Deco2 = new ModelRenderer(this, 6, 28);
		Deco2.addBox(0.5F, 0F, 1.5F, 1, 2, 1);
		Deco2.setRotationPoint(0F, 0F, 0F);
		Deco2.setTextureSize(64, 32);
		Deco2.mirror = true;
		setRotation(Deco2, 0F, 0F, 0F);
		Deco3 = new ModelRenderer(this, 6, 24);
		Deco3.addBox(0.5F, 2F, -0.5F, 1, 1, 2);
		Deco3.setRotationPoint(0F, 0F, 0F);
		Deco3.setTextureSize(64, 32);
		Deco3.mirror = true;
		setRotation(Deco3, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 0, 15);
		handle.addBox(0.5F, -16F, 0F, 1, 16, 1);
		handle.setRotationPoint(0F, 0F, 0F);
		handle.setTextureSize(64, 32);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
		head = new ModelRenderer(this, 30, 0);
		head.addBox(-1.5F, -20F, -3.5F, 5, 5, 4);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		deco4 = new ModelRenderer(this, 11, 29);
		deco4.addBox(0.5F, -1F, 1F, 1, 1, 1);
		deco4.setRotationPoint(0F, 0F, 0F);
		deco4.setTextureSize(64, 32);
		deco4.mirror = true;
		setRotation(deco4, 0F, 0F, 0F);
		deco5 = new ModelRenderer(this, 11, 29);
		deco5.addBox(0.5F, -1F, -1F, 1, 1, 1);
		deco5.setRotationPoint(0F, 0F, 0F);
		deco5.setTextureSize(64, 32);
		deco5.mirror = true;
		setRotation(deco5, 0F, 0F, 0F);
		Backhead = new ModelRenderer(this, 15, 1);
		Backhead.addBox(-1F, -19.5F, 1.5F, 4, 4, 2);
		Backhead.setRotationPoint(0F, 0F, 0F);
		Backhead.setTextureSize(64, 32);
		Backhead.mirror = true;
		setRotation(Backhead, 0F, 0F, 0F);
		bheaddeco = new ModelRenderer(this, 16, 8);
		bheaddeco.addBox(-1.5F, -20F, 3.5F, 5, 5, 1);
		bheaddeco.setRotationPoint(0F, 0F, 0F);
		bheaddeco.setTextureSize(64, 32);
		bheaddeco.mirror = true;
		setRotation(bheaddeco, 0F, 0F, 0F);
		HeadDeco = new ModelRenderer(this, 50, 0);
		HeadDeco.addBox(-2F, -20.5F, -4.5F, 6, 6, 1);
		HeadDeco.setRotationPoint(0F, 0F, 0F);
		HeadDeco.setTextureSize(64, 32);
		HeadDeco.mirror = true;
		setRotation(HeadDeco, 0F, 0F, 0F);
		pist1 = new ModelRenderer(this, 0, 0);
		pist1.addBox(-0.9F, -19F, 0F, 1, 1, 2);
		pist1.setRotationPoint(0F, 0F, 0F);
		pist1.setTextureSize(64, 32);
		pist1.mirror = true;
		setRotation(pist1, 0F, 0F, 0F);
		pist2 = new ModelRenderer(this, 0, 0);
		pist2.addBox(-0.9F, -17F, 0F, 1, 1, 2);
		pist2.setRotationPoint(0F, 0F, 0F);
		pist2.setTextureSize(64, 32);
		pist2.mirror = true;
		setRotation(pist2, 0F, 0F, 0F);
		pist3 = new ModelRenderer(this, 0, 0);
		pist3.addBox(1.9F, -17F, 0F, 1, 1, 2);
		pist3.setRotationPoint(0F, 0F, 0F);
		pist3.setTextureSize(64, 32);
		pist3.mirror = true;
		setRotation(pist3, 0F, 0F, 0F);
		pist4 = new ModelRenderer(this, 0, 0);
		pist4.addBox(1.9F, -19F, 0F, 1, 1, 2);
		pist4.setRotationPoint(0F, 0F, 0F);
		pist4.setTextureSize(64, 32);
		pist4.mirror = true;
		setRotation(pist4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		render(f5);
	}

	public void render(float f5){
		HammerBase.render(f5);
		Deco1.render(f5);
		Deco2.render(f5);
		Deco3.render(f5);
		handle.render(f5);
		head.render(f5);
		deco4.render(f5);
		deco5.render(f5);
		Backhead.render(f5);
		bheaddeco.render(f5);
		HeadDeco.render(f5);
		pist1.render(f5);
		pist2.render(f5);
		pist3.render(f5);
		pist4.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5,ent);
	}

}
