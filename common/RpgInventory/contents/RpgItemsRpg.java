package RpgInventory.contents;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import RpgInventory.mod_RpgInventory;
import RpgInventory.Configuration.RpgConfig;
import RpgInventory.item.ItemMats;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgPlusPlusArmor;
import RpgInventory.weapons.ItemGrandSword;
import RpgInventory.weapons.ItemNecroSkull;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class RpgItemsRpg {

	public static void init(mod_RpgInventory inv, CreativeTabs tab)
	{
		EnumArmorMaterial necroArmor = EnumHelper.addArmorMaterial("necro_material", 20, new int[]{2, 5, 1, 1}, 5);	// use of Souls
		EnumArmorMaterial paladin = EnumHelper.addArmorMaterial("pala_material", 20, new int[]{4, 7, 2, 1}, 5);	// use of Steel
		inv.NecroToolMaterial = EnumHelper.addToolMaterial("souls", 0, 1024, 5F, 1, 0);
		inv.PalaToolMaterial = EnumHelper.addToolMaterial("steel", 0, 1024, 5F, 1, 0);

		inv.necroHood = new BonusArmor(RpgConfig.instance.necroHoodID, necroArmor, 4, 0).setIconIndex(12).setItemName("Necro Helmet").setCreativeTab(mod_RpgInventory.tab);
		inv.necroChestplate = new BonusArmor(RpgConfig.instance.necroChestplateID, necroArmor, 4, 1).setIconIndex(12 + 16).setItemName("Necro Chestplate").setCreativeTab(mod_RpgInventory.tab);
		inv.necroLeggings = new BonusArmor(RpgConfig.instance.necroLeggingsID, necroArmor, 4, 2).setIconIndex(12 + 32).setItemName("Necro Leggings").setCreativeTab(mod_RpgInventory.tab);
		inv.necroBoots = new BonusArmor(RpgConfig.instance.necroBootsID, necroArmor, 4, 3).setIconIndex(12 + 48).setItemName("Necro Boots").setCreativeTab(mod_RpgInventory.tab);

		inv.palaHelm = new BonusArmor(RpgConfig.instance.palaHelmID, paladin, 4, 0).setIconIndex(11).setItemName("Pala Helmet").setCreativeTab(mod_RpgInventory.tab);
		inv.palaChest = new BonusArmor(RpgConfig.instance.palaChestID, paladin, 4, 1).setIconIndex(11 + 16).setItemName("Pala Chestplate").setCreativeTab(mod_RpgInventory.tab);
		inv.palaLeggings = new BonusArmor(RpgConfig.instance.palaLeggingsID, paladin, 4, 2).setIconIndex(11 + 32).setItemName("Pala Leggins").setCreativeTab(mod_RpgInventory.tab);
		inv.palaBoots = new BonusArmor(RpgConfig.instance.palaBootsID, paladin, 4, 3).setIconIndex(11 + 48).setItemName("Pala Boots").setCreativeTab(mod_RpgInventory.tab);

		inv.necro_shield = new ItemRpgPlusPlusArmor(RpgConfig.instance.necro_shieldID, 1, 250, "necro").setIconIndex(254).setItemName("Necro Shield").setCreativeTab(tab);
		inv.necro_weapon = new ItemNecroSkull(RpgConfig.instance.necro_weaponID, inv.NecroToolMaterial).setFull3D().setItemName("Necro Staff").setIconIndex(253).setCreativeTab(mod_RpgInventory.tab);

		inv.pala_shield = new ItemRpgPlusPlusArmor(RpgConfig.instance.pala_shieldID, 1, 450, "pala").setIconIndex(255).setItemName("Pala Shield").setCreativeTab(tab);
		inv.pala_weapon = new ItemGrandSword(RpgConfig.instance.pala_weaponID, inv.PalaToolMaterial).setFull3D().setIconIndex(252).setItemName("Pala Sword").setCreativeTab(mod_RpgInventory.tab);

		inv.necro_skin = new ItemMats(RpgConfig.instance.necro_skinID).setIconIndex(103).setItemName("NecroSkin").setCreativeTab(mod_RpgInventory.tab);
		inv.pala_steel = new ItemMats(RpgConfig.instance.pala_steelID).setIconIndex(23).setItemName("PalaSteel").setCreativeTab(mod_RpgInventory.tab);

		LanguageRegistry.addName(inv.necro_shield, "NecroMancer Shield");
		LanguageRegistry.addName(inv.pala_shield, "Paladin Shield");
		LanguageRegistry.addName(inv.necroHood, "NecroMancer Hood");
		LanguageRegistry.addName(inv.necroChestplate, "NecroMancer Vest");
		LanguageRegistry.addName(inv.necroLeggings, "NecroMancer Pants");
		LanguageRegistry.addName(inv.necroBoots, "NecroMancer Boots");
		LanguageRegistry.addName(inv.palaHelm, "Paladin Helmet");
		LanguageRegistry.addName(inv.palaChest, "Paladin Chestplate");
		LanguageRegistry.addName(inv.palaLeggings, "Paladin Leggings");
		LanguageRegistry.addName(inv.palaBoots, "Paladin Boots");
		LanguageRegistry.addName(inv.necro_weapon, "NecroMancer Skull");
		LanguageRegistry.addName(inv.pala_weapon, "Paladin's Pride");
		LanguageRegistry.addName(inv.pala_steel, "Paladin's Steel");
		LanguageRegistry.addName(inv.necro_skin, "Necromancer's Cloth");
	}
}
