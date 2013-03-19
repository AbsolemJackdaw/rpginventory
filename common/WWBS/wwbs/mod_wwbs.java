package WWBS.wwbs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import WWBS.wwbs.config.Config;
import WWBS.wwbs.wwbs.BlockBank;
import WWBS.wwbs.wwme.BlockME;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = "wwbs", name = "World Wide Banking and Trading", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"wwbsData"}, packetHandler = PacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"wwbsData"}, packetHandler = PacketHandler.class))
public class mod_wwbs {

	public static Block bank;
	public static Block me;
	public static CreativeTabs wwbstab;

	@SidedProxy(serverSide = "WWBS.wwbs.CommonProxy", clientSide = "WWBS.wwbs.ClientProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		Config.instance.loadConfig(event.getSuggestedConfigurationFile());
	}
	@Init
	public void load(FMLInitializationEvent event) {
		wwbstab = new InventoryTab(CreativeTabs.getNextID(), "Bank System Tab");

		bank = new BlockBank(Config.instance.bankBlock, Material.wood).setHardness(20f).setResistance(35f).setUnlocalizedName("bank")
				.setCreativeTab(wwbstab);
		me = new BlockME(Config.instance.MEBlock, Material.wood).setHardness(50f).setResistance(150f).setUnlocalizedName("M.E.")
				.setCreativeTab(wwbstab);

		LanguageRegistry.addName(bank, "wwbs");
		GameRegistry.registerBlock(bank, "Bank");
		LanguageRegistry.addName(me, "massiveExchange");
		GameRegistry.registerBlock(me, "Massive Exchange");
	}
}
