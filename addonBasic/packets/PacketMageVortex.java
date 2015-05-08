package addonBasic.packets;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.ServerTickHandler;
import addonBasic.RpgBaseAddon;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketMageVortex implements IMessage{

	public PacketMageVortex() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class HandlerPacketMageVortex implements IMessageHandler<PacketMageVortex, IMessage>{

		@Override
		public IMessage onMessage(PacketMageVortex message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;
			World world = p.worldObj;

			if (!world.isRemote) {

				ItemStack wand = p.getCurrentEquippedItem();

				if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
					if(!RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSALCHEMIST))
						return null;

					if ((wand.getItem() != RpgBaseAddon.soulSphere))
						return null;
				}
				if (!ServerTickHandler.globalCooldownMap.containsKey(p.getDisplayName())) {
					ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
				}
				if (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
					ServerTickHandler.globalCooldownMap.put(p.getDisplayName(),
							7 * 20);
					if ((wand.getItemDamage() + 3) >= wand.getMaxDamage()) {
						wand.damageItem(wand.getMaxDamage() - wand.getItemDamage(),p);
						p.renderBrokenItemStack(wand);
						p.setCurrentItemOrArmor(0, (ItemStack) null);
					} else if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
						wand.damageItem( 3, p);
					}
					float f = 10.0f;
					AxisAlignedBB pool = AxisAlignedBB.getBoundingBox(p.posX - f, p.posY - f, p.posZ - f, p.posX + f,p.posY + f, p.posZ + f);
					List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABBExcludingEntity(p, pool);

					if ((entl != null) && (entl.size() > 0)) {
						for (Entity el : entl) {
							if ((el != null) && (el != p)) {

								int var4;

								var4 = 10;
								if (entl instanceof EntityLivingBase) {
									var4 += EnchantmentHelper.getKnockbackModifier(
											p, (EntityLivingBase) el);
								}
								if (var4 > 0) {
									try {
										Vec3 posPlayer = Vec3.createVectorHelper(el.posX, el.posY, el.posZ);
										Vec3 posEntity = Vec3.createVectorHelper(p.posX, p.posY, p.posZ);
										Vec3 posFinal = posPlayer.createVectorHelper(posEntity.xCoord- posPlayer.xCoord,posEntity.yCoord- posPlayer.yCoord,posEntity.zCoord- posPlayer.zCoord).normalize();
										el.setVelocity(posFinal.xCoord * 4,posFinal.yCoord * 4,posFinal.zCoord * 4);
										el.attackEntityFrom(DamageSource.causePlayerDamage(p),(1));
									} catch (Throwable ex) {
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
			return null;
		}

	}

}