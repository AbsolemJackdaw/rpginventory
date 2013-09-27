package rpgRogueBeast;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundManager {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("subaraki:petWhistle.ogg");
	}
}
