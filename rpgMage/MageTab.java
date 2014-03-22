package rpgMage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MageTab extends CreativeTabs {

	public MageTab(int par1, String label) {
		super(par1, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return mod_RpgMageSet.archmageHood;
	}

	@Override
	public String getTranslatedTabLabel() {
		return "Mage Armoury";
	}
}
