/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Home
 */
public class GoldBlockSlot extends Slot {

	public GoldBlockSlot(IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack != null)
			if (par1ItemStack.getItem() instanceof ItemBlock)
				if (((ItemBlock) par1ItemStack.getItem()).field_150939_a /*
																		 * getBlock(
																		 * )
																		 */== Blocks.gold_block)
					return true;
		return false;
	}

}
