//package rpgInventory.handlers.Packets17;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.List;
//
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.ChatComponentText;
//import net.minecraft.util.DamageSource;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.handlers.CommonTickHandler;
//
//public class PacketBerserker extends AbstractPacket {
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
//			// System.out.println("Hammer time!");
//			ItemStack item1 = player.getCurrentEquippedItem();
//			ItemStack var31 = player.inventory.armorItemInSlot(3);
//			ItemStack var21 = player.inventory.armorItemInSlot(2);
//			ItemStack var11 = player.inventory.armorItemInSlot(1);
//			ItemStack var01 = player.inventory.armorItemInSlot(0);
//
//			if (!mod_RpgInventory.developers.contains(player
//					.getCommandSenderName().toLowerCase())) {
//				if ((item1 == null) || (var31 == null) || (var21 == null)
//						|| (var11 == null) || (var01 == null)) {
//					return;
//				}
//				if ((item1.getItem() != mod_RpgInventory.hammer)
//						|| (var31.getItem() != mod_RpgInventory.berserkerHood)
//						|| (var21.getItem() != mod_RpgInventory.berserkerChest)
//						|| (var11.getItem() != mod_RpgInventory.berserkerLegs)
//						|| (var01.getItem() != mod_RpgInventory.berserkerBoots)) {
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
//				CommonTickHandler.globalCooldownMap.put(player
//						.getCommandSenderName(), (mod_RpgInventory.donators
//						.contains(player.getCommandSenderName()) ? 6 : 7) * 20);
//				if ((item1.getItemDamage() + 3) >= item1.getMaxDamage()) {
//					// Trigger item break stuff
//					// Only damage what is left
//					if (!mod_RpgInventory.developers.contains(player
//							.getCommandSenderName().toLowerCase())) {
//						item1.damageItem(
//								item1.getMaxDamage() - item1.getItemDamage(),
//								player);
//					}
//					// Do the break item stuff
//					player.renderBrokenItemStack(item1);
//					// delete the item
//					player.setCurrentItemOrArmor(0, (ItemStack) null);
//				} else {
//					if (!mod_RpgInventory.developers.contains(player
//							.getCommandSenderName().toLowerCase())) {
//						item1.damageItem(3, player);
//					}
//				}
//
//				float range = 4.0f;
//				if (mod_RpgInventory.developers.contains(player
//						.getCommandSenderName().toLowerCase())) {
//					range = 8.0f;
//				} else {
//					range = mod_RpgInventory.donators.contains(player
//							.getCommandSenderName()) ? 5.5f : 4.0f;
//				}
//
//				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
//						player.posX - range, player.posY - range,
//						player.posZ - range, player.posX + range,
//						player.posY + range, player.posZ + range);
//
//				List<EntityLivingBase> entl = player.worldObj
//						.getEntitiesWithinAABB(EntityLivingBase.class, pool);
//				if ((entl != null) && (entl.size() > 0)) {
//					for (EntityLivingBase el : entl) {
//						if ((el != null) && (el != player)) {
//							try {
//								double xdir = el.posX - player.posX;
//								double zdir = el.posZ - player.posZ;
//
//								player.worldObj.spawnParticle("flame", el.posX,
//										el.posY, el.posZ, 0, 1, 0);
//
//								if (mod_RpgInventory.developers.contains(player
//										.getCommandSenderName().toLowerCase())) {
//									el.motionX = xdir * 3F;
//									el.motionY = 3F;
//									el.motionZ = zdir * 3F;
//								} else {
//									el.motionX = xdir
//											* (mod_RpgInventory.donators
//													.contains(player
//															.getCommandSenderName()) ? 2.2f
//													: 1.5F);
//									el.motionY = mod_RpgInventory.donators
//											.contains(player
//													.getCommandSenderName()) ? 2.2f
//											: 3F;
//									el.motionZ = zdir
//											* (mod_RpgInventory.donators
//													.contains(player
//															.getCommandSenderName()) ? 2.2f
//													: 3F);
//								}
//							} catch (Throwable ex) {
//							}
//							el.attackEntityFrom(DamageSource
//									.causePlayerDamage(player),
//									mod_RpgInventory.donators.contains(player
//											.getCommandSenderName()) ? 10 : 8);
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
// }
