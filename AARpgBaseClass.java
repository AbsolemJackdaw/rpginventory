package RpgInventory;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import RpgInventory.gui.RpgInventoryTab;
import RpgInventory.gui.RpgPacketHandler;
import RpgInventory.gui.inventory.RpgInventory;
import RpgInventory.item.ItemRageFood;
import RpgInventory.item.ItemRpg;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgInventory.weapons.bow.BowRender;
import RpgInventory.weapons.bow.ItemArcherBow;
import RpgInventory.weapons.claymore.ClaymoreRenderer;
import RpgInventory.weapons.claymore.ItemClaymore;
import RpgInventory.weapons.hammer.HammerRender;
import RpgInventory.weapons.hammer.ItemHammer;
import RpgInventory.weapons.staf.ItemStaf;
import RpgInventory.weapons.staf.StafRender;
import RpgInventory.weapons.wand.ItemMageWand;
import RpgInventory.weapons.wand.SoulSphereRender;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RPGInventoryMod", name = "Subarakis RPG Inventory mod", version = "14730")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = { "RpgInv" }, packetHandler = RpgPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = { "RPGInv" }, packetHandler = RpgPacketHandler.class))
public class AARpgBaseClass
{

	EntityPlayer					player;
	public static Item				neckgold;
	public static Item				neckdia;
	public static Item				neckem;
	public static Item				necklap;

	public static Item				glovesbutter;
	public static Item				glovesdia;
	public static Item				glovesem;
	public static Item				gloveslap;

	public static Item				ringgold;
	public static Item				ringdia;
	public static Item				ringem;
	public static Item				ringlap;

	public static Item				shieldwood;
	public static Item				shieldiron;
	public static Item				shieldgold;

	public static Item				cloak;
	public static Item				cloakI;
	public static Item				cloakSub;
	public static Item				cloakRed;
	public static Item				cloakYellow;
	public static Item				cloakGreen;
	public static Item				cloakBlue;

	public static Item				elfbow;			//  goes with Archers.
	public static Item				claymore;			// goes with Berserkers
	public static Item				hammer;
	public static Item				wand;				// goes with Mages
	public static Item				staf;

	public static Item				rageSeed;

	public static Item				magehood;
	public static Item				magegown;
	public static Item				magepants;
	public static Item				mageboots;

	public static Item				archerhood;
	public static Item				archerchest;
	public static Item				archerpants;
	public static Item				archerboots;

	public static Item				ramboband;
	public static Item				rambobody;
	public static Item				rambolegs;
	public static Item				rambofeet;

	public static Item				animalskin;
	public static Item				tanHide;
	public static Item				magecloth;

	public static Item				wizardBook;

	private String[][]				recipePatterns;
	private Object[][]				recipeItems;

	@SidedProxy(serverSide = "RpgInventory.CommonProxy", clientSide = "RpgInventory.ClientProxy")
	public static CommonProxy		proxy;

	public static AARpgBaseClass	instance;

	public static boolean			render3DClaymore;
	public static boolean			render3DHammer;
	public static boolean			render3DSoulSphere;
	public static boolean			render3DStaff;
	public static boolean			render3DBow;

	public static boolean			ArmorChest;

	public static boolean			useSpell;

	public AARpgBaseClass()
	{
		instance = this;
	}

	EnumArmorMaterial			robes	= EnumHelper.addArmorMaterial("magerobes", 20, new int[] { 2, 2, 2, 1 }, 5);	// use of Magic Tools
	EnumArmorMaterial			hides	= EnumHelper.addArmorMaterial("hides", 20, new int[] { 2, 3, 2, 2 }, 5);		// use of Bows
	EnumArmorMaterial			armoury	= EnumHelper.addArmorMaterial("armoury", 20, new int[] { 2, 4, 3, 2 }, 5);		// use of RageBreaker and Claymore

	EnumToolMaterial			clay	= EnumHelper.addToolMaterial("claymore", 0, 1024, 5F, 1, 0);
	EnumToolMaterial			stone	= EnumHelper.addToolMaterial("RageBreaker", 0, 750, 5F, 1, 0);

	public final CreativeTabs	tab		= new RpgInventoryTab(14, "RpgTab");

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		render3DClaymore = config.get(config.CATEGORY_GENERAL, "RenderClaymore", true).getBoolean(true);

		render3DHammer = config.get(config.CATEGORY_GENERAL, "RenderHammer", true).getBoolean(true);

		render3DSoulSphere = config.get(config.CATEGORY_GENERAL, "RenderSoulSphere", true).getBoolean(true);

		render3DStaff = config.get(config.CATEGORY_GENERAL, "RenderStaff", true).getBoolean(true);

		render3DBow = config.get(config.CATEGORY_GENERAL, "RenderBow", true).getBoolean(true);

		ArmorChest = config.get(config.CATEGORY_GENERAL, "(to render default, or smaller ArmorChests: RenderDefaultChest? ", true).getBoolean(true);

		useSpell = config.get(config.CATEGORY_GENERAL, "DayNight Cycle Spell: useSpell ?", true).getBoolean(true);

		config.save();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderInformation();

		neckgold = new ItemRpgArmor(5780, 0).setIconIndex(0).setItemName("buddernecklace").setCreativeTab(tab);
		neckdia = new ItemRpgArmor(5781, 0).setIconIndex(0 + 16).setItemName("neckwithdia").setCreativeTab(tab);
		neckem = new ItemRpgArmor(5782, 0).setIconIndex(0 + 32).setItemName("neckwithem").setCreativeTab(tab);
		necklap = new ItemRpgArmor(5783, 0).setIconIndex(0 + 48).setItemName("neckwithlap").setCreativeTab(tab);

		ringgold = new ItemRpgArmor(5784, 4).setIconIndex(1).setItemName("budderring").setCreativeTab(tab);
		ringdia = new ItemRpgArmor(5785, 4).setIconIndex(1 + 16).setItemName("ringwithdia").setCreativeTab(tab);
		ringem = new ItemRpgArmor(5786, 4).setIconIndex(1 + 32).setItemName("ringwithem").setCreativeTab(tab);
		ringlap = new ItemRpgArmor(5787, 4).setIconIndex(1 + 48).setItemName("ringwithlapis").setCreativeTab(tab);

		glovesbutter = new ItemRpgArmor(5788, 3).setIconIndex(2).setItemName("budderrrgloves").setCreativeTab(tab);
		glovesdia = new ItemRpgArmor(5789, 3).setIconIndex(2 + 16).setItemName("glovesdia").setCreativeTab(tab);
		glovesem = new ItemRpgArmor(5790, 3).setIconIndex(2 + 32).setItemName("glovesem").setCreativeTab(tab);
		gloveslap = new ItemRpgArmor(5791, 3).setIconIndex(2 + 48).setItemName("gloveslap").setCreativeTab(tab);

		shieldwood = new ItemRpgArmor(5792, 1).setIconIndex(3).setItemName("woodshield").setCreativeTab(tab);
		shieldiron = new ItemRpgArmor(5793, 1).setIconIndex(4).setItemName("ironshield").setCreativeTab(tab);
		shieldgold = new ItemRpgArmor(5794, 1).setIconIndex(5).setItemName("buddershield").setCreativeTab(tab);

		cloak = new ItemRpgArmor(5795, 2).setFull3D().setIconIndex(3 + 16).setItemName("cloak").setCreativeTab(tab);
		cloakI = new ItemRpgArmor(5796, 2).setFull3D().setIconIndex(3 + 16).setItemName("InvisibilityCloak").setCreativeTab(tab);

		magehood = new BonusArmor(5797, robes, 4, 0).setItemName("magehoody").setIconIndex(15).setCreativeTab(tab);
		magegown = new BonusArmor(5798, robes, 4, 1).setItemName("magegowny").setIconIndex(31).setCreativeTab(tab);
		magepants = new BonusArmor(5799, robes, 4, 2).setItemName("magepants").setIconIndex(47).setCreativeTab(tab);
		mageboots = new BonusArmor(5800, robes, 4, 3).setItemName("mageshoes").setIconIndex(63).setCreativeTab(tab);

		archerhood = new BonusArmor(5801, hides, 4, 0).setItemName("archerhoody").setIconIndex(14).setCreativeTab(tab);
		archerchest = new BonusArmor(5802, hides, 4, 1).setItemName("archerdhide").setIconIndex(30).setCreativeTab(tab);
		archerpants = new BonusArmor(5803, hides, 4, 2).setItemName("archerpants").setIconIndex(46).setCreativeTab(tab);
		archerboots = new BonusArmor(5804, hides, 4, 3).setItemName("archerboots").setIconIndex(62).setCreativeTab(tab);

		ramboband = new BonusArmor(5805, armoury, 4, 0).setItemName("rambo1").setIconIndex(13).setCreativeTab(tab);
		rambobody = new BonusArmor(5806, armoury, 4, 1).setItemName("rambo2").setIconIndex(29).setCreativeTab(tab);
		rambolegs = new BonusArmor(5807, armoury, 4, 2).setItemName("rambo3").setIconIndex(45).setCreativeTab(tab);
		rambofeet = new BonusArmor(5808, armoury, 4, 3).setItemName("rambo4").setIconIndex(61).setCreativeTab(tab);

		claymore = new ItemClaymore(5809, clay).setFull3D().setMaxDamage(1024).setItemName("Daggerkiller").setIconIndex(20).setCreativeTab(tab);
		wand = new ItemMageWand(5810).setFull3D().setMaxDamage(400).setItemName("MagesWand").setIconIndex(21).setCreativeTab(tab);
		elfbow = new ItemArcherBow(5811).setFull3D().setMaxDamage(350).setItemName("ArcherBow").setIconIndex(22).setCreativeTab(tab);

		animalskin = new ItemRpg(5812).setItemName("animal skin").setIconIndex(103).setCreativeTab(tab);
		tanHide = new ItemRpg(5813).setItemName("tanned hide").setIconIndex(103).setCreativeTab(tab);
		magecloth = new ItemRpg(5814).setItemName("mage cloth").setIconIndex(103).setCreativeTab(tab);

		wizardBook = new ItemRpg(5815).setItemName("wizardbook").setIconCoord(11, 11).setCreativeTab(tab);

		hammer = new ItemHammer(5816, stone).setItemName("BerserkerHammer").setMaxDamage(750).setIconIndex(26).setCreativeTab(tab);
		staf = new ItemStaf(5817).setItemName("MageStaff").setMaxStackSize(1).setMaxDamage(1500).setIconIndex(37).setCreativeTab(tab);

		rageSeed = new ItemRageFood(5818, 0, 0f, false).setAlwaysEdible().setIconCoord(9, 0).setItemName("RageSeed").setMaxStackSize(8).setCreativeTab(tab);

		cloakRed = new ItemRpgArmor(5819, 2).setFull3D().setIconIndex(3 + 16).setItemName("cloakred").setCreativeTab(tab);
		cloakYellow = new ItemRpgArmor(5820, 2).setFull3D().setIconIndex(3 + 16).setItemName("cloakyellow").setCreativeTab(tab);
		cloakGreen = new ItemRpgArmor(5821, 2).setFull3D().setIconIndex(3 + 16).setItemName("cloakgreen").setCreativeTab(tab);
		cloakBlue = new ItemRpgArmor(5822, 2).setFull3D().setIconIndex(3 + 16).setItemName("cloaksaint").setCreativeTab(tab);
		cloakSub = new ItemRpgArmor(5823, 2).setFull3D().setIconIndex(3 + 16).setItemName("cloakSub").setCreativeTab(tab);

		LanguageRegistry.addName(cloakRed, "Red Cape");
		LanguageRegistry.addName(cloakYellow, "Yellow Cape");
		LanguageRegistry.addName(cloakGreen, "Green Cape");
		LanguageRegistry.addName(cloakBlue, "Blue Cape");
		LanguageRegistry.addName(cloakSub, "Subaraki's Cape");

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

		LanguageRegistry.addName(shieldwood, "Wooden Shield");
		LanguageRegistry.addName(shieldiron, "Iron Shield");
		LanguageRegistry.addName(shieldgold, "Golden Shield");

		LanguageRegistry.addName(cloak, "Cloak");
		LanguageRegistry.addName(cloakI, "Invisibility Cloak");

		LanguageRegistry.addName(this.magehood, "Mage Hood");
		LanguageRegistry.addName(this.magegown, "Mage Gown");
		LanguageRegistry.addName(this.magepants, "Mage Under Gown");
		LanguageRegistry.addName(this.mageboots, "Mage Shoes");

		LanguageRegistry.addName(this.archerhood, "Archer Hood");
		LanguageRegistry.addName(this.archerchest, "Archer Chest");
		LanguageRegistry.addName(this.archerpants, "Archer Leggings");
		LanguageRegistry.addName(this.archerboots, "Archer Boots");

		LanguageRegistry.addName(this.ramboband, "Berserker's Head Protection");
		LanguageRegistry.addName(this.rambobody, "Berserker's Body Protection");
		LanguageRegistry.addName(this.rambolegs, "Berserker's Leg Protection");
		LanguageRegistry.addName(this.rambofeet, "Berserker's Feet Protection");

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

		MinecraftForge.addGrassSeed(new ItemStack(rageSeed, 1), 1);

		//SKINS
		GameRegistry.addRecipe(new ItemStack(animalskin, 1), new Object[] { "WWW", "WLW", "WWW", 'W', new ItemStack(Block.cloth, 1, 12), 'L', Item.leather });
		GameRegistry.addShapelessRecipe(new ItemStack(tanHide, 1), new Object[] { Item.leather, Item.silk, Item.silk, Item.silk, Item.silk });
		GameRegistry.addRecipe(new ItemStack(magecloth, 1), new Object[] { "WWW", "WLW", "WWW", 'W', new ItemStack(Item.dyePowder, 1, 4), 'L', Item.leather });
		//WEAPONRY
		GameRegistry.addRecipe(new ItemStack(elfbow, 1), new Object[] { "EPP", "P S", "PS ", 'E', Item.emerald, 'S', Item.silk, 'P', new ItemStack(Block.wood, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(wand, 1), new Object[] { "GGG", "GLG", "GGG", 'L', Block.blockLapis, 'G', Item.ingotGold });
		GameRegistry.addRecipe(new ItemStack(staf, 1), new Object[] { " ML", " SM", "S  ", 'M', Item.speckledMelon, 'S', Item.stick, 'L', new ItemStack(Item.dyePowder, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(claymore, 1), new Object[] { " S ", " S ", "LIL", 'I', Item.ingotGold, 'S', Block.stone, 'L', Item.leather });
		GameRegistry.addRecipe(new ItemStack(hammer, 1), new Object[] { "SSS", "LI ", "LI ", 'I', Item.ingotIron, 'S', Block.blockSteel, 'L', Item.leather });

		//SHIELDS
		GameRegistry.addRecipe(new ItemStack(shieldwood, 1), new Object[] { "WWW", "WBW", " W ", 'W', Block.planks, 'B', Block.wood });
		GameRegistry.addRecipe(new ItemStack(shieldiron, 1), new Object[] { "WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.blockSteel });
		GameRegistry.addRecipe(new ItemStack(shieldgold, 1), new Object[] { "WWW", "WBW", " W ", 'W', Item.ingotGold, 'B', Block.blockGold });
		//NECKLACE
		GameRegistry.addRecipe(new ItemStack(neckgold, 1), new Object[] { " SS", "  S", "I  ", 'S', Item.silk, 'I', Item.ingotGold });
		GameRegistry.addRecipe(new ItemStack(neckdia, 1), new Object[] { "e", "d", 'd', neckgold, 'e', Item.diamond });
		GameRegistry.addRecipe(new ItemStack(neckem, 1), new Object[] { "e", "d", 'd', neckgold, 'e', Item.emerald });
		GameRegistry.addRecipe(new ItemStack(necklap, 1), new Object[] { "e", "d", 'd', neckgold, 'e', new ItemStack(Item.dyePowder, 1, 4) });
		//RINGS
		GameRegistry.addRecipe(new ItemStack(ringgold, 1), new Object[] { "II ", "I I", "III", 'I', Item.ingotGold });
		GameRegistry.addRecipe(new ItemStack(ringdia, 1), new Object[] { "e", "d", 'd', ringgold, 'e', Item.diamond });
		GameRegistry.addRecipe(new ItemStack(ringem, 1), new Object[] { "e", "d", 'd', ringgold, 'e', Item.emerald });
		GameRegistry.addRecipe(new ItemStack(ringlap, 1), new Object[] { "e", "d", 'd', ringgold, 'e', new ItemStack(Item.dyePowder, 1, 4) });
		//GLOVES
		GameRegistry.addRecipe(new ItemStack(glovesbutter, 1), new Object[] { " II", "LII", "IL ", 'I', Item.ingotGold, 'L', Item.leather });
		GameRegistry.addRecipe(new ItemStack(glovesdia, 1), new Object[] { "e", "d", 'd', glovesbutter, 'e', Item.diamond });
		GameRegistry.addRecipe(new ItemStack(glovesem, 1), new Object[] { "e", "d", 'd', glovesbutter, 'e', Item.emerald });
		GameRegistry.addRecipe(new ItemStack(gloveslap, 1), new Object[] { "e", "d", 'd', glovesbutter, 'e', new ItemStack(Item.dyePowder, 1, 4) });
		//CLOAK
		GameRegistry.addRecipe(new ItemStack(cloak, 1), new Object[] { "SS", "II", "II", 'I', Block.cloth, 'S', Item.silk });
		GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.potion, 1, 8206) });
		GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.potion, 1, 8270) });

		GameRegistry.addRecipe(new ItemStack(cloakRed, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(cloakYellow, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 11)});
		GameRegistry.addRecipe(new ItemStack(cloakGreen, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(cloakBlue, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 12) });
		GameRegistry.addRecipe(new ItemStack(cloakSub, 1), new Object[] { "PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 0) });

		//MageBook
		GameRegistry.addShapelessRecipe(new ItemStack(wizardBook, 1), new Object[] { magecloth, Item.paper, Item.paper, Item.paper });

		//ARMOR
		recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" }, { "X X", "X X" } };
		recipeItems = new Object[][] { { animalskin, tanHide, magecloth }, { ramboband, archerhood, magehood },
				{ rambobody, archerchest, magegown }, { rambolegs, archerpants, magepants },
				{ rambofeet, archerboots, mageboots } };

		for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2)
		{
			Object var3 = this.recipeItems[0][var2];

			for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4)
			{
				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[] { this.recipePatterns[var4], 'X', var3 });
			}
		}

		if (render3DClaymore == true)
		{
			MinecraftForgeClient.registerItemRenderer(AARpgBaseClass.claymore.itemID, (IItemRenderer) new ClaymoreRenderer());
		}
		if (render3DHammer == true)
		{
			MinecraftForgeClient.registerItemRenderer(AARpgBaseClass.hammer.itemID, (IItemRenderer) new HammerRender());
		}
		if (render3DSoulSphere == true)
		{
			MinecraftForgeClient.registerItemRenderer(AARpgBaseClass.wand.itemID, (IItemRenderer) new SoulSphereRender());
		}
		if (render3DStaff == true)
		{
			MinecraftForgeClient.registerItemRenderer(AARpgBaseClass.staf.itemID, (IItemRenderer) new StafRender());
		}
		if (render3DBow == true)
		{
			MinecraftForgeClient.registerItemRenderer(AARpgBaseClass.elfbow.itemID, (IItemRenderer) new BowRender());
		}

		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "RPGInv";
	}
	
	public static class ITEMTYPE{
        public static final int NECKLACE = 0;
        public static final int SHIELD = 1;
        public static final int CLOAK= 2;
        public static final int GLOVES = 3;
        public static final int RING = 4;
    }
	
	
}
