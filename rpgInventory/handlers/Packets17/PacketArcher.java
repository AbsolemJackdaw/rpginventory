//package rpgInventory.handlers.Packets17;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.Random;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ChatComponentText;
//import net.minecraft.util.Vec3;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.entity.EntityHellArrow;
//import rpgInventory.handlers.CommonTickHandler;
//
//public class PacketArcher extends AbstractPacket {
//
//	private double xx;
//	private double yy;
//	private double zz;
//	public boolean self;
//
//	public PacketArcher() {
//	}
//
//	public PacketArcher(boolean self, double xx, double yy, double zz) {
//		super();
//		this.self = self;
//		this.xx = xx;
//		this.yy = yy;
//		this.zz = zz;
//	}
//
//	@Override
//	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//		this.self = buffer.readBoolean();
//		this.xx = buffer.readDouble();
//		this.yy = buffer.readDouble();
//		this.zz = buffer.readDouble();
//	}
//
//	@Override
//	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//		buffer.writeBoolean(self);
//		buffer.writeDouble(xx);
//		buffer.writeDouble(yy);
//		buffer.writeDouble(zz);
//	}
//
//	@Override
//	public void handleClientSide(EntityPlayer player) {
//	}
//
//	@Override
//	public void handleServerSide(EntityPlayer player) {
//
//		if (!player.worldObj.isRemote) {
//			boolean self = false;
//			self = this.self;
//
//			if (self == true) {
//				xx = (int) player.posX;
//				yy = (int) player.posY;
//				zz = (int) player.posZ;
//			}
//			// System.out.println(self + " " + xx + " " + yy + " " + zz);
//			ItemStack bow = player.getCurrentEquippedItem();
//			ItemStack top = player.inventory.armorItemInSlot(3);
//			ItemStack middle = player.inventory.armorItemInSlot(2);
//			ItemStack middle2 = player.inventory.armorItemInSlot(1);
//			ItemStack bottom = player.inventory.armorItemInSlot(0);
//			if (!mod_RpgInventory.developers.contains(player
//					.getCommandSenderName().toLowerCase())) {
//				if ((bow == null) || (top == null) || (middle == null)
//						|| (middle2 == null) || (bottom == null)) {
//					return;
//				}
//				if ((bow.getItem() != mod_RpgInventory.elfbow)
//						|| (top.getItem() != mod_RpgInventory.archerhood)
//						|| (middle.getItem() != mod_RpgInventory.archerchest)
//						|| (middle2.getItem() != mod_RpgInventory.archerpants)
//						|| (bottom.getItem() != mod_RpgInventory.archerboots)) {
//					return;
//				}
//			}
//			if (!CommonTickHandler.globalCooldownMap.containsKey(player
//					.getCommandSenderName())) {
//				CommonTickHandler.globalCooldownMap.put(
//						player.getCommandSenderName(), 0);
//			}
//			if (CommonTickHandler.globalCooldownMap.get(player
//					.getCommandSenderName()) <= 0) {
//				CommonTickHandler.globalCooldownMap.put(
//						player.getCommandSenderName(), 30 * 20);
//				if (!mod_RpgInventory.developers.contains(player
//						.getCommandSenderName().toLowerCase())) {
//					bow.damageItem(10, player);
//				}
//				for (int x1 = -10; x1 < 10; x1++) {
//
//					for (int z1 = -10; z1 < 10; z1++) {
//
//						Vec3 posStart = Vec3.createVectorHelper(xx, yy, zz);
//						Vec3 posArrow = posStart.addVector(x1, 0, z1);
//						// System.out.println(posArrow);
//						Double dist = posStart.distanceTo(posArrow);
//						if (dist < 10) {
//							if (self) {
//								if (dist > 2) {
//									if ((dist < (10 + 5.0F))) {
//										EntityHellArrow var8 = new EntityHellArrow(
//												player.worldObj, xx + x1,
//												yy + 100, zz + z1);
//										var8.setIsCritical(true);
//										var8.setDamage(10);
//										var8.setKnockbackStrength(5);
//										var8.setFire(20);
//										var8.canBePickedUp = 2;
//										Random itemRand = new Random();
//										// p.worldObj.playSoundAtEntity(p,
//										// "random.bow", 1.0F, 1.0F /
//										// (itemRand.nextFloat() * 0.4F + 1.2F)
//										// + 100);
//										if (!player.worldObj.isRemote) {
//											player.worldObj
//													.spawnEntityInWorld(var8);
//										}
//									}
//								}
//							} else {
//								if ((dist < (10 + 5.0F))) {
//									EntityHellArrow var8 = new EntityHellArrow(
//											player.worldObj, xx + x1, yy + 100,
//											zz + z1);
//									var8.setIsCritical(true);
//									var8.setDamage(10);
//									var8.setKnockbackStrength(5);
//									var8.setFire(20);
//									var8.canBePickedUp = 2;
//									Random itemRand = new Random();
//									// p.worldObj.playSoundAtEntity(p,
//									// "random.bow", 1.0F, 1.0F /
//									// (itemRand.nextFloat() * 0.4F + 1.2F) +
//									// 100);
//									if (!player.worldObj.isRemote) {
//										player.worldObj
//												.spawnEntityInWorld(var8);
//									}
//								}
//							}
//						}
//					}
//				}
//			} else {
//				player.addChatMessage(new ChatComponentText(
//						"You must wait for energy to replenish, left: "
//								+ Math.floor(1 + (CommonTickHandler.globalCooldownMap
//										.get(player.getCommandSenderName()) / 20))
//								+ " seconds"));
//			}
//		}
//	}
//
//}
