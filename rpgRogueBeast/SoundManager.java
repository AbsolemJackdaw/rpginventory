package rpgRogueBeast;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SoundManager {

	@SubscribeEvent
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("subaraki:petWhistle.ogg");
	}
}
