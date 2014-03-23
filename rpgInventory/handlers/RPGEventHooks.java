/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RPGEventHooks {

	public static Map<String, Integer> ArcherRepairTick = new ConcurrentHashMap();
	public static Map<String, Integer> HealerTick = new ConcurrentHashMap();
	public static Map<String, Integer> DiamondTick = new ConcurrentHashMap();
	public static Map<String, Integer> LapisTick = new ConcurrentHashMap();
	public static List<Integer> negativeEffects = new ArrayList();
	public static Map<String, Integer> CustomPotionList = new ConcurrentHashMap();
	Random rand = new Random();

	float vanillaReduction = 0f;

	@SubscribeEvent
	public void BreakSpeed(PlayerEvent.BreakSpeed evt) {
		/*
		 * Increases Block-breaking speed while wearing emerald ring (right
		 * slot)
		 */
		try {
			if (evt.entityPlayer != null) {
				PlayerRpgInventory inv = PlayerRpgInventory
						.get(evt.entityPlayer);
				ItemStack ringa = inv.getRing1();
				if ((ringa != null)
						&& ringa.getItem().equals(mod_RpgInventory.ringem)) {
					evt.newSpeed = evt.originalSpeed * 4;
				}
			}
		} catch (Throwable e) {
		}
	}

	public void damageItem(ItemStack item, PlayerRpgInventory inv,
			EntityPlayer p, int slot, int amount) {
		if (mod_RpgInventory.developers.contains(p.getDisplayName()
				.toLowerCase())) {
			return;
		}
		try {
			if ((item.getItemDamage() + amount) >= item.getMaxDamage()) {
				// Trigger item break stuff
				item = null;
			} else {
				item.damageItem(amount, p);
			}
			inv.setInventorySlotContents(slot, item);
		} catch (Throwable e) {
		}
	}

	@SubscribeEvent
	public void DeathEvent(LivingDeathEvent evt) {
		/* ====PET EXP==== */
		try {

			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;

				PlayerRpgInventory inv = PlayerRpgInventory
						.get((EntityPlayer) evt.entityLiving);
				if (mod_RpgInventory.playerClass
						.contains(mod_RpgInventory.CLASSBERSERKER)) {
					player.worldObj.newExplosion(player, player.posX,
							player.posY, player.posZ, 20, false, false);
				}
			}

		} catch (Throwable e) {
		}
	}

	protected int decreaseAirSupply(int par1) {
		Random rand = new Random();
		int j = 5;
		return (j > 0) && (rand.nextInt(j + 1) > 0) ? par1 : par1 - 1;
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		/*
		 * Be sure to check if the entity being constructed is the correct type
		 * for the extended properties you're about to add!The null check may
		 * not be necessary - I only use it to make sureproperties are only
		 * registered once per entity
		 */
		if ((event.entity instanceof EntityPlayer)
				&& (PlayerRpgInventory.get((EntityPlayer) event.entity) == null)) {
			// This is how extended properties are registered using our
			// convenient method from earlier
			PlayerRpgInventory.register((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void PlayerDamage(LivingHurtEvent evt) {

		try {
			/* ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS */
			Entity damager = evt.source.getSourceOfDamage();
			if (damager != null) {
				if (damager instanceof EntityPlayer) {
					float damagebonus = 0.0F;
					PlayerRpgInventory inv = PlayerRpgInventory
							.get((EntityPlayer) damager);
					ItemStack weapon = ((EntityPlayer) damager)
							.getCurrentEquippedItem();
					if (weapon != null) {
						if (weapon.getItem() == mod_RpgInventory.hammer) {
							if (mod_RpgInventory.playerClass
									.contains(mod_RpgInventory.CLASSBERSERKER)) {
								evt.ammount += 4;
							}
						}
					}
					if (mod_RpgInventory.playerClass
							.contains(mod_RpgInventory.CLASSBERSERKER)) {
						// hit harder with both hands 'free'
						if (!mod_RpgInventory.playerClass
								.contains(mod_RpgInventory.CLASSBERSERKERSHIELD)) {
							evt.ammount += 2;
						}
					}

					/* ==== LAPIS BONUS ATTACK==== */
					ItemStack neck = inv.getNecklace();
					ItemStack ringa = inv.getRing1();
					ItemStack ringb = inv.getRing2();
					ItemStack gloves = inv.getGloves();
					if ((neck != null)
							&& neck.getItem().equals(mod_RpgInventory.necklap)) {
						damagebonus += 0.3F;
					}
					if ((ringa != null)
							&& ringa.getItem().equals(mod_RpgInventory.ringlap)) {
						damagebonus += mod_RpgInventory.donators
								.contains(((EntityPlayer) damager)
										.getDisplayName()) ? 0.2f : 0.1F;
					}
					if ((ringb != null)
							&& ringb.getItem().equals(mod_RpgInventory.ringlap)) {
						damagebonus += mod_RpgInventory.donators
								.contains(((EntityPlayer) damager)
										.getDisplayName()) ? 0.2f : 0.1F;
					}
					if ((gloves != null)
							&& gloves.getItem().equals(
									mod_RpgInventory.gloveslap)) {
						damagebonus += 0.2F;
					}
					evt.ammount += MathHelper.floor_float(damagebonus
							* (evt.ammount));
				}
			}
		} catch (Throwable e) {
		}
		try {
			/* DAMAGING AND REDUCING DAMAGE FOR SHIELDS */
			if ((evt.entityLiving != null)
					&& (evt.entityLiving instanceof EntityPlayer)) {
				float damageReduction = 0.0F;
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				PlayerRpgInventory inv = PlayerRpgInventory.get(player);

				ItemStack shield = inv.getShield();
				if (shield != null) {

					if (((ItemRpgInvArmor) inv.getShield().getItem())
							.boundArmorClass().equals(
									mod_RpgInventory.playerClass)) {
						vanillaReduction += 0.6f;
					}

					if (vanillaReduction > 1f) {
						damageReduction = 1f + (vanillaReduction - 1f);
						vanillaReduction = 0;
					}
					evt.ammount -= damageReduction;
					// MathHelper.floor_float(((float) evt.ammount) *
					// damageReduction);
					damageItem(shield, inv, player, 1, 1);
				}
			}
		} catch (Throwable e) {
		}
		try {
			if ((evt.entityLiving != null)
					&& (evt.entityLiving instanceof EntityPlayer)) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				PlayerRpgInventory inv = PlayerRpgInventory.get(player);
				if ((inv.getGloves() != null)
						&& (inv.getGloves().getItem() == mod_RpgInventory.glovesem)) {
					if ((evt.ammount * 0.2F) < 1) {
						evt.ammount -= 1;
					} else {
						evt.ammount -= MathHelper.floor_float(evt.ammount
								* (mod_RpgInventory.donators.contains(player
										.getDisplayName()) ? 0.3f : 0.2F));
					}
				}
				if (inv != null) {
					ItemStack shield = inv.getShield();
					if (shield != null) {
						if (mod_RpgInventory.playerClass
								.contains(mod_RpgInventory.CLASSMAGESHIELD)) {
							float damageReduction = 0.20F;
							EntityLivingBase damagedealer = null;
							if (evt.source.isMagicDamage()) {
								damageReduction = 0.50F;
							} else if (evt.source.getSourceOfDamage() != null) {
								if (evt.source.isProjectile()) {
									if (evt.source.getSourceOfDamage() instanceof EntityArrow) {
										if (((EntityArrow) evt.source
												.getSourceOfDamage()).shootingEntity != null) {
											if (((EntityArrow) evt.source
													.getSourceOfDamage()).shootingEntity instanceof EntityLivingBase) {
												damagedealer = (EntityLivingBase) ((EntityArrow) evt.source
														.getSourceOfDamage()).shootingEntity;
											}
										}
									}
									if (evt.source.getSourceOfDamage() instanceof EntityLivingBase) {
										damagedealer = (EntityLivingBase) evt.source
												.getSourceOfDamage();
									}
								}
							}
							if (damagedealer != null) {
								if (damagedealer.isEntityUndead()) {
									damageReduction = 0.75F;
								}
							}
							evt.ammount -= MathHelper.floor_float((evt.ammount)
									* damageReduction);
							damageItem(shield, inv, player, 1, 1);

						} else if (mod_RpgInventory.playerClass
								.contains(mod_RpgInventory.CLASSARCHERSHIELD)) {
							float damageReduction = 0.25F;
							EntityLivingBase damagedealer = null;
							if (evt.source.getSourceOfDamage() != null) {
								if (evt.source.isProjectile()) {
									if (evt.source.getSourceOfDamage() instanceof EntityArrow) {
										if (((EntityArrow) evt.source
												.getSourceOfDamage()).shootingEntity != null) {
											if (((EntityArrow) evt.source
													.getSourceOfDamage()).shootingEntity instanceof EntityLivingBase) {
												damagedealer = (EntityLivingBase) ((EntityArrow) evt.source
														.getSourceOfDamage()).shootingEntity;
												damageReduction = 0.70F;
											}
										}
									}
									if (evt.source.getSourceOfDamage() instanceof EntityLivingBase) {
										damagedealer = (EntityLivingBase) evt.source
												.getSourceOfDamage();
									}
								}
							}
							if (damageReduction < 0.70F) {
								if (damagedealer != null) {
									if (damagedealer.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
										damageReduction = 0.40F;
									}
									if (damagedealer instanceof EntityTameable) {
										if (!damagedealer.isEntityUndead()) {
											damageReduction = 0.50F;
										}
									}
								}
							}
							if (evt.source.isFireDamage()) {
								evt.ammount += MathHelper
										.floor_float((evt.ammount) * 0.10F);
							} else {
								evt.ammount -= MathHelper
										.floor_float((evt.ammount)
												* damageReduction);
							}
							damageItem(shield, inv, player, 1, 1);
						}
					} else if (mod_RpgInventory.playerClass
							.contains(mod_RpgInventory.CLASSBERSERKERSHIELD)) {
						float damageReduction = 0.50F;
						evt.ammount -= MathHelper.floor_float((evt.ammount)
								* damageReduction);
						if (evt.ammount > 1) {
							// Flat 1 damage absorption on top of resistance if
							// the damage is greater than 1.
							// The additional absorbtion alone will never reduce
							// all damage.
							evt.ammount -= 1;
						}
						damageItem(shield, inv, player, 1, 1);
					}
				}
			}
		} catch (Throwable e) {
		}

		try {

			PlayerRpgInventory inv = PlayerRpgInventory
					.get((EntityPlayer) evt.entityLiving);

			ItemStack neck = inv.getNecklace();
			ItemStack ringa = inv.getRing1();
			ItemStack ringb = inv.getRing2();
			ItemStack gloves = inv.getGloves();

			// /*==== NEGATE FALLDAMAGE GOLD JEWELS ==== */
			// double speedboost = 0;
			// int goldenItems = 0;
			//
			// if (neck != null && neck.getItem() == mod_RpgInventory.neckgold)
			// {
			// goldenItems +=1;
			// }
			// if (ringa != null && ringa.getItem() ==
			// mod_RpgInventory.ringgold) {
			// goldenItems +=1;
			// }
			// if (ringb != null && ringb.getItem() ==
			// mod_RpgInventory.ringgold) {
			// goldenItems +=1;
			// }
			// if (gloves != null && gloves.getItem() ==
			// mod_RpgInventory.glovesbutter) {
			// goldenItems +=1;
			// }
			//
			// if( goldenItems == 4 && evt.source.equals(DamageSource.fall)){
			// if(evt.ammount < 3){
			// evt.setCanceled(true);
			// }else{
			// evt.ammount -=2;
			// }
			// }

		} catch (Exception e) {
		}
	}

	// public void goldJump(EntityPlayer p, double amount)
	// {
	// float jumpTicks = 0;
	//
	// if (p.isAirBorne)//is jumping
	// {
	// if (!p.isInWater() && !p.handleLavaMovement())
	// {
	// if (jumpTicks == 0)
	// {
	// p.motionY += amount;
	// jumpTicks = 10;
	// }
	// }
	// else
	// {
	// p.motionY += 0.03999999910593033D;
	// }
	// }
	// else
	// {
	// jumpTicks = 0;
	// }
	// }

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {
		/* ====UPDATING INVENTORY==== */
		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				PlayerRpgInventory.get((EntityPlayer) evt.entityLiving)
						.markDirty();
			}
		} catch (Throwable ex) {
		}

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {

					if ((p.getActivePotionEffects() != null)
							&& (p.getActivePotionEffects().size() > 0)) {
						PotionEffect decompose = p
								.getActivePotionEffect(mod_RpgInventory.decomposePotion);
						PotionEffect machicism = p
								.getActivePotionEffect(mod_RpgInventory.masochismPotion);
						if ((decompose != null) && (machicism != null)) {
							p.removePotionEffect(mod_RpgInventory.decomposePotion.id);
							p.removePotionEffect(mod_RpgInventory.masochismPotion.id);
							CustomPotionList.remove(p.getDisplayName());
						} else {
							if (decompose != null) {
								if (decompose.getDuration() == 0) {
									p.removePotionEffect(mod_RpgInventory.decomposePotion.id);
									CustomPotionList.remove(p.getDisplayName());
								} else {
									if (!CustomPotionList.containsKey(p
											.getDisplayName())) {
										CustomPotionList.put(
												p.getDisplayName(),
												decompose.getDuration());
									}
								}
							} else if (machicism != null) {
								if (machicism.getDuration() == 0) {
									p.removePotionEffect(mod_RpgInventory.masochismPotion.id);
									CustomPotionList.remove(p.getDisplayName());
								} else {
									if (!CustomPotionList.containsKey(p
											.getDisplayName())) {
										CustomPotionList.put(
												p.getDisplayName(),
												machicism.getDuration());
									}
								}
							}
						}
					}

					PlayerRpgInventory inv = PlayerRpgInventory.get(p);

					ItemStack neck = inv.getNecklace();
					ItemStack ringa = inv.getRing1();
					ItemStack ringb = inv.getRing2();
					ItemStack gloves = inv.getGloves();

					/* ====LAPIS WEAPON HEALING==== */
					boolean armorheal = false;
					if ((neck != null)
							&& neck.getItem().equals(mod_RpgInventory.necklap)) {
						armorheal = true;
					}
					if ((ringa != null)
							&& ringa.getItem().equals(mod_RpgInventory.ringlap)) {
						armorheal = true;
					}
					if ((ringb != null)
							&& ringb.getItem().equals(mod_RpgInventory.ringlap)) {
						armorheal = true;
					}
					if ((gloves != null)
							&& gloves.getItem().equals(
									mod_RpgInventory.gloveslap)) {
						armorheal = true;
					}
					if (armorheal) {
						if (!LapisTick.containsKey(p.getDisplayName())) {
							LapisTick.put(p.getDisplayName(), 60);
						}
					} else {
						LapisTick.remove(p.getDisplayName());
					}

					/* ====ARCHER EFFECTS==== */
					// doesnt work TODO
					// needs to be checked if actually works or not
					float jumpboost = p.jumpMovementFactor;
					if (mod_RpgInventory.playerClass
							.contains(mod_RpgInventory.CLASSARCHER)) {
						jumpboost *= mod_RpgInventory.donators.contains(p
								.getDisplayName()) ? 3.0f : 2.0F;
					}
					p.jumpMovementFactor = jumpboost;

					/* ====MAGE FALLDAMAGE NEGATION==== */
					if (mod_RpgInventory.playerClass
							.contains(mod_RpgInventory.CLASSMAGESHIELD)) {
						p.fallDistance = 0;
					}

					ItemStack weapon = p.getCurrentEquippedItem();
					if (weapon != null) {

						/* BERSEKRER KNOCKBACK */
						// sets Berserker weapon with knockback if the apropiate
						// classes match
						if (weapon.getItem() == mod_RpgInventory.hammer) {
							if (mod_RpgInventory.playerClass
									.contains(mod_RpgInventory.CLASSBERSERKERSHIELD)) {
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
									.contains(mod_RpgInventory.CLASSBERSERKER)) {
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

					/* ==== EMERALD WATER BREATHING ==== */
					// works
					if ((neck != null)
							&& neck.getItem().equals(mod_RpgInventory.neckem)) {
						boolean flag = (p instanceof EntityPlayer)
								&& p.capabilities.disableDamage;

						if (p.isEntityAlive()
								&& p.isInsideOfMaterial(Material.water)
								&& !flag) {
							p.setAir(decreaseAirSupply(p.getAir() + 1));

							if (p.getAir() == -20) {
								p.setAir(0);

								for (int i = 0; i < 8; ++i) {
									float f = this.rand.nextFloat()
											- this.rand.nextFloat();
									float f1 = this.rand.nextFloat()
											- this.rand.nextFloat();
									float f2 = this.rand.nextFloat()
											- this.rand.nextFloat();
									p.worldObj.spawnParticle("bubble", p.posX
											+ f, p.posY + f1, p.posZ + f2,
											p.motionX, p.motionY, p.motionZ);
								}
								p.attackEntityFrom(DamageSource.drown, 1);
							}
							p.extinguish();
						} else {
							p.setAir(300);
						}
					}

					/* ==== EMERALD CURE ==== */
					// works
					if ((ringb != null)
							&& ringb.getItem().equals(mod_RpgInventory.ringem)) {
						for (Integer id : negativeEffects) {
							p.removePotionEffect(id);
						}
					}

					/* ==== SPEED BOOST GOLD JEWELS ==== */
					float speedboost = 0;
					int goldenItems = 0;

					if ((neck != null)
							&& (neck.getItem() == mod_RpgInventory.neckgold)) {
						speedboost += mod_RpgInventory.donators.contains(p
								.getDisplayName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					if ((ringa != null)
							&& (ringa.getItem() == mod_RpgInventory.ringgold)) {
						speedboost += mod_RpgInventory.donators.contains(p
								.getDisplayName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					if ((ringb != null)
							&& (ringb.getItem() == mod_RpgInventory.ringgold)) {
						speedboost += mod_RpgInventory.donators.contains(p
								.getDisplayName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					if ((gloves != null)
							&& (gloves.getItem() == mod_RpgInventory.glovesbutter)) {
						speedboost += mod_RpgInventory.donators.contains(p
								.getDisplayName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					p.capabilities.setPlayerWalkSpeed(0.1f + speedboost);

					// if(p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()
					// !=
					// p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
					// + (double)speedboost){
					//

					/* ==== Something about the archer .__. ==== */
					if (ArcherRepairTick.containsKey(p.getDisplayName())) {
						if (mod_RpgInventory.playerClass
								.contains(mod_RpgInventory.CLASSARCHER)) {
							p.jumpMovementFactor = 0.09F;
						} else {
							ArcherRepairTick.remove(p.getDisplayName());
							p.jumpMovementFactor = 0.02F;
						}
					}

					/* ==== Invisibility Cloak==== */
					ItemStack cloak = inv.getCloak();
					if (cloak != null) {
						if (cloak.getItem() == mod_RpgInventory.cloakI) {
							p.addPotionEffect(new PotionEffect(
									Potion.invisibility.id, 20, 1));
						}
					}
				}
			}
		} catch (Throwable ex) {
		}
	}
}
