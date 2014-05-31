package addonBasic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.server.MinecraftServer;
import rpgInventory.handlers.RPGEventHooks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonTickHandler {

	public static Map<String, Integer> ArcherRepairTick = new ConcurrentHashMap();

	@SubscribeEvent
	public void tickStart(TickEvent.ServerTickEvent ev) {

		for (String username : ArcherRepairTick.keySet()) {
			try {
				if (ArcherRepairTick.get(username) > 0) {
					ArcherRepairTick.put(username,
							ArcherRepairTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						ArcherRepairTick.remove(username);
						continue;
					}

					if (player.getCurrentEquippedItem() != null) {
						if ((player.getCurrentEquippedItem().getItem() instanceof ItemBow)
								|| player.getCurrentEquippedItem().getItem()
								.equals(RpgBaseAddon.elfbow)) {
							if (!player.isUsingItem()) {
								ArcherRepairTick.put(
										player.getCommandSenderName(), 60);
								if (player.inventory.getCurrentItem()
										.getItemDamage() <= 1) {
									player.inventory.getCurrentItem()
									.setItemDamage(0);
								} else {
									player.inventory
									.getCurrentItem()
									.setItemDamage(
											player.inventory
											.getCurrentItem()
											.getItemDamage() - 1);
								}
							}
						}
					}
				}
			} catch (Throwable ex) {
			}
		}

		/*
		 * hook onto already existing healing ticks and heal player every 30
		 * ticks if using the lunar staff
		 */
		for (String username : RPGEventHooks.HealerTick.keySet()) {
			try {
				if (RPGEventHooks.HealerTick.get(username) <= 0) {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);

					if (player.getCurrentEquippedItem() != null) {
						if (player.getCurrentEquippedItem().getItem()
								.equals(RpgBaseAddon.staf)) {
							if (player.isUsingItem()) {
								RPGEventHooks.HealerTick.put(
										player.getCommandSenderName(), 30);
								if (player.getHealth() < player.getMaxHealth()) {
									// while staf is being used, regen the
									// healer
									player.heal(1);
								}
							}
						}
					}
				}
			} catch (Throwable ex) {
			}
		}

	}
}
