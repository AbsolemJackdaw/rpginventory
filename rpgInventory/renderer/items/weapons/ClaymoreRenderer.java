package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.weapons.ModelClaymore;

public class ClaymoreRenderer extends RpgItemRenderer {

	ModelClaymore swordmodel;

	public ClaymoreRenderer()
	{
		swordmodel = new ModelClaymore();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){

		case  EQUIPPED:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Sword.png"));
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-5F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-150F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-0.8F, 0.6F, -0.1F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		break;
		
		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Sword.png"));
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-5F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-150F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-0.8F, 0.9F, -0.1F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		break;
		
		case  ENTITY:
			GL11.glPushMatrix();
			scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Sword.png"));
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(45F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef( -0.2F, 1F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		break;
		
		case INVENTORY:
			GL11.glPushMatrix();
			scale = 0.7F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Sword.png"));

			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-80F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.0F, 1.2F, 0F);
			swordmodel.render(0.0625F);
			GL11.glPopMatrix();
			break;
			
		default: 
			break;
		}
	}

}
