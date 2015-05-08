package addonDread;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.handlers.ServerTickHandler;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;
import addonDread.packets.PacketNecroSpecial;
import addonDread.packets.PacketPalaSpecial;

public class SpecialAbility implements ISpecialAbility {


	@Override
	public void specialAbility(ItemStack item) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;

		if(item.getItem().equals(RpgDreadAddon.necroSkull)) 
			RpgDreadAddon.SNW.sendToServer(new PacketNecroSpecial());

		if(item.getItem().equals(RpgDreadAddon.paladinSword)) {
			RpgDreadAddon.SNW.sendToServer(new PacketPalaSpecial());

			if(ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) / 20 <= 0){
				RpgUtility.spawnParticle(p, 9, "crit", 100, 0);
			}
		}

	}
}
