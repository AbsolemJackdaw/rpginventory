package rpgRogueBeast;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.PlayerRpgContainer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.handlers.packets.PacketInventory;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		//Put away pet when gui is opened. This is a nerf and a fix for lag induced duping.

		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
		try{
			if(Minecraft.getMinecraft().currentScreen instanceof RpgGui){
				if (rpgRogueBeast.entity.IPet.playersWithActivePets.containsKey(player.username)) {
					rpgRogueBeast.entity.IPet pet = rpgRogueBeast.entity.IPet.playersWithActivePets.get(player.username).getPet();
					if (pet != null && !((EntityLiving) pet).isDead) {
						inv.setInventorySlotContents(6, pet.writePetToItemStack());
						rpgRogueBeast.entity.IPet.playersWithActivePets.remove(player.username);
						((EntityLiving) pet).setDead();
						PacketInventory.sendPacket(player, inv);
					}
				}
			}

		}catch(Exception e){

		}		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}



}
