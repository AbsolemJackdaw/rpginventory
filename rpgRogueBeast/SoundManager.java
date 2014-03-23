package rpgRogueBeast;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;

public class SoundManager {
	
	@SubscribeEvent
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("subaraki:petWhistle.ogg");
	}
}
