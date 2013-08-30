package rpgInventory.handelers.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.entity.EntityHellArrow;
import rpgInventory.handelers.CommonTickHandler;
import cpw.mods.fml.common.network.Player;

public class PacketArcher {

	private double xx;
	private double yy;
	private double zz;
	
	public PacketArcher(DataInputStream dis, EntityPlayer p, Player player, World world){
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//                        System.out.println(self + " " + xx + " " + yy + " " + zz);
			ItemStack bow = p.getCurrentEquippedItem();
			ItemStack top = p.inventory.armorItemInSlot(3);
			ItemStack middle = p.inventory.armorItemInSlot(2);
			ItemStack middle2 = p.inventory.armorItemInSlot(1);
			ItemStack bottom = p.inventory.armorItemInSlot(0);
			if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
				if (bow == null || top == null || middle == null || middle2 == null || bottom == null) {
					return;
				}
				if (bow.getItem() != mod_RpgInventory.elfbow || top.getItem() != mod_RpgInventory.archerhood || middle.getItem() != mod_RpgInventory.archerchest || middle2.getItem() != mod_RpgInventory.archerpants || bottom.getItem() != mod_RpgInventory.archerboots) {
					return;
				}
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
				CommonTickHandler.globalCooldownMap.put(p.username, 0);
			}
			if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
				CommonTickHandler.globalCooldownMap.put(p.username, 30 * 20);
				if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
					bow.damageItem(10, p);
				}
				for (int x1 = (int) -10; x1 < 10; x1++) {

					for (int z1 = (int) -10; z1 < 10; z1++) {

						Vec3 posStart = Vec3.createVectorHelper(xx, yy, zz);
						Vec3 posArrow = posStart.addVector(x1, 0, z1);
						//System.out.println(posArrow);
						Double dist = posStart.distanceTo(posArrow);
						if (dist < 10) {
							if (self) {
								if (dist > 2) {
									if ((dist < 10 + 5.0F)) {
										EntityHellArrow var8 = new EntityHellArrow(p.worldObj, xx + x1, yy + 100, zz + z1);
										var8.setIsCritical(true);
										var8.setDamage(10);
										var8.setKnockbackStrength(5);
										var8.setFire(20);
										var8.canBePickedUp = 2;
										Random itemRand = new Random();
										//p.worldObj.playSoundAtEntity(p, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 100);
										if (!p.worldObj.isRemote) {
											p.worldObj.spawnEntityInWorld(var8);
										}
									}
								}
							} else {
								if ((dist < 10 + 5.0F)) {
									EntityHellArrow var8 = new EntityHellArrow(p.worldObj, xx + x1, yy + 100, zz + z1);
									var8.setIsCritical(true);
									var8.setDamage(10);
									var8.setKnockbackStrength(5);
									var8.setFire(20);
									var8.canBePickedUp = 2;
									Random itemRand = new Random();
									//p.worldObj.playSoundAtEntity(p, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 100);
									if (!p.worldObj.isRemote) {
										p.worldObj.spawnEntityInWorld(var8);
									}
								}
							}
						}
					}
				}
			} else {
				p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
			}
		}
	}
}
