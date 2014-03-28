package rpgInventory.block.te.slot;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;

class SlotFurnace extends Slot {

	public int slotIndex;

	SlotFurnace(IInventory par2IInventory, int par3, int par4, int par5) {
		super(par2IInventory, par3, par4, par5);
		slotIndex = par3;
	}

	/**
	 * Returns the maximum stack size for a given slot (usually the same as
	 * getInventoryStackLimit(), but 1 in the case of armor slots)
	 */
	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for
	 * the armor slots.
	 */
	@Override
	public boolean isItemValid(ItemStack is) {
		// super.isItemValid(par1ItemStack);

		if (is != null)
			switch (slotIndex) {
			case 0:
				if ((is == new ItemStack(Items.coal))
						|| (is == new ItemStack(Items.lava_bucket))
						|| (is == new ItemStack(Items.blaze_rod)))
					return true;
			case 1:
				if (is == new ItemStack(Blocks.gold_block))
					return true;
				return false;
			case 2:
				if ((is == new ItemStack(mod_RpgInventory.colmold))
						|| (is == new ItemStack(mod_RpgInventory.wantmold))
						|| (is == new ItemStack(mod_RpgInventory.ringmold)))
					return true;
				return false;
			case 3:
				if ((is == new ItemStack(Items.diamond))
						|| (is == new ItemStack(Items.emerald))
						|| (is == new ItemStack(Items.gold_ingot))
						|| (is == new ItemStack(Items.dye, 1, 4)))
					return true;
				return false;
			}
		return false;
	}

	@Override
	public void putStack(ItemStack par1ItemStack) {
		if (this.inventory != null)
			this.inventory.setInventorySlotContents(this.slotIndex,
					par1ItemStack);
		this.onSlotChanged();
	}
}