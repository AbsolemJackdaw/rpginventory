package rpgInventory.handlers;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonTickHandler{

	/*
	 * WARNING NAMES OF METHODS TICKEND AND TICKSTART ARE OF NO IMPORTANCE i
	 * just kept them from the old handler
	 */

	/**
	 * Used to count down 1 second and send a package to every player on the
	 * server with info about the player's inventory
	 */
	private static int countdown = 20;
	public static Map<String, Integer> globalCooldownMap = new ConcurrentHashMap();

	private final int lapisTimer = 20 * 20;
	private int countDownLapis = lapisTimer;

	@SubscribeEvent
	public void tickEnd(TickEvent.ServerTickEvent ev) {

		List<EntityPlayer> players = MinecraftServer.getServer()
				.getConfigurationManager().playerEntityList;

		for (Entry<String, Integer> entry : globalCooldownMap.entrySet()) {
			if (entry.getValue() > 0)
				entry.setValue(entry.getValue() - 1);
			if (RpgInventoryMod.developers.contains(entry.getKey()
					.toLowerCase()))
				entry.setValue(0);
		}


		for (EntityPlayer player : players) {

			if (countdown == 0)
				continue;

			//TODO send info to other players in packets
		}

		countdown--;
		if (countdown < 0)
			countdown = 20;

		for (String username : RPGEventHooks.DiamondTick.keySet())
			try {
				if (RPGEventHooks.DiamondTick.get(username) > 0)
					RPGEventHooks.DiamondTick.put(username,
							RPGEventHooks.DiamondTick.get(username) - 1);
				else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.DiamondTick.remove(username);
						continue;
					}

					PlayerRpgInventory rpginv = PlayerRpgInventory.get(player);
					if (rpginv == null)
						continue;
					int delay;
					delay = RpgInventoryMod.donators.contains(player
							.getCommandSenderName()) ? 65 : 75;
					if ((rpginv.getNecklace() != null)
							&& rpginv.getNecklace().getItem()
							.equals(RpgInventoryMod.neckdia))
						delay -= 10;
					if ((rpginv.getGloves() != null)
							&& rpginv.getGloves().getItem()
							.equals(RpgInventoryMod.glovesdia))
						delay -= 10;
					if ((rpginv.getRing1() != null)
							&& rpginv.getRing1().getItem()
							.equals(RpgInventoryMod.ringdia))
						delay -= 10;
					if ((rpginv.getRing2() != null)
							&& rpginv.getRing2().getItem()
							.equals(RpgInventoryMod.ringdia))
						delay -= 10;
					RPGEventHooks.DiamondTick.put(
							player.getCommandSenderName(), delay);
					if (player.getHealth() < player.getMaxHealth())
						player.heal(1);
				}
			} catch (Throwable ex) {
			}
		for (String username : RPGEventHooks.HealerTick.keySet())
			try {
				if (RPGEventHooks.HealerTick.get(username) > 0)
					RPGEventHooks.HealerTick.put(username,
							RPGEventHooks.HealerTick.get(username) - 1);
				else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.HealerTick.remove(username);
						continue;
					}
				}
			} catch (Throwable ex) {
			}

		for (String username : RPGEventHooks.LapisTick.keySet())
			try {
				if (RPGEventHooks.LapisTick.get(username) > 0)
					RPGEventHooks.LapisTick.put(username,
							RPGEventHooks.LapisTick.get(username) - 1);
				else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.LapisTick.remove(username);
						continue;
					}
					PlayerRpgInventory rpginv = PlayerRpgInventory.get(player);
					if (rpginv == null)
						continue;
					int heal = 0;
					if ((rpginv.getNecklace() != null)
							&& rpginv.getNecklace().getItem()
							.equals(RpgInventoryMod.necklap))
						heal++;
					if ((rpginv.getGloves() != null)
							&& rpginv.getGloves().getItem()
							.equals(RpgInventoryMod.gloveslap))
						heal++;
					if ((rpginv.getRing1() != null)
							&& rpginv.getRing1().getItem()
							.equals(RpgInventoryMod.ringlap))
						heal++;
					if ((rpginv.getRing2() != null)
							&& rpginv.getRing2().getItem()
							.equals(RpgInventoryMod.ringlap))
						heal++;

					if (player.getCurrentEquippedItem() != null) {
						ItemStack stack = player.getCurrentEquippedItem();
						countDownLapis--;
						if (stack.isItemDamaged() && (stack.stackSize == 1)
								&& (stack.getMaxStackSize() == 1))
							if (stack.getItemDamage() <= stack.getMaxDamage())
								if (countDownLapis <= 0) {
									stack.setItemDamage(stack.getItemDamage()
											- heal);
									countDownLapis = RpgInventoryMod.donators
											.contains(player
													.getCommandSenderName()) ? 15 * 20
															: lapisTimer;
								}
					}
				}
			} catch (Throwable ex) {
			}
	}
}
