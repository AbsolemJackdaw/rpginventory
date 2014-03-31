package addonMasters.items;

import net.minecraft.item.ItemStack;
import rpgInventory.item.ItemMats;
import addonMasters.RpgMastersAddon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRBMats extends ItemMats {

	public ItemRBMats() {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		if (is.getItem() == RpgMastersAddon.beastLeather)
			return 0x0b910d;
		if (is.getItem() == RpgMastersAddon.rogueLeather)
			return 0xae1fe1;
		return 16777215;
	}
}
