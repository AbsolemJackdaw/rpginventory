package addonMasters.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.PacketHelper;
import addonMasters.entity.IPet;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketStorePet implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class HandlerPacketStorePet implements IMessageHandler<PacketStorePet, IMessage>{

		@Override
		public IMessage onMessage(PacketStorePet message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;
			
			PlayerRpgInventory inv = PlayerRpgInventory.get(p);
			if (IPet.playersWithActivePets.containsKey(p.getDisplayName())) {
				IPet pet = IPet.playersWithActivePets.get(
						p.getDisplayName()).getPet();
				if ((pet != null) && !((EntityLiving) pet).isDead) {
					inv.setInventorySlotContents(6,
							pet.writePetToItemStack());
					IPet.playersWithActivePets.remove(p.getDisplayName());
					((EntityLiving) pet).setDead();
					PacketHelper.syncOwnInventory((EntityPlayerMP) p, inv);
				}
			}
			return null;
		}
	}
}