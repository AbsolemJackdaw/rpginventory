package rpgInventory.handlers;

import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.PacketInventory;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

/**
 * To catch login/Logouts for readingw/writing the Inventory
 *
 * @author AbrarSyed
 */
public class PlayerTracker implements IPlayerTracker {

    @Override
    public void onPlayerLogin(EntityPlayer player) {
    	    	PacketInventory.sendPacket(player, PlayerRpgInventory.get(player));
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        // write the Inventory and then remove it from Ram.
    }

    // noone cares about this....
    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
        //Allow the pet to follow the player to other worlds(Dimensions).
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
    }
}
