package RpgInventory;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import RpgInventory.gui.inventory.RpgInv;
import cpw.mods.fml.common.IPlayerTracker;

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
            if (pet != null) {
                RpgInv inv = mod_RpgInventory.proxy.getInventory(player.username);
                inv.setInventorySlotContents(6, ((IPet) pet).writePetToItemStack(new ItemStack(mod_RpgInventory.crystal)));
                ((EntityLiving) pet).setDead();
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
            EntityLiving pet = (EntityLiving) IPet.playersWithActivePets.get(player.username);
            if (pet == null) {
                IPet.playersWithActivePets.remove(player.username);
            } else {
                pet.setWorld(player.worldObj);
                pet.setPositionAndUpdate(player.posX, player.posY, player.posZ);
            }
        }
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
    }
}
