package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncBlockShield implements IMessage{

	public PacketSyncBlockShield() {
	}
	
	public boolean b;
	
	public PacketSyncBlockShield(boolean b) {
		this.b = b;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		b = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(b);

	}
	
	public static class HandlerSyncBlockShield implements IMessageHandler<PacketSyncBlockShield, IMessage>{
		@Override
		public IMessage onMessage(PacketSyncBlockShield message,MessageContext ctx) {
			EntityPlayer p = ctx.getServerHandler().playerEntity;
			
			PlayerRpgInventory.get(p).setBlocking(message.b);
			
			return null;
		}
	}
}
