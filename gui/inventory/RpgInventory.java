package RpgInventory.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class RpgInventory implements IInventory
{

	public ItemStack[]	armorSlots	= new ItemStack[6];

	EntityPlayer		player;

	public RpgInventory(EntityPlayer par1EntityPlayer)
	{
		player = par1EntityPlayer;
	}

	@Override
	public int getSizeInventory()
	{
		return armorSlots.length;
	}

	public ItemStack getJewelInSlot(int par1)
	{
		return armorSlots[par1];
	}

	/**
	 * Returns a slot index in main inventory containing a specific itemID
	 */
	private int findJewel(int par1)
	{
		for (int var2 = 0; var2 < armorSlots.length; ++var2)
		{
			if (armorSlots[var2] != null && armorSlots[var2].itemID == par1)
				return var2;
		}

		return -1;
	}

	public boolean getJewel(int par1)
	{
		int var2 = findJewel(par1);
		return var2 >= 0;
	}

	public ItemStack getJewelFromStack(int par1)
	{
		ItemStack[] var2 = armorSlots;
		
		if (par1 >= var2.length)
		{
			par1 -= var2.length;
			var2 = armorSlots;
		}
		
		return var2[par1];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (armorSlots[par1] != null)
		{
			ItemStack var3;

			if (armorSlots[par1].stackSize <= par2)
			{
				var3 = armorSlots[par1];
				armorSlots[par1] = null;
				onInventoryChanged();
				return var3;
			}
			else
			{
				var3 = armorSlots[par1].splitStack(par2);

				if (armorSlots[par1].stackSize == 0)
				{
					armorSlots[par1] = null;
				}

				onInventoryChanged();
				return var3;
			}
		}
		else
			return null;
	}

	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		// nothing in this inventory should be dropped like the Crafting Table.
		/*
		 * if (armorSlots[par1] != null)
		 * {
		 * ItemStack var3 = armorSlots[par1];
		 * armorSlots[par1] = null;
		 * return var3;
		 * }
		 * else
		 */
		return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.armorSlots[par1] = par2ItemStack;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return armorSlots[par1];
	}

	@Override
	public String getInvName()
	{
		return "RpgInventory";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{
		// nothing needed...
		//inventoryChanged = true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return player.isDead ? false : par1EntityPlayer.getDistanceSqToEntity(player) <= 64.0D;
	}

	/**
	 * Writes the inventory out as a list of compound tags. This is where the slot indices are used (+100 for armor, +80
	 * for crafting).
	 */

	public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		System.out.println("written to NBT");
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < armorSlots.length; ++var3)
		{
			if (armorSlots[var3] != null)
			{
				NBTTagCompound compoundSlot = new NBTTagCompound();
				compoundSlot.setByte("Slot", (byte) var3);
				armorSlots[var3].writeToNBT(compoundSlot);
				var2.appendTag(compoundSlot);

			}
		}

		par1NBTTagCompound.setTag("Slot", var2);
		return par1NBTTagCompound;
	}

	/**
	 * Reads from the given tag list and fills the slots in the inventory with the correct items.
	 */
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		System.out.println("read from NBT");
		NBTTagList var2 = par1NBTTagCompound.getTagList("Slot");
		armorSlots = new ItemStack[getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound compoundSlot = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = compoundSlot.getByte("Slot");

			if (var5 >= 0 && var5 < armorSlots.length)
			{
				armorSlots[var5] = ItemStack.loadItemStackFromNBT(compoundSlot);
			}
		}
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

}
