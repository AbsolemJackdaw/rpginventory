package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.oldpackets.PacketArcher;
import rpgInventory.handlers.oldpackets.PacketBerserker;
import rpgInventory.handlers.oldpackets.PacketMageHeal;
import rpgInventory.handlers.oldpackets.PacketMageVortex;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class ServerPacketHandler {

	public static final int OPENRPGINV = 1;
	public static final int MAGE1 = 3;
	public static final int BERSERKER = 4;
	public static final int ARCHER = 5;
	public static final int MAGE2 = 7;
	public static final int INVENTORY = 15;
	public static final int SMP_INVENTORY_SYNC = 20;

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		ByteBuf buf = event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		try {

			int guiId = dis.readInt();
			switch (guiId) {
			case OPENRPGINV:
				FMLNetworkHandler.openGui(p, mod_RpgInventory.instance, 1,
						world, x, y, z);
				break;

			case MAGE1:
				new PacketMageHeal(dis, p, world);
				break;
			case BERSERKER:
				new PacketBerserker(world, p, dis);
				break;
			case ARCHER:
				new PacketArcher(dis, p, world);
				break;
			case MAGE2:
				new PacketMageVortex(dis, world, p);
				break;

			case SMP_INVENTORY_SYNC:
				String otherPlayerName = dis.readUTF();
				EntityPlayer other = world
						.getPlayerEntityByName(otherPlayerName);

				for (int i = 0; i < PlayerRpgInventory.get(other)
						.getSizeInventory(); i++) {
					PlayerRpgInventory.get(other).setInventorySlotContents(i,
							ByteBufUtils.readItemStack(buf));
				}
				// PlayerRpgInventory.get(other).setInventorySlotContents(0,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				// PlayerRpgInventory.get(other).setInventorySlotContents(1,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				// PlayerRpgInventory.get(other).setInventorySlotContents(2,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				// PlayerRpgInventory.get(other).setInventorySlotContents(3,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				// PlayerRpgInventory.get(other).setInventorySlotContents(4,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				// PlayerRpgInventory.get(other).setInventorySlotContents(5,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				// PlayerRpgInventory.get(other).setInventorySlotContents(6,
				// ItemStack.loadItemStackFromNBT(CompressedStreamTools.read(dis)));
				break;

			default:
				FMLLog.getLogger().info(
						"[SEVERE]Server : RpgInventory Send Unused packet !! Packet ID "
								+ guiId + ". Please report to mod author.");
				break;
			}
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
