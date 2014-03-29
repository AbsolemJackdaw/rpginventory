/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;



public class GoldBlockSlot extends Slot {

	private static ArrayList<Block> allBlocks = new ArrayList<Block>();

	public GoldBlockSlot(IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
		allBlocks.add(Blocks.gold_block);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack != null)
			if (par1ItemStack.getItem() instanceof ItemBlock)
				for(Block b : allBlocks)
					if (((ItemBlock) par1ItemStack.getItem()).field_150939_a /*getBlock*/== b)
						return true;
		return false;
	}

	public static void addCatalist(Block b){
		//If the item is already in the list, no need to put it in there again
		for(Block b2 : allBlocks){
			if(b2.equals(b))
				return;
		}
		allBlocks.add(b);
	}

}
