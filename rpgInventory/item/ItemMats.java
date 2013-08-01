package rpgInventory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMats extends Item {

    public ItemMats(int par1) {
        super(par1);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int par2) {
        if (is.getItem() == mod_RpgInventory.necro_skin) {
            return 0xee0e1d;
        }
        if (is.getItem() == mod_RpgInventory.pala_steel) {
            return 0xf9f925;
        }
        return 16777215;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon(texture);
    }
}
