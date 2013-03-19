package RpgRB;

import RpgInventory.EntityPetXP;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "RPGRB", name = "Rogue and BeastMaster Patch", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class))
public class mod_RpgRB {

	@SidedProxy(serverSide = "RpgRB.RBCommonProxy", clientSide = "RpgRB.RBClientProxy")
	public static RBCommonProxy proxy;

	//	@SidedProxy(serverSide = "RpgPlusPlus.CommonProxyRpgplus", clientSide = "RpgPlusPlus.ClientProxyRpgPlus")
	//	public static CommonProxyRpgplus proxy;

	// Armor Materials

	private static int uniqueLocalID = 0;
	public int getNextUniqueID(){
		return uniqueLocalID++;
	}
	@Init
	public void load(FMLInitializationEvent event) {

		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.daggers,1), new Object [] {" ei","eie","se ", 'i', Item.ingotIron, 'e',Item.spiderEye, 's',Item.stick});


		EntityRegistry.registerGlobalEntityID(BullPet.class, "BullPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SpiderPet.class, "SpiderPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(BoarPet.class, "BoarPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityTeleportStone.class, "TelePortStone", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(BullPet.class, "BullPet", getNextUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(SpiderPet.class, "SpiderPet", getNextUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(BoarPet.class, "BoarPet", getNextUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityPetXP.class, "PetXP", getNextUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityTeleportStone.class, "TelePortStone", getNextUniqueID(), this, 80, 1, true);

		proxy.registerRendering();
	}
}
