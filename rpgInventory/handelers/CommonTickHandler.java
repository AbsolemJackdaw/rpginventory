package rpgInventory.handelers;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommonTickHandler implements ITickHandler {
	
	/**Used to count down 1 second and send a package to every player on the server 
	 * with info about the player's inventory*/
	private static int countdown = 20;
	public static Map<String, Integer> globalCooldownMap = new ConcurrentHashMap();

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

		for (String username : RPGEventHooks.ArcherRepairTick.keySet()) {
			try {
				if (RPGEventHooks.ArcherRepairTick.get(username) > 0) {
					RPGEventHooks.ArcherRepairTick.put(username, RPGEventHooks.ArcherRepairTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.ArcherRepairTick.remove(username);
						continue;
					}
					if (player.getCurrentEquippedItem() != null) {
						if (player.getCurrentEquippedItem().getItem() instanceof ItemBow || player.getCurrentEquippedItem().getItem().equals(mod_RpgInventory.elfbow)) {
							if (!player.isUsingItem()) {
								RPGEventHooks.ArcherRepairTick.put(player.username, 60);
								if (player.inventory.getCurrentItem().getItemDamage() <= 1) {
									player.inventory.getCurrentItem().setItemDamage(0);
								} else {
									player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() - 1);
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
					RPGEventHooks.DiamondTick.put(username, RPGEventHooks.DiamondTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.DiamondTick.remove(username);
						continue;
					}
					RpgInv rpginv = mod_RpgInventory.proxy.getInventory(username);
					if (rpginv == null) {
						continue;
					}
					int delay = 70;
					if (rpginv.getNecklace() != null && rpginv.getNecklace().getItem().equals(mod_RpgInventory.neckdia)) {
						delay -= 10;
					}
					if (rpginv.getGloves() != null && rpginv.getGloves().getItem().equals(mod_RpgInventory.glovesdia)) {
						delay -= 10;
					}
					if (rpginv.getRing1() != null && rpginv.getRing1().getItem().equals(mod_RpgInventory.ringdia)) {
						delay -= 10;
					}
					if (rpginv.getRing2() != null && rpginv.getRing2().getItem().equals(mod_RpgInventory.ringdia)) {
						delay -= 10;
					}
					RPGEventHooks.DiamondTick.put(player.username, delay);
					if (player.func_110143_aJ() < player.func_110138_aP()) {
						player.heal(1);
					}
				}
			} catch (Throwable ex) {
			}
		}
		for (String username : RPGEventHooks.HealerTick.keySet()) {
			try {
				if (RPGEventHooks.HealerTick.get(username) > 0) {
					RPGEventHooks.HealerTick.put(username, RPGEventHooks.HealerTick.get(username) - 1);
				} else {
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
					if (player == null) {
						RPGEventHooks.HealerTick.remove(username);
						continue;
					}
					if (player.getCurrentEquippedItem() != null) {
						if (player.getCurrentEquippedItem().getItem().equals(mod_RpgInventory.staf)) {
							if (player.isUsingItem()) {
								RPGEventHooks.HealerTick.put(player.username, 30);
								if (player.func_110143_aJ() < player.func_110138_aP()) {
									//while staf is being used, regen the healer
									player.heal(1);
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
				EntityPlayer p = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
				PotionEffect decompose = p.getActivePotionEffect(mod_RpgInventory.decomposePotion);
				PotionEffect machicism = p.getActivePotionEffect(mod_RpgInventory.masochismPotion);
				if (decompose != null) {
					if (RPGEventHooks.CustomPotionList.get(username) - 20 > decompose.getDuration()) {
						RPGEventHooks.CustomPotionList.put(username, decompose.getDuration());
						if (p.func_110143_aJ() > 1) {
							p.attackEntityFrom(DamageSource.generic, 1);
						}
					}
				}
				if (machicism != null) {
					if (RPGEventHooks.CustomPotionList.get(username) - 20 > machicism.getDuration()) {
						RPGEventHooks.CustomPotionList.put(username, machicism.getDuration());
						if (p.func_110143_aJ() < p.func_110138_aP()) {
							//while staf is being used, regen the healer
							p.heal(1);
						}
					}
				}
			} catch (Throwable ex) {
			}
		}

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		for (Entry<String, Integer> entry : globalCooldownMap.entrySet()) {
			if (entry.getValue() > 0) {
				entry.setValue(entry.getValue() - 1);
			}
			if (mod_RpgInventory.developers.contains(entry.getKey().toLowerCase())) {
				entry.setValue(0);
			}
		}
		
		List<EntityPlayer> players = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).playerEntityList;
		for (EntityPlayer player : players) {
			if (player.func_110143_aJ() <= 0 || player.isDead) {
				if (!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
					dropJewels(player);
				}
			}
			if (countdown == 0) {
				try {
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setString("username", player.username);
					NBTTagList list = new NBTTagList("items");
					RpgInv inv = mod_RpgInventory.proxy.getInventory(player.username);
					for (int i = 0; i < inv.armorSlots.length; i++) {
						//This is safe, an empty NBTTag just just returns a null ItemStack
						//when processed, however appending a null compound tag explodes.
						NBTTagCompound tc = new NBTTagCompound();
						if (inv.armorSlots[i] != null) {
							tc = inv.armorSlots[i].writeToNBT(tc);
						}
						list.appendTag(tc);
					}					
					nbt.setTag("items", list);
										
					Packet250CustomPayload packet = new Packet250CustomPayload("RpgRawInv", CompressedStreamTools.compress(nbt));
					PacketDispatcher.sendPacketToAllPlayers(packet);
				} catch (Throwable ex) {
					ex.printStackTrace();
				}
			}
		}

		countdown--;
		if (countdown < 0) {
			countdown = 20;
		}
	}

	public void dropJewels(EntityPlayer player) {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			if (player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") == false) {
				RpgInv rpg = mod_RpgInventory.proxy.getInventory(player.username);
				int var1;
				player.inventory.dropAllItems();
				for (var1 = 0; var1 < rpg.armorSlots.length; ++var1) {
					if (rpg.getStackInSlot(var1) != null) {
						player.inventory.setInventorySlotContents(player.inventory.getFirstEmptyStack(), rpg.getStackInSlot(var1));
						rpg.setInventorySlotContents(var1, null);
					}
				}
				player.inventory.dropAllItems();
				//System.out.println("JEWELS DROPPED!");
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
