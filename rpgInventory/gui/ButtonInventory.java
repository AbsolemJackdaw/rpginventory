/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Richard Smith
 */
@SideOnly(Side.CLIENT)
public class ButtonInventory extends GuiButton {

	private EntityPlayer player;

	public ButtonInventory(int par1, int par2, int par3, String par4Str) {
		super(par1, par2, par3, par4Str);
		player = Minecraft.getMinecraft().thePlayer;
		this.width = 80;
		this.height = 20;
		this.field_146123_n /* drawButton boolean */= true;
		this.enabled = true;
	}

	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {

		if ((par2 >= this.xPosition) && (par2 <= (this.xPosition + this.width)))
			if ((par3 >= this.yPosition)
					&& (par3 <= (this.yPosition + this.height))) {

				if (this.displayString.equals("Rpg Inventory")) {

					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					DataOutputStream outputStream = new DataOutputStream(bytes);
					// try {
					// outputStream.writeInt(1);
					// Packet250CustomPayload packet = new
					// Packet250CustomPayload(
					// "RpgInv", bytes.toByteArray());
					// PacketDispatcher.sendPacketToServer(packet);
					// // System.out.println("Packet send");
					// } catch (IOException e) {
					// e.printStackTrace();
					// }
				} else if (this.displayString.equals("Close"))
					// player.closeScreen();
					player.openContainer = player.inventoryContainer;
				// Minecraft.getMinecraft().getSoundHandler().playSoundFX("random.click",
				// 1.0F, 1.0F);
				return false;
			}
		return super.mousePressed(par1Minecraft, par2, par3);
	}

}
