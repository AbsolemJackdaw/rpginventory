/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Home
 */
public class SlotFuel extends Slot {

    public final int slotID;

    public SlotFuel(IInventory inv, int slotID, int y, int z) {
        super(inv, slotID, y, z);
        this.slotID = slotID;
    }

    @Override
    public void onSlotChange(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        inventory.setInventorySlotContents(slotID, par2ItemStack);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {

        if (par1ItemStack.getItem().itemID == Item.coal.itemID
                || par1ItemStack.getItem().equals(Item.bucketLava)
                || par1ItemStack.getItem().equals(Item.blazeRod)) {
            return true;
        } else {
            return false;
        }
    }
}
