package WWBS.wwbs;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import WWBS.wwbs.gui.blockGui.GuiMainB;
import WWBS.wwbs.gui.blockGui.GuiMainME;

public class ClientProxy extends CommonProxy {

	@Override
	public void openGui(int id, EntityPlayer p, int x, int y, int z) {

		switch (id) {
		case 1:
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainB(p, x, y, z));
			break;
		case 2:
			Minecraft.getMinecraft()
					.displayGuiScreen(new GuiMainME(p, x, y, z));
			break;
		}

	}

	public void registerRendering() {

	}
}
