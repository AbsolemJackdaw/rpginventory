package rpgInventory.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.MouseEvent;

import org.lwjgl.input.Mouse;

import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.PacketSyncBlockShield;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;

public class MouseHandler {

	//this runs on client only
	@SubscribeEvent
	public void mouseInput(MouseInputEvent ev){
		//right click
		if(Mouse.getEventButton() == 1){
			if(Mouse.isButtonDown(1) ){
				//Set blocking
//				PlayerRpgInventory.get(Minecraft.getMinecraft().thePlayer).setBlocking(true);
				RpgInventoryMod.SNW.sendToServer(new PacketSyncBlockShield(true));

			}else{
//				PlayerRpgInventory.get(Minecraft.getMinecraft().thePlayer).setBlocking(false);
				RpgInventoryMod.SNW.sendToServer(new PacketSyncBlockShield(false));
			}
		}
	}

	@SubscribeEvent
	public void mouse(MouseEvent ev){
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		PlayerRpgInventory inv = PlayerRpgInventory.get(p);

		if(inv.getShield() == null)
			return;
		if(ev.button == 0 && PlayerRpgInventory.get(Minecraft.getMinecraft().thePlayer).isBlocking()){
			ev.setCanceled(true);
		}
	}
}
