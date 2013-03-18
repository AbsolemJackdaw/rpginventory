package wwbs;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import wwbs.gui.blockGui.GuiMainB;
import wwbs.gui.blockGui.GuiMainME;

public class ClientProxy extends CommonProxy{


	public void openGui(int id, EntityPlayer p) {

		switch(id)
		{
		case 1:
            Minecraft.getMinecraft().displayGuiScreen(new GuiMainB(p));
            break;
		case 2:
            Minecraft.getMinecraft().displayGuiScreen(new GuiMainME(p));
            break;
		}
		
	}
}
