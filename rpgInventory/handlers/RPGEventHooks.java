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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.utils.AbstractArmor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

public class RPGEventHooks {

	public static final int BOOTS = 36;
	public static final int LEGS = 37;
	public static final int CHEST = 38;
	public static final int HELM = 39;

	public static Map<String, Integer> HealerTick = new ConcurrentHashMap();
	public static Map<String, Integer> DiamondTick = new ConcurrentHashMap();
	public static Map<String, Integer> LapisTick = new ConcurrentHashMap();
	public static List<Integer> negativeEffects = new ArrayList();
	private static Random rand = new Random();

	private float vanillaReduction = 0f;

	@SubscribeEvent
	public void onClone(Clone evt){

		if (evt.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")){

			NBTTagCompound tag = new NBTTagCompound();

			PlayerRpgInventory.get(evt.entityPlayer).writeToNBT(tag);

			PlayerRpgInventory.get(evt.original).loadNBTData(tag);

		}
	}

	@SubscribeEvent
	public void BreakSpeed(PlayerEvent.BreakSpeed evt) {

		/*
		 * Increases Block-breaking speed while wearing emerald ring (right
		 * slot)
		 */
		if (evt.entityPlayer != null) {
			PlayerRpgInventory inv = PlayerRpgInventory
					.get(evt.entityPlayer);
			ItemStack ringa = inv.getRing1();
			if ((ringa != null)&& ringa.getItem().equals(RpgInventoryMod.ringem)) {
				evt.newSpeed = evt.originalSpeed * 4;
			}
		}
	}

	public void damageItem(ItemStack item, PlayerRpgInventory inv,EntityPlayer p, int slot, int amount) {
		if (RpgInventoryMod.developers.contains(p.getCommandSenderName()
				.toLowerCase())) {
			return;
		}
		if ((item.getItemDamage() + amount) >= item.getMaxDamage()) {
			// Trigger item break stuff
			item = null;
		} else {
			item.damageItem(amount, p);
		}
		inv.setInventorySlotContents(slot, item);
	}

	public void dropJewels(EntityPlayer player) {

		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {

			PlayerRpgInventory rpg = PlayerRpgInventory.get(player);
			int var1;

			player.inventory.dropAllItems();

			for (var1 = 0; var1 < rpg.armorSlots.length; ++var1){

				if (rpg.getStackInSlot(var1) != null) {
					player.inventory.setInventorySlotContents(player.inventory.getFirstEmptyStack(),rpg.getStackInSlot(var1));
					rpg.setInventorySlotContents(var1, null);
				}
			}
			player.inventory.dropAllItems();
		}
	}

	@SubscribeEvent
	public void DeathEvent(LivingDeathEvent evt) {

		if(evt.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.entityLiving;

			if (!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
				dropJewels(player);
			}
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
		if ((event.entity instanceof EntityPlayer)&& (PlayerRpgInventory.get((EntityPlayer) event.entity) == null)) {
			PlayerRpgInventory.register((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void PlayerDamage(LivingHurtEvent evt) {

		/* ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS */
		Entity damager = evt.source.getSourceOfDamage();
		if (damager != null) {
			if (damager instanceof EntityPlayer) {
				float damagebonus = 0.0F;
				PlayerRpgInventory inv = PlayerRpgInventory.get((EntityPlayer) damager);

				/* ==== LAPIS BONUS ATTACK==== */
				ItemStack neck = inv.getNecklace();
				ItemStack ringa = inv.getRing1();
				ItemStack ringb = inv.getRing2();
				ItemStack gloves = inv.getGloves();

				if ((neck != null)&& neck.getItem().equals(RpgInventoryMod.necklap)) {
					damagebonus += 0.3F;
				}
				if ((ringa != null)&& ringa.getItem().equals(RpgInventoryMod.ringlap)) {
					damagebonus += RpgInventoryMod.donators.contains(((EntityPlayer) damager).getCommandSenderName()) ? 0.2f : 0.1F;
				}
				if ((ringb != null)&& ringb.getItem().equals(RpgInventoryMod.ringlap)) {
					damagebonus += RpgInventoryMod.donators.contains(((EntityPlayer) damager).getCommandSenderName()) ? 0.2f : 0.1F;
				}
				if ((gloves != null)&& gloves.getItem().equals(RpgInventoryMod.gloveslap)) {
					damagebonus += 0.2F;
				}
				evt.ammount += MathHelper.floor_float(damagebonus* (evt.ammount));

			}
		}

		/* DAMAGING AND REDUCING DAMAGE FOR SHIELDS */
		if ((evt.entityLiving != null)&& (evt.entityLiving instanceof EntityPlayer)) {
			float damageReduction = 0.0F;
			EntityPlayer player = (EntityPlayer) evt.entityLiving;
			PlayerRpgInventory inv = PlayerRpgInventory.get(player);

			ItemStack shield = inv.getShield();
			if (shield != null) {

				if ((((ItemRpgInvArmor) inv.getShield().getItem()).bindShieldToArmorClass() + ((ItemRpgInvArmor) inv.getShield().getItem()).shieldClass()).equals(RpgInventoryMod.playerClass)) {
					vanillaReduction += 0.6f;
				}

				if (vanillaReduction > 1f) {
					damageReduction = 1f + (vanillaReduction - 1f);
					vanillaReduction = 0;
				}

				evt.ammount -= damageReduction;

				//only damage shields after mob, player or arrow damage
				if(evt.source.damageType.equals("player") || evt.source.damageType.equals("mob") || evt.source.damageType.equals("arrow")) {
					damageItem(shield, inv, player, 1, 1);
				}
			}
		}

		/*emerald gloves damage reduction*/
		if ((evt.entityLiving != null)&& (evt.entityLiving instanceof EntityPlayer)) {
			EntityPlayer player = (EntityPlayer) evt.entityLiving;
			PlayerRpgInventory inv = PlayerRpgInventory.get(player);
			if ((inv.getGloves() != null)&& (inv.getGloves().getItem() == RpgInventoryMod.glovesem)) {
				if ((evt.ammount * 0.2F) < 1) {
					evt.ammount -= 1;
				} else {
					evt.ammount -= MathHelper.floor_float(evt.ammount* (RpgInventoryMod.donators.contains(player.getCommandSenderName()) ? 0.3f : 0.2F));
				}
			}
		}

	}

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		if (evt.entityLiving instanceof EntityPlayer) {	

			EntityPlayer p = (EntityPlayer) evt.entityLiving;

			if (p != null) {

				PlayerRpgInventory inv = PlayerRpgInventory.get(p);

				/*keeps the owner's inventory up to date with himself.*/
				PlayerRpgInventory.get((EntityPlayer) evt.entityLiving).markDirty();

				keepJewelMapsSynced(inv);

				ItemStack neck = inv.getNecklace();
				ItemStack ringa = inv.getRing1();
				ItemStack ringb = inv.getRing2();
				ItemStack gloves = inv.getGloves();

				updateWaterBreathing(neck, p);

				updateNegateNegativeEffects(ringb,p);

				updateGoldenJewelryEffects(neck, ringa, ringb, gloves, p);

				notifyLapisPrescence(neck, ringa, ringb, gloves, p);

				determinePlayerClass(p);
			}
		}
	}

	private void keepJewelMapsSynced(PlayerRpgInventory inv){

		EntityPlayer player = inv.getPlayer();
		boolean addtoticks[] = new boolean[3];

		if (((inv.getNecklace() != null) && (inv.getNecklace().getItem() == RpgInventoryMod.neckdia))
				|| ((inv.getRing1() != null) && (inv.getRing1().getItem() == RpgInventoryMod.ringdia))
				|| ((inv.getRing2() != null) && (inv.getRing2().getItem() == RpgInventoryMod.ringdia))
				|| ((inv.getGloves() != null) && (inv.getGloves().getItem() == RpgInventoryMod.glovesdia))) {
			addtoticks[2] = true;
		}

		if (addtoticks[1]) {
			if (!RPGEventHooks.HealerTick.containsKey(player.getCommandSenderName())) {
				RPGEventHooks.HealerTick.put(player.getCommandSenderName(),0);
			}
		} else {
			// keep the cooldown hashmap clean
				RPGEventHooks.HealerTick.remove(player.getCommandSenderName());
		}

		if (addtoticks[2]) {
			if (!RPGEventHooks.DiamondTick.containsKey(player
					.getCommandSenderName())) {
				RPGEventHooks.DiamondTick.put(
						player.getCommandSenderName(), 0);
			}
		} else {
			// keep the cooldown hashmap clean
				RPGEventHooks.DiamondTick.remove(player.getCommandSenderName());
		}
	}


	private void updateWaterBreathing(ItemStack neck, EntityPlayer p){

		if ((neck != null)&& neck.getItem().equals(RpgInventoryMod.neckem)) {

			boolean flag = (p instanceof EntityPlayer)&& p.capabilities.disableDamage;

			if (p.isEntityAlive()&& p.isInsideOfMaterial(Material.water)&& !flag) {
				p.setAir(decreaseAirSupply(p.getAir() + 1));

				if (p.getAir() == -20) {
					p.setAir(0);

					for (int i = 0; i < 8; ++i) {
						float f = this.rand.nextFloat()- this.rand.nextFloat();
						float f1 = this.rand.nextFloat()- this.rand.nextFloat();
						float f2 = this.rand.nextFloat()- this.rand.nextFloat();
						p.worldObj.spawnParticle("bubble", p.posX+ f, p.posY + f1, p.posZ + f2,p.motionX, p.motionY, p.motionZ);
					}
					p.attackEntityFrom(DamageSource.drown, 1);
				}
				p.extinguish();
			} else {
				p.setAir(300);
			}
		}
	}

	private void updateNegateNegativeEffects(ItemStack ringb, EntityPlayer p) {
		if ((ringb != null)&& ringb.getItem().equals(RpgInventoryMod.ringem)) 
			for (Integer id : negativeEffects) 
				p.removePotionEffect(id);
	}

	private void updateGoldenJewelryEffects(ItemStack neck,ItemStack ringa, ItemStack ringb, ItemStack gloves, EntityPlayer p){
		float speedboost = 0;
		if ((neck != null)&& (neck.getItem() == RpgInventoryMod.neckgold)) {
			speedboost += RpgInventoryMod.donators.contains(p.getCommandSenderName()) ? 0.02f : 0.0125f;
		}
		if ((ringa != null)&& (ringa.getItem() == RpgInventoryMod.ringgold)) {
			speedboost += RpgInventoryMod.donators.contains(p.getCommandSenderName()) ? 0.02f : 0.0125f;
		}
		if ((ringb != null)&& (ringb.getItem() == RpgInventoryMod.ringgold)) {
			speedboost += RpgInventoryMod.donators.contains(p.getCommandSenderName()) ? 0.02f : 0.0125f;
		}
		if ((gloves != null)&& (gloves.getItem() == RpgInventoryMod.glovesbutter)) {
			speedboost += RpgInventoryMod.donators.contains(p.getCommandSenderName()) ? 0.02f : 0.0125f;
		}
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			p.capabilities.setPlayerWalkSpeed(0.1f + speedboost);

	}


	/**
	 * This checks wether the player wears class armor, and a shield, or
	 * just a shield (like vanilla shields)
	 */
	private void determinePlayerClass(EntityPlayer player){

		boolean skip = false;
		String classname = "";

		/*checking armor...*/
		for (ItemStack is : player.inventory.armorInventory) {
			if (is == null) {
				//if all armor is null, check for the shield
				if (PlayerRpgInventory.get(player).getShield() == null) {
					skip = true;
					classname = "none";
					break;//no need to iterate 4 times over the shield
				}
			} else // if there is one item that is no AbstractArmor, skip the setting of the playerclass
				if (!(is.getItem() instanceof AbstractArmor)) {
					// && (PlayerRpgInventory.get(player).getShield() == null)
					//chipped out that code ^ . i didnt see the point of also checking for the shield, as a play class can be made up of armor only
					skip = true;
					classname = "none";
					break;//no need to check for the next if one item is not classArmor
				}
		}
		if (!skip) {
			if ((player.inventory.getStackInSlot(HELM) != null)
					&& ((player.inventory.getStackInSlot(HELM).getItem()) instanceof AbstractArmor)
					&& (player.inventory.getStackInSlot(CHEST) != null)
					&& ((player.inventory.getStackInSlot(CHEST).getItem()) instanceof AbstractArmor)
					&& (player.inventory.getStackInSlot(LEGS) != null)
					&& ((player.inventory.getStackInSlot(LEGS).getItem()) instanceof AbstractArmor)
					&& (player.inventory.getStackInSlot(BOOTS) != null)
					&& ((player.inventory.getStackInSlot(BOOTS).getItem()) instanceof AbstractArmor)) {

				// this could be any piece of armor, given
				String a = ((AbstractArmor) player.inventory.getStackInSlot(HELM).getItem()).armorClassName();
				String b = ((AbstractArmor) player.inventory.getStackInSlot(CHEST).getItem()).armorClassName();
				String c = ((AbstractArmor) player.inventory.getStackInSlot(LEGS).getItem()).armorClassName();
				String d = ((AbstractArmor) player.inventory.getStackInSlot(BOOTS).getItem()).armorClassName();

				if(a.equals(b) && a.equals(c) && a.equals(d)){
					classname = a;
				}
			} else {
				classname = "none";
			}

			RpgInventoryMod.playerClass = classname;
			if (PlayerRpgInventory.get(player).getShield() != null) {
				if (((ItemRpgInvArmor) PlayerRpgInventory.get(player).getShield().getItem()).bindShieldToArmorClass().equals(classname)) {
					RpgInventoryMod.playerClass = classname+ ((ItemRpgInvArmor) PlayerRpgInventory.get(player).getShield().getItem()).shieldClass();
				}
			}
		}
	}



	/**Player update sets prescence of healing tick. TickHandler handles the actual regeneration */
	private void notifyLapisPrescence(ItemStack neck,ItemStack ringa, ItemStack ringb, ItemStack gloves, EntityPlayer p){
		/* ====LAPIS WEAPON HEALING==== */
		boolean armorheal = false;
		if ((neck != null)&& neck.getItem().equals(RpgInventoryMod.necklap)) {
			armorheal = true;
		}
		if ((ringa != null)&& ringa.getItem().equals(RpgInventoryMod.ringlap)) {
			armorheal = true;
		}
		if ((ringb != null)&& ringb.getItem().equals(RpgInventoryMod.ringlap)) {
			armorheal = true;
		}
		if ((gloves != null)&& gloves.getItem().equals(RpgInventoryMod.gloveslap)) {
			armorheal = true;
		}
		if (armorheal) {
			if (!LapisTick.containsKey(p.getCommandSenderName())) {
				LapisTick.put(p.getCommandSenderName(), 60);
			}
		} else {
			LapisTick.remove(p.getCommandSenderName());
		}
	}
}
