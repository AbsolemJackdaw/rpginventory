package rpgInventory.handlers.oldpackets;
//package rpgInventory.handlers.packets;
//
//import ibxm.Player;
//
//import java.io.ByteArrayInputStream;
//import java.io.DataInputStream;
//import java.io.IOException;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.world.World;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import cpw.mods.fml.common.FMLLog;
//import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
//
//public class RpgPacketHandler implements IPacketHandler {
//
//	public static final int OPENRPGINV = 1;
//	public static final int MAGE1 = 3;
//	public static final int BERSERKER = 4;
//	public static final int ARCHER = 5;
//	public static final int MAGE2 = 7;
//	public static final int INVENTORY = 15;
//	public static final int SMP_INVENTORY_SYNC = 20;
//
//	private void handlePackets(Packet250CustomPayload packet, Player player) {
//
//		EntityPlayer p = (EntityPlayer) player;
//		World world = p.worldObj;
//		int x = (int) p.posX;
//		int y = (int) p.posY;
//		int z = (int) p.posZ;
//
//		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
//				packet.data));
//		try {
//
//			int guiId = dis.readInt();
//			switch (guiId) {
//			case OPENRPGINV:
//				FMLNetworkHandler.openGui(p, mod_RpgInventory.instance, 1,
//						world, x, y, z);
//				break;
//
//			case MAGE1:
//				new PacketMageHeal(dis, p, player, world);
//				break;
//			case BERSERKER:
//				new PacketBerserker(world, p, player, dis);
//				break;
//			case ARCHER:
//				new PacketArcher(dis, p, player, world);
//				break;
//			case MAGE2:
//				new PacketMageVortex(dis, world, p, player);
//				break;
//
//			case INVENTORY:
//				PlayerRpgInventory.get(p).setInventorySlotContents(0,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(p).setInventorySlotContents(1,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(p).setInventorySlotContents(2,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(p).setInventorySlotContents(3,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(p).setInventorySlotContents(4,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(p).setInventorySlotContents(5,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(p).setInventorySlotContents(6,
//						packet.readItemStack(dis));
//				break;
//			case SMP_INVENTORY_SYNC:
//				String otherPlayerName = dis.readUTF();
//				EntityPlayer other = world
//						.getPlayerEntityByName(otherPlayerName);
//				PlayerRpgInventory.get(other).setInventorySlotContents(0,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(other).setInventorySlotContents(1,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(other).setInventorySlotContents(2,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(other).setInventorySlotContents(3,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(other).setInventorySlotContents(4,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(other).setInventorySlotContents(5,
//						packet.readItemStack(dis));
//				PlayerRpgInventory.get(other).setInventorySlotContents(6,
//						packet.readItemStack(dis));
//				break;
//
//			default:
//				FMLLog.getLogger().info(
//						"[SEVERE] RpgInventory Send Unused packet !! Packet ID "
//								+ guiId + ". Please report to mod author.");
//				break;
//			}
//			dis.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onPacketData(INetworkManager manager,
//			Packet250CustomPayload packet, Player player) {
//
//		if (packet.channel.equals("RpgInv")) {
//			handlePackets(packet, player);
//		}
//	}
//}
