package addonBasic.packets;

import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.ServerTickHandler;
import addonBasic.EntityHellArrow;
import addonBasic.RpgBaseAddon;

public class PacketArcher {

	private double xx;
	private double yy;
	private double zz;

	// TODO move packet

	/*
	 * MOVE PACKET TO API
	 */

	/** Move packet to API */
	public PacketArcher(ByteBufInputStream dis, EntityPlayer p, World world) {
		if (!world.isRemote) {
			boolean self = false;
			try {
				self = dis.readBoolean();

				if (self == true) {
					xx = (int) p.posX;
					yy = (int) p.posY;
					zz = (int) p.posZ;
				} else {
					xx = dis.readInt();
					yy = dis.readInt();
					zz = dis.readInt();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			ItemStack bow = p.getCurrentEquippedItem();
			ItemStack top = p.inventory.armorItemInSlot(3);
			ItemStack middle = p.inventory.armorItemInSlot(2);
			ItemStack middle2 = p.inventory.armorItemInSlot(1);
			ItemStack bottom = p.inventory.armorItemInSlot(0);
			if (!RpgInventoryMod.developers.contains(p.getDisplayName()
					.toLowerCase())) {
				if ((bow == null) || (top == null) || (middle == null)
						|| (middle2 == null) || (bottom == null)) {
					return;
				}
				if ((bow.getItem() != RpgBaseAddon.elfbow)
						|| (top.getItem() != RpgBaseAddon.archerhood)
						|| (middle.getItem() != RpgBaseAddon.archerchest)
						|| (middle2.getItem() != RpgBaseAddon.archerpants)
						|| (bottom.getItem() != RpgBaseAddon.archerboots)) {
					return;
				}
			}
			if (!ServerTickHandler.globalCooldownMap.containsKey(p
					.getDisplayName())) {
				ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
			}
			if (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
				ServerTickHandler.globalCooldownMap.put(p.getDisplayName(),
						30 * 20);
				if (!RpgInventoryMod.developers.contains(p.getDisplayName()
						.toLowerCase())) {
					bow.damageItem(10, p);
				}
				for (int x1 = -10; x1 < 10; x1++) {
					for (int z1 = -10; z1 < 10; z1++) {

						Vec3 posStart = Vec3.createVectorHelper(xx, yy, zz);
						Vec3 posArrow = posStart.addVector(x1, 0, z1);
						Double dist = posStart.distanceTo(posArrow);
						if (dist < 10) {
							if (self) {
								if (dist > 2) {
									if ((dist < (10 + 5.0F))) {
										EntityHellArrow var8 = new EntityHellArrow(
												p.worldObj, xx + x1, yy + 100,
												zz + z1);
										var8.setIsCritical(true);
										var8.setDamage(10);
										var8.setKnockbackStrength(5);
										var8.setFire(20);
										var8.canBePickedUp = 2;
										new Random();
										// p.worldObj.playSoundAtEntity(p,
										// "random.bow", 1.0F, 1.0F /
										// (itemRand.nextFloat() * 0.4F + 1.2F)
										// + 100);
										if (!p.worldObj.isRemote) {
											p.worldObj.spawnEntityInWorld(var8);
										}
									}
								}
							} else if ((dist < (10 + 5.0F))) {
								EntityHellArrow var8 = new EntityHellArrow(
										p.worldObj, xx + x1, yy + 100, zz + z1);
								var8.setIsCritical(true);
								var8.setDamage(10);
								var8.setKnockbackStrength(5);
								var8.setFire(20);
								var8.canBePickedUp = 2;
								new Random();
								// p.worldObj.playSoundAtEntity(p,
								// "random.bow", 1.0F, 1.0F /
								// (itemRand.nextFloat() * 0.4F + 1.2F) +
								// 100);
								if (!p.worldObj.isRemote) {
									p.worldObj.spawnEntityInWorld(var8);
								}
							}
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
		}
	}
}
