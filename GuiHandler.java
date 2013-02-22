package RpgInventory;

import RpgInventory.gui.inventory.RpgContainer;
import RpgInventory.gui.inventory.RpgGui;
import RpgInventory.gui.inventory.RpgInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		System.out.println("SERVER READ CONTAINER" + ID);

		if (ID == 1)
		{
			return new RpgContainer(player, AARpgBaseClass.proxy.getInventory(player.username));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		System.out.println("CLIENT READ GUI " + ID);

		if (ID == 1)
		{
			// MAJOR FIXED CODE HERE. 
			// Basically.. if the integrated server is running with the same inventory.. give a dummy inventory)
			if (FMLCommonHandler.instance().getMinecraftServerInstance() != null)
			{
				System.out.println("client");
				return new RpgGui(player, new RpgInventory(player));
			}

			else
			{
				System.out.println("integrated server active");
				return new RpgGui(player, AARpgBaseClass.proxy.getInventory(player.username));

			}
		}

		return null;
	}

}
