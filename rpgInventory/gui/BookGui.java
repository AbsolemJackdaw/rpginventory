package rpgInventory.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class BookGui extends GuiScreen
{
	public static String hi = "Wizard's Knowledge";
	public BookGui(EntityPlayer player)
	{}

	public final int xSizeOfTexture = 166;
	public final int ySizeOfTexture = 181;
	private EntityPlayer player;

	@Override
	public void drawScreen(int i, int j, float f){


		drawDefaultBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_110577_a(new ResourceLocation("rpginventorymod:textures/gui/bookgui.png"));
//		this.mc.renderEngine.bindTexture("/subaraki/bookgui.png");

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		//melon formula
		drawTexturedModalRect(posX+48, posY+32, 192, 48, 16, 16);//melon
		drawTexturedModalRect(posX+64, posY+32, 192, 0, 16, 16); //steve
		drawTexturedModalRect(posX+80, posY+32, 176, 16, 16, 16);   // =
		drawTexturedModalRect(posX+112, posY+32, 176, 48, 16, 16);   // heart
		// light formula
		drawTexturedModalRect(posX+48, posY+64, 192, 32, 16, 16);      //dust
		drawTexturedModalRect(posX+64, posY+64, 192, 0, 16, 16);    //steve
		drawTexturedModalRect(posX+80, posY+64, 176, 16, 16, 16);   // =
		drawTexturedModalRect(posX+112, posY+64, 176, 32, 16, 16);   // moon
		//fire formumula
		drawTexturedModalRect(posX+48, posY+92, 192, 64, 16, 16);      //powder
		drawTexturedModalRect(posX+64, posY+92, 176, 0, 16, 16);    //steve
		drawTexturedModalRect(posX+80, posY+92, 176, 16, 16, 16);   // =
		drawTexturedModalRect(posX+112, posY+92, 176, 64, 16, 16);   // charge
		//gold formula
		drawTexturedModalRect(posX+48, posY+124, 192, 80, 16, 16);      //ingot
		drawTexturedModalRect(posX+64, posY+124, 176, 0, 16, 16);    //steve
		drawTexturedModalRect(posX+80, posY+124, 176, 16, 16, 16);   // =
		drawTexturedModalRect(posX+112, posY+124, 176, 80, 16, 16);   // stand


		drawString(fontRenderer, hi, posX+40, posY+10 , 0xffffff);


		super.drawScreen(i, j, f);
	}

	public void initGui()
	{
		this.buttonList.clear();

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		this.buttonList.add(new GuiButton(0, posX+176,  posY, 20, 20, "X"));
	}
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	public void actionPerformed(GuiButton button){

		if(button.id == 0)
		{
			this.mc.thePlayer.closeScreen();
		}

	}
}