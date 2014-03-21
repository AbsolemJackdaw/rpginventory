package rpgInventory.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rpgInventory.block.te.GuiMF;
import rpgInventory.block.te.MoldContainer;
import rpgInventory.block.te.TEMold;
import rpgInventory.gui.rpginv.PlayerRpgContainer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.gui.rpginv.RpgGui;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == 1) {
			return new RpgGui(player, new PlayerRpgInventory(player));
		}

		if (ID == 2) {
			return new GuiMF(player.inventory,
					(TEMold) world.getBlockTileEntity(x, y, z));
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// System.out.println("SERVER READ CONTAINER" + ID);
		if (ID == 1) {
			PlayerRpgInventory inv = PlayerRpgInventory.get(player);
			return new PlayerRpgContainer(player, inv);
		}
		if (ID == 2) {
			return new MoldContainer(player.inventory,
					(TEMold) world.getBlockTileEntity(x, y, z));
		}

		return null;
	}
}
