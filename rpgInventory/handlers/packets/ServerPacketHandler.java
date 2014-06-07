package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;

public class ServerPacketHandler {

	public static final int OPENRPGINV = 1;
	public static final int INVENTORY = 15;
	public static final int SMP_INVENTORY_SYNC = 20;

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		if(!event.packet.channel().equals("RpgInv")) {
			return;
		}

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		try {

			int guiId = dis.readInt();

			switch (guiId) {
			case OPENRPGINV:
				FMLNetworkHandler.openGui(p, RpgInventoryMod.instance, 1, world, x, y, z);
				break;

			default:
				FMLLog.getLogger().info(
						"[SEVERE]Server : RpgInventory Send Unused packet !! Packet ID "
								+ guiId + ". Please report to mod author.");
				break;
			}
			dis.close();
		} catch (IOException e) {
			System.out.println("Server packet exception");
			e.printStackTrace();
		}
	}
}
