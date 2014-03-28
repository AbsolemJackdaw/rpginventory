package rpgInventory.handlers.oldpackets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.world.WorldServer;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.ClientPacketHandler;
import rpgInventory.handlers.packets.ServerPacketHandler;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class PacketInventory {

	/** Destined for the owner of the inventory */
	public static void sendPacket(EntityPlayerMP player, PlayerRpgInventory inv) {

		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);

		try {


			out.writeInt(ClientPacketHandler.INVENTORY);

			for(int i = 0; i < inv.getSizeInventory(); i++){
//				if(inv.armorSlots[i] != null)
					//CompressedStreamTools.write(inv.armorSlots[i].getTagCompound(),out);
				ByteBufUtils.writeItemStack(buf, inv.armorSlots[i]);
			}

			//			ByteBufUtils.writeItemStack(inv.armorSlots[0], out);
			//			Packet.writeItemStack(inv.armorSlots[1], out);
			//			Packet.writeItemStack(inv.armorSlots[2], out);
			//			Packet.writeItemStack(inv.armorSlots[3], out);
			//			Packet.writeItemStack(inv.armorSlots[4], out);
			//			Packet.writeItemStack(inv.armorSlots[5], out);
			//			Packet.writeItemStack(inv.armorSlots[6], out);

			mod_RpgInventory.Channel.sendTo(new FMLProxyPacket(buf,"RpgInv"), player);

			//			PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload(
			//					"RpgInv", out.toByteArray()), (Player) player);

			out.close();
		} catch (Exception ex) {
			//									 ex.printStackTrace();
			//			 System.out.println("caught exception while processing inventory packet");
		}
	}

	/** Destined to all other players */
	public static void sendServerPacket(EntityPlayer player) {

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);

		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);

		try {
			out.writeInt(ServerPacketHandler.SMP_INVENTORY_SYNC);
			out.writeUTF(player.getDisplayName());

			for(int i = 0; i < inv.getSizeInventory(); i++)
				ByteBufUtils.writeItemStack(buf, inv.armorSlots[i]);
			//				CompressedStreamTools.write(inv.armorSlots[i].getTagCompound(), out);
			//			Packet.writeItemStack(inv.armorSlots[0], out);
			//			Packet.writeItemStack(inv.armorSlots[1], out);
			//			Packet.writeItemStack(inv.armorSlots[2], out);
			//			Packet.writeItemStack(inv.armorSlots[3], out);
			//			Packet.writeItemStack(inv.armorSlots[4], out);
			//			Packet.writeItemStack(inv.armorSlots[5], out);
			//			Packet.writeItemStack(inv.armorSlots[6], out);

			if (!player.worldObj.isRemote) {

				TargetPoint point = new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 60);
				mod_RpgInventory.Channel.sendToAllAround(new FMLProxyPacket(buf,"RpgInv"), point);
				//				((WorldServer) player.worldObj).getEntityTracker()
				//						.sendPacketToAllPlayersTrackingEntity(
				//								player,
				//								new Packet250CustomPayload("RpgInv", out
				//										.toByteArray()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
