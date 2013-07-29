package WWBS.wwbs;

import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class InventoryTab extends CreativeTabs {

	public InventoryTab(int par1, String par2Str) {
		super(par1, par2Str);
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return Item.ingotGold.itemID;
	}

	public String getTranslatedTabLabel()
	{
		return "Bank Tab";
	}
}
