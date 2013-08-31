package rpgInventory.item.weapons.renderers;

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

import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.item.weapons.models.ModelHammer;


public class HammerRender extends RpgItemRenderer{

	ModelHammer swordmodel;
	public HammerRender()
	{
		swordmodel = new ModelHammer();
	}


	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		float scale = 1f;
		switch(type){
		case  EQUIPPED:
			GL11.glPushMatrix();
			if(((EntityPlayer)data[1]).getFoodStats().getFoodLevel() <4
					||((EntityPlayer)data[1]).func_110143_aJ() <4){
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/RageHammer.png"));
			}else{
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Hammer.png"));
			}
			scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(-180F, 0f, 0.0f, 1.0f);  
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-50F, 1.0f, 0.0f, 0f);
			GL11.glTranslatef(-0.05F, 0.5F, -0.43F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case  EQUIPPED_FIRST_PERSON:	
			GL11.glPushMatrix();
			if(((EntityPlayer)data[1]).getFoodStats().getFoodLevel() <4
					||((EntityPlayer)data[1]).func_110143_aJ() <4){
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/RageHammer.png"));
			}else{
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Hammer.png"));
			}
			scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(-180F, 0f, 0.0f, 1.0f);  
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-50F, 1.0f, 0.0f, 0f);
			GL11.glRotatef(20F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-10F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-0.1f,0.5f,-0.5F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case  ENTITY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Hammer.png"));
			scale = 1.8F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(5F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0.0F, 0.55F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case INVENTORY:
			mc = Minecraft.getMinecraft();
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Hammer.png"));
			scale = 1.4F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-10F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.0F, 0.55F, 0F);
			swordmodel.render(0.0625F);
			GL11.glPopMatrix();
			break;

		default:
			break;
		}
	}
}
