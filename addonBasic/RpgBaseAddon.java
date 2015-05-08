package addonBasic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import rpgInventory.RpgInventoryMod;
import rpgInventory.utils.RpgUtility;
import addonBasic.items.ItemRpg;
import addonBasic.items.armor.ItemAddonShields;
import addonBasic.items.armor.ItemArcherArmor;
import addonBasic.items.armor.ItemBerserkerArmor;
import addonBasic.items.armor.ItemMageArmor;
import addonBasic.items.weapons.ItemArcherBow;
import addonBasic.items.weapons.ItemHammer;
import addonBasic.items.weapons.ItemLunarStaff;
import addonBasic.items.weapons.ItemSoulSphere;
import addonBasic.packets.PacketArcher;
import addonBasic.packets.PacketArcher.HandlerPacketArcher;
import addonBasic.packets.PacketBerserker;
import addonBasic.packets.PacketBerserker.HandlerPacketBerserker;
import addonBasic.packets.PacketMageHeal;
import addonBasic.packets.PacketMageHeal.HandlerPacketMageHeal;
import addonBasic.packets.PacketMageVortex;
import addonBasic.packets.PacketMageVortex.HandlerPacketMageVortex;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = RpgBaseAddon.id, name = RpgBaseAddon.name, version = "RpgInv8.4", dependencies = "required-after:rpginventorymod")
public class RpgBaseAddon {

	private class AddonTab extends CreativeTabs {

		public AddonTab(int par1, String par2Str) {
			super(par1, par2Str);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return berserkerHood;
		}

		@Override
		public String getTranslatedTabLabel() {
			return this.getTabLabel();
		}

	}

	public static String CLASSARCHER = "archer";
	public static String CLASSBERSERKER = "berserker";
	public static String CLASSALCHEMIST = "basicalchemist";
	public static String CLASSARCHERSHIELD = "shieldedArcher";
	public static String CLASSBERSERKERSHIELD = "shieldedBerserker";
	public static String CLASSALCHEMISTSHIELD = "shieldedBasicalchemist";

	public static final String id = "RpgBase";
	public static final String name = "Berserker, alchemist and Archer Patch";

	public static CreativeTabs tab;
	public Item[] allItems;

	public static Item
	/* ====weapons==== */
	elfbow, hammer, soulSphere, lunarStaff,
	/* ====armor==== */
	alchemisthood, alchemistgown, alchemistpants, alchemistboots, archerhood, archerchest,
	archerpants, archerboots, berserkerHood, berserkerChest,
	berserkerLegs, berserkerBoots,
	/* ====leathers/skins==== */
	animalskin, tanHide, alchemistcloth,
	/* ====shields==== */
	archerShield, berserkerShield, talisman;

	private String[][] recipePatterns;
	private Object[][] recipeItems;

	public final ArmorMaterial alchemist = EnumHelper.addArmorMaterial("alchemist", 40,new int[] { 2, 2, 2, 1 }, 5);
	public final ArmorMaterial archer = EnumHelper.addArmorMaterial("archer",40, new int[] { 2, 3, 2, 2 }, 5);
	public final ArmorMaterial berserker = EnumHelper.addArmorMaterial("berserker", 40, new int[] { 2, 4, 3, 2 }, 5);

	ToolMaterial clay = EnumHelper.addToolMaterial("claymore", 0, 500, 5F, 4, 0);
	ToolMaterial lunarstaff = EnumHelper.addToolMaterial("lunarstaff", 0, 750, 0F, 0, 0);
	ToolMaterial rageBreaker = EnumHelper.addToolMaterial("RageBreaker", 0, 1024, 4F, 2, 0);

	public static final SimpleNetworkWrapper SNW = NetworkRegistry.INSTANCE.newSimpleChannel("BaseAddon");

	@SidedProxy(serverSide = "addonBasic.ServerProxy", clientSide = "addonBasic.ClientProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent evt) {

		proxy.registerRenderInformation();

		// SKINS
		GameRegistry.addRecipe(new ItemStack(animalskin, 1), new Object[] {
			"WWW", "WLW", "WWW", 'W', new ItemStack(Blocks.wool, 1, 12),
			'L', Items.leather });
		GameRegistry.addShapelessRecipe(new ItemStack(tanHide, 1),
				new Object[] { Items.leather, Items.string, Items.string,
			Items.string, Items.string });
		GameRegistry.addRecipe(new ItemStack(alchemistcloth, 1), new Object[] {
			"WWW", "WLW", "WWW", 'W', new ItemStack(Items.dye, 1, 4), 'L',
			Items.leather });
		// WEAPONRY
		GameRegistry.addRecipe(new ItemStack(elfbow, 1), new Object[] { "EPP",
			"P S", "PS ", 'E', Items.emerald, 'S', Items.string, 'P',
			new ItemStack(Blocks.log, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(soulSphere, 1), new Object[] { "GGG",
			"GLG", "GGG", 'L', Blocks.lapis_block, 'G', Items.gold_ingot });
		GameRegistry.addRecipe(new ItemStack(lunarStaff, 1), new Object[] { " ML",
			" SM", "S  ", 'M', Items.speckled_melon, 'S', Items.stick, 'L',
			new ItemStack(Items.dye, 1, 4) });
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

		// ARMOR
		recipePatterns = new String[][] { { "XXX", "X X" },
				{ "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" },
				{ "X X", "X X" } };
		recipeItems = new Object[][] { { animalskin, tanHide, alchemistcloth },
				{ berserkerHood, archerhood, alchemisthood },
				{ berserkerChest, archerchest, alchemistgown },
				{ berserkerLegs, archerpants, alchemistpants },
				{ berserkerBoots, archerboots, alchemistboots } };

		for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for (int var4 = 0; var4 < (this.recipeItems.length - 1); ++var4) {
				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[] {
					this.recipePatterns[var4], 'X', var3 });
			}
		}

		RpgUtility.registerAbilityWeapon(lunarStaff);
		RpgUtility.registerAbilityWeapon(hammer);
		RpgUtility.registerAbilityWeapon(elfbow);
		RpgUtility.registerAbilityWeapon(soulSphere);

		EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow",EntityRegistry.findGlobalUniqueEntityId(), this, 250, 1, true);

		FMLCommonHandler.instance().bus().register(new TickHandlerServer());
		MinecraftForge.EVENT_BUS.register(new BaseaddonEventHooks());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		SNW.registerMessage(HandlerPacketArcher.class, PacketArcher.class, 0, Side.SERVER);
		SNW.registerMessage(HandlerPacketMageVortex.class, PacketMageVortex.class, 1, Side.SERVER);
		SNW.registerMessage(HandlerPacketBerserker.class, PacketBerserker.class, 2, Side.SERVER);
		SNW.registerMessage(HandlerPacketMageHeal.class, PacketMageHeal.class, 3, Side.SERVER);
		
		tab = new AddonTab(CreativeTabs.getNextID(),"alchemist Archer Berserker Addon");

		archerShield = new ItemAddonShields(1, 200, "","subaraki:jewels/Shield1.png").setUnlocalizedName("shieldArcher").setCreativeTab(tab);
		berserkerShield = new ItemAddonShields(1, 450, "","subaraki:jewels/IronThorn.png").setUnlocalizedName("shieldBerserker").setCreativeTab(tab);
		talisman = new ItemAddonShields(1, 500, "","subaraki:jewels/alchemistShield.png").setUnlocalizedName("shieldalchemist").setCreativeTab(tab);

		alchemisthood = new ItemMageArmor(alchemist, 4, 0).setUnlocalizedName("mage1").setCreativeTab(tab);
		alchemistgown = new ItemMageArmor(alchemist, 4, 1).setUnlocalizedName("mage2").setCreativeTab(tab);
		alchemistpants = new ItemMageArmor(alchemist, 4, 2).setUnlocalizedName("mage3").setCreativeTab(tab);
		alchemistboots = new ItemMageArmor(alchemist, 4, 3).setUnlocalizedName("mage4").setCreativeTab(tab);

		archerhood = new ItemArcherArmor(archer, 4, 0).setUnlocalizedName("archer1").setCreativeTab(tab);
		archerchest = new ItemArcherArmor(archer, 4, 1).setUnlocalizedName("archer2").setCreativeTab(tab);
		archerpants = new ItemArcherArmor(archer, 4, 2).setUnlocalizedName("archer3").setCreativeTab(tab);
		archerboots = new ItemArcherArmor(archer, 4, 3).setUnlocalizedName("archer4").setCreativeTab(tab);

		berserkerHood = new ItemBerserkerArmor(berserker, 4, 0).setUnlocalizedName("berserk1").setCreativeTab(tab);
		berserkerChest = new ItemBerserkerArmor(berserker, 4, 1).setUnlocalizedName("berserk2").setCreativeTab(tab);
		berserkerLegs = new ItemBerserkerArmor(berserker, 4, 2).setUnlocalizedName("berserk3").setCreativeTab(tab);
		berserkerBoots = new ItemBerserkerArmor(berserker, 4, 3).setUnlocalizedName("berserk4").setCreativeTab(tab);

		soulSphere = new ItemSoulSphere().setFull3D().setMaxDamage(750).setUnlocalizedName("soulsphere").setCreativeTab(tab);
		elfbow = new ItemArcherBow().setFull3D().setMaxDamage(350).setUnlocalizedName("elmBow").setCreativeTab(tab);

		animalskin = new ItemRpg().setUnlocalizedName("a.leather").setCreativeTab(tab);
		tanHide = new ItemRpg().setUnlocalizedName("t.leather").setCreativeTab(tab);
		alchemistcloth = new ItemRpg().setUnlocalizedName("m.leather").setCreativeTab(tab);

		hammer = new ItemHammer(rageBreaker).setUnlocalizedName("rageBreaker").setCreativeTab(tab);
		
		lunarStaff = new ItemLunarStaff(lunarstaff).setMaxStackSize(1).setMaxDamage(1500).setUnlocalizedName("lunarStaff").setCreativeTab(tab);

		allItems = new Item[] { elfbow, /*claymore,*/ hammer, soulSphere, lunarStaff,
				berserkerShield, archerShield, talisman,
				alchemisthood, alchemistgown, alchemistpants, alchemistboots, archerhood,
				archerchest, archerpants, archerboots, berserkerHood,
				berserkerChest, berserkerLegs, berserkerBoots, animalskin,
				tanHide, alchemistcloth };

		for (int i = 0; i < allItems.length; i++) {
			if (allItems[i] != null) {

				String itemName = allItems[i].getUnlocalizedName().substring(allItems[i].getUnlocalizedName().indexOf(".") + 1);

				String itemNameCropped = itemName.substring(itemName.indexOf(".") + 1);

				if ((allItems[i] == animalskin)
						|| (allItems[i] == tanHide)
						|| (allItems[i] == alchemistcloth)) {
					allItems[i].setTextureName("minecraft:" + itemNameCropped);
				} else {
					allItems[i].setTextureName(RpgInventoryMod.name + ":"+ itemNameCropped);
				}

				GameRegistry.registerItem(allItems[i],allItems[i].getUnlocalizedName(),RpgInventoryMod.name);
			} else {
				System.out.println("Item is null !" + i);
			}
		}
	}
}
