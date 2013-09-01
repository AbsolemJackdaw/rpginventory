package rpgRogueBeast.packets;

import java.io.DataInputStream;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handelers.CommonTickHandler;
import rpgRogueBeast.entity.EntityTeleportStone;

public class PacketTeleport {

	public PacketTeleport(World world, EntityPlayer p, DataInputStream dis, Random rand){
		if (!world.isRemote) {
			ItemStack dagger = p.getCurrentEquippedItem();
			ItemStack var311 = p.inventory.armorItemInSlot(3);
			ItemStack var211 = p.inventory.armorItemInSlot(2);
			ItemStack var111 = p.inventory.armorItemInSlot(1);
			ItemStack var011 = p.inventory.armorItemInSlot(0);
			if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
				if (dagger == null || var311 == null || var211 == null || var111 == null || var011 == null) {
					return;
				}
				if (dagger.getItem() != mod_RpgInventory.daggers || var311.getItem() != mod_RpgInventory.rogueHood || var211.getItem() != mod_RpgInventory.rogueChest || var111.getItem() != mod_RpgInventory.rogueLegs || var011.getItem() != mod_RpgInventory.rogueBoots) {
					return;
				}
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
				CommonTickHandler.globalCooldownMap.put(p.username, 0);
			}
			if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
				CommonTickHandler.globalCooldownMap.put(p.username, 5 * 20);
				if (dagger.getItemDamage() + 3 >= dagger.getMaxDamage()) {
					dagger.damageItem(dagger.getMaxDamage() - dagger.getItemDamage(), p);
					p.renderBrokenItemStack(dagger);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
						dagger.damageItem(3, p);
					}
				}
				p.worldObj.spawnEntityInWorld(new EntityTeleportStone(p.worldObj, p));
				double d0 = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				p.worldObj.spawnParticle("largesmoke", p.posX + (double) (rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (rand.nextFloat() * p.height), p.posZ + (double) (rand.nextFloat() * p.width * 2.0F) - (double) p.width, d0, d1, d2);

			} else {
				p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
			}
		}
	}
}
