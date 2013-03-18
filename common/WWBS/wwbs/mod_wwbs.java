package WWBS.wwbs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import RpgRB.RpgRBPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;


@Mod(modid = "wwbs", name = "World Wide Banking and Trading", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"wwbsData"}, packetHandler = PacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"wwbsData"}, packetHandler = PacketHandler.class))
public class mod_wwbs {

	public static Block bank;

	@SidedProxy(serverSide = "WWBS.wwbs.CommonProxy", clientSide = "WWBS.wwbs.ClientProxy")
	public static CommonProxy proxy;


	@Init
	public void load(FMLInitializationEvent event) {

		bank = new BlockBank(250, Material.wood).setHardness(20f).setResistance(35f).setUnlocalizedName("bank");
	}
}
