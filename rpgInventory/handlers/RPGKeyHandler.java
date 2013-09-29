/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.packets.RpgPacketHandler;
import rpgInventory.utils.AbstractKeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RPGKeyHandler extends AbstractKeyHandler {

	public static Map<Integer, Integer> abilityMap = new HashMap();

	public RPGKeyHandler() {
		super();
		abilityMap.put(mod_RpgInventory.staf.itemID, RpgPacketHandler.MAGE1);
		abilityMap.put(mod_RpgInventory.hammer.itemID, RpgPacketHandler.BERSERKER);
		abilityMap.put(mod_RpgInventory.elfbow.itemID, RpgPacketHandler.ARCHER);
		abilityMap.put(mod_RpgInventory.wand.itemID, RpgPacketHandler.MAGE2);
		// abilityMap.put(mod_RpgInventory.daggers.itemID, 14);
		// 14 used in another packet !
	}

	public EntityLiving isTargetingEntity(EntityPlayer player, float distance) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity Return = null;
		float par1 = 1.0F;
		try {
			if (mc.renderViewEntity != null) {
				if (mc.theWorld != null) {
					double var2 = distance;
					mc.objectMouseOver = mc.renderViewEntity.rayTrace(distance, 1);
					double var4 = var2;
					Vec3 var6 = mc.renderViewEntity.getPosition(1);
					if (mc.objectMouseOver != null) {
						var4 = mc.objectMouseOver.hitVec.distanceTo(var6);
					}
					Vec3 var7 = mc.renderViewEntity.getLook(par1);
					Vec3 var8 = var6.addVector(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2);
					float var9 = 1.0F;
					List var10 = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.renderViewEntity, mc.renderViewEntity.boundingBox.addCoord(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2).expand((double) var9, (double) var9, (double) var9));
					double var11 = var4;
					for (int var13 = 0; var13 < var10.size(); ++var13) {
						Entity var14 = (Entity) var10.get(var13);

						if (var14.canBeCollidedWith()) {
							float var15 = var14.getCollisionBorderSize();
							AxisAlignedBB var16 = var14.boundingBox.expand((double) var15, (double) var15, (double) var15);
							MovingObjectPosition var17 = var16.calculateIntercept(var6, var8);
							if (var16.isVecInside(var6)) {
								if (0.0D < var11 || var11 == 0.0D) {
									Return = var14;
									var11 = 0.0D;
								}
							} else if (var17 != null) {
								double var18 = var6.distanceTo(var17.hitVec);

								if (var18 < var11 || var11 == 0.0D) {
									Return = var14;
									var11 = var18;
								}
							}
							if (Return != null && Return instanceof EntityLiving) {
								return (EntityLiving) Return;
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
	protected void specialAbility(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, ItemStack item) {
		if (abilityMap.containsKey(item.getItem().itemID)) {

			int i = abilityMap.get(item.getItem().itemID);
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(i);
				if (item.getItem().itemID == mod_RpgInventory.elfbow.itemID) {
					EntityLiving target = isTargetingEntity(Minecraft.getMinecraft().thePlayer, mod_RpgInventory.donators.contains(Minecraft.getMinecraft().thePlayer.username) ? 60 : 40);
					if (target != null) {
						outputStream.writeBoolean(false);
						outputStream.writeInt((int) Math.floor(target.posX));
						outputStream.writeInt((int) Math.floor(target.posY));
						outputStream.writeInt((int) Math.floor(target.posZ));
					} else {
						outputStream.writeBoolean(true);
					}
				}
				Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	//	@Override
	//	public String getLabel() {
	//		return "RPG Inventory Key";
	//	}
}
