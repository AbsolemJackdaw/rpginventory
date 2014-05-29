/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.block.te.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rpgInventory.item.ItemMold;
import rpgInventory.item.armor.ItemRpgInvArmor;


public class MoldSlot extends Slot {

	public MoldSlot(IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack != null)
			if ((par1ItemStack.getItem() instanceof ItemMold) || (par1ItemStack.getItem() instanceof ItemRpgInvArmor))
				return true;
		return false;
	}

}
