package WWBS.wwbs.wwme;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		hi = "Welcome " + player.username;
		inv = player.username + "'s Inventory";
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation("subaraki:me.png"));
		int var5 = 187;
		int var6 = 222;
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY - 25, 0, 0, var5, var6);
		drawString(fontRenderer, inv, (this.width / 2) - 75,
				(this.height / 2) + 17, 0xffffff);
		drawString(fontRenderer, "Buy", (this.width / 2) - 55,
				(this.height / 2) - 30, 0xffffff);
		drawString(fontRenderer, "Buy", (this.width / 2) + 5,
				(this.height / 2) - 30, 0xffffff);
		drawString(fontRenderer, "Buy", (this.width / 2) + 65,
				(this.height / 2) - 30, 0xffffff);
		drawString(fontRenderer, "Sell", (this.width / 2) - 55,
				(this.height / 2) - 90, 0xffffff);
		drawString(fontRenderer, "Sell", (this.width / 2) + 5,
				(this.height / 2) - 90, 0xffffff);
		drawString(fontRenderer, "Sell", (this.width / 2) + 65,
				(this.height / 2) - 90, 0xffffff);

	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = par1;
		this.ySize_lo = par2;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
	}
}
