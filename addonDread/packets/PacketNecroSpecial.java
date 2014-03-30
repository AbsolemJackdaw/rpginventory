package addonDread.packets;

import io.netty.buffer.ByteBufInputStream;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonDread.mod_RpgPlus;
import addonDread.minions.IMinion;
import addonDread.minions.MinionRegistry;

public class PacketNecroSpecial {

	public PacketNecroSpecial(ItemStack weapon, ByteBufInputStream dis,
			PlayerRpgInventory inv, EntityPlayer p) {
		try {
			dis.close();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}

		System.out.println("paf");
		if (weapon.getItem().equals(mod_RpgPlus.necro_weapon)
				&& mod_RpgInventory.playerClass	.contains(mod_RpgPlus.CLASSNECRO)) {
			if (MinionRegistry.playerMinions.containsKey(p.getDisplayName())) {
				List<IMinion> list = MinionRegistry.playerMinions.get(p
						.getDisplayName());
				for (IMinion minion : list) {
					minion.Harvest();
				}
			}
		}
	}
 }
