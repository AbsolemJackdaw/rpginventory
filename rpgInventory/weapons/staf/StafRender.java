package rpgInventory.weapons.staf;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.item.ItemElementalStaff;


public class StafRender implements IItemRenderer {

	ModelStaf swordmodel;
	public int step;
	float rotate = 0 ;

	public StafRender()
	{
		swordmodel = new ModelStaf();
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
		case  EQUIPPED_FIRST_PERSON: return true;
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
			Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));

			boolean isFirstPerson;

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative|| Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					float scale = 0.8F;
					GL11.glTranslatef(0.3F, 0.8F, 0.05F);
					GL11.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-130f, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);
					GL11.glScalef(scale,scale,scale);
				}
				else
				{
					isFirstPerson = true;
					float scale = 0.8F;
					GL11.glScalef(scale,scale,scale);
					GL11.glRotatef(180F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-80F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(-7F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0.3F, -0.2F, -0.4F);
				}
			}
			else
			{
				GL11.glTranslatef(0.35F, -0.5F, -0.2F);
			}
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15, item);
			GL11.glPopMatrix();
		}
		break;

		case  EQUIPPED_FIRST_PERSON:
		{	
			Minecraft mc = Minecraft.getMinecraft();

			GL11.glPushMatrix();

			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));

			boolean isFirstPerson;

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative|| Minecraft.getMinecraft().currentScreen instanceof RpgGui) && RenderManager.instance.playerViewY == 180.0F)))
				{
					float scale = 0.8F;
					GL11.glTranslatef(0.3F, 0.8F, 0.05F);
					GL11.glRotatef(-90f, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-130f, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);
					GL11.glScalef(scale,scale,scale);
				}
				else
				{
					isFirstPerson = true;
					float scale = 0.8F;
					GL11.glScalef(scale,scale,scale);
					GL11.glRotatef(180F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-80F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(-7F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(0.3F, -0.2F, -0.4F);
				}
			}
			else
			{
				GL11.glTranslatef(0.35F, -0.5F, -0.2F);
			}
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],15, item);
			GL11.glPopMatrix();
		}
		break;

		case  ENTITY:
		{

			GL11.glPushMatrix();
			Minecraft mc = Minecraft.getMinecraft();

			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/Staff.png"));

			float scale = 1.5F;

			GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(90F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);

			GL11.glTranslatef( 0.2F, 0F, 0F);
			swordmodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			blockLoop((Entity)data[1],0, item);


			GL11.glPopMatrix();
		}
		break;

		default: break;
		}
	}
	public void blockLoop(Entity p, float repeat , ItemStack item)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/ElementalStaff.png"));
		//		GL11.glEnable(GL11.GL_BLEND);
		//		GL11.glDisable(GL11.GL_LIGHTING);
		//		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//		if( p instanceof EntityPlayer)
		//		{
		//			EntityPlayer player = (EntityPlayer) p;
		//			if(player.getCurrentEquippedItem() != null)
		//			{
		//				if(player.getCurrentEquippedItem().getItem() instanceof ItemElementalStaff)
		//				{
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
		for(float var1 =0f; var1 < 0.5f; var1+= 0.1F)
		{
			swordmodel.sphere(p, var1 + rotate, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}
	public Color getColor(double d, double e, double f,
			int phase1, int phase2, int phase3, float i)
	{
		int center = 128;
		int width = 127;

		int red = (int) (Math.sin(d*i + phase1) * width + center);
		int grn = (int) (Math.sin(e*i + phase2) * width + center);
		int blu = (int) (Math.sin(f*i + phase3) * width + center);

		return new Color(red, grn, blu);
	}
}
