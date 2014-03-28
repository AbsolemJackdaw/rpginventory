package addonArchmage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import rpgInventory.mod_RpgInventory;
import addonArchmage.weapons.ItemElementalStaff;
import addonBasic.mod_addonBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = mod_RpgMageSet.id, name = mod_RpgMageSet.id, version = "RpgInv8.4", dependencies = "required-after:RpgBase")
// @NetworkMod(clientSideRequired = true, serverSideRequired = false,
// clientPacketHandlerSpec = @SidedPacketHandler(channels = { "RpgMSPacket" },
// packetHandler = RpgMSPacketHandler.class), serverPacketHandlerSpec =
// @SidedPacketHandler(channels = { "RpgMSPacket" }, packetHandler =
// RpgMSPacketHandler.class))
public class mod_RpgMageSet {

	public static final String name = "RPGMS";
	public static final String id = "RpgInv Mage Addon";

	public static String CLASSARCHMAGE = "archMage";
	public static String CLASSARCHMAGESHIELD = "shieldedArchMage";

	@SidedProxy(serverSide = "addonArchmage.MSCommonProxy", clientSide = "addonArchmage.MSClientProxy")
	public static MSCommonProxy proxy;

	public static CreativeTabs tab;

	public static Item archBook, fireStaff, frostStaff, earthStaff, windStaff,
			ultimateStaff, archmageHood, archmageChest, archmageLegs,
			archMageBoots, archMageLeather;

	public Item[] allItems;

	public final static ArmorMaterial archMage = EnumHelper.addArmorMaterial(
			"archmage", 20, new int[] { 4, 4, 4, 2 }, 5);

	@EventHandler
	public void load(FMLInitializationEvent event) {

		FMLLog.info("Rpg++ ArchMage Installed. Renderers can be Used");

		// LanguageRegistry.addName(fireStaff, "Fire Staff");
		// LanguageRegistry.addName(frostStaff, "Frost Staff");
		// LanguageRegistry.addName(earthStaff, "Earth Staff");
		// LanguageRegistry.addName(windStaff, "Wind Staff");
		// LanguageRegistry.addName(ultimateStaff, "Ultimate Staff");
		// LanguageRegistry.addName(archBook, "Archmage Aura Shield");
		// LanguageRegistry.addName(archmageHood, "Arch Mage Hat");
		// LanguageRegistry.addName(archmageChest, "Arch Mage Gown");
		// LanguageRegistry.addName(archmageLegs, "Arch Mage Leggings");
		// LanguageRegistry.addName(archMageBoots, "Arch Mage Boots");

		GameRegistry.addShapelessRecipe(new ItemStack(ultimateStaff),
				new Object[] { windStaff, earthStaff, frostStaff, fireStaff,
						Items.nether_star });
		GameRegistry.addRecipe(new ItemStack(windStaff), new Object[] { "III",
				"DSD", "III", 'I', Items.feather, 'D', Items.diamond, 'S',
				mod_addonBase.staf });
		GameRegistry.addRecipe(new ItemStack(frostStaff), new Object[] { "III",
				"DSD", "III", 'I', Blocks.ice, 'D', Items.diamond, 'S',
				mod_addonBase.staf });
		GameRegistry.addRecipe(new ItemStack(earthStaff), new Object[] { "III",
				"DSD", "III", 'I', Blocks.grass, 'D', Items.diamond, 'S',
				mod_addonBase.staf });
		GameRegistry.addRecipe(new ItemStack(fireStaff), new Object[] { "III",
				"DSD", "III", 'I', Items.blaze_powder, 'D', Items.diamond, 'S',
				mod_addonBase.staf });
		GameRegistry.addRecipe(new ItemStack(archBook), new Object[] { "III",
				"IBI", " I ", 'I', new ItemStack(Items.dye, 1, 4), 'B',
				Items.book });
		GameRegistry.addRecipe(new ItemStack(archMageBoots), new Object[] {
				"III", "IBI", "III", 'B', mod_addonBase.mageboots, 'I',
				Items.gold_nugget });
		GameRegistry.addRecipe(new ItemStack(archmageLegs), new Object[] {
				"III", "IBI", "III", 'B', mod_addonBase.magepants, 'I',
				Items.gold_nugget });
		GameRegistry.addRecipe(new ItemStack(archmageChest), new Object[] {
				"III", "IBI", "III", 'B', mod_addonBase.magegown, 'I',
				Items.gold_nugget });
		GameRegistry.addRecipe(new ItemStack(archmageHood), new Object[] {
				"III", "IBI", "III", 'B', mod_addonBase.magehood, 'I',
				Items.gold_nugget });

		// EntityRegistry.registerGlobalEntityID(EntityElementalBlocks.class,
		// "Elemental", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityElementalBlock.class,
				"Elemental", mod_RpgInventory.instance.getUniqueID(), this,
				250, 1, true);
		LanguageRegistry.instance().addStringLocalization(
				"entity.EntityElementalBlock.name", "MageElemental");

		proxy.registerRendering();

		MinecraftForge.EVENT_BUS.register(new MageEvents());

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		tab = new MageTab(CreativeTabs.getNextID(), "Archmage Addon");

		fireStaff = new ItemElementalStaff(1, 150).setMaxStackSize(1)
				.setMaxDamage(150).setUnlocalizedName("staffFire");
		frostStaff = new ItemElementalStaff(2, 150).setMaxStackSize(1)
				.setMaxDamage(150).setUnlocalizedName("staffIce");
		earthStaff = new ItemElementalStaff(3, 150).setMaxStackSize(1)
				.setMaxDamage(150).setUnlocalizedName("staffEarth");
		windStaff = new ItemElementalStaff(4, 500).setMaxStackSize(1)
				.setMaxDamage(150).setUnlocalizedName("staffWind");
		ultimateStaff = new ItemElementalStaff(5, 300).setMaxStackSize(1)
				.setMaxDamage(150).setUnlocalizedName("staffElemental");
		archBook = new ItemMageShield(1, 300, "",
				"subaraki:jewels/archMageShield.png")
				.setUnlocalizedName("archTome");

		archmageHood = new ItemMageARmor(archMage, 4, 0)
				.setUnlocalizedName("archMage1");
		archmageChest = new ItemMageARmor(archMage, 4, 1)
				.setUnlocalizedName("archMage2");
		archmageLegs = new ItemMageARmor(archMage, 4, 2)
				.setUnlocalizedName("archMage3");
		archMageBoots = new ItemMageARmor(archMage, 4, 3)
				.setUnlocalizedName("archMage4");

		fireStaff.setCreativeTab(tab);
		frostStaff.setCreativeTab(tab);
		earthStaff.setCreativeTab(tab);
		windStaff.setCreativeTab(tab);
		ultimateStaff.setCreativeTab(tab);
		archBook.setCreativeTab(tab);
		archmageHood.setCreativeTab(tab);
		archmageChest.setCreativeTab(tab);
		archmageLegs.setCreativeTab(tab);
		archMageBoots.setCreativeTab(tab);

		allItems = new Item[] { archBook, fireStaff, frostStaff, earthStaff,
				windStaff, ultimateStaff, archmageHood, archmageChest,
				archmageLegs, archMageBoots, archMageLeather };
		for (int i = 0; i < allItems.length; i++) {

			if (allItems[i] != null) {

				String itemName = allItems[i].getUnlocalizedName().substring(
						allItems[i].getUnlocalizedName().indexOf(".") + 1);

				String itemNameCropped = itemName.substring(itemName.indexOf(".") + 1);

				allItems[i].setTextureName(mod_RpgInventory.name + ":" + itemNameCropped);

				GameRegistry.registerItem(allItems[i],
						allItems[i].getUnlocalizedName(), name);

			} else {
				System.out.println("Item is null !" + i);
			}
		}
	}
}
