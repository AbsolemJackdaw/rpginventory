package WWBS.wwbs;

import WWBS.wwbs.wwbs.ContainerBank;
import WWBS.wwbs.wwbs.GuiBS;
import WWBS.wwbs.wwbs.WwbsTe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
			return new ContainerBank(player.inventory, (WwbsTe)world.getBlockTileEntity(x, y, z));	
//		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return new GuiBS(player, (WwbsTe)world.getBlockTileEntity(x, y, z));
//		return null;
	}

}
