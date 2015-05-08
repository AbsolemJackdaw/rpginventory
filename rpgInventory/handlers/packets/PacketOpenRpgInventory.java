package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenRpgInventory implements IMessage{

	public PacketOpenRpgInventory() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

	public static class HandlerOpenRpgInventory implements IMessageHandler<PacketOpenRpgInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketOpenRpgInventory message, MessageContext ctx) {
			EntityPlayerMP p = ctx.getServerHandler().playerEntity;
			World world = p.worldObj;
			
			FMLNetworkHandler.openGui(p, RpgInventoryMod.instance, 1, world, (int)p.posX, (int)p.posY, (int)p.posZ);
			
			return null;
		}
	}
}
