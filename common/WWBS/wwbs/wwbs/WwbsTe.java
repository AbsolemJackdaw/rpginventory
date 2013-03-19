package WWBS.wwbs.wwbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class WwbsTe extends TileEntity implements IInventory{

	@Override
	public int getSizeInventory() {
		
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		
	}

	@Override
	public String getInvName() {
		return "Bank";
	}

	@Override
	public boolean func_94042_c() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		return false;
	}

}
