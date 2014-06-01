package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class ClientPacketHandler extends ServerPacketHandler {

	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {

		if(!event.packet.channel().equals("RpgInv")) {
			return;
		}

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		ByteBuf buf = event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		try {

			int guiId = dis.readInt();
			switch (guiId) {
			case OPENRPGINV:
				FMLNetworkHandler.openGui(p, RpgInventoryMod.instance, 1,
						world, x, y, z);
				break;

			case INVENTORY:

				for (int i = 0; i < PlayerRpgInventory.get(p)
						.getSizeInventory(); i++) {
					PlayerRpgInventory.get(p).setInventorySlotContents(i,
							ByteBufUtils.readItemStack(buf));
				}

				break;

			case SMP_INVENTORY_SYNC:
				String otherPlayerName = dis.readUTF();
				EntityPlayer other = world.getPlayerEntityByName(otherPlayerName);

				if(other != null){
					PlayerRpgInventory rpg = PlayerRpgInventory.get(other);
					if(rpg != null){
						for (int i = 0; i < 7; i++){
							rpg.setInventorySlotContents(i,ByteBufUtils.readItemStack(buf));
						}
					}else{
						FMLLog.getLogger().info("packet info. 'inventory' was null. dropping packet");
					}
				}else{
					FMLLog.getLogger().info("packet info. 'other' was null. dropping packet");
				}

				break;

			default:
				FMLLog.getLogger().info(
						"[SEVERE] Client:  RpgInventory Send Unused packet !! Packet ID "
								+ guiId + ". Please report to mod author.");
				break;
			}
			dis.close();
		} catch (IOException e) {
		}
	}

}
