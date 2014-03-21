package rpgInventory.handlers.packets;

import ibxm.Player;

import java.io.DataInputStream;
import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.CommonTickHandler;

public class PacketMageVortex {

	public PacketMageVortex(DataInputStream dis, World world, EntityPlayer p,
			Player player) {
		if (!world.isRemote) {
			ItemStack wand = p.getCurrentEquippedItem();
			ItemStack hat = p.inventory.armorItemInSlot(3);
			ItemStack chest = p.inventory.armorItemInSlot(2);
			ItemStack legs = p.inventory.armorItemInSlot(1);
			ItemStack boots = p.inventory.armorItemInSlot(0);

			if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
				if ((wand == null) || (hat == null) || (chest == null)
						|| (legs == null) || (boots == null)) {
					return;
				}
				if ((wand.getItem() != mod_RpgInventory.wand)
						|| (hat.getItem() != mod_RpgInventory.magehood)
						|| (chest.getItem() != mod_RpgInventory.magegown)
						|| (legs.getItem() != mod_RpgInventory.magepants)
						|| (boots.getItem() != mod_RpgInventory.mageboots)) {
					return;
				}
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
				CommonTickHandler.globalCooldownMap.put(p.username, 0);
			}
			if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
				CommonTickHandler.globalCooldownMap.put(p.username, 7 * 20);
				if ((wand.getItemDamage() + 3) >= wand.getMaxDamage()) {
					// Trigger item break stuff
					// Only damage what is left
					wand.damageItem(wand.getMaxDamage() - wand.getItemDamage(),
							p);
					// Do the break item stuff
					p.renderBrokenItemStack(wand);
					// delete the item
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					if (!mod_RpgInventory.developers.contains(p.username
							.toLowerCase())) {
						wand.damageItem(mod_RpgInventory.donators
								.contains(p.username) ? 1 : 3, p);
					}
				}
				float f = mod_RpgInventory.donators.contains(p.username) ? 20.0f
						: 10.0f;
				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(
						p.posX - f, p.posY - f, p.posZ - f, p.posX + f,
						p.posY + f, p.posZ + f);
				List<EntityLivingBase> entl = p.worldObj
						.getEntitiesWithinAABBExcludingEntity(p, pool);

				if ((entl != null) && (entl.size() > 0)) {
					for (Entity el : entl) {
						if ((el != null) && (el != player)) {

							int var4;

							var4 = 10;
							if (entl instanceof EntityLivingBase) {
								var4 += EnchantmentHelper.getKnockbackModifier(
										p, (EntityLivingBase) el);
							}
							if (var4 > 0) {
								try {
									Vec3 posPlayer = Vec3.createVectorHelper(
											el.posX, el.posY, el.posZ);
									Vec3 posEntity = Vec3.createVectorHelper(
											p.posX, p.posY, p.posZ);
									Vec3 posFinal = posPlayer.myVec3LocalPool
											.getVecFromPool(
													posEntity.xCoord
															- posPlayer.xCoord,
													posEntity.yCoord
															- posPlayer.yCoord,
													posEntity.zCoord
															- posPlayer.zCoord)
											.normalize();
									el.setVelocity(posFinal.xCoord * 4,
											posFinal.yCoord * 4,
											posFinal.zCoord * 4);
									el.attackEntityFrom(DamageSource
											.causePlayerDamage(p),
											(mod_RpgInventory.donators
													.contains(p.username) ? 3
													: 1));
								} catch (Throwable ex) {
								}
							}
						}
					}
				}
			} else {
				p.addChatMessage("You must wait for energy to replenish, left: "
						+ Math.floor(1 + (CommonTickHandler.globalCooldownMap
								.get(p.username) / 20)) + " seconds");
			}
		}
	}
}
