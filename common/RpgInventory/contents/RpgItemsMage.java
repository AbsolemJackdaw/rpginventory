package RpgInventory.contents;

import net.minecraft.creativetab.CreativeTabs;
import RpgInventory.mod_RpgInventory;
import RpgInventory.Configuration.RpgConfig;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgMageSet.weapons.ItemElementalStaff;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class RpgItemsMage {

	public static void init(mod_RpgInventory inv, CreativeTabs tab)
	{
		inv.fireStaff = new ItemElementalStaff(RpgConfig.instance.fireStaff, 1).setItemName("FireStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(53).setCreativeTab(mod_RpgInventory.tab);
		inv.frostStaff = new ItemElementalStaff(RpgConfig.instance.frostStaff, 2).setItemName("FrostStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(54).setCreativeTab(mod_RpgInventory.tab);
		inv.earthStaff = new ItemElementalStaff(RpgConfig.instance.staffEarth, 3).setItemName("EarthStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(55).setCreativeTab(mod_RpgInventory.tab);
		inv.windStaff = new ItemElementalStaff(RpgConfig.instance.staffWind, 4).setItemName("WindStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(56).setCreativeTab(mod_RpgInventory.tab);
		inv.ultimateStaff = new ItemElementalStaff(RpgConfig.instance.staffUltimate, 5).setItemName("UltimateStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(57).setCreativeTab(mod_RpgInventory.tab);
		inv.archBook = new ItemRpgArmor(RpgConfig.instance.archBook,1,300,"").setIconCoord(4,5).setCreativeTab(tab);

		LanguageRegistry.addName(inv.fireStaff, "Fire Staff");
		LanguageRegistry.addName(inv.frostStaff, "Frost Staff");
		LanguageRegistry.addName(inv.earthStaff, "Earth Staff");
		LanguageRegistry.addName(inv.windStaff, "Wind Staff");
		LanguageRegistry.addName(inv.ultimateStaff, "Ultimate Staff");
		LanguageRegistry.addName(inv.archBook, "ArchMage Book");
	}
}
