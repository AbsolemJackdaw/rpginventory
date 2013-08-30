package rpgInventory.handelers.packets;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import rpgInventory.mod_RpgInventory;

public class PacketCrystal {

	public PacketCrystal(DataInputStream dis, EntityPlayer p){
		int entityid;
		try {
			entityid = dis.readInt();

			if (entityid == 0) {
				p.attackEntityFrom(DamageSource.magic, 1);
			} else {
				Entity e = p.worldObj.getEntityByID(entityid);
				if (e != null) {
					if (e instanceof EntityPig) {
						ItemStack is = new ItemStack(mod_RpgInventory.crystal, 1, 1);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					} else if (e instanceof EntitySpider || e instanceof EntityCaveSpider) {
						ItemStack is = new ItemStack(mod_RpgInventory.crystal, 1, 2);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					} else if (e instanceof EntityCow) {
						ItemStack is = new ItemStack(mod_RpgInventory.crystal, 1, 3);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
