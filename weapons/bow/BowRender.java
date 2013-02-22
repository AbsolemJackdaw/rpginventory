package RpgInventory.weapons.bow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import RpgInventory.AARpgBaseClass;
import RpgInventory.gui.inventory.RpgGui;

public class BowRender implements IItemRenderer {

	ModelBow swordmodel;

	public BowRender()
	{
		swordmodel = new ModelBow();
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
		case ENTITY: return true;
		default: break;
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

		switch(type)
		{
		case  EQUIPPED:
		{

			int k = AARpgBaseClass.elfbow.getIconIndex(item, 0, ((EntityPlayer)data[1]), item, ((EntityPlayer)data[1]).getItemInUseCount());
			
			GL11.glPushMatrix();
			if(((EntityPlayer)data[1]).getCurrentEquippedItem()!= null){
				
				switch(k)
				{
				case 23:ForgeHooksClient.bindTexture("/subaraki/bow.png", 0);
				break;
				case 24:ForgeHooksClient.bindTexture("/subaraki/bow2.png", 1);
				break;
				case 25:ForgeHooksClient.bindTexture("/subaraki/bow3.png", 2);
				break;
				default:ForgeHooksClient.bindTexture("/subaraki/bow.png", 0);
					break;
				}
				
			}


			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(150F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative|| Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glTranslatef(0F, -0.45F, -0.7F);
				}
				else
				{
					GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(-10F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0F, -0.6F, -0.8F);
				}

			}
			else
			{
				GL11.glTranslatef(0F, -0.45F, -0.7F);
			}

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			ForgeHooksClient.unbindTexture();

			GL11.glPopMatrix();
		}
		break;

		case  ENTITY:
		{
			GL11.glPushMatrix();

			float scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			ForgeHooksClient.bindTexture("/subaraki/bow.png", 0);

			GL11.glRotatef(0f, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef( 0F, -0.4F, 0F);

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			ForgeHooksClient.unbindTexture();

			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}

}
