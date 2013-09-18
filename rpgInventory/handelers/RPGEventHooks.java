/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.handelers;

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
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.EnumRpgClass;
import rpgInventory.IPet;
import rpgInventory.mod_RpgInventory;
import rpgInventory.entity.EntityPetXP;
import rpgInventory.gui.rpginv.RpgInv;
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
	public void BreakSpeed(PlayerEvent.BreakSpeed evt) {
		try {
			if (evt.entityPlayer != null) {
				RpgInv rpginv = mod_RpgInventory.proxy.getInventory(evt.entityPlayer.username);
				ItemStack ringa = rpginv.getRing1();
				if (ringa != null && ringa.getItem().equals(mod_RpgInventory.ringem)) {
					evt.newSpeed = evt.originalSpeed * 2;

					//TODO this should like the potion effect Haste. not speed x)
				}
			}
		} catch (Throwable e) {
		}
	}

	@ForgeSubscribe
	public void DeathEvent(LivingDeathEvent evt) {
		try {
			if ((!evt.entityLiving.worldObj.isRemote) && evt.entityLiving != null && evt.source != null && evt.source.getSourceOfDamage() != null && evt.source.getSourceOfDamage() instanceof IPet) {
				EntityLivingBase corpse = evt.entityLiving;
				EntityLivingBase murderer = (EntityLiving)evt.source.getSourceOfDamage();
				int totalXP = corpse.field_110158_av;
				while (totalXP > 0) {
					int partialXP = EntityXPOrb.getXPSplit(totalXP);
					totalXP -= partialXP;
					corpse.worldObj.spawnEntityInWorld(new EntityPetXP(murderer.worldObj, corpse.posX, corpse.posY, corpse.posZ, partialXP));
				}
			}
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				RpgInv rpginv = mod_RpgInventory.proxy.getInventory(player.username);
//				rpginv.classSets = EnumRpgClass.getPlayerClasses();
				if (rpginv.hasClass(EnumRpgClass.BERSERKER)) {
					//newExplosion(entitytoexplode,x,y,z,power,setfire,breakblocks);
					player.worldObj.newExplosion((Entity) player, player.posX, player.posY, player.posZ, 20, false, false);
				}
			}

		} catch (Throwable e) {
		}
	}
	/*
     @ForgeSubscribe
     public void EntityUpdate(LivingUpdateEvent evt) {

     }
	 */
	
//	@ForgeSubscribe
//	public void PlayerRender(RenderPlayerEvent.Specials evt ){
//		
//	}

	@ForgeSubscribe
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {
		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				mod_RpgInventory.proxy.getInventory(((EntityPlayer)evt.entityLiving).username).onInventoryChanged();
			}
		} catch (Throwable ex) {
		}

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {
					//					if (p.func_110143_aJ() >= p.func_110138_aP()) {
					//						p.setEntityHealth(p.func_110138_aP());
					//					}

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

					RpgInv rpginv = mod_RpgInventory.proxy.getInventory(p.username);
					rpginv.classSets = EnumRpgClass.getPlayerClasses(p);

					ItemStack neck = rpginv.getNecklace();
					ItemStack ringa = rpginv.getRing1();
					ItemStack ringb = rpginv.getRing2();
					ItemStack gloves = rpginv.getGloves();
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
					float jumpboost = p.jumpMovementFactor;
					if (rpginv.hasClass(EnumRpgClass.ARCHER)) {
						jumpboost *= 4.0F;
					}
					p.jumpMovementFactor = jumpboost;
					ItemStack weapon = p.getCurrentEquippedItem();
					if (weapon != null) {
						if (EnumRpgClass.getPlayerClasses(p).contains(EnumRpgClass.SHIELDEDARCHMAGE) || mod_RpgInventory.developers.contains(p.username.toLowerCase()))
						{
							if (weapon.getItem().equals(mod_RpgInventory.fireStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{if (p.isBurning()) {if (p.func_110143_aJ() < 6) {p.setEntityHealth(6);} p.extinguish();}}
							if (weapon.getItem().equals(mod_RpgInventory.windStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{p.fallDistance = 0;}
							if (weapon.getItem().equals(mod_RpgInventory.frostStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{if (p.getAir() < 20)p.setAir(20);}
							if (weapon.getItem().equals(mod_RpgInventory.earthStaff) || weapon.getItem().equals(mod_RpgInventory.ultimateStaff)) 
							{p.curePotionEffects(new ItemStack(Item.bucketMilk, 1));}
						}
						if (weapon.itemID == mod_RpgInventory.hammer.itemID) {

							if (rpginv.hasClass(EnumRpgClass.SHIELDEDBERSERKER)) {
								if ((p.getFoodStats().getFoodLevel() < 4 || p.func_110143_aJ() < 4)) {
									Map tmp = EnchantmentHelper.getEnchantments(weapon);
									tmp.put(Enchantment.knockback.effectId, 3);
									EnchantmentHelper.setEnchantments(tmp, weapon);
								} else {
									Map tmp = EnchantmentHelper.getEnchantments(weapon);
									tmp.put(Enchantment.knockback.effectId, 2);
									EnchantmentHelper.setEnchantments(tmp, weapon);
								}
							} else if (rpginv.hasClass(EnumRpgClass.BERSERKER)) {
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

					if (rpginv.hasClass(EnumRpgClass.NECRO)) {
						if (p.getActivePotionEffect(Potion.regeneration) != null) {
							p.addPotionEffect(new PotionEffect(mod_RpgInventory.decomposePotion.id, p.getActivePotionEffect(Potion.regeneration).getDuration() * 2, p.getActivePotionEffect(Potion.regeneration).getAmplifier()));
							p.removePotionEffect(Potion.regeneration.id);
						}
						if (p.getActivePotionEffect(Potion.poison) != null) {
							p.addPotionEffect(new PotionEffect(mod_RpgInventory.masochismPotion.id, p.getActivePotionEffect(Potion.poison).getDuration() / 2, p.getActivePotionEffect(Potion.poison).getAmplifier()));
							p.removePotionEffect(Potion.poison.id);
						}
					}
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

					if(ringb != null && ringb.getItem().equals(mod_RpgInventory.ringem)) {
						for (Integer id : negativeEffects) {
							p.removePotionEffect(id);
						}
					}

					float speedboost = p.getAIMoveSpeed();
					if (neck != null && neck.itemID == mod_RpgInventory.neckgold.itemID) {
						speedboost += 0.020F;
						this.goldJump(p,0.01D);
					}
					if (ringa != null && ringa.getItem() == mod_RpgInventory.ringgold) {
						speedboost += 0.020F;
						this.goldJump(p,0.02D);	
					}
					if (ringb != null && ringb.getItem() == mod_RpgInventory.ringgold) {
						speedboost += 0.020F;
						this.goldJump(p,0.02D);
					}
					if (gloves != null && gloves.getItem() == mod_RpgInventory.glovesbutter) {
						speedboost += 0.020F;
						this.goldJump(p,0.01D);
					}
					if (rpginv.hasClass(EnumRpgClass.PALADIN)) {
						speedboost *= 0.75F;
					}
					p.setAIMoveSpeed(speedboost);

					if (ArcherRepairTick.containsKey(p.username)) {
						if (rpginv.hasClass(EnumRpgClass.ARCHER)) {
							p.jumpMovementFactor = 0.09F;
						} else {
							ArcherRepairTick.remove(p.username);
							p.jumpMovementFactor = 0.02F;
						}
					} else if (ArcherRepairTick.containsKey(p.username)) {
						if (rpginv.hasClass(EnumRpgClass.SHIELDEDMAGE)) {
							p.fallDistance = 0;
							evt.entityLiving.fireResistance = 200;
						} else {
							evt.entityLiving.fireResistance = 0;
						}
					}
					ItemStack cloak = mod_RpgInventory.proxy.getInventory(p.username).getCloak();
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
		//FMLLog.getLogger().info("before: "+ evt.ammount);

		try {
			Entity damager = evt.source.getSourceOfDamage();
			if (damager != null) {
				if (damager instanceof EntityPlayer) {
					float damagebonus = 0.0F;
					RpgInv rpginv = mod_RpgInventory.proxy.getInventory(((EntityPlayer) damager).username);
					rpginv.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer) damager);
					ItemStack weapon = ((EntityPlayer) damager).getCurrentEquippedItem();
					if (weapon != null) {
						if (weapon.itemID == mod_RpgInventory.hammer.itemID) {
							if (rpginv.hasClass(EnumRpgClass.BERSERKER)) {
								evt.ammount += 4;
							}
						} else if (weapon.itemID == mod_RpgInventory.pala_weapon.itemID) {
							if (rpginv.hasClass(EnumRpgClass.PALADIN)) {
								evt.ammount += 3;
							}
						}
					}
					if (rpginv.hasClass(EnumRpgClass.PALADIN)) {
						if (damager.worldObj.isDaytime()) {
							evt.ammount += 3;
						}
						if (evt.entityLiving.isEntityUndead()) {
							if (((EntityPlayer) damager).func_110143_aJ() < ((EntityPlayer) damager).func_110138_aP()) {
								((EntityPlayer) damager).heal(1);
							}
							evt.ammount += 3;
							evt.entityLiving.setFire(4);
						}
					}
					if (rpginv.hasClass(EnumRpgClass.NECRO)) {
						if (!damager.worldObj.isDaytime()) {
							evt.ammount += 3;
						}
					}
					if (rpginv.hasClass(EnumRpgClass.BERSERKER)) {
						if (!rpginv.hasClass(EnumRpgClass.SHIELDEDBERSERKER)) {
							evt.ammount += 2;
						}
					}
					ItemStack neck = rpginv.getNecklace();
					ItemStack ringa = rpginv.getRing1();
					ItemStack ringb = rpginv.getRing2();
					ItemStack gloves = rpginv.getGloves();
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
					evt.ammount += MathHelper.floor_float(damagebonus * ((float) evt.ammount));
				}
			}
		} catch (Throwable e) {
		}
		try {
			if (evt.entityLiving != null && evt.entityLiving instanceof EntityPlayer) {
				float damageReduction = 0.0F;
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				RpgInv rpginv = mod_RpgInventory.proxy.getInventory(player.username);
				rpginv.classSets = EnumRpgClass.getPlayerClasses((EntityPlayer) evt.entityLiving);

				ItemStack shield = rpginv.getShield();
				if (shield != null) {
					
					if (rpginv.hasClass(EnumRpgClass.WOOD)) {
						vanillaReduction += 0.27f;
					} else if (rpginv.hasClass(EnumRpgClass.IRON)) {
						vanillaReduction += 0.4f;
					} else if (rpginv.hasClass(EnumRpgClass.GOLD)) {
						vanillaReduction += 0.7f;
					} else if (rpginv.hasClass(EnumRpgClass.DIAMOND)) {
						vanillaReduction += 1.50f;
					}
					if(vanillaReduction > 1f){
						damageReduction = 1f + (vanillaReduction - 1f);
						vanillaReduction= 0;
					}
					evt.ammount -= damageReduction;//MathHelper.floor_float(((float) evt.ammount) * damageReduction);
					
					//FMLLog.getLogger().info("after: "+ evt.ammount);
					damageItem(shield, rpginv, player, 1, 1);
				}
			}
		} catch (Throwable e) {
		}
		try {
			if (evt.entityLiving != null && evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;
				RpgInv inv = mod_RpgInventory.proxy.getInventory(player.username);
				inv.classSets = EnumRpgClass.getPlayerClasses(player);
				ItemStack var3 = player.inventory.armorItemInSlot(3);
				ItemStack var2 = player.inventory.armorItemInSlot(2);
				ItemStack var1 = player.inventory.armorItemInSlot(1);
				ItemStack var0 = player.inventory.armorItemInSlot(0);
				Item item;
				Item item1;
				Item item2;
				Item item3;
				if (inv.getGloves() != null && inv.getGloves().getItem() == mod_RpgInventory.glovesem) {
					if (((float) evt.ammount * 0.2F) < 1) {
						evt.ammount -= 1;
					} else {
						evt.ammount -= MathHelper.floor_float((float) evt.ammount * 0.2F);
					}
				}
				if (var0 != null && var1 != null && var2 != null && var3 != null) {
					item = var3.getItem();
					item1 = var2.getItem();
					item2 = var1.getItem();
					item3 = var0.getItem();
					if (item != null && item1 != null && item2 != null && item3 != null) {
						if (inv != null) {
							ItemStack shield = inv.getShield();
							if (shield != null) {
								if (inv.hasClass(EnumRpgClass.SHIELDEDMAGE)) {
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

								} else if (inv.hasClass(EnumRpgClass.SHIELDEDARCHER)) {
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
							} else if (inv.hasClass(EnumRpgClass.SHIELDEDBERSERKER)) {
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
				}

			}

		} catch (Throwable e) {
		}

	}

	public void damageItem(ItemStack item, RpgInv inv, EntityPlayer p, int slot, int amount) {
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
