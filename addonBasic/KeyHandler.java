package addonBasic;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import rpgInventory.handlers.ServerTickHandler;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;
import addonBasic.packets.PacketArcher;
import addonBasic.packets.PacketBerserker;
import addonBasic.packets.PacketMageHeal;
import addonBasic.packets.PacketMageVortex;

public class KeyHandler implements ISpecialAbility {

	public EntityLivingBase isTargetingEntity(EntityPlayer player,
			float distance) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity Return = null;
		float par1 = 1.0F;
		try {
			if (mc.renderViewEntity != null) {
				if (mc.theWorld != null) {
					double var2 = distance;
					mc.objectMouseOver = mc.renderViewEntity.rayTrace(distance,
							1);
					double var4 = var2;
					Vec3 var6 = mc.renderViewEntity.getPosition(1);
					if (mc.objectMouseOver != null) {
						var4 = mc.objectMouseOver.hitVec.distanceTo(var6);
					}
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
									&& (Return instanceof EntityLivingBase)) {
								return (EntityLivingBase) Return;
							}
						}
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

		if((item.getItem() == RpgBaseAddon.hammer)) {

			RpgBaseAddon.SNW.sendToServer(new PacketBerserker());

			if(ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) / 20 <= 0){
				RpgUtility.spawnParticle(p, 6, "smoke", 5, 1);
				RpgUtility.spawnParticle(p, 8, "largeexplode", 6, 1);
			}
		}

		if((item.getItem() == RpgBaseAddon.soulSphere))
			RpgBaseAddon.SNW.sendToServer(new PacketMageVortex());


		if((item.getItem() == RpgBaseAddon.lunarStaff)) {
			RpgBaseAddon.SNW.sendToServer(new PacketMageHeal());

		}

		if((item.getItem() ==  RpgBaseAddon.elfbow)) {
			EntityLivingBase target = isTargetingEntity(Minecraft.getMinecraft().thePlayer,40);
			if (target != null) 
				RpgBaseAddon.SNW.sendToServer(new PacketArcher(target.posX, target.posY, target.posZ));
		}
	}
}
