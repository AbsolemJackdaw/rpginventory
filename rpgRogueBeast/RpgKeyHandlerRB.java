package rpgRogueBeast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.RPGKeyHandler;
import rpgInventory.utils.AbstractKeyHandler;
import rpgRogueBeast.packets.RpgRBPacketHandler;

public class RpgKeyHandlerRB extends AbstractKeyHandler {

	@Override
	protected void specialAbility(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, ItemStack item) {
		try {
			if (item.getItem().equals(mod_RpgRB.daggers)) {
				ByteArrayOutputStream bt = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bt);
				try {
					out.writeInt(14);
					Packet250CustomPayload packet = new Packet250CustomPayload("RpgRBPacket", bt.toByteArray());
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException ex) {
					Logger.getLogger(RPGKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} catch (Throwable e) {
		}		
	}
}
