/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgNecroPaladin.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import rpgInventory.handelers.CommonTickHandler;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.minions.IMinion;
import rpgNecroPaladin.minions.MinionRegistry;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 *
 * @author Home
 */
public class RpgPlusPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		//
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		int weaponID = 0;
		EntityPlayer p = (EntityPlayer) player;
		try {
			weaponID = dis.readInt();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		ItemStack weapon = p.getCurrentEquippedItem();
		RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
		inv.classSets = EnumRpgClass.getPlayerClasses(p);

		if (inv != null) {
			switch (weaponID) {

			case WEAPONIDS.SKULLRCLICK:
				new PacketSpawnMinion(weapon, dis, inv, p);
				break;
			case WEAPONIDS.NECROSPECIAL:
				new PacketNecroSpecial(weapon, dis, inv, p);
				break;

			case WEAPONIDS.PALADINSPECIAL:
				break;
			default:
				break;
			}
		}
	}

	public static class WEAPONIDS {

		public static final int SKULLRCLICK = 5;
		public static final int NECROSPECIAL = 6;
		public static final int PALADINSPECIAL = 9;
	}
}
