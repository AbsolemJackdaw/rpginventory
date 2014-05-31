package addonMasters.packets;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.oldpackets.PacketInventory;
import addonMasters.entity.IPet;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class RBServerPacketHandler {


	public static final int PETGUI = 2;
	public static final int CRYSTAL = 11;
	public static final int STOREPET = 7;
	public static final int TELEPORT = 14;


	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();

		World world = p.worldObj;

		try {
			int guiId = dis.readInt();
			switch (guiId) {
			case TELEPORT:
				new PacketTeleport(world, p, dis);
				break;
			case PETGUI:
				new PacketPetGui(dis, p);
				break;
			case CRYSTAL:
				new PacketCrystal(dis, p);
				break;
			case STOREPET:
				PlayerRpgInventory inv = PlayerRpgInventory.get(p);
				if (IPet.playersWithActivePets.containsKey(p.getDisplayName())) {
					IPet pet = IPet.playersWithActivePets.get(
							p.getDisplayName()).getPet();
					if ((pet != null) && !((EntityLiving) pet).isDead) {
						inv.setInventorySlotContents(6,
								pet.writePetToItemStack());
						IPet.playersWithActivePets.remove(p.getDisplayName());
						((EntityLiving) pet).setDead();
						PacketInventory.sendPacket(p, inv);
					}
				}
				break;
			default:
				break;
			}

			dis.close();
		} catch (Exception e) {
			FMLLog.getLogger().info(
					"Failed to process server packet. rpg rb packethandler.");
			e.printStackTrace();		}
	}
}
