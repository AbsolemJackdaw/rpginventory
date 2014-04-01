package rpgInventory.block.te;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MoldRecipes {

//	private static Map<Item, ItemStack> necklaceCombination = new HashMap<Item, ItemStack>();
//	private static Map<Item, ItemStack> gloveCombination = new HashMap<Item, ItemStack>();
//	private static Map<Item, ItemStack> ringCombination = new HashMap<Item, ItemStack>();


	//ItemStack = result
	//Item paired with hashmap = mold
	//Block = Block to be molten
	//Item paired with block is the mineral
	public static Map<HashMap<Item,HashMap<Block,Item>>, ItemStack> test = new HashMap<HashMap<Item, HashMap<Block,Item>>, ItemStack>();
	
	// private Map exp = new HashMap();

	public MoldRecipes() {
		
	}
	
	public static void addRecipe(Item mold, Item mineral, ItemStack result, Block catalist){
		Map<Block,Item> a = new HashMap<Block, Item>();
		Map<Item,HashMap<Block,Item>> b = new HashMap<Item,HashMap<Block,Item>>();
		a.put(catalist, mineral);
		b.put(mold, (HashMap<Block, Item>) a);
		
		test.put((HashMap<Item, HashMap<Block, Item>>) b, result);
	}
	
	public static ItemStack getSmeltingResult(Item mineral, Item mold, Block catalist){
		Map<Block,Item> a = new HashMap<Block, Item>();
		Map<Item,HashMap<Block,Item>> b = new HashMap<Item,HashMap<Block,Item>>();
		a.put(catalist, mineral);
		b.put(mold, (HashMap<Block, Item>) a);
		
		if(test.containsKey(b)){
			return test.get(b);
		}
		
		return null;
		
	}

//	public static void addRecipe(Item mold, Item p, ItemStack result, float experience) {
//
//		if (mold != null) {
//			if (mold == mod_RpgInventory.colmold)
//				necklaceCombination.put(p, result);
//			if (mold == mod_RpgInventory.wantmold)
//				gloveCombination.put(p, result);
//			if (mold == mod_RpgInventory.ringmold)
//				ringCombination.put(p, result);
//		}
		// exp.put((mineral), Float.valueOf(experience));
		// exp.put((result), Float.valueOf(experience));
		// exp.put((mold), Float.valueOf(experience));
//	}


//	public static ItemStack getSmeltingResult(Item mineral, Item mold) {
//
//		if (mold == mod_RpgInventory.colmold)
//			return necklaceCombination.get(mineral);
//		else if (mold == mod_RpgInventory.wantmold)
//			return gloveCombination.get(mineral);
//		else if (mold == mod_RpgInventory.ringmold)
//			return ringCombination.get(mineral);
//		else {
//			return null;
//		}
//	}
}
