/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters;

import addonMasters.packets.RBServerPacketHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.ServerPacketHandler;

public class RBCommonProxy {

	public ModelBiped getArmorModel(int id) {
		return null;
	}

	public void openGUI(EntityPlayer p, PlayerRpgInventory inventory) {

	}

	public void registerRendering() {
	}
}
