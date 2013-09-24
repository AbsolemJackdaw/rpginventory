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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgRogueBeast.entity.EntityPetXP;
import rpgRogueBeast.entity.IPet;
import cpw.mods.fml.common.FMLLog;

public class RPGEventHooks {

	public static Map<String, Integer> ArcherRepairTick = new ConcurrentHashMap();
	public static Map<String, Integer> HealerTick = new ConcurrentHashMap();
	public static Map<String, Integer> DiamondTick = new ConcurrentHashMap();
	public static Map<String, Integer> LapisTick = new ConcurrentHashMap();
	public static List<Integer> negativeEffects = new ArrayList();
	public static Map<String, Integer> CustomPotionList = new ConcurrentHashMap();
	Random rand = new Random();


	float vanillaReduction = 0f;

	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		/*
		 *Be sure to check if the entity being constructed is the correct type
		 *for the extended properties you're about to add!
		 *The null check may not be necessary - I only use it to make sure
		 *properties are only registered once per entity
		 */
		if (event.entity instanceof EntityPlayer && PlayerRpgInventory.get((EntityPlayer) event.entity) == null)
			// This is how extended properties are registered using our convenient method from earlier
			PlayerRpgInventory.register((EntityPlayer) event.entity);
	}

	@ForgeSubscribe
	public void BreakSpeed(PlayerEvent.BreakSpeed evt) {
		try {
			if (evt.entityPlayer != null) {
				PlayerRpgInventory inv = PlayerRpgInventory.get(evt.entityPlayer);
				ItemStack ringa = inv.getRing1();
				if (ringa != null && ringa.getItem().equals(mod_RpgInventory.ringem)) {
					evt.newSpeed = evt.originalSpeed * 4;
				}
			}
		} catch (Throwable e) {
		}
	}

	@ForgeSubscribe
	public void DeathEvent(LivingDeathEvent evt) {
		/*====PET EXP====*/
		try {
			if ((!evt.entityLiving.worldObj.isRemote) && evt.entityLiving != null && evt.source != null && evt.source.getSourceOfDamage() != null && evt.source.getSourceOfDamage() instanceof IPet) {
				if(evt.entityLiving instanceof EntityLiving){
					EntityLiving corpse = (EntityLiving)evt.entityLiving;
					EntityLivingBase murderer = (EntityLiving)evt.source.getSourceOfDamage();
					int totalXP = corpse.experienceValue; 

					while (totalXP > 0) {
						int partialXP = EntityXPOrb.getXPSplit(totalXP);
						totalXP -= partialXP;
						corpse.worldObj.spawnEntityInWorld(new EntityPetXP(murderer.worldObj, corpse.posX, corpse.posY, corpse.posZ, partialXP));
					}
				}
			}
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;

				PlayerRpgInventory inv = PlayerRpgInventory.get((EntityPlayer) evt.entityLiving);
				if (EnumRpgClass.getPlayerClasses((EntityPlayer)evt.entityLiving).contains(EnumRpgClass.BERSERKER)) {
					player.worldObj.newExplosion((Entity) player, player.posX, player.posY, player.posZ, 20, false, false);
				}
			}

		} catch (Throwable e) {
		}
	}

	@ForgeSubscribe
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {
		/*====UPDATING INVENTORY====*/
		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				PlayerRpgInventory.get((EntityPlayer) evt.entityLiving).onInventoryChanged();
			}
		} catch (Throwable ex) {
		}

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {

					if (p.getActivePotionEffects() != null && p.getActivePotionEffects().size() > 0) {
						PotionEffect decompose = p.getActivePotionEffect(mod_RpgInventory.decomposePotion);
						PotionEffect machicism = p.getActivePotionEffect(mod_RpgInventory.masochismPotion);
						if (decompose != null && machicism != null) {
							p.removePotionEffect(mod_RpgInventory.decomposePotion.id);
							p.removePotionEffect(mod_RpgInventory.masochismPotion.id);
							CustomPotionList.remove(p.username);
						} else {
							if (decompose != null) {
								if (decompose.getDuration() == 0) {
									p.removePotionEffect(mod_RpgInventory.decomposePotion.id);
									CustomPotionList.remove(p.username);
								} else {
									if (!CustomPotionList.containsKey(p.username)) {
										CustomPotionList.put(p.username, decompose.getDuration());
									}
								}
							} else if (machicism != null) {
								if (machicism.getDuration() == 0) {
									p.removePotionEffect(mod_RpgInventory.masochismPotion.id);
									CustomPotionList.remove(p.username);
								} else {
									if (!CustomPotionList.containsKey(p.username)) {
										CustomPotionList.put(p.username, machicism.getDuration());
									}
								}
							}
						}
					}


					PlayerRpgInventory inv = PlayerRpgInventory.get(p);
					inv.classSets = EnumRpgClass.getPlayerClasses(p);

					ItemStack neck = inv.getNecklace();
					ItemStack ringa = inv.getRing1();
					ItemStack ringb = inv.getRing2();
					ItemStack gloves = inv.getGloves();

					/*====LAPIS WEAPON HEALING====*/ 
					boolean armorheal = false;
					if (neck != null && neck.getItem().equals(mod_RpgInventory.necklap)) {
						armorheal = true;
					}
					if (ringa != null && ringa.getItem().equals(mod_RpgInventory.ringlap)) {
						armorheal = true;
					}
					if (ringb != null && ringb.getItem().equals(mod_RpgInventory.ringlap)) {
						armorheal = true;
					}
					if (gloves != null && gloves.getItem().equals(mod_RpgInventory.gloveslap)) {
						armorheal = true;
					}
					if (armorheal) {
						if (!LapisTick.containsKey(p.username)) {
							LapisTick.put(p.username, 60);
						}
					} else {
						LapisTick.remove(p.username);
					}

					/*====ARCHER EFFECTS====*/
					//doesnt work TODO
					float jumpboost = p.jumpMovementFactor;
					if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.ARCHER)) {
						jumpboost *= 4.0F;
					}
					p.jumpMovementFactor = jumpboost;

					/*====MAGE FALLDAMAGE NEGATION====*/
					if(EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.SHIELDEDMAGE)){
						p.fallDistance = 0;
					}

					/*==== ARCHMAGE EFFECTS ====*/
					ItemStack weapon = p.getCurrentEquippedItem();
					if (weapon != null) {
						if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.SHIELDEDARCHMAGE) || mod_RpgInventory.developers.contains(p.username.toLowerCase()))
						{
							if (weapon.getItem().equals(mod_RpgInventory.fireStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{
								if (p.isBurning()) {
									if (p.getHealth() < 6) {
										p.setHealth(6);
									} p.extinguish();
								}
							}
							if (weapon.getItem().equals(mod_RpgInventory.windStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{
								p.fallDistance = 0; // negates fall damage
							}
							if (weapon.getItem().equals(mod_RpgInventory.frostStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{
								if (p.getAir() < 20)p.setAir(20); // you can not drown !
							}
							if (weapon.getItem().equals(mod_RpgInventory.earthStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{
								p.curePotionEffects(new ItemStack(Item.bucketMilk, 1)); // cure all negative potion effects
							}
						}

						/*BERSEKRER KNOCKBACK*/
						//sets Berserker weapon with knockback if the apropiate classes match
						if (weapon.itemID == mod_RpgInventory.hammer.itemID) {
							if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.SHIELDEDBERSERKER)) {
								if ((p.getFoodStats().getFoodLevel() < 4 || p.getHealth() < 4)) {
									Map tmp = EnchantmentHelper.getEnchantments(weapon);
									tmp.put(Enchantment.knockback.effectId, 3);
									EnchantmentHelper.setEnchantments(tmp, weapon);
								} else {
									Map tmp = EnchantmentHelper.getEnchantments(weapon);
									tmp.put(Enchantment.knockback.effectId, 2);
									EnchantmentHelper.setEnchantments(tmp, weapon);
								}
							} else if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.BERSERKER)) {
								Map tmp = EnchantmentHelper.getEnchantments(weapon);
								tmp.put(Enchantment.knockback.effectId, 1);
								EnchantmentHelper.setEnchantments(tmp, weapon);
							} else {
								Map tmp = EnchantmentHelper.getEnchantments(weapon);
								tmp.remove(Enchantment.knockback.effectId);
								EnchantmentHelper.setEnchantments(tmp, weapon);
							}
						}
					}

					/*something for necromancers*/
					if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.NECRO)) {
						if (p.getActivePotionEffect(Potion.regeneration) != null) {
							p.addPotionEffect(new PotionEffect(mod_RpgInventory.decomposePotion.id, p.getActivePotionEffect(Potion.regeneration).getDuration() * 2, p.getActivePotionEffect(Potion.regeneration).getAmplifier()));
							p.removePotionEffect(Potion.regeneration.id);
						}
						if (p.getActivePotionEffect(Potion.poison) != null) {
							p.addPotionEffect(new PotionEffect(mod_RpgInventory.masochismPotion.id, p.getActivePotionEffect(Potion.poison).getDuration() / 2, p.getActivePotionEffect(Potion.poison).getAmplifier()));
							p.removePotionEffect(Potion.poison.id);
						}
					}

					/*==== EMERALD WATER BREATHING ==== */
					//works
					if (neck != null && neck.getItem().equals(mod_RpgInventory.neckem))
					{
						boolean flag = p instanceof EntityPlayer && ((EntityPlayer)p).capabilities.disableDamage;

						if (p.isEntityAlive() && p.isInsideOfMaterial(Material.water) && !flag)
						{
							p.setAir(decreaseAirSupply(p.getAir()+1));

							if (p.getAir() == -20)
							{
								p.setAir(0);

								for (int i = 0; i < 8; ++i)
								{
									float f = this.rand.nextFloat() - this.rand.nextFloat();
									float f1 = this.rand.nextFloat() - this.rand.nextFloat();
									float f2 = this.rand.nextFloat() - this.rand.nextFloat();
									p.worldObj.spawnParticle("bubble", p.posX + (double)f, p.posY + (double)f1, p.posZ + (double)f2, p.motionX, p.motionY, p.motionZ);
								}
								p.attackEntityFrom(DamageSource.drown, 1);
							}
							p.extinguish();
						}
						else
						{
							p.setAir(300);
						}
					}

					/*==== EMERALD CURE ==== */
					//works
					if(ringb != null && ringb.getItem().equals(mod_RpgInventory.ringem)) {
						for (Integer id : negativeEffects) {
							p.removePotionEffect(id);
						}
					}

					/*==== SPEED BOOST GOLD JEWELS ==== */					
					double speedboost = 0;
					int goldenItems = 0;

					if (neck != null && neck.itemID == mod_RpgInventory.neckgold.itemID) {
						speedboost += 0.01250F;
						this.goldJump(p,0.01D);
						goldenItems +=1;
					}
					if (ringa != null && ringa.getItem() == mod_RpgInventory.ringgold) {
						speedboost += 0.01250F;
						this.goldJump(p,0.02D);	
						goldenItems +=1;
					}
					if (ringb != null && ringb.getItem() == mod_RpgInventory.ringgold) {
						speedboost += 0.01250F;
						this.goldJump(p,0.02D);
						goldenItems +=1;
					}
					if (gloves != null && gloves.getItem() == mod_RpgInventory.glovesbutter) {
						speedboost += 0.01250F;
						this.goldJump(p,0.01D);
						goldenItems +=1;
					}
					if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.PALADIN)) {
						speedboost *= 0.75F;// slows down
					}

					p.motionX *= speedboost+1.0D;
					p.motionZ *= speedboost+1.0D;

					/*==== Something about the archer .__. ==== */
					if (ArcherRepairTick.containsKey(p.username)) {
						if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.ARCHER)) {
							p.jumpMovementFactor = 0.09F;
						} else {
							ArcherRepairTick.remove(p.username);
							p.jumpMovementFactor = 0.02F;
						}
					} else if (ArcherRepairTick.containsKey(p.username)) {
						if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.SHIELDEDMAGE)) {
							p.fallDistance = 0;
							evt.entityLiving.fireResistance = 200;
						} else {
							evt.entityLiving.fireResistance = 0;
						}
					}

					/*==== Invisibility Cloak==== */
					ItemStack cloak = inv.getCloak();
					if (cloak != null) {
						if (cloak.itemID == mod_RpgInventory.cloakI.itemID) {
							p.addPotionEffect(new PotionEffect(Potion.invisibility.id, 20, 1));
						}
					}
				}
			}
		} catch (Throwable ex) {
			FMLLog.getLogger().info("Failed to update Player !! rpgEventHooks");
		}
	}

	@ForgeSubscribe
	public void PlayerDamage(LivingHurtEvent evt) {

		try {
			/*ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS*/
			Entity damager = evt.source.getSourceOfDamage();
			if (damager != null) {
				if (damager instanceof EntityPlayer) {
					float damagebonus = 0.0F;
					PlayerRpgInventory inv = PlayerRpgInventory.get((EntityPlayer) damager);
					inv.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer) damager);
					ItemStack weapon = ((EntityPlayer) damager).getCurrentEquippedItem();
					if (weapon != null) {
						if (weapon.itemID == mod_RpgInventory.hammer.itemID) {
							if (EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.BERSERKER)) {
								evt.ammount += 4;
							}
						} 
						else if (weapon.itemID == mod_RpgInventory.pala_weapon.itemID) {
							if (EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.PALADIN)) {
								evt.ammount += 3;
							}
						}
					}
					if (EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.PALADIN)) {
						if (damager.worldObj.isDaytime()) {
							evt.ammount += 3;
						}
						//paladin heals himself when hitting undead
						if (evt.entityLiving.isEntityUndead()) {
							if (((EntityPlayer) damager).getHealth() < ((EntityPlayer) damager).getMaxHealth()) {
								((EntityPlayer) damager).heal(1);
							}
							evt.ammount += 3;
							evt.entityLiving.setFire(4);
						}
					}
					if (EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.NECRO)) {
						if (!damager.worldObj.isDaytime()) {
							evt.ammount += 3;
						}
					}
					if (EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.BERSERKER)) {
						// hit harder with both hands 'free'
						if (!EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.SHIELDEDBERSERKER)) { 
							evt.ammount += 2;
						}
					}

					/*==== LAPIS BONUS ATTACK==== */
					ItemStack neck = inv.getNecklace();
					ItemStack ringa = inv.getRing1();
					ItemStack ringb = inv.getRing2();
					ItemStack gloves = inv.getGloves();
					if (neck != null && neck.getItem().equals(mod_RpgInventory.necklap)) {
						damagebonus += 0.3F;
					}
					if (ringa != null && ringa.getItem().equals(mod_RpgInventory.ringlap)) {
						damagebonus += 0.1F;
					}
					if (ringb != null && ringb.getItem().equals(mod_RpgInventory.ringlap)) {
						damagebonus += 0.1F;
					}
					if (gloves != null && gloves.getItem().equals(mod_RpgInventory.gloveslap)) {
						damagebonus += 0.2F;
					}
					//					FMLLog.getLogger().info("1 " + evt.ammount);
					evt.ammount += MathHelper.floor_float(damagebonus * ((float) evt.ammount));
					//					FMLLog.getLogger().info("2 " + evt.ammount);
				}
			}
		} catch (Throwable e) {
		}
		try {
			/*DAMAGING AND REDUCING DAMAGE / VANILLA SHIELDS*/
			if (evt.entityLiving != null && evt.entityLiving instanceof EntityPlayer) {
				float damageReduction = 0.0F;
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				PlayerRpgInventory inv = PlayerRpgInventory.get(player);
				inv.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer) evt.entityLiving);

				ItemStack shield = inv.getShield();
				if (shield != null) {
					if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.WOOD)) {
						vanillaReduction += 0.27f;
					} else if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.IRON)) {
						vanillaReduction += 0.4f;
					} else if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.GOLD)) {
						vanillaReduction += 0.7f;
					} else if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.DIAMOND)) {
						vanillaReduction += 1.50f;
					}
					if(vanillaReduction > 1f){
						damageReduction = 1f + (vanillaReduction - 1f);
						vanillaReduction= 0;
					}
					evt.ammount -= damageReduction;
					//MathHelper.floor_float(((float) evt.ammount) * damageReduction);
					damageItem(shield, inv, player, 1, 1);
				}
			}
		} catch (Throwable e) {
		}
		try {
			if (evt.entityLiving != null && evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				PlayerRpgInventory inv = PlayerRpgInventory.get(player);
				inv.classSets = EnumRpgClass.getPlayerClasses(player);
				if (inv.getGloves() != null && inv.getGloves().getItem() == mod_RpgInventory.glovesem) {
					if (((float) evt.ammount * 0.2F) < 1) {
						evt.ammount -= 1;
					} else {
						evt.ammount -= MathHelper.floor_float((float) evt.ammount * 0.2F);
					}
				}
				if (inv != null) {
					ItemStack shield = inv.getShield();
					if (shield != null) {
						if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.SHIELDEDMAGE)) {
							float damageReduction = 0.20F;
							EntityLiving damagedealer = null;
							if (evt.source.isMagicDamage()) {
								damageReduction = 0.50F;
							} else if (evt.source.getSourceOfDamage() != null) {
								if (evt.source.isProjectile()) {
									if (evt.source.getSourceOfDamage() instanceof EntityArrow) {
										if (((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity != null) {
											if (((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity instanceof EntityLiving) {
												damagedealer = (EntityLiving) ((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity;
											}
										}
									}
									if (evt.source.getSourceOfDamage() instanceof EntityLiving) {
										damagedealer = (EntityLiving) evt.source.getSourceOfDamage();
									}
								}
							}
							if (damagedealer != null) {
								if (damagedealer.isEntityUndead()) {
									damageReduction = 0.75F;
								}
							}
							evt.ammount -= MathHelper.floor_float(((float) evt.ammount) * damageReduction);
							damageItem(shield, inv, player, 1, 1);

						} else if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.SHIELDEDARCHER)) {
							float damageReduction = 0.25F;
							EntityLiving damagedealer = null;
							if (evt.source.getSourceOfDamage() != null) {
								if (evt.source.isProjectile()) {
									if (evt.source.getSourceOfDamage() instanceof EntityArrow) {
										if (((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity != null) {
											if (((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity instanceof EntityLiving) {
												damagedealer = (EntityLiving) ((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity;
												damageReduction = 0.70F;
											}
										}
									}
									if (evt.source.getSourceOfDamage() instanceof EntityLiving) {
										damagedealer = (EntityLiving) evt.source.getSourceOfDamage();
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
								evt.ammount += MathHelper.floor_float(((float) evt.ammount) * 0.10F);
							} else {
								evt.ammount -= MathHelper.floor_float(((float) evt.ammount) * damageReduction);
							}
							damageItem(shield, inv, player, 1, 1);
						}
					} else if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.SHIELDEDBERSERKER)) {
						float damageReduction = 0.50F;
						evt.ammount -= MathHelper.floor_float(((float) evt.ammount) * damageReduction);
						if (evt.ammount > 1) {
							//Flat 1 damage absorption on top of resistance if the damage is greater than 1.
							//The additional absorbtion alone will never reduce all damage.
							evt.ammount -= 1;
						}
						damageItem(shield, inv, player, 1, 1);
					}
				}
			}
		} catch (Throwable e) {
		}

		try {

			PlayerRpgInventory inv = PlayerRpgInventory.get((EntityPlayer)evt.entityLiving);
			inv.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer)evt.entityLiving);

			ItemStack neck = inv.getNecklace();
			ItemStack ringa = inv.getRing1();
			ItemStack ringb = inv.getRing2();
			ItemStack gloves = inv.getGloves();

			/*==== NEGATE FALLDAMAGE GOLD JEWELS ==== */					
			double speedboost = 0;
			int goldenItems = 0;

			if (neck != null && neck.getItem() == mod_RpgInventory.neckgold) {
				goldenItems +=1;
			}
			if (ringa != null && ringa.getItem() == mod_RpgInventory.ringgold) {
				goldenItems +=1;
			}
			if (ringb != null && ringb.getItem() == mod_RpgInventory.ringgold) {
				goldenItems +=1;
			}
			if (gloves != null && gloves.getItem() == mod_RpgInventory.glovesbutter) {
				goldenItems +=1;
			}

			if( goldenItems == 4 && evt.source.equals(DamageSource.fall)){
				//				FMLLog.getLogger().info("" + evt.ammount);
				if(evt.ammount < 3){
					evt.setCanceled(true);
				}else{
					evt.ammount -=2;
				}

			}

		} catch (Exception e) {
		}

	}

	public void damageItem(ItemStack item, PlayerRpgInventory inv, EntityPlayer p, int slot, int amount) {
		if (mod_RpgInventory.developers.contains(p.username.toLowerCase())) return;
		try {
			if (item.getItemDamage() + amount >= item.getMaxDamage()) {
				//Trigger item break stuff
				item = null;
			} else {
				item.damageItem(amount, p);
			}
			inv.setInventorySlotContents(slot, item);
		} catch (Throwable e) {
		}

	}

	public void goldJump(EntityPlayer p, double amount)
	{
		float jumpTicks = 0;

		if (p.isAirBorne)//is jumping
		{
			if (!p.isInWater() && !p.handleLavaMovement())
			{
				if (jumpTicks == 0)
				{
					p.motionY += amount;
					jumpTicks = 10;
				}
			}
			else
			{
				p.motionY += 0.03999999910593033D;
			}
		}
		else
		{
			jumpTicks = 0;
		}
	}

	protected int decreaseAirSupply(int par1)
	{
		Random rand = new Random();
		int j = 5;
		return j > 0 && rand.nextInt(j + 1) > 0 ? par1 : par1 - 1;
	}
}
