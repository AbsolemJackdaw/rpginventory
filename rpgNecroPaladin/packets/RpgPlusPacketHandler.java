///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package rpgNecroPaladin.packets;
//
//import ibxm.Player;
//
//import java.io.ByteArrayInputStream;
//import java.io.DataInputStream;
//import java.io.IOException;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//
///**
// * 
// * @author Home
// */
//public class RpgPlusPacketHandler implements IPacketHandler {
//
//	public static class WEAPONIDS {
//
//		public static final int SKULLRCLICK = 5;
//		public static final int NECROSPECIAL = 6;
//		public static final int PALADINSPECIAL = 9;
//	}
//
//	@Override
//	public void onPacketData(INetworkManager manager,
//			Packet250CustomPayload packet, Player player) {
//		//
//		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
//				packet.data));
//		int weaponID = 0;
//		EntityPlayer p = (EntityPlayer) player;
//		try {
//			weaponID = dis.readInt();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		ItemStack weapon = p.getCurrentEquippedItem();
//		PlayerRpgInventory inv = PlayerRpgInventory.get(p);
//
//		if (inv != null) {
//			switch (weaponID) {
//
//			case WEAPONIDS.SKULLRCLICK:
//				new PacketSpawnMinion(weapon, dis, inv, p);
//				break;
//			case WEAPONIDS.NECROSPECIAL:
//				new PacketNecroSpecial(weapon, dis, inv, p);
//				break;
//			case WEAPONIDS.PALADINSPECIAL:
//				new PacketPalaSpecial(player, p, dis, weapon, inv);
//				break;
//			default:
//				break;
//			}
//		}
//	}
// }
