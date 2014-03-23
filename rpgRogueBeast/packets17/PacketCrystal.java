package rpgRogueBeast.packets17;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import rpgRogueBeast.mod_RpgRB;

public class PacketCrystal extends RpgRBAbstractPacket {

	private EntityLivingBase entity;
	private int entityid;
	
	public PacketCrystal() {}
	
	public PacketCrystal(EntityLivingBase entity) {
		super();
		this.entity = entity;
	}
	
	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.entityid = buffer.readInt();
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(entity.getEntityId());
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		
		if (entityid == 0) {
			player.attackEntityFrom(DamageSource.magic, 1);
		} else {
			Entity e = player.worldObj.getEntityByID(entityid);
			if (e != null) {
				if (e instanceof EntityPig) {
					ItemStack is = new ItemStack(mod_RpgRB.crystal, 1, 1);
					e.entityDropItem(is, 0.5F);
					e.setDead();
				} else if ((e instanceof EntitySpider)
						|| (e instanceof EntityCaveSpider)) {
					ItemStack is = new ItemStack(mod_RpgRB.crystal, 1, 2);
					e.entityDropItem(is, 0.5F);
					e.setDead();
				} else if (e instanceof EntityCow) {
					ItemStack is = new ItemStack(mod_RpgRB.crystal, 1, 3);
					e.entityDropItem(is, 0.5F);
					e.setDead();
				}
			}
		}
	}
}
