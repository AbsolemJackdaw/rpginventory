package RpgPlusPlus;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgInventory.mod_RpgInventory;
import RpgPlusPlus.minions.EntityMinionS;
import RpgPlusPlus.minions.EntityMinionZ;
import RpgPlusPlus.weapons.grandsword.GrandSwordRender;
import RpgPlusPlus.weapons.skull.NecroRenderer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
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

@Mod(modid = "RPGPlusPlus", name = "Subarakis RPG++ mod", version = "2.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgPlusPlus"}, packetHandler = RpgPlusPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgPlusPlus"}, packetHandler = RpgPlusPacketHandler.class))
public class mod_RpgPlus {

	

	@SidedProxy(serverSide = "RpgPlusPlus.CommonProxyRpgplus", clientSide = "RpgPlusPlus.ClientProxyRpgPlus")
	public static CommonProxyRpgplus proxy;
	// Armor Materials
	
	private static int uniqueLocalID = 0;
	public int getNextUniqueID(){
		return uniqueLocalID++;
	}
	@Init
	public void load(FMLInitializationEvent event) {
		

	}
        @PostInit
        public void post(FMLPostInitializationEvent evt){
            proxy.registerRenderInformation();


		/**
		 * Adding Crafting Recipes
		 */
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.necro_skin, 1), new Object[]{"BWB", "WLW", "BWB", 'W', Item.spiderEye, 'B', Item.bone, 'L', Item.leather});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.pala_steel, 1), new Object[]{"GGG", "BIB", "GGG", 'G', Item.ingotGold, 'B', (new ItemStack(Item.potion.itemID, 1, 0)), 'I', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.necro_shield, 1), new Object[]{"WWW", "WBW", " W ", 'W', mod_RpgInventory.necro_skin, 'B', new ItemStack(Item.skull,1,1)});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.pala_shield, 1), new Object[]{"WWW", "WBW", " W ", 'W', mod_RpgInventory.pala_steel, 'B', Block.blockSteel});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.necro_weapon, 1), new Object[]{"WWW", "WBW", "WWW", 'W', Item.bone, 'B', new ItemStack(Item.skull,1,1)});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.pala_weapon, 1), new Object[]{"S", "S", "G", 'S', mod_RpgInventory.pala_steel, 'G', Item.ingotGold});

		/**
		 * Add tamable Entities
		 */

		EntityRegistry.registerGlobalEntityID(EntityMinionS.class, "skeletonMinion", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityMinionZ.class, "zombieMinion", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityMinionS.class, "skeletonMinion", getNextUniqueID(), this, 100, 2, true);
		EntityRegistry.registerModEntity(EntityMinionZ.class, "zombieMinion", getNextUniqueID(), this, 100, 2, true);
		LanguageRegistry.instance().addStringLocalization("entity.EntityMinionS.name", "Skeleton Minion");
		LanguageRegistry.instance().addStringLocalization("entity.EntityMinionZ.name", "Zombie Minion");


		//will register on integrated server too
		TickRegistry.registerTickHandler(new CommonTickHandlerRpgPlus(), Side.SERVER);
        }
}
