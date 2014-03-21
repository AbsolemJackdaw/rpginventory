package WWBS.wwbs;

import ibxm.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class PacketHandler implements IPacketHandler {

	public static PacketHandler instance;

	public PacketHandler() {
		instance = this;
	}

	public void handlePacket(Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		try {
			int guiId = dis.readInt();
			switch (guiId) {
			case 0:
				FMLNetworkHandler.openGui(p, mod_wwbs.instance, 2, p.worldObj,
						dis.readInt(), dis.readInt(), dis.readInt());
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		this.handlePacket(packet, player);

		if (packet.channel.equals("wwbsData")) {
			this.handlePacket(packet, player);
		}
	}

}
