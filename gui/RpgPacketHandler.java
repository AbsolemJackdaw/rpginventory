package RpgInventory.gui;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import RpgInventory.AARpgBaseClass;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class RpgPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		if (packet.channel.equals("RPGInv")) {
			handleRandom(packet, player);
		}

	}

	private void handleRandom(Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer)player;
		World world = p.worldObj;
		int x = (int)p.posX;
		int y = (int)p.posY;
		int z = (int)p.posZ;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		System.out.println("Packet was send and recieved.");
		if(!world.isRemote)
		{
			try
			{
				int guiId = dis.readInt();
				dis.close(); 

				if(guiId == 1)
				{
					FMLNetworkHandler.openGui(p, AARpgBaseClass.instance, 1, world, x, y, z);
					System.out.println("gui launched ");
				}

			}
			catch (IOException e)
			{
				System.out.println("failed");

				e.printStackTrace();
			}
		}
	}    
}
