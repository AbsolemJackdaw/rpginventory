package rpgInventory.handlers;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.PacketInventory;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CommonTickHandler implements ITickHandler {

	/*
	 * TickEvent.Tick s% 
	 * use with @SubscribeEvent 
	 * 
	 * 
	 * */
	
	
	
	/**
	 * Used to count down 1 second and send a package to every player on the
	 * server with info about the player's inventory
	 */
	private static int countdown = 20;
	public static Map<String, Integer> globalCooldownMap = new ConcurrentHashMap();

	private final int lapisTimer = 20 * 20;
	private int countDownLapis = lapisTimer;

	public void dropJewels(EntityPlayer player) {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			if (player.worldObj.getGameRules().getGameRuleBooleanValue(
					"keepInventory") == false) {
				PlayerRpgInventory rpg = PlayerRpgInventory.get(player);
				int var1;
				player.inventory.dropAllItems();
				for (var1 = 0; var1 < rpg.armorSlots.length; ++var1) {
					if (rpg.getStackInSlot(var1) != null) {
						player.inventory.setInventorySlotContents(
								player.inventory.getFirstEmptyStack(),
								rpg.getStackInSlot(var1));
						rpg.setInventorySlotContents(var1, null);
					}
				}
				player.inventory.dropAllItems();
				// System.out.println("JEWELS DROPPED!");
			}
		}
	}

	@Override
	public String getLabel() {
		return "RpgInventoryServ";
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		for (Entry<String, Integer> entry : globalCooldownMap.entrySet()) {
			if (entry.getValue() > 0) {
				entry.setValue(entry.getValue() - 1);
			}
			if (mod_RpgInventory.developers.contains(entry.getKey()
					.toLowerCase())) {
				entry.setValue(0);
			}
		}

		List<EntityPlayer> players = MinecraftServer
				.getServerConfigurationManager(MinecraftServer.getServer()).playerEntityList;
		for (EntityPlayer player : players) {
			if ((player.getHealth() <= 0) || player.isDead) {
				if (!player.worldObj.getGameRules().getGameRuleBooleanValue(
						"keepInventory")) {
					dropJewels(player);
				}
			}
			if (countdown == 0) {
				//
				PacketInventory.sendServerPacket(player);
			}
		}

		countdown--;
		if (countdown < 0) {
			countdown = 20;
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
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

		for (String username : RPGEventHooks.ArcherRepairTick.keySet()) {
			try {
				if (RPGEventHooks.ArcherRepairTick.get(username) > 0) {
					RPGEventHooks.ArcherRepairTick.put(username,
							RPGEventHooks.ArcherRepairTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.ArcherRepairTick.remove(username);
						continue;
					}
					if (player.getCurrentEquippedItem() != null) {
						if ((player.getCurrentEquippedItem().getItem() instanceof ItemBow)
								|| player.getCurrentEquippedItem().getItem()
										.equals(mod_RpgInventory.elfbow)) {
							if (!player.isUsingItem()) {
								RPGEventHooks.ArcherRepairTick.put(
										player.getDisplayName(), 60);
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
		for (String username : RPGEventHooks.DiamondTick.keySet()) {
			try {
				if (RPGEventHooks.DiamondTick.get(username) > 0) {
					RPGEventHooks.DiamondTick.put(username,
							RPGEventHooks.DiamondTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.DiamondTick.remove(username);
						continue;
					}

					PlayerRpgInventory rpginv = PlayerRpgInventory.get(player);
					if (rpginv == null) {
						continue;
					}
					int delay;
					delay = mod_RpgInventory.donators.contains(player
							.getDisplayName()) ? 65 : 75;
					if ((rpginv.getNecklace() != null)
							&& rpginv.getNecklace().getItem()
									.equals(mod_RpgInventory.neckdia)) {
						delay -= 10;
					}
					if ((rpginv.getGloves() != null)
							&& rpginv.getGloves().getItem()
									.equals(mod_RpgInventory.glovesdia)) {
						delay -= 10;
					}
					if ((rpginv.getRing1() != null)
							&& rpginv.getRing1().getItem()
									.equals(mod_RpgInventory.ringdia)) {
						delay -= 10;
					}
					if ((rpginv.getRing2() != null)
							&& rpginv.getRing2().getItem()
									.equals(mod_RpgInventory.ringdia)) {
						delay -= 10;
					}
					RPGEventHooks.DiamondTick.put(player.getDisplayName(),
							delay);
					if (player.getHealth() < player.getMaxHealth()) {
						player.heal(1);
					}
				}
			} catch (Throwable ex) {
			}
		}
		for (String username : RPGEventHooks.HealerTick.keySet()) {
			try {
				if (RPGEventHooks.HealerTick.get(username) > 0) {
					RPGEventHooks.HealerTick.put(username,
							RPGEventHooks.HealerTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.HealerTick.remove(username);
						continue;
					}
					if (player.getCurrentEquippedItem() != null) {
						if (player.getCurrentEquippedItem().getItem()
								.equals(mod_RpgInventory.staf)) {
							if (player.isUsingItem()) {
								RPGEventHooks.HealerTick.put(
										player.getDisplayName(), 30);
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

		for (String username : RPGEventHooks.LapisTick.keySet()) {
			try {
				if (RPGEventHooks.LapisTick.get(username) > 0) {
					RPGEventHooks.LapisTick.put(username,
							RPGEventHooks.LapisTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer()
							.getConfigurationManager()
							.getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.LapisTick.remove(username);
						continue;
					}
					PlayerRpgInventory rpginv = PlayerRpgInventory.get(player);
					if (rpginv == null) {
						continue;
					}
					int heal = 0;
					if ((rpginv.getNecklace() != null)
							&& rpginv.getNecklace().getItem()
									.equals(mod_RpgInventory.necklap)) {
						heal++;
					}
					if ((rpginv.getGloves() != null)
							&& rpginv.getGloves().getItem()
									.equals(mod_RpgInventory.gloveslap)) {
						heal++;
					}
					if ((rpginv.getRing1() != null)
							&& rpginv.getRing1().getItem()
									.equals(mod_RpgInventory.ringlap)) {
						heal++;
					}
					if ((rpginv.getRing2() != null)
							&& rpginv.getRing2().getItem()
									.equals(mod_RpgInventory.ringlap)) {
						heal++;
					}

					if (player.getCurrentEquippedItem() != null) {
						ItemStack stack = player.getCurrentEquippedItem();
						countDownLapis--;
						if (stack.isItemDamaged() && (stack.stackSize == 1)
								&& (stack.getMaxStackSize() == 1)) {
							if (stack.getItemDamage() <= stack.getMaxDamage()) {
								if (countDownLapis <= 0) {
									stack.setItemDamage(stack.getItemDamage()
											- heal);
									countDownLapis = mod_RpgInventory.donators
											.contains(player.getDisplayName()) ? 15 * 20
											: lapisTimer;
								}
							}
						}
					}
				}
			} catch (Throwable ex) {
			}
		}

		for (String username : RPGEventHooks.CustomPotionList.keySet()) {
			try {
				EntityPlayer p = MinecraftServer.getServer()
						.getConfigurationManager()
						.getPlayerForUsername(username);
				PotionEffect decompose = p
						.getActivePotionEffect(mod_RpgInventory.decomposePotion);
				PotionEffect machicism = p
						.getActivePotionEffect(mod_RpgInventory.masochismPotion);
				if (decompose != null) {
					if ((RPGEventHooks.CustomPotionList.get(username) - 20) > decompose
							.getDuration()) {
						RPGEventHooks.CustomPotionList.put(username,
								decompose.getDuration());
						if (p.getHealth() > 1) {
							p.attackEntityFrom(DamageSource.generic, 1);
						}
					}
				}
				if (machicism != null) {
					if ((RPGEventHooks.CustomPotionList.get(username) - 20) > machicism
							.getDuration()) {
						RPGEventHooks.CustomPotionList.put(username,
								machicism.getDuration());
						if (p.getHealth() < p.getMaxHealth()) {
							// while staf is being used, regen the healer
							p.heal(1);
						}
					}
				}
			} catch (Throwable ex) {
			}
		}

	}
}
