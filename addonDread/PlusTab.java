package addonDread;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlusTab extends CreativeTabs {

	public PlusTab(int par1, String label) {
		super(par1, label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return mod_RpgPlus.necro_weapon;
	}

	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}
}
