package rpgInventory.block.te;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MoldRecipes {


	//ItemStack = result
	//Item paired with hashmap = mold
	//Block = Block to be molten
	//Item paired with block is the mineral
	public static Map<HashMap<Item,HashMap<Block,Item>>, ItemStack> test = new HashMap<HashMap<Item, HashMap<Block,Item>>, ItemStack>();

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

		if(test.containsKey(b))
			return test.get(b);

		return null;

	}

	public MoldRecipes() {

	}
}
