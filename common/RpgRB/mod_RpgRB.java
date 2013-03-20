package RpgRB;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(modid = "RPGRB", name = "Rogue and BeastMaster Patch", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class))
public class mod_RpgRB {

	@SidedProxy(serverSide = "RpgRB.RBCommonProxy", clientSide = "RpgRB.RBClientProxy")
	public static RBCommonProxy proxy;
	
	@Init
	public void load(FMLInitializationEvent event) {
		
	}
}
