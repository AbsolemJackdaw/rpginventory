/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GoldBlockSlot extends Slot {

	private static ArrayList<Item> allBlocks = new ArrayList<Item>();

	public static void addCatalist(Item b){
		for(Item b2 : allBlocks)
			if(b2.equals(b))
				return;
		allBlocks.add(b);
	}

	public GoldBlockSlot(IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
		allBlocks.add(Item.getItemFromBlock(Blocks.gold_block));
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack != null)
			if (par1ItemStack.getItem() instanceof ItemBlock)
				for(Item b : allBlocks)
					if (par1ItemStack.getItem().equals(b))
						return true;
		return false;
	}

}
