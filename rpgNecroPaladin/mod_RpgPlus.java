package rpgNecroPaladin;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import rpgInventory.config.RpgConfig;
import rpgInventory.item.ItemMats;
import rpgNecroPaladin.items.BonusArmorPlus;
import rpgNecroPaladin.items.ItemGrandSword;
import rpgNecroPaladin.items.ItemNecroPaladinMats;
import rpgNecroPaladin.items.ItemNecroSkull;
import rpgNecroPaladin.items.ItemRpgInvArmorPlus;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.packets.RpgPlusPacketHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "RPGPlusPlus", name = "Subarakis RPG++ mod", version = "RpgInv8.4", dependencies="required-after:rpginventorymod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgPlusPlus"}, packetHandler = RpgPlusPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgPlusPlus"}, packetHandler = RpgPlusPacketHandler.class))
public class mod_RpgPlus {
	private String[][] recipePatterns;
	private Object[][] recipeItems;

	public  final static EnumArmorMaterial necroArmor = EnumHelper.addArmorMaterial("necromancer", 20, new int[]{2, 5, 1, 1}, 5);	// use of Souls
	public  final static EnumArmorMaterial paladin = EnumHelper.addArmorMaterial("paladin", 20, new int[]{4, 7, 2, 1}, 5);	// use of Steel
	
	public EnumToolMaterial NecroToolMaterial = EnumHelper.addToolMaterial("souls", 0, 1024, 5F, 1, 0);
	public EnumToolMaterial PalaToolMaterial = EnumHelper.addToolMaterial("steel", 0, 1024, 5F, 1, 0);
	
	public static Item 
	/*====shields====*/
	pala_shield, necro_shield,
	/*====weapons====*/
	pala_weapon,necro_weapon,
	/*====armor====*/
	necroHood, necroChestplate, necroLeggings, necroBoots,
	palaHelm, palaChest, palaLeggings, palaBoots,
	/*====leathers/skins====*/
	necro_skin, pala_steel;

	@SidedProxy(serverSide = "rpgNecroPaladin.CommonProxyRpgplus", clientSide = "rpgNecroPaladin.ClientProxyRpgPlus")
	public static CommonProxyRpgplus proxy;

	public static CreativeTabs tab;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		
		FMLLog.info("Rpg++ Necromancer and Paladin is installed. Renderers can be Used", 1);

		tab = new PlusTab(CreativeTabs.getNextID(), "++Tab");

		necroHood = new BonusArmorPlus(RpgConfig.instance.necroHoodID, necroArmor, 4, 0).setUnlocalizedName("necro1");
		necroChestplate = new BonusArmorPlus(RpgConfig.instance.necroChestplateID, necroArmor, 4, 1).setUnlocalizedName("necro2");
		necroLeggings = new BonusArmorPlus(RpgConfig.instance.necroLeggingsID, necroArmor, 4, 2).setUnlocalizedName("necro3");
		necroBoots = new BonusArmorPlus(RpgConfig.instance.necroBootsID, necroArmor, 4, 3).setUnlocalizedName("necro4");

		palaHelm = new BonusArmorPlus(RpgConfig.instance.palaHelmID, paladin, 4, 0).setUnlocalizedName("paladin1");
		palaChest = new BonusArmorPlus(RpgConfig.instance.palaChestID, paladin, 4, 1).setUnlocalizedName("paladin2");
		palaLeggings = new BonusArmorPlus(RpgConfig.instance.palaLeggingsID, paladin, 4, 2).setUnlocalizedName("paladin3");
		palaBoots = new BonusArmorPlus(RpgConfig.instance.palaBootsID, paladin, 4, 3).setUnlocalizedName("paladin4");

		necro_shield = new ItemRpgInvArmorPlus(RpgConfig.instance.necro_shieldID, 1, 250, "necro","subaraki:jewels/NecroShield.png").setUnlocalizedName("shieldNecro");
		necro_weapon = new ItemNecroSkull(RpgConfig.instance.necro_weaponID, NecroToolMaterial).setFull3D().setUnlocalizedName("Skull");

		pala_shield = new ItemRpgInvArmorPlus(RpgConfig.instance.pala_shieldID, 1, 450, "pala","subaraki:jewels/PaladinShield.png").setUnlocalizedName("shieldPaladin");
		pala_weapon = new ItemGrandSword(RpgConfig.instance.pala_weaponID, PalaToolMaterial).setFull3D().setUnlocalizedName("paladinPride");

		necro_skin = new ItemNecroPaladinMats(RpgConfig.instance.necro_skinID).setUnlocalizedName("n.leather");
		pala_steel = new ItemNecroPaladinMats(RpgConfig.instance.pala_steelID).setUnlocalizedName("p.iron_ingot");

		LanguageRegistry.addName(necro_shield, "NecroMancer Shield");
		LanguageRegistry.addName(pala_shield, "Paladin Shield");
		LanguageRegistry.addName(necroHood, "NecroMancer Hood");
		LanguageRegistry.addName(necroChestplate, "NecroMancer Vest");
		LanguageRegistry.addName(necroLeggings, "NecroMancer Pants");
		LanguageRegistry.addName(necroBoots, "NecroMancer Boots");
		LanguageRegistry.addName(palaHelm, "Paladin Helmet");
		LanguageRegistry.addName(palaChest, "Paladin Chestplate");
		LanguageRegistry.addName(palaLeggings, "Paladin Leggings");
		LanguageRegistry.addName(palaBoots, "Paladin Boots");
		LanguageRegistry.addName(necro_weapon, "NecroMancer Skull");
		LanguageRegistry.addName(pala_weapon, "Paladin's Pride");
		LanguageRegistry.addName(pala_steel, "Paladin's Steel");
		LanguageRegistry.addName(necro_skin, "Necromancer's Cloth");

		GameRegistry.addRecipe(new ItemStack(necro_skin, 1), new Object[]{"BWB", "WLW", "BWB", 'W', Item.spiderEye, 'B', Item.bone, 'L', Item.leather});
		GameRegistry.addRecipe(new ItemStack(pala_steel, 1), new Object[]{"GGG", "BIB", "GGG", 'G', Item.ingotGold, 'B', (new ItemStack(Item.potion.itemID, 1, 0)), 'I', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(necro_shield, 1), new Object[]{"WWW", "WBW", " W ", 'W', necro_skin, 'B', new ItemStack(Item.skull,1,1)});
		GameRegistry.addRecipe(new ItemStack(pala_shield, 1), new Object[]{"WWW", "WBW", " W ", 'W', pala_steel, 'B', Block.blockIron});
		GameRegistry.addRecipe(new ItemStack(necro_weapon, 1), new Object[]{"WWW", "WBW", "WWW", 'W', Item.bone, 'B', new ItemStack(Item.skull,1,1)});
		GameRegistry.addRecipe(new ItemStack(pala_weapon, 1), new Object[]{"S", "S", "G", 'S', pala_steel, 'G', Item.ingotGold});

		recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
		recipeItems = new Object[][]{{pala_steel, necro_skin}, {palaHelm, necroHood},
				{palaChest, necroChestplate}, {palaLeggings, necroLeggings},
				{palaBoots, necroBoots}};

		for (int var2 = 0; var2 < recipeItems[0].length; ++var2) {
			Object var3 = recipeItems[0][var2];

			for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
				GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
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

	}
	
	@EventHandler
	public void post(FMLPostInitializationEvent evt){

		proxy.registerRenderInformation();
		
		EntityRegistry.registerGlobalEntityID(EntityMinionS.class, "skeletonMinion", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityMinionZ.class, "zombieMinion", EntityRegistry.findGlobalUniqueEntityId());
		LanguageRegistry.instance().addStringLocalization("entity.EntityMinionS.name", "Skeleton Minion");
		LanguageRegistry.instance().addStringLocalization("entity.EntityMinionZ.name", "Zombie Minion");

		
		TickRegistry.registerTickHandler(new CommonTickHandlerRpgPlus(), Side.SERVER);
	}
}
