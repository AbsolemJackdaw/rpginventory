package rpgNecroPaladin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.FMLLog;

public class NecroPaladinEvents {

	
	@ForgeSubscribe
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt){
		
		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) evt.entityLiving;
			
			double mX = p.motionX;
			double mZ = p.motionZ;
			double speedBoost = 0;
			try {
				if (mod_RpgInventory.playerClass.contains(mod_RpgPlus.CLASSPALADIN)) {
					mX *= 0.75F;// slows down
					mZ *= 0.75F;
				}
				p.motionX = mX;
				p.motionZ = mZ;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				
				if (mod_RpgInventory.playerClass.contains(mod_RpgPlus.CLASSNECRO)) {
					if (p.getActivePotionEffect(Potion.regeneration) != null) {
						p.addPotionEffect(new PotionEffect(mod_RpgInventory.decomposePotion.id, p.getActivePotionEffect(Potion.regeneration).getDuration() * 2, p.getActivePotionEffect(Potion.regeneration).getAmplifier()));
						p.removePotionEffect(Potion.regeneration.id);
					}
					if (p.getActivePotionEffect(Potion.poison) != null) {
						p.addPotionEffect(new PotionEffect(mod_RpgInventory.masochismPotion.id, p.getActivePotionEffect(Potion.poison).getDuration() / 2, p.getActivePotionEffect(Potion.poison).getAmplifier()));
						p.removePotionEffect(Potion.poison.id);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
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
					ItemStack weapon = ((EntityPlayer) damager).getCurrentEquippedItem();					
					if (mod_RpgInventory.playerClass.contains(mod_RpgPlus.CLASSPALADIN) ) {
						if (damager.worldObj.isDaytime()) {
							evt.ammount += 2;
							if (weapon != null) {
								if (weapon.itemID == mod_RpgPlus.pala_weapon.itemID) {
									if (mod_RpgInventory.playerClass.contains(mod_RpgPlus.CLASSPALADIN) ) {
										evt.ammount += 2;
									}
								}
							}
						}
						//paladin heals himself when hitting undead
						if (evt.entityLiving.isEntityUndead()) {
							if (((EntityPlayer) damager).getHealth() < ((EntityPlayer) damager).getMaxHealth()) {
								((EntityPlayer) damager).heal( mod_RpgInventory.donators.contains( ((EntityPlayer)damager).username) ? 2 : 1);
							}
							evt.ammount += 3;
							evt.entityLiving.setFire( mod_RpgInventory.donators.contains( ((EntityPlayer)damager).username) ? 5 : 2);
						}
					}
					if (mod_RpgInventory.playerClass.contains(mod_RpgPlus.CLASSNECRO)){
						if (!damager.worldObj.isDaytime()) {
							evt.ammount += 3;
						}
					}
				}
			}
		} catch (Exception e) {
			FMLLog.getLogger().info("Couldnt add extra damage to paladin weapon. report to mod author.");
		}
	}
}
