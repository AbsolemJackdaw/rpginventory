package rpgVanillaShields;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class VanillaEvents {


	float vanillaReduction = 0f;

	@ForgeSubscribe
	public void PlayerDamage(LivingHurtEvent evt) {

		try {
			/*ADDING EXTRA DAMAGE TO CLASS ARMOR COMBINATIONS*/
			Entity damager = evt.source.getSourceOfDamage();
			if (damager != null) {
				if (damager instanceof EntityPlayer) {
					try {
						/*DAMAGING AND REDUCING DAMAGE / VANILLA SHIELDS*/
						if (evt.entityLiving != null && evt.entityLiving instanceof EntityPlayer) {
							float damageReduction = 0.0F;
							EntityPlayer player = (EntityPlayer) evt.entityLiving;
							PlayerRpgInventory inv = PlayerRpgInventory.get(player);

							ItemStack shield = inv.getShield();
							if (shield != null && shield.getItem() instanceof ItemRpgInvShields) {

								if (mod_RpgInventory.playerClass.contains(mod_VanillaShields.WOODENSHIELD)) {
									vanillaReduction += 0.27f;
								} else if (mod_RpgInventory.playerClass.contains(mod_VanillaShields.IRONSHIELD)) {
									vanillaReduction += 0.4f;
								} else if (mod_RpgInventory.playerClass.contains(mod_VanillaShields.GOLDENSHIELD)) {
									vanillaReduction += 0.7f;
								} else if (mod_RpgInventory.playerClass.contains(mod_VanillaShields.DIAMONDSHIELD)) {
									vanillaReduction += 1.50f;
								}
								vanillaReduction += mod_RpgInventory.donators.contains(player.username) ? 0.20f : 0;
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
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void damageItem(ItemStack item, PlayerRpgInventory inv, EntityPlayer p, int slot, int amount) {
		if (mod_RpgInventory.developers.contains(p.username.toLowerCase())){
			return;
		}
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
}
