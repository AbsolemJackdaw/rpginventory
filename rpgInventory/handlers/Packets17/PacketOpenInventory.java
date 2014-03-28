//package rpgInventory.handlers.Packets17;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import net.minecraft.entity.player.EntityPlayer;
//import rpgInventory.mod_RpgInventory;
//import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
//
//public class PacketOpenInventory extends AbstractPacket {
//
//	public PacketOpenInventory() {
//	}
//
//	// this is the same as readBytes
//	@Override
//	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//		System.out.println("yeah, decoded");
//
//	}
//
//	// this is the same as writeBytes
//	@Override
//	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//		System.out.println("yeah, encoded");
//
//	}
//
//	@Override
//	public void handleClientSide(EntityPlayer player) {
//		System.out.println("yeah, client bitch");
//
//	}
//
//	@Override
//	public void handleServerSide(EntityPlayer player) {
//
//		System.out.println("yeah");
//		FMLNetworkHandler.openGui(player, mod_RpgInventory.instance, 1,
//				player.getEntityWorld(), (int) player.posX, (int) player.posY,
//				(int) player.posZ);
//	}
//}
