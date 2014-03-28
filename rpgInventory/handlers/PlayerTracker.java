package rpgInventory.handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.oldpackets.PacketInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

/**
 * To catch login/Logouts for reading/writing the Inventory
 * 
 * @author AbrarSyed
 */
public class PlayerTracker /* implements IPlayerTracker */{

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerChangedDimensionEvent e) {
		// TODO
		System.out.println("send packet here dimension");

		if (!e.player.worldObj.isRemote) {
			PacketInventory.sendPacket((EntityPlayerMP)e.player, PlayerRpgInventory.get(e.player));
			//			PacketInventory pack = new PacketInventory();
			//			PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
			//			pipe.sendTo(pack, (EntityPlayerMP) e.player);
			// PacketInventory.sendPacket(e.player,
		}
		PlayerRpgInventory.get(e.player);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e) {
		// TODO
		System.out.println("send packet here login");

		if (!e.player.worldObj.isRemote) {
			//			PacketInventory pack = new PacketInventory();
			//			PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
			//			pipe.sendTo(pack, (EntityPlayerMP) e.player);
			PacketInventory.sendPacket((EntityPlayerMP)e.player, PlayerRpgInventory.get(e.player));

		}
		// PacketInventory.sendPacket(e.player,
		PlayerRpgInventory.get(e.player);
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent e) {
		// write the Inventory and then remove it from Ram.
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent e) {

		// TODO
		System.out.println("send packet here respawn");
		// PacketInventory.sendPacket(e.player,

		if (!e.player.worldObj.isRemote) {
			PacketInventory.sendPacket((EntityPlayerMP)e.player, PlayerRpgInventory.get(e.player));
			//			PacketInventory pack = new PacketInventory();
			//			PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
			//			pipe.sendTo(pack, (EntityPlayerMP) e.player);
		}
		PlayerRpgInventory.get(e.player);
	}
}
