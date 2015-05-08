package addonMasters.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import addonMasters.RpgMastersAddon;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketCrystal implements IMessage{

	public PacketCrystal() {
	}

	public int entityID;
	
	public PacketCrystal(int entityID) {
		this.entityID = entityID;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityID);
	}

	public static class HandlerPacketCrystal implements IMessageHandler<PacketCrystal, IMessage>{

		@Override
		public IMessage onMessage(PacketCrystal message, MessageContext ctx) {

			EntityPlayer p = ctx.getServerHandler().playerEntity;

			if (message.entityID == 0) {
				p.attackEntityFrom(DamageSource.magic, 1);
			} else {
				Entity e = p.worldObj.getEntityByID(message.entityID);
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
					}else if (e instanceof EntityChicken) {
						ItemStack is = new ItemStack(RpgMastersAddon.crystal, 1, 4);
						e.entityDropItem(is, 0.5F);
						e.setDead();
					}
				}
			}
			return null;
		}
	}
}
