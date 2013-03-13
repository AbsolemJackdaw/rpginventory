package RpgInventory;

import RpgInventory.gui.inventory.RpgInv;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

/**
 * To catch login/Logouts for readingw/writing the Inventory
 *
 * @author AbrarSyed
 */
public class PlayerTracker implements IPlayerTracker {

    @Override
    public void onPlayerLogin(EntityPlayer player) {
        mod_RpgInventory.proxy.loadInventory(player.username);
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        // write the Inventory and then remove it from Ram.
        mod_RpgInventory.proxy.discardInventory(player.username);
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
