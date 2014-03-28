package rpgAddonMasters;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RBTab extends CreativeTabs {

	public RBTab(int par1, String label) {
		super(par1, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return mod_RpgRB.beastAxe;
	}

	@Override
	public String getTranslatedTabLabel() {
		return "Rogue/BeastMaster Armoury";
	}
}
