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
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BaseaddonEventHooks {

	public void damageItem(ItemStack item, PlayerRpgInventory inv,
			EntityPlayer p, int slot, int amount) {
		if (RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase()))
			return;

		if ((item.getItemDamage() + amount) >= item.getMaxDamage()) {
			// Trigger item break stuff
			item = null;
		} else
			item.damageItem(amount, p);

		inv.setInventorySlotContents(slot, item);
	}

	@SubscribeEvent
	public void PlayerDamage(LivingHurtEvent evt) {

		Entity damager = evt.source.getSourceOfDamage();
		/**extra damage*/
		if (damager != null) {
			if (damager instanceof EntityPlayer){

				PlayerRpgInventory.get((EntityPlayer) damager);

				ItemStack weapon = ((EntityPlayer) damager).getCurrentEquippedItem();

				if (weapon != null) {
					if (weapon.getItem() == RpgBaseAddon.hammer) 
						if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKER)) 
							evt.ammount += 4;
				}
				// hit harder with both hands 'free'
				else if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKER)) 
					if (!RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKERSHIELD)) 
						evt.ammount += 2;
			}
		}

		/**damage reduction by shields*/
		if ((evt.entityLiving != null) && (evt.entityLiving instanceof EntityPlayer)) {

			EntityPlayer player = (EntityPlayer) evt.entityLiving;
			PlayerRpgInventory inv = PlayerRpgInventory.get(player);
			DamageSource src = evt.source;

			if (inv != null) {
				ItemStack shield = inv.getShield();

				if (shield != null) {

					if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSALCHEMISTSHIELD)) {

						float damageReduction = 0.20F;
						EntityLivingBase damagedealer = null;

						if (src.isMagicDamage()) {
							damageReduction = 0.50F;

						} else if (src.getSourceOfDamage() != null && evt.source.isProjectile() && src.getSourceOfDamage() instanceof EntityArrow) {
							EntityArrow arrow = ((EntityArrow) src.getSourceOfDamage());
							if (arrow.shootingEntity != null && arrow.shootingEntity instanceof EntityLivingBase) 
								damagedealer = (EntityLivingBase) ((EntityArrow) evt.source.getSourceOfDamage()).shootingEntity;
						}
						else if (evt.source.getSourceOfDamage() instanceof EntityLivingBase)
							damagedealer = (EntityLivingBase) evt.source.getSourceOfDamage();

						if (damagedealer != null) {
							if (damagedealer.isEntityUndead())
								damageReduction = 0.75F;
						}

						evt.ammount -= MathHelper.floor_float((evt.ammount)* damageReduction);
						damageItem(shield, inv, player, 1, 1);

					} else if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSARCHERSHIELD)) {

						float damageReduction = 0.25F;

						EntityLivingBase damagedealer = null;

						if (src.getSourceOfDamage() instanceof EntityLivingBase) 
							damagedealer = (EntityLivingBase) evt.source.getSourceOfDamage();

						else if (src.getSourceOfDamage() != null && evt.source.isProjectile() && src.getSourceOfDamage() instanceof EntityArrow) {

							EntityArrow arrow = ((EntityArrow) evt.source.getSourceOfDamage());

							if (arrow.shootingEntity != null && arrow.shootingEntity instanceof EntityLivingBase) {
								damagedealer = (EntityLivingBase)arrow.shootingEntity;
								damageReduction = 0.70F;
							}
						}
						if (damageReduction < 0.70F && damagedealer != null) {
							if (damagedealer.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) 
								damageReduction = 0.40F;
							if (damagedealer instanceof EntityTameable) 
								if (!damagedealer.isEntityUndead()) 
									damageReduction = 0.50F;
						}
						if (src.isFireDamage()) 
							evt.ammount += MathHelper.floor_float((evt.ammount) * 0.10F);
						else 
							evt.ammount -= MathHelper.floor_float((evt.ammount)* damageReduction);

						damageItem(shield, inv, player, 1, 1);
					}
					else if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKERSHIELD)) {
						float damageReduction = 0.50F;
						evt.ammount -= MathHelper.floor_float((evt.ammount)* damageReduction);
						if (evt.ammount > 1) {
							// Flat 1 damage absorption on top of resistance if the damage is greater than 1.
							// The additional absorbtion alone will never reduce all damage.
							evt.ammount -= 1;
						}
						damageItem(shield, inv, player, 1, 1);
					}
				} 
			}
		}
	}

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) evt.entityLiving;
			if (p != null) {

				ItemStack weapon = p.getCurrentEquippedItem();

				if (weapon != null) {
					if (weapon.getItem() == RpgBaseAddon.hammer) { //adds knockback effect to hammer
						if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKERSHIELD)) {
							if (((p.getFoodStats().getFoodLevel() < 4) || (p.getHealth() < 4))) {
								Map tmp = EnchantmentHelper.getEnchantments(weapon);
								tmp.put(Enchantment.knockback.effectId, 3);
								EnchantmentHelper.setEnchantments(tmp,weapon);
							} else {
								Map tmp = EnchantmentHelper.getEnchantments(weapon);
								tmp.put(Enchantment.knockback.effectId, 2);
								EnchantmentHelper.setEnchantments(tmp,weapon);
							}
						} else if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKER)) {
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
			}
		}

		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) evt.entityLiving;
			if (p != null) {

				PlayerRpgInventory.get(p);
				float jumpboost = p.jumpMovementFactor;
				if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSARCHER)) {
					jumpboost *= 2.0F;
				}
				p.jumpMovementFactor = jumpboost;

				/* ====MAGE FALLDAMAGE NEGATION==== */
				if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSALCHEMISTSHIELD)) {
					p.fallDistance = 0;
				}
			}
		}
	}
}
