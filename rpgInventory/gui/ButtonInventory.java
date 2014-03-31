/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.gui;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.handlers.packets.ServerPacketHandler;
import rpgInventory.RpgInventoryMod;

import java.io.IOException;

/**
 * 
 * @author Richard Smith
 */
@SideOnly(Side.CLIENT)
public class ButtonInventory extends GuiButton {

	private EntityPlayer player;

	public ButtonInventory(int par1, int par2, int par3, int x, int y,
			String par4Str) {
		super(par1, par2, par3, par4Str);
		player = Minecraft.getMinecraft().thePlayer;
		this.width = x;
		this.height = y;
		this.field_146123_n /* drawButton boolean */= true;
		this.enabled = true;
	}

	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {

		if ((par2 >= this.xPosition) && (par2 <= (this.xPosition + this.width)))
			if ((par3 >= this.yPosition)
					&& (par3 <= (this.yPosition + this.height))) {

				if (this.displayString.equals("Rpg Inventory"))
					/* Open Rpg Inventory gui */
					try {
						ByteBuf buf = Unpooled.buffer();
						ByteBufOutputStream out = new ByteBufOutputStream(buf);
						out.writeInt(ServerPacketHandler.OPENRPGINV);
						RpgInventoryMod.Channel
								.sendToServer(new FMLProxyPacket(buf, "RpgInv"));

						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				else if (this.displayString.equals("Close")) {
                    // player.closeScreen();
//                    player.openContainer = player.inventoryContainer;
                    Minecraft.getMinecraft().currentScreen = null;
                    Minecraft.getMinecraft().setIngameFocus();
                }
				// Minecraft.getMinecraft().getSoundHandler().playSoundFX("random.click",
				// 1.0F, 1.0F);
				return false;
			}
		return super.mousePressed(par1Minecraft, par2, par3);
	}

}
