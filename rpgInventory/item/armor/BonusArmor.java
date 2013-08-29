package rpgInventory.item.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;

public class BonusArmor extends ItemArmor{

    public final int renderIndex;

    public BonusArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
            int par3, int par4) {
        super(par1, par2EnumArmorMaterial, par3, par4);
        this.renderIndex = par3;

    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:" + itemName);
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
    		String type) {
    	if (itemstack.itemID == mod_RpgInventory.magehood.itemID || itemstack.itemID == mod_RpgInventory.magegown.itemID || itemstack.itemID == mod_RpgInventory.mageboots.itemID) {
            return "armor:mage_1.png";
        }
        if (itemstack.itemID == mod_RpgInventory.magepants.itemID) {
            return "armor:mage_2.png";
        }

        if (itemstack.itemID == mod_RpgInventory.archerboots.itemID || itemstack.itemID == mod_RpgInventory.archerchest.itemID || itemstack.itemID == mod_RpgInventory.archerhood.itemID) {
            return "armor:arch_1.png";
        }
        if (itemstack.itemID == mod_RpgInventory.archerpants.itemID) {
            return "armor:arch_2.png";
        }
        if (itemstack.itemID == mod_RpgInventory.berserkerHood.itemID || itemstack.itemID == mod_RpgInventory.berserkerChest.itemID || itemstack.itemID == mod_RpgInventory.berserkerBoots.itemID) {
            return "armor:berserk_1.png";
        }
        if (itemstack.itemID == mod_RpgInventory.berserkerLegs.itemID) {
            return "armor:berserk_2.png";
        }
        // RPG++
        if (mod_RpgInventory.hasRpg) {
            if (itemstack.itemID == mod_RpgInventory.necroHood.itemID || itemstack.itemID == mod_RpgInventory.necroChestplate.itemID || itemstack.itemID == mod_RpgInventory.necroBoots.itemID) {
                return "armor:necro_1.png";
            }
            if (itemstack.itemID == mod_RpgInventory.necroLeggings.itemID) {
                return "armor:necro_2.png";
            }

            if (itemstack.itemID == mod_RpgInventory.palaHelm.itemID || itemstack.itemID == mod_RpgInventory.palaChest.itemID || itemstack.itemID == mod_RpgInventory.palaBoots.itemID) {
                return "armor:pal_1.png";
            }
            if (itemstack.itemID == mod_RpgInventory.palaLeggings.itemID) {
                return "armor:pal_2.png";
            }
        }
        //Rpg Rogue Beastmaster
        if (mod_RpgInventory.hasRogue) {
            if (itemstack.itemID == mod_RpgInventory.beastBoots.itemID || itemstack.itemID == mod_RpgInventory.beastChest.itemID || itemstack.itemID == mod_RpgInventory.beastHood.itemID) {
                return "armor:beast_1.png";
            }
            if (itemstack.itemID == mod_RpgInventory.beastLegs.itemID) {
                return "armor:beast_2.png";
            }

            if (itemstack.itemID == mod_RpgInventory.rogueHood.itemID || itemstack.itemID == mod_RpgInventory.rogueChest.itemID || itemstack.itemID == mod_RpgInventory.rogueBoots.itemID) {
                return "armor:rogue_1.png";
            }
            if (itemstack.itemID == mod_RpgInventory.rogueLegs.itemID) {
                return "armor:rogue_2.png";
            }
        }
        if (mod_RpgInventory.hasMage) {
            if (itemstack.itemID == mod_RpgInventory.archmageHood.itemID || itemstack.itemID == mod_RpgInventory.archmageChest.itemID || itemstack.itemID == mod_RpgInventory.archMageBoots.itemID) {
                return "armor:archMage_1.png";
            }
            if (itemstack.itemID == mod_RpgInventory.archmageLegs.itemID) {
                return "armor:archMage_2.png";
            }
        }
        return null;
    }
}
