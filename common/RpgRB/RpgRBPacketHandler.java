package RpgRB;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


public class RpgRBPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;
		EntityPlayer entityplayer = (EntityPlayer)player;
		World World = entityplayer.worldObj;
		ObjectInputStream dis;
		try
		{
			dis = new ObjectInputStream(new ByteArrayInputStream(packet.data));
			int guiId = dis.readInt();
			int petID = dis.readInt();
			String petName = dis.readUTF();
			dis.close();

			if(guiId == 1)
			{
				BoarPet boar = (BoarPet)World.getEntityByID(petID);
				boar.setName(petName);
			}
			if(guiId == 2)
			{
				BullPet bull = (BullPet)World.getEntityByID(petID);
				bull.setName(petName);
			}
			if(guiId == 3)
			{
				SpiderPet spider = (SpiderPet)World.getEntityByID(petID);
				spider.setName(petName);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("nameRecievingFailed");
		}
	}

}
