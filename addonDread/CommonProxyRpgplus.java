package addonDread;

import net.minecraft.client.model.ModelBiped;
import addonBasic.RpgBaseAddon;
import addonDread.packets.DreadServerPacketHandler;

public class CommonProxyRpgplus {

	public ModelBiped getArmorModel(int id) {
		return null;
	}

	public void registerRenderInformation() {
		// tick registered both sides behind an EFFECTIVE check
		// So integrated server will register too.
		

	}

}
