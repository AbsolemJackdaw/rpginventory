package rpgNecroPaladin;

import net.minecraft.creativetab.CreativeTabs;
import rpgInventory.mod_RpgInventory;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.packets.RpgPlusPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
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



	@SidedProxy(serverSide = "rpgNecroPaladin.CommonProxyRpgplus", clientSide = "rpgNecroPaladin.ClientProxyRpgPlus")
	public static CommonProxyRpgplus proxy;

	public static CreativeTabs tab;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		tab = new PlusTab(CreativeTabs.getNextID(), "++Tab");

		mod_RpgInventory.necroHood.setCreativeTab(tab);
		mod_RpgInventory.necroChestplate.setCreativeTab(tab);
		mod_RpgInventory.necroLeggings.setCreativeTab(tab);
		mod_RpgInventory.necroBoots.setCreativeTab(tab);

		mod_RpgInventory.palaHelm.setCreativeTab(tab);
		mod_RpgInventory.palaChest.setCreativeTab(tab);
		mod_RpgInventory.palaLeggings.setCreativeTab(tab);
		mod_RpgInventory.palaBoots.setCreativeTab(tab);

		mod_RpgInventory.necro_shield.setCreativeTab(tab);
		mod_RpgInventory.necro_weapon.setCreativeTab(tab);
		mod_RpgInventory.pala_shield.setCreativeTab(tab);
		mod_RpgInventory.pala_weapon.setCreativeTab(tab);
		mod_RpgInventory.necro_skin.setCreativeTab(tab);
		mod_RpgInventory.pala_steel.setCreativeTab(tab);


	}
	
	@EventHandler
	public void post(FMLPostInitializationEvent evt){

		proxy.registerRenderInformation();
		
		EntityRegistry.registerGlobalEntityID(EntityMinionS.class, "skeletonMinion", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityMinionZ.class, "zombieMinion", EntityRegistry.findGlobalUniqueEntityId());
//		EntityRegistry.registerModEntity(EntityMinionS.class, "skeletonMinion", mod_RpgInventory.instance.getUniqueID(), this, 100, 2, true);
//		EntityRegistry.registerModEntity(EntityMinionZ.class, "zombieMinion", mod_RpgInventory.instance.getUniqueID(), this, 100, 2, true);
		LanguageRegistry.instance().addStringLocalization("entity.EntityMinionS.name", "Skeleton Minion");
		LanguageRegistry.instance().addStringLocalization("entity.EntityMinionZ.name", "Zombie Minion");

		
		TickRegistry.registerTickHandler(new CommonTickHandlerRpgPlus(), Side.SERVER);
	}
}
