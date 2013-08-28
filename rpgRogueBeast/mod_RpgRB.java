package rpgRogueBeast;

import rpgInventory.mod_RpgInventory;
import rpgInventory.item.petXp.EntityPetXP;
import rpgRogueBeast.beastmaster.BoarPet;
import rpgRogueBeast.beastmaster.BullPet;
import rpgRogueBeast.beastmaster.SpiderPet;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = "RPGRB", name = "Rogue and BeastMaster Patch", version = "RpgInv8.4", dependencies="required-after:RPGInventoryMod")
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
		
		proxy.registerRendering();
		
		EntityRegistry.registerGlobalEntityID(BullPet.class, "BullPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SpiderPet.class, "SpiderPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(BoarPet.class, "BoarPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityTeleportStone.class, "TelePortStone", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(BullPet.class, "BullPet", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(SpiderPet.class, "SpiderPet", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(BoarPet.class, "BoarPet", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityPetXP.class, "PetXP", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityTeleportStone.class, "TelePortStone", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);

	}
}
