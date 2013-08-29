package rpgMage;

import rpgInventory.mod_RpgInventory;
import rpgMage.weapons.EntityElementalBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = "RPGMS", name = "RpgInv Mage Addon", version = "RpgInv8.4", dependencies="required-after:rpginventorymod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgMSPacket"}, packetHandler = RpgMSPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgMSPacket"}, packetHandler = RpgMSPacketHandler.class))
public class mod_RpgMageSet {


	@SidedProxy(serverSide = "rpgMage.MSCommonProxy", clientSide = "rpgMage.MSClientProxy")
	public static MSCommonProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent event) {


		EntityRegistry.registerGlobalEntityID(EntityElementalBlock.class, "Elemental", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityElementalBlock.class, "Elemental", mod_RpgInventory.instance.getUniqueID(), this, 250, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.EntityElementalBlock.name", "MageElemental");

		proxy.registerRendering();
	}
}

