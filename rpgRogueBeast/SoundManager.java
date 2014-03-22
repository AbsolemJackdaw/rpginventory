package rpgRogueBeast;

import net.minecraftforge.client.event.sound.SoundLoadEvent;

public class SoundManager {
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("subaraki:petWhistle.ogg");
	}
}
