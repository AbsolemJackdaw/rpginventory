package rpgInventory.block.te;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiMF extends GuiContainer {

	private TEMold forgeInventory;

	public GuiMF(InventoryPlayer inventory, TEMold te) {
		super(new MoldContainer(inventory, te));
		forgeInventory = te;
		this.xSize = 176;
		this.ySize = 184;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
		fontRenderer.drawString("Mold Forge", 5, (ySize - 180), 0xffffff);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		mc.renderEngine.func_110577_a(new ResourceLocation("rpginventorymod:textures/gui/Forge.png"));
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

		if (forgeInventory.isBurning()) {
			int burn = forgeInventory.getTimeRemainingScaled(33);
			drawTexturedModalRect(j + 33, k + 58/*-burn+33*/, 176, 0/*33-burn*/, 45, burn);
		}

		int update = forgeInventory.getProgressScaled(60);
		drawTexturedModalRect(j + 78, k + 43, 176, 33, update, 15);

				j += 96;
				k += 51;
				float ratio = 0.0625F;
				float x = 1 * ratio;
				float y = 6 * ratio;

				mc.renderEngine.func_110577_a(new ResourceLocation("rpginventorymod:textures/gui/RPGinventoryTM.png"));
		        GL11.glEnable(GL11.GL_BLEND);
		        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.85F);
		
		        GL11.glBegin(GL11.GL_QUADS);
		        GL11.glTexCoord2f(x, y);
		        GL11.glVertex3f(j, k, 0);
		        GL11.glTexCoord2f(x, y + ratio);
		        GL11.glVertex3f(j, k + 16, 0);
		        GL11.glTexCoord2f(x + ratio, y + ratio);
		        GL11.glVertex3f(j + 16, k + 16, 0);
		        GL11.glTexCoord2f(x + ratio, y);
		        GL11.glVertex3f(j + 16, k, 0);
		        GL11.glEnd();
		
		
		        x = 2 * ratio;
		        j -= 48;
		        k -= 19;
		        GL11.glBegin(GL11.GL_QUADS);
		        GL11.glTexCoord2f(x, y);
		        GL11.glVertex3f(j, k, 0);
		        GL11.glTexCoord2f(x, y + ratio);
		        GL11.glVertex3f(j, k + 16, 0);
		        GL11.glTexCoord2f(x + ratio, y + ratio);
		        GL11.glVertex3f(j + 16, k + 16, 0);
		        GL11.glTexCoord2f(x + ratio, y);
		        GL11.glVertex3f(j + 16, k, 0);
		        GL11.glEnd();
		
		
		        x = 0 * ratio;
		        j += 48;
		        GL11.glBegin(GL11.GL_QUADS);
		        GL11.glTexCoord2f(x, y);
		        GL11.glVertex3f(j, k, 0);
		        GL11.glTexCoord2f(x, y + ratio);
		        GL11.glVertex3f(j, k + 16, 0);
		        GL11.glTexCoord2f(x + ratio, y + ratio);
		        GL11.glVertex3f(j + 16, k + 16, 0);
		        GL11.glTexCoord2f(x + ratio, y);
		        GL11.glVertex3f(j + 16, k, 0);
		        GL11.glEnd();
		
		        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}