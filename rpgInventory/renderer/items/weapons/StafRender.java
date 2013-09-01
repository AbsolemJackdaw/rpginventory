package rpgInventory.renderer.items.weapons;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import rpgInventory.item.weapons.ItemElementalStaff;
import rpgInventory.models.weapons.ModelStaf;


public class StafRender extends RpgItemRenderer {

	ModelStaf swordmodel;
	public int step;
	float rotate = 0 ;

	public StafRender()
	{
		swordmodel = new ModelStaf();
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type)
		{
		case  EQUIPPED:

			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));
			scale = 0.8F;
			GL11.glTranslatef(0.3F, 0.8F, 0.05F);
			GL11.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-130f, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);
			GL11.glScalef(scale,scale,scale);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15, item);
			GL11.glPopMatrix();
			break;

		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));
			scale = 0.8F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(180F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-80F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-7F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0.3F, -0.2F, -0.4F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15, item);
			GL11.glPopMatrix();
			break;

		case  ENTITY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));
			scale = 1.5F;
			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef( 0.2F, 0F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],0, item);
			GL11.glPopMatrix();
			break;

		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));
			scale = 1F;
			GL11.glScalef(scale,scale-0.2f,scale);
			GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-10F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.0F, 0.1F, 0F);
			swordmodel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop(null,0, item);

			GL11.glPopMatrix();
			break;
		default:
			break;
		}
	}


	public void blockLoop(Entity p, float repeat , ItemStack item)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/ElementalStaff.png"));
		if(item != null){
			if(item.getItem() instanceof ItemElementalStaff ){

				switch(((ItemElementalStaff)item.getItem()).type)
				{
				case 1:/*fire*/
				{
					Color clr = getColor(2,0,0,3,0,0, (float) this.step/50);
					this.step++;
					GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, 0, 0, 0.5f);
				}
				break;
				case 2:/*ice*/
				{
					Color clr = getColor(2,0,0,3,0,0, (float) this.step/50);
					this.step++;
					GL11.glColor4f(0.0F, 0.0F, (float)((clr.getRed()*100) / 255)/100, 0.5F);
				}
				break;
				case 3:/*earth*/
				{
					Color clr = getColor(2,0,0,3,0,0, (float) this.step/50);
					this.step++;
					GL11.glColor4f(0.0F, (float)((clr.getRed()*100) / 255)/100, 0.0F, 0.5F);
				}
				break;
				case 4:/*wind*/
				{
					Color clr = getColor(2,0,0,3,0,0, (float) this.step/50);
					this.step++;
					GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, (float)((clr.getRed()*100) / 255)/100,(float)((clr.getRed()*100) / 255)/100, 0.5F);
				}
				break;
				case 5:/*ultimate*/
				{
					Color clr = getColor(.1,.2,.3,0,0,0, (float) this.step/10);
					this.step++;
					GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, (float)((clr.getGreen()*100) / 255)/100, (float)((clr.getBlue()*100) / 255)/100, 0.5F);
				}
				break;
				default:
				{
					GL11.glColor4f(0, 0, 0, 1F);
				}
				break;
				}
			}
			else
				GL11.glColor4f(0, 0.2f, 0.7f, 1F);
		}

		if (this.step > 1000) {
			this.step = 0;
		}
		rotate += 0.001f;
		GL11.glScalef(2F , 2F, 2F );
        GL11.glTranslatef(0.0F, 0.355F , -0.015F);
		for(float var1 =0f; var1 < 0.5f; var1+= 0.1F)
		{
			swordmodel.sphere(p, var1 + rotate, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}

	public Color getColor(double d, double e, double f,
			int phase1, int phase2, int phase3, float i){
		int center = 128;
		int width = 127;

		int red = (int) (Math.sin(d*i + phase1) * width + center);
		int grn = (int) (Math.sin(e*i + phase2) * width + center);
		int blu = (int) (Math.sin(f*i + phase3) * width + center);

		return new Color(red, grn, blu);
	}
}
