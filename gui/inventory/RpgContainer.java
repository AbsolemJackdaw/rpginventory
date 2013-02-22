package RpgInventory.gui.inventory;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import RpgInventory.AARpgBaseClass;
import RpgInventory.item.armor.ItemRpgArmor;

public class RpgContainer extends Container {

	RpgInventory inventory;

	@Override
	public List getInventory() {
		return Arrays.asList(inventory.armorSlots);
	}

	public RpgContainer(EntityPlayer player, RpgInventory inv) {
		if (this.isPlayerNotUsingContainer(player)) {
			this.setPlayerIsPresent(player, true);
		}
		this.addSlotToContainer(new SlotRpgArmor(inv, 0, 6, 16));// necklace 
		this.addSlotToContainer(new SlotRpgArmor(inv, 1, 6, 37));//shield
		this.addSlotToContainer(new SlotRpgArmor(inv, 2, 82, 16));//cloak
		this.addSlotToContainer(new SlotRpgArmor(inv, 3, 82, 38));//gloves
		this.addSlotToContainer(new SlotRpgArmor(inv, 4, 82, 59));//ring
		this.addSlotToContainer(new SlotRpgArmor(inv, 5, 6, 58));//ring

		//ADD THIS FIRST
		// quickbar inventory
		for (int var4 = 0; var4 < 9; ++var4) {
			this.addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 142));
		}
		// players inventory
		for (int var4 = 0; var4 < 3; ++var4) {
			for (int var5 = 0; var5 < 9; ++var5) {
				this.addSlotToContainer(new Slot(player.inventory, (var5 + (var4 + 1) * 9), 8 + var5 * 18, 84 + var4 * 18));
			}
		}


		inventory = inv;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber) {
		//Shift clicked the players inventory
		if (this.isPlayerNotUsingContainer(player)) {
			this.setPlayerIsPresent(player, true);
		}
		if (slotnumber - 6 >= 0) {
			if (player.inventory.mainInventory[slotnumber - 6] != null //offset for our inventory.
					&& player.inventory.mainInventory[slotnumber - 6].getItem() instanceof ItemRpgArmor) { //Make sure we are dealing with the right item type.
				ItemRpgArmor tmp = (ItemRpgArmor) player.inventory.mainInventory[slotnumber - 6].getItem();
				switch (tmp.armorType) {
				case AARpgBaseClass.ITEMTYPE.NECKLACE:
					if (((SlotRpgArmor) this.getSlot(0)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory.mainInventory[slotnumber - 6]);
					player.inventory.mainInventory[slotnumber - 6] = null;
					this.slotClick(0, 0, 0, player);
				case AARpgBaseClass.ITEMTYPE.SHIELD:
					if (((SlotRpgArmor) this.getSlot(1)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory.mainInventory[slotnumber - 6]);
					player.inventory.mainInventory[slotnumber - 6] = null;
					this.slotClick(1, 0, 0, player);
				case AARpgBaseClass.ITEMTYPE.CLOAK:
					if (((SlotRpgArmor) this.getSlot(2)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory.mainInventory[slotnumber - 6]);
					player.inventory.mainInventory[slotnumber - 6] = null;
					this.slotClick(2, 0, 0, player);
				case AARpgBaseClass.ITEMTYPE.GLOVES:
					if (((SlotRpgArmor) this.getSlot(3)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory.mainInventory[slotnumber - 6]);
					player.inventory.mainInventory[slotnumber - 6] = null;
					this.slotClick(3, 0, 0, player);
				case AARpgBaseClass.ITEMTYPE.RING:
					if (((SlotRpgArmor) this.getSlot(4)).getStack() != null && ((SlotRpgArmor) this.getSlot(5)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory.mainInventory[slotnumber - 6]);
					player.inventory.mainInventory[slotnumber - 6] = null;
					if (((SlotRpgArmor) this.getSlot(4)).getStack() == null) {
						this.slotClick(4, 0, 0, player);
					} else {
						this.slotClick(5, 0, 0, player);
					}
				}
			}
		} //Shift clicked the rpgarmor inventory
		else if (this.inventory != null) {
			int i = 0;
			for (ItemStack is : player.inventory.mainInventory) {
				if (is == null) {
					player.inventory.mainInventory[i] = this.inventory.armorSlots[slotnumber];
					this.inventory.armorSlots[slotnumber] = null;
					return player.inventory.mainInventory[slotnumber];
				}
				i++;
			}
		}
		return null;
	}

	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}