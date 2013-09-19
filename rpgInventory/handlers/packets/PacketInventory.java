package rpgInventory.handlers.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketInventory {

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
			ex.printStackTrace();
		}
	}
}
