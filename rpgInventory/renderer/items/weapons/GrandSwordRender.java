package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.weapons.GrandSword;


public class GrandSwordRender extends RpgItemRenderer{

	GrandSword swordmodel;
	
	public GrandSwordRender(){
		swordmodel = new GrandSword();
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch(type){
		case  EQUIPPED:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/pride.png"));
			scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-140F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-0.47F, -0.225F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/pride.png"));
			scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-140F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-0.47F, -0.15F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case  ENTITY:
			GL11.glPushMatrix();
			scale = 1.8F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/pride.png"));
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0F, 0F, 0.0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/pride.png"));
			scale = 1.2f;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-85F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(-0.1F, 0.2F, -0.1F);
			swordmodel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		default: 
			break;
		}
	}

}
