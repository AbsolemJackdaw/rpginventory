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

@SideOnly(Side.CLIENT)
public class GuiMainME extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 0;
	private int ySize = 0;

	public GuiMainME(EntityPlayer player) {
		super();
	}
	public static String rpg = "Rpg";
	public static String hi = "Inventory";

	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_98187_b("/subaraki/RpgInv.png");
		int var5 = this.height;
		int var6 = this.width;
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRenderer, rpg, this.width / 2 + 39, this.height / 2 - 23, 0xffffff);
		drawString(fontRenderer, hi, this.width / 2 + 39, this.height / 2 - 15, 0xffffff);
	}


	public void initGui() {
		super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		this.buttonList.add(new GuiButton(0, posX + 130, posY + 1, 50, 20, "Back"));
		if (mod_RpgInventory.hasRogue == true) {
			this.buttonList.add(new GuiButton(1, posX + 130, posY + 22, 50, 20, "Pet"));
		}

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

		}
	}
}
