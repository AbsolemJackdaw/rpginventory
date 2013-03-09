//package RpgInventory.forge;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.minecraft.block.Block;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//
//public class MeltItems {
//
//	public static final MeltItems recipes = new MeltItems();
//	
//	private static HashMap<MoldRecipes,ItemStack> recipeSet = 
//			new HashMap<MoldRecipes,ItemStack>();
//	private Map combinationList = new HashMap();
//
//	public static final MeltItems recipes() {
//		return recipes;
//	}
//
//	private	 MeltItems()
//	{
//		addCombination(new ItemStack(Item.bucketWater), new ItemStack(Item.bucketLava), new ItemStack(Block.obsidian));
//
//		addCombination(new ItemStack(Item.bucketLava), new ItemStack(Item.bucketMilk), new ItemStack(Block.glass));
//
//		addCombination(new ItemStack(Block.glowStone), new ItemStack(Item.redstone), new ItemStack(Block.oreRedstone));
//	}
//
//	public void addCombination(ItemStack item, ItemStack item2, ItemStack stack) {
//		ItemStack[] comboBreakdown = {item.copy(), item2.copy()};
//
//		combinationList.put(Arrays.asList(item.itemID, item2.itemID), stack);
//		//		exp.put(Integer.valueOf(stack.itemID), Float.valueOf(experience));
//		//		exp.put(Integer.valueOf(item.itemID), Float.valueOf(experience));
//		//		exp.put(Integer.valueOf(item2.itemID), Float.valueOf(experience));
//	}
//
//	public ItemStack getCombinationResult(int stack, int stack2) {
//		//int[] ids = {stack, stack2};
//
//		return (ItemStack)combinationList.get(Arrays.asList(stack, stack2));
//	}
//
//	public Map getCombinationList() {
//		return combinationList;
//	}
//
//	public static void addRecipe(MoldRecipes rec, ItemStack output)
//	{
//		recipeSet.put(rec, output);
//	}
//
////	public static ItemStack checkRecipe(ItemStack ing1, ItemStack ing2)
////	{	
////		recipeSet.get(new MoldRecipes(ing1,ing2));
////		return ing2;
////	}
//
//}
