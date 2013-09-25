package rpgRogueBeast.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import rpgInventory.item.ItemMats;
import rpgRogueBeast.mod_RpgRB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRBMats extends ItemMats {

    public ItemRBMats(int par1) {
        super(par1);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int par2) {
        if (is.getItem() == mod_RpgRB.beastLeather) {
            return 0x0b910d;
        }
        if (is.getItem() == mod_RpgRB.rogueLeather) {
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
