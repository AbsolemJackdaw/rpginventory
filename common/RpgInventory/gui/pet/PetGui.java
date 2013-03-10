package RpgInventory.gui.pet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PetGui extends GuiScreen {
	private BufferedImage img;
	private int imgID = 2055;
	public static BoarPet boar;
	public static BullPet bull;
	public static SpiderPet spider;
	Entity thePet;
	int petType; /*Spider 3, bull 2, boar 1*/
	EntityPlayer p;
	public PetGui(EntityPlayer p1, int petID, int type, Entity ent)
	{
		World world = p1.worldObj;
		thePet = ent;
		p= p1;
		petType = type;
		ent.worldObj.getEntityByID(petID);
	}

	private GuiTextField textfield;


	public final int xSizeOfTexture = 181;
	public final int ySizeOfTexture = 156;

	public void initGui() {
		
		int var4 = this.mc.renderEngine.getTexture("/subaraki/petgui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		controlList.clear();
		controlList.add(new GuiButton(1, posX +90, posY+100, 70, 20, "Done"));
		controlList.add(new GuiButton(2, posX +235, posY+1, 20, 20, "X"));

		textfield = new GuiTextField(fontRenderer, posX+68 , posY+60, 120, 20);
		textfield.setText(thePet.getEntityName());
		textfield.setMaxStringLength(32);

	}
	protected void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			ByteArrayOutputStream bytes = new ByteArrayOutputStream() ;
			ObjectOutput out;
			try {
				out = new ObjectOutputStream(bytes);			    
				out.writeInt(petType);
				out.writeInt(thePet.entityId);
				out.writeUTF(textfield.getText());
				out.close();
				Packet250CustomPayload packet = new Packet250CustomPayload("RpgRBPacket",bytes.toByteArray());
				PacketDispatcher.sendPacketToPlayer(packet, (Player)p);
				System.out.println(petType);
				System.out.println(thePet.entityId);
				System.out.println(textfield.getText());
			}
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("nameSendFailed");

			}
			this.mc.thePlayer.closeScreen();

		}
		if(guibutton.id == 2)
		{
			this.mc.thePlayer.closeScreen();
		}
	}
	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		textfield.textboxKeyTyped(c, i);
	}
	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		textfield.mouseClicked(i, j, k);
	}
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	public void onGuiClosed()
	{
	}
	public void drawScreen(int i, int j, float f)
	{
		drawDefaultBackground();
		try {
			int var4 = this.mc.renderEngine.getTexture("/subaraki/petgui.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture(var4);
			int posX = (this.width - xSizeOfTexture) / 2;
			int posY = (this.height - ySizeOfTexture) / 2;
			drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		}
		finally
		{
			textfield.drawTextBox();
		}
		super.drawScreen(i, j, f);
	}


}