package rpgInventory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import rpgInventory.block.BlockForge;
import rpgInventory.block.te.TEMold;
import rpgInventory.config.RpgConfig;
import rpgInventory.entity.EntityHellArrow;
import rpgInventory.gui.RpgInventoryTab;
import rpgInventory.handelers.CommonTickHandler;
import rpgInventory.handelers.GuiHandler;
import rpgInventory.handelers.PlayerTracker;
import rpgInventory.handelers.RPGEventHooks;
import rpgInventory.handelers.packets.RpgPacketHandler;
import rpgInventory.handelers.proxy.CommonProxy;
import rpgInventory.item.ItemCandy;
import rpgInventory.item.ItemCrystal;
import rpgInventory.item.ItemMats;
import rpgInventory.item.ItemMold;
import rpgInventory.item.ItemRBMats;
import rpgInventory.item.ItemRBMats2;
import rpgInventory.item.ItemRageFood;
import rpgInventory.item.ItemRpg;
import rpgInventory.item.PetExpPotion;
import rpgInventory.item.armor.BonusArmor;
import rpgInventory.item.armor.ItemRpgArmor;
import rpgInventory.item.armor.ItemRpgPlusPlusArmor;
import rpgInventory.item.weapons.ItemArcherBow;
import rpgInventory.item.weapons.ItemBeastAxe;
import rpgInventory.item.weapons.ItemClaymore;
import rpgInventory.item.weapons.ItemElementalStaff;
import rpgInventory.item.weapons.ItemGrandSword;
import rpgInventory.item.weapons.ItemHammer;
import rpgInventory.item.weapons.ItemMageSphere;
import rpgInventory.item.weapons.ItemNecroSkull;
import rpgInventory.item.weapons.ItemStaf;
import rpgInventory.richUtil.potions.DecomposePotion;
import rpgInventory.richUtil.potions.MasochismPotion;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "rpginventorymod", name = "RPG Inventory Mod", version = "1.5.1 8.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv", "RpgRawInv"}, packetHandler = RpgPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv"}, packetHandler = RpgPacketHandler.class))
public class mod_RpgInventory {

	public static mod_RpgInventory instance;
	@SidedProxy(serverSide = "rpgInventory.handelers.proxy.CommonProxy", clientSide = "rpgInventory.handelers.proxy.ClientProxy")
	public static CommonProxy proxy;
	public static Item neckgold;
	public static Item neckdia;
	public static Item neckem;
	public static Item necklap;
	public static Item glovesbutter;
	public static Item glovesdia;
	public static Item glovesem;
	public static Item gloveslap;
	public static Item ringgold;
	public static Item ringdia;
	public static Item ringem;
	public static Item ringlap;
	public static Item archersShield;
	public static Item berserkerShield;
	public static Item talisman;
	public static Item pala_shield;
	public static Item necro_shield;
	public static Item shieldWood;
	public static Item shieldIron;
	public static Item shieldGold;
	public static Item shieldDiamond;
	public static Item cloak;
	public static Item cloakI;
	public static Item cloakSub;
	public static Item cloakRed;
	public static Item cloakYellow;
	public static Item cloakGreen;
	public static Item cloakBlue;
	public static Item elfbow;
	public static Item claymore;
	public static Item hammer;
	public static Item wand;
	public static Item staf;
	public static Item rageSeed;
	public static Item magehood;
	public static Item magegown;
	public static Item magepants;
	public static Item mageboots;
	public static Item archerhood;
	public static Item archerchest;
	public static Item archerpants;
	public static Item archerboots;
	public static Item berserkerHood;
	public static Item berserkerChest;
	public static Item berserkerLegs;
	public static Item berserkerBoots;
	public static Item beastHood;
	public static Item beastChest;
	public static Item beastLegs;
	public static Item beastBoots;
	public static Item rogueHood;
	public static Item rogueChest;
	public static Item rogueLegs;
	public static Item rogueBoots;
	public static Item necroHood;
	public static Item necroChestplate;
	public static Item necroLeggings;
	public static Item necroBoots;
	public static Item palaHelm;
	public static Item palaChest;
	public static Item palaLeggings;
	public static Item palaBoots;
	public static Item pala_weapon;
	public static Item animalskin;
	public static Item tanHide;
	public static Item magecloth;
	public static Item wizardBook;
	public static Item colmold;
	public static Item ringmold;
	public static Item wantmold;
	public static Item necro_weapon;
	public static Item necro_skin;
	public static Item pala_steel;
	public static Item daggers;
	public static Item crystal;
	public static Item rogueLeather;
	public static Item beastLeather;
	public static Item whistle;
	public static Item fireStaff;
	public static Item frostStaff;
	public static Item archBook;
	public static Item beastShield;
	public static Item earthStaff;
	public static Item windStaff;
	public static Item ultimateStaff;
	public static Item beastAxe;
	public static Item archMageLeather;
	public static Item archmageHood;
	public static Item archmageChest;
	public static Item archmageLegs;
	public static Item archMageBoots;
	public static Item petCandy;
	public static Item tangledBrench;
	public static Item PetXPBottle;
	public static Block forgeBlock;
	//Die bitches.
	public static List<String> developers = new ArrayList<String>();
	public static List<Integer> rpvInvIDs = new ArrayList();
	private String[][] recipePatterns;
	private Object[][] recipeItems;
	public static boolean hasRpg;
	public static boolean hasShields;
	public static boolean hasRogue;
	public static boolean hasMage;
	private static int uniqueID = 0;
	public static Potion decomposePotion;
	public static Potion masochismPotion;

	public mod_RpgInventory() {
		instance = this;
	}
	public int getUniqueID() {
		return uniqueID++;
	}
	EnumArmorMaterial robes = EnumHelper.addArmorMaterial("magerobes", 20, new int[]{2, 2, 2, 1}, 5);
	EnumArmorMaterial hides = EnumHelper.addArmorMaterial("hides", 20, new int[]{2, 3, 2, 2}, 5);
	EnumArmorMaterial archMage = EnumHelper.addArmorMaterial("archmagerobes", 20, new int[]{4, 4, 4, 2}, 5);
	EnumArmorMaterial armoury = EnumHelper.addArmorMaterial("armoury", 20, new int[]{2, 4, 3, 2}, 5);
	EnumArmorMaterial rogueArmor = EnumHelper.addArmorMaterial("rogue", 20, new int[]{3, 5, 4, 3}, 5);
	EnumArmorMaterial beastMaster = EnumHelper.addArmorMaterial("beast", 20, new int[]{4, 5, 4, 3}, 5);
	EnumToolMaterial clay = EnumHelper.addToolMaterial("claymore", 0, 750, 5F, 6, 0);
	EnumToolMaterial stone = EnumHelper.addToolMaterial("RageBreaker", 0, 1024, 5F, 4, 0);
	public static EnumToolMaterial BeastAxeMaterial = EnumHelper.addToolMaterial("BeastAxe", 4, 1280, 6.0F, 3, 22);
	public EnumToolMaterial NecroToolMaterial;
	public EnumToolMaterial PalaToolMaterial;
	public static CreativeTabs tab;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		RpgConfig.instance.loadConfig(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// NOTHING BEFORE THE GOD DAMN TAB !
		tab = new RpgInventoryTab(CreativeTabs.getNextID(), "RpgTab");


		try {

			Class.forName("rpgNecroPaladin.mod_RpgPlus");
			FMLLog.info("Rpg++ Necromancer and Paladin is installed. Renderers can be Used", 1);
			hasRpg = true;
		} catch (Throwable e) {
			FMLLog.info("Rpg++ Necromancer and Paladin has not been detected. Renderers for Rpg++ Excluded");
			hasRpg = false;
		}
		//look for Vanilla Shields
		try {
			Class.forName("rpgVanillaShields.mod_VanillaShields");
			FMLLog.info("Rpg++ Vanilla Shields is installed. Renderers can be Used");
			hasShields = true;
		} catch (Throwable e) {
			FMLLog.info("Rpg++ Vanilla Shields has not been detected. Renderers for Vanilla Shields Excluded");
			hasShields = false;
		}
		try {
			Class.forName("rpgRogueBeast.mod_RpgRB");
			FMLLog.info("Rpg++ Rogue and BeastMaster Installed. Renderers can be Used");
			hasRogue = true;
		} catch (Throwable e) {
			FMLLog.info("Rpg++ Rogue and BeastMaster not detected. Renderers for Vanilla Shields Excluded");
			hasRogue = false;
		}
		try {
			Class.forName("rpgMage.mod_RpgMageSet");
			FMLLog.info("Rpg++ ArchMage Installed. Renderers can be Used");
			hasMage = true;
		} catch (Throwable e) {
			FMLLog.info("Rpg++ ArchMage not detected. Renderers Excluded");
			hasMage = false;
		}

		developers.add("unjustice");
		developers.add("artix_all_mighty");
		developers.add("rich1051414");

		forgeBlock = new BlockForge(RpgConfig.instance.forgeblockID, Material.rock).setHardness(5f).setUnlocalizedName("MoldForge").setCreativeTab(tab);

		neckgold = new ItemRpgArmor(RpgConfig.instance.neckgoldID, 0, -1, "").setUnlocalizedName("neckGold").setCreativeTab(tab);
		neckdia = new ItemRpgArmor(RpgConfig.instance.neckdiaID, 0, -1, "").setUnlocalizedName("neckDia").setCreativeTab(tab);
		neckem = new ItemRpgArmor(RpgConfig.instance.neckemID, 0, -1, "").setUnlocalizedName("neckEm").setCreativeTab(tab);
		necklap = new ItemRpgArmor(RpgConfig.instance.necklapID, 0, -1, "").setUnlocalizedName("neckLap").setCreativeTab(tab);

		ringgold = new ItemRpgArmor(RpgConfig.instance.ringgoldID, 4, -1, "").setUnlocalizedName("ringGold").setCreativeTab(tab);
		ringdia = new ItemRpgArmor(RpgConfig.instance.ringdiaID, 4, -1, "").setUnlocalizedName("ringDia").setCreativeTab(tab);
		ringem = new ItemRpgArmor(RpgConfig.instance.ringemID, 4, -1, "").setUnlocalizedName("ringEm").setCreativeTab(tab);
		ringlap = new ItemRpgArmor(RpgConfig.instance.ringlapID, 4, -1, "").setUnlocalizedName("ringLap").setCreativeTab(tab);

		glovesbutter = new ItemRpgArmor(RpgConfig.instance.glovesbutterID, 3, -1, "").setUnlocalizedName("gloveGold").setCreativeTab(tab);
		glovesdia = new ItemRpgArmor(RpgConfig.instance.glovesdiaID, 3, -1, "").setUnlocalizedName("gloveDia").setCreativeTab(tab);
		glovesem = new ItemRpgArmor(RpgConfig.instance.glovesemID, 3, -1, "").setUnlocalizedName("gloveEm").setCreativeTab(tab);
		gloveslap = new ItemRpgArmor(RpgConfig.instance.gloveslapID, 3, -1, "").setUnlocalizedName("gloveEm").setCreativeTab(tab);

		archersShield = new ItemRpgArmor(RpgConfig.instance.archersShieldID, 1, 200, "").setUnlocalizedName("shieldArcher").setCreativeTab(tab);
		berserkerShield = new ItemRpgArmor(RpgConfig.instance.berserkerShieldID, 1, 350, "").setUnlocalizedName("shieldBerserker").setCreativeTab(tab);
		talisman = new ItemRpgArmor(RpgConfig.instance.talismanID, 1, 200, "").setUnlocalizedName("shieldMage").setCreativeTab(tab);

		cloak = new ItemRpgArmor(RpgConfig.instance.cloakID, 2, -1, "").setFull3D().setUnlocalizedName("capeGrey").setCreativeTab(tab);
		cloakI = new ItemRpgArmor(RpgConfig.instance.cloakIID, 2, -1, "").setFull3D().setUnlocalizedName("i.capeGrey").setCreativeTab(tab);

		magehood = new BonusArmor(RpgConfig.instance.magehoodID, robes, 4, 0).setUnlocalizedName("mage1").setCreativeTab(tab);
		magegown = new BonusArmor(RpgConfig.instance.magegownID, robes, 4, 1).setUnlocalizedName("mage2").setCreativeTab(tab);
		magepants = new BonusArmor(RpgConfig.instance.magepantsID, robes, 4, 2).setUnlocalizedName("mage3").setCreativeTab(tab);
		mageboots = new BonusArmor(RpgConfig.instance.magebootsID, robes, 4, 3).setUnlocalizedName("mage4").setCreativeTab(tab);

		archerhood = new BonusArmor(RpgConfig.instance.archerhoodID, hides, 4, 0).setUnlocalizedName("archer1").setCreativeTab(tab);
		archerchest = new BonusArmor(RpgConfig.instance.archerchestID, hides, 4, 1).setUnlocalizedName("archer2").setCreativeTab(tab);
		archerpants = new BonusArmor(RpgConfig.instance.archerpantsID, hides, 4, 2).setUnlocalizedName("archer3").setCreativeTab(tab);
		archerboots = new BonusArmor(RpgConfig.instance.archerbootsID, hides, 4, 3).setUnlocalizedName("archer4").setCreativeTab(tab);

		berserkerHood = new BonusArmor(RpgConfig.instance.berserkerHoodID, armoury, 4, 0).setUnlocalizedName("berserk1").setCreativeTab(tab);
		berserkerChest = new BonusArmor(RpgConfig.instance.berserkerChestID, armoury, 4, 1).setUnlocalizedName("berserk2").setCreativeTab(tab);
		berserkerLegs = new BonusArmor(RpgConfig.instance.berserkerLegsID, armoury, 4, 2).setUnlocalizedName("berserk3").setCreativeTab(tab);
		berserkerBoots = new BonusArmor(RpgConfig.instance.berserkerBootsID, armoury, 4, 3).setUnlocalizedName("berserk4").setCreativeTab(tab);

		claymore = new ItemClaymore(RpgConfig.instance.claymoreID, clay).setFull3D().setMaxDamage(1024).setUnlocalizedName("claymore").setCreativeTab(tab);
		wand = new ItemMageSphere(RpgConfig.instance.wandID).setFull3D().setMaxDamage(400).setUnlocalizedName("soulsphere").setCreativeTab(tab);
		elfbow = new ItemArcherBow(RpgConfig.instance.elfbowID).setFull3D().setMaxDamage(350).setUnlocalizedName("elmBow").setCreativeTab(tab);

		animalskin = new ItemRpg(RpgConfig.instance.animalskinID).setUnlocalizedName("a.leather").setCreativeTab(tab);
		tanHide = new ItemRpg(RpgConfig.instance.tanHideID).setUnlocalizedName("t.leather").setCreativeTab(tab);
		magecloth = new ItemRpg(RpgConfig.instance.mageclothID).setUnlocalizedName("m.leather").setCreativeTab(tab);

		wizardBook = new ItemRpg(RpgConfig.instance.wizardBookID).setUnlocalizedName("a.book_normal").setCreativeTab(tab);

		hammer = new ItemHammer(RpgConfig.instance.hammerID, stone).setMaxDamage(750).setUnlocalizedName("rageBreaker").setCreativeTab(tab);
		staf = new ItemStaf(RpgConfig.instance.stafID).setMaxStackSize(1).setMaxDamage(1500).setUnlocalizedName("lunarStaff").setCreativeTab(tab);

		rageSeed = new ItemRageFood(RpgConfig.instance.rageSeedID, 0, 0f, false).setAlwaysEdible().setUnlocalizedName("r.seeds").setMaxStackSize(8).setCreativeTab(tab);

		cloakRed = new ItemRpgArmor(RpgConfig.instance.cloakRedID, 2, -1, "").setFull3D().setUnlocalizedName("r.capeGrey").setCreativeTab(tab);
		cloakYellow = new ItemRpgArmor(RpgConfig.instance.cloakYellowID, 2, -1, "").setFull3D().setUnlocalizedName("y.capeGrey").setCreativeTab(tab);
		cloakGreen = new ItemRpgArmor(RpgConfig.instance.cloakGreenID, 2, -1, "").setFull3D().setUnlocalizedName("g.capeGrey").setCreativeTab(tab);
		cloakBlue = new ItemRpgArmor(RpgConfig.instance.cloakBlueID, 2, -1, "").setFull3D().setUnlocalizedName("b.capeGrey").setCreativeTab(tab);
		cloakSub = new ItemRpgArmor(RpgConfig.instance.cloakSubID, 2, -1, "").setFull3D().setUnlocalizedName("s.capeGrey").setCreativeTab(tab);

		colmold = new ItemMold(RpgConfig.instance.colmoldID).setUnlocalizedName("moldNeck").setCreativeTab(tab);
		ringmold = new ItemMold(RpgConfig.instance.ringmoldID).setUnlocalizedName("moldRing").setCreativeTab(tab);
		wantmold = new ItemMold(RpgConfig.instance.wantmoldID).setUnlocalizedName("moldGlove").setCreativeTab(tab);

		if (hasRpg == true) {
			EnumArmorMaterial necroArmor = EnumHelper.addArmorMaterial("necro_material", 20, new int[]{2, 5, 1, 1}, 5);	// use of Souls
			EnumArmorMaterial paladin = EnumHelper.addArmorMaterial("pala_material", 20, new int[]{4, 7, 2, 1}, 5);	// use of Steel
			NecroToolMaterial = EnumHelper.addToolMaterial("souls", 0, 1024, 5F, 1, 0);
			PalaToolMaterial = EnumHelper.addToolMaterial("steel", 0, 1024, 5F, 1, 0);

			necroHood = new BonusArmor(RpgConfig.instance.necroHoodID, necroArmor, 4, 0).setUnlocalizedName("necro1");
			necroChestplate = new BonusArmor(RpgConfig.instance.necroChestplateID, necroArmor, 4, 1).setUnlocalizedName("necro2");
			necroLeggings = new BonusArmor(RpgConfig.instance.necroLeggingsID, necroArmor, 4, 2).setUnlocalizedName("necro3");
			necroBoots = new BonusArmor(RpgConfig.instance.necroBootsID, necroArmor, 4, 3).setUnlocalizedName("necro4");

			palaHelm = new BonusArmor(RpgConfig.instance.palaHelmID, paladin, 4, 0).setUnlocalizedName("paladin1");
			palaChest = new BonusArmor(RpgConfig.instance.palaChestID, paladin, 4, 1).setUnlocalizedName("paladin2");
			palaLeggings = new BonusArmor(RpgConfig.instance.palaLeggingsID, paladin, 4, 2).setUnlocalizedName("paladin3");
			palaBoots = new BonusArmor(RpgConfig.instance.palaBootsID, paladin, 4, 3).setUnlocalizedName("paladin4");

			necro_shield = new ItemRpgPlusPlusArmor(RpgConfig.instance.necro_shieldID, 1, 250, "necro").setUnlocalizedName("shieldNecro");
			necro_weapon = new ItemNecroSkull(RpgConfig.instance.necro_weaponID, NecroToolMaterial).setFull3D().setUnlocalizedName("Skull");

			pala_shield = new ItemRpgPlusPlusArmor(RpgConfig.instance.pala_shieldID, 1, 450, "pala").setUnlocalizedName("shieldPaladin");
			pala_weapon = new ItemGrandSword(RpgConfig.instance.pala_weaponID, PalaToolMaterial).setFull3D().setUnlocalizedName("paladinPride");

			necro_skin = new ItemMats(RpgConfig.instance.necro_skinID).setUnlocalizedName("n.leather");
			pala_steel = new ItemMats(RpgConfig.instance.pala_steelID).setUnlocalizedName("p.leather");

			LanguageRegistry.addName(necro_shield, "NecroMancer Shield");
			LanguageRegistry.addName(pala_shield, "Paladin Shield");
			LanguageRegistry.addName(necroHood, "NecroMancer Hood");
			LanguageRegistry.addName(necroChestplate, "NecroMancer Vest");
			LanguageRegistry.addName(necroLeggings, "NecroMancer Pants");
			LanguageRegistry.addName(necroBoots, "NecroMancer Boots");
			LanguageRegistry.addName(palaHelm, "Paladin Helmet");
			LanguageRegistry.addName(palaChest, "Paladin Chestplate");
			LanguageRegistry.addName(palaLeggings, "Paladin Leggings");
			LanguageRegistry.addName(palaBoots, "Paladin Boots");
			LanguageRegistry.addName(necro_weapon, "NecroMancer Skull");
			LanguageRegistry.addName(pala_weapon, "Paladin's Pride");
			LanguageRegistry.addName(pala_steel, "Paladin's Steel");
			LanguageRegistry.addName(necro_skin, "Necromancer's Cloth");

			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.necro_skin, 1), new Object[]{"BWB", "WLW", "BWB", 'W', Item.spiderEye, 'B', Item.bone, 'L', Item.leather});
			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.pala_steel, 1), new Object[]{"GGG", "BIB", "GGG", 'G', Item.ingotGold, 'B', (new ItemStack(Item.potion.itemID, 1, 0)), 'I', Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.necro_shield, 1), new Object[]{"WWW", "WBW", " W ", 'W', mod_RpgInventory.necro_skin, 'B', new ItemStack(Item.skull,1,1)});
			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.pala_shield, 1), new Object[]{"WWW", "WBW", " W ", 'W', mod_RpgInventory.pala_steel, 'B', Block.blockIron});
			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.necro_weapon, 1), new Object[]{"WWW", "WBW", "WWW", 'W', Item.bone, 'B', new ItemStack(Item.skull,1,1)});
			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.pala_weapon, 1), new Object[]{"S", "S", "G", 'S', mod_RpgInventory.pala_steel, 'G', Item.ingotGold});

		}

		if (hasShields == true) {
			shieldWood = new ItemRpgArmor(RpgConfig.instance.shieldWoodID, 1, 50, "wood").setUnlocalizedName("shieldWood");
			shieldIron = new ItemRpgArmor(RpgConfig.instance.shieldIronID, 1, 125, "iron").setUnlocalizedName("shieldIron");
			shieldGold = new ItemRpgArmor(RpgConfig.instance.shieldGoldID, 1, 250, "gold").setUnlocalizedName("shieldGold");
			shieldDiamond = new ItemRpgArmor(RpgConfig.instance.shieldDiamondID, 1, 500, "diamond").setUnlocalizedName("shieldDiamond");

			LanguageRegistry.addName(shieldWood, "Wooden Shield");
			LanguageRegistry.addName(shieldIron, "Iron Shield");
			LanguageRegistry.addName(shieldGold, "Golden Shield");
			LanguageRegistry.addName(shieldDiamond, "Diamond Shield");
		}
		if (hasRogue == true) {

			daggers = new ItemRpgArmor(RpgConfig.instance.daggersID, 1, 800, "").setUnlocalizedName("dagger");
			beastAxe = new ItemBeastAxe(RpgConfig.instance.beastAxe).setFull3D().setUnlocalizedName("forestAxe");

			rogueLeather = new ItemRBMats(RpgConfig.instance.rogueLeatherID).setUnlocalizedName("r.leather");
			beastLeather = new ItemRBMats(RpgConfig.instance.beastLeatherID).setUnlocalizedName("b.leather");
			beastShield = new ItemRpgArmor(RpgConfig.instance.beastShield, 1, 150, "").setUnlocalizedName("shieldBeastMaster");

			rogueHood = new BonusArmor(RpgConfig.instance.rogueHoodID, rogueArmor, 4, 0).setUnlocalizedName("rogue1");
			rogueChest = new BonusArmor(RpgConfig.instance.rogueChestID, rogueArmor, 4, 1).setUnlocalizedName("rogue2");
			rogueLegs = new BonusArmor(RpgConfig.instance.rogueLegsID, rogueArmor, 4, 2).setUnlocalizedName("rogue3");
			rogueBoots = new BonusArmor(RpgConfig.instance.rogueBootsID, rogueArmor, 4, 3).setUnlocalizedName("rogue4");

			beastHood = new BonusArmor(RpgConfig.instance.beastHoodID, beastMaster, 4, 0).setUnlocalizedName("beast1");
			beastChest = new BonusArmor(RpgConfig.instance.beastChestID, beastMaster, 4, 1).setUnlocalizedName("beast2");
			beastLegs = new BonusArmor(RpgConfig.instance.beastLegsID, beastMaster, 4, 2).setUnlocalizedName("beast3");
			beastBoots = new BonusArmor(RpgConfig.instance.beastBootsID, beastMaster, 4, 3).setUnlocalizedName("beast4");

			whistle = new ItemRBMats2(RpgConfig.instance.whistleID).setUnlocalizedName("whistle");

			petCandy = new ItemCandy(RpgConfig.instance.candy).setUnlocalizedName("petCandy");
			tangledBrench = new ItemCandy(RpgConfig.instance.brench).setUnlocalizedName("tangledBrench");
			PetXPBottle = new PetExpPotion(RpgConfig.instance.petxppotion).setUnlocalizedName("PetXPBottle");

			crystal = new ItemCrystal(RpgConfig.instance.crystalID, ITEMTYPE.CRYSTAL, -1, "").setUnlocalizedName("petCrystal");

			LanguageRegistry.addName(daggers, "Rogue Daggers");
			LanguageRegistry.addName(rogueLeather, "Rogue Leather");
			LanguageRegistry.addName(beastLeather, "BeastMaster Leather");
			LanguageRegistry.addName(rogueHood, "Rogue Hood");
			LanguageRegistry.addName(rogueChest, "Rogue Breast Plate");
			LanguageRegistry.addName(rogueLegs, "Rogue Chaps");
			LanguageRegistry.addName(rogueBoots, "Rogue Boots");
			LanguageRegistry.addName(beastHood, "BeastMaster Hood");
			LanguageRegistry.addName(beastChest, "BeastMaster Body Protection");
			LanguageRegistry.addName(beastLegs, "BeastMaster Leg Protection");
			LanguageRegistry.addName(beastBoots, "BeastMaster Shoes");
			LanguageRegistry.addName(whistle, "Pet Whistle");
			LanguageRegistry.addName(beastShield, "BeastMaster Shield");
			LanguageRegistry.addName(beastAxe, "BeastMaster Forest Axe");
			LanguageRegistry.addName(petCandy, "Rare Pet Candy");
			LanguageRegistry.addName(tangledBrench, "Tangled Brench");
			LanguageRegistry.addName(PetXPBottle, "Bottle 'O Pet");

			LanguageRegistry.addName(new ItemStack(crystal, 1, 0), "Pet Crystal");
			LanguageRegistry.addName(new ItemStack(crystal, 1, 1), "Boar");
			LanguageRegistry.addName(new ItemStack(crystal, 1, 2), "Spider");
			LanguageRegistry.addName(new ItemStack(crystal, 1, 3), "Bull");

			GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.daggers,1), new Object [] {" ei","eie","se ", 'i', Item.ingotIron, 'e',Item.spiderEye, 's',Item.stick});
			GameRegistry.addShapelessRecipe(new ItemStack(whistle), new Object[]{Item.stick, Item.reed, Item.reed});
			GameRegistry.addRecipe(new ItemStack(beastLeather), new Object[]{"LLL", "LVL", "LLL", 'L', Block.leaves, 'V', Item.leather});
			GameRegistry.addRecipe(new ItemStack(rogueLeather), new Object[]{"DSD", "SLS", "DSD", 'S', Item.silk, 'L', Item.leather, 'D', new ItemStack(Item.dyePowder, 1, 5)});
			GameRegistry.addRecipe(new ItemStack(beastShield), new Object[]{"III", "IDI", " I ", 'I', beastLeather, 'D', Block.wood});
			GameRegistry.addRecipe(new ItemStack(beastAxe), new Object[]{" IW", " SI", "S  ", 'S', tangledBrench, 'I', Block.blockIron, 'W', Block.wood});
			GameRegistry.addShapelessRecipe(new ItemStack(tangledBrench), new Object[]{Item.stick, Item.stick, Item.silk, Item.silk, Item.silk, Item.silk});

			recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
			recipeItems = new Object[][]{{rogueLeather, beastLeather}, {rogueHood, beastHood},
					{rogueChest, beastChest}, {rogueLegs, beastLegs}, {rogueBoots, beastBoots}};

			for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
				Object var3 = this.recipeItems[0][var2];

				for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
					Item var5 = (Item) this.recipeItems[var4 + 1][var2];
					GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
				}
			}
			addChestLoot(new ItemStack(mod_RpgInventory.PetXPBottle), 1, 1, 40, "Pet Drinks");
			addCandyChestLoot(new ItemStack(mod_RpgInventory.petCandy), 1, 6, 20, "Easter Egg");
		}
		if (hasMage == true) {
			fireStaff = new ItemElementalStaff(RpgConfig.instance.fireStaff, 1, 150).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffFire");
			frostStaff = new ItemElementalStaff(RpgConfig.instance.frostStaff, 2, 150).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffIce");
			earthStaff = new ItemElementalStaff(RpgConfig.instance.staffEarth, 3, 150).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffEarth");
			windStaff = new ItemElementalStaff(RpgConfig.instance.staffWind, 4, 500).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffWind");
			ultimateStaff = new ItemElementalStaff(RpgConfig.instance.staffUltimate, 5, 300).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffElemental");
			archBook = new ItemRpgArmor(RpgConfig.instance.archBook, 1, 300, "").setUnlocalizedName("archTome");

			archmageHood = new BonusArmor(RpgConfig.instance.archmageHood, archMage, 4, 0).setUnlocalizedName("archMage1");
			archmageChest = new BonusArmor(RpgConfig.instance.archmageChest, archMage, 4, 1).setUnlocalizedName("archMage2");
			archmageLegs = new BonusArmor(RpgConfig.instance.archmageLegs, archMage, 4, 2).setUnlocalizedName("archMage3");
			archMageBoots = new BonusArmor(RpgConfig.instance.archmageBoots, archMage, 4, 3).setUnlocalizedName("archMage4");

			LanguageRegistry.addName(fireStaff, "Fire Staff");
			LanguageRegistry.addName(frostStaff, "Frost Staff");
			LanguageRegistry.addName(earthStaff, "Earth Staff");
			LanguageRegistry.addName(windStaff, "Wind Staff");
			LanguageRegistry.addName(ultimateStaff, "Ultimate Staff");
			LanguageRegistry.addName(archBook, "ArchMage Book");
			LanguageRegistry.addName(archmageHood, "Arch Mage Hat");
			LanguageRegistry.addName(archmageChest, "Arch Mage Gown");
			LanguageRegistry.addName(archmageLegs, "Arch Mage Leggings");
			LanguageRegistry.addName(archMageBoots, "Arch Mage Boots");

			GameRegistry.addShapelessRecipe(new ItemStack(ultimateStaff), new Object[]{windStaff, earthStaff, frostStaff, fireStaff, Item.netherStar});
			GameRegistry.addRecipe(new ItemStack(windStaff), new Object[]{"III", "DSD", "III", 'I', Item.feather, 'D', Item.diamond, 'S', staf});
			GameRegistry.addRecipe(new ItemStack(frostStaff), new Object[]{"III", "DSD", "III", 'I', Block.ice, 'D', Item.diamond, 'S', staf});
			GameRegistry.addRecipe(new ItemStack(earthStaff), new Object[]{"III", "DSD", "III", 'I', Block.grass, 'D', Item.diamond, 'S', staf});
			GameRegistry.addRecipe(new ItemStack(fireStaff), new Object[]{"III", "DSD", "III", 'I', Item.blazePowder, 'D', Item.diamond, 'S', staf});
			GameRegistry.addRecipe(new ItemStack(archBook), new Object[]{"III", "IBI", " I ", 'I', new ItemStack(Item.dyePowder, 1, 4), 'B', Item.book});
			GameRegistry.addRecipe(new ItemStack(archMageBoots), new Object[]{"III", "IBI", "III", 'B', mageboots, 'I', Item.goldNugget});
			GameRegistry.addRecipe(new ItemStack(archmageLegs), new Object[]{"III", "IBI", "III", 'B', magepants, 'I', Item.goldNugget});
			GameRegistry.addRecipe(new ItemStack(archmageChest), new Object[]{"III", "IBI", "III", 'B', magegown, 'I', Item.goldNugget});
			GameRegistry.addRecipe(new ItemStack(archmageHood), new Object[]{"III", "IBI", "III", 'B', magehood, 'I', Item.goldNugget});


		}

		proxy.registerRenderInformation();

		addChestLoot(new ItemStack(mod_RpgInventory.colmold), 1, 1, 40, "Necklace Mold");
		addChestLoot(new ItemStack(mod_RpgInventory.ringmold), 1, 1, 30, "Ring Mold");
		addChestLoot(new ItemStack(mod_RpgInventory.wantmold), 1, 1, 40, "Gloves Mold");

		GameRegistry.registerTileEntity(TEMold.class, "temold");

		LanguageRegistry.addName(forgeBlock, "Mold Forge");
		GameRegistry.registerBlock(forgeBlock, "MoldForge");

		LanguageRegistry.addName(cloakRed, "Red Cape");
		LanguageRegistry.addName(cloakYellow, "Yellow Cape");
		LanguageRegistry.addName(cloakGreen, "Green Cape");
		LanguageRegistry.addName(cloakBlue, "Blue Cape");
		LanguageRegistry.addName(cloakSub, "Black Cape");

		LanguageRegistry.addName(neckgold, "Gold Necklace");
		LanguageRegistry.addName(neckdia, "Health Necklace");
		LanguageRegistry.addName(neckem, " Buffed Necklace");
		LanguageRegistry.addName(necklap, " Strength Necklace");

		LanguageRegistry.addName(glovesbutter, "Gold Gloves");
		LanguageRegistry.addName(glovesdia, "Health Gloves");
		LanguageRegistry.addName(glovesem, "Buffed Gloves");
		LanguageRegistry.addName(gloveslap, "Strength Gloves");

		LanguageRegistry.addName(ringgold, "Golden Ring");
		LanguageRegistry.addName(ringdia, "Health Ring");
		LanguageRegistry.addName(ringem, "Buffed Ring");
		LanguageRegistry.addName(ringlap, "Strength Ring");

		LanguageRegistry.addName(archersShield, "Small Archer Shield");
		LanguageRegistry.addName(berserkerShield, "Berserker's Iron Thorn");
		LanguageRegistry.addName(talisman, "Wizard's Talisman");

		LanguageRegistry.addName(cloak, "Cloak");
		LanguageRegistry.addName(cloakI, "Invisibility Cloak");

		LanguageRegistry.addName(magehood, "Mage Hood");
		LanguageRegistry.addName(magegown, "Mage Gown");
		LanguageRegistry.addName(magepants, "Mage Under Gown");
		LanguageRegistry.addName(mageboots, "Mage Shoes");

		LanguageRegistry.addName(archerhood, "Archer Hood");
		LanguageRegistry.addName(archerchest, "Archer Chest");
		LanguageRegistry.addName(archerpants, "Archer Leggings");
		LanguageRegistry.addName(archerboots, "Archer Boots");

		LanguageRegistry.addName(berserkerHood, "Berserker's Head Protection");
		LanguageRegistry.addName(berserkerChest, "Berserker's Body Protection");
		LanguageRegistry.addName(berserkerLegs, "Berserker's Leg Protection");
		LanguageRegistry.addName(berserkerBoots, "Berserker's Feet Protection");

		LanguageRegistry.addName(wand, "Soul Sphere");
		LanguageRegistry.addName(claymore, "Berserker's Claymore");
		LanguageRegistry.addName(elfbow, "Archer's Bow of Birch");

		LanguageRegistry.addName(animalskin, "Animal Skin");
		LanguageRegistry.addName(tanHide, "Tanned Hide");
		LanguageRegistry.addName(magecloth, "Mage Cloth");

		LanguageRegistry.addName(wizardBook, "Wizard's Knowledge, Volume I");
		LanguageRegistry.addName(hammer, "Berserker's Rage Breaker");
		LanguageRegistry.addName(staf, "Lunar Staff");
		LanguageRegistry.addName(rageSeed, "Rage Seeds");

		LanguageRegistry.addName(colmold, "Necklace Mold");
		LanguageRegistry.addName(ringmold, "Ring Mold");
		LanguageRegistry.addName(wantmold, "Glove Mold");


		MinecraftForge.addGrassSeed(new ItemStack(rageSeed, 1), 1);

		//SKINS
		GameRegistry.addRecipe(new ItemStack(animalskin, 1), new Object[]{"WWW", "WLW", "WWW", 'W', new ItemStack(Block.cloth, 1, 12), 'L', Item.leather});
		GameRegistry.addShapelessRecipe(new ItemStack(tanHide, 1), new Object[]{Item.leather, Item.silk, Item.silk, Item.silk, Item.silk});
		GameRegistry.addRecipe(new ItemStack(magecloth, 1), new Object[]{"WWW", "WLW", "WWW", 'W', new ItemStack(Item.dyePowder, 1, 4), 'L', Item.leather});
		//WEAPONRY
		GameRegistry.addRecipe(new ItemStack(elfbow, 1), new Object[]{"EPP", "P S", "PS ", 'E', Item.emerald, 'S', Item.silk, 'P', new ItemStack(Block.wood, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(wand, 1), new Object[]{"GGG", "GLG", "GGG", 'L', Block.blockLapis, 'G', Item.ingotGold});
		GameRegistry.addRecipe(new ItemStack(staf, 1), new Object[]{" ML", " SM", "S  ", 'M', Item.speckledMelon, 'S', Item.stick, 'L', new ItemStack(Item.dyePowder, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(claymore, 1), new Object[]{" S ", " S ", "LIL", 'I', Item.ingotIron, 'S', Block.stone, 'L', Item.leather});
		GameRegistry.addRecipe(new ItemStack(hammer, 1), new Object[]{"SSS", "LI ", "LI ", 'I', Item.ingotIron, 'S', Block.blockIron, 'L', Item.leather});

		//SHIELDS
		GameRegistry.addRecipe(new ItemStack(archersShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.wood});
		GameRegistry.addRecipe(new ItemStack(berserkerShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.blockIron});
		GameRegistry.addRecipe(new ItemStack(talisman, 1), new Object[]{"WWW", "WBW", " W ", 'W', new ItemStack(Item.dyePowder, 1, 4), 'B', Block.blockLapis});

		//CLOAK
		GameRegistry.addRecipe(new ItemStack(cloak, 1), new Object[]{"SS", "II", "II", 'I', Block.cloth, 'S', Item.silk});
		GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.potion, 1, 8206)});
		GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.potion, 1, 8270)});

		GameRegistry.addRecipe(new ItemStack(cloakRed, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(cloakYellow, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 11)});
		GameRegistry.addRecipe(new ItemStack(cloakGreen, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(cloakBlue, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 12)});
		GameRegistry.addRecipe(new ItemStack(cloakSub, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 0)});

		GameRegistry.addRecipe(new ItemStack(forgeBlock, 1), new Object[]{"BBB", "BOB", "BBB", 'B', Block.brick, 'O', Block.obsidian});

		//MageBook
		GameRegistry.addShapelessRecipe(new ItemStack(wizardBook, 1), new Object[]{magecloth, Item.paper, Item.paper, Item.paper});

		//ARMOR
		recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
		recipeItems = new Object[][]{{animalskin, tanHide, magecloth}, {berserkerHood, archerhood, magehood},
				{berserkerChest, archerchest, magegown}, {berserkerLegs, archerpants, magepants},
				{berserkerBoots, archerboots, mageboots}};

		for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
			}
		}
		if (hasRpg == true) {
			recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
			recipeItems = new Object[][]{{pala_steel, necro_skin}, {palaHelm, necroHood},
					{palaChest, necroChestplate}, {palaLeggings, necroLeggings},
					{palaBoots, necroBoots}};

			for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
				Object var3 = this.recipeItems[0][var2];

				for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
					Item var5 = (Item) this.recipeItems[var4 + 1][var2];
					GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
				}
			}
		}

		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new RPGEventHooks());
		EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow", getUniqueID(), this, 250, 1, true);


		//hack to increase the number of potion types allowed

		if (Potion.potionTypes.length < 256) {
			boolean found = false;
			Field fallbackfield = null;
			Potion[] potionTypes = null;
			for (Field f : Potion.class.getDeclaredFields()) {
				try {
					if (fallbackfield != null && f.getType() == Potion[].class) {
						fallbackfield = f;
					}
					if (f.getName().equals("potionTypes") || f.getName().equals("a") || f.getName().equals("field_76425_a")) {
						found = true;
						Field modfield = Field.class.getDeclaredField("modifiers");
						modfield.setAccessible(true);
						modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

						potionTypes = (Potion[]) f.get(null);
						final Potion[] newPotionTypes = new Potion[256];
						System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
						f.set(null, newPotionTypes);
						break;
					}
				} catch (Exception e) {
					System.err.println("Severe error, please report this to the mod author:");
					System.err.println(e);
				}
			}
			try {
				if (fallbackfield != null && !found) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(fallbackfield, fallbackfield.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[]) fallbackfield.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					fallbackfield.set(null, newPotionTypes);
				}
			} catch (Exception ex) {
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(ex);
			}
		}

		if(hasRpg == true)
		{
			for (int pos = 32; pos < Potion.potionTypes.length; pos++) {
				if (Potion.potionTypes[pos] == null) {
					if (decomposePotion == null) {
						decomposePotion = new DecomposePotion(pos);
						Potion.potionTypes[pos] = decomposePotion;
					} else if (masochismPotion == null) {
						masochismPotion = new MasochismPotion(pos);
						Potion.potionTypes[pos] = masochismPotion;
					} else {
						break;
					}
				}
			}
			RPGEventHooks.negativeEffects.add(2);
			RPGEventHooks.negativeEffects.add(4);
			RPGEventHooks.negativeEffects.add(9);
			RPGEventHooks.negativeEffects.add(15);
			RPGEventHooks.negativeEffects.add(17);
			RPGEventHooks.negativeEffects.add(18);
			RPGEventHooks.negativeEffects.add(19);
			RPGEventHooks.negativeEffects.add(20);
			RPGEventHooks.negativeEffects.add(decomposePotion.id);
		}


	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		proxy.registerLate();
		//All mods should be initialized now, check what potion effects are installed
		//and list the bad ones for later reference.
	}

	public static class ITEMTYPE {

		public static final int NECKLACE = 0;
		public static final int SHIELD = 1;
		public static final int CLOAK = 2;
		public static final int GLOVES = 3;
		public static final int RING = 4;
		public static final int CRYSTAL = 5;
	}

	public void addChestLoot(ItemStack is, int min, int max, int rarity, String item) {
		FMLLog.info("Adding to chests: " + item, min);
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("villageBlacksmith").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidJungleChest").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidDesertyChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);

	}

	public void addCandyChestLoot(ItemStack is, int min, int max, int rarity, String item) {
		FMLLog.info("Adding to chests: " + item, min);
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdCorridor").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdLibrary").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdCrossing").addItem(chestGen);
		ChestGenHooks.getInfo("bonusChest").addItem(chestGen);

	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
		commandManager.registerCommand(new rpgInventory.handelers.CommandPanel());
		rpgInventory.handelers.CommandPanel.init();
	}
}
