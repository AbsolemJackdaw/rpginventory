package rpgRogueBeast;

import net.minecraft.creativetab.CreativeTabs;
import rpgInventory.mod_RpgInventory;
import rpgInventory.entity.EntityPetXP;
import rpgRogueBeast.entity.BoarPet;
import rpgRogueBeast.entity.BullPet;
import rpgRogueBeast.entity.EntityTeleportStone;
import rpgRogueBeast.entity.SpiderPet;
import rpgRogueBeast.packets.RpgRBPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = "RPGRB", name = "Rogue and BeastMaster Patch", version = "RpgInv8.4", dependencies="required-after:rpginventorymod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class))
public class mod_RpgRB {

	@SidedProxy(serverSide = "rpgRogueBeast.RBCommonProxy", clientSide = "rpgRogueBeast.RBClientProxy")
	public static RBCommonProxy proxy;
	
	public static CreativeTabs tab;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		tab = new RBTab(CreativeTabs.getNextID(), "RBTab");

		mod_RpgInventory.daggers.setCreativeTab(tab);
		mod_RpgInventory.beastAxe.setCreativeTab(tab);

		mod_RpgInventory.rogueLeather.setCreativeTab(tab);
		mod_RpgInventory.beastShield.setCreativeTab(tab);
		mod_RpgInventory.beastLeather.setCreativeTab(tab);

		mod_RpgInventory.rogueHood.setCreativeTab(tab);
		mod_RpgInventory.rogueChest.setCreativeTab(tab);
		mod_RpgInventory.rogueLegs.setCreativeTab(tab);
		mod_RpgInventory.rogueBoots.setCreativeTab(tab);
		
		mod_RpgInventory.beastHood.setCreativeTab(tab);
		mod_RpgInventory.beastChest.setCreativeTab(tab);
		mod_RpgInventory.beastLegs.setCreativeTab(tab);
		mod_RpgInventory.beastBoots.setCreativeTab(tab);

		mod_RpgInventory.petCandy.setCreativeTab(tab);
		mod_RpgInventory.tangledBrench.setCreativeTab(tab);
		mod_RpgInventory.PetXPBottle.setCreativeTab(tab);
		
		mod_RpgInventory.crystal.setCreativeTab(tab);
		
		mod_RpgInventory.whistle.setCreativeTab(tab);
		
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
