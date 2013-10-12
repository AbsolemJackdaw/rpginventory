package WWBS.wwbs.gui.blockGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMainB extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 176;
	private int ySize = 90;
	public int posX;
	public int posY;
	public int posZ;
	
	public GuiMainB(EntityPlayer player,int x, int y, int z ) {
		super();

	}
	public static String hi = "World Wide Banking System";
	
	@Override
	public void initGui() {
		//		super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(0, posX+15 , posY+30 , 80, 20, "Acces Account"));
		this.buttonList.add(new GuiButton(1, posX+15 , posY+55 , 80, 20, "Exit Bank"));

	}
	public void drawScreen(int par1, int par2, float par3) {
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation("subaraki:MainGui.png"));
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawTexturedModalRect(posX*2, posY+5, 0, 90, 45, ySize);
		drawString(fontRenderer, hi, this.width / 2-84, this.height / 2-40, 0xffffff);
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
