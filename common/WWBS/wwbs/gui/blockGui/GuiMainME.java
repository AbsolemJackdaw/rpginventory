package WWBS.wwbs.gui.blockGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import RpgInventory.mod_RpgInventory;
import RpgInventory.gui.inventory.RpgContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiMainME extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 176;
	private int ySize = 90;

	public GuiMainME(EntityPlayer player) {
		super();
	}
	public static String hi = "World Wide Massive Exchanging";
	public static String hi2 = "System";

	@Override
	public void initGui() {
		//		super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(0, posX+15 , posY+30 , 80, 20, "Acces the M.E.S"));
		this.buttonList.add(new GuiButton(1, posX+15 , posY+55 , 80, 20, "Exit M.E.S"));

	}
	public void drawScreen(int par1, int par2, float par3) {
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/subaraki/MainGui.png");
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawTexturedModalRect(posX*2-15, posY, 50, 90, 80, ySize);
		drawString(fontRenderer, hi, this.width / 2-84, this.height / 2-40, 0xffffff);
		drawString(fontRenderer, hi2, this.width / 2-84, this.height / 2-30, 0xffffff);

		super.drawScreen(par1, par2, par3);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		if (button.id == 0) 
		{

		} 
		else if (button.id == 1) 
		{
			mc.thePlayer.closeScreen();
		}
	}
}
