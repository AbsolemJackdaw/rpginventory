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
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.utils.AbstractArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RPGEventHooks {

	public static final int BOOTS = 36;
	public static final int LEGS = 37;
	public static final int CHEST = 38;
	public static final int HELM = 39;

	public static Map<String, Integer> HealerTick = new ConcurrentHashMap();
	public static Map<String, Integer> DiamondTick = new ConcurrentHashMap();
	public static Map<String, Integer> LapisTick = new ConcurrentHashMap();
	public static List<Integer> negativeEffects = new ArrayList();
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
						&& ringa.getItem().equals(RpgInventoryMod.ringem))
					evt.newSpeed = evt.originalSpeed * 4;
			}
		} catch (Throwable e) {
		}
	}

	public void damageItem(ItemStack item, PlayerRpgInventory inv,
			EntityPlayer p, int slot, int amount) {
		if (RpgInventoryMod.developers.contains(p.getCommandSenderName()
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
	public void DeathEvent(LivingDeathEvent evt) {
		/* ====PET EXP==== */
		try {

			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) evt.entityLiving;

				PlayerRpgInventory inv = PlayerRpgInventory
						.get((EntityPlayer) evt.entityLiving);
				// if (mod_RpgInventory.playerClass
				// .contains(mod_RpgInventory.CLASSBERSERKER))
				// player.worldObj.newExplosion(player, player.posX,
				// player.posY, player.posZ, 20, false, false);
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
		 * not be necessary - I only use it to make sure properties are only
		 * registered once per entity
		 */
		if ((event.entity instanceof EntityPlayer)
				&& (PlayerRpgInventory.get((EntityPlayer) event.entity) == null))
			// This is how extended properties are registered using our
			// convenient method from earlier
			PlayerRpgInventory.register((EntityPlayer) event.entity);
	}

	@SubscribeEvent
	public void PlayerDamage(LivingHurtEvent evt) {


		/* ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS */
		Entity damager = evt.source.getSourceOfDamage();
		if (damager != null)
			if (damager instanceof EntityPlayer) {
				float damagebonus = 0.0F;
				PlayerRpgInventory inv = PlayerRpgInventory
						.get((EntityPlayer) damager);

				/* ==== LAPIS BONUS ATTACK==== */
				ItemStack neck = inv.getNecklace();
				ItemStack ringa = inv.getRing1();
				ItemStack ringb = inv.getRing2();
				ItemStack gloves = inv.getGloves();
				if ((neck != null)
						&& neck.getItem().equals(RpgInventoryMod.necklap))
					damagebonus += 0.3F;
				if ((ringa != null)
						&& ringa.getItem().equals(RpgInventoryMod.ringlap))
					damagebonus += RpgInventoryMod.donators
					.contains(((EntityPlayer) damager)
							.getCommandSenderName()) ? 0.2f : 0.1F;
				if ((ringb != null)
						&& ringb.getItem().equals(RpgInventoryMod.ringlap))
					damagebonus += RpgInventoryMod.donators
					.contains(((EntityPlayer) damager)
							.getCommandSenderName()) ? 0.2f : 0.1F;
				if ((gloves != null)
						&& gloves.getItem().equals(
								RpgInventoryMod.gloveslap))
					damagebonus += 0.2F;
				System.out.println(evt.ammount + "+ ("+evt.ammount+ "*" + damagebonus+")");
				evt.ammount += MathHelper.floor_float(damagebonus
						* (evt.ammount));
				
				System.out.println(evt.ammount);
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

					System.out.println(((ItemRpgInvArmor) inv.getShield().getItem())
							.boundArmorClass() + ((ItemRpgInvArmor) inv.getShield().getItem())
							.shieldClass());
					System.out.println(RpgInventoryMod.playerClass);
					if ((((ItemRpgInvArmor) inv.getShield().getItem()).boundArmorClass() + ((ItemRpgInvArmor) inv.getShield().getItem()).shieldClass())
							.equals(RpgInventoryMod.playerClass))
						vanillaReduction += 0.6f;

					if (vanillaReduction > 1f) {
						damageReduction = 1f + (vanillaReduction - 1f);
						vanillaReduction = 0;
					}
					System.out.println("1reduced : " + damageReduction + "\n2damage taken "+ evt.ammount);

					evt.ammount -= damageReduction;

					System.out.println("3total damage recieved" + evt.ammount);
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
						&& (inv.getGloves().getItem() == RpgInventoryMod.glovesem))
					if ((evt.ammount * 0.2F) < 1)
						evt.ammount -= 1;
					else
						evt.ammount -= MathHelper
						.floor_float(evt.ammount
								* (RpgInventoryMod.donators.contains(player
										.getCommandSenderName()) ? 0.3f
												: 0.2F));
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

		} catch (Exception e) {
		}
	}

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		/* ====UPDATING INVENTORY==== */
		try {
			if (evt.entityLiving instanceof EntityPlayer)
				PlayerRpgInventory.get((EntityPlayer) evt.entityLiving)
				.markDirty();

		} catch (Throwable ex) {
		}

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;

				if (p != null) {

					PlayerRpgInventory inv = PlayerRpgInventory.get(p);

					ItemStack neck = inv.getNecklace();
					ItemStack ringa = inv.getRing1();
					ItemStack ringb = inv.getRing2();
					ItemStack gloves = inv.getGloves();

					/* ====LAPIS WEAPON HEALING==== */
					boolean armorheal = false;
					if ((neck != null)
							&& neck.getItem().equals(RpgInventoryMod.necklap))
						armorheal = true;
					if ((ringa != null)
							&& ringa.getItem().equals(RpgInventoryMod.ringlap))
						armorheal = true;
					if ((ringb != null)
							&& ringb.getItem().equals(RpgInventoryMod.ringlap))
						armorheal = true;
					if ((gloves != null)
							&& gloves.getItem().equals(
									RpgInventoryMod.gloveslap))
						armorheal = true;
					if (armorheal) {
						if (!LapisTick.containsKey(p.getCommandSenderName()))
							LapisTick.put(p.getCommandSenderName(), 60);
					} else
						LapisTick.remove(p.getCommandSenderName());

					/* ==== EMERALD WATER BREATHING ==== */
					if ((neck != null)
							&& neck.getItem().equals(RpgInventoryMod.neckem)) {
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
						} else
							p.setAir(300);
					}

					/* ==== EMERALD CURE ==== */
					// works
					if ((ringb != null)
							&& ringb.getItem().equals(RpgInventoryMod.ringem))
						for (Integer id : negativeEffects)
							p.removePotionEffect(id);

					/* ==== SPEED BOOST GOLD JEWELS ==== */
					float speedboost = 0;
					int goldenItems = 0;

					if ((neck != null)
							&& (neck.getItem() == RpgInventoryMod.neckgold)) {
						speedboost += RpgInventoryMod.donators.contains(p
								.getCommandSenderName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					if ((ringa != null)
							&& (ringa.getItem() == RpgInventoryMod.ringgold)) {
						speedboost += RpgInventoryMod.donators.contains(p
								.getCommandSenderName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					if ((ringb != null)
							&& (ringb.getItem() == RpgInventoryMod.ringgold)) {
						speedboost += RpgInventoryMod.donators.contains(p
								.getCommandSenderName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					if ((gloves != null)
							&& (gloves.getItem() == RpgInventoryMod.glovesbutter)) {
						speedboost += RpgInventoryMod.donators.contains(p
								.getCommandSenderName()) ? 0.02f : 0.0125f;
						goldenItems += 1;
					}
					p.capabilities.setPlayerWalkSpeed(0.1f + speedboost);

					// if(p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()
					// !=
					// p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
					// + (double)speedboost){
					//

					/* ==== Invisibility Cloak==== */
					ItemStack cloak = inv.getCloak();
					if (cloak != null)
						if (cloak.getItem() == RpgInventoryMod.cloakI)
							p.addPotionEffect(new PotionEffect(
									Potion.invisibility.id, 20, 1));
				}
			}


		} catch (Throwable ex) {
		}


		//		System.out.println("reading this ... ");

		/**
		 * This checks wether the player wears class armor, and a shield, or
		 * just a shield (like vanilla shields)
		 */
		if (evt.entity instanceof EntityPlayer) {
			//			System.out.println("reading ...");

			EntityPlayer player = (EntityPlayer)evt.entityLiving;
			boolean skip = false;
			for (ItemStack is : player.inventory.armorInventory)
				if (is == null) {
					if (PlayerRpgInventory.get(player).getShield() == null) {
						skip = true;
						RpgInventoryMod.playerClass = "none";
					}
				} else // if there is one item that is no AbstractArmor, skip the setting of the playerclass
					if (!(is.getItem() instanceof AbstractArmor)&& (PlayerRpgInventory.get(player).getShield() == null)) {
						skip = true;
						RpgInventoryMod.playerClass = "none";
					}
			if (!skip)
				if ((player.inventory.getStackInSlot(HELM) != null)
						&& ((player.inventory.getStackInSlot(HELM).getItem()) instanceof AbstractArmor)
						&& (player.inventory.getStackInSlot(CHEST) != null)
						&& ((player.inventory.getStackInSlot(CHEST).getItem()) instanceof AbstractArmor)
						&& (player.inventory.getStackInSlot(LEGS) != null)
						&& ((player.inventory.getStackInSlot(LEGS).getItem()) instanceof AbstractArmor)
						&& (player.inventory.getStackInSlot(BOOTS) != null)
						&& ((player.inventory.getStackInSlot(BOOTS).getItem()) instanceof AbstractArmor)) {

					// this could be any piece of armor, given
					String classname = ((AbstractArmor) player.inventory
							.getStackInSlot(HELM).getItem()).armorClassName();

					RpgInventoryMod.playerClass = classname;

					if (PlayerRpgInventory.get(player).getShield() != null)
						if (((ItemRpgInvArmor) PlayerRpgInventory.get(player)
								.getShield().getItem()).boundArmorClass()
								.equals(classname))
							RpgInventoryMod.playerClass = classname
							+ ((ItemRpgInvArmor) PlayerRpgInventory
									.get(player).getShield().getItem())
									.shieldClass();
				} else {
					RpgInventoryMod.playerClass = "none";
					if (((ItemRpgInvArmor) PlayerRpgInventory.get(player)
							.getShield().getItem()).boundArmorClass().equals(
									"none"))
						if (PlayerRpgInventory.get(player).getShield() != null)
							RpgInventoryMod.playerClass = ((ItemRpgInvArmor) PlayerRpgInventory
									.get(player).getShield().getItem())
									.shieldClass();
				}

		}
	}
}
