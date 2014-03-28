package rpgVanillaShields;

import rpgInventory.mod_RpgInventory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "VanillaShields", name = "Vanilla Shields Mod", version = "RpgInv8.4", dependencies = "required-after:rpginventorymod")
// @NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mod_VanillaShields {

	@SidedProxy(serverSide = "rpgVanillaShields.CommonProxy", clientSide = "rpgVanillaShields.ClientProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tab;

	public static String WOODENSHIELD = "vanillaWood";
	public static String IRONSHIELD = "vanillaIron";
	public static String GOLDENSHIELD = "vanillaGolden";
	public static String DIAMONDSHIELD = "vanillaDiamond";

	public static Item shieldWood, shieldIron, shieldGold, shieldDiamond;

	public Item[] allItems;

	@EventHandler
	public void load(FMLInitializationEvent event) {

		FMLLog.info("Rpg++ Vanilla Shields is installed. Renderers can be Used");

		LanguageRegistry.addName(shieldWood, "Wooden Shield");
		LanguageRegistry.addName(shieldIron, "Iron Shield");
		LanguageRegistry.addName(shieldGold, "Golden Shield");
		LanguageRegistry.addName(shieldDiamond, "Diamond Shield");
		shieldWood.setCreativeTab(tab);
		shieldIron.setCreativeTab(tab);
		shieldGold.setCreativeTab(tab);
		shieldDiamond.setCreativeTab(tab);

		GameRegistry.addRecipe(new ItemStack(shieldWood, 1), new Object[] {
			"WWW", "WBW", " W ", 'W', Blocks.planks, 'B', Blocks.log });
		GameRegistry.addRecipe(new ItemStack(shieldIron, 1), new Object[] {
			"WWW", "WWW", " W ", 'W', Items.iron_ingot, });
		GameRegistry.addRecipe(new ItemStack(shieldGold, 1), new Object[] {
			"WWW", "WWW", " W ", 'W', Items.gold_ingot });
		GameRegistry.addRecipe(new ItemStack(shieldDiamond, 1), new Object[] {
			"WWW", "WBW", " W ", 'W', Items.diamond, 'B',
			Blocks.diamond_block });

		proxy.registerRenderers();

		MinecraftForge.EVENT_BUS.register(new VanillaEvents());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e){

		tab = new ShieldTab(CreativeTabs.getNextID(), "ShieldTab");

		shieldWood = new ItemRpgInvShields(1, 50, "wood",
				"subaraki:jewels/ShieldWood.png")
		.setUnlocalizedName("shieldWood");
		shieldIron = new ItemRpgInvShields(1, 125, "iron",
				"subaraki:jewels/ShieldIron.png")
		.setUnlocalizedName("shieldIron");
		shieldGold = new ItemRpgInvShields(1, 250, "gold",
				"subaraki:jewels/ShieldGold.png")
		.setUnlocalizedName("shieldGold");
		shieldDiamond = new ItemRpgInvShields(1, 500, "diamond",
				"subaraki:jewels/ShieldDiamond.png")
		.setUnlocalizedName("shieldDiamond");

		allItems = new Item[]{
				shieldWood, shieldIron, shieldGold, shieldDiamond			
		};

		for (int i = 0; i < allItems.length; i++) {



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
			} else {
				System.out.println("Item is null !" + i);
			}
		}
	}
}
