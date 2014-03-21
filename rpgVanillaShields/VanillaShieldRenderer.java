package rpgVanillaShields;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import rpgInventory.renderer.items.shields.ShieldRenderer;

public class VanillaShieldRenderer extends ShieldRenderer {

	public VanillaShieldRenderer(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glTranslatef(-0.5f, -1.2f, 0);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-50, 0, 1, 0);
		GL11.glRotatef(-50, 1, 0, 0);
		GL11.glTranslatef(-0.7f, -0.5f, -0.9f);
		GL11.glScalef(1.15F, 1.15f, 1.15F);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glTranslatef(0f, -1.2f, -0.6f);
		GL11.glScalef(1.F, 1.f, 1.F);
	}

	@Override
	public void renderInventory() {
		GL11.glRotatef(90, 0f, 1f, 0f);
		GL11.glTranslatef(-0.6f, -0.3f, 0f);
	}

	@Override
	public void renderScale() {
		GL11.glRotatef(180, 01, 0, 0);
		GL11.glScalef(1F, 1F, 1F);
	}
}
