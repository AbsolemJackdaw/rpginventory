package RpgInventory.weapons.hammer;

import RpgInventory.EnumRpgClass;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.mod_RpgInventory;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHammer extends ItemSword {

    Random rand = new Random();

    public ItemHammer(int par1, EnumToolMaterial mat) {
        super(par1, EnumToolMaterial.STONE);
        this.maxStackSize = 1;
        this.setMaxDamage(EnumToolMaterial.IRON.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        RpgInv rpg = mod_RpgInventory.proxy.getInventory(player.username);
        if (rpg.hasClass(EnumRpgClass.BERSERKER)) {
            if (rpg.hasClass(EnumRpgClass.SHIELDEDBERSERKER)) {
                if (player.getFoodStats().getFoodLevel() < 6
                        || player.getHealth() < 6) {
                    player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 1));
                } else if (player.getFoodStats().getFoodLevel() < 3
                        || player.getHealth() < 3) {
                    player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
                }
            } else {
                if (player.getFoodStats().getFoodLevel() < 4
                        || player.getHealth() < 4) {

                    player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 1));
                }
            }

        }
        par1ItemStack.damageItem(1, player);
        if(par1ItemStack.getItemDamage() > par1ItemStack.getMaxDamage()){
            par1ItemStack = null;
        }
        return par1ItemStack;
    }

    public String getTextureFile() {
        return "/subaraki/RPGinventoryTM.png";
    }
}
