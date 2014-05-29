package addonBasic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import rpgInventory.RpgInventoryMod;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;
import addonBasic.packets.ClientPacketHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class KeyHandler implements ISpecialAbility {

	public EntityLivingBase isTargetingEntity(EntityPlayer player,
			float distance) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity Return = null;
		float par1 = 1.0F;
		try {
			if (mc.renderViewEntity != null)
				if (mc.theWorld != null) {
					double var2 = distance;
					mc.objectMouseOver = mc.renderViewEntity.rayTrace(distance,
							1);
					double var4 = var2;
					Vec3 var6 = mc.renderViewEntity.getPosition(1);
					if (mc.objectMouseOver != null)
						var4 = mc.objectMouseOver.hitVec.distanceTo(var6);
					Vec3 var7 = mc.renderViewEntity.getLook(par1);
					Vec3 var8 = var6.addVector(var7.xCoord * var2, var7.yCoord
							* var2, var7.zCoord * var2);
					float var9 = 1.0F;
					List var10 = mc.theWorld
							.getEntitiesWithinAABBExcludingEntity(
									mc.renderViewEntity,
									mc.renderViewEntity.boundingBox.addCoord(
											var7.xCoord * var2,
											var7.yCoord * var2,
											var7.zCoord * var2).expand(var9,
													var9, var9));
					double var11 = var4;
					for (int var13 = 0; var13 < var10.size(); ++var13) {
						Entity var14 = (Entity) var10.get(var13);

						if (var14.canBeCollidedWith()) {
							float var15 = var14.getCollisionBorderSize();
							AxisAlignedBB var16 = var14.boundingBox.expand(
									var15, var15, var15);
							MovingObjectPosition var17 = var16
									.calculateIntercept(var6, var8);
							if (var16.isVecInside(var6)) {
								if ((0.0D < var11) || (var11 == 0.0D)) {
									Return = var14;
									var11 = 0.0D;
								}
							} else if (var17 != null) {
								double var18 = var6.distanceTo(var17.hitVec);

								if ((var18 < var11) || (var11 == 0.0D)) {
									Return = var14;
									var11 = var18;
								}
							}
							if ((Return != null)
									&& (Return instanceof EntityLivingBase))
								return (EntityLivingBase) Return;
						}
					}
				}
		} catch (Throwable e) {
		}

		return null;
	}

	@Override
	public void specialAbility(ItemStack item) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;

		if(RpgUtility.canSpecial(p, RpgBaseAddon.hammer))
			try {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(ClientPacketHandler.BERSERKER);
				RpgBaseAddon.Channel.sendToServer(new FMLProxyPacket(buf,"BaseAddon"));
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		if(RpgUtility.canSpecial(p, RpgBaseAddon.wand))
			try {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(ClientPacketHandler.MAGE2);
				//				if(!p.worldObj.isRemote)
				RpgBaseAddon.Channel.sendToServer(new FMLProxyPacket(buf,"BaseAddon"));
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}


		if(RpgUtility.canSpecial(p, RpgBaseAddon.staf))
			try {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(ClientPacketHandler.MAGE1);
				//				if(!p.worldObj.isRemote)
				RpgBaseAddon.Channel.sendToServer(new FMLProxyPacket(buf,"BaseAddon"));
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		if(RpgUtility.canSpecial(p, RpgBaseAddon.elfbow))
			try {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(ClientPacketHandler.ARCHER);

				EntityLivingBase target = isTargetingEntity(
						Minecraft.getMinecraft().thePlayer,
						RpgInventoryMod.donators.contains(Minecraft
								.getMinecraft().thePlayer
								.getCommandSenderName()) ? 60 : 40);
				if (target != null) {
					out.writeBoolean(false);
					out.writeInt((int) Math.floor(target.posX));
					out.writeInt((int) Math.floor(target.posY));
					out.writeInt((int) Math.floor(target.posZ));
					RpgBaseAddon.Channel.sendToServer(new FMLProxyPacket(buf,"BaseAddon"));
					out.close();
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

}
