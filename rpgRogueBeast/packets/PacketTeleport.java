//package rpgRogueBeast.packets;
//
//import java.io.DataInputStream;
//import java.util.Random;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.handlers.CommonTickHandler;
//import rpgRogueBeast.mod_RpgRB;
//import rpgRogueBeast.entity.EntityTeleportStone;
//
//public class PacketTeleport {
//
//	public PacketTeleport(World world, EntityPlayer p, DataInputStream dis,
//			Random rand) {
//		if (!world.isRemote) {
//			ItemStack dagger = p.getCurrentEquippedItem();
//
//			if (!mod_RpgInventory.developers.contains(p.getDisplayName()
//					.toLowerCase())) {
//				if (!mod_RpgInventory.playerClass
//						.contains(mod_RpgRB.CLASSROGUE)) {
//					return;
//				}
//			}
//			if (!CommonTickHandler.globalCooldownMap.containsKey(p
//					.getDisplayName())) {
//				CommonTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
//			}
//			if (CommonTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
//				CommonTickHandler.globalCooldownMap
//						.put(p.getDisplayName(), (mod_RpgInventory.donators
//								.contains(p.getDisplayName()) ? 3 : 5) * 20);
//				if ((dagger.getItemDamage() + 3) >= dagger.getMaxDamage()) {
//					dagger.damageItem(
//							dagger.getMaxDamage() - dagger.getItemDamage(), p);
//					p.renderBrokenItemStack(dagger);
//					p.setCurrentItemOrArmor(0, (ItemStack) null);
//				} else {
//					if (!mod_RpgInventory.developers.contains(p
//							.getDisplayName().toLowerCase())) {
//						dagger.damageItem(3, p);
//					}
//				}
//				p.worldObj.spawnEntityInWorld(new EntityTeleportStone(
//						p.worldObj, p));
//				double d0 = rand.nextGaussian() * 0.02D;
//				double d1 = rand.nextGaussian() * 0.02D;
//				double d2 = rand.nextGaussian() * 0.02D;
//				p.worldObj.spawnParticle("largesmoke",
//						(p.posX + (rand.nextFloat() * p.width * 2.0F))
//								- p.width, p.posY + 0.5D
//								+ (rand.nextFloat() * p.height),
//						(p.posZ + (rand.nextFloat() * p.width * 2.0F))
//								- p.width, d0, d1, d2);
//
//			} else {
//				p.addChatMessage("You must wait for energy to replenish, left: "
//						+ Math.floor(1 + (CommonTickHandler.globalCooldownMap
//								.get(p.getDisplayName()) / 20)) + " seconds");
//			}
//		}
//	}
// }
