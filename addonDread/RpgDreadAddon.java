package addonDread;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import rpgInventory.RpgInventoryMod;
import rpgInventory.config.RpgConfig;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.utils.RpgUtility;
import addonDread.items.ItemGrandSword;
import addonDread.items.ItemNecroArmor;
import addonDread.items.ItemNecroPaladinMats;
import addonDread.items.ItemNecroSkull;
import addonDread.items.ItemPaladinArmor;
import addonDread.items.ItemRpgInvArmorPlus;
import addonDread.minions.EntityMinionS;
import addonDread.minions.EntityMinionZ;
import addonDread.packets.DreadServerPacketHandler;
import addonDread.richutils.potions.DecomposePotion;
import addonDread.richutils.potions.MasochismPotion;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RPGPlusPlus", name = "Necro Paladin Addon", version = "RpgInv8.4", dependencies = "required-after:rpginventorymod")
// @NetworkMod(clientSideRequired = true, serverSideRequired = false,
// clientPacketHandlerSpec = @SidedPacketHandler(channels = { "RpgPlusPlus" },
// packetHandler = RpgPlusPacketHandler.class), serverPacketHandlerSpec =
// @SidedPacketHandler(channels = { "RpgPlusPlus" }, packetHandler =
// RpgPlusPacketHandler.class))
public class RpgDreadAddon {

	public static String CLASSNECRO = "necro";
	public static String CLASSNECROSHIELD = "shieldedNecro";
	public static String CLASSPALADIN = "paladin";
	public static String CLASSPALADINSHIELD = "shieldedPaladin";

	public static Potion decomposePotion;
	public static Potion masochismPotion;

	private String[][] recipePatterns;
	private Object[][] recipeItems;

	public final static ArmorMaterial necroArmor = EnumHelper.addArmorMaterial(
			"necromancer", 20, new int[] { 2, 5, 1, 1 }, 5); // use
	// of
	// Souls
	public final static ArmorMaterial paladin = EnumHelper.addArmorMaterial(
			"paladin", 20, new int[] { 4, 7, 2, 1 }, 5); // use
	// of
	// Steel

	public ToolMaterial NecroToolMaterial = EnumHelper.addToolMaterial("souls",
			0, 1024, 5F, 1, 0);
	public ToolMaterial PalaToolMaterial = EnumHelper.addToolMaterial("steel",
			0, 1024, 5F, 1, 0);

	public static Item allItems[];
	public static Item
	/* ====shields==== */
	pala_shield, necro_shield,
	/* ====weapons==== */
	pala_weapon, necro_weapon,
	/* ====armor==== */
	necroHood, necroChestplate, necroLeggings, necroBoots, palaHelm, palaChest,
			palaLeggings, palaBoots,
			/* ====leathers/skins==== */
			necro_skin, pala_steel;

	@SidedProxy(serverSide = "addonDread.CommonProxyRpgplus", clientSide = "addonDread.ClientProxyRpgPlus")
	public static CommonProxyRpgplus proxy;

	public static FMLEventChannel Channel;

	public static CreativeTabs tab;

	@EventHandler
	public void load(FMLInitializationEvent event) {

		FMLLog.info(
				"Rpg++ Necromancer and Paladin is installed. Renderers can be Used",
				1);


		GameRegistry.addRecipe(new ItemStack(necro_skin, 1), new Object[] {
				"BWB", "WLW", "BWB", 'W', Items.spider_eye, 'B', Items.bone,
				'L', Items.leather });
		GameRegistry.addRecipe(new ItemStack(pala_steel, 1),
				new Object[] { "GGG", "BIB", "GGG", 'G', Items.gold_ingot, 'B',
						(new ItemStack(Items.potionitem, 1, 0)), 'I',
						Items.iron_ingot });
		GameRegistry.addRecipe(new ItemStack(necro_shield, 1), new Object[] {
				"WWW", "WBW", " W ", 'W', necro_skin, 'B',
				new ItemStack(Items.skull, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(pala_shield, 1), new Object[] {
				"WWW", "WBW", " W ", 'W', pala_steel, 'B', Blocks.iron_block });
		GameRegistry.addRecipe(new ItemStack(necro_weapon, 1), new Object[] {
				"WWW", "WBW", "WWW", 'W', Items.bone, 'B',
				new ItemStack(Items.skull, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(pala_weapon, 1), new Object[] {
				"S", "S", "G", 'S', pala_steel, 'G', Items.gold_ingot });

		recipePatterns = new String[][] { { "XXX", "X X" },
				{ "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" },
				{ "X X", "X X" } };
		recipeItems = new Object[][] { { pala_steel, necro_skin },
				{ palaHelm, necroHood }, { palaChest, necroChestplate },
				{ palaLeggings, necroLeggings }, { palaBoots, necroBoots } };

		for (int var2 = 0; var2 < recipeItems[0].length; ++var2) {
			Object var3 = recipeItems[0][var2];

			for (int var4 = 0; var4 < (this.recipeItems.length - 1); ++var4) {
				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[] {
						this.recipePatterns[var4], 'X', var3 });
			}
		}

		necroHood.setCreativeTab(tab);
		necroChestplate.setCreativeTab(tab);
		necroLeggings.setCreativeTab(tab);
		necroBoots.setCreativeTab(tab);

		palaHelm.setCreativeTab(tab);
		palaChest.setCreativeTab(tab);
		palaLeggings.setCreativeTab(tab);
		palaBoots.setCreativeTab(tab);

		necro_shield.setCreativeTab(tab);
		necro_weapon.setCreativeTab(tab);
		pala_shield.setCreativeTab(tab);
		pala_weapon.setCreativeTab(tab);
		necro_skin.setCreativeTab(tab);
		pala_steel.setCreativeTab(tab);

		MinecraftForge.EVENT_BUS.register(new NecroPaladinEvents());
		FMLCommonHandler.instance().bus().register(new CommonTickHandlerRpgPlus());
		MinecraftForge.EVENT_BUS.register(new DreadEventHooks());
		// hack to increase the number of potion types allowed

		if (Potion.potionTypes.length < 256) {
			boolean found = false;
			Field fallbackfield = null;
			Potion[] potionTypes = null;
			for (Field f : Potion.class.getDeclaredFields())
				try {
					if ((fallbackfield != null)
							&& (f.getType() == Potion[].class))
						fallbackfield = f;
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

		for (int pos = 32; pos < Potion.potionTypes.length; pos++) {
			if (Potion.potionTypes[pos] == null)
				if (decomposePotion == null) {
					decomposePotion = new DecomposePotion(pos);
					Potion.potionTypes[pos] = decomposePotion;
				} else if (masochismPotion == null) {
					masochismPotion = new MasochismPotion(pos);
					Potion.potionTypes[pos] = masochismPotion;
				} else
					break;

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
		
		Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("DreadPacket");
		RpgUtility.registerAbilityWeapon(necro_weapon);
		RpgUtility.registerAbilityWeapon(pala_weapon);
		RpgDreadAddon.Channel.register(new DreadServerPacketHandler());

		
	}

	@EventHandler
	public void post(FMLPostInitializationEvent evt) {

		proxy.registerRenderInformation();

		EntityRegistry.registerGlobalEntityID(EntityMinionS.class,
				"skeletonMinion", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityMinionZ.class,
				"zombieMinion", EntityRegistry.findGlobalUniqueEntityId());
		LanguageRegistry.instance().addStringLocalization(
				"entity.EntityMinionS.name", "Skeleton Minion");
		LanguageRegistry.instance().addStringLocalization(
				"entity.EntityMinionZ.name", "Zombie Minion");

		/**
		 * TODO TickRegistry.registerTickHandler(new CommonTickHandlerRpgPlus(),
		 * Side.SERVER); TickRegistry.registerTickHandler(new
		 * CommonTickHandlerRpgPlus(), Side.CLIENT);
		 */

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		tab = new PlusTab(CreativeTabs.getNextID(), "Necromancer Paladin Addon");

		necroHood = new ItemNecroArmor(necroArmor, 4, 0)
				.setUnlocalizedName("necro1");
		necroChestplate = new ItemNecroArmor(necroArmor, 4, 1)
				.setUnlocalizedName("necro2");
		necroLeggings = new ItemNecroArmor(necroArmor, 4, 2)
				.setUnlocalizedName("necro3");
		necroBoots = new ItemNecroArmor(necroArmor, 4, 3)
				.setUnlocalizedName("necro4");

		palaHelm = new ItemPaladinArmor(paladin, 4, 0)
				.setUnlocalizedName("paladin1");
		palaChest = new ItemPaladinArmor(paladin, 4, 1)
				.setUnlocalizedName("paladin2");
		palaLeggings = new ItemPaladinArmor(paladin, 4, 2)
				.setUnlocalizedName("paladin3");
		palaBoots = new ItemPaladinArmor(paladin, 4, 3)
				.setUnlocalizedName("paladin4");

		necro_shield = new ItemRpgInvArmorPlus(1, 250, "necro",
				"subaraki:jewels/NecroShield.png")
				.setUnlocalizedName("shieldNecro");
		necro_weapon = new ItemNecroSkull(NecroToolMaterial).setFull3D()
				.setUnlocalizedName("Skull");

		pala_shield = new ItemRpgInvArmorPlus(1, 450, "pala",
				"subaraki:jewels/PaladinShield.png")
				.setUnlocalizedName("shieldPaladin");
		pala_weapon = new ItemGrandSword(RpgConfig.instance.pala_weaponID,
				PalaToolMaterial).setFull3D()
				.setUnlocalizedName("paladinPride");

		necro_skin = new ItemNecroPaladinMats(RpgConfig.instance.necro_skinID)
				.setUnlocalizedName("n.leather");
		pala_steel = new ItemNecroPaladinMats(RpgConfig.instance.pala_steelID)
				.setUnlocalizedName("p.iron_ingot");

		allItems = new Item[] { pala_shield, necro_shield, pala_weapon,
				necro_weapon, necroHood, necroChestplate, necroLeggings,
				necroBoots, palaHelm, palaChest, palaLeggings, palaBoots,
				necro_skin, pala_steel };

		for (int i = 0; i < allItems.length; i++)
			if (allItems[i] != null) {

				String itemName = allItems[i].getUnlocalizedName().substring(
						allItems[i].getUnlocalizedName().indexOf(".") + 1);

				String itemNameCropped = itemName.substring(itemName
						.indexOf(".") + 1);

				if ((allItems[i] == necro_skin) || (allItems[i] == pala_steel))
					allItems[i].setTextureName("minecraft:" + itemNameCropped);
				else
					allItems[i].setTextureName(RpgInventoryMod.name + ":"
							+ itemNameCropped);

				GameRegistry
						.registerItem(allItems[i],
								allItems[i].getUnlocalizedName(),
								RpgInventoryMod.name);
			} else
				System.out.println("Item is null !" + i);
	}
}
