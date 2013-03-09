package RpgInventory.contents;

import net.minecraft.creativetab.CreativeTabs;
import RpgInventory.mod_RpgInventory;
import RpgInventory.mod_RpgInventory.ITEMTYPE;
import RpgInventory.Configuration.RpgConfig;
import RpgInventory.item.ItemCrystal;
import RpgInventory.item.ItemMold;
import RpgInventory.item.ItemRageFood;
import RpgInventory.item.ItemRpg;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgInventory.weapons.bow.ItemArcherBow;
import RpgInventory.weapons.claymore.ItemClaymore;
import RpgInventory.weapons.hammer.ItemHammer;
import RpgInventory.weapons.staf.ItemStaf;
import RpgInventory.weapons.wand.ItemMageWand;

public class RpgItems {

	public static void init(mod_RpgInventory inv, CreativeTabs tab)
	{
		inv.neckgold = new ItemRpgArmor(RpgConfig.instance.neckgoldID, 0, -1, "").setIconIndex(0).setItemName("goldnecklace").setCreativeTab(tab);
		inv.neckdia = new ItemRpgArmor(RpgConfig.instance.neckdiaID, 0, -1, "").setIconIndex(0 + 16).setItemName("neckwithdia").setCreativeTab(tab);
		inv.neckem = new ItemRpgArmor(RpgConfig.instance.neckemID, 0, -1, "").setIconIndex(0 + 32).setItemName("neckwithem").setCreativeTab(tab);
		inv.necklap = new ItemRpgArmor(RpgConfig.instance.necklapID, 0, -1, "").setIconIndex(0 + 48).setItemName("neckwithlap").setCreativeTab(tab);

		inv.ringgold = new ItemRpgArmor(RpgConfig.instance.ringgoldID, 4, -1, "").setIconIndex(1).setItemName("goldring").setCreativeTab(tab);
		inv.ringdia = new ItemRpgArmor(RpgConfig.instance.ringdiaID, 4, -1, "").setIconIndex(1 + 16).setItemName("ringwithdia").setCreativeTab(tab);
		inv.ringem = new ItemRpgArmor(RpgConfig.instance.ringemID, 4, -1, "").setIconIndex(1 + 32).setItemName("ringwithem").setCreativeTab(tab);
		inv.ringlap = new ItemRpgArmor(RpgConfig.instance.ringlapID, 4, -1, "").setIconIndex(1 + 48).setItemName("ringwithlapis").setCreativeTab(tab);

		inv.glovesbutter = new ItemRpgArmor(RpgConfig.instance.glovesbutterID, 3, -1, "").setIconIndex(2).setItemName("goldrrgloves").setCreativeTab(tab);
		inv.glovesdia = new ItemRpgArmor(RpgConfig.instance.glovesdiaID, 3, -1, "").setIconIndex(2 + 16).setItemName("glovesdia").setCreativeTab(tab);
		inv.glovesem = new ItemRpgArmor(RpgConfig.instance.glovesemID, 3, -1, "").setIconIndex(2 + 32).setItemName("glovesem").setCreativeTab(tab);
		inv.gloveslap = new ItemRpgArmor(RpgConfig.instance.gloveslapID, 3, -1, "").setIconIndex(2 + 48).setItemName("gloveslap").setCreativeTab(tab);

		inv.archersShield = new ItemRpgArmor(RpgConfig.instance.archersShieldID, 1, 200, "").setIconIndex(3).setItemName("woodshield").setCreativeTab(tab);
		inv.berserkerShield = new ItemRpgArmor(RpgConfig.instance.berserkerShieldID, 1, 350, "").setIconIndex(4).setItemName("ironshield").setCreativeTab(tab);
		inv.talisman = new ItemRpgArmor(RpgConfig.instance.talismanID, 1, 200, "").setIconIndex(5).setItemName("talisman").setCreativeTab(tab);

		inv.cloak = new ItemRpgArmor(RpgConfig.instance.cloakID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloak").setCreativeTab(tab);
		inv.cloakI = new ItemRpgArmor(RpgConfig.instance.cloakIID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("InvisibilityCloak").setCreativeTab(tab);

		inv.magehood = new BonusArmor(RpgConfig.instance.magehoodID, inv.robes, 4, 0).setItemName("magehoody").setIconIndex(15).setCreativeTab(tab);
		inv.magegown = new BonusArmor(RpgConfig.instance.magegownID, inv.robes, 4, 1).setItemName("magegowny").setIconIndex(31).setCreativeTab(tab);
		inv.magepants = new BonusArmor(RpgConfig.instance.magepantsID,inv. robes, 4, 2).setItemName("magepants").setIconIndex(47).setCreativeTab(tab);
		inv.mageboots = new BonusArmor(RpgConfig.instance.magebootsID, inv.robes, 4, 3).setItemName("mageshoes").setIconIndex(63).setCreativeTab(tab);

		inv.archerhood = new BonusArmor(RpgConfig.instance.archerhoodID, inv.hides, 4, 0).setItemName("archerhoody").setIconIndex(14).setCreativeTab(tab);
		inv.archerchest = new BonusArmor(RpgConfig.instance.archerchestID, inv.hides, 4, 1).setItemName("archerdhide").setIconIndex(30).setCreativeTab(tab);
		inv.archerpants = new BonusArmor(RpgConfig.instance.archerpantsID, inv.hides, 4, 2).setItemName("archerpants").setIconIndex(46).setCreativeTab(tab);
		inv.archerboots = new BonusArmor(RpgConfig.instance.archerbootsID, inv.hides, 4, 3).setItemName("archerboots").setIconIndex(62).setCreativeTab(tab);

		inv.berserkerHood = new BonusArmor(RpgConfig.instance.berserkerHoodID, inv.armoury, 4, 0).setItemName("rambo1").setIconIndex(13).setCreativeTab(tab);
		inv.berserkerChest = new BonusArmor(RpgConfig.instance.berserkerChestID, inv.armoury, 4, 1).setItemName("rambo2").setIconIndex(29).setCreativeTab(tab);
		inv.berserkerLegs = new BonusArmor(RpgConfig.instance.berserkerLegsID, inv.armoury, 4, 2).setItemName("rambo3").setIconIndex(45).setCreativeTab(tab);
		inv.berserkerBoots = new BonusArmor(RpgConfig.instance.berserkerBootsID, inv.armoury, 4, 3).setItemName("rambo4").setIconIndex(61).setCreativeTab(tab);

		inv.claymore = new ItemClaymore(RpgConfig.instance.claymoreID, inv.clay).setFull3D().setMaxDamage(1024).setItemName("Daggerkiller").setIconIndex(20).setCreativeTab(tab);
		inv.wand = new ItemMageWand(RpgConfig.instance.wandID).setFull3D().setMaxDamage(400).setItemName("MagesWand").setIconIndex(21).setCreativeTab(tab);
		inv.elfbow = new ItemArcherBow(RpgConfig.instance.elfbowID).setFull3D().setMaxDamage(350).setItemName("ArcherBow").setIconIndex(22).setCreativeTab(tab);

		inv.animalskin = new ItemRpg(RpgConfig.instance.animalskinID).setItemName("animal skin").setIconIndex(103).setCreativeTab(tab);
		inv.tanHide = new ItemRpg(RpgConfig.instance.tanHideID).setItemName("tanned hide").setIconIndex(103).setCreativeTab(tab);
		inv.magecloth = new ItemRpg(RpgConfig.instance.mageclothID).setItemName("mage cloth").setIconIndex(103).setCreativeTab(tab);

		inv.wizardBook = new ItemRpg(RpgConfig.instance.wizardBookID).setItemName("wizardbook").setIconCoord(11, 11).setCreativeTab(tab);

		inv.hammer = new ItemHammer(RpgConfig.instance.hammerID, inv.stone).setItemName("BerserkerHammer").setMaxDamage(750).setIconIndex(26).setCreativeTab(tab);
		inv.staf = new ItemStaf(RpgConfig.instance.stafID).setItemName("MageStaff").setMaxStackSize(1).setMaxDamage(1500).setIconIndex(37).setCreativeTab(tab);

		inv.rageSeed = new ItemRageFood(RpgConfig.instance.rageSeedID, 0, 0f, false).setAlwaysEdible().setIconCoord(9, 0).setItemName("RageSeed").setMaxStackSize(8).setCreativeTab(tab);

		inv.cloakRed = new ItemRpgArmor(RpgConfig.instance.cloakRedID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakred").setCreativeTab(tab);
		inv.cloakYellow = new ItemRpgArmor(RpgConfig.instance.cloakYellowID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakyellow").setCreativeTab(tab);
		inv.cloakGreen = new ItemRpgArmor(RpgConfig.instance.cloakGreenID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakgreen").setCreativeTab(tab);
		inv.cloakBlue = new ItemRpgArmor(RpgConfig.instance.cloakBlueID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloaksaint").setCreativeTab(tab);
		inv.cloakSub = new ItemRpgArmor(RpgConfig.instance.cloakSubID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakSub").setCreativeTab(tab);

		inv.colmold = new ItemMold(RpgConfig.instance.colmoldID).setIconIndex(64).setItemName("colmold").setCreativeTab(tab);
		inv.ringmold = new ItemMold(RpgConfig.instance.ringmoldID).setIconIndex(65).setItemName("ringmold").setCreativeTab(tab);
		inv.wantmold = new ItemMold(RpgConfig.instance.wantmoldID).setIconIndex(66).setItemName("wantmold").setCreativeTab(tab);

		inv.crystal = new ItemCrystal(RpgConfig.instance.crystalID, ITEMTYPE.CRYSTAL, -1, "").setIconIndex(52).setItemName("petCrystal").setCreativeTab(tab);
	}
}
