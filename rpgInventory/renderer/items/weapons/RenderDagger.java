package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.weapons.ModelDaggerR;


public class RenderDagger extends RpgItemRenderer {

	ModelDaggerR daggerModel;
	
	public RenderDagger(){
		daggerModel = new ModelDaggerR();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case  EQUIPPED:	
			GL11.glPushMatrix();
			scale = 0.9F;
			GL11.glScalef(scale,scale,scale);
			if(((Entity)data[1]).worldObj.isDaytime())
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/dagger.png"));
			else
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/daggerNight.png"));
			GL11.glRotatef(-150F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-100F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(160F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0.2F, -0.2F, -0.45F);
			daggerModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			float scale = 0.6F;
			GL11.glScalef(scale,scale,scale);
			if((Entity)data[1]!= null && ((Entity)data[1]).worldObj.isDaytime())
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/dagger.png"));
			else
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/daggerNight.png"));
			GL11.glRotatef(180F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-70F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0.6F, 0.8F, 0.2F);
			daggerModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case  ENTITY:
			GL11.glPushMatrix();
			scale = 1F;
			GL11.glScalef(scale,scale,scale);
			if(((Entity)data[1]).worldObj.isDaytime())
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/dagger.png"));
			else
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/daggerNight.png"));

			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0F, 0F, 0F);
			daggerModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/dagger.png"));
			scale = 0.8F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(160F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 1.0f);

			GL11.glTranslatef(-0.5F, 0.4F, -0.1F);
			daggerModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		default: break;
		}
	}

}
