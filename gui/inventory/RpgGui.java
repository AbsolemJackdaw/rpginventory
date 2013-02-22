package RpgInventory.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import RpgInventory.AARpgBaseClass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RpgGui extends GuiContainer
{
	private float	xSize_lo;
	private float	ySize_lo;

	public RpgGui(EntityPlayer player, RpgInventory inv)
	{
		super(new RpgContainer(player, inv));
		//		 xSize = 176;
		//		 ySize = 166;
	}

	public static String	hi	= "Rpg Inventory";

	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{

		int var4 = this.mc.renderEngine.getTexture("/subaraki/RpgInv.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = this.height;
		int var6 = this.width;
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRenderer, hi, this.width / 2 + 15, this.height / 2 - 15, 0xffffff);
		renderPlayerinInv(this.mc, posX + 51, posY + 75, 30, (float) (posX + 51) - this.xSize_lo, (float) (posY + 75 - 50) - this.ySize_lo);
	}

	public static void renderPlayerinInv(Minecraft par0Minecraft, int par1, int par2, int par3, float par4, float par5)
	{
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par1, (float) par2, 50.0F);
		GL11.glScalef((float) (-par3), (float) par3, (float) par3);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var6 = par0Minecraft.thePlayer.renderYawOffset;
		float var7 = par0Minecraft.thePlayer.rotationYaw;
		float var8 = par0Minecraft.thePlayer.rotationPitch;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		par0Minecraft.thePlayer.renderYawOffset = (float) Math.atan((double) (par4 / 40.0F)) * 20.0F;
		par0Minecraft.thePlayer.rotationYaw = (float) Math.atan((double) (par4 / 40.0F)) * 40.0F;
		par0Minecraft.thePlayer.rotationPitch = -((float) Math.atan((double) (par5 / 40.0F))) * 20.0F;
		par0Minecraft.thePlayer.rotationYawHead = par0Minecraft.thePlayer.rotationYaw;
		GL11.glTranslatef(0.0F, par0Minecraft.thePlayer.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(par0Minecraft.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		par0Minecraft.thePlayer.renderYawOffset = var6;
		par0Minecraft.thePlayer.rotationYaw = var7;
		par0Minecraft.thePlayer.rotationPitch = var8;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	public void initGui()
	{
		super.initGui();
		this.controlList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		this.controlList.add(new GuiButton(0, posX + 115, posY + 20, 50, 20, "Back"));
	}

	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button)
	{

		if (button.id == 0)
		{
			EntityPlayer p = ModLoader.getMinecraftInstance().thePlayer;

			AARpgBaseClass.proxy.openGUI(p, 1);

			// ModLoader.openGUI(p, new AlternativeGui(Minecraft.getMinecraft().thePlayer));
		}

	}
}
