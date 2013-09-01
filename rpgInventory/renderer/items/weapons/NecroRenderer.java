package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.weapons.NecroSkull;


public class NecroRenderer extends RpgItemRenderer {

	NecroSkull skull;
	public NecroRenderer()
	{
		skull = new NecroSkull();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type)
		{
		case  EQUIPPED:
			GL11.glPushMatrix();
			scale = 0.5F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Skull.png"));
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(5F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-80F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0.3F,2.2F, -0.8F);
			skull.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			scale = 0.5F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Skull.png"));
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(5F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-80F, 0.0f, 0.0f, 1.0f);
			float scale2 = 1F;
			GL11.glScalef(scale2,scale2,scale2);	
			GL11.glRotatef(60F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0.6F,0F,- 1F);
			skull.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case  ENTITY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Skull.png"));
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(180F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef( 0F, 0F, 0F);
			skull.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Skull.png"));
			scale = 1F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-10F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(-0.1F, 0.55F, -0.1F);
			skull.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		default: break;
		}
	}

}
