/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgInventory;
import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.item.armor.BonusArmor;

/**
 *
 * @author Home
 */
public enum EnumRpgClass {

    ARCHER, MAGE, BERSERKER, NECRO, PALADIN,
    SHIELDEDARCHER, SHIELDEDMAGE, SHIELDEDBERSERKER, SHIELDEDNECRO, SHIELDEDPALADIN,
    WOOD, IRON, GOLD, DIAMOND, CRYSTAL;

    public static EnumSet<EnumRpgClass> getPlayerClasses(EntityPlayer p) {
        EnumRpgClass classenum = null;
        EnumRpgClass shieldenum = null;
        EnumSet<EnumRpgClass> classEnum = EnumSet.noneOf(EnumRpgClass.class);
        if (p != null && p.inventory != null && p.inventory.armorInventory != null) {
            for (ItemStack is : p.inventory.armorInventory) {
                if (is == null) {
                    //if any armorslots are empty, the class is incomplete, and no further checks needed.
                    classenum = null;
                    //tmp represents the base class enum, break the for loop instead of return
                    //so we can check for shields
                    break;
                }
                if (is.getItem() instanceof BonusArmor) {
                    if (is.getItem().equals(mod_RpgInventory.archerhood) || is.getItem().equals(mod_RpgInventory.archerchest)
                            || is.getItem().equals(mod_RpgInventory.archerpants) || is.getItem().equals(mod_RpgInventory.archerboots)) {
                        if (classenum == null) {
                            classenum = ARCHER;
                        }
                        //This means it has armor pieces from other classes.
                        if (classenum != ARCHER) {
                            //tmp represents the base class enum, break the for loop instead of return
                            //so we can check for shields
                            classenum = null;
                            break;
                        }
                    } else if (is.getItem().equals(mod_RpgInventory.magehood) || is.getItem().equals(mod_RpgInventory.magegown)
                            || is.getItem().equals(mod_RpgInventory.magepants) || is.getItem().equals(mod_RpgInventory.mageboots)) {
                        if (classenum == null) {
                            classenum = MAGE;
                        }
                        //This means it has armor pieces from other classes.
                        if (classenum != MAGE) {
                            //tmp represents the base class enum, break the for loop instead of return
                            //so we can check for shields
                            classenum = null;
                            break;
                        }
                    } else if (is.getItem().equals(mod_RpgInventory.berserkerHood) || is.getItem().equals(mod_RpgInventory.berserkerChest)
                            || is.getItem().equals(mod_RpgInventory.berserkerLegs) || is.getItem().equals(mod_RpgInventory.berserkerBoots)) {
                        if (classenum == null) {
                            classenum = BERSERKER;
                        }
                        //This means it has armor pieces from other classes.
                        if (classenum != BERSERKER) {
                            //tmp represents the base class enum, break the for loop instead of return
                            //so we can check for shields
                            classenum = null;
                            break;
                        }
                    } else if (is.getItem().equals(mod_RpgInventory.palaHelm) || is.getItem().equals(mod_RpgInventory.palaChest)
                            || is.getItem().equals(mod_RpgInventory.palaLeggings) || is.getItem().equals(mod_RpgInventory.palaBoots)) {
                        if (classenum == null) {
                            classenum = PALADIN;
                        }
                        //This means it has armor pieces from other classes.
                        if (classenum != PALADIN) {
                            //tmp represents the base class enum, break the for loop instead of return
                            //so we can check for shields
                            classenum = null;
                            break;
                        }
                    } else if (is.getItem().equals(mod_RpgInventory.necroHood) || is.getItem().equals(mod_RpgInventory.necroChestplate)
                            || is.getItem().equals(mod_RpgInventory.necroLeggings) || is.getItem().equals(mod_RpgInventory.necroBoots)) {
                        if (classenum == null) {
                            classenum = NECRO;
                        }
                        //This means it has armor pieces from other classes.
                        if (classenum != NECRO) {
                            //tmp represents the base class enum, break the for loop instead of return
                            //so we can check for shields
                            classenum = null;
                            break;
                        }
                    }
                } else {
                    //tmp represents the base class enum, break the for loop instead of return
                    //so we can check for shields
                    classenum = null;
                    break;
                }
            }
        }
        RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
        //This means charactor has a class enum
        if (inv != null) {
            ItemStack shield = inv.getShield();
            if (shield != null) {
                if (classenum != null) {
                    if (shield.getItem().equals(mod_RpgInventory.archersShield) && classenum == ARCHER) {
                        shieldenum = SHIELDEDARCHER;
                    } else if (shield.getItem().equals(mod_RpgInventory.talisman) && classenum == MAGE) {
                        shieldenum = SHIELDEDMAGE;
                    } else if (shield.getItem().equals(mod_RpgInventory.berserkerShield) && classenum == BERSERKER) {
                        shieldenum = SHIELDEDBERSERKER;
                    } else if (shield.getItem().equals(mod_RpgInventory.pala_shield) && classenum == PALADIN) {
                        shieldenum = SHIELDEDPALADIN;
                    } else if (shield.getItem().equals(mod_RpgInventory.necro_shield) && classenum == NECRO) {
                        shieldenum = SHIELDEDNECRO;
                    }
                }
                //So classes can use the vanilla armor too if they want
                if (shieldenum == null) {
                    if (shield.getItem().equals(mod_RpgInventory.shieldWood)) {
                        shieldenum = WOOD;
                    } else if (shield.getItem().equals(mod_RpgInventory.shieldIron)) {
                        shieldenum = IRON;
                    } else if (shield.getItem().equals(mod_RpgInventory.shieldGold)) {
                        shieldenum = GOLD;
                    } else if (shield.getItem().equals(mod_RpgInventory.shieldDiamond)) {
                        shieldenum = DIAMOND;
                    }
                }
            }
        }
        if (classenum != null) {
            classEnum.add(classenum);
        }
        if (shieldenum != null) {
            classEnum.add(shieldenum);
        }
        if(inv.getCrystal() != null){
            classEnum.add(CRYSTAL);
        }
        return classEnum;
    }
}
