/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import org.lwjgl.input.Keyboard;

import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.packets.ServerPacketHandler;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class RPGKeyHandler implements ISpecialAbility{


	protected static KeyBinding keyInventory = new KeyBinding(
			"RPG Inventory Key", Keyboard.KEY_END, "rpginventorymod");

	protected static KeyBinding keySpecial = new KeyBinding(
			"RPG Special Ability", Keyboard.KEY_F, "rpginventorymod");

	public static final int OPENRPGINV = 1;
	public static final int INVENTORY = 15;
	public static final int SMP_INVENTORY_SYNC = 20;



	public RPGKeyHandler() {
		super();

		ClientRegistry.registerKeyBinding(keyInventory);
		ClientRegistry.registerKeyBinding(keySpecial);

		// registeredKeyBinds.toArray(new KeyBinding[registeredKeyBinds
		// .size()]), new boolean[registeredKeyBinds.size()]
	}

	public RPGKeyHandler(KeyBinding[] k, boolean[] b) {
		// super(bindKeys, reps);
		super();

	}

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

	@SubscribeEvent
	public void keys(KeyInputEvent evt) {

		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (keySpecial.isPressed()) {

				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if ((guiscreen == null) && !(item == null)){

					for(Item i : abilityMap.keySet()){
						if(item.getItem().equals(i)){
							specialAbility(item);
							System.out.println(RpgUtility.allAbilities);
							for(int c =0; c < RpgUtility.allAbilities.size(); c++)
								RpgUtility.allAbilities.get(c).specialAbility(item);
						}
					}
				}
			} else if (keyInventory.isPressed()) {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(ServerPacketHandler.OPENRPGINV);
				mod_RpgInventory.Channel.sendToServer(new FMLProxyPacket(buf,
						"RpgInv"));
				out.close();
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public void specialAbility(ItemStack item) {
		//no special abilities here .. .3.

		//		if (abilityMap.containsKey(item.getItem())) {
		//
		//			int i = abilityMap.get(item.getItem());
		//
		//			try {
		//				ByteBuf buf = Unpooled.buffer();
		//				ByteBufOutputStream out = new ByteBufOutputStream(buf);
		//				out.writeInt(i);
		//				mod_RpgInventory.Channel.sendToServer(new FMLProxyPacket(buf,
		//						"RpgInv"));
		//				out.close();
		//
		//			} catch (IOException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//
		//
		//			// TODO place this elsewhere. the bow and mage staff are no
		//			// longer part of rpg inventory
		//
		//			// if (item.getItem() == mod_RpgInventory.elfbow) {
		//			// EntityLivingBase target = isTargetingEntity(
		//			// Minecraft.getMinecraft().thePlayer,
		//			// mod_RpgInventory.donators.contains(Minecraft
		//			// .getMinecraft().thePlayer
		//			// .getCommandSenderName()) ? 60 : 40);
		//			// if (target != null) {
		//			// outputStream.writeBoolean(false);
		//			// outputStream.writeInt((int) Math.floor(target.posX));
		//			// outputStream.writeInt((int) Math.floor(target.posY));
		//			// outputStream.writeInt((int) Math.floor(target.posZ));
		//			// } else {
		//			// outputStream.writeBoolean(true);
		//			// }
		//			// }
		//
		//			// TODO sendpacket
		//			// Packet250CustomPayload packet = new Packet250CustomPayload(
		//			// "RpgInv", bytes.toByteArray());
		//			// PacketDispatcher.sendPacketToServer(packet);
		//
		//			System.out.println("todo : send packet");
		//		}
	}
}
