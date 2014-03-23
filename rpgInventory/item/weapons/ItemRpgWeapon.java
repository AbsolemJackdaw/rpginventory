/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.item.weapons;

import net.minecraft.item.Item;

/**
 * 
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class ItemRpgWeapon extends Item {
	public ItemRpgWeapon() {
		super();
		this.maxStackSize = 1;
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:"+ itemName;
		return this;
	}
}
