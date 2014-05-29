package rpgVanillaShields;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VanillaEvents {

	float vanillaReduction = 0f;

	public void damageItem(ItemStack item, PlayerRpgInventory inv,
			EntityPlayer p, int slot, int amount) {
		if (RpgInventoryMod.developers.contains(p.getDisplayName()
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


		/* ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS */
		//			Entity damager = evt.source.getSourceOfDamage();
		//			if (damager != null)
		//				if (damager instanceof EntityPlayer)


		/* DAMAGING AND REDUCING DAMAGE / VANILLA SHIELDS */
		if ((evt.entityLiving != null)
				&& (evt.entityLiving instanceof EntityPlayer) && (evt.source!=DamageSource.starve)) {

			float damageReduction = 0.0F;
			EntityPlayer player = (EntityPlayer) evt.entityLiving;
			PlayerRpgInventory inv = PlayerRpgInventory
					.get(player);

			ItemStack shield = inv.getShield();
			if ((shield != null)
					&& (shield.getItem() instanceof ItemRpgInvShields)) {

				if (RpgInventoryMod.playerClass
						.contains(RpgVanillaShields.WOODENSHIELD))
					vanillaReduction += 0.27f;
				else if (RpgInventoryMod.playerClass
						.contains(RpgVanillaShields.IRONSHIELD))
					vanillaReduction += 0.4f;
				else if (RpgInventoryMod.playerClass
						.contains(RpgVanillaShields.GOLDENSHIELD))
					vanillaReduction += 0.7f;
				else if (RpgInventoryMod.playerClass
						.contains(RpgVanillaShields.DIAMONDSHIELD))
					vanillaReduction += 1.50f;
				vanillaReduction += RpgInventoryMod.donators
						.contains(player.getCommandSenderName()) ? 0.20f
								: 0;
				if (vanillaReduction > 1f) {
					damageReduction = 1f + (vanillaReduction - 1f);
					vanillaReduction = 0;
				}

				evt.ammount -= damageReduction;

				damageItem(shield, inv, player, 1, 1);
			}
		}

	}
}
