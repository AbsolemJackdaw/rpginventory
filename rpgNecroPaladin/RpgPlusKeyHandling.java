package rpgNecroPaladin;

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
import net.minecraft.network.packet.Packet250CustomPayload;
import rpgInventory.handlers.RPGKeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RpgPlusKeyHandling extends RPGKeyHandler {


	@Override
	public void specialAbility(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, ItemStack item) {
		
		try {
			if (item.getItem().equals(mod_RpgPlus.necro_weapon)) {
				ByteArrayOutputStream bt = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bt);
				try {
					out.writeInt(6);
					Packet250CustomPayload packet = new Packet250CustomPayload("RpgPlusPlus", bt.toByteArray());
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException ex) {
					Logger.getLogger(RPGKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
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
					Packet250CustomPayload packet = new Packet250CustomPayload("RpgPlusPlus", bt.toByteArray());
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException ex) {
					Logger.getLogger(RPGKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} catch (Throwable e) {
		}		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "RpgPlusHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (kb.keyDescription.equals("RPG Special Ability")) {
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if (guiscreen == null && !(item == null)) {
					specialAbility(types, kb, tickEnd, item);
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
