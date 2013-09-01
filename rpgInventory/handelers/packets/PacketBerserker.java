package rpgInventory.handelers.packets;

import java.io.DataInputStream;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handelers.CommonTickHandler;
import cpw.mods.fml.common.network.Player;

public class PacketBerserker {

	public PacketBerserker(World world, EntityPlayer p, Player player, DataInputStream dis){
		if (!world.isRemote) {
			//System.out.println("Hammer time!");
			ItemStack item1 = p.getCurrentEquippedItem();
			ItemStack var31 = p.inventory.armorItemInSlot(3);
			ItemStack var21 = p.inventory.armorItemInSlot(2);
			ItemStack var11 = p.inventory.armorItemInSlot(1);
			ItemStack var01 = p.inventory.armorItemInSlot(0);

			if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
				if (item1 == null || var31 == null || var21 == null || var11 == null || var01 == null) {
					return;
				}
				if (item1.getItem() != mod_RpgInventory.hammer || var31.getItem() != mod_RpgInventory.berserkerHood || var21.getItem() != mod_RpgInventory.berserkerChest || var11.getItem() != mod_RpgInventory.berserkerLegs || var01.getItem() != mod_RpgInventory.berserkerBoots) {
					return;
				}
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
				CommonTickHandler.globalCooldownMap.put(p.username, 0);
			}
			if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
				CommonTickHandler.globalCooldownMap.put(p.username, 7 * 20);
				if (item1.getItemDamage() + 3 >= item1.getMaxDamage()) {
					//Trigger item break stuff
					//Only damage what is left
					if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
						item1.damageItem(item1.getMaxDamage() - item1.getItemDamage(), p);
					}
					//Do the break item stuff
					p.renderBrokenItemStack(item1);
					//delete the item
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
						item1.damageItem(3, p);
					}
				}
				
				float range = 4.0f;
				if (!mod_RpgInventory.developers.contains(p.username.toLowerCase()))
					range = 4.0f;
				else
					range= 8.0f;
					
					AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - range, p.posY - range, p.posZ - range, p.posX + range, p.posY + range, p.posZ + range);

				List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
				if (entl != null && entl.size() > 0) {
					for (EntityLiving el : entl) {
						if (el != null && el != player) {
							try {
								double xdir = el.posX - p.posX;
								double zdir = el.posZ - p.posZ;

								if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
									el.motionX = xdir * 1.5F;
									el.motionY = 1.5F;
									el.motionZ = zdir * 1.5F;
								}else{
									el.motionX = xdir * 3F;
									el.motionY = 3F;
									el.motionZ = zdir * 3F;
								}
							} catch (Throwable ex) {
							}
							el.attackEntityFrom(DamageSource.causePlayerDamage(p), 10);
						}
					}
				}
			} else {
				p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
			}
		}
	}
}
