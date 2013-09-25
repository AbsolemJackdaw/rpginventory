package rpgNecroPaladin;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class RoguePaladinEvents {


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
						if (weapon.itemID == mod_RpgPlus.pala_weapon.itemID) {
							if (EnumRpgClass.getPlayerClasses((EntityPlayer) damager).contains(EnumRpgClass.PALADIN)) {
								evt.ammount += 3;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			FMLLog.getLogger().info("Couldnt add extra damage to paladin weapon. report to mod author.");
		}
	}
}
