/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rpgInventory.item.ItemMold;

/**
 *
 * @author Home
 */
public class MoldSlot extends Slot{

    public MoldSlot(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        if(par1ItemStack != null){
            if(par1ItemStack.getItem() instanceof ItemMold){
                return true;
            }
        }
        return false;
    }
    public int getSlotStackLimit()
    {
        return 1;
    }
    
}
