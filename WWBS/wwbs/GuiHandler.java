package WWBS.wwbs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import WWBS.wwbs.wwbs.ContainerBank;
import WWBS.wwbs.wwbs.GuiBS;
import WWBS.wwbs.wwbs.WwbsTe;
import WWBS.wwbs.wwme.ContainerME;
import WWBS.wwbs.wwme.GuiME;
import WWBS.wwbs.wwme.WwmeTE;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		if (ID == 1) {
			return new GuiBS(player, (WwbsTe) world.getBlockTileEntity(x, y, z));
		}
		if (ID == 2) {
			return new GuiME(player, (WwmeTE) world.getBlockTileEntity(x, y, z));
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == 1) {
			return new ContainerBank(player.inventory,
					(WwbsTe) world.getBlockTileEntity(x, y, z));
		}
		if (ID == 2) {
			return new ContainerME(player.inventory,
					(WwmeTE) world.getBlockTileEntity(x, y, z));
		}
		return null;
	}

}
