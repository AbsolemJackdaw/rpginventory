package addonBasic.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class ClientPacketHandler extends ServerPacketHandler{

	public static final int MAGE1 = 3;
	public static final int BERSERKER = 4;
	public static final int ARCHER = 5;
	public static final int MAGE2 = 7;

	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {

		if(!event.packet.channel().equals("BaseAddon"))
			return;
		
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		ByteBuf buf = event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;


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
			System.out.println("[SEVERE] CLIENT~ Error occured with handling packets in Mage Berserker Archer Addon. ");
			e.printStackTrace();
		}
	}
}
