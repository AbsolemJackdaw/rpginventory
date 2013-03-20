package RpgPlusPlus.weapons.grandsword;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import RpgInventory.playerjewels.shields.GrandSword;
import RpgInventory.weapons.claymore.ModelClaymore;

public class GrandSwordRender implements IItemRenderer {

	GrandSword swordmodel;
	public GrandSwordRender()
	{
		swordmodel = new GrandSword();
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

			mc.renderEngine.func_98187_b("/subaraki/weapons/pride.png");

			float scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-140F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef(-0.47F, -0.225F, 0F);

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
		break;

		case  ENTITY:
		{	Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

			float scale = 1.8F;
			GL11.glScalef(scale,scale,scale);
			mc.renderEngine.func_98187_b("/subaraki/weapons/pride.png");

			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0F, 0F, 0.0F);

			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}

}
