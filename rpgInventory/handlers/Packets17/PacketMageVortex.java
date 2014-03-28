//package rpgInventory.handlers.Packets17;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.List;
//
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.ChatComponentText;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.Vec3;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.handlers.CommonTickHandler;
//
//public class PacketMageVortex extends AbstractPacket {
//
//	@Override
//	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//	}
//
//	@Override
//	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//	}
//
//	@Override
//	public void handleClientSide(EntityPlayer player) {
//	}
//
//	@Override
//	public void handleServerSide(EntityPlayer player) {
//		if (!player.worldObj.isRemote) {
//			ItemStack wand = player.getCurrentEquippedItem();
//			ItemStack hat = player.inventory.armorItemInSlot(3);
//			ItemStack chest = player.inventory.armorItemInSlot(2);
//			ItemStack legs = player.inventory.armorItemInSlot(1);
//			ItemStack boots = player.inventory.armorItemInSlot(0);
//
//			if (!mod_RpgInventory.developers.contains(player
//					.getCommandSenderName().toLowerCase())) {
//				if ((wand == null) || (hat == null) || (chest == null)
//						|| (legs == null) || (boots == null)) {
//					return;
//				}
//				if ((wand.getItem() != mod_RpgInventory.wand)
//						|| (hat.getItem() != mod_RpgInventory.magehood)
//						|| (chest.getItem() != mod_RpgInventory.magegown)
//						|| (legs.getItem() != mod_RpgInventory.magepants)
//						|| (boots.getItem() != mod_RpgInventory.mageboots)) {
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
//						player.getCommandSenderName(), 7 * 20);
//				if ((wand.getItemDamage() + 3) >= wand.getMaxDamage()) {
//					// Trigger item break stuff
//					// Only damage what is left
//					wand.damageItem(wand.getMaxDamage() - wand.getItemDamage(),
//							player);
//					// Do the break item stuff
//					player.renderBrokenItemStack(wand);
//					// delete the item
//					player.setCurrentItemOrArmor(0, (ItemStack) null);
//				} else {
//					if (!mod_RpgInventory.developers.contains(player
//							.getCommandSenderName().toLowerCase())) {
//						wand.damageItem(mod_RpgInventory.donators
//								.contains(player.getCommandSenderName()) ? 1
//								: 3, player);
//					}
//				}
//				float f = mod_RpgInventory.donators.contains(player
//						.getCommandSenderName()) ? 20.0f : 10.0f;
//				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
//						player.posX - f, player.posY - f, player.posZ - f,
//						player.posX + f, player.posY + f, player.posZ + f);
//				List<EntityLivingBase> entl = player.worldObj
//						.getEntitiesWithinAABBExcludingEntity(player, pool);
//
//				if ((entl != null) && (entl.size() > 0)) {
//					for (Entity el : entl) {
//						if ((el != null) && (el != player)) {
//
//							int var4;
//
//							var4 = 10;
//							if (entl instanceof EntityLivingBase) {
//								var4 += EnchantmentHelper.getKnockbackModifier(
//										player, (EntityLivingBase) el);
//							}
//							if (var4 > 0) {
//								try {
//									Vec3 posPlayer = Vec3.createVectorHelper(
//											el.posX, el.posY, el.posZ);
//									Vec3 posEntity = Vec3.createVectorHelper(
//											player.posX, player.posY,
//											player.posZ);
//									Vec3 posFinal = posPlayer.myVec3LocalPool
//											.getVecFromPool(
//													posEntity.xCoord
//															- posPlayer.xCoord,
//													posEntity.yCoord
//															- posPlayer.yCoord,
//													posEntity.zCoord
//															- posPlayer.zCoord)
//											.normalize();
//									el.setVelocity(posFinal.xCoord * 4,
//											posFinal.yCoord * 4,
//											posFinal.zCoord * 4);
//									el.attackEntityFrom(
//											DamageSource
//													.causePlayerDamage(player),
//											(mod_RpgInventory.donators.contains(player
//													.getCommandSenderName()) ? 3
//													: 1));
//								} catch (Throwable ex) {
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
//}
