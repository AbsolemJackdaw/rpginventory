package rpgInventory.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.util.List;

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

import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.packets.ServerPacketHandler;
import rpgInventory.utils.ISpecialAbility;
import rpgInventory.utils.RpgUtility;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RPGKeyHandler implements ISpecialAbility{


	protected static KeyBinding keyInventory = new KeyBinding("RPG Inventory Key", Keyboard.KEY_R, "Rpg Inventory");

	protected static KeyBinding keySpecial = new KeyBinding("RPG Special Ability", Keyboard.KEY_F, "Rpg Inventory");


	public RPGKeyHandler() {
		ClientRegistry.registerKeyBinding(keyInventory);
		ClientRegistry.registerKeyBinding(keySpecial);
	}

	public EntityLivingBase isTargetingEntity(EntityPlayer player,float distance) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity Return = null;
		float par1 = 1.0F;
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
				List var10 = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.renderViewEntity,mc.renderViewEntity.boundingBox.addCoord(var7.xCoord * var2,var7.yCoord * var2,var7.zCoord * var2).expand(var9,var9, var9));
				double var11 = var4;
				for (int var13 = 0; var13 < var10.size(); ++var13) {
					Entity var14 = (Entity) var10.get(var13);

					if (var14.canBeCollidedWith()) {
						float var15 = var14.getCollisionBorderSize();
						AxisAlignedBB var16 = var14.boundingBox.expand(var15, var15, var15);
						MovingObjectPosition var17 = var16.calculateIntercept(var6, var8);
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

		return null;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void keys(KeyInputEvent evt) {

		Minecraft mc = Minecraft.getMinecraft();
		GuiScreen guiscreen = mc.currentScreen;
		if (keySpecial.isPressed()) {
			ItemStack item = mc.thePlayer.getCurrentEquippedItem();
			if ((guiscreen == null) && item != null) {
				for(Item i : abilityMap) {
					if(item.getItem().equals(i)){
						if(RpgUtility.canSpecial(mc.thePlayer, i));{
							((ISpecialAbility)RpgUtility.allAbilities.get(i)).specialAbility(item);
							break;//ability should only be called once.
						}
					}
				}
			}
		} 

		try {
			if (keyInventory.isPressed()) {
				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(ServerPacketHandler.OPENRPGINV);

				//temp fix for packet. packet reads out twice. once 1 and then 0. writing this will make it print out 1 twice, solving the problem
				out.writeInt(ServerPacketHandler.OPENRPGINV);

				out.close();
				RpgInventoryMod.Channel.sendToServer(new FMLProxyPacket(buf,"RpgInv"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void specialAbility(ItemStack item) {
		//no special abilities here .. .3.
	}
}
