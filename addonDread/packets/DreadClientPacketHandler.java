package addonDread.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class DreadClientPacketHandler extends DreadServerPacketHandler{

	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event){
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
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
