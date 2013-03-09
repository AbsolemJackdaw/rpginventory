package RpgInventory.contents;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import RpgInventory.mod_RpgInventory;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class RpgItemsNames {

	public static void init(mod_RpgInventory inv, CreativeTabs tab)
	{
		LanguageRegistry.addName(inv.forgeBlock, "Mold Forge");

		LanguageRegistry.addName(inv.cloakRed, "Red Cape");
		LanguageRegistry.addName(inv.cloakYellow, "Yellow Cape");
		LanguageRegistry.addName(inv.cloakGreen, "Green Cape");
		LanguageRegistry.addName(inv.cloakBlue, "Blue Cape");
		LanguageRegistry.addName(inv.cloakSub, "Black Cape");

		LanguageRegistry.addName(inv.neckgold, "Gold Necklace");
		LanguageRegistry.addName(inv.neckdia, "Health Necklace");
		LanguageRegistry.addName(inv.neckem, " Buffed Necklace");
		LanguageRegistry.addName(inv.necklap, " Strength Necklace");

		LanguageRegistry.addName(inv.glovesbutter, "Gold Gloves");
		LanguageRegistry.addName(inv.glovesdia, "Health Gloves");
		LanguageRegistry.addName(inv.glovesem, "Buffed Gloves");
		LanguageRegistry.addName(inv.gloveslap, "Strength Gloves");

		LanguageRegistry.addName(inv.ringgold, "Golden Ring");
		LanguageRegistry.addName(inv.ringdia, "Health Ring");
		LanguageRegistry.addName(inv.ringem, "Buffed Ring");
		LanguageRegistry.addName(inv.ringlap, "Strength Ring");

		LanguageRegistry.addName(inv.archersShield, "Small Archer Shield");
		LanguageRegistry.addName(inv.berserkerShield, "Berserker's Iron Thorn");
		LanguageRegistry.addName(inv.talisman, "Wizard's Talisman");

		LanguageRegistry.addName(inv.cloak, "Cloak");
		LanguageRegistry.addName(inv.cloakI, "Invisibility Cloak");

		LanguageRegistry.addName(inv.magehood, "Mage Hood");
		LanguageRegistry.addName(inv.magegown, "Mage Gown");
		LanguageRegistry.addName(inv.magepants, "Mage Under Gown");
		LanguageRegistry.addName(inv.mageboots, "Mage Shoes");

		LanguageRegistry.addName(inv.archerhood, "Archer Hood");
		LanguageRegistry.addName(inv.archerchest, "Archer Chest");
		LanguageRegistry.addName(inv.archerpants, "Archer Leggings");
		LanguageRegistry.addName(inv.archerboots, "Archer Boots");

		LanguageRegistry.addName(inv.berserkerHood, "Berserker's Head Protection");
		LanguageRegistry.addName(inv.berserkerChest, "Berserker's Body Protection");
		LanguageRegistry.addName(inv.berserkerLegs, "Berserker's Leg Protection");
		LanguageRegistry.addName(inv.berserkerBoots, "Berserker's Feet Protection");

		LanguageRegistry.addName(inv.wand, "Soul Sphere");
		LanguageRegistry.addName(inv.claymore, "Berserker's Claymore");
		LanguageRegistry.addName(inv.elfbow, "Archer's Bow of Birch");

		LanguageRegistry.addName(inv.animalskin, "Animal Skin");
		LanguageRegistry.addName(inv.tanHide, "Tanned Hide");
		LanguageRegistry.addName(inv.magecloth, "Mage Cloth");

		LanguageRegistry.addName(inv.wizardBook, "Wizard's Knowledge, Volume I");
		LanguageRegistry.addName(inv.hammer, "Berserker's Rage Breaker");
		LanguageRegistry.addName(inv.staf, "Lunar Staff");
		LanguageRegistry.addName(inv.rageSeed, "Rage Seeds");

		LanguageRegistry.addName(inv.colmold, "Mold");
		LanguageRegistry.addName(inv.ringmold, "Mold");
		LanguageRegistry.addName(inv.wantmold, "Mold");
		LanguageRegistry.addName(new ItemStack(inv.crystal,1,0), "Pet Crystal");
		LanguageRegistry.addName(new ItemStack(inv.crystal,1,1), "Boar");
		LanguageRegistry.addName(new ItemStack(inv.crystal,1,2), "Spider");
		LanguageRegistry.addName(new ItemStack(inv.crystal,1,3), "Bull");

	}
}
