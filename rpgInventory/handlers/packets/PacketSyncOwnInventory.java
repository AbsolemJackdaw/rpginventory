package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncOwnInventory implements IMessage {

	public ItemStack stack[] = new ItemStack[7];
	public boolean blocking;
	
	public PacketSyncOwnInventory() {
	}

	public PacketSyncOwnInventory(EntityPlayer player) {
		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
		blocking = inv.isBlocking();
		
		for(int i = 0; i < 7; i ++)
			stack[i] = inv.getJewelFromStack(i);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		blocking = buf .readBoolean();
		for (int i = 0; i < 7; i++){
			stack[i] = ByteBufUtils.readItemStack(buf);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(blocking);
		for (int i = 0; i < 7; i++) {
			ByteBufUtils.writeItemStack(buf, stack[i]);
		}
	}

	public static class HandlerSyncOwnInventory implements IMessageHandler<PacketSyncOwnInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOwnInventory message,MessageContext ctx) {
			PlayerRpgInventory rpg = PlayerRpgInventory.get(RpgInventoryMod.proxy.getClientPlayer());

			for (int i = 0; i < 7; i++){
				rpg.setInventorySlotContents(i,message.stack[i]);
			}
			return null;
		}
	}
}
