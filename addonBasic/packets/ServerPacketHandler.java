package addonBasic.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class ServerPacketHandler {

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		ByteBuf buf = event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		try {

		} catch (Exception e) {
			System.out.println("[SEVERE] SERVER~ Error occured with handling packets in Mage Berserker Archer Addon. ");
			e.printStackTrace();
		}
	}
}
