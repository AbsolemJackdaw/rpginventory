package rpgInventory.handlers.oldpackets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.ClientPacketHandler;
import rpgInventory.handlers.packets.ServerPacketHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class PacketInventory {

	/** Destined for the owner of the inventory */
	public static void sendPacket(EntityPlayerMP player, PlayerRpgInventory inv) {


		try {
			ByteBuf buf = Unpooled.buffer();
			ByteBufOutputStream out = new ByteBufOutputStream(buf);
			out.writeInt(ClientPacketHandler.INVENTORY);

			for (int i = 0; i < inv.getSizeInventory(); i++)
				ByteBufUtils.writeItemStack(buf, inv.armorSlots[i]);

			if (!player.worldObj.isRemote)
				RpgInventoryMod.Channel.sendTo(new FMLProxyPacket(buf,
						"RpgInv"), player);
			out.close();
		} catch (Exception ex) {
		}
	}

	/** Destined to all other players */
	public static void sendServerPacket(EntityPlayer player) {

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);

		try {
			ByteBuf buf = Unpooled.buffer();
			ByteBufOutputStream out = new ByteBufOutputStream(buf);
			out.writeInt(ServerPacketHandler.SMP_INVENTORY_SYNC);
			out.writeUTF(player.getCommandSenderName());

			for (int i = 0; i < inv.getSizeInventory(); i++)
				ByteBufUtils.writeItemStack(buf, inv.armorSlots[i]);

			TargetPoint point = new TargetPoint(player.dimension,
					player.posX, player.posY, player.posZ, 60);

			RpgInventoryMod.Channel.sendToAllAround(new FMLProxyPacket(buf,"RpgInv"), point);

			out.close();
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
	}
}
