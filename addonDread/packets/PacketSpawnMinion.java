package addonDread.packets;


import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonDread.CommonTickHandlerRpgPlus;
import addonDread.RpgDreadAddon;
import addonDread.minions.EntityMinionS;
import addonDread.minions.EntityMinionZ;

public class PacketSpawnMinion {

	public PacketSpawnMinion(ItemStack weapon, ByteBufInputStream dis,
			PlayerRpgInventory inv, EntityPlayer p) {
		
		System.out.println(CommonTickHandlerRpgPlus.rpgPluscooldownMap + " " +RpgInventoryMod.playerClass);

		
		if (weapon.getItem().equals(RpgDreadAddon.necro_weapon)) {
			if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(p.getDisplayName())) {
				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(
						p.getDisplayName(), 0);
			}
			
			
			if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p
					.getDisplayName()) <= 0) {
				// 2 second cooldown
				CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p
						.getDisplayName(), 20 * (RpgInventoryMod.donators
						.contains(p.getDisplayName()) ? 1 : 2));
				 
				// Allow staff/hammer to perform one last aoe then break the
				// weapon if its damaged enough.
				if ((weapon.getItemDamage() + 2) >= weapon.getMaxDamage()) {
					// Trigger item break stuff
					weapon.damageItem(
							(weapon.getMaxDamage() - weapon.getItemDamage()) + 1,
							p);
					// delete the item
					p.renderBrokenItemStack(weapon);
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					weapon.damageItem(2, p);
				}
				World world = p.worldObj;
				if (RpgInventoryMod.playerClass
						.contains(RpgDreadAddon.CLASSNECROSHIELD)) {
					if (!world.isRemote) {
						EntityMinionS var4 = new EntityMinionS(world, p);
						if (var4 != null) {
							var4.setPosition(p.posX, p.posY, p.posZ);
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(p.getDisplayName());
						}
					}
				} else {
					if (!world.isRemote) {
						EntityMinionZ var4 = new EntityMinionZ(world, p);
						if (var4 != null) {
							var4.setPosition(p.posX, p.posY, p.posZ);
							world.spawnEntityInWorld(var4);
							var4.setTamed(true);
							var4.setOwner(p.getDisplayName());
						}
					}
				}
			} else {
				p.addChatMessage(new ChatComponentText("You must wait for energy to replenish, left: "
						+ Math.floor(1 + (CommonTickHandlerRpgPlus.rpgPluscooldownMap
								.get(p.getDisplayName()) / 20)) + " seconds"));
			}
		}
	}
 }
