package WWBS.wwbs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import WWBS.wwbs.config.Config;
import WWBS.wwbs.wwbs.BlockBank;
import WWBS.wwbs.wwme.BlockME;
import WWBS.wwbs.wwme.WwmeTE;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "wwbs", name = "World Wide Banking and Trading", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "wwbsData" }, packetHandler = PacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = { "wwbsData" }, packetHandler = PacketHandler.class))
public class mod_wwbs {

	public static Block bank;
	public static Block me;
	public static CreativeTabs wwbstab;

	@SidedProxy(serverSide = "WWBS.wwbs.CommonProxy", clientSide = "WWBS.wwbs.ClientProxy")
	public static CommonProxy proxy;

	public static mod_wwbs instance;

	public mod_wwbs() {
		instance = this;
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		wwbstab = new InventoryTab(CreativeTabs.getNextID(), "Bank System Tab");

		bank = new BlockBank(Config.bankBlock, Material.wood).setHardness(20f)
				.setResistance(35f).setUnlocalizedName("bank")
				.setCreativeTab(wwbstab);
		me = new BlockME(Config.MEBlock, Material.wood).setHardness(50f)
				.setResistance(150f).setUnlocalizedName("M.E.")
				.setCreativeTab(wwbstab);

		LanguageRegistry.addName(bank, "Bank");
		GameRegistry.registerBlock(bank, "wwbs");
		LanguageRegistry.addName(me, "Massive Exchange");
		GameRegistry.registerBlock(me, "massiveExchange");

		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

		GameRegistry.registerTileEntity(WwmeTE.class, "wwme");

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		Config.instance.loadConfig(event.getSuggestedConfigurationFile());
	}
}
