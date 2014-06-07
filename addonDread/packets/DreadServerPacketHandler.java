package addonDread.packets;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class DreadServerPacketHandler {

	public static final int SKULLRCLICK = 5;
	public static final int NECROSPECIAL = 6;
	public static final int PALADINSPECIAL = 9;

	@SubscribeEvent
	public void onServertPacket(ServerCustomPacketEvent event){

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		event.packet.payload();

		try {

			int weaponID = dis.readInt();

			ItemStack weapon = p.getCurrentEquippedItem();
			PlayerRpgInventory inv = PlayerRpgInventory.get(p);

			if (inv != null) {
				switch (weaponID) {
				case SKULLRCLICK:
					new PacketSpawnMinion(weapon, inv, p);
					break;
				case NECROSPECIAL:
					new PacketNecroSpecial(weapon, inv, p);
					break;
				case PALADINSPECIAL:
					new PacketPalaSpecial( p, weapon, inv);
					break;
				default:
					break;
				}
			}
			
			dis.close();
		} catch (Exception e) {
		}
	}
}
