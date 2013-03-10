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
        if (IPet.playersWithActivePets.containsKey(player.username)) {
            EntityLiving pet = (EntityLiving) IPet.playersWithActivePets.get(player.username);
            //This should never happen, but if it does, don't lose the players pet
            //AND crash them at the same time D:
            if (pet != null && !((EntityLiving) pet).isDead) {
                RpgInv inv = mod_RpgInventory.proxy.getInventory(player.username);
                inv.setInventorySlotContents(6, ((IPet) pet).writePetToItemStack());
                pet.setDead();
            }
            //Keep Hashmap clean.
            IPet.playersWithActivePets.remove(player.username);
        }
        // write the Inventory and then remove it from Ram.
        mod_RpgInventory.proxy.discardInventory(player.username);
    }

    // noone cares about this....
    //I DO I DO!!!!
    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
        //Allow the pet to follow the player to other worlds(Dimensions).
        if (IPet.playersWithActivePets.containsKey(player.username)) {
            try {
                EntityLiving pet = (EntityLiving) IPet.playersWithActivePets.get(player.username);
                pet.setWorld(player.worldObj);
                pet.setPositionAndUpdate(player.posX, player.posY, player.posZ);
            } catch (Throwable ex) {
            }
        }
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
    }
}
