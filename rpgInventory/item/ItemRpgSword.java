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
}
