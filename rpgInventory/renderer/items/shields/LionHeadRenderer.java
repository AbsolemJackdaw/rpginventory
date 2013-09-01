package rpgInventory.renderer.items.shields;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

public class LionHeadRenderer extends ShieldRenderer{

	public LionHeadRenderer(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glTranslatef(0f, -1.2f, -0.1f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(50, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(-50, 0, 0, 1);

		GL11.glTranslatef(0.5f,0f,-0.2f);
		GL11.glScalef(1.15F,1.15f,1.15F);
	}

	@Override
	public void renderScale() {
		GL11.glRotatef(180, 01, 0, 0);
		GL11.glScalef(1F,1F,1F);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glTranslatef(0f, -.7f, -0.6f);
		GL11.glScalef(1.F,1.f,1.F);
		GL11.glRotatef(-90, 0, 1, 0);
	}

	@Override
	public void renderInventory() {
		GL11.glScalef(1f,1f,1f);
		GL11.glTranslatef(-0.25f, -0.05f, 0f);
	}
}
