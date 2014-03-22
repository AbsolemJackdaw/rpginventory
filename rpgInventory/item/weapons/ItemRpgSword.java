/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.item.weapons;

import net.minecraft.item.ItemSword;

public class ItemRpgSword extends ItemSword {

	public ItemRpgSword(ToolMaterial etm) {
		super(etm);
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
