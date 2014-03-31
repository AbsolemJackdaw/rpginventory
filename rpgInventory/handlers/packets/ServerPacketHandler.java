package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class ServerPacketHandler {

	public static final int OPENRPGINV = 1;
	public static final int INVENTORY = 15;
	public static final int SMP_INVENTORY_SYNC = 20;

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		if(!event.packet.channel().equals("RpgInv"))
			return;
		
		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
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

			case SMP_INVENTORY_SYNC:
//				String otherPlayerName = dis.readUTF();
//				EntityPlayer other = world.getPlayerEntityByName(otherPlayerName);
//				System.out.println(other);
//				for (int i = 0; i < PlayerRpgInventory.get(other)
//						.getSizeInventory(); i++)
//					PlayerRpgInventory.get(other).setInventorySlotContents(i,
//							ByteBufUtils.readItemStack(buf));
				
				break;

			default:
				FMLLog.getLogger().info(
						"[SEVERE]Server : RpgInventory Send Unused packet !! Packet ID "
								+ guiId + ". Please report to mod author.");
				break;
			}
			dis.close();
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Server packet exception");
		}
	}
}
