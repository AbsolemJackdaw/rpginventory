package rpgRogueBeast;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import rpgRogueBeast.entity.EntityPetXP;
import rpgRogueBeast.entity.IPet;

public class BeastMasterEvent {

	@SubscribeEvent
	public void DeathEvent(LivingDeathEvent evt) {
		/* ====PET EXP==== */
		try {
			if ((!evt.entityLiving.worldObj.isRemote)
					&& (evt.entityLiving != null) && (evt.source != null)
					&& (evt.source.getSourceOfDamage() != null)
					&& (evt.source.getSourceOfDamage() instanceof IPet)) {
				if (evt.entityLiving instanceof EntityLiving) {
					EntityLiving corpse = (EntityLiving) evt.entityLiving;
					EntityLivingBase murderer = (EntityLiving) evt.source
							.getSourceOfDamage();
					int totalXP = corpse.experienceValue;

					while (totalXP > 0) {
						int partialXP = EntityXPOrb.getXPSplit(totalXP);
						totalXP -= partialXP;
						corpse.worldObj.spawnEntityInWorld(new EntityPetXP(
								murderer.worldObj, corpse.posX, corpse.posY,
								corpse.posZ, partialXP));
					}
				}
			}
		} catch (Exception e) {

		}
	}
}
