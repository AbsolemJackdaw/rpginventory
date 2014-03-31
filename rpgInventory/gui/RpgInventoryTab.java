package rpgInventory.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import rpgInventory.RpgInventoryMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RpgInventoryTab extends CreativeTabs {

	public RpgInventoryTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return RpgInventoryMod.ringem;
	}

	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}
}
