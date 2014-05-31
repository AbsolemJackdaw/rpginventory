package addonMasters;

import java.util.List;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import addonMasters.entity.BMPetImpl;
import addonMasters.entity.EntityPetXP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BeastMasterEvent {

	@SubscribeEvent
	public void someEvent(EntityJoinWorldEvent e){
		if((e.entity instanceof EntityXPOrb) && !(e.entity instanceof EntityPetXP)){
			EntityXPOrb orb = (EntityXPOrb) e.entity;
			List<BMPetImpl> pets = orb.worldObj.getEntitiesWithinAABB(BMPetImpl.class, orb.boundingBox.expand(3d, 3d, 3d));
			if(pets.size() > 0)
			{
				orb.worldObj.spawnEntityInWorld(new EntityPetXP(
						orb.worldObj, orb.posX, orb.posY,
						orb.posZ, orb.getXpValue()));
				//				System.out.println("a pet was detected");
			}
		}
	}
	//	@SubscribeEvent
	//	public void DeathEvent(LivingDeathEvent evt) {
	//
	//		/* ====PET EXP==== */
	//		try {
	//			if ((!evt.entityLiving.worldObj.isRemote)
	//					&& (evt.entityLiving != null) && (evt.source != null)
	//					&& (evt.source.getSourceOfDamage() != null)
	//					&& (evt.source.getSourceOfDamage() instanceof IPet))
	//				if (evt.entityLiving instanceof EntityLiving) {
	//					EntityLiving corpse = (EntityLiving) evt.entityLiving;
	//					EntityLivingBase murderer = (EntityLiving) evt.source.getSourceOfDamage();
	//
	//					float f = 5.0f;
	//					AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
	//							murderer.posX - f, murderer.posY - f, murderer.posZ - f, murderer.posX + f,murderer.posY + f, murderer.posZ + f);
	//
	//					List<EntityXPOrb> a = corpse.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, pool);
	//					int totalXP = 0;
	//
	//					for(EntityXPOrb orb : a){
	//						totalXP += orb.getXpValue();
	//					}
	//					while (totalXP > 0) {
	//						int partialXP = EntityXPOrb.getXPSplit(totalXP);
	//						totalXP -= partialXP;
	//						corpse.worldObj.spawnEntityInWorld(new EntityPetXP(
	//								murderer.worldObj, corpse.posX, corpse.posY,
	//								corpse.posZ, partialXP));
	//					}
	//				}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}
}
