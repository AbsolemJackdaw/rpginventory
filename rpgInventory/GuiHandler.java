package rpgInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rpgInventory.forge.GuiMF;
import rpgInventory.forge.MoldContainer;
import rpgInventory.forge.TEMold;
import rpgInventory.gui.inventory.RpgContainer;
import rpgInventory.gui.inventory.RpgGui;
import rpgInventory.gui.inventory.RpgInv;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //System.out.println("SERVER READ CONTAINER" + ID);
        if (ID == 1) {
            return new RpgContainer(player, mod_RpgInventory.proxy.getInventory(player.username));
        }
        if (ID == 2) {
            return new MoldContainer(player.inventory, (TEMold) world.getBlockTileEntity(x, y, z));
        }


        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 1) {
            return new RpgGui(player, new RpgInv(player.username));
        }

        if (ID == 2) {
            return new GuiMF(player.inventory, (TEMold) world.getBlockTileEntity(x, y, z));
        }
        return null;
    }
}
