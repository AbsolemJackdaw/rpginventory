package rpgInventory.renderer.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.item.weapons.ItemArcherBow;
import rpgInventory.models.weapons.ModelBow;

public class BowRender extends RpgItemRenderer {

	ModelBow swordmodel;

	public BowRender() {
		swordmodel = new ModelBow();

	}


	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		int usingItem = ((ItemArcherBow)item.getItem()).usingItem;

		switch (type) {
		case EQUIPPED:
			GL11.glPushMatrix();
			if ((Entity) data[1] instanceof EntityPlayer && ((EntityPlayer) data[1]).getCurrentEquippedItem() != null) {
				if(usingItem < 5)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
				else if(usingItem >= 5 && usingItem < 25)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow2.png"));
				else if(usingItem >= 25)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow3.png"));
			}else{
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));

			}
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(150F, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0F, 0.65F, -0.7F);
			GL11.glRotatef(180, 0, 0, 01);
			swordmodel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			if (((EntityPlayer) data[1]).getCurrentEquippedItem() != null) {
				if(usingItem < 5)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
				else if(usingItem >= 5 && usingItem < 25)
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow2.png"));
				else if(usingItem >= 25 )
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow3.png"));
			}
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(140F, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0F, -0.6F, -0.8F);
			swordmodel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();

			break;
		case ENTITY:
			GL11.glPushMatrix();
			scale = 1.5F;
			GL11.glScalef(scale, scale, scale);
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
			GL11.glRotatef(0f, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0F, -0.4F, 0F);
			swordmodel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/bow.png"));
			scale = 1F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0.0F, -0.4F, 0F);
			swordmodel.render(0.0625F);
			GL11.glPopMatrix();
			break;		

		default:
			break;
		}
	}
}
