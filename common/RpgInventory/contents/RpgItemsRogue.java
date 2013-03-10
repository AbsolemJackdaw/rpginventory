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

public class RpgItemsRogue {

	public static void init(mod_RpgInventory inv, CreativeTabs tab)
	{
		inv.daggers = new ItemRpgArmor(RpgConfig.instance.daggersID, 1, 0, "").setItemName("daggerRogue").setIconCoord(6, 2).setCreativeTab(tab);
		inv.beastAxe = new ItemBeastAxe(RpgConfig.instance.beastAxe).setFull3D().setIconIndex(39).setItemName("beastmasteraxe").setCreativeTab(tab);
		
		inv.rogueLeather = new ItemRBMats(RpgConfig.instance.rogueLeatherID).setItemName("rogue leather").setIconIndex(103).setCreativeTab(tab);
		inv.beastLeather = new ItemRBMats(RpgConfig.instance.beastLeatherID).setItemName("beast leather").setIconIndex(103).setCreativeTab(tab);
		inv.beastShield = new ItemRpgArmor(RpgConfig.instance.beastShield, 1, 0, "").setItemName("beastShield").setIconCoord(3, 5).setCreativeTab(tab);

		inv.rogueHood = new BonusArmor(RpgConfig.instance.rogueHoodID, inv.rogueArmor, 4, 0).setItemName("rogue1").setIconCoord(12,4).setCreativeTab(tab);
		inv.rogueChest = new BonusArmor(RpgConfig.instance.rogueChestID,inv. rogueArmor, 4, 1).setItemName("rogue2").setIconCoord(12,5).setCreativeTab(tab);
		inv.rogueLegs = new BonusArmor(RpgConfig.instance.rogueLegsID, inv.rogueArmor, 4, 2).setItemName("rogue3").setIconCoord(12,6).setCreativeTab(tab);
		inv.rogueBoots = new BonusArmor(RpgConfig.instance.rogueBootsID, inv.rogueArmor, 4, 3).setItemName("rogue4").setIconCoord(12,7).setCreativeTab(tab);

		inv.beastHood = new BonusArmor(RpgConfig.instance.beastHoodID, inv.beastMaster, 4, 0).setItemName("beast1").setIconCoord(11,4).setCreativeTab(tab);
		inv.beastChest = new BonusArmor(RpgConfig.instance.beastChestID, inv.beastMaster, 4, 1).setItemName("beast2").setIconCoord(11,5).setCreativeTab(tab);
		inv.beastLegs = new BonusArmor(RpgConfig.instance.beastLegsID, inv.beastMaster, 4, 2).setItemName("beast3").setIconCoord(11,6).setCreativeTab(tab);
		inv.beastBoots = new BonusArmor(RpgConfig.instance.beastBootsID, inv.beastMaster, 4, 3).setItemName("beast4").setIconCoord(11,7).setCreativeTab(tab);

		inv.whistle = new ItemRBMats2(RpgConfig.instance.whistleID).setIconIndex(40).setItemName("whistle").setCreativeTab(tab);

		LanguageRegistry.addName(inv.daggers, "Rogue Daggers");
		LanguageRegistry.addName(inv.rogueLeather, "Rogue Leather");
		LanguageRegistry.addName(inv.beastLeather, "BeastMaster Leather");
		LanguageRegistry.addName(inv.rogueHood, "Rogue Hood");
		LanguageRegistry.addName(inv.rogueChest, "Rogue Breast Plate");
		LanguageRegistry.addName(inv.rogueLegs, "Rogue Chaps");
		LanguageRegistry.addName(inv.rogueBoots, "Rogue Boots");
		LanguageRegistry.addName(inv.beastHood, "BeastMaster Hood");
		LanguageRegistry.addName(inv.beastChest, "BeastMaster Body Protection");
		LanguageRegistry.addName(inv.beastLegs, "BeastMaster Leg Protection");
		LanguageRegistry.addName(inv.beastBoots, "BeastMaster Shoes");
		LanguageRegistry.addName(inv.whistle, "Pet Whistle");
		LanguageRegistry.addName(inv.whistle, "Pet Whistle");
		LanguageRegistry.addName(inv.beastShield, "BeastMaster Shield");
		LanguageRegistry.addName(inv.beastAxe, "BeastMaster Forest Axe");


		GameRegistry.addShapelessRecipe(new ItemStack(inv.whistle), new Object[]{Item.stick, Item.reed,Item.reed});

		inv.recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
		inv.recipeItems = new Object[][]{{inv.rogueLeather, inv.beastLeather }, {inv.rogueHood,inv.beastHood},
				{inv.rogueChest, inv.beastChest}, {inv.rogueLegs, inv.beastLegs}, {inv.rogueBoots, inv.beastBoots}};

		for (int var2 = 0; var2 < inv.recipeItems[0].length; ++var2) {
			Object var3 = inv.recipeItems[0][var2];

			for (int var4 = 0; var4 < inv.recipeItems.length - 1; ++var4) {
				Item var5 = (Item) inv.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[]{inv.recipePatterns[var4], 'X', var3});
			}
		}
	}
}
