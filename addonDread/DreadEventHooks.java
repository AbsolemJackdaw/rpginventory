package addonDread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rpgInventory.RpgInventoryMod;
import addonBasic.RpgBaseAddon;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DreadEventHooks {
	public static Map<String, Integer> CustomPotionList = new ConcurrentHashMap();


	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) evt.entityLiving;
			if (p != null) {

				ItemStack weapon = p.getCurrentEquippedItem();
				if (weapon != null) {
					if (weapon.getItem() == RpgDreadAddon.paladinSword) {
						if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSPALADINSHIELD)) {
							Map tmp = EnchantmentHelper.getEnchantments(weapon);
							tmp.put(Enchantment.smite.effectId, 3);
							EnchantmentHelper.setEnchantments(tmp,weapon);
						} else if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSPALADIN)) {
							Map tmp = EnchantmentHelper.getEnchantments(weapon);
							tmp.put(Enchantment.smite.effectId, 2);
							EnchantmentHelper.setEnchantments(tmp, weapon);
						} else {
							Map tmp = EnchantmentHelper.getEnchantments(weapon);
							tmp.remove(Enchantment.smite.effectId);
							EnchantmentHelper.setEnchantments(tmp, weapon);
						}
					}
				}
			}
		}

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {
					// TODO place potion effects under addonDread
					if ((p.getActivePotionEffects() != null)
							&& (p.getActivePotionEffects().size() > 0)) {
						PotionEffect decompose = p
								.getActivePotionEffect(RpgDreadAddon.decomposePotion);
						PotionEffect machicism = p
								.getActivePotionEffect(RpgDreadAddon.masochismPotion);
						if ((decompose != null) && (machicism != null)) {
							p.removePotionEffect(RpgDreadAddon.decomposePotion.id);
							p.removePotionEffect(RpgDreadAddon.masochismPotion.id);
							CustomPotionList.remove(p.getCommandSenderName());
						} else if (decompose != null) {
							if (decompose.getDuration() == 0) {
								p.removePotionEffect(RpgDreadAddon.decomposePotion.id);
								CustomPotionList.remove(p
										.getCommandSenderName());
							} else if (!CustomPotionList.containsKey(p
									.getCommandSenderName())) {
								CustomPotionList.put(p.getCommandSenderName(),
										decompose.getDuration());
							}
						} else if (machicism != null) {
							if (machicism.getDuration() == 0) {
								p.removePotionEffect(RpgDreadAddon.masochismPotion.id);
								CustomPotionList.remove(p
										.getCommandSenderName());
							} else if (!CustomPotionList.containsKey(p
									.getCommandSenderName())) {
								CustomPotionList.put(p.getCommandSenderName(),
										machicism.getDuration());
							}
						}
					}
				}
			}
		} catch (Throwable e) {

		}
	}
}
