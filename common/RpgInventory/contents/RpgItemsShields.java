package RpgInventory.contents;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import RpgInventory.mod_RpgInventory;
import RpgInventory.Configuration.RpgConfig;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgRB.ItemRBMats;
import RpgRB.ItemRBMats2;
import RpgRB.axe.ItemBeastAxe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class RpgItemsShields {

	public static void init(mod_RpgInventory inv, CreativeTabs tab)
	{
		inv.shieldWood = new ItemRpgArmor(RpgConfig.instance.shieldWoodID, 1, 50, "wood").setIconIndex(67).setItemName("vanillaWood").setCreativeTab(tab);
		inv.shieldIron = new ItemRpgArmor(RpgConfig.instance.shieldIronID, 1, 125, "iron").setIconIndex(68).setItemName("vanillaIron").setCreativeTab(tab);
		inv.shieldGold = new ItemRpgArmor(RpgConfig.instance.shieldGoldID, 1, 250, "gold").setIconIndex(69).setItemName("vanillaGold").setCreativeTab(tab);
		inv.shieldDiamond = new ItemRpgArmor(RpgConfig.instance.shieldDiamondID, 1, 500, "diamond").setIconIndex(70).setItemName("vanillaDiamond").setCreativeTab(tab);

		LanguageRegistry.addName(inv.shieldWood, "Wooden Shield");
		LanguageRegistry.addName(inv.shieldIron, "Iron Shield");
		LanguageRegistry.addName(inv.shieldGold, "Golden Shield");
		LanguageRegistry.addName(inv.shieldDiamond, "Diamond Shield");
	}
}
