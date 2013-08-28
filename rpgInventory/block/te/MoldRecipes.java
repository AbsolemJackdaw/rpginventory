package rpgInventory.block.te;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;

public class MoldRecipes
{

	public static MoldRecipes recipes = new MoldRecipes();

	private Map combinationList = new HashMap();
	private Map breakdownList = new HashMap();
	private Map exp = new HashMap();

	public static final MoldRecipes recipes() {
		return recipes;
	}

	private MoldRecipes() {
            
            addRecipe(new ItemStack(mod_RpgInventory.ringmold),new ItemStack(Item.ingotGold), new ItemStack(mod_RpgInventory.ringgold), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.ringmold),new ItemStack(Item.emerald), new ItemStack(mod_RpgInventory.ringem), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.ringmold),new ItemStack(Item.diamond), new ItemStack(mod_RpgInventory.ringdia), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.ringmold),new ItemStack(Item.dyePowder,1,4), new ItemStack(mod_RpgInventory.ringlap), 0.7F);

            addRecipe(new ItemStack(mod_RpgInventory.colmold),new ItemStack(Item.ingotGold), new ItemStack(mod_RpgInventory.neckgold), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.colmold),new ItemStack(Item.emerald), new ItemStack(mod_RpgInventory.neckem), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.colmold),new ItemStack(Item.diamond), new ItemStack(mod_RpgInventory.neckdia), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.colmold),new ItemStack(Item.dyePowder,1,4), new ItemStack(mod_RpgInventory.necklap), 0.7F);

            addRecipe(new ItemStack(mod_RpgInventory.wantmold),new ItemStack(Item.ingotGold), new ItemStack(mod_RpgInventory.glovesbutter), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.wantmold),new ItemStack(Item.emerald), new ItemStack(mod_RpgInventory.glovesem), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.wantmold),new ItemStack(Item.diamond), new ItemStack(mod_RpgInventory.glovesdia), 0.7F);
            addRecipe(new ItemStack(mod_RpgInventory.wantmold),new ItemStack(Item.dyePowder,1,4), new ItemStack(mod_RpgInventory.gloveslap), 0.7F);

	}

	public void addRecipe(ItemStack item, ItemStack item2, ItemStack stack, float experience) {
		ItemStack[] comboBreakdown = {item.copy(), item2.copy()};

		combinationList.put(Arrays.asList(item.itemID, item2.itemID), stack);
		breakdownList.put(stack.itemID, comboBreakdown);
		exp.put(Integer.valueOf(stack.itemID), Float.valueOf(experience));
		exp.put(Integer.valueOf(item.itemID), Float.valueOf(experience));
		exp.put(Integer.valueOf(item2.itemID), Float.valueOf(experience));
	}

	public ItemStack getSmeltingResult(int stack, int stack2) {
		//int[] ids = {stack, stack2};

		return (ItemStack)combinationList.get(Arrays.asList(stack, stack2));
	}

	public ItemStack[] getBreakdownResult(int id) {
		return (ItemStack[])breakdownList.get(id);
	}

	public Map getCombinationList() {
		return combinationList;
	}

	public float getExperience(int i) {
		return this.exp.containsKey(Integer.valueOf(i)) ? ((Float)exp.get(Integer.valueOf(i))).floatValue() : 0.0F;
	}

	public Map getBreakdownList() {
		return breakdownList;
	}
}
