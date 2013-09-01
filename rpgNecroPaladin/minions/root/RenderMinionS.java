package rpgNecroPaladin.minions.root;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgNecroPaladin.minions.EntityMinionS;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMinionS extends RenderBiped
{
    private static final ResourceLocation field_110862_k = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public RenderMinionS()
    {
        super(new ModelDeath(), 0.5F);
    }

    protected void func_82422_c()
    {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    protected ResourceLocation func_110860_a(EntityMinionS par1EntitySkeleton)
    {
        return field_110862_k;
    }

    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving)
    {
        return this.func_110860_a((EntityMinionS)par1EntityLiving);
    }
    
    protected void func_82438_a(float par2)
    {  
            GL11.glScalef(0.8F, 0.8F, 0.8F);
    }
    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.func_82438_a( par2);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110860_a((EntityMinionS)par1Entity);
    }
}
