package rpgNecroPaladin;

import java.util.HashMap;
import java.util.Map.Entry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonTickHandlerRpgPlus /* implements ITickHandler */{

	public static HashMap<String, Integer> rpgPluscooldownMap = new HashMap();

	// @Override
	// public String getLabel() {
	// return "RpgInventoryServ";
	// }

	@SubscribeEvent
	public void tickEnd(TickEvent.ServerTickEvent ev) {
		for (Entry<String, Integer> entry : rpgPluscooldownMap.entrySet()) {
			if (entry.getValue() > 0) {
				entry.setValue(entry.getValue() - 1);
			}
		}
	}

	// /**
	// * called upon player's death. Will drop Jewels in the world
	// */
	// @Override
	// public EnumSet<TickType> ticks() {
	// return EnumSet.of(TickType.SERVER);
	// }
	//
	// @Override
	// public void tickStart(EnumSet<TickType> type, Object... tickData) {
	// }
}
