package addonDread;

import net.minecraft.client.model.ModelBiped;
import addonBasic.mod_addonBase;
import addonDread.packets.DreadServerPacketHandler;

public class CommonProxyRpgplus {

	public ModelBiped getArmorModel(int id) {
		return null;
	}

	public void registerRenderInformation() {
		// tick registered both sides behind an EFFECTIVE check
		// So integrated server will register too.
		
		mod_RpgPlus.Channel.register(new DreadServerPacketHandler());

	}

}
