package RpgInventory;
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
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import RpgInventory.Configuration.RpgConfig;
import RpgInventory.PotionEffects.DecomposePotion;
import RpgInventory.PotionEffects.MasochismPotion;
import RpgInventory.contents.RpgItems;
import RpgInventory.contents.RpgItemsMage;
import RpgInventory.contents.RpgItemsNames;
import RpgInventory.contents.RpgItemsRogue;
import RpgInventory.contents.RpgItemsRpg;
import RpgInventory.contents.RpgItemsShields;
import RpgInventory.forge.BlockForge;
import RpgInventory.forge.TEMold;
import RpgInventory.gui.RpgInventoryTab;
import RpgInventory.item.ItemMats;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgInventory.item.armor.ItemRpgPlusPlusArmor;
import RpgInventory.weapons.ItemGrandSword;
import RpgInventory.weapons.ItemNecroSkull;
import RpgInventory.weapons.bow.BowRender;
import RpgInventory.weapons.bow.EntityHellArrow;
import RpgInventory.weapons.claymore.ClaymoreRenderer;
import RpgInventory.weapons.hammer.HammerRender;
import RpgInventory.weapons.staf.StafRender;
import RpgInventory.weapons.wand.SoulSphereRender;
import RpgMageSet.weapons.ItemElementalStaff;
import RpgRB.ItemRBMats;
import RpgRB.ItemRBMats2;
import RpgRB.axe.AxeRender;
import RpgRB.axe.ItemBeastAxe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
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

@Mod(modid = "RPGInventoryMod", name = "RPG Inventory Mod", version = "147.70")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv","RpgRawInv"}, packetHandler = RpgPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv"}, packetHandler = RpgPacketHandler.class))
public class mod_RpgInventory {

	public static mod_RpgInventory instance;

	@SidedProxy(serverSide = "RpgInventory.CommonProxy", clientSide = "RpgInventory.ClientProxy")
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
	public static Item elfbow;			//  goes with Archers.
	public static Item claymore;		// goes with Berserkers
	public static Item hammer;
	public static Item wand;			// goes with Mages
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

	public static Block forgeBlock;
	//Die bitches.
	public static List<String> developers = new ArrayList<String>();
	public String[][] recipePatterns;
	public Object[][] recipeItems;
	public static boolean hasRpg;
	public static boolean hasShields;
	public static boolean hasRogue;
	public static boolean hasMage;
	public static boolean pony;

	private static int uniqueID = 0;
	public static Potion decomposePotion;
	public static Potion masochismPotion;

	public mod_RpgInventory() {
		instance = this;
	}

	public int getUniqueID() {
		return uniqueID++;
	}

	public EnumArmorMaterial robes = EnumHelper.addArmorMaterial("magerobes", 20, new int[]{2, 2, 2, 1}, 5);	// use of Magic Tools
	public EnumArmorMaterial hides = EnumHelper.addArmorMaterial("hides", 20, new int[]{2, 3, 2, 2}, 5);		// use of Bows
	public EnumArmorMaterial armoury = EnumHelper.addArmorMaterial("armoury", 20, new int[]{2, 4, 3, 2}, 5);
	public EnumArmorMaterial rogueArmor = EnumHelper.addArmorMaterial("rogue", 20, new int[]{3, 5, 4, 3}, 5);
	public EnumArmorMaterial beastMaster = EnumHelper.addArmorMaterial("beast", 20, new int[]{4, 5, 4, 3}, 5);

	public	EnumToolMaterial clay = EnumHelper.addToolMaterial("claymore", 0, 1024, 5F, 6, 0);
	public EnumToolMaterial stone = EnumHelper.addToolMaterial("RageBreaker", 0, 750, 5F, 4, 0);
	public EnumToolMaterial NecroToolMaterial;
	public EnumToolMaterial PalaToolMaterial;

	public static CreativeTabs tab;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		RpgConfig.instance.loadConfig(event.getSuggestedConfigurationFile());
	}

	@Init
	public void load(FMLInitializationEvent event) {
		// NOTHING BEFORE THE GOD DAMN TAB !

		tab = new RpgInventoryTab(CreativeTabs.getNextID(), "RpgTab");
		RpgItems.init(this, tab);

		//Look for Rpg ++
		try {
			Class.forName("RpgPlusPlus.mod_RpgPlus");
			System.out.println("Rpg++ Necromancer and Paladin is installed. Renderers can be Used");
			hasRpg = true;
		} catch (Throwable e) {
			System.out.println("Rpg++ Necromancer and Paladin has not been detected. Renderers for Rpg++ Excluded");
			hasRpg = false;
		}
		//look for Vanilla Shields
		try {
			Class.forName("RpgShields.mod_VanillaShields");
			System.out.println("Rpg++ Vanilla Shields is installed. Renderers can be Used");
			hasShields = true;
		} catch (Throwable e) {
			System.out.println("Rpg++ Vanilla Shields has not been detected. Renderers for Vanilla Shields Excluded");
			hasShields = false;
		}
		try {
			Class.forName("RpgRB.mod_RpgRB");
			System.out.println("Rpg++ Rogue and BeastMaster Installed. Renderers can be Used");
			hasRogue = true;
		} catch (Throwable e) {
			System.out.println("Rpg++ Rogue and BeastMaster not detected. Renderers for Vanilla Shields Excluded");
			hasRogue = false;
		}
		try {
			Class.forName("RpgMageSet.mod_RpgMageSet");
			System.out.println("Rpg++ ArchMage Installed. Renderers can be Used");
			hasMage = true;
		} catch (Throwable e) {
			System.out.println("Rpg++ ArchMage not detected. Renderers Excluded");
			hasMage = false;
		}

		developers.add("Unjustice");
		developers.add("artix_all_mighty");
                developers.add("rich1051414");

		forgeBlock = new BlockForge(RpgConfig.instance.forgeblockID, Material.rock).setHardness(5f).setBlockName("MoldForge").setCreativeTab(tab);

		if (hasRpg == true) {
			RpgItemsRpg.init(this, tab);
		}if (hasShields == true) {
			RpgItemsShields.init(this, tab);
		}if (hasRogue == true) {
			RpgItemsRogue.init(this, tab);
		}if(hasMage == true){
			RpgItemsMage.init(this, tab);
		} RpgItemsNames.init(this, tab);


		proxy.registerRenderInformation();


		addChestLoot(new ItemStack(mod_RpgInventory.colmold), 1, 1, 40, "Necklace Mold");
		addChestLoot(new ItemStack(mod_RpgInventory.ringmold), 1, 1, 30, "Ring Mold");
		addChestLoot(new ItemStack(mod_RpgInventory.wantmold), 1, 1, 40, "Gloves Mold");

		GameRegistry.registerTileEntity(TEMold.class, "temold");

		GameRegistry.registerBlock(forgeBlock, "MoldForge");

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
		GameRegistry.addRecipe(new ItemStack(hammer, 1), new Object[]{"SSS", "LI ", "LI ", 'I', Item.ingotIron, 'S', Block.blockSteel, 'L', Item.leather});

		//SHIELDS
		GameRegistry.addRecipe(new ItemStack(archersShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.wood});
		GameRegistry.addRecipe(new ItemStack(berserkerShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.blockSteel});
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

		if (RpgConfig.instance.render3DClaymore == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.claymore.itemID, (IItemRenderer) new ClaymoreRenderer());
		}
		if (RpgConfig.instance.render3DHammer == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.hammer.itemID, (IItemRenderer) new HammerRender());
		}
		if (RpgConfig.instance.render3DSoulSphere == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.wand.itemID, (IItemRenderer) new SoulSphereRender());
		}
		if (RpgConfig.instance.render3DStaff == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.staf.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.frostStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.fireStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(earthStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(windStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(ultimateStaff.itemID, (IItemRenderer) new StafRender());
		}
		if (RpgConfig.instance.render3DBow == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.elfbow.itemID, (IItemRenderer) new BowRender());
		}
		if (RpgConfig.instance.render3DAxe == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.beastAxe.itemID, (IItemRenderer) new AxeRender());
		}


		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new RPGEventHooks());
		EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow", getUniqueID(), this, 250, 1, true);


		//hack to increase the number of potion types allowed
		if (Potion.potionTypes.length < 256) {
			Potion[] potionTypes = null;

			for (Field f : Potion.class.getDeclaredFields()) {
				f.setAccessible(true);
				try {
					if (f.getName().equals("potionTypes") || f.getName().equals("a")) {
						Field modfield = Field.class.getDeclaredField("modifiers");
						modfield.setAccessible(true);
						modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

						potionTypes = (Potion[]) f.get(null);
						final Potion[] newPotionTypes = new Potion[256];
						System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
						f.set(null, newPotionTypes);
					}
				} catch (Exception e) {
					System.err.println("Severe error, please report this to the mod author:");
					System.err.println(e);
				}
			}
		}
		//0-32 is used by vanilla
		//Check to make sure this hack wasnt already applied and >32 potions
		//already exist. insert only when null
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

	@Mod.PostInit
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
		System.out.println("Added " + item + " to chests");
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("villageBlacksmith").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidJungleChest").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidDesertyChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);

	}

	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
		commandManager.registerCommand(new RpgInventory.CommandPanel());
		RpgInventory.CommandPanel.init();
	}
}
