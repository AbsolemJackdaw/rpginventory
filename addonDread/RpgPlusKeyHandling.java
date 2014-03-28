package addonDread;

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

public class RpgPlusKeyHandling extends RPGKeyHandler {

	@Override
	public void keys(KeyInputEvent evt) {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (RPGKeyHandler.keySpecial.isPressed()) {
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if ((guiscreen == null) && !(item == null))
					specialAbility(item);
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public void specialAbility(ItemStack item) {

		try {
			if (item.getItem().equals(mod_RpgPlus.necro_weapon)) {
				ByteArrayOutputStream bt = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bt);
				try {
					out.writeInt(6);

					System.out.println("Send packet rpg++ keyhandler");
					// Packet250CustomPayload packet = new
					// Packet250CustomPayload(
					// "RpgPlusPlus", bt.toByteArray());
					// PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException ex) {
					Logger.getLogger(RPGKeyHandler.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		} catch (Throwable e) {
		}
		try {
			if (item.getItem().equals(mod_RpgPlus.pala_weapon)) {
				ByteArrayOutputStream bt = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bt);
				try {
					out.writeInt(9);
					// TODO
					System.out.println("add packet for paladin special here");
					// Packet250CustomPayload packet = new
					// Packet250CustomPayload(
					// "RpgPlusPlus", bt.toByteArray());
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
