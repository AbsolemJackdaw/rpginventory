package rpgVanillaShields;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShieldTab extends CreativeTabs {

	public ShieldTab(int par1, String label) {
		super(par1, label);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return mod_VanillaShields.shieldDiamond;
	}

	@Override
	public String getTranslatedTabLabel() {
		return "Vanilla Shields";
	}
}
