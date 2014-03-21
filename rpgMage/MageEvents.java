package rpgMage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.common.FMLLog;

public class MageEvents {

	@ForgeSubscribe
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				ItemStack weapon = p.getCurrentEquippedItem();
				if (weapon != null) {
					/* ==== ARCHMAGE EFFECTS ==== */
					if (mod_RpgInventory.playerClass
							.contains(mod_RpgMageSet.CLASSARCHMAGESHIELD)
							|| mod_RpgInventory.developers.contains(p.username
									.toLowerCase())) {
						if (weapon.getItem().equals(mod_RpgMageSet.fireStaff)
								|| weapon.getItem().equals(
										mod_RpgMageSet.ultimateStaff)) {
							if (p.isBurning()) {
								if (p.getHealth() < 6) {
									p.setHealth(mod_RpgInventory.donators
											.contains(p.username) ? 8 : 6);
								}
								p.extinguish();
							}
						}
						if (weapon.getItem().equals(mod_RpgMageSet.windStaff)
								|| weapon.getItem().equals(
										mod_RpgMageSet.ultimateStaff)) {
							p.fallDistance = 0; // negates fall damage
						}
						if (weapon.getItem().equals(mod_RpgMageSet.frostStaff)
								|| weapon.getItem().equals(
										mod_RpgMageSet.ultimateStaff)) {
							if (p.getAir() < 20) {
								p.setAir(20); // you can not drown !
							}
						}
						if (weapon.getItem().equals(mod_RpgMageSet.earthStaff)
								|| weapon.getItem().equals(
										mod_RpgMageSet.ultimateStaff)) {
							p.curePotionEffects(new ItemStack(Item.bucketMilk,
									1)); // cure all negative potion effects
						}
					}
				}
			}

		} catch (Exception e) {
			FMLLog.getLogger()
					.info("[WARNING] could not execute ArchMage Event Effects ! Please report to mod author.");
		}

	}
}
