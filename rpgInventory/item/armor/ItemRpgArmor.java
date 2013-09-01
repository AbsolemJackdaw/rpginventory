package rpgInventory.item.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpgArmor extends Item {

    /**
     * Holds the 'base' maxDamage that each armorType have.
     */
    private final int[] maxDamageArray = new int[]{30, 20, 50, 20, 30, 30};
    /**
     * Stores the armor type: 0 is necklace, 2 is cloak, 1 is shield, 3 is
     * gloves, 4 are rings
     */
    public int armorType;

    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on
     * the player: 0 is cloth, 1 is chain, 2 is iron, 3 is diamond and 4 is
     * gold.
     */
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:" + itemName);
    }
    public int renderJewelIndex;
    private String Name;

    public ItemRpgArmor(int par1, int par4, int maxDamage, String name) {
        super(par1);
        this.armorType = par4;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setMaxDamage(maxDamage);
        Name = name;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        if (par1ItemStack.itemID == mod_RpgInventory.cloakI.itemID) {
            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.itemID == mod_RpgInventory.cloakRed.itemID) {
            return 0xd2120e;
        }
        if (par1ItemStack.itemID == mod_RpgInventory.cloakGreen.itemID) {
            return 0x0fb15d;
        }
        if (par1ItemStack.itemID == mod_RpgInventory.cloakYellow.itemID) {
            return 0xf7cd09;
        }
        if (par1ItemStack.itemID == mod_RpgInventory.cloakSub.itemID) {
            return 0x440001;
        }
        if (par1ItemStack.itemID == mod_RpgInventory.cloakBlue.itemID) {
            return 0x291ef6;
        }

        return 0xffffff;
    }

    public void armorEffects(ItemStack is, EntityPlayer player) {
    }

    /**
     * gets the desired effect for jewelry of a certain kind. 0-3 speed boost
     * 4-7 healing, 8-11 damage and weapon healing, 12-15 emerald effects, 16-19
     * shield n cloak
     */
    public void effectSwitch(int id, EntityPlayer player, ItemStack is) {
    }

    /**
     * an effect that simulates drinking from a milk bucket, but only for
     * debuffs.
     */
    public void getMilkEffect(ItemStack itemstack, int time, EntityPlayer player) {
    }

    public void healWeapon(EntityPlayer player, int chances) {
    }

    /**
     * Returns the 'max damage' factor array for the armor, each piece of armor
     * have a durability factor (that gets multiplied by armor material factor)
     */
    int[] getMaxDamageArray() {
        return maxDamageArray;
    }


    /**
     * allows items to add custom lines of information to the mouseover
     * description
     */
    public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
        if (stack.itemID == mod_RpgInventory.ringem.itemID) {
            list.add(StatCollector.translateToLocal("Left: Dispell Debuffs 20s"));
            list.add(StatCollector.translateToLocal("Right: Haste"));
        }

        if (stack.itemID == mod_RpgInventory.neckem.itemID) {
            list.add(StatCollector.translateToLocal("Water Breathing"));
        }

        if (stack.itemID == mod_RpgInventory.glovesem.itemID) {
            list.add(StatCollector.translateToLocal("Resistance"));
        }

        if (stack.itemID == mod_RpgInventory.ringdia.itemID || stack.itemID == mod_RpgInventory.glovesdia.itemID
                || stack.itemID == mod_RpgInventory.neckdia.itemID) {
            list.add(StatCollector.translateToLocal("Healing"));
        }

        if (stack.itemID == mod_RpgInventory.ringgold.itemID || stack.itemID == mod_RpgInventory.glovesbutter.itemID
                || stack.itemID == mod_RpgInventory.neckgold.itemID) {
            list.add(StatCollector.translateToLocal("Jump/Speed"));
        }

        if (stack.itemID == mod_RpgInventory.ringlap.itemID || stack.itemID == mod_RpgInventory.gloveslap.itemID
                || stack.itemID == mod_RpgInventory.necklap.itemID) {
            list.add(StatCollector.translateToLocal("Strength/Passive Weapon Healing"));
        }
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        RpgInv rpg = mod_RpgInventory.proxy.getInventory(((EntityPlayer) par3EntityLiving).username);
        rpg.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer) par3EntityLiving);

        if (((EntityPlayer) par3EntityLiving).getCurrentEquippedItem().getItem() == mod_RpgInventory.daggers) {
        	if (rpg.hasClass(EnumRpgClass.NINJA)) {
                par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 1));
            }else if (rpg.hasClass(EnumRpgClass.ROGUE)) {
                par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 40, 0));
                if (((EntityPlayer) par3EntityLiving).worldObj.isDaytime()) {
                    par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 10);
                }
            } 
            if (((EntityPlayer) par3EntityLiving).worldObj.isDaytime()) {
                par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 10);
            } else {
                par2EntityLiving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) par3EntityLiving), 6);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Name;
    }
}
