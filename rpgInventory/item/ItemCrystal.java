package rpgInventory.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import rpgInventory.item.armor.ItemRpgArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemCrystal extends ItemRpgArmor {

    public static final String[] pets = new String[]{"Empty Crystal", "Boar", "Spider", "Bull"};

    public ItemCrystal(int id, int armorType, int maxDamage, String name) {
        super(id, armorType, maxDamage, name);
        this.setHasSubtypes(true);
        //Max stack size MUST be 1!
        this.maxStackSize = 1;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:petCrystal");
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 3);
        String itemname = pets[var2];
        if (var2 > 0) {
            NBTTagCompound tags = par1ItemStack.getTagCompound();
            if (tags != null) {
                if (tags.hasKey("PetLevel") && tags.hasKey("PetName")) {
                    itemname = tags.getString("PetName");
                }
            }
        }
        return itemname;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
        NBTTagCompound tags = stack.getTagCompound();
        int var2 = MathHelper.clamp_int(stack.getItemDamage(), 0, 3);

        if (var2 > 0) {
        }
        if (tags != null) {
            if (tags.hasKey("PetLevel")) {
                list.add(StatCollector.translateToLocal("Level : " + String.valueOf(tags.getInteger("PetLevel"))));
            }
            if (tags.hasKey("PetAttack")) {
                list.add(StatCollector.translateToLocal("ATK : " + String.valueOf(tags.getInteger("PetAttack"))));
            }
            if (tags.hasKey("PetHealth") && tags.hasKey("PetHealth")) {
                list.add(StatCollector.translateToLocal("HP : "
                        + String.valueOf(tags.getFloat("PetHealth") + "/"
                        + String.valueOf(tags.getFloat("PetMaxHealth")))));
            }
            if (tags.hasKey("PetLevel")) {
                if (tags.getInteger("PetLevel") >= 50) {
                    if (tags.hasKey("isSaddled")) {
                        if (tags.getBoolean("isSaddled") == true) {
                            list.add(StatCollector.translateToLocal("Saddled"));
                        }
                        if (tags.getBoolean("isSaddled") == false) {
                            list.add(StatCollector.translateToLocal("No Saddle"));
                        }
                    }
                } else {
                    list.add(StatCollector.translateToLocal("Levels left to saddle: ") + String.valueOf(50 - tags.getInteger("PetLevel")));
                }
            }
            if (tags.hasKey("OwnerName")) {
                list.add(StatCollector.translateToLocal("Owner :" + tags.getString("OwnerName")));
            }
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye
     * returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack is) {
        if (is.getItemDamage() > 0) {
            return true;
        }
        return is.isItemEnchanted();
    }

    public int getColorFromItemStack(ItemStack b2, int par2) {
        if (b2.getItemDamage() == 1) {
            return 0xc36113;
        }

        if (b2.getItemDamage() == 2) {
            return 0x0a8274;
        }

        if (b2.getItemDamage() == 3) {
            return 0xe71809;
        }

        return 0xffffff;
    }
}
