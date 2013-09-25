package rpgMage;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MageTab extends CreativeTabs {

	public MageTab(int par1,String label) {
		super(par1, label);
		// TODO Auto-generated constructor stub
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return mod_RpgMageSet.archmageHood.itemID;
	}

	public String getTranslatedTabLabel()
	{
		return "Mage Armoury";
	}
}
