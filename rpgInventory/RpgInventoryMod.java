package rpgInventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import rpgInventory.block.BlockForge;
import rpgInventory.block.te.TEMold;
import rpgInventory.gui.RpgInventoryTab;
import rpgInventory.handlers.GuiHandler;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.handlers.ServerTickHandler;
import rpgInventory.handlers.packets.ServerPacketHandler;
import rpgInventory.handlers.proxy.ClientProxy;
import rpgInventory.handlers.proxy.CommonProxy;
import rpgInventory.item.ItemMold;
import rpgInventory.item.armor.ItemRpgInvArmor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = RpgInventoryMod.name, name = RpgInventoryMod.ID, version = RpgInventoryMod.version)
public class RpgInventoryMod {

	public static class ITEMTYPE {

		public static final int NECKLACE = 0;
		public static final int SHIELD = 1;
		public static final int CLOAK = 2;
		public static final int GLOVES = 3;
		public static final int RING = 4;
		public static final int CRYSTAL = 5;
	}

	public static final String name = "rpginventorymod";
	public static final String ID = "Rpg Inventory";

	protected static final String version = "1.7.2";

	public static String playerClass = "none";
	public static RpgInventoryMod instance;

	public static FMLEventChannel Channel;

	@SidedProxy(serverSide = "rpgInventory.handlers.proxy.CommonProxy", clientSide = "rpgInventory.handlers.proxy.ClientProxy")
	public static CommonProxy proxy;

	public static Item
	/* ====jewels==== */
	neckgold, neckdia, neckem, necklap, glovesbutter, glovesdia, glovesem,
	gloveslap, ringgold, ringdia, ringem, ringlap,
	/* ====cloaks==== */
	cloakWhite,
	cloakBlack,
	cloakRed,
	cloakGreen,
	cloakBrown,
	cloakBlue,
	cloakPurple,
	cloakCyan,
	cloakSilver,
	cloakGray,
	cloakPink,
	cloakLime,
	cloakYellow,
	cloakLightblue,
	cloakMagenta,
	cloakOrange,
	cloakI,

	/* ====molds==== */
	colmold, ringmold, wantmold;

	public Item[] allItems;

	public static Block forgeBlock;
	// Die bitches.
	public static List<String> developers = new ArrayList<String>();

	public static List<Integer> rpvInvIDs = new ArrayList();

	private static int uniqueID = 0;

	public static CreativeTabs tab;

	public static ArrayList<String> donators = new ArrayList<String>();

	public static final String channelName = "RpgInv";

	public RpgInventoryMod() {
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

		ToLoad.loadMoldRecipes();
		ToLoad.loadGameRecipes();

		Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("RpgInv");
		proxy.load();
		
		RpgInventoryMod.Channel.register(new ServerPacketHandler());

		setDonators();

		developers.add("unjustice");
		developers.add("artix_all_mighty");
		developers.add("rich1051414");

		proxy.registerRenderInformation();

		addChestLoot(new ItemStack(RpgInventoryMod.colmold), 1, 1, 15,"Necklace Mold");
		addChestLoot(new ItemStack(RpgInventoryMod.ringmold), 1, 1, 15,"Ring Mold");
		addChestLoot(new ItemStack(RpgInventoryMod.wantmold), 1, 1, 15,"Gloves Mold");
		addRareLoot(new ItemStack(RpgInventoryMod.cloakI), 1, 1, 1,	"Invisibility Cloak");

		addChestLoot(new ItemStack(Items.gold_ingot), 1, 3, 15, "More Golden Ingots");
		addChestLoot(new ItemStack(Blocks.gold_block), 1, 1, 8, "Golden Blocks");

		GameRegistry.registerTileEntity(TEMold.class, "temold");

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new RPGEventHooks());
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
		
		ClientProxy.renderHandler();

	}

	public String playerClass() {
		return playerClass;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		proxy.registerLate();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		// NOTHING BEFORE THE GOD DAMN TAB !
		// any items that need to be in it, put in it BEFORE the tab exists will
		// not be in
		tab = new RpgInventoryTab(CreativeTabs.getNextID(),
				"Rpg Inventory Jewelery");

		neckgold = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, 17,"rpginventorymod:jewels/NeckGold.png").setUnlocalizedName("neckGold").setCreativeTab(tab);
		neckdia = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, 17,"rpginventorymod:jewels/NeckDia.png").setUnlocalizedName("neckDia").setCreativeTab(tab);
		neckem = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, 17,"rpginventorymod:jewels/NeckEm.png").setUnlocalizedName("neckEm").setCreativeTab(tab);
		necklap = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, 17,"rpginventorymod:jewels/NeckLap.png").setUnlocalizedName("neckLap").setCreativeTab(tab);

		ringgold = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, 17, "").setUnlocalizedName("ringGold").setCreativeTab(tab);
		ringdia = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, 17, "").setUnlocalizedName("ringDia").setCreativeTab(tab);
		ringem = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, 17, "").setUnlocalizedName("ringEm").setCreativeTab(tab);
		ringlap = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, 17, "").setUnlocalizedName("ringLap").setCreativeTab(tab);

		glovesbutter = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, 17,"rpginventorymod:jewels/Glove.png").setUnlocalizedName("gloveGold").setCreativeTab(tab);
		glovesdia = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, 17,"rpginventorymod:jewels/GloveDia.png").setUnlocalizedName("gloveDia").setCreativeTab(tab);
		glovesem = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, 17,"rpginventorymod:jewels/GloveEm.png").setUnlocalizedName("gloveEm").setCreativeTab(tab);
		gloveslap = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, 17,"rpginventorymod:jewels/GloveLap.png").setUnlocalizedName("gloveLap").setCreativeTab(tab);


		cloakI = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 17,"subaraki:capes/GreyCape.png").setFull3D().setUnlocalizedName("i.capeGrey").setCreativeTab(tab);

		cloakBlack 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 0,	"textures/blocks/wool_colored_black.png").setFull3D().setUnlocalizedName("bla.capeGrey").setCreativeTab(tab);
		cloakRed 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 1,	"textures/blocks/wool_colored_red.png").setFull3D().setUnlocalizedName("red.capeGrey").setCreativeTab(tab);
		cloakGreen 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 2,	"textures/blocks/wool_colored_green.png").setFull3D().setUnlocalizedName("gre.capeGrey").setCreativeTab(tab);
		cloakBrown 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 3,	"textures/blocks/wool_colored_brown.png").setFull3D().setUnlocalizedName("bro.capeGrey").setCreativeTab(tab);
		cloakBlue 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 4,	"textures/blocks/wool_colored_blue.png").setFull3D().setUnlocalizedName("ble.capeGrey").setCreativeTab(tab);
		cloakPurple = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 5,	"textures/blocks/wool_colored_purple.png").setFull3D().setUnlocalizedName("pur.capeGrey").setCreativeTab(tab);
		cloakCyan 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 6,	"textures/blocks/wool_colored_cyan.png").setFull3D().setUnlocalizedName("cya.capeGrey").setCreativeTab(tab);
		cloakSilver = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 7,	"textures/blocks/wool_colored_silver.png").setFull3D().setUnlocalizedName("sil.capeGrey").setCreativeTab(tab);
		cloakGray 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 8,	"textures/blocks/wool_colored_gray.png").setFull3D().setUnlocalizedName("gra.capeGrey").setCreativeTab(tab);
		cloakPink	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 9,	"textures/blocks/wool_colored_pink.png").setFull3D().setUnlocalizedName("pin.capeGrey").setCreativeTab(tab);
		cloakLime 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 10,	"textures/blocks/wool_colored_lime.png").setFull3D().setUnlocalizedName("lim.capeGrey").setCreativeTab(tab);
		cloakYellow = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 11,	"textures/blocks/wool_colored_yellow.png").setFull3D().setUnlocalizedName("yel.capeGrey").setCreativeTab(tab);
		cloakLightblue 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 12,	"textures/blocks/wool_colored_light_blue.png").setFull3D().setUnlocalizedName("lig.capeGrey").setCreativeTab(tab);
		cloakMagenta 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 13,	"textures/blocks/wool_colored_magenta.png").setFull3D().setUnlocalizedName("mag.capeGrey").setCreativeTab(tab);
		cloakOrange 	= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 14,	"textures/blocks/wool_colored_orange.png").setFull3D().setUnlocalizedName("ora.capeGrey").setCreativeTab(tab);
		cloakWhite 		= new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, 15,	"textures/blocks/wool_colored_white.png").setFull3D().setUnlocalizedName("whi.capeGrey").setCreativeTab(tab);

		colmold = new ItemMold().setUnlocalizedName("moldNeck").setCreativeTab(tab);
		ringmold = new ItemMold().setUnlocalizedName("moldRing").setCreativeTab(tab);
		wantmold = new ItemMold().setUnlocalizedName("moldGlove").setCreativeTab(tab);

		forgeBlock = new BlockForge(Material.rock).setHardness(5f).setBlockName("MoldForge").setCreativeTab(tab);
		GameRegistry.registerBlock(forgeBlock, "MoldForge");

		allItems = new Item[] { neckgold, neckdia, neckem, necklap,
				glovesbutter, glovesdia, glovesem, gloveslap, ringgold,
				ringdia, ringem, ringlap, colmold, ringmold, wantmold,
				cloakWhite, cloakBlack,cloakRed,cloakGreen,cloakBrown,cloakBlue,cloakPurple,
				cloakCyan,cloakSilver,cloakGray,cloakPink,cloakLime,cloakYellow,cloakLightblue,
				cloakMagenta,cloakOrange,cloakI,
		};

		for (int i = 0; i < allItems.length; i++) {
			if (allItems[i] != null) {
				String itemName = allItems[i].getUnlocalizedName().substring(allItems[i].getUnlocalizedName().indexOf(".") + 1);

				String itemNameCropped = itemName.substring(itemName.indexOf(".") + 1);

				allItems[i].setTextureName(RpgInventoryMod.name + ":"+ itemNameCropped);

				GameRegistry.registerItem(allItems[i],allItems[i].getUnlocalizedName(),RpgInventoryMod.name);

			} else {
				System.out.println("Item is null !" + i);
			}
		}
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
