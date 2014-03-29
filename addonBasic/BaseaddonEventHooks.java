package addonBasic;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BaseaddonEventHooks {

	public void damageItem(ItemStack item, PlayerRpgInventory inv,
			EntityPlayer p, int slot, int amount) {
		if (mod_RpgInventory.developers.contains(p.getCommandSenderName()
				.toLowerCase()))
			return;
		try {
			if ((item.getItemDamage() + amount) >= item.getMaxDamage())
				// Trigger item break stuff
				item = null;
			else
				item.damageItem(amount, p);
			inv.setInventorySlotContents(slot, item);
		} catch (Throwable e) {
		}
	}

	@SubscribeEvent
	public void PlayerDamage(LivingHurtEvent evt) {
		try {
			/* ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS */
			Entity damager = evt.source.getSourceOfDamage();
			if (damager != null)
				if (damager instanceof EntityPlayer) {
					float damagebonus = 0.0F;
					PlayerRpgInventory inv = PlayerRpgInventory
							.get((EntityPlayer) damager);
					ItemStack weapon = ((EntityPlayer) damager)
							.getCurrentEquippedItem();
					if (weapon != null)
						if (weapon.getItem() == mod_addonBase.hammer)
							if (mod_RpgInventory.playerClass
									.contains(mod_addonBase.CLASSBERSERKER))
								evt.ammount += 4;
					if (mod_RpgInventory.playerClass
							.contains(mod_addonBase.CLASSBERSERKER))
						// hit harder with both hands 'free'
						if (!mod_RpgInventory.playerClass
								.contains(mod_addonBase.CLASSBERSERKERSHIELD))
							evt.ammount += 2;
				}
		} catch (Throwable e) {
		}

		try {
			if ((evt.entityLiving != null)
					&& (evt.entityLiving instanceof EntityPlayer)) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				PlayerRpgInventory inv = PlayerRpgInventory.get(player);

				if (inv != null) {
					ItemStack shield = inv.getShield();
					if (shield != null) {
						if (mod_RpgInventory.playerClass
								.contains(mod_addonBase.CLASSMAGESHIELD)) {
							float damageReduction = 0.20F;
							EntityLivingBase damagedealer = null;
							if (evt.source.isMagicDamage())
								damageReduction = 0.50F;
							else if (evt.source.getSourceOfDamage() != null)
								if (evt.source.isProjectile()) {
									if (evt.source.getSourceOfDamage() instanceof EntityArrow)
										if (((EntityArrow) evt.source
												.getSourceOfDamage()).shootingEntity != null)
											if (((EntityArrow) evt.source
													.getSourceOfDamage()).shootingEntity instanceof EntityLivingBase)
												damagedealer = (EntityLivingBase) ((EntityArrow) evt.source
														.getSourceOfDamage()).shootingEntity;
									if (evt.source.getSourceOfDamage() instanceof EntityLivingBase)
										damagedealer = (EntityLivingBase) evt.source
												.getSourceOfDamage();
								}
							if (damagedealer != null)
								if (damagedealer.isEntityUndead())
									damageReduction = 0.75F;
							evt.ammount -= MathHelper.floor_float((evt.ammount)
									* damageReduction);
							damageItem(shield, inv, player, 1, 1);

						} else if (mod_RpgInventory.playerClass
								.contains(mod_addonBase.CLASSARCHERSHIELD)) {
							float damageReduction = 0.25F;
							EntityLivingBase damagedealer = null;
							if (evt.source.getSourceOfDamage() != null)
								if (evt.source.isProjectile()) {
									if (evt.source.getSourceOfDamage() instanceof EntityArrow)
										if (((EntityArrow) evt.source
												.getSourceOfDamage()).shootingEntity != null)
											if (((EntityArrow) evt.source
													.getSourceOfDamage()).shootingEntity instanceof EntityLivingBase) {
												damagedealer = (EntityLivingBase) ((EntityArrow) evt.source
														.getSourceOfDamage()).shootingEntity;
												damageReduction = 0.70F;
											}
									if (evt.source.getSourceOfDamage() instanceof EntityLivingBase)
										damagedealer = (EntityLivingBase) evt.source
												.getSourceOfDamage();
								}
							if (damageReduction < 0.70F)
								if (damagedealer != null) {
									if (damagedealer.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
										damageReduction = 0.40F;
									if (damagedealer instanceof EntityTameable)
										if (!damagedealer.isEntityUndead())
											damageReduction = 0.50F;
								}
							if (evt.source.isFireDamage())
								evt.ammount += MathHelper
										.floor_float((evt.ammount) * 0.10F);
							else
								evt.ammount -= MathHelper
										.floor_float((evt.ammount)
												* damageReduction);
							damageItem(shield, inv, player, 1, 1);
						}
					} else if (mod_RpgInventory.playerClass
							.contains(mod_addonBase.CLASSBERSERKERSHIELD)) {
						float damageReduction = 0.50F;
						evt.ammount -= MathHelper.floor_float((evt.ammount)
								* damageReduction);
						if (evt.ammount > 1)
							// Flat 1 damage absorption on top of resistance if
							// the damage is greater than 1.
							// The additional absorbtion alone will never reduce
							// all damage.
							evt.ammount -= 1;
						damageItem(shield, inv, player, 1, 1);
					}
				}
			}
		} catch (Throwable e) {
		}
	}

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		try {

			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {

					/* BERSEKRER KNOCKBACK */
					// sets Berserker weapon with knockback if the apropiate
					// classes match

					/* ==== Something about the archer .__. ==== */
					if (CommonTickHandler.ArcherRepairTick.containsKey(p
							.getCommandSenderName()))
						if (mod_RpgInventory.playerClass
								.contains(mod_addonBase.CLASSARCHER))
							p.jumpMovementFactor = 0.09F;
						else {
							CommonTickHandler.ArcherRepairTick.remove(p
									.getCommandSenderName());
							p.jumpMovementFactor = 0.02F;
						}

					ItemStack weapon = p.getCurrentEquippedItem();
					if (weapon != null)
						if (weapon.getItem() == mod_addonBase.hammer)
							if (mod_RpgInventory.playerClass
									.contains(mod_addonBase.CLASSBERSERKERSHIELD)) {
								if (((p.getFoodStats().getFoodLevel() < 4) || (p
										.getHealth() < 4))) {
									Map tmp = EnchantmentHelper
											.getEnchantments(weapon);
									tmp.put(Enchantment.knockback.effectId, 3);
									EnchantmentHelper.setEnchantments(tmp,
											weapon);
								} else {
									Map tmp = EnchantmentHelper
											.getEnchantments(weapon);
									tmp.put(Enchantment.knockback.effectId, 2);
									EnchantmentHelper.setEnchantments(tmp,
											weapon);
								}
							} else if (mod_RpgInventory.playerClass
									.contains(mod_addonBase.CLASSBERSERKER)) {
								Map tmp = EnchantmentHelper
										.getEnchantments(weapon);
								tmp.put(Enchantment.knockback.effectId, 1);
								EnchantmentHelper.setEnchantments(tmp, weapon);
							} else {
								Map tmp = EnchantmentHelper
										.getEnchantments(weapon);
								tmp.remove(Enchantment.knockback.effectId);
								EnchantmentHelper.setEnchantments(tmp, weapon);
							}
				}
			}
		} catch (Exception e) {
		}

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {

					PlayerRpgInventory inv = PlayerRpgInventory.get(p);
					/* ====ARCHER EFFECTS==== */
					// doesnt work TODO
					// needs to be checked if actually works or not
					float jumpboost = p.jumpMovementFactor;
					if (mod_RpgInventory.playerClass
							.contains(mod_addonBase.CLASSARCHER))
						jumpboost *= mod_RpgInventory.donators.contains(p
								.getCommandSenderName()) ? 3.0f : 2.0F;
					p.jumpMovementFactor = jumpboost;

					/* ====MAGE FALLDAMAGE NEGATION==== */
					if (mod_RpgInventory.playerClass
							.contains(mod_addonBase.CLASSMAGESHIELD))
						p.fallDistance = 0;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
