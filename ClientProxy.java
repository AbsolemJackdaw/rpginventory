package RpgInventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.DimensionManager;
import RpgInventory.gui.AlternativeGui;
import RpgInventory.gui.BookGui;
import RpgInventory.gui.inventory.RpgContainer;
import RpgInventory.gui.inventory.RpgGui;
import RpgInventory.gui.inventory.RpgInventory;
import RpgInventory.playerjewels.RenderPlayerJewels;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	RpgInventory inv = null;

	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture("/subaraki/RPGinventoryTM.png");
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);

		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerJewels(new ModelBiped()));
	}

	public void openGUI(EntityPlayer p1, int id)
	{
		switch (id)
		{
		case 1:
			if (Minecraft.getMinecraft().playerController.isInCreativeMode())
			{
				Minecraft.getMinecraft().displayGuiScreen(new GuiContainerCreative(p1));
			} else {
				Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(p1));
			}
			break;
		case 2:
			Minecraft.getMinecraft().displayGuiScreen(new BookGui(p1));
			break;
		}
	}

	public RpgInventory getInventory(String username)
	{
		return inv;
	}

	public void loadInventory(String username)
	{
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		EntityPlayer player = server.getConfigurationManager().getPlayerForUsername(username);
		RpgInventory inv = new RpgInventory(player);

		File file = new File(DimensionManager.getCurrentSaveRootDirectory(), "InventoryRPG.dat");
		if (file.exists())  // ONLY if the file exists...
		{
			try
			{
				NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(file));
				inv.readFromNBT(nbt);
			}
			catch (Exception e)
			{
				// log it as severe
				FMLCommonHandler.instance().getFMLLogger().severe("[RPGInventoryMod] Error reading RPG Inventory for player " + username);
				e.printStackTrace();
			}
		}

		this.inv = inv;
	}

	public void discardInventory(String username)
	{
		if (inv == null)  // nothing to unload here.
			return;

		File file = new File(DimensionManager.getCurrentSaveRootDirectory(), "InventoryRPG.dat");
		if (!file.getParentFile().exists() || !file.getParentFile().isDirectory())
			file.getParentFile().mkdirs();  // create the folders if they don' exist.

		try
		{
			if (!file.exists())
				file.createNewFile();

			NBTTagCompound nbt = inv.writeToNBT(new NBTTagCompound());
			CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file));

		}
		catch (Exception e)
		{
			// log it as severe
			FMLCommonHandler.instance().getFMLLogger().severe("[RPGInventoryMod] Error writing RPG Inventory for player " + username);
			e.printStackTrace();
		}
	}
}
