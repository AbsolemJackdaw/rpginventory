package WWBS.wwbs.wwbs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBS extends InventoryEffectRenderer {


	private static InventoryBasic invGetinv = new InventoryBasic("tmp", true, 45);

	private float currentScroll = 0.0F;

	private boolean isScrolling = false;


	private float xSize_lo;
	private float ySize_lo;

	public GuiBS(EntityPlayer player) {
		super(null); // must be a container TODO

	}
	public static String rpg = "Rpg";
	public static String hi = "Inventory";

	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
		
		int k = this.guiLeft;
        int l = this.guiTop;
        int i1 = k + 175;
        int j1 = l + 18;
        int k1 = i1 + 14;
        int l1 = j1 + 112;
		if (this.isScrolling)
		{
			this.currentScroll = ((float)(par2 - j1) - 7.5F) / ((float)(l1 - j1) - 15.0F);

			if (this.currentScroll < 0.0F)
			{
				this.currentScroll = 0.0F;
			}

			if (this.currentScroll > 1.0F)
			{
				this.currentScroll = 1.0F;
			}

			((ContainerBank)this.inventorySlots).scrollTo(this.currentScroll);
		}

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
	public void initGui() 
	{
		super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		this.buttonList.add(new GuiButton(0, posX + 130, posY + 1, 50, 20, "button"));
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


	private boolean needsScrollBars()
	{
		return ((ContainerBank)this.inventorySlots).hasMoreThan1PageOfItemsInList();
	}

	public void handleMouseInput()
	{
		super.handleMouseInput();
		int i = Mouse.getEventDWheel();

		if (i != 0 && this.needsScrollBars())
		{
			int j = ((ContainerBank)this.inventorySlots).itemList.size() / 9 - 5 + 1;

			if (i > 0)
			{
				i = 1;
			}

			if (i < 0)
			{
				i = -1;
			}

			this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);

			if (this.currentScroll < 0.0F)
			{
				this.currentScroll = 0.0F;
			}

			if (this.currentScroll > 1.0F)
			{
				this.currentScroll = 1.0F;
			}

			((ContainerBank)this.inventorySlots).scrollTo(this.currentScroll);
		}
	}
	static InventoryBasic getInventory()
	{
		return invGetinv;
	}
}
