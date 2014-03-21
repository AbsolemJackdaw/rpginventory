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
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:"
				+ itemName);
	}
}
