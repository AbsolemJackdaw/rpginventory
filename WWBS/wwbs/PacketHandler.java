package WWBS.wwbs;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	
	public int x1;
	public int y1;
	public int z1;
	
	public static PacketHandler instance;
	public PacketHandler()
	{
		instance = this;
	}
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		this.handlePacket(packet, player);

		if(packet.channel.equals("wwbsData"))
		{
			this.handlePacket(packet, player);
		}
	}

	public void handlePacket(Packet250CustomPayload packet, Player player)
	{

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int guiId = dis.readInt();
			if(guiId == 1)
			{
				x1 = dis.readInt();
				y1 = dis.readInt();
				z1 = dis.readInt();
				
			}
			if(guiId == 2)
			{
				x1 = dis.readInt();
				y1 = dis.readInt();
				z1 = dis.readInt();
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
