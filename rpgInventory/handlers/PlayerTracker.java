package rpgInventory.handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.oldpackets.PacketInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

/**
 * To catch login/Logouts for reading/writing the Inventory
 */
public class PlayerTracker{

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerChangedDimensionEvent e) {
		if (!e.player.worldObj.isRemote) {
			PacketInventory.sendPacket((EntityPlayerMP) e.player,
					PlayerRpgInventory.get(e.player));
		}
		PlayerRpgInventory.get(e.player);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e) {
		if (!e.player.worldObj.isRemote) {
			PacketInventory.sendPacket((EntityPlayerMP) e.player,
					PlayerRpgInventory.get(e.player));
		}
		PlayerRpgInventory.get(e.player);
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent e) {
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if (!e.player.worldObj.isRemote) {
			PacketInventory.sendPacket((EntityPlayerMP) e.player,
					PlayerRpgInventory.get(e.player));
		}
		PlayerRpgInventory.get(e.player);
	}
}
