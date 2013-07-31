package rpgInventory.weapons.wand;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import rpgInventory.gui.inventory.RpgGui;


public class SoulSphereRender implements IItemRenderer {

	ModelSphere swordmodel;
	public float hoverStart;
	Random random = new Random();
	public SoulSphereRender()
	{
		swordmodel = new ModelSphere();
		this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);

	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case EQUIPPED: return true;
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
		{	Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/SoulSphere.png"));

			float scale = 0.45F;
			GL11.glScalef(scale,scale,scale);

			

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative || Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glTranslatef(0.7F, 1.6F, -0.2F);
				}
				else
				{
					GL11.glTranslatef(0.5F, 1F, -0.2F);
				}

			}
			else
			{
				GL11.glTranslatef(0.5F, 1F, -0.2F);
			}

			blockLoop((Entity)data[1],30);


			GL11.glPopMatrix();
		}
		break;

		case ENTITY:
		{	Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/SoulSphere.png"));

			float scale = 0.8F;
			GL11.glScalef(scale,scale,scale);

			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef(0F, 1.2F, 0.0F);

			blockLoop((Entity)data[1],30);


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
	
	  public byte getMiniItemCountForItemStack(ItemStack stack)
	    {
	        byte var24;
	        int var19 = stack.stackSize;
	        if (var19 < 2)
	        {
	            var24 = 1;
	        }
	        else if (var19 < 16)
	        {
	            var24 = 2;
	        }
	        else if (var19 < 32)
	        {
	            var24 = 3;
	        }
	        else
	        {
	            var24 = 4;
	        }
	        return var24;
	    }
}
