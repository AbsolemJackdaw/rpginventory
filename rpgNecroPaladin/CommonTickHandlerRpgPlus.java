package rpgNecroPaladin;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map.Entry;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandlerRpgPlus implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}
        public static HashMap<String, Integer> rpgPluscooldownMap = new HashMap();
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
            for(Entry<String, Integer> entry: rpgPluscooldownMap.entrySet()){
                if(entry.getValue() > 0){
                    entry.setValue(entry.getValue() - 1);
                }
            }
	}

	/**
	 * called upon player's death. Will drop Jewels in the world
	 */
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return "RpgInventoryServ";
	}
}
