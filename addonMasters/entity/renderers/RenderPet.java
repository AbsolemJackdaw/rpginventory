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
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import addonMasters.entity.BMPetImpl;

public class RenderPet extends RenderLiving {

	public RenderPet() {
		super(new ModelCow(), 0.5F);
	}

	@Override
	public void doRender(Entity pet, double par2, double par4, double par6,
			float par8, float par9) {
		this.renderCow((BMPetImpl) pet, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(EntityLiving pet, double par2, double par4,
			double par6, float par8, float par9) {
		this.renderCow((BMPetImpl) pet, par2, par4, par6, par8, par9);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return ((BMPetImpl) entity).getTexture();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase pet, float par2) {
		this.scalePet((BMPetImpl) pet, par2);
	}

	public void renderCow(BMPetImpl pet, double par2, double par4, double par6,
			float par8, float par9) {

		this.mainModel = pet.getModel();
		super.doRender(pet, par2, par4, par6, par8, par9);
		if (pet.riddenByEntity == null) {
			super.func_147906_a(pet,
					pet.getEntityName() + " Lvl." + pet.getLevel(), par2,
					(par4 + pet.height) - 0.2F, par6, 32);
		} else {
			// render living label
			super.func_147906_a(pet,
					pet.getEntityName() + " Lvl." + pet.getLevel(), par2,
					((par4 + pet.height) - 0.2F) + pet.riddenByEntity.yOffset
					+ pet.riddenByEntity.ySize, par6, 32);
		}

	}

	protected void scalePet(BMPetImpl pet, float par2) {
		float var3 = pet.getPetSize();
		GL11.glScalef(var3, var3, var3);

	}
}
