package rpgRogueBeast.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class RpgRBPacketHandler implements IPacketHandler {
	private Random rand = new Random(5);

	public static final int PETGUI = 2;
	public static final int CRYSTAL = 11;
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		handleRandom(packet, player);
	}
	private void handleRandom(Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try{
			int guiId = dis.readInt();
			switch (guiId) {
			case 14:
				new PacketTeleport(world, p, dis, rand);
				break;
			case PETGUI:
				new PacketPetGui(dis, p);
				break;
			case CRYSTAL:
				new PacketCrystal(dis, p);
				break;
				
			default:
				break;
			}
		}catch(Throwable e){
			FMLLog.getLogger().info("Failed to process packet. rpg rb packethandler.");
			e.printStackTrace();
		}
	}
}
