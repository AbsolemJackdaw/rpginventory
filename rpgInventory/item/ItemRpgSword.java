/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.item;

import net.minecraft.item.ItemSword;

public class ItemRpgSword extends ItemSword {

	public ItemRpgSword(ToolMaterial etm) {
		super(etm);
		this.maxStackSize = 1;
	}

	// @Override
	// public Item setTextureName(String s) {
	// String itemName = getUnlocalizedName().substring(
	// getUnlocalizedName().indexOf(".") + 1);
	// this.iconString = "rpginventorymod:" + itemName;
	// return this;
	// }
}
