package addonDread.packets17;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import addonDread.mod_RpgPlus;
import addonDread.minions.IMinion;
import addonDread.minions.MinionRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketNecroSpecial extends RpgPlusAbstractPacket {

	private EntityPlayer player;
	private ItemStack weapon;

	public PacketNecroSpecial() {
	}

	public PacketNecroSpecial(EntityPlayer player) {
		super();
		this.player = player;
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.weapon = ByteBufUtils.readItemStack(buffer);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		ByteBufUtils.writeItemStack(buffer,
				this.player.getCurrentEquippedItem());
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		if (weapon.getItem().equals(mod_RpgPlus.necro_weapon)
				&& mod_RpgInventory.playerClass
						.contains(mod_RpgPlus.CLASSNECRO)) {
			if (MinionRegistry.playerMinions.containsKey(player
					.getDisplayName())) {
				List<IMinion> list = MinionRegistry.playerMinions.get(player
						.getDisplayName());
				for (IMinion minion : list) {
					minion.Harvest();
				}
			}
		}
	}
}
