package rpgInventory.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RpgInventoryTab extends CreativeTabs {

	public RpgInventoryTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return mod_RpgInventory.ringem;
	}

	@Override
	public String getTranslatedTabLabel() {
		return "RPG Armoury";
	}

}
