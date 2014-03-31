package addonMasters;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import addonBasic.RpgBaseAddon;
import addonBasic.packets.ClientPacketHandler;
import addonMasters.packets.RBServerPacketHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;

public class WeaponAbility implements ISpecialAbility {

	@Override
	public void specialAbility(ItemStack item) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;

		if(RpgUtility.canSpecial(p, RpgMastersAddon.daggers)){
			try {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(RBServerPacketHandler.TELEPORT);
				RpgMastersAddon.Channel.sendToServer(new FMLProxyPacket(buf,"R_BChannel"));
				out.close();
			} catch (Exception e) {
			}
		}
	}

}
