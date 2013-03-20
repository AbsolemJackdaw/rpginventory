package RpgMageSet;

import RpgMageSet.weapons.EntityElementalBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;


@Mod(modid = "RPGMS", name = "RpgInv Mage Addon", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgMSPacket"}, packetHandler = RpgMSPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgMSPacket"}, packetHandler = RpgMSPacketHandler.class))
public class mod_RpgMageSet {


	@SidedProxy(serverSide = "RpgMageSet.MSCommonProxy", clientSide = "RpgMageSet.MSClientProxy")
	public static MSCommonProxy proxy;


	private static int uniqueLocalID = 0;
	public int getNextUniqueID(){
		return uniqueLocalID++;
	}
	@Init
	public void load(FMLInitializationEvent event) {
		//proxy.registerRenderInformation();
	}
}

