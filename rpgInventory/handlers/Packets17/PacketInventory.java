//package rpgInventory.handlers.Packets17;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import cpw.mods.fml.common.network.ByteBufUtils;
//import cpw.mods.fml.common.network.internal.FMLProxyPacket;
//
//public class PacketInventory extends AbstractPacket {
//
//	public PlayerRpgInventory inv;
//	public EntityPlayer player;
//	public ItemStack[] armorSlots = new ItemStack[7];
//
//	public PacketInventory() {
//		
//	}
//
//	public PacketInventory(EntityPlayer player, PlayerRpgInventory inv) {
//		super();
//		this.player = player;
//		this.inv = inv;
//		
//	}
//
//	// this is the same as readBytes
//	@Override
//	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//
//		// using 7 as a test, not sure if using length will return 0 or 7
//		for (int i = 0; i < 7; i++) {
//			PlayerRpgInventory.get(this.player).setInventorySlotContents(i,
//					ByteBufUtils.readItemStack(buffer));
//			this.armorSlots = PlayerRpgInventory.get(this.player).armorSlots;
//		}
//	}
//
//	// this is the same as writeBytes
//	@Override
//	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//
//		for (ItemStack armorSlot : this.inv.armorSlots) {
//			ByteBufUtils.writeItemStack(buffer, armorSlot);
//		}
//	}
//
//	@Override
//	public void handleClientSide(EntityPlayer player) {
//
//	}
//
//	@Override
//	public void handleServerSide(EntityPlayer player) {
//
//		for (int i = 0; i < this.armorSlots.length; i++) {
//			PlayerRpgInventory.get(player).setInventorySlotContents(i,
//					this.armorSlots[i]);
//		}
//	}
// }
