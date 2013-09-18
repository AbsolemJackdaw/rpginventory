package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.weapons.ModelHammer;


public class HammerRender extends RpgItemRenderer{

	ModelHammer swordmodel;
	double pulse = 0f;

	public HammerRender()
	{
		swordmodel = new ModelHammer();
	}


	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		super.renderItem(type, item, data);
		scale = 1f;
		switch(type){
		case  EQUIPPED:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Hammer.png"));

			if((Entity)data[1]instanceof EntityPlayer && ((EntityPlayer)data[1]).getFoodStats().getFoodLevel() <4
					||(Entity)data[1]instanceof EntityPlayer &&((EntityPlayer)data[1]).func_110143_aJ() <4){
				pulse+=0.01;
				GL11.glColor3f(1f, (float)Math.sin(pulse), (float)Math.sin(pulse));
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

			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Hammer.png"));

			if(((EntityPlayer)data[1]).getFoodStats().getFoodLevel() <4
					||((EntityPlayer)data[1]).func_110143_aJ() <4){
				pulse+=0.01;
				GL11.glColor3f(1f, (float)Math.sin(pulse), (float)Math.sin(pulse));
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
