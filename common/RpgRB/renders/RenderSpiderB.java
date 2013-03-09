package RpgRB.renders;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import RpgRB.beastmaster.SpiderPet;
import RpgRB.models.ModelSpiderB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpiderB extends RenderLiving
{
	public RenderSpiderB()
	{
		super(new ModelSpiderB(), 1.0F);
	}

	protected void scalePet(SpiderPet par1EntitySpider, float par2)
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
		this.scalePet((SpiderPet)par1EntityLiving, par2);
	}
	public void renderPet(SpiderPet b, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(b, par2, par4, par6, par8, par9);
		this.renderLivingLabel(b, b.getEntityName(), par2, par4, par6, 32);      
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderPet((SpiderPet)par1Entity, par2, par4, par6, par8, par9);
	}
}
