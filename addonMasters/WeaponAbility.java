package addonMasters;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;
import addonMasters.packets.PacketTeleport;

public class WeaponAbility implements ISpecialAbility {

	@Override
	public void specialAbility(ItemStack item) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;

		if(RpgUtility.canSpecial(p, RpgMastersAddon.daggers)) {
			RpgMastersAddon.SNW.sendToServer(new PacketTeleport());
		}
	}

}
