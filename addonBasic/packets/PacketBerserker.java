package addonBasic.packets;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.ServerTickHandler;
import addonBasic.RpgBaseAddon;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketBerserker implements IMessage {

	public PacketBerserker() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class HandlerPacketBerserker implements IMessageHandler<PacketBerserker, IMessage>{

		@Override
		public IMessage onMessage(PacketBerserker message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;

			ItemStack item1 = p.getCurrentEquippedItem();
			ItemStack var31 = p.inventory.armorItemInSlot(3);
			ItemStack var21 = p.inventory.armorItemInSlot(2);
			ItemStack var11 = p.inventory.armorItemInSlot(1);
			ItemStack var01 = p.inventory.armorItemInSlot(0);

			if (!RpgInventoryMod.developers.contains(p.getDisplayName()
					.toLowerCase())) {
				if ((item1 == null) || (var31 == null) || (var21 == null)
						|| (var11 == null) || (var01 == null)) {
					return null;
				}
				if ((item1.getItem() != RpgBaseAddon.hammer)
						|| (var31.getItem() != RpgBaseAddon.berserkerHood)
						|| (var21.getItem() != RpgBaseAddon.berserkerChest)
						|| (var11.getItem() != RpgBaseAddon.berserkerLegs)
						|| (var01.getItem() != RpgBaseAddon.berserkerBoots)) {
					return null;
				}
			}
			if (!ServerTickHandler.globalCooldownMap.containsKey(p.getDisplayName())) {
				ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
			}
			if (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
				ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), (7) * 20);
				if ((item1.getItemDamage() + 3) >= item1.getMaxDamage()) {
					// Trigger item break stuff
					// Only damage what is left
					if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
						item1.damageItem(
								item1.getMaxDamage() - item1.getItemDamage(), p);
					}
					// Do the break item stuff
					p.renderBrokenItemStack(item1);
					// delete the item
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
					item1.damageItem(3, p);
				}

				float range = 4.0f;
				if (RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
					range = 8.0f;
				} else {
					range = 4.0f;
				}

				AxisAlignedBB pool = AxisAlignedBB.getBoundingBox(
						p.posX - range, p.posY - range, p.posZ - range,
						p.posX + range, p.posY + range, p.posZ + range);

				List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, pool);
				if ((entl != null) && (entl.size() > 0)) {
					for (EntityLivingBase el : entl) {
						if ((el != null) && (el != p)) {
							try {
								double xdir = el.posX - p.posX;
								double zdir = el.posZ - p.posZ;

								if (RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
									el.motionX = xdir * 3F;
									el.motionY = 3F;
									el.motionZ = zdir * 3F;
								} else {
									el.motionX = xdir* ( 1.5F);
									el.motionY =  3F;
									el.motionZ = zdir* (3F);
								}
							} catch (Throwable ex) {
							}
							el.attackEntityFrom(DamageSource.causePlayerDamage(p),8);
							p.worldObj.playSoundAtEntity(p, "random.explode", 1.0f, 1.0f);

						}
					}
				}
			} else {
				p.addChatMessage(new ChatComponentText(
						"You must wait for energy to replenish, left: "
								+ Math.floor(1 + (ServerTickHandler.globalCooldownMap
										.get(p.getDisplayName()) / 20))
										+ " seconds"));
			}

			return null;
		}
	}
}

//	public PacketBerserker(World world, EntityPlayer p, ByteBufInputStream dis) {
//		
//		ItemStack item1 = p.getCurrentEquippedItem();
//		ItemStack var31 = p.inventory.armorItemInSlot(3);
//		ItemStack var21 = p.inventory.armorItemInSlot(2);
//		ItemStack var11 = p.inventory.armorItemInSlot(1);
//		ItemStack var01 = p.inventory.armorItemInSlot(0);
//
//		if (!RpgInventoryMod.developers.contains(p.getDisplayName()
//				.toLowerCase())) {
//			if ((item1 == null) || (var31 == null) || (var21 == null)
//					|| (var11 == null) || (var01 == null)) {
//				return;
//			}
//			if ((item1.getItem() != RpgBaseAddon.hammer)
//					|| (var31.getItem() != RpgBaseAddon.berserkerHood)
//					|| (var21.getItem() != RpgBaseAddon.berserkerChest)
//					|| (var11.getItem() != RpgBaseAddon.berserkerLegs)
//					|| (var01.getItem() != RpgBaseAddon.berserkerBoots)) {
//				return;
//			}
//		}
//		if (!ServerTickHandler.globalCooldownMap.containsKey(p.getDisplayName())) {
//			ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
//		}
//		if (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
//			ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), (RpgInventoryMod.donators.contains(p.getDisplayName()) ? 6 : 7) * 20);
//			if ((item1.getItemDamage() + 3) >= item1.getMaxDamage()) {
//				// Trigger item break stuff
//				// Only damage what is left
//				if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
//					item1.damageItem(
//							item1.getMaxDamage() - item1.getItemDamage(), p);
//				}
//				// Do the break item stuff
//				p.renderBrokenItemStack(item1);
//				// delete the item
//				p.setCurrentItemOrArmor(0, (ItemStack) null);
//			} else if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
//				item1.damageItem(3, p);
//			}
//
//			float range = 4.0f;
//			if (RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
//				range = 8.0f;
//			} else {
//				range = RpgInventoryMod.donators.contains(p.getDisplayName()) ? 5.5f : 4.0f;
//			}
//
//			AxisAlignedBB pool = AxisAlignedBB.getBoundingBox(
//					p.posX - range, p.posY - range, p.posZ - range,
//					p.posX + range, p.posY + range, p.posZ + range);
//
//			List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, pool);
//			if ((entl != null) && (entl.size() > 0)) {
//				for (EntityLivingBase el : entl) {
//					if ((el != null) && (el != p)) {
//						try {
//							double xdir = el.posX - p.posX;
//							double zdir = el.posZ - p.posZ;
//
//							if (RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
//								el.motionX = xdir * 3F;
//								el.motionY = 3F;
//								el.motionZ = zdir * 3F;
//							} else {
//								el.motionX = xdir* (RpgInventoryMod.donators.contains(p.getDisplayName()) ? 2.2f: 1.5F);
//								el.motionY = RpgInventoryMod.donators.contains(p.getDisplayName()) ? 2.2f: 3F;
//								el.motionZ = zdir* (RpgInventoryMod.donators.contains(p.getDisplayName()) ? 2.2f: 3F);
//							}
//						} catch (Throwable ex) {
//						}
//						el.attackEntityFrom(DamageSource.causePlayerDamage(p),RpgInventoryMod.donators.contains(p.getDisplayName()) ? 10 : 8);
//						p.worldObj.playSoundAtEntity(p, "random.explode", 1.0f, 1.0f);
//
//					}
//				}
//			}
//		} else {
//			p.addChatMessage(new ChatComponentText(
//					"You must wait for energy to replenish, left: "
//							+ Math.floor(1 + (ServerTickHandler.globalCooldownMap
//									.get(p.getDisplayName()) / 20))
//									+ " seconds"));
//		}
//
//	}
//}
