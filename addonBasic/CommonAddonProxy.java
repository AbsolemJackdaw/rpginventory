package addonBasic;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.ServerPacketHandler;

public class CommonAddonProxy {

	public ModelBiped getArmorModel(int id) {
		return null;
	}

	public void openGUI(EntityPlayer p, PlayerRpgInventory inventory) {

	}

	public void registerRenderInformation() {
		mod_addonBase.Channel.register(new ServerPacketHandler());
	}

}
