package RpgInventory;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

/**
 * To catch login/Logouts for readingw/writing the Inventory
 * @author AbrarSyed
 */
public class PlayerTracker implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		// read their Inventory data and add it to the 
		AARpgBaseClass.proxy.loadInventory(player.username);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		// write the Inventory and then remove it from Ram.
		AARpgBaseClass.proxy.discardInventory(player.username);
	}

	// noone cares about this....
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}

	
	// idk about you guys.. but idk about this.
	@Override
	public void onPlayerRespawn(EntityPlayer player) {}

}
