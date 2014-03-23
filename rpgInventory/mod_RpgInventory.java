package rpgInventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import rpgInventory.block.BlockForge;
import rpgInventory.block.te.TEMold;
import rpgInventory.entity.EntityHellArrow;
import rpgInventory.gui.RpgInventoryTab;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.handlers.proxy.ClientProxy;
import rpgInventory.handlers.proxy.CommonProxy;
import rpgInventory.item.ItemMold;
import rpgInventory.item.ItemRageFood;
import rpgInventory.item.ItemRpg;
import rpgInventory.item.armor.ItemArcherArmor;
import rpgInventory.item.armor.ItemBerserkerArmor;
import rpgInventory.item.armor.ItemMageArmor;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.item.weapons.ItemArcherBow;
import rpgInventory.item.weapons.ItemClaymore;
import rpgInventory.item.weapons.ItemHammer;
import rpgInventory.item.weapons.ItemMageSphere;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = mod_RpgInventory.name, name = mod_RpgInventory.ID, version = mod_RpgInventory.version)
// @NetworkMod(clientSideRequired = true, serverSideRequired = true,
// clientPacketHandlerSpec =
// @SidedPacketHandler(channels = {"RpgInv"}, packetHandler =
// RpgPacketHandler.class),
// serverPacketHandlerSpec =
// @SidedPacketHandler(channels = {"RpgInv"}, packetHandler =
// RpgPacketHandler.class))
public class mod_RpgInventory {

	public static class ITEMTYPE {

		public static final int NECKLACE = 0;
		public static final int SHIELD = 1;
		public static final int CLOAK = 2;
		public static final int GLOVES = 3;
		public static final int RING = 4;
		public static final int CRYSTAL = 5;
	}

	protected static final String name = "rpginventorymod";
	protected static final String ID = "Rpg Inventory";

	protected static final String version = "1.7.2";
	public static String CLASSARCHER = "archer";
	public static String CLASSBERSERKER = "berserker";

	public static String CLASSMAGE = "basicMage";
	public static String CLASSARCHERSHIELD = "shieldedArcher";
	public static String CLASSBERSERKERSHIELD = "shieldedBerserker";

	public static String CLASSMAGESHIELD = "shieldedBasicMage";

	public static String playerClass = "none";
	public static mod_RpgInventory instance;

	@SidedProxy(serverSide = "rpgInventory.handlers.proxy.CommonProxy", clientSide = "rpgInventory.handlers.proxy.ClientProxy")
	public static CommonProxy proxy;

	public static Item
	/* ====jewels==== */
	neckgold, neckdia, neckem, necklap, glovesbutter, glovesdia, glovesem,
			gloveslap, ringgold, ringdia, ringem, ringlap,
			/* ====shields==== */
			archerShield, berserkerShield, talisman,
			/* ====cloaks==== */
			cloak, cloakI, cloakSub, cloakRed, cloakYellow, cloakGreen,
			cloakBlue,
			/* ====weapons==== */
			elfbow, claymore, hammer, wand, staf,
			/* ====extra items==== */
			rageSeed, wizardBook,
			/* ====armor==== */
			magehood, magegown, magepants, mageboots, archerhood, archerchest,
			archerpants, archerboots, berserkerHood, berserkerChest,
			berserkerLegs, berserkerBoots,
			/* ====leathers/skins==== */
			animalskin, tanHide, magecloth,
			/* ====molds==== */
			colmold, ringmold, wantmold;

	public static Block forgeBlock;
	// Die bitches.
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

	public final ArmorMaterial mage = EnumHelper.addArmorMaterial("mage", 20,
			new int[] { 2, 2, 2, 1 }, 5);

	public final ArmorMaterial archer = EnumHelper.addArmorMaterial("archer",
			20, new int[] { 2, 3, 2, 2 }, 5);
	public final ArmorMaterial berserker = EnumHelper.addArmorMaterial(
			"berserker", 20, new int[] { 2, 4, 3, 2 }, 5);
	ToolMaterial clay = EnumHelper
			.addToolMaterial("claymore", 0, 750, 5F, 6, 0);

	ToolMaterial stone = EnumHelper.addToolMaterial("RageBreaker", 0, 1024, 5F,
			4, 0);
	public static CreativeTabs tab;

	public static ArrayList<String> donators = new ArrayList<String>();

	public mod_RpgInventory() {
		instance = this;
	}

	public void addCandyChestLoot(ItemStack is, int min, int max, int rarity,
			String item) {
		FMLLog.info("Adding to chests: " + item, min);
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(
				is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdCorridor").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdLibrary").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdCrossing").addItem(chestGen);
		ChestGenHooks.getInfo("bonusChest").addItem(chestGen);

	}

	public void addChestLoot(ItemStack is, int min, int max, int rarity,
			String item) {
		FMLLog.info("Adding to chests: " + item, min);
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(
				is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("villageBlacksmith").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidJungleChest").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidDesertyChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);

	}

	public void addRareLoot(ItemStack is, int min, int max, int rarity,
			String item) {
		FMLLog.info("Adding to chests: " + item, min);
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(
				is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdCorridor").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdLibrary").addItem(chestGen);
		ChestGenHooks.getInfo("strongholdCrossing").addItem(chestGen);
	}

	public int getUniqueID() {
		return uniqueID++;
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		setDonators();
		// GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, name));

		// NOTHING BEFORE THE GOD DAMN TAB !
		// any items that need to be in it, put in it BEFORE the tab exists will
		// not be in
		tab = new RpgInventoryTab(CreativeTabs.getNextID(), "RpgTab");

		try {
			Class.forName("rpgNecroPaladin.mod_RpgPlus");
			hasRpg = true;
		} catch (Throwable e) {
			hasRpg = false;
		}
		try {
			Class.forName("rpgVanillaShields.mod_VanillaShields");
			hasShields = true;
		} catch (Throwable e) {
			hasShields = false;
		}
		try {
			Class.forName("rpgRogueBeast.mod_RpgRB");
			hasRogue = true;
		} catch (Throwable e) {
			hasRogue = false;
		}
		try {
			Class.forName("rpgMage.mod_RpgMageSet");
			hasMage = true;
		} catch (Throwable e) {
			hasMage = false;
		}

		developers.add("unjustice");
		developers.add("artix_all_mighty");
		developers.add("rich1051414");
		developers.add("darkhax");

		forgeBlock = new BlockForge(Material.rock).setHardness(5f)
		/* .setUnlocalizedName("MoldForge") */.setCreativeTab(tab);

		neckgold = new ItemRpgInvArmor(0, -1, "",
				"subaraki:jewels/NeckGold.png").setUnlocalizedName("neckGold")
				.setCreativeTab(tab);
		neckdia = new ItemRpgInvArmor(0, -1, "", "subaraki:jewels/NeckDia.png")
				.setUnlocalizedName("neckDia").setCreativeTab(tab);
		neckem = new ItemRpgInvArmor(0, -1, "", "subaraki:jewels/NeckEm.png")
				.setUnlocalizedName("neckEm").setCreativeTab(tab);
		necklap = new ItemRpgInvArmor(0, -1, "", "subaraki:jewels/NeckLap.png")
				.setUnlocalizedName("neckLap").setCreativeTab(tab);

		ringgold = new ItemRpgInvArmor(4, -1, "", "").setUnlocalizedName(
				"ringGold").setCreativeTab(tab);
		ringdia = new ItemRpgInvArmor(4, -1, "", "").setUnlocalizedName(
				"ringDia").setCreativeTab(tab);
		ringem = new ItemRpgInvArmor(4, -1, "", "")
				.setUnlocalizedName("ringEm").setCreativeTab(tab);
		ringlap = new ItemRpgInvArmor(4, -1, "", "").setUnlocalizedName(
				"ringLap").setCreativeTab(tab);

		glovesbutter = new ItemRpgInvArmor(3, -1, "",
				"subaraki:jewels/Glove.png").setUnlocalizedName("gloveGold")
				.setCreativeTab(tab);
		glovesdia = new ItemRpgInvArmor(3, -1, "",
				"subaraki:jewels/GloveDia.png").setUnlocalizedName("gloveDia")
				.setCreativeTab(tab);
		glovesem = new ItemRpgInvArmor(3, -1, "", "subaraki:jewels/GloveEm.png")
				.setUnlocalizedName("gloveEm").setCreativeTab(tab);
		gloveslap = new ItemRpgInvArmor(3, -1, "",
				"subaraki:jewels/GloveLap.png").setUnlocalizedName("gloveLap")
				.setCreativeTab(tab);

		archerShield = new ItemRpgInvArmor(1, 200, "",
				"subaraki:jewels/Shield1.png").setUnlocalizedName(
				"shieldArcher").setCreativeTab(tab);
		berserkerShield = new ItemRpgInvArmor(1, 350, "",
				"subaraki:jewels/IronThorn.png").setUnlocalizedName(
				"shieldBerserker").setCreativeTab(tab);
		talisman = new ItemRpgInvArmor(1, 200, "",
				"subaraki:jewels/mageShield.png").setUnlocalizedName(
				"shieldMage").setCreativeTab(tab);

		cloak = new ItemRpgInvArmor(2, -1, "", "subaraki:capes/GreyCape.png")
				.setFull3D().setUnlocalizedName("capeGrey").setCreativeTab(tab);
		cloakI = new ItemRpgInvArmor(2, -1, "", "subaraki:capes/GreyCape.png")
				.setFull3D().setUnlocalizedName("i.capeGrey")
				.setCreativeTab(tab);

		magehood = new ItemMageArmor(mage, 4, 0).setUnlocalizedName("mage1")
				.setCreativeTab(tab);
		magegown = new ItemMageArmor(mage, 4, 1).setUnlocalizedName("mage2")
				.setCreativeTab(tab);
		magepants = new ItemMageArmor(mage, 4, 2).setUnlocalizedName("mage3")
				.setCreativeTab(tab);
		mageboots = new ItemMageArmor(mage, 4, 3).setUnlocalizedName("mage4")
				.setCreativeTab(tab);

		archerhood = new ItemArcherArmor(archer, 4, 0).setUnlocalizedName(
				"archer1").setCreativeTab(tab);
		archerchest = new ItemArcherArmor(archer, 4, 1).setUnlocalizedName(
				"archer2").setCreativeTab(tab);
		archerpants = new ItemArcherArmor(archer, 4, 2).setUnlocalizedName(
				"archer3").setCreativeTab(tab);
		archerboots = new ItemArcherArmor(archer, 4, 3).setUnlocalizedName(
				"archer4").setCreativeTab(tab);

		berserkerHood = new ItemBerserkerArmor(berserker, 4, 0)
				.setUnlocalizedName("berserk1").setCreativeTab(tab);
		berserkerChest = new ItemBerserkerArmor(berserker, 4, 1)
				.setUnlocalizedName("berserk2").setCreativeTab(tab);
		berserkerLegs = new ItemBerserkerArmor(berserker, 4, 2)
				.setUnlocalizedName("berserk3").setCreativeTab(tab);
		berserkerBoots = new ItemBerserkerArmor(berserker, 4, 3)
				.setUnlocalizedName("berserk4").setCreativeTab(tab);

		claymore = new ItemClaymore(clay).setFull3D().setMaxDamage(1024)
				.setUnlocalizedName("claymore").setCreativeTab(tab);
		wand = new ItemMageSphere().setFull3D().setMaxDamage(400)
				.setUnlocalizedName("soulsphere").setCreativeTab(tab);
		elfbow = new ItemArcherBow().setFull3D().setMaxDamage(350)
				.setUnlocalizedName("elmBow").setCreativeTab(tab);

		animalskin = new ItemRpg().setUnlocalizedName("a.leather")
				.setCreativeTab(tab);
		tanHide = new ItemRpg().setUnlocalizedName("t.leather").setCreativeTab(
				tab);
		magecloth = new ItemRpg().setUnlocalizedName("m.leather")
				.setCreativeTab(tab);

		wizardBook = new ItemRpg().setUnlocalizedName("a.book_normal")
				.setCreativeTab(tab);

		hammer = new ItemHammer(stone).setMaxDamage(750)
				.setUnlocalizedName("rageBreaker").setCreativeTab(tab);
		staf = new ItemStaf().setMaxStackSize(1).setMaxDamage(1500)
				.setUnlocalizedName("lunarStaff").setCreativeTab(tab);

		rageSeed = new ItemRageFood(0, 0f, false).setAlwaysEdible()
				.setUnlocalizedName("r.seeds_melon").setMaxStackSize(8)
				.setCreativeTab(tab);

		cloakRed = new ItemRpgInvArmor(2, -1, "", "subaraki:capes/RedCape.png")
				.setFull3D().setUnlocalizedName("r.capeGrey")
				.setCreativeTab(tab);
		cloakYellow = new ItemRpgInvArmor(2, -1, "",
				"subaraki:capes/GoldCape.png").setFull3D()
				.setUnlocalizedName("y.capeGrey").setCreativeTab(tab);
		cloakGreen = new ItemRpgInvArmor(2, -1, "",
				"subaraki:capes/GreenCape.png").setFull3D()
				.setUnlocalizedName("g.capeGrey").setCreativeTab(tab);
		cloakBlue = new ItemRpgInvArmor(2, -1, "", "subaraki:capes/SkyCape.png")
				.setFull3D().setUnlocalizedName("b.capeGrey")
				.setCreativeTab(tab);
		cloakSub = new ItemRpgInvArmor(2, -1, "", "subaraki:capes/BlaCape.png")
				.setFull3D().setUnlocalizedName("s.capeGrey")
				.setCreativeTab(tab);

		colmold = new ItemMold().setUnlocalizedName("moldNeck").setCreativeTab(
				tab);
		ringmold = new ItemMold().setUnlocalizedName("moldRing")
				.setCreativeTab(tab);
		wantmold = new ItemMold().setUnlocalizedName("moldGlove")
				.setCreativeTab(tab);

		proxy.registerRenderInformation();

		addChestLoot(new ItemStack(mod_RpgInventory.colmold), 1, 1, 20,
				"Necklace Mold");
		addChestLoot(new ItemStack(mod_RpgInventory.ringmold), 1, 1, 10,
				"Ring Mold");
		addChestLoot(new ItemStack(mod_RpgInventory.wantmold), 1, 1, 20,
				"Gloves Mold");
		addRareLoot(new ItemStack(mod_RpgInventory.cloakI), 1, 1, 1,
				"Gloves Mold");

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
		LanguageRegistry.addName(talisman, "Aura Shield");

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
		LanguageRegistry.addName(claymore, "Berserker Claymore");
		LanguageRegistry.addName(elfbow, "Birch Bow");

		LanguageRegistry.addName(animalskin, "Animal Skin");
		LanguageRegistry.addName(tanHide, "Tanned Hide");
		LanguageRegistry.addName(magecloth, "Mage Cloth");

		LanguageRegistry.addName(wizardBook, "Wizard's Knowledge, Volume I");
		LanguageRegistry.addName(hammer, "Rage Breaker");
		LanguageRegistry.addName(staf, "Lunar Staff");
		LanguageRegistry.addName(rageSeed, "Rage Seeds");

		LanguageRegistry.addName(colmold, "Necklace Mold");
		LanguageRegistry.addName(ringmold, "Ring Mold");
		LanguageRegistry.addName(wantmold, "Glove Mold");

		MinecraftForge.addGrassSeed(new ItemStack(rageSeed, 1), 1);

		// SKINS
		GameRegistry.addRecipe(new ItemStack(animalskin, 1), new Object[] {
				"WWW", "WLW", "WWW", 'W', new ItemStack(Blocks.wool, 1, 12),
				'L', Items.leather });
		GameRegistry.addShapelessRecipe(new ItemStack(tanHide, 1),
				new Object[] { Items.leather, Items.string, Items.string,
						Items.string, Items.string });
		GameRegistry.addRecipe(new ItemStack(magecloth, 1), new Object[] {
				"WWW", "WLW", "WWW", 'W', new ItemStack(Items.dye, 1, 4), 'L',
				Items.leather });
		// WEAPONRY
		GameRegistry.addRecipe(new ItemStack(elfbow, 1), new Object[] { "EPP",
				"P S", "PS ", 'E', Items.emerald, 'S', Items.string, 'P',
				new ItemStack(Blocks.log, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(wand, 1), new Object[] { "GGG",
				"GLG", "GGG", 'L', Blocks.lapis_block, 'G', Items.gold_ingot });
		GameRegistry.addRecipe(new ItemStack(staf, 1), new Object[] { " ML",
				" SM", "S  ", 'M', Items.speckled_melon, 'S', Items.stick, 'L',
				new ItemStack(Items.dye, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(claymore, 1), new Object[] {
				" S ", " S ", "LIL", 'I', Items.iron_ingot, 'S', Blocks.stone,
				'L', Items.leather });
		GameRegistry.addRecipe(new ItemStack(hammer, 1), new Object[] { "SSS",
				"LI ", "LI ", 'I', Items.iron_ingot, 'S', Blocks.iron_block,
				'L', Items.leather });

		// SHIELDS
		GameRegistry.addRecipe(new ItemStack(archerShield, 1), new Object[] {
				"WWW", "WBW", " W ", 'W', Items.iron_ingot, 'B', Blocks.log });
		GameRegistry.addRecipe(new ItemStack(berserkerShield, 1), new Object[] {
				"WWW", "WBW", " W ", 'W', Items.iron_ingot, 'B',
				Blocks.iron_block });
		GameRegistry.addRecipe(new ItemStack(talisman, 1), new Object[] {
				"WWW", "WBW", " W ", 'W', new ItemStack(Items.dye, 1, 4), 'B',
				Blocks.lapis_block });

		// CLOAK
		GameRegistry.addRecipe(new ItemStack(cloak, 1), new Object[] { "SS",
				"II", "II", 'I', Blocks.wool, 'S', Items.string });
		GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[] { "PPP",
				"PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.potionitem, 1, 8206) });
		GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[] { "PPP",
				"PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.potionitem, 1, 8270) });

		GameRegistry.addRecipe(new ItemStack(cloakRed, 1), new Object[] {
				"PPP", "PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.dye, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(cloakYellow, 1), new Object[] {
				"PPP", "PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.dye, 1, 11) });
		GameRegistry.addRecipe(new ItemStack(cloakGreen, 1), new Object[] {
				"PPP", "PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.dye, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(cloakBlue, 1), new Object[] {
				"PPP", "PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.dye, 1, 12) });
		GameRegistry.addRecipe(new ItemStack(cloakSub, 1), new Object[] {
				"PPP", "PCP", "PPP", 'C', cloak, 'P',
				new ItemStack(Items.dye, 1, 0) });

		GameRegistry.addRecipe(new ItemStack(forgeBlock, 1), new Object[] {
				"BBB", "BOB", "BBB", 'B', Blocks.brick_block, 'O',
				Blocks.obsidian });

		// MageBook
		GameRegistry
				.addShapelessRecipe(new ItemStack(wizardBook, 1), new Object[] {
						magecloth, Items.paper, Items.paper, Items.paper });

		// ARMOR
		recipePatterns = new String[][] { { "XXX", "X X" },
				{ "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" },
				{ "X X", "X X" } };
		recipeItems = new Object[][] { { animalskin, tanHide, magecloth },
				{ berserkerHood, archerhood, magehood },
				{ berserkerChest, archerchest, magegown },
				{ berserkerLegs, archerpants, magepants },
				{ berserkerBoots, archerboots, mageboots } };

		for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for (int var4 = 0; var4 < (this.recipeItems.length - 1); ++var4) {
				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[] {
						this.recipePatterns[var4], 'X', var3 });
			}
		}

		// TODO
		// NetworkRegistry.instance().registerGuiHandler(this, new
		// GuiHandler());
		// GameRegistry.registerPlayerTracker(new PlayerTracker());
		// TickRegistry.registerTickHandler(new CommonTickHandler(),
		// Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new RPGEventHooks());
		EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow",
				getUniqueID(), this, 250, 1, true);

		ClientProxy.renderHandler();

		// hack to increase the number of potion types allowed

		if (Potion.potionTypes.length < 256) {
			boolean found = false;
			Field fallbackfield = null;
			Potion[] potionTypes = null;
			for (Field f : Potion.class.getDeclaredFields()) {
				try {
					if ((fallbackfield != null)
							&& (f.getType() == Potion[].class)) {
						fallbackfield = f;
					}
					if (f.getName().equals("potionTypes")
							|| f.getName().equals("a")
							|| f.getName().equals("field_76425_a")) {
						found = true;
						Field modfield = Field.class
								.getDeclaredField("modifiers");
						modfield.setAccessible(true);
						modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

						potionTypes = (Potion[]) f.get(null);
						final Potion[] newPotionTypes = new Potion[256];
						System.arraycopy(potionTypes, 0, newPotionTypes, 0,
								potionTypes.length);
						f.set(null, newPotionTypes);
						break;
					}
				} catch (Exception e) {
					System.err
							.println("Severe error, please report this to the mod author:");
					System.err.println(e);
				}
			}
			try {
				if ((fallbackfield != null) && !found) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(fallbackfield, fallbackfield.getModifiers()
							& ~Modifier.FINAL);

					potionTypes = (Potion[]) fallbackfield.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0,
							potionTypes.length);
					fallbackfield.set(null, newPotionTypes);
				}
			} catch (Exception ex) {
				System.err
						.println("Severe error, please report this to the mod author:");
				System.err.println(ex);
			}
		}

		if (hasRpg == true) {
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

	public String playerClass() {
		return playerClass;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		proxy.registerLate();
		// All mods should be initialized now, check what potion effects are
		// installed
		// and list the bad ones for later reference.
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// loadConfig(event.getSuggestedConfigurationFile());
	}

	// @SideOnly(Side.CLIENT)
	// private void registerClientEvents(){
	// MinecraftForge.EVENT_BUS.register(new RenderRpgPlayer());
	// }

	@EventHandler
	public void serverLoad(FMLServerStartingEvent e) {
		// NetworkRegistry.INSTANCE.newChannel("RpgInv", new
		// RpgPacketHandler());

	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		//TODO get commands back
//		CommandHandler commandManager = (CommandHandler) event.getServer()
//				.getCommandManager();
//		commandManager
//				.registerCommand(new rpgInventory.handlers.CommandPanel());
//		rpgInventory.handlers.CommandPanel.init();
	}

	private void setDonators() {
		try {
			URL url = new URL(
					"http://www.dnstechpack.com/user/subaraki/rpgcapes/donatorList.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				donators.add(str);
			}
			FMLLog.getLogger().info(
					"Added Dev-Donation rank for : " + donators.toString());

			in.close();
		} catch (MalformedURLException e) {
			FMLLog.getLogger().info(
					"[ERROR] Couldn't Handle Donators. Index 1.");
		} catch (IOException e) {
			FMLLog.getLogger().info(
					"[ERROR] Couldn't Handle Donators. Index 2.");
		}
	}
}
