package rpgRogueBeast;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RBTab extends CreativeTabs {

	public RBTab(int par1, String label) {
		super(par1, label);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return mod_RpgRB.beastAxe.itemID;
	}

	@Override
	public String getTranslatedTabLabel() {
		return "Rogue/BeastMaster Armoury";
	}
}
