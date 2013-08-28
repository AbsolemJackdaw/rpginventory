/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotMineral extends Slot{

    public SlotMineral(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        if(par1ItemStack != null){
            if(par1ItemStack.getItem() == Item.ingotGold){
                return true;
            }
            if(par1ItemStack.getItem() == Item.emerald){
                return true;
            }
            if(par1ItemStack.getItem() == Item.diamond){
                return true;
            }
            if(par1ItemStack.getItem() == Item.dyePowder){
                if(par1ItemStack.getItemDamage() == 4){
                    return true;
                }
            }
        }
        return false;
    }
    
}
