package rpgInventory.gui.rpginv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.Packets17.PacketInventory;
import rpgInventory.handlers.Packets17.PacketPipeline17;
import rpgInventory.item.armor.ItemRpgInvArmor;

public class PlayerRpgContainer extends Container {

	PlayerRpgInventory inventory;

	public PlayerRpgContainer(EntityPlayer player, PlayerRpgInventory inv) {
		if (inv == null) {

			inv = PlayerRpgInventory.get(player);
		}

		if (this.isPlayerNotUsingContainer(player)) {
			this.setPlayerIsPresent(player, true);
		}
		this.addSlotToContainer(new SlotRpgArmor(inv, 0, 6, 16, player));// necklace
		this.addSlotToContainer(new SlotRpgArmor(inv, 1, 6, 37, player));// shield
		this.addSlotToContainer(new SlotRpgArmor(inv, 2, 82, 16, player));// cloak
		this.addSlotToContainer(new SlotRpgArmor(inv, 3, 82, 38, player));// gloves
		this.addSlotToContainer(new SlotRpgArmor(inv, 4, 82, 59, player));// ring
		this.addSlotToContainer(new SlotRpgArmor(inv, 5, 6, 58, player));// ring
		this.addSlotToContainer(new SlotRpgArmor(inv, 6, 105, 16, player));// crystal

		// ADD THIS FIRST
		// quickbar inventory
		for (int var4 = 0; var4 < 9; ++var4) {
			this.addSlotToContainer(new Slot(player.inventory, var4,
					8 + (var4 * 18), 142));
		}
		// players inventory
		for (int var4 = 0; var4 < 3; ++var4) {
			for (int var5 = 0; var5 < 9; ++var5) {
				this.addSlotToContainer(new Slot(player.inventory,
						(var5 + ((var4 + 1) * 9)), 8 + (var5 * 18),
						84 + (var4 * 18)));
			}
		}
		inventory = inv;
		// Put away pet when gui is opened. This is a nerf and a fix for lag
		// induced duping.
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {

		// TODO
		System.out.println("complete packet here closing inventory");
		// PacketInventory.sendPacket(par1EntityPlayer, this.inventory);
		if (!par1EntityPlayer.worldObj.isRemote) {
			PacketInventory pack = new PacketInventory();
			PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
			pipe.sendTo(pack, (EntityPlayerMP) par1EntityPlayer);
		}
		// pipe.sendToAll(pack);

		super.onContainerClosed(par1EntityPlayer);
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		super.onCraftMatrixChanged(par1IInventory);
	}

	@Override
	public ItemStack slotClick(int par1, int par2, int par3,
			EntityPlayer par4EntityPlayer) {
		ItemStack rv = super.slotClick(par1, par2, par3, par4EntityPlayer);

		System.out.println("complete packet send from clickslot");
		// PacketInventory.sendPacket(par4EntityPlayer, this.inventory);

		if (par4EntityPlayer.worldObj.isRemote) {
			PacketInventory pack = new PacketInventory();
			// TODO fill packet
			PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
			pipe.sendToServer(pack);
		}
		return rv;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber) {
		// Shift clicked the players inventory
		if ((slotnumber - 7) >= 0) {
			ItemStack tmp1 = player.inventory.getStackInSlot(slotnumber - 7);
			if ((tmp1 != null) && (tmp1.getItem() instanceof ItemRpgInvArmor)) {
				ItemRpgInvArmor tmp = (ItemRpgInvArmor) tmp1.getItem();
				switch (tmp.armorType) {
				case mod_RpgInventory.ITEMTYPE.NECKLACE:
					if (((SlotRpgArmor) this.getSlot(0)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory
							.getStackInSlot(slotnumber - 7));
					player.inventory.setInventorySlotContents(slotnumber - 7,
							null);
					this.slotClick(0, 0, 0, player);
				case mod_RpgInventory.ITEMTYPE.SHIELD:
					if (((SlotRpgArmor) this.getSlot(1)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory
							.getStackInSlot(slotnumber - 7));
					player.inventory.setInventorySlotContents(slotnumber - 7,
							null);
					this.slotClick(1, 0, 0, player);
				case mod_RpgInventory.ITEMTYPE.CLOAK:
					if (((SlotRpgArmor) this.getSlot(2)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory
							.getStackInSlot(slotnumber - 7));
					player.inventory.setInventorySlotContents(slotnumber - 7,
							null);
					this.slotClick(2, 0, 0, player);
				case mod_RpgInventory.ITEMTYPE.GLOVES:
					if (((SlotRpgArmor) this.getSlot(3)).getStack() != null) {
						return null;
					}
					player.inventory.setItemStack(player.inventory
							.getStackInSlot(slotnumber - 7));
					player.inventory.setInventorySlotContents(slotnumber - 7,
							null);
					this.slotClick(3, 0, 0, player);
				case mod_RpgInventory.ITEMTYPE.RING:
					if ((((SlotRpgArmor) this.getSlot(4)).getStack() != null)
							&& (((SlotRpgArmor) this.getSlot(5)).getStack() != null)) {
						return null;
					}
					player.inventory.setItemStack(player.inventory
							.getStackInSlot(slotnumber - 7));
					player.inventory.setInventorySlotContents(slotnumber - 7,
							null);
					if (((SlotRpgArmor) this.getSlot(4)).getStack() == null) {
						this.slotClick(4, 0, 0, player);
					} else {
						this.slotClick(5, 0, 0, player);
					}
				case mod_RpgInventory.ITEMTYPE.CRYSTAL:
					// System.out.println(tmp1.getItemDamage());
					if (((SlotRpgArmor) this.getSlot(6)).getStack() != null) {
						return null;
					}
					if (tmp1.getItemDamage() == 0) {
						return null;
					}

					player.inventory.setItemStack(player.inventory
							.getStackInSlot(slotnumber - 7));
					player.inventory.setInventorySlotContents(slotnumber - 7,
							null);
					this.slotClick(6, 0, 0, player);
				}
			}
		} // Shift clicked the rpgarmor inventory
		else if (this.inventory != null) {
			int i = 0;
			for (ItemStack is : player.inventory.mainInventory) {
				if (is == null) {
					player.inventory.setInventorySlotContents(i,
							this.inventory.getStackInSlot(slotnumber));
					this.inventory.setInventorySlotContents(slotnumber, null);
					return null;
				}
				i++;
			}
		}
		return null;
	}
}