package addonDread.packets;
//package rpgNecroPaladin.packets;
//
//import java.io.DataInputStream;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import rpgNecroPaladin.CommonTickHandlerRpgPlus;
//import rpgNecroPaladin.mod_RpgPlus;
//import rpgNecroPaladin.minions.EntityMinionS;
//import rpgNecroPaladin.minions.EntityMinionZ;
//
//public class PacketSpawnMinion {
//
//	public PacketSpawnMinion(ItemStack weapon, DataInputStream dis,
//			PlayerRpgInventory inv, EntityPlayer p) {
//		try {
//			dis.close();
//		} catch (Throwable ex) {
//			ex.printStackTrace();
//		}
//
//		if (weapon.getItem().equals(mod_RpgPlus.necro_weapon)
//				&& mod_RpgInventory.playerClass
//						.contains(mod_RpgPlus.CLASSNECRO)) {
//			if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(p
//					.getDisplayName())) {
//				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(
//						p.getDisplayName(), 0);
//			}
//			if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p
//					.getDisplayName()) <= 0) {
//				// 2 second cooldown
//				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p
//						.getDisplayName(), 20 * (mod_RpgInventory.donators
//						.contains(p.getDisplayName()) ? 1 : 2));
//				// System.out.println("SpawnMob");
//				// Allow staff/hammer to perform one last aoe then break the
//				// weapon if its damaged enough.
//				if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
//					// Trigger item break stuff
//					weapon.damageItem(
//							(weapon.getMaxDamage() - weapon.getItemDamage()) + 1,
//							p);
//					// delete the item
//					p.renderBrokenItemStack(weapon);
//					p.setCurrentItemOrArmor(0, (ItemStack) null);
//				} else {
//					weapon.damageItem(2, p);
//				}
//				World world = p.worldObj;
//				if (mod_RpgInventory.playerClass
//						.contains(mod_RpgPlus.CLASSNECROSHIELD)) {
//					if (!world.isRemote) {
//						EntityMinionS var4 = new EntityMinionS(world, p);
//						if (var4 != null) {
//							var4.setPosition(p.posX, p.posY, p.posZ);
//							world.spawnEntityInWorld(var4);
//							var4.setTamed(true);
//							var4.setOwner(p.getDisplayName());
//						}
//					}
//				} else {
//					if (!world.isRemote) {
//						EntityMinionZ var4 = new EntityMinionZ(world, p);
//						if (var4 != null) {
//							var4.setPosition(p.posX, p.posY, p.posZ);
//							world.spawnEntityInWorld(var4);
//							var4.setTamed(true);
//							var4.setOwner(p.getDisplayName());
//						}
//					}
//				}
//			} else {
//				p.addChatMessage("You must wait for energy to replenish, left: "
//						+ Math.floor(1 + (CommonTickHandlerRpgPlus.rpgPluscooldownMap
//								.get(p.getDisplayName()) / 20)) + " seconds");
//			}
//		}
//	}
// }
