package addonMasters;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import addonMasters.entity.EntityPetXP;
import addonMasters.entity.IPet;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BeastMasterEvent {

	@SubscribeEvent
	public void DeathEvent(LivingDeathEvent evt) {
		// System.out.println("events");

		/* ====PET EXP==== */
		try {
			if ((!evt.entityLiving.worldObj.isRemote)
					&& (evt.entityLiving != null) && (evt.source != null)
					&& (evt.source.getSourceOfDamage() != null)
					&& (evt.source.getSourceOfDamage() instanceof IPet))
				if (evt.entityLiving instanceof EntityLiving) {
					EntityLiving corpse = (EntityLiving) evt.entityLiving;
					EntityLivingBase murderer = (EntityLiving) evt.source
							.getSourceOfDamage();

					float f = 2.0f;
					AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
							murderer.posX - f, murderer.posY - f, murderer.posZ - f, murderer.posX + f,murderer.posY + f, murderer.posZ + f);

					List<EntityXPOrb> a = corpse.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, pool);
					
					int totalXP = 0;

					for(EntityXPOrb orb : a){
						totalXP += orb.getXpValue();
					}
					System.out.println(totalXP);
					while (totalXP > 0) {
						int partialXP = EntityXPOrb.getXPSplit(totalXP);
						totalXP -= partialXP;
						corpse.worldObj.spawnEntityInWorld(new EntityPetXP(
								murderer.worldObj, corpse.posX, corpse.posY,
								corpse.posZ, partialXP));
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
