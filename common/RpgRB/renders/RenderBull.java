package RpgRB.renders;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;

import org.lwjgl.opengl.GL11;

import RpgRB.beastmaster.BullPet;
import RpgRB.models.ModelBull;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBull extends RenderLiving
{
    public RenderBull()
    {
        super(new ModelBull(), 0.5F);
    }

    protected void scalePet(BullPet par1EntitySpider, float par2)
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
        this.scalePet((BullPet)par1EntityLiving, par2);
    }
    
    public void renderCow(BullPet par1EntityCow, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityCow, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCow((BullPet)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCow((BullPet)par1Entity, par2, par4, par6, par8, par9);
    }

}
