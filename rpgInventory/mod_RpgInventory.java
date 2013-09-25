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
import rpgInventory.handlers.CommonTickHandler;
import rpgInventory.handlers.GuiHandler;
import rpgInventory.handlers.PlayerTracker;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.handlers.packets.RpgPacketHandler;
import rpgInventory.handlers.proxy.ClientProxy;
import rpgInventory.handlers.proxy.CommonProxy;
import rpgInventory.item.ItemMold;
import rpgInventory.item.ItemRageFood;
import rpgInventory.item.ItemRpg;
import rpgInventory.item.armor.ItemClassArmor;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.item.weapons.ItemArcherBow;
import rpgInventory.item.weapons.ItemClaymore;
import rpgInventory.item.weapons.ItemHammer;
import rpgInventory.item.weapons.ItemMageSphere;
import rpgInventory.item.weapons.ItemStaf;
import rpgInventory.renderer.RenderRpgPlayer;
import rpgInventory.richUtil.potions.DecomposePotion;
import rpgInventory.richUtil.potions.MasochismPotion;
import rpgMage.weapons.ItemElementalStaff;
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
import cpw.mods.fml.relauncher.SideOnly;



@Mod(modid = "rpginventorymod", name = "RPG Inventory Mod", version = "1.6.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = true,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv"}, packetHandler = RpgPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv"}, packetHandler = RpgPacketHandler.class))
public class mod_RpgInventory {

	public static mod_RpgInventory instance;
	@SidedProxy(serverSide = "rpgInventory.handlers.proxy.CommonProxy", clientSide = "rpgInventory.handlers.proxy.ClientProxy")
	public static CommonProxy proxy;

	public static Item 
	/*====jewels====*/
	neckgold,neckdia, neckem, necklap, 
	glovesbutter, glovesdia, glovesem, gloveslap, 
	ringgold, ringdia, ringem, ringlap,
	/*====shields====*/
	archerShield, berserkerShield, talisman,
	archBook,
	shieldWood, shieldIron, shieldGold, shieldDiamond, 
	/*====cloaks====*/
	cloak, cloakI, cloakSub, cloakRed, cloakYellow, cloakGreen, cloakBlue, 
	/*====weapons====*/
	elfbow, claymore, hammer, wand, staf,
	fireStaff, frostStaff, earthStaff, windStaff, ultimateStaff,
	/*====extra items====*/
	rageSeed,wizardBook, 
	/*====armor====*/
	magehood, magegown, magepants, mageboots,
	archerhood, archerchest, archerpants, archerboots,
	berserkerHood, berserkerChest, berserkerLegs, berserkerBoots,
	archmageHood, archmageChest, archmageLegs, archMageBoots,
	/*====leathers/skins====*/
	animalskin, tanHide, magecloth, archMageLeather,
	/*====molds====*/
	colmold, ringmold, wantmold;

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
	public  final EnumArmorMaterial mage = EnumHelper.addArmorMaterial("mage", 20, new int[]{2, 2, 2, 1}, 5);
	public  final EnumArmorMaterial archer = EnumHelper.addArmorMaterial("archer", 20, new int[]{2, 3, 2, 2}, 5);
	public  final EnumArmorMaterial archMage = EnumHelper.addArmorMaterial("archmage", 20, new int[]{4, 4, 4, 2}, 5);
	public  final EnumArmorMaterial berserker = EnumHelper.addArmorMaterial("berserker", 20, new int[]{2, 4, 3, 2}, 5);
	
	EnumToolMaterial clay = EnumHelper.addToolMaterial("claymore", 0, 750, 5F, 6, 0);
	EnumToolMaterial stone = EnumHelper.addToolMaterial("RageBreaker", 0, 1024, 5F, 4, 0);
	
	public static CreativeTabs tab;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RpgConfig.instance.loadConfig(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// NOTHING BEFORE THE GOD DAMN TAB ! 
		//any items that need to be in it, put in it BEFORE the tab exists will not be in
		tab = new RpgInventoryTab(CreativeTabs.getNextID(), "RpgTab");


		//enabling items for "plugins"
		try {
			Class.forName("rpgNecroPaladin.mod_RpgPlus");
//			FMLLog.info("Rpg++ Necromancer and Paladin is installed. Renderers can be Used", 1);
			hasRpg = true;
		} catch (Throwable e) {
//			FMLLog.info("Rpg++ Necromancer and Paladin has not been detected. Renderers for Rpg++ Excluded");
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

		neckgold = new ItemRpgInvArmor(RpgConfig.instance.neckgoldID, 0, -1, "", "subaraki:jewels/NeckGold.png").setUnlocalizedName("neckGold").setCreativeTab(tab);
		neckdia = new ItemRpgInvArmor(RpgConfig.instance.neckdiaID, 0, -1, "", "subaraki:jewels/NeckDia.png").setUnlocalizedName("neckDia").setCreativeTab(tab);
		neckem = new ItemRpgInvArmor(RpgConfig.instance.neckemID, 0, -1, "", "subaraki:jewels/NeckEm.png").setUnlocalizedName("neckEm").setCreativeTab(tab);
		necklap = new ItemRpgInvArmor(RpgConfig.instance.necklapID, 0, -1, "", "subaraki:jewels/NeckLap.png").setUnlocalizedName("neckLap").setCreativeTab(tab);

		ringgold = new ItemRpgInvArmor(RpgConfig.instance.ringgoldID, 4, -1, "", "").setUnlocalizedName("ringGold").setCreativeTab(tab);
		ringdia = new ItemRpgInvArmor(RpgConfig.instance.ringdiaID, 4, -1, "", "").setUnlocalizedName("ringDia").setCreativeTab(tab);
		ringem = new ItemRpgInvArmor(RpgConfig.instance.ringemID, 4, -1, "", "").setUnlocalizedName("ringEm").setCreativeTab(tab);
		ringlap = new ItemRpgInvArmor(RpgConfig.instance.ringlapID, 4, -1, "", "").setUnlocalizedName("ringLap").setCreativeTab(tab);

		glovesbutter = new ItemRpgInvArmor(RpgConfig.instance.glovesbutterID, 3, -1, "", "subaraki:jewels/Glove.png").setUnlocalizedName("gloveGold").setCreativeTab(tab);
		glovesdia = new ItemRpgInvArmor(RpgConfig.instance.glovesdiaID, 3, -1, "", "subaraki:jewels/GloveDia.png").setUnlocalizedName("gloveDia").setCreativeTab(tab);
		glovesem = new ItemRpgInvArmor(RpgConfig.instance.glovesemID, 3, -1, "", "subaraki:jewels/GloveEm.png").setUnlocalizedName("gloveEm").setCreativeTab(tab);
		gloveslap = new ItemRpgInvArmor(RpgConfig.instance.gloveslapID, 3, -1, "", "subaraki:jewels/GloveLap.png").setUnlocalizedName("gloveLap").setCreativeTab(tab);

		archerShield = new ItemRpgInvArmor(RpgConfig.instance.archersShieldID, 1, 200, "", "subaraki:jewels/Shield1.png").setUnlocalizedName("shieldArcher").setCreativeTab(tab);
		berserkerShield = new ItemRpgInvArmor(RpgConfig.instance.berserkerShieldID, 1, 350, "", "subaraki:jewels/IronThorn.png").setUnlocalizedName("shieldBerserker").setCreativeTab(tab);
		talisman = new ItemRpgInvArmor(RpgConfig.instance.talismanID, 1, 200, "", "subaraki:jewels/talisman.png").setUnlocalizedName("shieldMage").setCreativeTab(tab);

		cloak = new ItemRpgInvArmor(RpgConfig.instance.cloakID, 2, -1, "","").setFull3D().setUnlocalizedName("capeGrey").setCreativeTab(tab);
		cloakI = new ItemRpgInvArmor(RpgConfig.instance.cloakIID, 2, -1, "","").setFull3D().setUnlocalizedName("i.capeGrey").setCreativeTab(tab);

		magehood = new ItemClassArmor(RpgConfig.instance.magehoodID, mage, 4, 0).setUnlocalizedName("mage1").setCreativeTab(tab);
		magegown = new ItemClassArmor(RpgConfig.instance.magegownID, mage, 4, 1).setUnlocalizedName("mage2").setCreativeTab(tab);
		magepants = new ItemClassArmor(RpgConfig.instance.magepantsID, mage, 4, 2).setUnlocalizedName("mage3").setCreativeTab(tab);
		mageboots = new ItemClassArmor(RpgConfig.instance.magebootsID, mage, 4, 3).setUnlocalizedName("mage4").setCreativeTab(tab);

		archerhood = new ItemClassArmor(RpgConfig.instance.archerhoodID, archer, 4, 0).setUnlocalizedName("archer1").setCreativeTab(tab);
		archerchest = new ItemClassArmor(RpgConfig.instance.archerchestID, archer, 4, 1).setUnlocalizedName("archer2").setCreativeTab(tab);
		archerpants = new ItemClassArmor(RpgConfig.instance.archerpantsID, archer, 4, 2).setUnlocalizedName("archer3").setCreativeTab(tab);
		archerboots = new ItemClassArmor(RpgConfig.instance.archerbootsID, archer, 4, 3).setUnlocalizedName("archer4").setCreativeTab(tab);

		berserkerHood = new ItemClassArmor(RpgConfig.instance.berserkerHoodID, berserker, 4, 0).setUnlocalizedName("berserk1").setCreativeTab(tab);
		berserkerChest = new ItemClassArmor(RpgConfig.instance.berserkerChestID, berserker, 4, 1).setUnlocalizedName("berserk2").setCreativeTab(tab);
		berserkerLegs = new ItemClassArmor(RpgConfig.instance.berserkerLegsID, berserker, 4, 2).setUnlocalizedName("berserk3").setCreativeTab(tab);
		berserkerBoots = new ItemClassArmor(RpgConfig.instance.berserkerBootsID, berserker, 4, 3).setUnlocalizedName("berserk4").setCreativeTab(tab);

		claymore = new ItemClaymore(RpgConfig.instance.claymoreID, clay).setFull3D().setMaxDamage(1024).setUnlocalizedName("claymore").setCreativeTab(tab);
		wand = new ItemMageSphere(RpgConfig.instance.wandID).setFull3D().setMaxDamage(400).setUnlocalizedName("soulsphere").setCreativeTab(tab);
		elfbow = new ItemArcherBow(RpgConfig.instance.elfbowID).setFull3D().setMaxDamage(350).setUnlocalizedName("elmBow").setCreativeTab(tab);

		animalskin = new ItemRpg(RpgConfig.instance.animalskinID).setUnlocalizedName("a.leather").setCreativeTab(tab);
		tanHide = new ItemRpg(RpgConfig.instance.tanHideID).setUnlocalizedName("t.leather").setCreativeTab(tab);
		magecloth = new ItemRpg(RpgConfig.instance.mageclothID).setUnlocalizedName("m.leather").setCreativeTab(tab);

		wizardBook = new ItemRpg(RpgConfig.instance.wizardBookID).setUnlocalizedName("a.book_normal").setCreativeTab(tab);

		hammer = new ItemHammer(RpgConfig.instance.hammerID, stone).setMaxDamage(750).setUnlocalizedName("rageBreaker").setCreativeTab(tab);
		staf = new ItemStaf(RpgConfig.instance.stafID).setMaxStackSize(1).setMaxDamage(1500).setUnlocalizedName("lunarStaff").setCreativeTab(tab);

		rageSeed = new ItemRageFood(RpgConfig.instance.rageSeedID, 0, 0f, false).setAlwaysEdible().setUnlocalizedName("r.seeds_melon").setMaxStackSize(8).setCreativeTab(tab);

		cloakRed = new ItemRpgInvArmor(RpgConfig.instance.cloakRedID, 2, -1, "","subaraki:capes/RedCape.png").setFull3D().setUnlocalizedName("r.capeGrey").setCreativeTab(tab);
		cloakYellow = new ItemRpgInvArmor(RpgConfig.instance.cloakYellowID, 2, -1, "","subaraki:capes/GoldCape.png").setFull3D().setUnlocalizedName("y.capeGrey").setCreativeTab(tab);
		cloakGreen = new ItemRpgInvArmor(RpgConfig.instance.cloakGreenID, 2, -1, "","subaraki:capes/GreenCape.png").setFull3D().setUnlocalizedName("g.capeGrey").setCreativeTab(tab);
		cloakBlue = new ItemRpgInvArmor(RpgConfig.instance.cloakBlueID, 2, -1, "","subaraki:capes/SkyCape.png").setFull3D().setUnlocalizedName("b.capeGrey").setCreativeTab(tab);
		cloakSub = new ItemRpgInvArmor(RpgConfig.instance.cloakSubID, 2, -1, "","subaraki:capes/BlaCape.png").setFull3D().setUnlocalizedName("s.capeGrey").setCreativeTab(tab);

		colmold = new ItemMold(RpgConfig.instance.colmoldID).setUnlocalizedName("moldNeck").setCreativeTab(tab);
		ringmold = new ItemMold(RpgConfig.instance.ringmoldID).setUnlocalizedName("moldRing").setCreativeTab(tab);
		wantmold = new ItemMold(RpgConfig.instance.wantmoldID).setUnlocalizedName("moldGlove").setCreativeTab(tab);

		if (hasRpg == true) {

			
		}

		if (hasShields == true) {
			shieldWood = new ItemRpgInvArmor(RpgConfig.instance.shieldWoodID, 1, 50, "wood","subaraki:jewels/ShieldWood.png").setUnlocalizedName("shieldWood");
			shieldIron = new ItemRpgInvArmor(RpgConfig.instance.shieldIronID, 1, 125, "iron","subaraki:jewels/ShieldIron.png").setUnlocalizedName("shieldIron");
			shieldGold = new ItemRpgInvArmor(RpgConfig.instance.shieldGoldID, 1, 250, "gold","subaraki:jewels/ShieldGold.png").setUnlocalizedName("shieldGold");
			shieldDiamond = new ItemRpgInvArmor(RpgConfig.instance.shieldDiamondID, 1, 500, "diamond","subaraki:jewels/ShieldDiamond.png").setUnlocalizedName("shieldDiamond");

			LanguageRegistry.addName(shieldWood, "Wooden Shield");
			LanguageRegistry.addName(shieldIron, "Iron Shield");
			LanguageRegistry.addName(shieldGold, "Golden Shield");
			LanguageRegistry.addName(shieldDiamond, "Diamond Shield");
		}
		if (hasRogue == true) {

			
		}
		if (hasMage == true) {
			fireStaff = new ItemElementalStaff(RpgConfig.instance.fireStaff, 1, 150).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffFire");
			frostStaff = new ItemElementalStaff(RpgConfig.instance.frostStaff, 2, 150).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffIce");
			earthStaff = new ItemElementalStaff(RpgConfig.instance.staffEarth, 3, 150).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffEarth");
			windStaff = new ItemElementalStaff(RpgConfig.instance.staffWind, 4, 500).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffWind");
			ultimateStaff = new ItemElementalStaff(RpgConfig.instance.staffUltimate, 5, 300).setMaxStackSize(1).setMaxDamage(150).setUnlocalizedName("staffElemental");
			archBook = new ItemRpgInvArmor(RpgConfig.instance.archBook, 1, 300, "", "subaraki:jewels/book.png").setUnlocalizedName("archTome");

			archmageHood = new ItemClassArmor(RpgConfig.instance.archmageHood, archMage, 4, 0).setUnlocalizedName("archMage1");
			archmageChest = new ItemClassArmor(RpgConfig.instance.archmageChest, archMage, 4, 1).setUnlocalizedName("archMage2");
			archmageLegs = new ItemClassArmor(RpgConfig.instance.archmageLegs, archMage, 4, 2).setUnlocalizedName("archMage3");
			archMageBoots = new ItemClassArmor(RpgConfig.instance.archmageBoots, archMage, 4, 3).setUnlocalizedName("archMage4");

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

		LanguageRegistry.addName(archerShield, "Small Archer Shield");
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
		GameRegistry.addRecipe(new ItemStack(archerShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.wood});
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
			
		}

		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new RPGEventHooks());
		EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow", getUniqueID(), this, 250, 1, true);
		

		ClientProxy.renderHandler();
		

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
		commandManager.registerCommand(new rpgInventory.handlers.CommandPanel());
		rpgInventory.handlers.CommandPanel.init();
	}
	
	@SideOnly(Side.CLIENT)
	private void registerClientEvents(){
		MinecraftForge.EVENT_BUS.register(new RenderRpgPlayer());
	}
}
