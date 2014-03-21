package rpgInventory.handlers;

import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.PacketInventory;

/**
 * To catch login/Logouts for reading/writing the Inventory
 * 
 * @author AbrarSyed
 */
public class PlayerTracker implements IPlayerTracker {

	// noone cares about this....
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		PacketInventory.sendPacket(player, PlayerRpgInventory.get(player));
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PacketInventory.sendPacket(player, PlayerRpgInventory.get(player));
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		// write the Inventory and then remove it from Ram.
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		PacketInventory.sendPacket(player, PlayerRpgInventory.get(player));
	}
}
