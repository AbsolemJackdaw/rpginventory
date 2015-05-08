package addonBasic.packets;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.ServerTickHandler;
import addonBasic.RpgBaseAddon;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketMageHeal implements IMessage{

	public PacketMageHeal() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}


	public static class HandlerPacketMageHeal implements IMessageHandler<PacketMageHeal, IMessage>{

		@Override
		public IMessage onMessage(PacketMageHeal message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;
			World world = p.worldObj;

			if (!world.isRemote) {
				ItemStack item = p.getCurrentEquippedItem();
				if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
					if(!RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSALCHEMIST))
						return null;
					if ((item.getItem() != RpgBaseAddon.lunarStaff))
						return null;
				}
				if (!ServerTickHandler.globalCooldownMap.containsKey(p.getDisplayName())) {
					ServerTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
				}
				if (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
					ServerTickHandler.globalCooldownMap.put(p.getDisplayName(),5 * 20);

					if ((item.getItemDamage() + 3) >= item.getMaxDamage()) {
						item.damageItem((item.getMaxDamage() - item.getItemDamage()) + 1, p);
						p.renderBrokenItemStack(item);
						p.setCurrentItemOrArmor(0, (ItemStack) null);
					} else if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
						item.damageItem(3, p);
					}
					AxisAlignedBB pool = AxisAlignedBB.getBoundingBox(
							p.posX - 4.0F, p.posY - 4.0F, p.posZ - 4.0F,
							p.posX + 4.0F, p.posY + 4.0F, p.posZ + 4.0F);
					List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, pool);
					if ((entl != null) && (entl.size() > 0)) {
						for (EntityLivingBase el : entl) {
							if (el != null) {
								double dist = p.getDistanceSqToEntity(el);
								double potstrength = 1.0D - (Math.sqrt(dist) / (4.0D));
								Potion.heal.affectEntity(p, el,(2),potstrength);
							}
						}
					}
				} else {
					p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
							+ Math.floor(1 + (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) / 20))+ " seconds"));
				}
			}
			return null;
		}
	}
}