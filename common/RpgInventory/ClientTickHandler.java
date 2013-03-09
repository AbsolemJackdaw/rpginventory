package RpgInventory;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;

public class ClientTickHandler implements ITickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if (Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null) {
            if (Minecraft.getMinecraft().currentScreen.getClass() == GuiInventory.class) {
                Minecraft.getMinecraft().thePlayer.openGui(mod_RpgInventory.instance, 0, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer.serverPosX, Minecraft.getMinecraft().thePlayer.serverPosY, Minecraft.getMinecraft().thePlayer.serverPosZ);
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "RpgInventory";
    }
}
