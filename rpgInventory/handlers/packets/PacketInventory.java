package rpgInventory.handlers.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.WorldServer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketInventory {

	/** Destined for the owner of the inventory*/ 
	public static void sendPacket(EntityPlayer player, PlayerRpgInventory inv){

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		try {
			out.writeInt(RpgPacketHandler.INVENTORY);

			Packet.writeItemStack(inv.armorSlots[0], out);
			Packet.writeItemStack(inv.armorSlots[1], out);
			Packet.writeItemStack(inv.armorSlots[2], out);
			Packet.writeItemStack(inv.armorSlots[3], out);
			Packet.writeItemStack(inv.armorSlots[4], out);
			Packet.writeItemStack(inv.armorSlots[5], out);
			Packet.writeItemStack(inv.armorSlots[6], out);

			PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload("RpgInv", out.toByteArray()),
					(Player)player);

		} catch (Exception ex) {
//			ex.printStackTrace();
		}
	}

	/**Destined to all other players*/
	public static void sendServerPacket(EntityPlayer player){

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		try {
			out.writeInt(RpgPacketHandler.SMP_INVENTORY_SYNC);
			out.writeUTF(player.username);
			Packet.writeItemStack(inv.armorSlots[0], out);
			Packet.writeItemStack(inv.armorSlots[1], out);
			Packet.writeItemStack(inv.armorSlots[2], out);
			Packet.writeItemStack(inv.armorSlots[3], out);
			Packet.writeItemStack(inv.armorSlots[4], out);
			Packet.writeItemStack(inv.armorSlots[5], out);
			Packet.writeItemStack(inv.armorSlots[6], out);

			if(!player.worldObj.isRemote){
				((WorldServer)player.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(player,
						new Packet250CustomPayload("RpgInv", out.toByteArray()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
