package rpgInventory.block.te;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import rpgInventory.mod_RpgInventory;
import rpgInventory.block.BlockForge;
import rpgInventory.item.ItemMold;
import cpw.mods.fml.common.registry.GameRegistry;

public class TEMold extends TileEntity implements IInventory {

	public static int getItemTime(ItemStack par0ItemStack) {
		if (par0ItemStack == null)
			return 0;
		else {
			Item var1 = Items.apple; // making sure its not null
			Block var2 = Blocks.stone;

			if (par0ItemStack.getItem() != null)
				var1 = par0ItemStack.getItem();
			else
				var2 = ((ItemBlock) par0ItemStack.getItem()).field_150939_a;

			if (var1 == Items.coal)
				return 100;
			if (var1 == Items.lava_bucket)
				return 3200;
			if (var1 == Items.blaze_rod)
				return 1600;

			if (var2 == Blocks.coal_block)
				return 1600;

			return GameRegistry.getFuelValue(par0ItemStack);
		}
	}

	public static boolean isGoldBlock(ItemStack stack) {
		if (Block.getBlockFromItem(stack.getItem()) != null)
			if (Block.getBlockFromItem(stack.getItem()) == Blocks.gold_block)
				return true;
		return false;
	}

	public static boolean isItemFuel(ItemStack stack) {
		return getItemTime(stack) > 0;
	}

	public static boolean isMineral(ItemStack stack) {
		if (stack.getItem() != null) {
			if (stack.getItem().equals(Items.emerald))
				return true;
			if (stack.getItem().equals(Items.diamond))
				return true;
			if (stack.getItem().equals((Items.gold_ingot)))
				return true;
			if ((stack.getItem() instanceof ItemDye)
					&& (stack.getItemDamage() == 4))
				return true;
		}
		return false;
	}

	public static boolean isMold(ItemStack stack) {
		if (stack.getItem() != null)
			if (stack.getItem() instanceof ItemMold)
				return true;
		return false;
	}

	/** 0 fuel, 1 gold block, 2 mold, 3 mineral, 4 result */
	private ItemStack[] moldforgeItemStacks;
	/**
	 * The number of ticks that the furnace will keep burning
	 */
	public int goldBurnTime;

	private boolean isActive;

	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	public int goldItemBurnTime;

	/**
	 * The number of ticks that the current item has been cooking for
	 */
	public int goldCookTime;

	public int front;

	private int smeltTime = 800;

	public TEMold() {
		moldforgeItemStacks = new ItemStack[5];
		goldBurnTime = 0;
		goldItemBurnTime = 0;
		goldCookTime = 0;
	}

	private boolean canSmelt() {
		if ((moldforgeItemStacks[0] == null)
				&& (moldforgeItemStacks[1] == null))
			return false;

		ItemStack result = null;

		ItemStack mineral = null;
		ItemStack mold = null;

		// System.out.println(moldforgeItemStacks[3]);
		if (moldforgeItemStacks[1] != null) {
			if ((moldforgeItemStacks[2] != null)
					&& (moldforgeItemStacks[3] != null)) {
				mold = moldforgeItemStacks[2];
				mineral = moldforgeItemStacks[3];
				if (mineral != null)
					if (mineral.getItem() instanceof ItemDye) {
						if (mineral.getItemDamage() == 4)
							result = mod_RpgInventory.recipes
									.getSmeltingResult(mineral.getItem(),
											mold.getItem());
					} else
						result = mod_RpgInventory.recipes.getSmeltingResult(
								mineral.getItem(), mold.getItem());
			}

			if ((result == null) && (moldforgeItemStacks[2] != null)
					&& (moldforgeItemStacks[3] != null)) {
				mold = moldforgeItemStacks[2];
				mineral = moldforgeItemStacks[3];
				if (mineral != null)
					if (mineral.getItem() instanceof ItemDye) {
						if (mineral.getItemDamage() == 4)
							result = mod_RpgInventory.recipes
									.getSmeltingResult(mineral.getItem(),
											mold.getItem());
					} else
						result = mod_RpgInventory.recipes.getSmeltingResult(
								mineral.getItem(), mold.getItem());

			}
		}

		if (result == null)
			return false;

		if (moldforgeItemStacks[4] == null)
			return true;

		if (!moldforgeItemStacks[4].isItemEqual(result))
			return false;

		if ((moldforgeItemStacks[4].stackSize < getInventoryStackLimit())
				&& (moldforgeItemStacks[4].stackSize < moldforgeItemStacks[4]
						.getMaxStackSize()))
			return true;

		return moldforgeItemStacks[4].stackSize < result.getMaxStackSize();
	}

	@Override
	public void closeInventory() {

	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null)
			if (stack.stackSize <= amt)
				setInventorySlotContents(slot, null);
			else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
					setInventorySlotContents(slot, null);
			}
		return stack;
	}

	public int getFrontDirection() {
		return this.front;
	}

	@Override
	public String getInventoryName() {
		return "Mold Forge";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public int getProgressScaled(int i) {
		return (goldCookTime * i) / smeltTime;
	}

	@Override
	public int getSizeInventory() {
		return moldforgeItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return moldforgeItemStacks[slot];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.moldforgeItemStacks[slot] != null) {
			ItemStack var2 = this.moldforgeItemStacks[slot];
			this.moldforgeItemStacks[slot] = null;
			return var2;
		} else
			return null;
	}

	public int getTimeRemainingScaled(int i) {
		if (goldItemBurnTime == 0)
			goldItemBurnTime = 100;

		return (goldBurnTime * i) / goldItemBurnTime;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isBurning() {
		return goldBurnTime > 0;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {

		return true;
	}

	@Override
	public void openInventory() {

	}

	public void produceJewel() {
		if (canSmelt()) {
			ItemStack mineral = null;
			ItemStack mold = null;

			if ((moldforgeItemStacks[2] != null)
					&& (moldforgeItemStacks[3] != null)) {
				mold = moldforgeItemStacks[2];
				mineral = moldforgeItemStacks[3];
			}

			ItemStack stack = mod_RpgInventory.recipes.getSmeltingResult(
					mineral.getItem(), mold.getItem());

			if (stack == null)
				stack = mod_RpgInventory.recipes.getSmeltingResult(
						mineral.getItem(), mold.getItem());

			if (moldforgeItemStacks[4] == null)
				moldforgeItemStacks[4] = stack.copy();
			else if (moldforgeItemStacks[4] == stack)
				moldforgeItemStacks[4].stackSize += 1;
			if (moldforgeItemStacks[3].stackSize > 1)
				--moldforgeItemStacks[3].stackSize;
			else
				moldforgeItemStacks[3] = null;
			if (moldforgeItemStacks[2].stackSize > 1)
				--moldforgeItemStacks[2].stackSize;
			else
				moldforgeItemStacks[2] = null;
			if (moldforgeItemStacks[1].stackSize > 1)
				--moldforgeItemStacks[1].stackSize;
			else
				moldforgeItemStacks[1] = null;

			if ((moldforgeItemStacks[0] != null)
					&& (moldforgeItemStacks[0].stackSize == 0)) {
				Item i = moldforgeItemStacks[0].getItem().getContainerItem();
				moldforgeItemStacks[0] = i == null ? null : new ItemStack(i);
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < moldforgeItemStacks.length))
				moldforgeItemStacks[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
		front = tagCompound.getInteger("FrontDirection");
		goldBurnTime = tagCompound.getShort("BurnTime");
		goldCookTime = tagCompound.getShort("CookTime");
		goldItemBurnTime = getItemTime(moldforgeItemStacks[0]);
	}

	public void setFrontDirection(int f) {
		this.front = f;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		moldforgeItemStacks[slot] = stack;

		if ((stack != null) && (stack.stackSize > getInventoryStackLimit()))
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public void updateEntity() {

		boolean var1 = this.goldBurnTime > 0;
		boolean var2 = false;

		// System.out.println(isBurning() + " "+ canSmelt() +blockMetadata + " "
		// + goldBurnTime);

		if (goldBurnTime > 0)
			goldBurnTime -= 1;

		if ((goldBurnTime == 0) && this.canSmelt()) {
			goldItemBurnTime = goldBurnTime = getItemTime(moldforgeItemStacks[0]);

			if (goldBurnTime > 0) {
				var2 = true;

				if (moldforgeItemStacks[0] != null) {
					--moldforgeItemStacks[0].stackSize;

					if (moldforgeItemStacks[0].stackSize == 0) {
						Item var3 = moldforgeItemStacks[0].getItem()
								.getContainerItem();
						moldforgeItemStacks[0] = var3 == null ? null
								: new ItemStack(var3);
					}
				}
			}
		}

		if (isBurning() && canSmelt()) {
			goldCookTime += 1;

			if (goldCookTime == smeltTime) {
				goldCookTime = 0;
				produceJewel();
				var2 = true;
			}
		} else
			goldCookTime = 0;

		if (var1 != (goldBurnTime > 0))
			var2 = true;
		if (isActive() != isBurning()) {
			isActive = isBurning();
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

		if (var2)
			// this.onInventoryChanged();
			markDirty();

		Block b = worldObj.getBlock(xCoord, yCoord, zCoord);
		if (b instanceof BlockForge) {
			BlockForge f = (BlockForge) b;
			if (isBurning())
				f.isBurning = true;
			else
				f.isBurning = false;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < moldforgeItemStacks.length; i++) {
			ItemStack stack = moldforgeItemStacks[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);

		tagCompound.setInteger("FrontDirection", front);
		tagCompound.setShort("BurnTime", (short) goldBurnTime);
		tagCompound.setShort("CookTime", (short) goldCookTime);
	}
}
