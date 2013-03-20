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

		//will register on integrated server too
		TickRegistry.registerTickHandler(new CommonTickHandlerRpgPlus(), Side.SERVER);
	}
}
