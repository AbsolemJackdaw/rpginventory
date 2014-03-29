package addonDread;

import java.util.HashMap;
import java.util.Map.Entry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonTickHandlerRpgPlus /* implements ITickHandler */{

	public static HashMap<String, Integer> rpgPluscooldownMap = new HashMap();

	@SubscribeEvent
	public void tickEnd(TickEvent.ServerTickEvent ev) {

		/*
		 *Manage cooldown map
		 *used for spawning minions 
		 */
		for (Entry<String, Integer> entry : rpgPluscooldownMap.entrySet())
			if (entry.getValue() > 0)
				entry.setValue(entry.getValue() - 1);

	}
}
