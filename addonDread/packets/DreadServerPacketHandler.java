package addonDread.packets;

import addonDread.CommonTickHandlerRpgPlus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
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
		ByteBuf buf = event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		try {

			int weaponID = dis.readInt();

			ItemStack weapon = p.getCurrentEquippedItem();
			PlayerRpgInventory inv = PlayerRpgInventory.get(p);

			if (inv != null) {
				switch (weaponID) {
				case SKULLRCLICK:
					new PacketSpawnMinion(weapon, dis, inv, p);
					break;
				case NECROSPECIAL:
					new PacketNecroSpecial(weapon, dis, inv, p);
					break;
				case PALADINSPECIAL:
					new PacketPalaSpecial( p, dis, weapon, inv);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
		}
	}
}
