package rpgInventory.handlers.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class PacketHelper {

	/** Destined for the owner of the inventory */
	public static void syncOwnInventory(EntityPlayerMP player, PlayerRpgInventory inv) {
		RpgInventoryMod.SNW.sendTo(new PacketSyncOwnInventory(player), player);
	}

//	@Deprecated
//	/** Destined to all other players */
//	public static void sendDataToPlayersAround(EntityPlayer player) {
//
////		TargetPoint point = new TargetPoint(player.dimension,player.posX, player.posY, player.posZ, 60);
////
////		RpgInventoryMod.SNW.sendToAllAround(new PacketSyncOtherInventory(player), point);
//	}
}
