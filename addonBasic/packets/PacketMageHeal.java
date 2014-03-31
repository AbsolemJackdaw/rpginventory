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
import rpgInventory.handlers.CommonTickHandler;
import addonBasic.RpgBaseAddon;

public class PacketMageHeal {

	// TODO move packet

	/*
	 * MOVE PACKET TO API
	 */

	/** Move packet to API */
	public PacketMageHeal(ByteBufInputStream dis, EntityPlayer p, World world) {

		if (!world.isRemote) {
			ItemStack item = p.getCurrentEquippedItem();
			ItemStack var3 = p.inventory.armorItemInSlot(3);
			ItemStack var2 = p.inventory.armorItemInSlot(2);
			ItemStack var1 = p.inventory.armorItemInSlot(1);
			ItemStack var0 = p.inventory.armorItemInSlot(0);
			if (!RpgInventoryMod.developers.contains(p.getDisplayName()
					.toLowerCase())) {
				if ((item == null) || (var3 == null) || (var2 == null)
						|| (var1 == null) || (var0 == null))
					return;
				if ((item.getItem() != RpgBaseAddon.staf)
						|| (var3.getItem() != RpgBaseAddon.magehood)
						|| (var2.getItem() != RpgBaseAddon.magegown)
						|| (var1.getItem() != RpgBaseAddon.magepants)
						|| (var0.getItem() != RpgBaseAddon.mageboots))
					return;
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p
					.getDisplayName()))
				CommonTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
			if (CommonTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
				CommonTickHandler.globalCooldownMap.put(p.getDisplayName(),
						5 * 20);
				// System.out.println("Healing time!");
				// Allow staff/hammer to perform one last aoe then break the
				// weapon if its damaged enough.
				if ((item.getItemDamage() + 3) >= item.getMaxDamage()) {
					// Trigger item break stuff
					item.damageItem(
							(item.getMaxDamage() - item.getItemDamage()) + 1, p);
					// delete the item
					p.renderBrokenItemStack(item);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else if (!RpgInventoryMod.developers.contains(p
						.getDisplayName().toLowerCase()))
					item.damageItem(3, p);
				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
						p.posX - 4.0F, p.posY - 4.0F, p.posZ - 4.0F,
						p.posX + 4.0F, p.posY + 4.0F, p.posZ + 4.0F);
				List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(
						EntityLivingBase.class, pool);
				if ((entl != null) && (entl.size() > 0))
					for (EntityLivingBase el : entl)
						if (el != null) {
							double dist = p.getDistanceSqToEntity(el);
							double potstrength = 1.0D - (Math.sqrt(dist) / (RpgInventoryMod.donators
									.contains(p.getDisplayName()) ? 6.0D : 4.0D));
							Potion.heal.affectEntity(p, el,
									(RpgInventoryMod.donators.contains(p
											.getDisplayName()) ? 4 : 2),
									potstrength);
						}
			} else
				p.addChatMessage(new ChatComponentText(
						"You must wait for energy to replenish, left: "
								+ Math.floor(1 + (CommonTickHandler.globalCooldownMap
										.get(p.getDisplayName()) / 20))
								+ " seconds"));
		}
	}
}
