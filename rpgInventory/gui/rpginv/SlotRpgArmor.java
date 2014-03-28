package rpgInventory.gui.rpginv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.oldpackets.PacketInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;

class SlotRpgArmor extends Slot {

	public int slotIndex;
	public EntityPlayer player;

	SlotRpgArmor(PlayerRpgInventory par2IInventory, int par3, int par4,
			int par5, EntityPlayer player) {
		super(par2IInventory, par3, par4, par5);
		this.player = player;
		slotIndex = par3;
	}

	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		player = par1EntityPlayer;
		return super.canTakeStack(par1EntityPlayer);
	}

	/**
	 * Returns the maximum stack size for a given slot (usually the same as
	 * getInventoryStackLimit(), but 1 in the case of armor slots)
	 */
	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return true;
		}
		if ((par1ItemStack.getItem() instanceof ItemRpgInvArmor)) {
			ItemRpgInvArmor tmp = (ItemRpgInvArmor) par1ItemStack.getItem();
			switch (slotIndex) {
			case 0:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.NECKLACE) {
					return true;
				}
				return false;
			case 1:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.SHIELD) {
					return true;
				}
				return false;
			case 2:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.CLOAK) {
					return true;
				}
				return false;
			case 3:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.GLOVES) {
					return true;
				}
				return false;
			case 4:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.RING) {
					return true;
				}
				return false;
			case 5:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.RING) {
					return true;
				}
				return false;
			case 6:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.CRYSTAL) {
					if (par1ItemStack.getItemDamage() > 0) {
						return true;
					}
				}
				return false;
			default:
				// System.out.println("Unknown RPG Inventory type:" +
				// slotIndex);
				return false;
			}
		}
		return false;
	}

	@Override
	public void onSlotChanged() {

		// TODO
		// PacketInventory.sendPacket(player, (PlayerRpgInventory)
		// this.inventory);

		if (!player.worldObj.isRemote) {
			PacketInventory.sendPacket((EntityPlayerMP) player, PlayerRpgInventory.get(player));
			System.out.println("send packet here onslotchanged");

//			PacketInventory pack = new PacketInventory();
//			PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
//			pipe.sendTo(pack, (EntityPlayerMP) player);
		}

		super.onSlotChanged();
	}

	@Override
	public void putStack(ItemStack par1ItemStack) {
		if (this.inventory != null) {
			this.inventory.setInventorySlotContents(this.slotIndex,
					par1ItemStack);
		}
		this.onSlotChanged();
	}

}