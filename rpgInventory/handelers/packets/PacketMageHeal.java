package rpgInventory.handelers.packets;

import java.io.DataInputStream;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handelers.CommonTickHandler;
import cpw.mods.fml.common.network.Player;

public class PacketMageHeal {

	public PacketMageHeal(DataInputStream dis, EntityPlayer p, Player player, World world){
		
		if (!world.isRemote) {
			ItemStack item = p.getCurrentEquippedItem();
			ItemStack var3 = p.inventory.armorItemInSlot(3);
			ItemStack var2 = p.inventory.armorItemInSlot(2);
			ItemStack var1 = p.inventory.armorItemInSlot(1);
			ItemStack var0 = p.inventory.armorItemInSlot(0);
			if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
				if (item == null || var3 == null || var2 == null || var1 == null || var0 == null) {
					return;
				}
				if (item.getItem() != mod_RpgInventory.staf || var3.getItem() != mod_RpgInventory.magehood || var2.getItem() != mod_RpgInventory.magegown || var1.getItem() != mod_RpgInventory.magepants || var0.getItem() != mod_RpgInventory.mageboots) {
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
				if (item.getItemDamage() + 3 >= item.getMaxDamage()) {
					//Trigger item break stuff
					item.damageItem(item.getMaxDamage() - item.getItemDamage() + 1, p);
					//delete the item
					p.renderBrokenItemStack(item);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
						item.damageItem(3, p);
					}
				}
				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - 4.0F, p.posY - 4.0F, p.posZ - 4.0F, p.posX + 4.0F, p.posY + 4.0F, p.posZ + 4.0F);
				List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
				if (entl != null && entl.size() > 0) {
					for (EntityLiving el : entl) {
						if (el != null) {
							double dist = ((EntityPlayer) player).getDistanceSqToEntity(el);
							double potstrength = 1.0D - Math.sqrt(dist) / 4.0D;
							Potion.heal.affectEntity((EntityLiving) player, el, 2, potstrength);
						}
					}
				}
			} else {
				p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
			}
		}
	}
}
