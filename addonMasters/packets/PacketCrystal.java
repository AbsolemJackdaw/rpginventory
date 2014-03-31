package addonMasters.packets;

import io.netty.buffer.ByteBufInputStream;

import java.io.DataInputStream;
import java.io.IOException;

import addonMasters.RpgMastersAddon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class PacketCrystal {

	public PacketCrystal(ByteBufInputStream dis, EntityPlayer p) {

		int entityid;
		try {
			entityid = dis.readInt();

			if (entityid == 0) {
				p.attackEntityFrom(DamageSource.magic, 1);
			} else {
				Entity e = p.worldObj.getEntityByID(entityid);
				if (e != null) {
					if (e instanceof EntityPig) {
						ItemStack is = new ItemStack(RpgMastersAddon.crystal, 1, 1);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					} else if ((e instanceof EntitySpider)
							|| (e instanceof EntityCaveSpider)) {
						ItemStack is = new ItemStack(RpgMastersAddon.crystal, 1, 2);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					} else if (e instanceof EntityCow) {
						ItemStack is = new ItemStack(RpgMastersAddon.crystal, 1, 3);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
 }
