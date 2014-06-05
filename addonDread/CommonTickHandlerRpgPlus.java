package addonDread;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.RpgInventoryMod;
import addonBasic.CommonTickHandler;
import addonBasic.RpgBaseAddon;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonTickHandlerRpgPlus {

	public static HashMap<String, Integer> rpgPluscooldownMap = new HashMap();

	@SubscribeEvent
	public void tickEnd(TickEvent.ServerTickEvent ev) {

		/*
		 * Manage cooldown mapused for spawning minions
		 */
		for (Entry<String, Integer> entry : rpgPluscooldownMap.entrySet()) {
			if (entry.getValue() > 0) {
				entry.setValue(entry.getValue() - 1);
			}
		}
	}

}
