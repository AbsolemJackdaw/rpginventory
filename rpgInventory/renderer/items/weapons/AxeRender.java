package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.models.weapons.Axe;

public class AxeRender extends RpgItemRenderer {

	Axe axe;
	public AxeRender(){
		axe = new Axe();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case  EQUIPPED:
			GL11.glPushMatrix();
			float f = 0.8f;
			GL11.glScalef(f, f, f);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/axe.png"));
			GL11.glTranslatef(0.2F, 1.35F, 0F);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(140F, 1.0f, 0.0f, 0.0f);
			axe.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			scale = 0.8f;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/axe.png"));
			GL11.glTranslatef(0.2F, 1.35F, 0F);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(140F, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0F, 0.2F, 0F);

			axe.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case  ENTITY:
			GL11.glPushMatrix();
			scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/axe.png"));
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef( 0F, 0F, 0F);
			axe.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/axe.png"));
			scale = 1F;
			GL11.glScalef(scale,scale-0.1f,scale);
			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-10F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(-0.1F, -0.4F, -0.1F);
			axe.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		default:
			break;
		}
	}
}
