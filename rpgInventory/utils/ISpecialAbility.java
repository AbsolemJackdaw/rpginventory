package rpgInventory.utils;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ISpecialAbility {

	public static List<Item> abilityMap = new ArrayList();

	/**This method gets fired every time the special ability key is pressed
	 *Make sure to check for your ability weapon and/or armor class as all
	 *other registered specialAbilities will be called too.
	 * */
	public void specialAbility(ItemStack item);

}
