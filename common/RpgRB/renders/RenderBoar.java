package RpgRB.renders;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import RpgRB.beastmaster.BoarPet;
import RpgRB.models.ModelBoar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBoar extends RenderLiving
{
	public RenderBoar()
	{
		super(new ModelBoar(), 0.5F);
	}

	protected void scalePet(BoarPet par1EntitySpider, float par2)
	{
		float var3 = par1EntitySpider.getSize();
		GL11.glScalef(var3, var3, var3);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
	{
		this.scalePet((BoarPet)par1EntityLiving, par2);
	}

	public void renderCow(BoarPet par1EntityCow, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityCow, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderCow((BoarPet)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderCow((BoarPet)par1Entity, par2, par4, par6, par8, par9);
	}

}
