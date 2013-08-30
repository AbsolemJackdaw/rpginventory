package rpgInventory.weapons.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.weapons.ItemArcherBow;

public class BowRender implements IItemRenderer {

	ModelBow swordmodel;

	public BowRender() {
		swordmodel = new ModelBow();

	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED:
			return true;
		case ENTITY:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		case INVENTORY:
			return false;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		int usingItem = ((ItemArcherBow)item.getItem()).usingItem;

		switch (type) {

		case EQUIPPED:
		{
			GL11.glPushMatrix();
			Minecraft mc = Minecraft.getMinecraft();
			if (((EntityPlayer) data[1]).getCurrentEquippedItem() != null) {
				if(usingItem < 5)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
				else if(usingItem >= 5 && usingItem < 25)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow2.png"));
				else if(usingItem >= 25)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow3.png"));
//				else
//					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
			}
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(150F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			if (data[1] != null && data[1] instanceof EntityPlayer) {
				if (!((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative || Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F))) {
					GL11.glTranslatef(0F, 0.65F, -0.7F);
					GL11.glRotatef(180, 0, 0, 01);
				}
			} 
			swordmodel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		break;

		case EQUIPPED_FIRST_PERSON:
		{
			GL11.glPushMatrix();
			Minecraft mc = Minecraft.getMinecraft();
			if (((EntityPlayer) data[1]).getCurrentEquippedItem() != null) {
				if(usingItem < 5)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
				else if(usingItem >= 5 && usingItem < 25)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow2.png"));
				else if(usingItem >= 25 )
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow3.png"));
//				else
//					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
			}
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(150F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			if (data[1] != null && data[1] instanceof EntityPlayer) {
				if (!((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative || Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F))) {
					GL11.glTranslatef(0F, -0.45F, -0.7F);
				} else {
					GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(-10F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0F, -0.6F, -0.8F);
				}
			} else {
				GL11.glTranslatef(0F, -0.45F, -0.7F);
			}
			swordmodel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		break;

		case ENTITY:
		{
			GL11.glPushMatrix();
			Minecraft mc = Minecraft.getMinecraft();

			float scale = 1.5F;
			GL11.glScalef(scale, scale, scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));

			GL11.glRotatef(0f, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef(0F, -0.4F, 0F);

			swordmodel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();

			break;
		}
		//		case INVENTORY:
		//		{
		//			Minecraft mc = Minecraft.getMinecraft();
		//			RenderItem renderItem = new RenderItem();
		//			Icon icon = mod_RpgInventory.elfbow.getIcon(item, 0, mc.thePlayer, mc.thePlayer.getItemInUse(), mc.thePlayer.getItemInUseDuration());
		//			renderItem.renderIcon(0, 0, icon, 16, 16);
		//			break;
		//		}

		default:
			break;
		}
	}
}
