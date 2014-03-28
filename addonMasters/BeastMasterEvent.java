package addonMasters;

import java.lang.reflect.Field;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import addonMasters.entity.EntityPetXP;
import addonMasters.entity.IPet;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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

					Field f = corpse.getClass().getField("experienceValue");
					f.setAccessible(true);
					int totalXP = f.getInt(corpse);

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
			e.printStackTrace();
		}
	}
}
