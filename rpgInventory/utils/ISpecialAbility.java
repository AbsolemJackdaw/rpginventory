package rpgInventory.utils;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ISpecialAbility {


	public static Map<Item, Integer> abilityMap = new HashMap();


	/**This method gets fired every time the special ability key is pressed
	 *Make sure to check for your ability weapon and/or armor class as all
	 *other registered specialAbilities will be called too.
	 * */
	public void specialAbility(ItemStack item);

}
