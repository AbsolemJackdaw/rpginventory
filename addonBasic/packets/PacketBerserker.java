package addonBasic.packets;

import io.netty.buffer.ByteBufInputStream;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.CommonTickHandler;
import addonBasic.RpgBaseAddon;

public class PacketBerserker {

	public PacketBerserker(World world, EntityPlayer p, ByteBufInputStream dis) {


		
			 //System.out.println("Hammer time!");
			ItemStack item1 = p.getCurrentEquippedItem();
			ItemStack var31 = p.inventory.armorItemInSlot(3);
			ItemStack var21 = p.inventory.armorItemInSlot(2);
			ItemStack var11 = p.inventory.armorItemInSlot(1);
			ItemStack var01 = p.inventory.armorItemInSlot(0);

			if (!RpgInventoryMod.developers.contains(p.getDisplayName()
					.toLowerCase())) {
				if ((item1 == null) || (var31 == null) || (var21 == null)
						|| (var11 == null) || (var01 == null))
					return;
				if ((item1.getItem() != RpgBaseAddon.hammer)
						|| (var31.getItem() != RpgBaseAddon.berserkerHood)
						|| (var21.getItem() != RpgBaseAddon.berserkerChest)
						|| (var11.getItem() != RpgBaseAddon.berserkerLegs)
						|| (var01.getItem() != RpgBaseAddon.berserkerBoots))
					return;
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p
					.getDisplayName()))
				CommonTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
			if (CommonTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
				CommonTickHandler.globalCooldownMap
						.put(p.getDisplayName(), (RpgInventoryMod.donators
								.contains(p.getDisplayName()) ? 6 : 7) * 20);
				if ((item1.getItemDamage() + 3) >= item1.getMaxDamage()) {
					// Trigger item break stuff
					// Only damage what is left
					if (!RpgInventoryMod.developers.contains(p
							.getDisplayName().toLowerCase()))
						item1.damageItem(
								item1.getMaxDamage() - item1.getItemDamage(), p);
					// Do the break item stuff
					p.renderBrokenItemStack(item1);
					// delete the item
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else if (!RpgInventoryMod.developers.contains(p
						.getDisplayName().toLowerCase()))
					item1.damageItem(3, p);

				float range = 4.0f;
				if (RpgInventoryMod.developers.contains(p.getDisplayName()
						.toLowerCase()))
					range = 8.0f;
				else
					range = RpgInventoryMod.donators.contains(p
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
													            
								if (RpgInventoryMod.developers.contains(p
										.getDisplayName().toLowerCase())) {
									el.motionX = xdir * 3F;
									el.motionY = 3F;
									el.motionZ = zdir * 3F;
								} else {
									el.motionX = xdir
											* (RpgInventoryMod.donators
													.contains(p
															.getDisplayName()) ? 2.2f
													: 1.5F);
									el.motionY = RpgInventoryMod.donators
											.contains(p.getDisplayName()) ? 2.2f
											: 3F;
									el.motionZ = zdir
											* (RpgInventoryMod.donators
													.contains(p
															.getDisplayName()) ? 2.2f
													: 3F);
									}
							} catch (Throwable ex) {
							}
							el.attackEntityFrom(DamageSource
									.causePlayerDamage(p),
									RpgInventoryMod.donators.contains(p
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
