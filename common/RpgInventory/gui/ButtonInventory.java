/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgInventory.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 *
 * @author Richard Smith
 */
@SideOnly(Side.CLIENT)
public class ButtonInventory extends GuiButton {
    private EntityPlayer player;
    public ButtonInventory(int par1, int par2, int par3, String par4Str) {
        super(par1, par2, par3, par4Str);
        player = Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public void mouseReleased(int par1, int par2) {
        super.mouseReleased(par1, par2);
        switch(this.id){
            case 0:
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(bytes);
            try {
                outputStream.writeInt(1);
                Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
                PacketDispatcher.sendPacketToServer(packet);
                System.out.println("Packet send");
            } catch (IOException e) {
                e.printStackTrace();
            }
                break;
            case 1:
                player.closeScreen();
                break;
            case 2:
                //TODO:Add pet shortcut
        }
    }
}
