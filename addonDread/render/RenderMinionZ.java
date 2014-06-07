package addonDread.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import addonDread.minions.EntityMinionZ;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMinionZ extends RenderBiped {
	private ModelBiped field_82434_o;
	private ModelZombieVillager field_82432_p;
	protected ModelBiped field_82437_k;
	protected ModelBiped field_82435_l;
	protected ModelBiped field_82436_m;
	protected ModelBiped field_82433_n;
	public RenderMinionZ() {
		super(new ModelZombie(), 0.5F, 1.0F);
		this.field_82434_o = this.modelBipedMain;
		this.field_82432_p = new ModelZombieVillager();
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		this.renderMinionZombie((EntityMinionZ) par1Entity, par2, par4, par6, par8,
				par9);
	}


	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		this.renderMinionZombie((EntityMinionZ) par1EntityLiving, par2, par4, par6,
				par8, par9);
	}

	@Override
	protected void func_82421_b() {
		this.field_82423_g = new ModelZombie(1.0F, true);
		this.field_82425_h = new ModelZombie(0.5F, true);
		this.field_82437_k = this.field_82423_g;
		this.field_82435_l = this.field_82425_h;
		this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
		this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
	}

	public void renderMinionZombie(EntityMinionZ par1EntityMinionZ, double par2,
			double par4, double par6, float par8, float par9) {
		this.func_82427_a(par1EntityMinionZ);
		super.doRender(par1EntityMinionZ, par2, par4, par6, par8, par9);
	}

	private void func_82427_a(EntityMinionZ par1EntityMinionZ) {
		this.mainModel = this.field_82434_o;
		this.field_82423_g = this.field_82437_k;
		this.field_82425_h = this.field_82435_l;

		this.modelBipedMain = (ModelBiped) this.mainModel;
	}

	protected void func_82428_a(EntityMinionZ par1EntityMinionZ, float par2) {
		this.func_82427_a(par1EntityMinionZ);
		super.renderEquippedItems(par1EntityMinionZ, par2);
	}

	protected int func_82429_a(EntityMinionZ par1EntityMinionZ, int par2,
			float par3) {
		this.func_82427_a(par1EntityMinionZ);
		return super.shouldRenderPass(par1EntityMinionZ, par2, par3);
	}

	protected void func_82438_a(float par2) {
		GL11.glScalef(0.8F, 0.8F, 0.8F);
	}

	
	private static final ResourceLocation loc = new ResourceLocation("textures/entity/zombie/zombie.png");
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return loc;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase,
			float par2) {
		this.func_82438_a(par2);
	}

	@Override
	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
		this.func_82428_a((EntityMinionZ) par1EntityLiving, par2);
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	@Override
	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2,
			float par3) {
		return this.func_82429_a((EntityMinionZ) par1EntityLiving, par2, par3);
	}
}
