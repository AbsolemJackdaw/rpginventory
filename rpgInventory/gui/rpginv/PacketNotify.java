package rpgInventory.gui.rpginv;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketNotify implements IMessage {

	public static String message;

	public PacketNotify() {
	}
	
	public PacketNotify(String message) {
		this.message = message;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		message = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, message);
	}
	
	public static class HandlerPacketNotify implements IMessageHandler<PacketNotify, IMessage>{

		@Override
		public IMessage onMessage(PacketNotify message, MessageContext ctx) {
			
			EntityPlayer p = ctx.getServerHandler().playerEntity;
			
			if(!p.worldObj.isRemote){
				p.addChatComponentMessage(new ChatComponentText(PacketNotify.message));
			}
			
			return null;
		}
		
	}

}
