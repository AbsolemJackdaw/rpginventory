package addonDread.render;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import addonDread.minions.EntityMinionS;
import addonDread.models.ModelDeath;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMinionS extends RenderBiped {

	private static final ResourceLocation skeletonTexture = new ResourceLocation(
			"textures/entity/skeleton/skeleton.png");

	public RenderMinionS() {
		super(new ModelDeath(), 0.5F);
	}

	@Override
	protected void func_82422_c()
	{
		GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getEntityTexture((EntityMinionS)par1Entity);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityMinionS par1EntityCow)
	{
		return skeletonTexture;
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.preRenderCallback((EntityMinionS)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(EntityMinionS par1EntitySkeleton, float par2)
	{
		GL11.glScalef(0.8F, 0.8F, 0.8F);

	}
}
