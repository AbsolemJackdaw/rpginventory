package rpgInventory.handelers.packets;

import java.io.DataInputStream;
import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handelers.CommonTickHandler;
import cpw.mods.fml.common.network.Player;

public class PacketMageVortex {

	public PacketMageVortex(DataInputStream dis, World world, EntityPlayer p, Player player){
		if (!world.isRemote) {
			ItemStack wand = p.getCurrentEquippedItem();
			ItemStack var311 = p.inventory.armorItemInSlot(3);
			ItemStack var211 = p.inventory.armorItemInSlot(2);
			ItemStack var111 = p.inventory.armorItemInSlot(1);
			ItemStack var011 = p.inventory.armorItemInSlot(0);
			if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
				if (wand == null || var311 == null || var211 == null || var111 == null || var011 == null) {
					return;
				}
				if (wand.getItem() != mod_RpgInventory.wand || var311.getItem() != mod_RpgInventory.magehood || var211.getItem() != mod_RpgInventory.magegown || var111.getItem() != mod_RpgInventory.magepants || var011.getItem() != mod_RpgInventory.mageboots) {
					return;
				}
			}
			if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
				CommonTickHandler.globalCooldownMap.put(p.username, 0);
			}
			if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
				CommonTickHandler.globalCooldownMap.put(p.username, 7 * 20);
				if (wand.getItemDamage() + 3 >= wand.getMaxDamage()) {
					//Trigger item break stuff
					//Only damage what is left
					wand.damageItem(wand.getMaxDamage() - wand.getItemDamage(), p);
					//Do the break item stuff
					p.renderBrokenItemStack(wand);
					//delete the item
					p.setCurrentItemOrArmor(0, (ItemStack) null);
				} else {
					if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
						wand.damageItem(3, p);
					}
				}
				AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - 20.0F, p.posY - 20.0F, p.posZ - 20.0F, p.posX + 20.0F, p.posY + 20.0F, p.posZ + 20.0F);
				List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABBExcludingEntity(p, pool);//p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);

				if (entl != null && entl.size() > 0) {
					for (Entity el : entl) {
						if (el != null && el != player) {

							int var4;

							var4 = 10;
							if (entl instanceof EntityLiving) {
								var4 += EnchantmentHelper.getKnockbackModifier(p, (EntityLiving) el);
							}
							if (var4 > 0) {
								try {
									Vec3 posPlayer = Vec3.createVectorHelper(el.posX, el.posY, el.posZ);
									Vec3 posEntity = Vec3.createVectorHelper(p.posX, p.posY, p.posZ);
									Vec3 posFinal = posPlayer.myVec3LocalPool.getVecFromPool(posEntity.xCoord - posPlayer.xCoord, posEntity.yCoord - posPlayer.yCoord, posEntity.zCoord - posPlayer.zCoord).normalize();
									//System.out.println(posFinal);
									el.setVelocity(posFinal.xCoord * 4, posFinal.yCoord * 4, posFinal.zCoord * 4);
									el.attackEntityFrom(DamageSource.causePlayerDamage(p), 1);
								} catch (Throwable ex) {
								}
							}
						}
					}
				}
			} else {
				p.addChatMessage("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
			}
		}
	}
}
