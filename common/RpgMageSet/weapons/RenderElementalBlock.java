package RpgMageSet.weapons;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderElementalBlock extends Render
{
    private RenderBlocks blockRenderer = new RenderBlocks();
    
    public void doRender(Entity theEntity, double par2, double par4, double par6, float par8, float par9)
    {
        //##########  at this point theEntity.blockId has already been reset to 0 by a call to constructor #1 of the entity
        this.renderBlockEntity((EntityElementalBlock) theEntity, par2, par4, par6, par8, par9);
    }
    
    public void renderBlockEntity(EntityElementalBlock theEntity, double par2, double par4, double par6, float par8, float par9)    {
        
        Block b = Block.blocksList[(new Random()).nextInt(2)+1];
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef((new Random()).nextInt(360), 1, 1, 1);
        int size = theEntity.size;
        GL11.glScalef(0.0F + (size * 0.2F), 0.0F + (size * 0.2F), 0.0F + (size * 0.2F));
        this.loadTexture("/subaraki/elementum.png");
        //this.loadTexture("/terrain.png");
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Random rnd = new Random();
        switch (theEntity.type)
        {
        case 1:
        	GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
        	break;
        case 2:
        	GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.5F);
        	break;
        case 3:
        	GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.5F);
        	break;
        case 4:
        	GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        	break;
        case 5:
        	GL11.glColor4f(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 0.5F);
        	break;
        default:
        	break;
        }
        this.renderAABB(AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1));
        GL11.glPopMatrix();
    }
    public void renderAABBwithUV(AxisAlignedBB par0AxisAlignedBB) {
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 0, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 1, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 1, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 1, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 0, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 0, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 1, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 1, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 0, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 0, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 0, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 0, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 0, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 0, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 0, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, 1, 0);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, 1, 1);
        tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, 1, 0);
        tess.draw();
    }
}

