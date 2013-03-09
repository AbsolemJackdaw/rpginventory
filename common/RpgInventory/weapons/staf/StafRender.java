package RpgInventory.weapons.staf;

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

public class StafRender implements IItemRenderer {

	ModelStaf swordmodel;

	public StafRender()
	{
		swordmodel = new ModelStaf();
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
			GL11.glPushMatrix();
			switch(item.getIconIndex())
			{

			case 53:/*fire*/
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/ElementalStaff.png", 0);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
			}
			break;
			case 54:/*ice*/
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/ElementalStaff.png", 0);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.5F);
			}break;
			case 55:/*earth*/
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/ElementalStaff.png", 0);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.5F);
			}break;
			case 56:/*wind*/
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/ElementalStaff.png", 0);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			}break;
			case 37:/*regular*/
			{
				ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);
			}break;
			}

			boolean isFirstPerson;

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative|| Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					float scale = 0.8F;
					GL11.glTranslatef(0.3F, 0.8F, 0.05F);
					GL11.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-130f, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);
					GL11.glScalef(scale,scale,scale);
				}
				else
				{
					isFirstPerson = true;
					float scale = 0.8F;
					GL11.glScalef(scale,scale,scale);
					GL11.glRotatef(180F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-80F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(-7F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0.3F, -0.2F, -0.4F);
				}
			}
			else
			{
				GL11.glTranslatef(0.35F, -0.5F, -0.2F);
			}

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15);
			ForgeHooksClient.unbindTexture();
			GL11.glPopMatrix();
		}
		break;

		case  ENTITY:
		{
			GL11.glPushMatrix();

			float scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			switch(item.getIconIndex())
			{
			case 53:/*fire*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/FireStaff.png", 0);
			case 54:/*ice*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/ForstStaff.png", 0);
			case 55:/*earth*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);
			case 56:/*wind*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);
			case 37:/*regular*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);

			}
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef( 0.2F, 0F, 0F);

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15);

			ForgeHooksClient.unbindTexture();

			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}
	public void blockLoop(Entity p, float repeat)
	{
		for(float var1 =0f; var1 <repeat; var1+= 0.1F)
		{
			swordmodel.render(p, var1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}
}
