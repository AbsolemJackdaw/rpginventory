package addonMasters.packets;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.ServerTickHandler;
import addonMasters.RpgMastersAddon;
import addonMasters.entity.EntityTeleportStone;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketTeleport implements IMessage{

	static Random rand = new Random();
	
	public PacketTeleport() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}


	public static class HandlerPacketTeleport implements IMessageHandler<PacketTeleport, IMessage>{

		@Override
		public IMessage onMessage(PacketTeleport message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;
			World world = p.worldObj;
			
			if (!world.isRemote) {
				ItemStack dagger = p.getCurrentEquippedItem();

				if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
					if (!RpgInventoryMod.playerClass.contains(RpgMastersAddon.CLASSROGUE)) {
						return null;
					}
				}
				if (!ServerTickHandler.globalCooldownMap.containsKey(p
						.getDisplayName())) {
					ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
				}
				if (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
					ServerTickHandler.globalCooldownMap
					.put(p.getDisplayName(), (5) * 20);
					if ((dagger.getItemDamage() + 3) >= dagger.getMaxDamage()) {
						dagger.damageItem(
								dagger.getMaxDamage() - dagger.getItemDamage(), p);
						p.renderBrokenItemStack(dagger);
						p.setCurrentItemOrArmor(0, (ItemStack) null);
					} else if (!RpgInventoryMod.developers.contains(p
							.getDisplayName().toLowerCase())) {
						dagger.damageItem(3, p);
					}
					p.worldObj.spawnEntityInWorld(new EntityTeleportStone(
							p.worldObj, p));
					double d0 = rand.nextGaussian() * 0.02D;
					double d1 = rand.nextGaussian() * 0.02D;
					double d2 = rand.nextGaussian() * 0.02D;
					p.worldObj.spawnParticle("largesmoke",
							(p.posX + (rand.nextFloat() * p.width * 2.0F))- p.width,
							p.posY + 0.5D+ (rand.nextFloat() * p.height),
							(p.posZ + (rand.nextFloat() * p.width * 2.0F))- p.width, 
							d0, d1, d2);

				} else {
					p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
							+ Math.floor(1 + (ServerTickHandler.globalCooldownMap
									.get(p.getDisplayName()) / 20)) + " seconds"));
				}
			}

			return null;
		}
	}
}
