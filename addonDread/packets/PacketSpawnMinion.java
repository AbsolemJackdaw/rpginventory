package addonDread.packets;


import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonDread.CommonTickHandlerRpgPlus;
import addonDread.RpgDreadAddon;
import addonDread.minions.EntityMinionS;
import addonDread.minions.EntityMinionZ;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnMinion implements IMessage{

	public PacketSpawnMinion() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class HandlerPacketSpawnMinion implements IMessageHandler<PacketSpawnMinion, IMessage>{

		@Override
		public IMessage onMessage(PacketSpawnMinion message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;
			PlayerRpgInventory inv = PlayerRpgInventory.get(p);
			ItemStack weapon = p.getCurrentEquippedItem();

			if (weapon.getItem().equals(RpgDreadAddon.necroSkull)) {
				if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(p.getDisplayName())) {
					CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(
							p.getDisplayName(), 0);
				}

				if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p.getDisplayName()) <= 0) {
					// 2 second cooldown
					CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p.getDisplayName(), 20 * (2));

					// Allow staff/hammer to perform one last aoe then break the
					// weapon if its damaged enough.
					if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
						// Trigger item break stuff
						weapon.damageItem((weapon.getMaxDamage() - weapon.getItemDamage()) + 1,p);
						// delete the item
						p.renderBrokenItemStack(weapon);
						p.setCurrentItemOrArmor(0, (ItemStack) null);
					} else {
						weapon.damageItem(2, p);
					}
					World world = p.worldObj;
					int randomSkeleton = 20;

					if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECROSHIELD)) {
						randomSkeleton = 7;
					}

					if(new Random().nextInt(randomSkeleton) == 0){
						EntityMinionS var4 = new EntityMinionS(world, p);
						if (var4 != null) {
							var4.setPosition(p.posX, p.posY, p.posZ);
							var4.setTamed(true);
							var4.func_152115_b(p.getDisplayName());
							if (!world.isRemote)
								world.spawnEntityInWorld(var4);
						}
					}
					else{
						EntityMinionZ var4 = new EntityMinionZ(world, p);
						if (var4 != null) {
							var4.setPosition(p.posX, p.posY, p.posZ);
							var4.setTamed(true);
							var4.func_152115_b(p.getDisplayName());
							if (!world.isRemote)
								world.spawnEntityInWorld(var4);
						}
					}
				} else {
					p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
							+ Math.floor(1 + (CommonTickHandlerRpgPlus.rpgPluscooldownMap
									.get(p.getDisplayName()) / 20)) + " seconds"));
				}

			}
			return null;
		}
	}
}



//public PacketSpawnMinion(ItemStack weapon, PlayerRpgInventory inv, EntityPlayer p) {
//
//	System.out.println(CommonTickHandlerRpgPlus.rpgPluscooldownMap + " " +RpgInventoryMod.playerClass);
//
//	if (weapon.getItem().equals(RpgDreadAddon.necroSkull)) {
//		if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(p.getDisplayName())) {
//			CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(
//					p.getDisplayName(), 0);
//		}
//
//		if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p.getDisplayName()) <= 0) {
//			// 2 second cooldown
//			CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p.getDisplayName(), 20 * (RpgInventoryMod.donators
//					.contains(p.getDisplayName()) ? 1 : 2));
//
//			// Allow staff/hammer to perform one last aoe then break the
//			// weapon if its damaged enough.
//			if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
//				// Trigger item break stuff
//				weapon.damageItem(
//						(weapon.getMaxDamage() - weapon.getItemDamage()) + 1,
//						p);
//				// delete the item
//				p.renderBrokenItemStack(weapon);
//				p.setCurrentItemOrArmor(0, (ItemStack) null);
//			} else {
//				weapon.damageItem(2, p);
//			}
//			World world = p.worldObj;
//			int randomSkeleton = 20;
//
//			if (RpgInventoryMod.playerClass.contains(RpgDreadAddon.CLASSNECROSHIELD)) {
//				randomSkeleton = 7;
//			}
//
//			if(new Random().nextInt(randomSkeleton) == 0){
//				EntityMinionS var4 = new EntityMinionS(world, p);
//				if (var4 != null) {
//					var4.setPosition(p.posX, p.posY, p.posZ);
//					var4.setTamed(true);
//					var4.func_152115_b(p.getDisplayName());
//					if (!world.isRemote)
//						world.spawnEntityInWorld(var4);
//				}
//			}
//			else{
//				EntityMinionZ var4 = new EntityMinionZ(world, p);
//				if (var4 != null) {
//					var4.setPosition(p.posX, p.posY, p.posZ);
//					var4.setTamed(true);
//					var4.func_152115_b(p.getDisplayName());
//					if (!world.isRemote)
//						world.spawnEntityInWorld(var4);
//				}
//			}
//		} else {
//			p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
//					+ Math.floor(1 + (CommonTickHandlerRpgPlus.rpgPluscooldownMap
//							.get(p.getDisplayName()) / 20)) + " seconds"));
//		}
//	}
//}