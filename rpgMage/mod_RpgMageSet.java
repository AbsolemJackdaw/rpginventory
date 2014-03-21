package rpgMage;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;
import rpgMage.weapons.ItemElementalStaff;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RPGMS", name = "RpgInv Mage Addon", version = "RpgInv8.4", dependencies = "required-after:rpginventorymod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "RpgMSPacket" }, packetHandler = RpgMSPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = { "RpgMSPacket" }, packetHandler = RpgMSPacketHandler.class))
public class mod_RpgMageSet {

	public static String CLASSARCHMAGE = "archMage";
	public static String CLASSARCHMAGESHIELD = "shieldedArchMage";

	@SidedProxy(serverSide = "rpgMage.MSCommonProxy", clientSide = "rpgMage.MSClientProxy")
	public static MSCommonProxy proxy;

	public static CreativeTabs tab;

	public static Item archBook, fireStaff, frostStaff, earthStaff, windStaff,
			ultimateStaff, archmageHood, archmageChest, archmageLegs,
			archMageBoots, archMageLeather;

	public final static EnumArmorMaterial archMage = EnumHelper
			.addArmorMaterial("archmage", 20, new int[] { 4, 4, 4, 2 }, 5);

	@EventHandler
	public void load(FMLInitializationEvent event) {

		FMLLog.info("Rpg++ ArchMage Installed. Renderers can be Used");

		tab = new MageTab(CreativeTabs.getNextID(), "MageTab");

		fireStaff = new ItemElementalStaff(RpgConfig.instance.fireStaff, 1, 150)
				.setMaxStackSize(1).setMaxDamage(150)
				.setUnlocalizedName("staffFire");
		frostStaff = new ItemElementalStaff(RpgConfig.instance.frostStaff, 2,
				150).setMaxStackSize(1).setMaxDamage(150)
				.setUnlocalizedName("staffIce");
		earthStaff = new ItemElementalStaff(RpgConfig.instance.staffEarth, 3,
				150).setMaxStackSize(1).setMaxDamage(150)
				.setUnlocalizedName("staffEarth");
		windStaff = new ItemElementalStaff(RpgConfig.instance.staffWind, 4, 500)
				.setMaxStackSize(1).setMaxDamage(150)
				.setUnlocalizedName("staffWind");
		ultimateStaff = new ItemElementalStaff(
				RpgConfig.instance.staffUltimate, 5, 300).setMaxStackSize(1)
				.setMaxDamage(150).setUnlocalizedName("staffElemental");
		archBook = new ItemMageShield(RpgConfig.instance.archBook, 1, 300, "",
				"subaraki:jewels/archMageShield.png")
				.setUnlocalizedName("archTome");

		archmageHood = new ItemMageARmor(RpgConfig.instance.archmageHood,
				archMage, 4, 0).setUnlocalizedName("archMage1");
		archmageChest = new ItemMageARmor(RpgConfig.instance.archmageChest,
				archMage, 4, 1).setUnlocalizedName("archMage2");
		archmageLegs = new ItemMageARmor(RpgConfig.instance.archmageLegs,
				archMage, 4, 2).setUnlocalizedName("archMage3");
		archMageBoots = new ItemMageARmor(RpgConfig.instance.archmageBoots,
				archMage, 4, 3).setUnlocalizedName("archMage4");

		LanguageRegistry.addName(fireStaff, "Fire Staff");
		LanguageRegistry.addName(frostStaff, "Frost Staff");
		LanguageRegistry.addName(earthStaff, "Earth Staff");
		LanguageRegistry.addName(windStaff, "Wind Staff");
		LanguageRegistry.addName(ultimateStaff, "Ultimate Staff");
		LanguageRegistry.addName(archBook, "Archmage Aura Shield");
		LanguageRegistry.addName(archmageHood, "Arch Mage Hat");
		LanguageRegistry.addName(archmageChest, "Arch Mage Gown");
		LanguageRegistry.addName(archmageLegs, "Arch Mage Leggings");
		LanguageRegistry.addName(archMageBoots, "Arch Mage Boots");

		GameRegistry.addShapelessRecipe(new ItemStack(ultimateStaff),
				new Object[] { windStaff, earthStaff, frostStaff, fireStaff,
						Item.netherStar });
		GameRegistry.addRecipe(new ItemStack(windStaff), new Object[] { "III",
				"DSD", "III", 'I', Item.feather, 'D', Item.diamond, 'S',
				mod_RpgInventory.staf });
		GameRegistry.addRecipe(new ItemStack(frostStaff), new Object[] { "III",
				"DSD", "III", 'I', Block.ice, 'D', Item.diamond, 'S',
				mod_RpgInventory.staf });
		GameRegistry.addRecipe(new ItemStack(earthStaff), new Object[] { "III",
				"DSD", "III", 'I', Block.grass, 'D', Item.diamond, 'S',
				mod_RpgInventory.staf });
		GameRegistry.addRecipe(new ItemStack(fireStaff), new Object[] { "III",
				"DSD", "III", 'I', Item.blazePowder, 'D', Item.diamond, 'S',
				mod_RpgInventory.staf });
		GameRegistry.addRecipe(new ItemStack(archBook), new Object[] { "III",
				"IBI", " I ", 'I', new ItemStack(Item.dyePowder, 1, 4), 'B',
				Item.book });
		GameRegistry.addRecipe(new ItemStack(archMageBoots), new Object[] {
				"III", "IBI", "III", 'B', mod_RpgInventory.mageboots, 'I',
				Item.goldNugget });
		GameRegistry.addRecipe(new ItemStack(archmageLegs), new Object[] {
				"III", "IBI", "III", 'B', mod_RpgInventory.magepants, 'I',
				Item.goldNugget });
		GameRegistry.addRecipe(new ItemStack(archmageChest), new Object[] {
				"III", "IBI", "III", 'B', mod_RpgInventory.magegown, 'I',
				Item.goldNugget });
		GameRegistry.addRecipe(new ItemStack(archmageHood), new Object[] {
				"III", "IBI", "III", 'B', mod_RpgInventory.magehood, 'I',
				Item.goldNugget });

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

		EntityRegistry.registerGlobalEntityID(EntityElementalBlock.class,
				"Elemental", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityElementalBlock.class,
				"Elemental", mod_RpgInventory.instance.getUniqueID(), this,
				250, 1, true);
		LanguageRegistry.instance().addStringLocalization(
				"entity.EntityElementalBlock.name", "MageElemental");

		proxy.registerRendering();

		MinecraftForge.EVENT_BUS.register(new MageEvents());

	}
}
