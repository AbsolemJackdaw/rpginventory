/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotMineral extends Slot {

	private static ArrayList<Item> allItems = new ArrayList<Item>();

	public SlotMineral(IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
		allItems.add(Items.gold_ingot);
		allItems.add(Items.emerald);
		allItems.add(Items.diamond);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack != null) {

			if (par1ItemStack.getItem() == Items.dye){
				if (par1ItemStack.getItemDamage() == 4)
					return true;
			}

			for(Item i : allItems){
				if(par1ItemStack.getItem().equals(i)){
					return true;
				}
			}
		}
		return false;
	}


	public static void addAllowedItem(Item item){
		
		//If the item is already in the list, no need to put it in there again
		for(Item i : allItems){
			if(i.equals(item))
				return;
		}
		allItems.add(item);
	}
}
