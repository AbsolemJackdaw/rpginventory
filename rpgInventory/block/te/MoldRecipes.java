package rpgInventory.block.te;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;

public class MoldRecipes {

	private static Map<Item, ItemStack> necklaceCombination = new HashMap<Item, ItemStack>();
	private static Map<Item, ItemStack> gloveCombination = new HashMap<Item, ItemStack>();
	private static Map<Item, ItemStack> ringCombination = new HashMap<Item, ItemStack>();

	// private Map<ItemStack, ItemStack[]> breakdownList = new
	// HashMap<ItemStack, ItemStack[]>();

	// private Map exp = new HashMap();

	public MoldRecipes() {
		
	}

	public static void addRecipe(Item mold, Item p, ItemStack result, float experience) {

		if (mold != null) {
			if (mold == mod_RpgInventory.colmold)
				necklaceCombination.put(p, result);
			if (mold == mod_RpgInventory.wantmold)
				gloveCombination.put(p, result);
			if (mold == mod_RpgInventory.ringmold)
				ringCombination.put(p, result);
		}
		// exp.put((mineral), Float.valueOf(experience));
		// exp.put((result), Float.valueOf(experience));
		// exp.put((mold), Float.valueOf(experience));
	}


	public static ItemStack getSmeltingResult(Item mineral, Item mold) {

		if (mold == mod_RpgInventory.colmold)
			return necklaceCombination.get(mineral);
		else if (mold == mod_RpgInventory.wantmold)
			return gloveCombination.get(mineral);
		else if (mold == mod_RpgInventory.ringmold)
			return ringCombination.get(mineral);
		else {
			System.out.println("mold wasnt recognized");
			return null;
		}
	}
}
