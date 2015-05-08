package addonDread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DreadEventHooks {
	public static Map<String, Integer> CustomPotionList = new ConcurrentHashMap();


	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent evt) {

		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) evt.entityLiving;
			if (p != null) {
				paladinSmiteBonus(p);

				updatePotions(p);

				updateNecroPotionEffects(p);
			}
		}
	}

	@SubscribeEvent
	public void PlayerDamage(LivingHurtEvent evt) {

		Entity damager = evt.source.getSourceOfDamage();
		if (damager != null) {
			if (damager instanceof EntityPlayer) {
				PlayerRpgInventory.get((EntityPlayer) damager);
				ItemStack weapon = ((EntityPlayer) damager).getCurrentEquippedItem();
				if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSPALADIN)) {
					System.out.println(damager.worldObj.isDaytime());
					if (damager.worldObj.isDaytime()) {
						evt.ammount += 2;
						if (weapon != null) {
							if (weapon.getItem() == RpgDreadAddon.paladinSword) {
								if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSPALADIN)) {
									evt.ammount += 1;
								}
							}
						}
					}
					// paladin heals himself when hitting undead
					if (evt.entityLiving.isEntityUndead()) {
						if (((EntityPlayer) damager).getHealth() < ((EntityPlayer) damager).getMaxHealth()) {
							if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSPALADINSHIELD))
								((EntityPlayer) damager).heal(1);
						}
						evt.ammount += 3;
						evt.entityLiving.setFire(2);
					}
				}
				if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {
					if (!damager.worldObj.isDaytime()) {
						evt.ammount += 3;
					}
				}
			}
		}
	}

	private void paladinSmiteBonus(EntityPlayer p){
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


	private void updatePotions(EntityPlayer p){
		try {

			if ((p.getActivePotionEffects() != null)&& (p.getActivePotionEffects().size() > 0)) {
				PotionEffect decompose = p.getActivePotionEffect(RpgDreadAddon.decomposePotion);
				PotionEffect machicism = p.getActivePotionEffect(RpgDreadAddon.masochismPotion);
				if ((decompose != null) && (machicism != null)) {
					p.removePotionEffect(RpgDreadAddon.decomposePotion.id);
					p.removePotionEffect(RpgDreadAddon.masochismPotion.id);
					CustomPotionList.remove(p.getDisplayName());
				} else if (decompose != null) {
					if (decompose.getDuration() == 0) {
						p.removePotionEffect(RpgDreadAddon.decomposePotion.id);
						CustomPotionList.remove(p.getDisplayName());
					} else if (!CustomPotionList.containsKey(p.getDisplayName())) {
						CustomPotionList.put(p.getDisplayName(),
								decompose.getDuration());
					}
				} else if (machicism != null) {
					if (machicism.getDuration() == 0) {
						p.removePotionEffect(RpgDreadAddon.masochismPotion.id);
						CustomPotionList.remove(p.getDisplayName());
					} else if (!CustomPotionList.containsKey(p							.getDisplayName())) {
						CustomPotionList.put(p.getDisplayName(),machicism.getDuration());
					}
				}
			}
		}catch (Throwable e) {
		} 
	}


	private void updateNecroPotionEffects(EntityPlayer p){
		try {

			if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {
				if (p.getActivePotionEffect(Potion.regeneration) != null) {
					p.addPotionEffect(new PotionEffect(RpgDreadAddon.decomposePotion.id, p.getActivePotionEffect(Potion.regeneration).getDuration() * 2, p.getActivePotionEffect(Potion.regeneration).getAmplifier()));
					p.removePotionEffect(Potion.regeneration.id);
				}
				if (p.getActivePotionEffect(Potion.poison) != null) {
					p.addPotionEffect(new PotionEffect(RpgDreadAddon.masochismPotion.id, p.getActivePotionEffect(Potion.poison).getDuration() / 2, p.getActivePotionEffect(Potion.poison).getAmplifier()));
					p.removePotionEffect(Potion.poison.id);
				}
			}
		} catch (Exception e) {

			System.out.println("Fack. I crashed. Something went wrong with them potions ! "+ this.getClass().getName());
		}
	}


	private void updatePaladinSpeed(EntityPlayer p){
		double mX = p.motionX;
		double mZ = p.motionZ;
		if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSPALADIN)) {
			mX *= 0.9F;// slows down
			mZ *= 0.9F;
		}
		p.motionX = mX;
		p.motionZ = mZ;
	}
}
