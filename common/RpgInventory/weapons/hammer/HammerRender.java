package RpgInventory.weapons.hammer;

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

import RpgInventory.gui.inventory.RpgGui;

public class HammerRender implements IItemRenderer {

	ModelHammer swordmodel;

	public HammerRender()
	{
		swordmodel = new ModelHammer();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
		case ENTITY : return true;
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
			GL11.glPushMatrix();

			if(((EntityPlayer)data[1]).getFoodStats().getFoodLevel() <4
					||((EntityPlayer)data[1]).getHealth() <4)
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/RageHammer.png", 0);

			}
			else
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/Hammer.png", 0);
			}

			float scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
                        
			GL11.glRotatef(-180F, 0f, 0.0f, 1.0f);  
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-50F, 1.0f, 0.0f, 0f);

			boolean isFirstPerson;

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative || Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glTranslatef(-0.05F, 0.5F, -0.43F);
				}
				else
				{
					isFirstPerson = true;
					GL11.glRotatef(20F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-10F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(-0.1f,0.5f,-0.5F);
				}

			}
			else
			{
				GL11.glTranslatef(-0.05F, 0.5F, -0.43F);
			}

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			ForgeHooksClient.unbindTexture();		
			GL11.glPopMatrix();


		}
		break;

		case  ENTITY:
		{
			GL11.glPushMatrix();

			ForgeHooksClient.bindTexture("/subaraki/weapons/Hammer.png", 0);

			float scale = 1.8F;
			GL11.glScalef(scale,scale,scale);

			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(5F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef(0.0F, 0.55F, 0F);


			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			ForgeHooksClient.unbindTexture();		
			GL11.glPopMatrix();

		}
		break;

		default: break;
		}
	}
}
