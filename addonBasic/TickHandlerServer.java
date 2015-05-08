package addonBasic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import rpgInventory.handlers.RPGEventHooks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class TickHandlerServer {

	@SubscribeEvent
	public void tickStart(ServerTickEvent ev) {

		/*
		 * hook onto already existing healing ticks and heal player every 30
		 * ticks if using the lunar staff
		 */

		for (String username : RPGEventHooks.HealerTick.keySet()) {
			if (RPGEventHooks.HealerTick.get(username) > 0) {
				RPGEventHooks.HealerTick.put(username,
						RPGEventHooks.HealerTick.get(username) - 1);
			} else {
				EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
				if (player == null) {
					RPGEventHooks.HealerTick.remove(username);
					continue;
				}
			}
		}


		for (String username : RPGEventHooks.HealerTick.keySet()) {
			if (RPGEventHooks.HealerTick.get(username) <= 0) {
				EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);

				if (player.getCurrentEquippedItem() != null) {
					if (player.getCurrentEquippedItem().getItem().equals(RpgBaseAddon.lunarStaff)) {
						if (player.isUsingItem()) {
							RPGEventHooks.HealerTick.put(player.getDisplayName(), 30);
							if (player.getHealth() < player.getMaxHealth()) {
								// while staf is being used, regen the
								// healer
								player.heal(1);
							}
						}
					}
				}
			}
		}
	}
}
