package rpgNecroPaladin.packets;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import rpgNecroPaladin.CommonTickHandlerRpgPlus;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;

public class PacketSpawnMinion {

	public PacketSpawnMinion(ItemStack weapon, DataInputStream dis, RpgInv inv, EntityPlayer p){
		try {
			dis.close();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		inv.classSets = EnumRpgClass.getPlayerClasses(p);

		if (weapon.getItem().equals(mod_RpgInventory.necro_weapon) && inv.hasClass(EnumRpgClass.NECRO)) {
			if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(p.username)) {
				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p.username, 0);
			}
			if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p.username) <= 0) {
				//2 second cooldown
				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p.username, 20 * 2);
				//System.out.println("SpawnMob");
				//Allow staff/hammer to perform one last aoe then break the weapon if its damaged enough.
				if (weapon.getItemDamage() + 2 >= weapon.getMaxDamage()) {
					//Trigger item break stuff
					weapon.damageItem(weapon.getMaxDamage() - weapon.getItemDamage() + 1, p);
					//delete the item
					p.renderBrokenItemStack(weapon);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					weapon.damageItem(2, p);
				}
				World world = p.worldObj;
				if (inv.hasClass(EnumRpgClass.SHIELDEDNECRO)) {
					if (!world.isRemote) {
						EntityMinionS var4 = new EntityMinionS(world, p);
						if (var4 != null) {
							var4.setPosition(p.posX, p.posY, p.posZ);
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(p.username);
						}
					}
				} else {
					if (!world.isRemote) {
						EntityMinionZ var4 = new EntityMinionZ(world, p);
						if (var4 != null) {
							var4.setPosition(p.posX, p.posY, p.posZ);
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(p.username);
						}
					}
				}
			} else {
				p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p.username) / 20) + " seconds");
			}
		}
	}
}
