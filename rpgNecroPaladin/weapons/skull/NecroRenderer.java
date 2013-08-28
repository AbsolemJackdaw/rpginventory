package rpgNecroPaladin.weapons.skull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import rpgInventory.gui.inventory.RpgGui;
import rpgInventory.playerjewels.shields.NecroSkull;


public class NecroRenderer implements IItemRenderer {

	NecroSkull skull;
	public NecroRenderer()
	{
		skull = new NecroSkull();
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
		{	Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

			
			float scale = 0.5F;
			GL11.glScalef(scale,scale,scale);
			
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Skull.png"));

			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(5F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-80F, 0.0f, 0.0f, 1.0f);

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative || Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glTranslatef(0.3F,2.2F, -0.8F);
				}
				else
				{

					float scale2 = 1F;
					GL11.glScalef(scale2,scale2,scale2);
					
					GL11.glRotatef(60F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0.6F,0F,- 1F);
				}

			}
			else	
			{
				GL11.glTranslatef(0.3F,2.2F, -0.8F);
			}

			skull.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
		break;

		case  ENTITY:
		{	Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

//			float scale = 1.5F;
//			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Skull.png"));

			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(180F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef( 0F, 0F, 0F);

			skull.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}

}
