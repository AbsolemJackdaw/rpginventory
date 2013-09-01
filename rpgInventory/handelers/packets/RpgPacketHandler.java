package rpgInventory.handelers.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class RpgPacketHandler implements IPacketHandler {


	public static final int OPENRPGINV = 1;
	public static final int PETGUI = 2;
	public static final int MAGE1 = 3;
	public static final int BERSERKER = 4;
	public static final int ARCHER = 5;
	public static final int MAGE2 = 7;
	public static final int CRYSTAL = 11;

	private Random rand = new Random(5);

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

		if (packet.channel.equals("RpgInv")) {
			handleRandom(packet, player);
		} else if (packet.channel.equals("RpgRawInv")) {
			handleRawInventory(packet, (EntityPlayer) player);
		}
	}

	private void handleRawInventory(Packet250CustomPayload packet, EntityPlayer p) {
		try {
			NBTTagCompound nbt = CompressedStreamTools.decompress(packet.data);
			RpgInv inv = new RpgInv(nbt.getString("username"));
			NBTTagList list = nbt.getTagList("items");
			
//			FMLLog.getLogger().info(""+nbt.getString("username"));

			for (int i = 0; i < inv.armorSlots.length; i++) {
				NBTTagCompound tc = (NBTTagCompound) list.tagAt(i);
				if (tc != null) {
					inv.armorSlots[i] = ItemStack.loadItemStackFromNBT(tc);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleRandom(Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		//System.out.println("Packet was send and recieved.");
		try {
			int guiId = dis.readInt();
			switch (guiId) {
			case OPENRPGINV:
				if (!world.isRemote) {
					FMLNetworkHandler.openGui(p, mod_RpgInventory.instance, 1, world, x, y, z);
				}
				break;
			case PETGUI:
				new PacketPetGui(dis, p);
				break;
			case MAGE1:
				new PacketMageHeal(dis, p, player, world);
				break;
			case BERSERKER:
				new PacketBerserker(world, p, player, dis);
				break;
			case ARCHER:
				new PacketArcher(dis, p, player, world);
				break;
			case MAGE2:
				new PacketMageVortex(dis, world, p, player);
				break;
			case 8: // i think this is the book gui
				if (!world.isRemote) {
					FMLNetworkHandler.openGui(p, mod_RpgInventory.instance, 2, world, x, y, z);
				}
				break;
				//case 9 used for Paladin
			case 10:
				FMLLog.getLogger().info("[SEVERE] RpgInventory Send Unused packet !! 10");
				break;
			case CRYSTAL:
				new PacketCrystal(dis, p);
				break;
			case 12:
				FMLLog.getLogger().info("[SEVERE] RpgInventory Send Unused packet !! 12");
				break;
			case 13:
				//mod_RpgInventory.proxy.candy(p);
				FMLLog.getLogger().info("[SEVERE] RpgInventory Send Unused packet !! 13");
				break;
			default:
				FMLLog.getLogger().info("[SEVERE] RpgInventory Send Unused packet !! UnDesignated");
				break;
			}
			dis.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
	}
}
