package rpgInventory.handlers.oldpackets;

import io.netty.buffer.ByteBufInputStream;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.CommonTickHandler;
import addonBasic.mod_addonBase;

public class PacketBerserker {

	// TODO move packet

	/*
	 * MOVE PACKET TO API
	 */

	/** Move packet to API */
	public PacketBerserker(World world, EntityPlayer p, ByteBufInputStream dis) {
		if (!world.isRemote) {
			// System.out.println("Hammer time!");
			ItemStack item1 = p.getCurrentEquippedItem();
			ItemStack var31 = p.inventory.armorItemInSlot(3);
			ItemStack var21 = p.inventory.armorItemInSlot(2);
			ItemStack var11 = p.inventory.armorItemInSlot(1);
			ItemStack var01 = p.inventory.armorItemInSlot(0);

			if (!mod_RpgInventory.developers.contains(p.getDisplayName()
					.toLowerCase())) {
				if ((item1 == null) || (var31 == null) || (var21 == null)
						|| (var11 == null) || (var01 == null))
					return;
				if ((item1.getItem() != mod_addonBase.hammer)
						|| (var31.getItem() != mod_addonBase.berserkerHood)
						|| (var21.getItem() != mod_addonBase.berserkerChest)
						|| (var11.getItem() != mod_addonBase.berserkerLegs)
						|| (var01.getItem() != mod_addonBase.berserkerBoots))
					return;
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p
					.getDisplayName()))
				CommonTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
			if (CommonTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
				CommonTickHandler.globalCooldownMap
						.put(p.getDisplayName(), (mod_RpgInventory.donators
								.contains(p.getDisplayName()) ? 6 : 7) * 20);
				if ((item1.getItemDamage() + 3) >= item1.getMaxDamage()) {
					// Trigger item break stuff
					// Only damage what is left
					if (!mod_RpgInventory.developers.contains(p
							.getDisplayName().toLowerCase()))
						item1.damageItem(
								item1.getMaxDamage() - item1.getItemDamage(), p);
					// Do the break item stuff
					p.renderBrokenItemStack(item1);
					// delete the item
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else if (!mod_RpgInventory.developers.contains(p
						.getDisplayName().toLowerCase()))
					item1.damageItem(3, p);

				float range = 4.0f;
				if (mod_RpgInventory.developers.contains(p.getDisplayName()
						.toLowerCase()))
					range = 8.0f;
				else
					range = mod_RpgInventory.donators.contains(p
							.getDisplayName()) ? 5.5f : 4.0f;

				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
						p.posX - range, p.posY - range, p.posZ - range,
						p.posX + range, p.posY + range, p.posZ + range);

				List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(
						EntityLivingBase.class, pool);
				if ((entl != null) && (entl.size() > 0))
					for (EntityLivingBase el : entl)
						if ((el != null) && (el != p)) {
							try {
								double xdir = el.posX - p.posX;
								double zdir = el.posZ - p.posZ;

								p.worldObj.spawnParticle("flame", el.posX,
										el.posY, el.posZ, 0, 1, 0);

								if (mod_RpgInventory.developers.contains(p
										.getDisplayName().toLowerCase())) {
									el.motionX = xdir * 3F;
									el.motionY = 3F;
									el.motionZ = zdir * 3F;
								} else {
									el.motionX = xdir
											* (mod_RpgInventory.donators
													.contains(p
															.getDisplayName()) ? 2.2f
													: 1.5F);
									el.motionY = mod_RpgInventory.donators
											.contains(p.getDisplayName()) ? 2.2f
											: 3F;
									el.motionZ = zdir
											* (mod_RpgInventory.donators
													.contains(p
															.getDisplayName()) ? 2.2f
													: 3F);
								}
							} catch (Throwable ex) {
							}
							el.attackEntityFrom(DamageSource
									.causePlayerDamage(p),
									mod_RpgInventory.donators.contains(p
											.getDisplayName()) ? 10 : 8);
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
