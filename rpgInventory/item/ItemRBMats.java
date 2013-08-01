package rpgInventory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRBMats extends Item {

    public ItemRBMats(int par1) {
        super(par1);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int par2) {
        if (is.getItem() == mod_RpgInventory.beastLeather) {
            return 0x0b910d;
        }
        if (is.getItem() == mod_RpgInventory.rogueLeather) {
            return 0xae1fe1;
        }
        return 16777215;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon(texture);
    }
}
