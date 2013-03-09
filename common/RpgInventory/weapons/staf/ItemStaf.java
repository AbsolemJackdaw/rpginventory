package RpgInventory.weapons.staf;

import RpgInventory.mod_RpgInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemStaf extends ItemSword {

    protected boolean hasAttacked = false;

    public ItemStaf(int par1) {
        super(par1, EnumToolMaterial.STONE);
    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer p, int par4) {
        int time = this.getMaxItemUseDuration(par1ItemStack) - par4;

        if (p.isSneaking()) {
            if (time > 0 && time <= 100) {
                p.spawnExplosionParticle();
                double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
                double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
                double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
                p.worldObj.spawnParticle("heart", p.posX + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (p.worldObj.rand.nextFloat() * p.height), p.posZ + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, var2, var4, var6);
                int heal = (int) time / 50;
                if (p.getHealth() + heal <= p.getMaxHealth()) {
                    p.heal(heal);
                } else {
                    p.setEntityHealth(p.getMaxHealth());
                }
            }
            if (time > 100) {
                p.spawnExplosionParticle();
                double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
                double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
                double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
                p.worldObj.spawnParticle("heart", p.posX + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (p.worldObj.rand.nextFloat() * p.height), p.posZ + (double) (p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double) p.width, var2, var4, var6);
                int heal = (int) time / 30;
                if (p.getHealth() + heal <= p.getMaxHealth()) {
                    p.heal(heal);
                } else {
                    p.setEntityHealth(p.getMaxHealth());
                }
            }
        }
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer p) {
        ItemStack var33 = p.inventory.armorItemInSlot(3);
        ItemStack var32 = p.inventory.armorItemInSlot(2);
        ItemStack var31 = p.inventory.armorItemInSlot(1);
        ItemStack var30 = p.inventory.armorItemInSlot(0);

        if (var33 != null && var32 != null && var31 != null && var30 != null) {
            Item item = var33.getItem();
            Item item1 = var32.getItem();
            Item item2 = var31.getItem();
            Item item3 = var30.getItem();

            if (item.equals(mod_RpgInventory.magehood) && item1.equals(mod_RpgInventory.magegown)
                    && item2.equals(mod_RpgInventory.magepants) && item3.equals(mod_RpgInventory.mageboots)) {
                p.setItemInUse(is, this.getMaxItemUseDuration(is));
            }
        }
        return is;
    }

    public int getStrenght(EntityPlayer p) {
        return p.experienceLevel == 0 ? 1 : (int) p.experienceLevel;
    }

    public String getTextureFile() {
        return "/subaraki/RPGinventoryTM.png";
    }
}
