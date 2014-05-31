package addonBasic.packets;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class ServerPacketHandler {

	public static final int MAGE1 = 3;
	public static final int BERSERKER = 4;
	public static final int ARCHER = 5;
	public static final int MAGE2 = 7;

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		if(!event.packet.channel().equals("BaseAddon")) {
			return;
		}

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();


		World world = p.worldObj;

		try {
			int guiID = dis.readInt();

			switch (guiID) {

			case MAGE1:
				new PacketMageHeal(dis, p, world);
				break;
			case BERSERKER:
				new PacketBerserker(world, p, dis);
				break;
			case ARCHER:
				new PacketArcher(dis, p, world);
				break;
			case MAGE2:
				new PacketMageVortex(dis, world, p);
				break;

			}
		} catch (Exception e) {
			System.out.println("[SEVERE] SERVER~ Error occured with handling packets in Mage Berserker Archer Addon. ");
			e.printStackTrace();
		}
	}
}
