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
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.TEMold;
import rpgInventory.entity.EntityHellArrow;
import rpgInventory.gui.RpgInventoryTab;
import rpgInventory.handlers.ClientTickHandler;
import rpgInventory.handlers.CommonTickHandler;
import rpgInventory.handlers.GuiHandler;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.handlers.RPGKeyHandler;
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
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
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

	public static MoldRecipes recipes;

	public static final String name = "rpginventorymod";
	public static final String ID = "Rpg Inventory";

	protected static final String version = "1.7.2";
	public static String CLASSARCHER = "archer";
	public static String CLASSBERSERKER = "berserker";

	public static String CLASSMAGE = "basicMage";
	public static String CLASSARCHERSHIELD = "shieldedArcher";
	public static String CLASSBERSERKERSHIELD = "shieldedBerserker";

	public static String CLASSMAGESHIELD = "shieldedBasicMage";

	public static String playerClass = "none";
	public static mod_RpgInventory instance;

	// public static final PacketPipeline17 PIPELINE = new PacketPipeline17();
	public static FMLEventChannel Channel;

	@SidedProxy(serverSide = "rpgInventory.handlers.proxy.CommonProxy", clientSide = "rpgInventory.handlers.proxy.ClientProxy")
	public static CommonProxy proxy;

	public static Item
	/* ====jewels==== */
	neckgold, neckdia, neckem, necklap, glovesbutter, glovesdia, glovesem,
			gloveslap, ringgold, ringdia, ringem, ringlap,
			/* ====cloaks==== */
			cloak, cloakI, cloakSub, cloakRed, cloakYellow, cloakGreen,
			cloakBlue,
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

		recipes = new MoldRecipes();

		// PIPELINE.initialise();
		Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("RpgInv");
		proxy.load();

		setDonators();
		// GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, name));

		developers.add("unjustice");
		developers.add("artix_all_mighty");
		developers.add("rich1051414");
		developers.add("darkhax");

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

		// DONE ?
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		MinecraftForge.EVENT_BUS.register(new CommonTickHandler());
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());

		FMLCommonHandler.instance().bus().register(new RPGKeyHandler());

		MinecraftForge.EVENT_BUS.register(new RPGEventHooks());

		EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow",
				getUniqueID(), this, 250, 1, true);

		ClientProxy.renderHandler();

	}

	public String playerClass() {
		return playerClass;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		// PIPELINE.postInitialise();

		proxy.registerLate();
		// All mods should be initialized now, check what potion effects are
		// installed
		// and list the bad ones for later reference.
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// loadConfig(event.getSuggestedConfigurationFile());

		// NOTHING BEFORE THE GOD DAMN TAB !
		// any items that need to be in it, put in it BEFORE the tab exists will
		// not be in
		tab = new RpgInventoryTab(CreativeTabs.getNextID(),
				"Rpg Inventory Jewelery");

		neckgold = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, "",
				"subaraki:jewels/NeckGold.png").setUnlocalizedName("neckGold")
				.setCreativeTab(tab);
		neckdia = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, "", "subaraki:jewels/NeckDia.png")
				.setUnlocalizedName("neckDia").setCreativeTab(tab);
		neckem = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, "", "subaraki:jewels/NeckEm.png")
				.setUnlocalizedName("neckEm").setCreativeTab(tab);
		necklap = new ItemRpgInvArmor(ItemRpgInvArmor.NECKLACE, -1, "", "subaraki:jewels/NeckLap.png")
				.setUnlocalizedName("neckLap").setCreativeTab(tab);

		ringgold = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, "", "").setUnlocalizedName(
				"ringGold").setCreativeTab(tab);
		ringdia = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, "", "").setUnlocalizedName(
				"ringDia").setCreativeTab(tab);
		ringem = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, "", "")
				.setUnlocalizedName("ringEm").setCreativeTab(tab);
		ringlap = new ItemRpgInvArmor(ItemRpgInvArmor.RING, -1, "", "").setUnlocalizedName(
				"ringLap").setCreativeTab(tab);

		glovesbutter = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, "",
				"subaraki:jewels/Glove.png").setUnlocalizedName("gloveGold")
				.setCreativeTab(tab);
		glovesdia = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, "",
				"subaraki:jewels/GloveDia.png").setUnlocalizedName("gloveDia")
				.setCreativeTab(tab);
		glovesem = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, "", "subaraki:jewels/GloveEm.png")
				.setUnlocalizedName("gloveEm").setCreativeTab(tab);
		gloveslap = new ItemRpgInvArmor(ItemRpgInvArmor.GLOVES, -1, "",
				"subaraki:jewels/GloveLap.png").setUnlocalizedName("gloveLap")
				.setCreativeTab(tab);

		cloak = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "", "subaraki:capes/GreyCape.png")
				.setFull3D().setUnlocalizedName("capeGrey").setCreativeTab(tab);
		cloakI = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "", "subaraki:capes/GreyCape.png")
				.setFull3D().setUnlocalizedName("i.capeGrey")
				.setCreativeTab(tab);

		cloakRed = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "", "subaraki:capes/RedCape.png")
				.setFull3D().setUnlocalizedName("r.capeGrey")
				.setCreativeTab(tab);

		cloakYellow = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "",
				"subaraki:capes/GoldCape.png").setFull3D()
				.setUnlocalizedName("y.capeGrey").setCreativeTab(tab);

		cloakGreen = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "",
				"subaraki:capes/GreenCape.png").setFull3D()
				.setUnlocalizedName("g.capeGrey").setCreativeTab(tab);
		cloakBlue = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "", "subaraki:capes/SkyCape.png")
				.setFull3D().setUnlocalizedName("b.capeGrey")
				.setCreativeTab(tab);
		cloakSub = new ItemRpgInvArmor(ItemRpgInvArmor.CLOAK, -1, "", "subaraki:capes/BlaCape.png")
				.setFull3D().setUnlocalizedName("s.capeGrey")
				.setCreativeTab(tab);

		colmold = new ItemMold().setUnlocalizedName("moldNeck").setCreativeTab(
				tab);
		ringmold = new ItemMold().setUnlocalizedName("moldRing")
				.setCreativeTab(tab);
		wantmold = new ItemMold().setUnlocalizedName("moldGlove")
				.setCreativeTab(tab);

		forgeBlock = new BlockForge(Material.rock).setHardness(5f)
				.setBlockName("MoldForge").setCreativeTab(tab);
		GameRegistry.registerBlock(forgeBlock, "MoldForge");

		allItems = new Item[] { neckgold, neckdia, neckem, necklap,
				glovesbutter, glovesdia, glovesem, gloveslap, ringgold,
				ringdia, ringem, ringlap, cloak, cloakI, cloakSub, cloakRed,
				cloakYellow, cloakGreen, cloakBlue, colmold, ringmold, wantmold };

		// DONE
		for (int i = 0; i < allItems.length; i++)
			if (allItems[i] != null) {
				String itemName = allItems[i].getUnlocalizedName().substring(
						allItems[i].getUnlocalizedName().indexOf(".") + 1);

				String itemNameCropped = itemName.substring(itemName
						.indexOf(".") + 1);

				allItems[i].setTextureName(mod_RpgInventory.name + ":"
						+ itemNameCropped);

				GameRegistry
						.registerItem(allItems[i],
								allItems[i].getUnlocalizedName(),
								mod_RpgInventory.name);

			} else
				System.out.println("Item is null !" + i);
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
		// TODO get commands back

		// CommandHandler commandManager = (CommandHandler) event.getServer()
		// .getCommandManager();
		// commandManager
		// .registerCommand(new rpgInventory.handlers.CommandPanel());
		// rpgInventory.handlers.CommandPanel.init();
	}

	private void setDonators() {
		try {
			URL url = new URL(
					"http://www.dnstechpack.com/user/subaraki/rpgcapes/donatorList.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str;
			while ((str = in.readLine()) != null)
				donators.add(str);
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
