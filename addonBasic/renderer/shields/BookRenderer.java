package addonBasic.renderer.shields;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import rpgInventory.renderer.ShieldRenderer;

public class BookRenderer extends ShieldRenderer {

	public BookRenderer(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glTranslatef(-0.9f, 0f, 0.6f);
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glScalef(1.5F, 1.5f, 1.5F);

	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-50, 0, 1, 0);
		GL11.glRotatef(-50, 1, 0, 0);

		GL11.glTranslatef(-1.2f, -0.2f, -1f);
		GL11.glScalef(2F, 2f, 2F);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glTranslatef(0f, -1.5f, -1f);
		GL11.glScalef(1F, 1.5f, 1F);
		GL11.glRotatef(-50, 0, 1, 0);
	}

	@Override
	public void renderInventory() {
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glRotatef(90, 1, 0, 0);
		float scale = 3f;
		GL11.glScalef(scale, scale, scale);

		GL11.glTranslatef(-0.6f, -0.4f, -0f);
	}

	@Override
	public void renderScale() {
		GL11.glRotatef(180, 01, 0, 0);
		GL11.glScalef(1F, 1F, 1F);
	}
}
