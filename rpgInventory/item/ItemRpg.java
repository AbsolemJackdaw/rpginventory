package rpgInventory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpg extends Item {

    public ItemRpg(int par1) {
        super(par1);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon(texture);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        if (par1ItemStack.itemID == mod_RpgInventory.magecloth.itemID || par1ItemStack.itemID == mod_RpgInventory.wizardBook.itemID) {
            return true;
        }
        return false;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if (par1ItemStack.itemID == mod_RpgInventory.wizardBook.itemID) {
            mod_RpgInventory.proxy.openGUI(player, 2);
        }
        return par1ItemStack;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int par2) {
        if (is.getItem() == mod_RpgInventory.tanHide) {
            return 0xa24203;
        }
        if (is.getItem() == mod_RpgInventory.magecloth) {
            return 0x000080;
        }
        if (is.getItem() == mod_RpgInventory.animalskin) {
            return 0x71544f;
        }
        return 16777215;
    }
}
