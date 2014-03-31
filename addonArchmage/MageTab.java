package addonArchmage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MageTab extends CreativeTabs {

	public MageTab(int par1, String label) {
		super(par1, label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return RpgArchmageAddon.archmageHood;
	}

	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}
}
