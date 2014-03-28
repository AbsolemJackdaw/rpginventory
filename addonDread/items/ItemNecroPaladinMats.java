package addonDread.items;

import net.minecraft.item.ItemStack;
import rpgInventory.item.ItemMats;
import addonDread.mod_RpgPlus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNecroPaladinMats extends ItemMats {

	public ItemNecroPaladinMats(int par1) {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		if (is.getItem() == mod_RpgPlus.necro_skin) {
			return 0xee0e1d;
		}
		if (is.getItem() == mod_RpgPlus.pala_steel) {
			return 0xf9f925;
		}
		return 16777215;
	}
}
