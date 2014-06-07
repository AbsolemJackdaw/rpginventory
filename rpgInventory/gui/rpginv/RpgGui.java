package rpgInventory.gui.rpginv;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import rpgInventory.RpgInventoryMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RpgGui extends GuiContainer {

	private float xSize_lo;
	private float ySize_lo;

	public static String rpg = "Rpg";
	public static String hi = "Inventory";
	
	public static final ResourceLocation gui = new ResourceLocation("rpginventorymod:textures/gui/RpgInv.png");

	public static void renderPlayerinInv(Minecraft par0Minecraft, int par1,
			int par2, int par3, float par4, float par5) {
		
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef(par1, par2, 50.0F);
		GL11.glScalef((-par3), par3, par3);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var6 = par0Minecraft.thePlayer.renderYawOffset;
		float var7 = par0Minecraft.thePlayer.rotationYaw;
		float var8 = par0Minecraft.thePlayer.rotationPitch;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan(par5 / 40.0F)) * 20.0F, 1.0F, 0.0F,0.0F);
		par0Minecraft.thePlayer.renderYawOffset = (float) Math.atan(par4 / 40.0F) * 20.0F;
		par0Minecraft.thePlayer.rotationYaw = (float) Math.atan(par4 / 40.0F) * 40.0F;
		par0Minecraft.thePlayer.rotationPitch = -((float) Math.atan(par5 / 40.0F)) * 20.0F;
		par0Minecraft.thePlayer.rotationYawHead = par0Minecraft.thePlayer.rotationYaw;
		GL11.glTranslatef(0.0F, par0Minecraft.thePlayer.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(par0Minecraft.thePlayer,0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
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

	public RpgGui(EntityPlayer player, PlayerRpgInventory inv) {
		super(new PlayerRpgContainer(player, inv));
	}

	@Override
	public void actionPerformed(GuiButton button) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		if (button.id == 0) {
			RpgInventoryMod.proxy.openGUI(p, 1);
		}

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(gui);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRendererObj, rpg, (this.width / 2) + 39,(this.height / 2) - 23, 0xffffff);
		drawString(fontRendererObj, hi, (this.width / 2) + 39,(this.height / 2) - 15, 0xffffff);

		renderPlayerinInv(this.mc, posX + 51, posY + 75, 30, (posX + 51)- this.xSize_lo, (posY + 75) - 50 - this.ySize_lo);
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

		this.buttonList.add(new GuiButton(0, posX + 130, posY + 1, 50, 20,"Back"));

	}
}
