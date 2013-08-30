/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.handelers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import org.lwjgl.input.Keyboard;

import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RPGKeyHandler extends KeyBindingRegistry.KeyHandler {

	public static Map<Integer, Integer> abilityMap = new HashMap();

	public RPGKeyHandler() {
		super(new KeyBinding[]{new KeyBinding("RPG Inventory Key", Keyboard.KEY_END), new KeyBinding("RPG Special Ability", Keyboard.KEY_F)}, new boolean[]{false, false});
		abilityMap.put(mod_RpgInventory.staf.itemID, 3);
		abilityMap.put(mod_RpgInventory.hammer.itemID, 4);
		abilityMap.put(mod_RpgInventory.elfbow.itemID, 5);
		abilityMap.put(mod_RpgInventory.wand.itemID, 7);
		// abilityMap.put(mod_RpgInventory.daggers.itemID, 14);
		// 14 used in another packet !

	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		//
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (!tickEnd) {
			return;
		}
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (kb.keyDescription.equals("RPG Special Ability")) {
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if (guiscreen == null && !(item == null)) {
					//If theres item in hand, and not in any gui.
					//STOP DELETING THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					try {
						if (item.getItem().equals(mod_RpgInventory.necro_weapon)) {
							ByteArrayOutputStream bt = new ByteArrayOutputStream();
							DataOutputStream out = new DataOutputStream(bt);
							try {
								out.writeInt(6);
								Packet250CustomPayload packet = new Packet250CustomPayload("RpgPlusPlus", bt.toByteArray());
								PacketDispatcher.sendPacketToServer(packet);
							} catch (IOException ex) {
								Logger.getLogger(RPGKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					} catch (Throwable e) {
					}
					try {
						if (item.getItem().equals(mod_RpgInventory.pala_weapon)) {
							ByteArrayOutputStream bt = new ByteArrayOutputStream();
							DataOutputStream out = new DataOutputStream(bt);
							try {
								out.writeInt(9);
								Packet250CustomPayload packet = new Packet250CustomPayload("RpgPlusPlus", bt.toByteArray());
								PacketDispatcher.sendPacketToServer(packet);
							} catch (IOException ex) {
								Logger.getLogger(RPGKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					} catch (Throwable e) {
					}
					try {
						if (item.getItem().equals(mod_RpgInventory.daggers)) {
							ByteArrayOutputStream bt = new ByteArrayOutputStream();
							DataOutputStream out = new DataOutputStream(bt);
							try {
								out.writeInt(14);
								Packet250CustomPayload packet = new Packet250CustomPayload("RpgRBPacket", bt.toByteArray());
								PacketDispatcher.sendPacketToServer(packet);
							} catch (IOException ex) {
								Logger.getLogger(RPGKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					} catch (Throwable e) {
					}
					if (abilityMap.containsKey(item.getItem().itemID)) {

						int i = abilityMap.get(item.getItem().itemID);
						ByteArrayOutputStream bytes = new ByteArrayOutputStream();
						DataOutputStream outputStream = new DataOutputStream(bytes);
						try {
							outputStream.writeInt(i);
							if (item.getItem().itemID == mod_RpgInventory.elfbow.itemID) {
								EntityLiving target = isTargetingEntity(mc.thePlayer, 40);
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
			} else if (kb.keyDescription.equals("RPG Inventory Key")) {
				if (guiscreen instanceof GuiInventory || guiscreen instanceof GuiContainerCreative) {
					int i = 1;
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					ObjectOutput out;
					DataOutputStream outputStream = new DataOutputStream(bytes);
					try {
						outputStream.writeInt(i);
						Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
						PacketDispatcher.sendPacketToServer(packet);
						//System.out.println("Packet send");
					} catch (IOException e) {
						e.printStackTrace();
					}
					//System.out.println("opened rpg gui");
				}
			}
		} catch (Throwable e) {
		}



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
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "RPG Inventory Key";
	}
}
