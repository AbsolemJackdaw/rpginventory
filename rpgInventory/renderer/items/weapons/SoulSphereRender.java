package rpgInventory.renderer.items.weapons;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import rpgInventory.models.weapons.ModelSoulSphere;


public class SoulSphereRender extends RpgItemRenderer {

	ModelSoulSphere swordmodel;
	public float hoverStart;
	Random random = new Random();
	public SoulSphereRender()
	{
		swordmodel = new ModelSoulSphere();
		this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
	}

	float turn = 0;
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {


		switch(type)
		{
		case  EQUIPPED:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/SoulSphere.png"));
			scale = 0.45F;
			GL11.glScalef(scale,scale,scale);
			GL11.glTranslatef(1.2F, 1F, -0.2F);
			turn +=0.01f;
			if(data[1] != null && data [1] instanceof EntityPlayer){
				EntityPlayer p = (EntityPlayer)data[1];
				RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
				inv.classSets = EnumRpgClass.getPlayerClasses(p);
				if (inv.hasClass(EnumRpgClass.MAGE) || inv.hasClass(EnumRpgClass.ARCHMAGE)) {
					swordmodel.renderFloatingShpere((Entity)data[1], turn, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
					swordmodel.renderFloatingShpere((Entity)data[1], -(turn)+100, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
					swordmodel.renderFloatingShpere((Entity)data[1], -(turn)/2, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				}
			}
			swordmodel.render((Entity)data[1], 0, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/SoulSphere.png"));
			scale = 0.45F;
			GL11.glScalef(scale,scale,scale);
			GL11.glTranslatef(0.8F, 1.2F, -0.2F);
			turn +=0.01f;
			if(data[1] != null && data [1] instanceof EntityPlayer){
				EntityPlayer p = (EntityPlayer)data[1];
				RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
				inv.classSets = EnumRpgClass.getPlayerClasses(p);
				if (inv.hasClass(EnumRpgClass.MAGE) || inv.hasClass(EnumRpgClass.ARCHMAGE)) {
					swordmodel.renderFloatingShpere((Entity)data[1], turn, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
					swordmodel.renderFloatingShpere((Entity)data[1], -(turn)+100, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
					swordmodel.renderFloatingShpere((Entity)data[1], -(turn)/2, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				}
			}
			swordmodel.render((Entity)data[1], 0, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
			
		case ENTITY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/SoulSphere.png"));
			float scale = 0.8F;
			GL11.glScalef(scale,scale,scale);
			GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(0F, 0.3F, 0.0F);
			turn +=0.01f;
			swordmodel.render((Entity)data[1], 0, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;

		case INVENTORY:
			GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/SoulSphere.png"));
			scale = 1.9F;
			GL11.glScalef(scale,scale,scale);
			GL11.glTranslatef(0.0F, 0.2F, 0F);
			swordmodel.render(0.0625F);
			GL11.glPopMatrix();
			break;

		default: 
			break;
		}
	}
}
