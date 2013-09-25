package rpgNecroPaladin.packets;

import java.io.DataInputStream;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.EnumRpgClass;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgNecroPaladin.mod_RpgPlus;
import rpgNecroPaladin.minions.IMinion;
import rpgNecroPaladin.minions.MinionRegistry;

public class PacketNecroSpecial {

	public PacketNecroSpecial(ItemStack weapon, DataInputStream dis, PlayerRpgInventory inv, EntityPlayer p){
		try {
            dis.close();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
		
		inv.classSets = EnumRpgClass.getPlayerClasses(p);

        if (weapon.getItem().equals(mod_RpgPlus.necro_weapon) && inv.hasClass(EnumRpgClass.NECRO)) {
            if (MinionRegistry.playerMinions.containsKey(p.username)) {
                List<IMinion> list = MinionRegistry.playerMinions.get(p.username);
                for (IMinion minion : list) {
                    minion.Harvest();
                }
            }
        }
	}
}
