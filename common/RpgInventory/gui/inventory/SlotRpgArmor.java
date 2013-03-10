package RpgInventory.gui.inventory;

import RpgInventory.IPet;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import RpgInventory.mod_RpgInventory;
import RpgRB.ItemCrystal;
import RpgInventory.item.armor.ItemRpgArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

class SlotRpgArmor extends Slot {

    public int slotIndex;
    public EntityPlayer player;

    SlotRpgArmor(IInventory par2IInventory, int par3, int par4, int par5) {
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
    public boolean isItemValid(ItemStack par1ItemStack) {
        if (par1ItemStack != null) {
            if ((par1ItemStack.getItem() instanceof ItemRpgArmor)) {
                ItemRpgArmor tmp = (ItemRpgArmor) par1ItemStack.getItem();
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
                                if (IPet.playersWithActivePets.containsKey(player.username)) {
                                    IPet pet = IPet.playersWithActivePets.get(player.username);
                                    if (pet != null && !((EntityLiving) pet).isDead) {
                                        this.inventory.setInventorySlotContents(this.slotIndex, pet.writePetToItemStack());
                                        ((EntityLiving) pet).setDead();
                                        IPet.playersWithActivePets.remove(player.username);
                                    }
                                }
                                return true;
                            }
                        }
                        return false;
                    default:

                }
            }
        } else if (getStack() != null && mod_RpgInventory.crystal != null && getStack().itemID == mod_RpgInventory.crystal.itemID && IPet.playersWithActivePets.containsKey(player.username)) {
            IPet pet = IPet.playersWithActivePets.get(player.username);
            if (pet != null && !((EntityLiving) pet).isDead) {
                this.inventory.setInventorySlotContents(this.slotIndex, pet.writePetToItemStack());
                ((EntityLiving) pet).setDead();
                IPet.playersWithActivePets.remove(player.username);
            }
        }

        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
        player = par1EntityPlayer;
        return super.canTakeStack(par1EntityPlayer);
    }

    @Override
    public void putStack(ItemStack par1ItemStack) {
        if (this.inventory != null) {
            this.inventory.setInventorySlotContents(this.slotIndex, par1ItemStack);
        }
        this.onSlotChanged();
    }
}