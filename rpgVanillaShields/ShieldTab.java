package rpgVanillaShields;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShieldTab extends CreativeTabs {

	public ShieldTab(int par1,String label) {
		super(par1, label);
		// TODO Auto-generated constructor stub
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return mod_VanillaShields.shieldDiamond.itemID;
	}

	public String getTranslatedTabLabel()
	{
		return "Vanilla Shields";
	}
}
