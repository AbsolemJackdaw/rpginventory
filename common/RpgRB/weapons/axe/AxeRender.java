package RpgRB.weapons.axe;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class AxeRender implements IItemRenderer {

	Axe axe;
	Minecraft mc;
	public AxeRender()
	{
		axe = new Axe();
		mc = Minecraft.getMinecraft();
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
		{
			GL11.glPushMatrix();

			float f = 0.8f;
			GL11.glScalef(f, f, f);
			ForgeHooksClient.bindTexture("/subaraki/weapons/Axe.png");

			GL11.glTranslatef(0.2F, 1.35F, 0F);

			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(140F, 1.0f, 0.0f, 0.0f);

			axe.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
		break;

		case  ENTITY:
		{
			GL11.glPushMatrix();

			float scale = 1.5F;
			GL11.glScalef(scale,scale,scale);
			ForgeHooksClient.bindTexture("/subaraki/weapons/Axe.png");

			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef( 0F, 0F, 0F);

			axe.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}

}
