package WWBS.wwbs.wwme;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class WwmeTE extends TileEntity implements IInventory {

	public ItemStack[] inv;

	public WwmeTE() {
		inv = new ItemStack[6];
	}

	@Override
	public void closeChest() {

	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.inv[i] != null) {
			ItemStack itemstack;

			if (this.inv[i].stackSize <= j) {
				itemstack = this.inv[i];
				this.inv[i] = null;
				this.onInventoryChanged();
				return itemstack;
			} else {
				itemstack = this.inv[i].splitStack(j);

				if (this.inv[i].stackSize == 0) {
					this.inv[i] = null;
				}

				this.onInventoryChanged();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getInvName() {
		return "Massive Exchange";
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// if (this.inv[i] != null)
		// {
		// ItemStack itemstack = this.inv[i];
		// this.inv[i] = null;
		// return itemstack;
		// }
		// else
		// {
		// return null;
		// }
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.inv[i] = itemstack;

		if ((itemstack != null)
				&& (itemstack.stackSize > this.getInventoryStackLimit())) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}
}
