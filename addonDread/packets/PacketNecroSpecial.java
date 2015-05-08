package addonDread.packets;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.RpgInventoryMod;
import addonDread.RpgDreadAddon;
import addonDread.minions.IMinion;
import addonDread.minions.MinionRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketNecroSpecial implements IMessage {

	public PacketNecroSpecial() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class HandlerNecroSpecial implements IMessageHandler<PacketNecroSpecial, IMessage>{

		@Override
		public IMessage onMessage(PacketNecroSpecial message, MessageContext ctx) {
			EntityPlayer p = ctx.getServerHandler().playerEntity;
			ItemStack weapon = p.getCurrentEquippedItem();
			if (weapon.getItem().equals(RpgDreadAddon.necroSkull)
					&& RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECRO)) {
				if (MinionRegistry.playerMinions.containsKey(p.getDisplayName())) {
					List<IMinion> list = MinionRegistry.playerMinions.get(p.getDisplayName());
					for (IMinion minion : list) {
						minion.Harvest();
					}
				}
			}
			return null;
		}
	}
}