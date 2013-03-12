package RpgInventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import RpgInventory.gui.inventory.RpgInv;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {
    // username, Inventory map.

    private HashMap<String, RpgInv> invs;
    public static final String RPG_DIR = "RPG_Inventories";

    public int getSphereID() {
        return 0;
    }

    public void addEntry(String username, RpgInv inv) {
        //
    }

    public void registerLate() {
    }

    public void registerRenderInformation() {
        // nothing for rendering huh....
    }

    public void openGUI(EntityPlayer player, int id) {
    }
    
    public void playerLevel(EntityPlayer player, int amount)
    {
    	player.addExperienceLevel(-amount);
    }

    public RpgInv getInventory(String username) {
        for(String s: invs.keySet()){
            if(MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(s) == null){
                discardInventory(username);
            }
        }
        //Dynamic loading of user inventories.
        //Keep it loaded so it can be manipulated. It will be resaved on the next get.
        if(!invs.containsKey(username)){
            loadInventory(username);
        }
        return invs.get(username);
    }

    public void loadInventory(String username) {
        if (invs == null) {
            invs = new HashMap<String, RpgInv>();
        }
        RpgInv inv = new RpgInv(username);

        File file = new File(DimensionManager.getCurrentSaveRootDirectory(), RPG_DIR + File.separator + username + "Rpg.dat");
        if (file.exists()) // ONLY if the file exists...
        {
            try {
                NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(file));
                inv.readFromNBT(nbt);
            } catch (Exception e) {
                // log it as severe
                FMLCommonHandler.instance().getFMLLogger().severe("[RPGInventoryMod] Error reading RPG Inventory for player " + username);
                e.printStackTrace();
            }
        }
        invs.put(username, inv);
        inv.onInventoryChanged();
        
    }

    public void discardInventory(String username) {
        RpgInv inv = invs.remove(username);

        if (inv == null) // nothing to unload here.
        {
            return;
        }

        File file = new File(DimensionManager.getCurrentSaveRootDirectory(), RPG_DIR + File.separator + username + "Rpg.dat");
        if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();  // create the folders if they don' exist.
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            NBTTagCompound nbt = inv.writeToNBT(new NBTTagCompound());
            CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file));
        } catch (Exception e) {
            // log it as severe
            FMLCommonHandler.instance().getFMLLogger().severe("[RPGInventoryMod] Error writing RPG Inventory for player " + username);
            e.printStackTrace();
        }
    }
}
