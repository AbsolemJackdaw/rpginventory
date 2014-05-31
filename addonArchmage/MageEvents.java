package addonArchmage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import rpgInventory.RpgInventoryMod;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MageEvents {

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				ItemStack weapon = p.getCurrentEquippedItem();
				if (weapon != null) {
					/* ==== ARCHMAGE EFFECTS ==== */
					if (RpgInventoryMod.playerClass
							.contains(RpgArchmageAddon.CLASSARCHMAGESHIELD)
							|| RpgInventoryMod.developers.contains(p
									.getDisplayName().toLowerCase())) {
						if (weapon.getItem().equals(RpgArchmageAddon.fireStaff)
								|| weapon.getItem().equals(
										RpgArchmageAddon.ultimateStaff)) {
							if (p.isBurning()) {
								if (p.getHealth() < 6) {
									p.setHealth(RpgInventoryMod.donators
											.contains(p.getDisplayName()) ? 8
													: 6);
								}
								p.extinguish();
							}
						}
						if (weapon.getItem().equals(RpgArchmageAddon.windStaff)
								|| weapon.getItem().equals(
										RpgArchmageAddon.ultimateStaff))
						{
							p.fallDistance = 0; // negates fall damage
						}
						if (weapon.getItem().equals(RpgArchmageAddon.frostStaff)
								|| weapon.getItem().equals(
										RpgArchmageAddon.ultimateStaff))
						{
							if (p.getAir() < 20)
							{
								p.setAir(20); // you can not drown !
							}
						}
						if (weapon.getItem().equals(RpgArchmageAddon.earthStaff)
								|| weapon.getItem().equals(
										RpgArchmageAddon.ultimateStaff))
						{
							p.curePotionEffects(new ItemStack(
									Items.milk_bucket, 1)); // cure all negative
							// potion effects
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
