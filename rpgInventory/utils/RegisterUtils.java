package rpgInventory.utils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.slot.SlotMineral;

public class RegisterUtils {

	
	public static void addForgeMoldRecipe(Item mold, Item mineral, ItemStack result){
		MoldRecipes.addRecipe(mold, mineral, result, 0.7F);
	}
	
	public static void addForgeMoldMineral(Item i){
		SlotMineral.addAllowedItem(i);
	}
}
