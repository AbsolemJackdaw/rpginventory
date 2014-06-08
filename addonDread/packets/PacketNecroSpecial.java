package addonDread.packets;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonDread.RpgDreadAddon;
import addonDread.minions.IMinion;
import addonDread.minions.MinionRegistry;

public class PacketNecroSpecial {

	public PacketNecroSpecial(ItemStack weapon,PlayerRpgInventory inv, EntityPlayer p) {

		System.out.println("paf");
		if (weapon.getItem().equals(RpgDreadAddon.necroSkull)
				&& RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {
			if (MinionRegistry.playerMinions.containsKey(p.getDisplayName())) {
				List<IMinion> list = MinionRegistry.playerMinions.get(p.getDisplayName());
				for (IMinion minion : list) {
					minion.Harvest();
				}
			}
		}
	}
}
