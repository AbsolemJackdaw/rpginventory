package addonMasters;

import java.util.List;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.entity.BeastMasterPet;
import addonMasters.entity.EntityPetXP;
import addonMasters.entity.IPet;
import addonMasters.entity.IPet.PetID;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class BeastMasterEvent {

	@SubscribeEvent
	public void joinWorldEvent(EntityJoinWorldEvent e){

		if((e.entity instanceof EntityXPOrb) && !(e.entity instanceof EntityPetXP)){
			EntityXPOrb orb = (EntityXPOrb) e.entity;
			List<BeastMasterPet> pets = orb.worldObj.getEntitiesWithinAABB(BeastMasterPet.class, orb.boundingBox.expand(3d, 3d, 3d));
			if(pets.size() > 0)
			{
				orb.worldObj.spawnEntityInWorld(new EntityPetXP(
						orb.worldObj, orb.posX, orb.posY,
						orb.posZ, orb.getXpValue()));
			}
		}
	}
}
