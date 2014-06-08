/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity.renderers;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import addonMasters.entity.BeastMasterPet;
import addonMasters.entity.pet.ChickenPet;

public class RenderPet extends RenderLiving {

	public RenderPet() {
		super(new ModelCow(), 0.5F);
	}

	@Override
	public void doRender(Entity pet, double par2, double par4, double par6,
			float par8, float par9) {
		this.renderCow((BeastMasterPet) pet, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(EntityLiving pet, double par2, double par4,
			double par6, float par8, float par9) {
		this.renderCow((BeastMasterPet) pet, par2, par4, par6, par8, par9);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((BeastMasterPet) entity).getTexture();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase pet, float par2) {
		this.scalePet((BeastMasterPet) pet, par2);
	}

	public void renderCow(BeastMasterPet pet, double par2, double par4, double par6,
			float par8, float par9) {

		this.mainModel = pet.getModel();
		super.doRender(pet, par2, par4, par6, par8, par9);
		if (pet.riddenByEntity == null) {
			super.func_147906_a(pet,pet.getEntityName() + " Lvl." + pet.getLevel(), par2,
					(par4 + pet.height) - 0.2F, par6, 32);
		} else {
			// render living label
			super.func_147906_a(pet,
					pet.getEntityName() + " Lvl." + pet.getLevel(), par2,
					((par4 + pet.height) - 0.2F) + pet.riddenByEntity.yOffset
					+ pet.riddenByEntity.ySize, par6, 32);
		}

	}

	protected void scalePet(BeastMasterPet pet, float par2) {
		float var3 = pet.getPetSize();
		GL11.glScalef(var3, var3, var3);

	}
	
	@Override
	protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
		if(! (par1EntityLivingBase instanceof ChickenPet))
			return 0f;
        return this.handleRotationFloat((ChickenPet)par1EntityLivingBase, par2);
    }
	
	protected float handleRotationFloat(ChickenPet pet, float par2)
    {
        float f1 = pet.field_70888_h + (pet.field_70886_e - pet.field_70888_h) * par2;
        float f2 = pet.field_70884_g + (pet.destPos - pet.field_70884_g) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

}
