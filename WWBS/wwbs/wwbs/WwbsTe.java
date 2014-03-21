package WWBS.wwbs.wwbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class WwbsTe extends TileEntity implements IInventory {

	private ItemStack[] invSlot = new ItemStack[54];

	@Override
	public void closeChest() {

	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getInvName() {
		return "Bank";
	}

	@Override
	public int getSizeInventory() {

		return 54;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.invSlot[slot] != null) {
			ItemStack var2 = this.invSlot[slot];
			this.invSlot[slot] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < invSlot.length)) {
				invSlot[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		invSlot[slot] = stack;

		if ((stack != null) && (stack.stackSize > getInventoryStackLimit())) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < invSlot.length; i++) {
			ItemStack stack = invSlot[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		// if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		// {
		// try{
		// ObjectOutputStream oss = new ObjectOutputStream(new
		// FileOutputStream(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).getChunkSaveLocation().getParent()+File.separator+"BankAccount.dat"));
		// oss.writeObject(null); // should be the map i don't have yet.
		// oss.flush();
		// oss.close();
		// }
		// catch(Throwable e){
		//
		// }
		// }
	}
}
