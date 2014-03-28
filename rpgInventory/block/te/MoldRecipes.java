package rpgInventory.block.te;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;

public class MoldRecipes {

	// public static MoldRecipes recipes = new MoldRecipes();
	//
	// public static final MoldRecipes recipes() {
	// return recipes;
	// }

	/* item to melt, result */
	// private Map<Item, ItemStack> combinationList = new HashMap<Item,
	// ItemStack>();

	private Map<Item, ItemStack> necklaceCombination = new HashMap<Item, ItemStack>();
	private Map<Item, ItemStack> gloveCombination = new HashMap<Item, ItemStack>();
	private Map<Item, ItemStack> ringCombination = new HashMap<Item, ItemStack>();

	// private Map<ItemStack, ItemStack[]> breakdownList = new
	// HashMap<ItemStack, ItemStack[]>();

	// private Map exp = new HashMap();

	public MoldRecipes() {

		addRecipe(mod_RpgInventory.ringmold, Items.gold_ingot, new ItemStack(
				mod_RpgInventory.ringgold), 0.7F);
		addRecipe(mod_RpgInventory.ringmold, Items.emerald, new ItemStack(
				mod_RpgInventory.ringem), 0.7F);
		addRecipe(mod_RpgInventory.ringmold, Items.diamond, new ItemStack(
				mod_RpgInventory.ringdia), 0.7F);
		addRecipe(mod_RpgInventory.ringmold, Items.dye, new ItemStack(
				mod_RpgInventory.ringlap), 0.7F);

		addRecipe(mod_RpgInventory.colmold, Items.gold_ingot, new ItemStack(
				mod_RpgInventory.neckgold), 0.7F);
		addRecipe(mod_RpgInventory.colmold, Items.emerald, new ItemStack(
				mod_RpgInventory.neckem), 0.7F);
		addRecipe(mod_RpgInventory.colmold, Items.diamond, new ItemStack(
				mod_RpgInventory.neckdia), 0.7F);
		addRecipe(mod_RpgInventory.colmold, Items.dye, new ItemStack(
				mod_RpgInventory.necklap), 0.7F);

		addRecipe(mod_RpgInventory.wantmold, Items.gold_ingot, new ItemStack(
				mod_RpgInventory.glovesbutter), 0.7F);
		addRecipe(mod_RpgInventory.wantmold, Items.emerald, new ItemStack(
				mod_RpgInventory.glovesem), 0.7F);
		addRecipe(mod_RpgInventory.wantmold, Items.diamond, new ItemStack(
				mod_RpgInventory.glovesdia), 0.7F);
		addRecipe(mod_RpgInventory.wantmold, Items.dye, new ItemStack(
				mod_RpgInventory.gloveslap), 0.7F);
	}

	public void addRecipe(Item mold, Item p, ItemStack result, float experience) {
		// ItemStack[] comboBreakdown = { Items.copy(), Items2.copy() };

		if (mold != null) {
			if (mold == mod_RpgInventory.colmold) {
				necklaceCombination.put(p, result);
			}
			if (mold == mod_RpgInventory.wantmold) {
				gloveCombination.put(p, result);
			}
			if (mold == mod_RpgInventory.ringmold) {
				ringCombination.put(p, result);
			}
		}
		// Item moldUsed = mold;
		// breakdownList.put(stack, comboBreakdown);
		// exp.put((mineral), Float.valueOf(experience));
		// exp.put((result), Float.valueOf(experience));
		// exp.put((mold), Float.valueOf(experience));
	}

	// public Map getBreakdownList() {
	// return breakdownList;
	// }
	//
	// public ItemStack[] getBreakdownResult(int id) {
	// return (ItemStack[]) breakdownList.get(id);
	// }
	//
	// public Map getCombinationList() {
	// return combinationList;
	// }

	// public float getExperience(int i) {
	// return this.exp.containsKey(Integer.valueOf(i)) ? ((Float) exp
	// .get(Integer.valueOf(i))).floatValue() : 0.0F;
	// }

	public ItemStack getSmeltingResult(Item mineral, Item mold) {

		if (mold == mod_RpgInventory.colmold) {
			// System.out.println("mold recognized");
			return necklaceCombination.get(mineral);
		}

		else if (mold == mod_RpgInventory.wantmold) {
			return gloveCombination.get(mineral);
		}

		else if (mold == mod_RpgInventory.ringmold) {
			return ringCombination.get(mineral);
		} else {
			System.out.println("mold wasnt recognized");
			return null;
		}
	}
}
