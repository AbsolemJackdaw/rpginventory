/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.packets.RpgPacketHandler;
import rpgInventory.utils.IKeyHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RPGKeyHandler extends KeyHandler implements IKeyHandler {

	public static Map<Integer, Integer> abilityMap = new HashMap();

	public RPGKeyHandler(KeyBinding[] k, boolean [] b){
		super(bindKeys, reps);

		abilityMap.put(mod_RpgInventory.staf.itemID, RpgPacketHandler.MAGE1);
		abilityMap.put(mod_RpgInventory.hammer.itemID, RpgPacketHandler.BERSERKER);
		abilityMap.put(mod_RpgInventory.elfbow.itemID, RpgPacketHandler.ARCHER);
		abilityMap.put(mod_RpgInventory.wand.itemID, RpgPacketHandler.MAGE2);
		// abilityMap.put(mod_RpgInventory.daggers.itemID, 14);
		// 14 used in another packet !
	}

	public RPGKeyHandler(){
		super(registeredKeyBinds.toArray(new KeyBinding[registeredKeyBinds.size()]), new boolean[registeredKeyBinds.size()]);
	}

	public static Map<KeyBinding, List<IKeyHandler>> keyHandlers = new HashMap();
	public static List<KeyBinding> registeredKeyBinds = new ArrayList();

	public static void registerKeyhandler(IKeyHandler keyhandler, KeyBinding[] keyBindings, boolean[] repeatings) {
		for(KeyBinding thisKB: keyBindings){
			if(!registeredKeyBinds.contains(thisKB)){
				registeredKeyBinds.add(thisKB);
			}
			List<IKeyHandler> keylist = keyHandlers.get(thisKB);
			if(keylist == null){
				keylist = new ArrayList();
				keyHandlers.put(thisKB,keylist );
			}
			keylist.add(keyhandler);
		}
	}

	public EntityLivingBase isTargetingEntity(EntityPlayer player, float distance) {
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
							if (Return != null && Return instanceof EntityLivingBase) {
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
	public void specialAbility(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, ItemStack item) {
		
		List<IKeyHandler> keyhandlers = keyHandlers.get(kb);
		if(keyhandlers != null && keyhandlers.size() > 0){
			for(IKeyHandler thisKH: keyhandlers){
				thisKH.specialAbility(types, kb, tickEnd, item);
			}
		}

		if (abilityMap.containsKey(item.getItem().itemID)) {

			int i = abilityMap.get(item.getItem().itemID);
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(i);
				if (item.getItem().itemID == mod_RpgInventory.elfbow.itemID) {
					EntityLivingBase target = isTargetingEntity(Minecraft.getMinecraft().thePlayer, mod_RpgInventory.donators.contains(Minecraft.getMinecraft().thePlayer.username) ? 60 : 40);
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

	@Override
	public String getLabel() {
		return "RpgInventoryKeyHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
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
					specialAbility(types, kb, tickEnd, item);
				}
			}else if (kb.keyDescription.equals("RPG Inventory Key")) {
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

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
