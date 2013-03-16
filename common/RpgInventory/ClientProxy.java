package RpgInventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.DimensionManager;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import RpgInventory.gui.BookGui;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.playerjewels.RenderPlayerJewels;
import RpgInventory.weapons.bow.EntityHellArrow;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.Map.Entry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class ClientProxy extends CommonProxy {
    //Testing

    private HashMap<String, RpgInv> invs;
    public static int sphereID;
    public static boolean firstUpdate = false;

    public int getSphereID() {
        return sphereID;
    }
   
    public void registerRenderInformation() {
        MinecraftForgeClient.preloadTexture("/subaraki/RPGinventoryTM.png");
        KeyBindingRegistry.registerKeyBinding(new RPGKeyHandler());
        RenderingRegistry.registerEntityRenderingHandler(EntityHellArrow.class, new RenderArrow());


        Sphere sphere = new Sphere();
        //GLU_POINT will render it as dots.
        //GLU_LINE will render as wireframe
        //GLU_SILHOUETTE will render as ?shadowed? wireframe
        //GLU_FILL as a solid.
        sphere.setDrawStyle(GLU.GLU_FILL);
        //GLU_SMOOTH will try to smoothly apply lighting
        //GLU_FLAT will have a solid brightness per face, and will not shade.
        //GLU_NONE will be completely solid, and probably will have no depth to it's appearance.        
        sphere.setNormals(GLU.GLU_FLAT);
        //GLU_INSIDE will render as if you are inside the sphere, making it appear inside out.(Similar to how ender portals are rendered)
        sphere.setOrientation(GLU.GLU_OUTSIDE);

        sphere.setTextureFlag(true);
        //Simple 1x1 red texture to serve as the spheres skin, the only pixel in this image is red.
        //sphereID is returned from our sphereID() method
        sphereID = GL11.glGenLists(1);
        //Create a new list to hold our sphere data.
        GL11.glNewList(sphereID, GL11.GL_COMPILE);
        //Offset the sphere by it's radius so it will be centered
        GL11.glTranslatef((float) 0.50F, (float) 0.50F, (float) 0.50F);
        //Call our string that we mapped to our texture
//        ForgeHooksClient.bindTexture("/subaraki/jewels/talisman.png", 0);
        //The drawing the sphere is automattically doing is getting added to our list. Careful, the last 2 variables 
        //control the detail, but have a massive impact on performance. 32x32 is a good balance on my machine.
        //GLU.
        sphere.draw(0.5F, 12, 24);
        //Drawing done, unbind our texture
//        ForgeHooksClient.unbindTexture();
        //Tell LWJGL that we are done creating our list.
        GL11.glEndList();
    }

    public void registerLate() {
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        
        RenderPlayerJewels renderballs = new RenderPlayerJewels(new ModelBiped());
        //Ok guys. This is a workaround for other mods the hook the player render(smart moving)
        //Basically we want to learn the currently bound renderers, and use them to
        //render the player, and then render our items.
        Map<Class<? extends Entity>, Render> map = RenderManager.instance.entityRenderMap;
        for (Entry<Class<? extends Entity>, Render> entry : map.entrySet()) {
            if (EntityPlayer.class.isAssignableFrom(entry.getKey())) {
                Class clazz = entry.getValue().getClass();
                    RenderPlayerJewels.defaultPlayerRender.put(entry.getKey(), entry.getValue());
                    RenderingRegistry.registerEntityRenderingHandler(entry.getKey(), renderballs);
                    
            }
        }
        RenderingRegistry.instance().loadEntityRenderers(RenderManager.instance.entityRenderMap);
    }

    public void openGUI(EntityPlayer p1, int id) {
        switch (id) {
            case 1:
                if (Minecraft.getMinecraft().playerController.isInCreativeMode()) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiContainerCreative(p1));
                } else {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(p1));
                }
                break;
            case 2:
                Minecraft.getMinecraft().displayGuiScreen(new BookGui(p1));
                break;
            case 3:
                Minecraft.getMinecraft().displayGuiScreen(new RpgInventory.gui.pet.PetGui(p1));

        }
    }

    public RpgInv getInventory(String username) {
        if (invs == null) {
            invs = new HashMap();
        }
        if (invs.get(username) == null) {
            invs.put(username, new RpgInv(username));
        }
        return invs.get(username);
    }

    public void addEntry(String username, RpgInv inv) {
        if (invs == null) {
            invs = new HashMap();
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        if ((username.equals(Minecraft.getMinecraft().thePlayer.username) && !firstUpdate)) {
            inv.onInventoryChanged();
            invs.put(username, inv);
            firstUpdate = true;
        } else if (!username.equals(Minecraft.getMinecraft().thePlayer.username)) {
            invs.put(username, inv);
        }
    }

    public void loadInventory(String username) {
        RpgInv inv = new RpgInv(username);

        File file = new File(DimensionManager.getCurrentSaveRootDirectory(), "InventoryRPG.dat");
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
        if (invs == null) {
            invs = new HashMap();
        }
        invs.put(username, inv);
    }

    public void discardInventory(String username) {
        if (invs.get(username) == null) // nothing to unload here.
        {
            return;
        }

        File file = new File(DimensionManager.getCurrentSaveRootDirectory(), "InventoryRPG.dat");
        if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();  // create the folders if they don' exist.
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            NBTTagCompound nbt = invs.get(username).writeToNBT(new NBTTagCompound());
            CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file));

        } catch (Exception e) {
            // log it as severe
            FMLCommonHandler.instance().getFMLLogger().severe("[RPGInventoryMod] Error writing RPG Inventory for player " + username);
            e.printStackTrace();
        }
    }
}
