package RpgInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import RpgInventory.forge.GuiMF;
import RpgInventory.forge.MoldContainer;
import RpgInventory.forge.TEMold;
import RpgInventory.gui.inventory.RpgContainer;
import RpgInventory.gui.inventory.RpgGui;
import RpgInventory.gui.inventory.RpgInv;
import cpw.mods.fml.common.FMLCommonHandler;
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
        if (ID == 3) {
            
            return new RpgInventory.gui.pet.PetGui(player);
        }
        return null;
    }
}
