package rpgInventory.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.slot.GoldBlockSlot;
import rpgInventory.block.te.slot.SlotMineral;
import rpgInventory.item.ItemMold;

public class RpgRegistry {

	
	public static void addForgeMoldRecipe(ItemMold mold, Item mineral, Block catalist, ItemStack result){
		MoldRecipes.addRecipe(mold, mineral, result, catalist );
	}
	
	public static void addForgeMoldMineral(Item i){
		SlotMineral.addAllowedItem(i);
	}
	
	public static void addCatalistBlock(Block b){
		GoldBlockSlot.addCatalist(b);
	}
	
	@Deprecated
	/**extend ItemMold*/
	public static void addMold(Item i){
	}
}
