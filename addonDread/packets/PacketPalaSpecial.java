package addonDread.packets;

import io.netty.buffer.ByteBufInputStream;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.CommonTickHandler;
import addonDread.mod_RpgPlus;

public class PacketPalaSpecial {

	public PacketPalaSpecial(EntityPlayer p,ByteBufInputStream dis, ItemStack weapon, PlayerRpgInventory inv) {
		try {
			dis.close();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		inv.markDirty();
		

		if (!mod_RpgInventory.developers.contains(p.getDisplayName()
				.toLowerCase()) || (weapon == null)) {
			if (!mod_RpgInventory.playerClass.toLowerCase()
					.contains(mod_RpgPlus.CLASSPALADIN)) {
				return;
			}
		}

		if (!CommonTickHandler.globalCooldownMap
				.containsKey(p.getDisplayName())) {
			CommonTickHandler.globalCooldownMap.put(p.getDisplayName(), 0);
		}
		if (CommonTickHandler.globalCooldownMap.get(p.getDisplayName()) <= 0) {
			CommonTickHandler.globalCooldownMap.put(p.getDisplayName(),
					(mod_RpgInventory.donators.contains(p.getDisplayName()) ? 5
							: 7) * 20);
			// System.out.println("Healing time!");
			// Allow staff/hammer to perform one last aoe then break the weapon
			// if its damaged enough.
			if ((weapon.getItemDamage() + 3) >= weapon.getMaxDamage()) {
				// Trigger item break stuff
				weapon.damageItem(
						(weapon.getMaxDamage() - weapon.getItemDamage()) + 1, p);
				// delete the item
				p.renderBrokenItemStack(weapon);
				p.setCurrentItemOrArmor(0, (ItemStack) null);
			} else {
				if (!mod_RpgInventory.developers.contains(p.getDisplayName()
						.toLowerCase())) {
					weapon.damageItem(3, p);
				}
			}
			
			float rad = 6.0f;
			AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
					p.posX - rad, p.posY - rad, p.posZ - rad, p.posX + rad,
					p.posY + rad, p.posZ + rad);
			List<EntityLivingBase> entl = p.worldObj.getEntitiesWithinAABB(
					EntityLivingBase.class, pool);
			
			System.out.println("heal" + entl);
			
			if ((entl != null) && (entl.size() > 0)) {
				for (EntityLivingBase el : entl) {
					if (el != null) {
						double dist = (p.getDistanceSqToEntity(el));
						double potstrength = 1.0D - (Math.sqrt(dist) / 4.0D);
						Potion.heal.affectEntity(p, el,(mod_RpgInventory.donators.contains(p.getCommandSenderName()) ? 5 : 2),
								potstrength * 2);

					}
				}
			}
		} else {
			p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
					+ Math.floor(1 + (CommonTickHandler.globalCooldownMap.get(p
							.getDisplayName()) / 20)) + " seconds"));
		}
	}
 }
