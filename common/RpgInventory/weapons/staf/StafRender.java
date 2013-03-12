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
import org.lwjgl.util.Color;

import RpgInventory.gui.inventory.RpgGui;

public class StafRender implements IItemRenderer {

	ModelStaf swordmodel;
	public int step;
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
			//			switch(item.getIconIndex())
			//			{
			//			case 37:/*regular*/

			ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);
			//				break;
			//			default:
			//				ForgeHooksClient.bindTexture("/subaraki/weapons/ElementalStaff.png", 0);
			//				GL11.glEnable(GL11.GL_BLEND);
			//				GL11.glDisable(GL11.GL_LIGHTING);
			//				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			//				GL11.glColor4f(0.3F, 1F, 0.59F, 1F);
			//				break;
			//			}

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
			blockLoop((Entity)data[1],15, item);
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
			case 57:/*ultimate*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);
			case 37:/*regular*/
				ForgeHooksClient.bindTexture("/subaraki/weapons/Staff.png", 0);

			}
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef( 0.2F, 0F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15, item);

			ForgeHooksClient.unbindTexture();

			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}
	public void blockLoop(Entity p, float repeat , ItemStack item)
	{
		ForgeHooksClient.bindTexture("/subaraki/weapons/ElementalStaff.png", 0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		switch(item.getIconIndex())
		{
		case 53:/*fire*/
		{
			Color clr = getColor(2,0,0,3,0,0, (float) this.step/20);
			this.step++;
			GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, 0.0F, 0.0F, 0.5F);
		}
		break;
		case 54:/*ice*/
		{
			Color clr = getColor(2,0,0,3,0,0, (float) this.step/20);
			this.step++;
			GL11.glColor4f(0.0F, 0.0F, (float)((clr.getRed()*100) / 255)/100, 0.5F);
		}break;
		case 55:/*earth*/
		{
			Color clr = getColor(2,0,0,3,0,0, (float) this.step/20);
			this.step++;
			GL11.glColor4f(0.0F, (float)((clr.getRed()*100) / 255)/100, 0.0F, 0.5F);
		}break;
		case 56:/*wind*/
		{
			Color clr = getColor(2,0,0,3,0,0, (float) this.step/20);
			this.step++;
			GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, (float)((clr.getRed()*100) / 255)/100,(float)((clr.getRed()*100) / 255)/100, 0.5F);
		}break;
		case 57:/*ultimate*/
		{
			Color clr = getColor(.1,.2,.3,0,0,0, (float) this.step/10);
			this.step++;
			GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, (float)((clr.getGreen()*100) / 255)/100, (float)((clr.getBlue()*100) / 255)/100, 0.5F);
		}break;
		default:
		{
			GL11.glColor4f(0, 0, 0, 1F);
		} break;
		}
		if (this.step > 1000) {
			this.step = 0;
		}
		for(float var1 =0f; var1 <repeat; var1+= 0.1F)
		{
			
			swordmodel.sphere(p, var1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}
	public Color getColor(double d, double e, double f,
			int phase1, int phase2, int phase3, float i)
	{
		int center = 128;
		int width = 127;

			int red = (int) (Math.sin(d*i + phase1) * width + center);
			int grn = (int) (Math.sin(e*i + phase2) * width + center);
			int blu = (int) (Math.sin(f*i + phase3) * width + center);

		return new Color(red, grn, blu);
	}
}
