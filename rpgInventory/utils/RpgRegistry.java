package rpgInventory.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.TEMold;
import rpgInventory.block.te.slot.GoldBlockSlot;
import rpgInventory.block.te.slot.SlotMineral;
import rpgInventory.item.ItemMold;

public class RpgRegistry {

	/**
	 * mold : extend itemmold to make it working
	 * mineral : any new can be registered with addForgeMoldMineral
	 * catalist : block that will be molten : any new can be registered with addCatalistBlock
	 * result : the resulting item after all melting is done
	 */
	public static void addForgeMoldRecipe(Item mold, Item mineral, Block catalist, ItemStack result){
		MoldRecipes.addRecipe(mold, mineral, result, catalist );
	}

	public static void addForgeMoldMineral(Item i){
		SlotMineral.addAllowedItem(i);
	}

	public static void addCatalistBlock(Block b){
		GoldBlockSlot.addCatalist(b);
	}

	/**ItemStack : item or block to burn
	 * int time : how long it burns
	 * */
	public static void addFuel(ItemStack is, int time){
		if(is != null){
			if(is.getItem() != null){
				if(is.getItem() instanceof Item){
					TEMold.addFuelItem(is.getItem(), time);
				}
				if(Block.getBlockFromItem(is.getItem())!= null){
					TEMold.addFuelBlock(Block.getBlockFromItem(is.getItem()), time);
				}
			}
		}
	}

	@Deprecated
	/**extend ItemMold*/
	public static void addMold(Item i){
	}
}
