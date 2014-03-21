package rpgRogueBeast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import rpgInventory.handlers.RPGKeyHandler;

public class RpgKeyHandlerRB extends RPGKeyHandler {

	@Override
	public String getLabel() {
		return "RpgRBHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (kb.keyDescription.equals("RPG Special Ability")) {
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if ((guiscreen == null) && !(item == null)) {
					specialAbility(types, kb, tickEnd, item);
				}
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public void specialAbility(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, ItemStack item) {
		try {
			if (item.getItem().equals(mod_RpgRB.daggers)) {
				ByteArrayOutputStream bt = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bt);
				try {
					out.writeInt(14);
					Packet250CustomPayload packet = new Packet250CustomPayload(
							"RpgRBPacket", bt.toByteArray());
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException ex) {
					Logger.getLogger(RPGKeyHandler.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}
