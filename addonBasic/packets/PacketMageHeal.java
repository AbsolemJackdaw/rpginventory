package addonBasic.packets;

import io.netty.buffer.ByteBufInputStream;

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

public class PacketMageHeal {

	public PacketMageHeal(ByteBufInputStream dis, EntityPlayer p, World world) {

		if (!world.isRemote) {
			ItemStack item = p.getCurrentEquippedItem();
			if (!RpgInventoryMod.developers.contains(p.getDisplayName().toLowerCase())) {
				if(!RpgInventoryMod.playerClass.equals(RpgBaseAddon.CLASSALCHEMIST))
					return;
				if ((item.getItem() != RpgBaseAddon.lunarStaff))
					return;
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
				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
						p.posX - 4.0F, p.posY - 4.0F, p.posZ - 4.0F,
						p.posX + 4.0F, p.posY + 4.0F, p.posZ + 4.0F);
				List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(
						EntityLivingBase.class, pool);
				if ((entl != null) && (entl.size() > 0)) {
					for (EntityLivingBase el : entl) {
						if (el != null) {
							double dist = p.getDistanceSqToEntity(el);
							double potstrength = 1.0D - (Math.sqrt(dist) / (RpgInventoryMod.donators.contains(p.getDisplayName()) ? 6.0D : 4.0D));
							Potion.heal.affectEntity(p, el,(RpgInventoryMod.donators.contains(p.getDisplayName()) ? 4 : 2),potstrength);
						}
					}
				}
			} else {
				p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
						+ Math.floor(1 + (ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) / 20))+ " seconds"));
			}
		}
	}
}
