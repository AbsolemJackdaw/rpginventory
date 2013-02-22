package RpgInventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import RpgInventory.gui.inventory.RpgContainer;
import RpgInventory.gui.inventory.RpgInventory;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy
{
	// username, Inventory map.
	private HashMap<String, RpgInventory>	invs;
	public static final String				RPG_DIR	= "RPG_Inventories";

	public void registerRenderInformation()
	{
		// nothing for rendering huh....
	}

	public void openGUI(EntityPlayer player, int id)
	{
	}

	public RpgInventory getInventory(String username)
	{
		return invs.get(username);
	}

	public void loadInventory(String username)
	{
		if (invs == null)
			invs = new HashMap<String, RpgInventory>();
		
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		EntityPlayer player = server.getConfigurationManager().getPlayerForUsername(username);
		RpgInventory inv = new RpgInventory(player);

		File file = new File(DimensionManager.getCurrentSaveRootDirectory(), RPG_DIR+"\\"+username + "Rpg.dat");
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

		invs.put(username, inv);
	}

	public void discardInventory(String username)
	{
		RpgInventory inv = invs.remove(username);

		if (inv == null)  // nothing to unload here.
			return;

		File file = new File(DimensionManager.getCurrentSaveRootDirectory(), RPG_DIR+"\\"+username + "Rpg.dat");
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
