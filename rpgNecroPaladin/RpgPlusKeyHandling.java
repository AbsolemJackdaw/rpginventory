package rpgNecroPaladin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import rpgInventory.handlers.RPGKeyHandler;
import rpgInventory.utils.AbstractKeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RpgPlusKeyHandling extends AbstractKeyHandler{

	public RpgPlusKeyHandling(KeyBinding[] keyBindings, boolean[] repeatings) {
		super();
	}

	@Override
	protected void specialAbility(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, ItemStack item) {
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

}
