package RpgInventory.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import RpgInventory.mod_RpgInventory;
import RpgRB.ItemCrystal;
import RpgInventory.item.armor.ItemRpgArmor;

public class RpgContainer extends Container {

    RpgInv inventory;

    public RpgContainer(EntityPlayer player, RpgInv inv) {
        if (inv == null) {
            inv = mod_RpgInventory.proxy.getInventory(player.username);
        }
        if (this.isPlayerNotUsingContainer(player)) {
            this.setPlayerIsPresent(player, true);
        }
        this.addSlotToContainer(new SlotRpgArmor(inv, 0, 6, 16));// necklace 
        this.addSlotToContainer(new SlotRpgArmor(inv, 1, 6, 37));//shield
        this.addSlotToContainer(new SlotRpgArmor(inv, 2, 82, 16));//cloak
        this.addSlotToContainer(new SlotRpgArmor(inv, 3, 82, 38));//gloves
        this.addSlotToContainer(new SlotRpgArmor(inv, 4, 82, 59));//ring
        this.addSlotToContainer(new SlotRpgArmor(inv, 5, 6, 58));//ring
        this.addSlotToContainer(new SlotRpgArmor(inv, 6, 105, 16));//crystal


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
        if (slotnumber - 7 >= 0) {
            ItemStack tmp1 = player.inventory.getStackInSlot(slotnumber - 7);
            if (tmp1 != null && tmp1.getItem() instanceof ItemRpgArmor) {
                ItemRpgArmor tmp = (ItemRpgArmor) tmp1.getItem();
                switch (tmp.armorType) {
                    case mod_RpgInventory.ITEMTYPE.NECKLACE:
                        if (((SlotRpgArmor) this.getSlot(0)).getStack() != null) {
                            return null;
                        }
                        player.inventory.setItemStack(player.inventory.getStackInSlot(slotnumber - 7));
                        player.inventory.setInventorySlotContents(slotnumber - 7, null);
                        this.slotClick(1, 0, 0, player);
                    case mod_RpgInventory.ITEMTYPE.SHIELD:
                        if (((SlotRpgArmor) this.getSlot(1)).getStack() != null) {
                            return null;
                        }
                        player.inventory.setItemStack(player.inventory.getStackInSlot(slotnumber - 7));
                        player.inventory.setInventorySlotContents(slotnumber - 7, null);
                        this.slotClick(1, 0, 0, player);
                    case mod_RpgInventory.ITEMTYPE.CLOAK:
                        if (((SlotRpgArmor) this.getSlot(2)).getStack() != null) {
                            return null;
                        }
                        player.inventory.setItemStack(player.inventory.getStackInSlot(slotnumber - 7));
                        player.inventory.setInventorySlotContents(slotnumber - 7, null);
                        this.slotClick(2, 0, 0, player);
                    case mod_RpgInventory.ITEMTYPE.GLOVES:
                        if (((SlotRpgArmor) this.getSlot(3)).getStack() != null) {
                            return null;
                        }
                        player.inventory.setItemStack(player.inventory.getStackInSlot(slotnumber - 7));
                        player.inventory.setInventorySlotContents(slotnumber - 7, null);
                        this.slotClick(3, 0, 0, player);
                    case mod_RpgInventory.ITEMTYPE.RING:
                        if (((SlotRpgArmor) this.getSlot(4)).getStack() != null && ((SlotRpgArmor) this.getSlot(5)).getStack() != null) {
                            return null;
                        }
                        player.inventory.setItemStack(player.inventory.getStackInSlot(slotnumber - 7));
                        player.inventory.setInventorySlotContents(slotnumber - 7, null);
                        if (((SlotRpgArmor) this.getSlot(4)).getStack() == null) {
                            this.slotClick(4, 0, 0, player);
                        } else {
                            this.slotClick(5, 0, 0, player);
                        }
                    case mod_RpgInventory.ITEMTYPE.CRYSTAL:
                        System.out.println(tmp1.getItemDamage());
                        if (((SlotRpgArmor) this.getSlot(6)).getStack() != null) {
                            return null;
                        }
                        if (tmp1.getItemDamage() == 0) {
                            return null;
                        }
                        
                        player.inventory.setItemStack(player.inventory.getStackInSlot(slotnumber - 7));
                        player.inventory.setInventorySlotContents(slotnumber - 7, null);
                        this.slotClick(6, 0, 0, player);

                }
            } 
        } //Shift clicked the rpgarmor inventory
        else if (this.inventory != null) {
            int i = 0;
            for (ItemStack is : player.inventory.mainInventory) {
                if (is == null) {
                    player.inventory.setInventorySlotContents(i, this.inventory.getStackInSlot(slotnumber));
                    this.inventory.setInventorySlotContents(slotnumber, null);
                    return null;
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