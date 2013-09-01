package rpgInventory.renderer.items.shields;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

public class ArcherShield extends ShieldRenderer{

	public ArcherShield(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glTranslatef(-0.55f, 0.0f, 0);
	}

	@Override
	public void renderEquipped() {
		GL11.glScalef(1.5f,1.5f,1.5f);

		GL11.glRotatef(50, 0, 1, 0);
		GL11.glRotatef(-50, 1, 0, 0);
		GL11.glTranslatef(-0.7f, -0.7f, 0.2f);
		GL11.glScalef(1.15F,1.15f,1.15F);
	}

	@Override
	public void renderScale() {
		GL11.glScalef(2F,2F,2F);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glTranslatef(1f, -0.2f, 0.6f);
		float scale = 1.5f;
		GL11.glScalef(scale,scale,scale);
		GL11.glRotatef(180, 0, 1, 0);
	}

	@Override
	public void renderInventory() {
		GL11.glRotatef(-90, 0f, 1f, 0f);
		GL11.glTranslatef(-0.6f, -0.45f, 0f);
	}
}
