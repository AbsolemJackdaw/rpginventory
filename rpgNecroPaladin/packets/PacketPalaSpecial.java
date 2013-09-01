package rpgNecroPaladin.packets;

import java.io.DataInputStream;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import rpgInventory.handelers.CommonTickHandler;
import cpw.mods.fml.common.network.Player;

public class PacketPalaSpecial {

	public PacketPalaSpecial(Player player, EntityPlayer p, DataInputStream dis, ItemStack weapon, RpgInv inv){
		try {
			dis.close();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		inv.onInventoryChanged();
		inv.classSets = EnumRpgClass.getPlayerClasses(p);

		if (!mod_RpgInventory.developers.contains(p.username.toLowerCase()) || weapon == null) {
			if (!inv.hasClass(EnumRpgClass.PALADIN)) {
				return;
			}
		}

		if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
			CommonTickHandler.globalCooldownMap.put(p.username, 0);
		}
		if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
			CommonTickHandler.globalCooldownMap.put(p.username, 5 * 20);
			//System.out.println("Healing time!");
			//Allow staff/hammer to perform one last aoe then break the weapon if its damaged enough.
			if (weapon.getItemDamage() + 3 >= weapon.getMaxDamage()) {
				//Trigger item break stuff
				weapon.damageItem(weapon.getMaxDamage() - weapon.getItemDamage() + 1, p);
				//delete the item
				p.renderBrokenItemStack(weapon);
				p.setCurrentItemOrArmor(0, (ItemStack) null);
			} else {
				if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
					weapon.damageItem(3, p);
				}
			}
			float rad = 6.0f;
			AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - rad, p.posY - rad, p.posZ - rad, p.posX + rad, p.posY + rad, p.posZ + rad);
			List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
			if (entl != null && entl.size() > 0) {
				for (EntityLiving el : entl) {
					if (el != null) {
						double dist = ((EntityPlayer) player).getDistanceSqToEntity(el);
						double potstrength = 1.0D - Math.sqrt(dist) / 4.0D;
						Potion.heal.affectEntity((EntityLiving) player, el, 2, potstrength * 2);
					}
				}
			}
		} else {
			p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
		}
	}
}
