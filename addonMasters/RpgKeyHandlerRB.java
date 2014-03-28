package addonMasters;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import rpgInventory.handlers.RPGKeyHandler;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class RpgKeyHandlerRB extends RPGKeyHandler {

	@Override
	public void keys(KeyInputEvent evt) {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (keySpecial.isPressed()) {
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if ((guiscreen == null) && !(item == null)) {
					specialAbility(item);
				}
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public void specialAbility(ItemStack item) {
		try {
			if (item.getItem().equals(mod_RpgRB.daggers)) {
				ByteArrayOutputStream bt = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bt);
				try {
					out.writeInt(14);

					// TODO
					System.out.println();
					// Packet250CustomPayload packet = new
					// Packet250CustomPayload(
					// "RpgRBPacket", bt.toByteArray());
					// PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException ex) {
					Logger.getLogger(RPGKeyHandler.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		} catch (Throwable e) {
		}
	}
}
