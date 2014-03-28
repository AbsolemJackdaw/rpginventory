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
//import net.minecraft.potion.Potion;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.ChatComponentText;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.handlers.CommonTickHandler;
//
//public class PacketMageHeal extends AbstractPacket {
//
//	public PacketMageHeal() {
//	}
//
//	@Override
//	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//
//	}
//
//	@Override
//	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//
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
//		if (!player.worldObj.isRemote) {
//			ItemStack item = player.getCurrentEquippedItem();
//			ItemStack var3 = player.inventory.armorItemInSlot(3);
//			ItemStack var2 = player.inventory.armorItemInSlot(2);
//			ItemStack var1 = player.inventory.armorItemInSlot(1);
//			ItemStack var0 = player.inventory.armorItemInSlot(0);
//			if (!mod_RpgInventory.developers.contains(player
//					.getCommandSenderName().toLowerCase())) {
//				if ((item == null) || (var3 == null) || (var2 == null)
//						|| (var1 == null) || (var0 == null)) {
//					return;
//				}
//				if ((item.getItem() != mod_RpgInventory.staf)
//						|| (var3.getItem() != mod_RpgInventory.magehood)
//						|| (var2.getItem() != mod_RpgInventory.magegown)
//						|| (var1.getItem() != mod_RpgInventory.magepants)
//						|| (var0.getItem() != mod_RpgInventory.mageboots)) {
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
//						player.getCommandSenderName(), 5 * 20);
//				// System.out.println("Healing time!");
//				// Allow staff/hammer to perform one last aoe then break the
//				// weapon if its damaged enough.
//				if ((item.getItemDamage() + 3) >= item.getMaxDamage()) {
//					// Trigger item break stuff
//					item.damageItem(
//							(item.getMaxDamage() - item.getItemDamage()) + 1,
//							player);
//					// delete the item
//					player.renderBrokenItemStack(item);
//					player.setCurrentItemOrArmor(0, (ItemStack) null);
//				} else {
//					if (!mod_RpgInventory.developers.contains(player
//							.getCommandSenderName().toLowerCase())) {
//						item.damageItem(3, player);
//					}
//				}
//				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
//						player.posX - 4.0F, player.posY - 4.0F,
//						player.posZ - 4.0F, player.posX + 4.0F,
//						player.posY + 4.0F, player.posZ + 4.0F);
//				List<EntityLivingBase> entl = player.worldObj
//						.getEntitiesWithinAABB(EntityLivingBase.class, pool);
//				if ((entl != null) && (entl.size() > 0)) {
//					for (EntityLivingBase el : entl) {
//						if (el != null) {
//							double dist = player.getDistanceSqToEntity(el);
//							double potstrength = 1.0D - (Math.sqrt(dist) / (mod_RpgInventory.donators
//									.contains(player.getCommandSenderName()) ? 6.0D
//									: 4.0D));
//							Potion.heal.affectEntity(player, el,
//									(mod_RpgInventory.donators.contains(player
//											.getCommandSenderName()) ? 4 : 2),
//									potstrength);
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
