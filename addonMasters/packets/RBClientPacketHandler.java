package addonMasters.packets;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;


public class RBClientPacketHandler extends RBServerPacketHandler {


	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();

		World world = p.worldObj;

		try {
			int id = dis.readInt();
			switch (id) {
			case 14:
				new PacketTeleport(world, p, dis);
				break;
			case PETGUI:
				new PacketPetGui(dis, p);
				break;
			case CRYSTAL:
				new PacketCrystal(dis, p);
				break;

			case STOREPET:
				//server side
				//				PlayerRpgInventory inv = PlayerRpgInventory.get(p);
				//				if (IPet.playersWithActivePets.containsKey(p.getDisplayName())) {
				//					IPet pet = IPet.playersWithActivePets.get(
				//							p.getDisplayName()).getPet();
				//					if ((pet != null) && !((EntityLiving) pet).isDead) {
				//						inv.setInventorySlotContents(6,
				//								pet.writePetToItemStack());
				//						IPet.playersWithActivePets.remove(p.getDisplayName());
				//						((EntityLiving) pet).setDead();
				//						PacketInventory.sendPacket(p, inv);
				//					}
				//				}
				break;
			default:
				break;
			}

			dis.close();
		} catch (Exception e) {
			FMLLog.getLogger().info(
					"Failed to process packet. rpg rb packethandler.");
			e.printStackTrace();		}
	}

}
