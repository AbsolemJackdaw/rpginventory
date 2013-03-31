package WWBS.wwbs.wwme;

import RpgInventory.mod_RpgInventory;
import WWBS.wwbs.wwbs.ContainerBank;
import WWBS.wwbs.wwbs.WwbsTe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiME extends GuiContainer {

	private float xSize_lo;
	private float ySize_lo;
	EntityPlayer player;
	public static String inv;
	public static String hi;
	
	public GuiME(EntityPlayer player, WwmeTE te) {
		super(new ContainerME(player.inventory, te));
		this.player = player;
		hi =  "Welcome "+ player.username;
		inv = player.username +"'s Inventory";
	}
	

	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/subaraki/me.png");
		int var5 = 187;
		int var6 = 222;
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY-25, 0, 0, var5, var6);
		drawString(fontRenderer, inv, this.width / 2 -75, this.height / 2 +17, 0xffffff);
		drawString(fontRenderer, "Buy", this.width / 2 -55, this.height / 2 - 30, 0xffffff);
		drawString(fontRenderer, "Buy", this.width / 2 +5, this.height / 2 - 30, 0xffffff);
		drawString(fontRenderer, "Buy", this.width / 2 +65, this.height / 2 - 30, 0xffffff);
		drawString(fontRenderer, "Sell", this.width / 2 -55, this.height / 2 - 90, 0xffffff);
		drawString(fontRenderer, "Sell", this.width / 2 +5, this.height / 2 - 90, 0xffffff);
		drawString(fontRenderer, "Sell", this.width / 2 +65, this.height / 2-90, 0xffffff);

	}

	public void initGui() {
		super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

//		this.buttonList.add(new GuiButton(0, posX , posY , 50, 20, "button"));
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
	}

}

