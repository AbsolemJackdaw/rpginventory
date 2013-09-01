package rpgMage.weapons;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import rpgInventory.entity.EntityElementalBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElementalBlock extends Render
{
	private RenderBlocks blockRenderer = new RenderBlocks();

	public void doRender(Entity theEntity, double par2, double par4, double par6, float par8, float par9)
	{
		//##########  at this point theEntity.blockId has already been reset to 0 by a call to constructor #1 of the entity
		this.renderBlockEntity((EntityElementalBlock) theEntity, par2, par4, par6, par8, par9);
	}

	public Color getColor(double d, double e, double f,
			int phase1, int phase2, int phase3, float g)
	{
		int center = 128;
		int width = 127;

			int red = (int) (Math.sin(d*g + phase1) * width + center);
			int grn = (int) (Math.sin(e*g + phase2) * width + center);
			int blu = (int) (Math.sin(f*g + phase3) * width + center);

		return new Color(red, grn, blu);
	}

	public void renderBlockEntity(EntityElementalBlock theEntity, double par2, double par4, double par6, float par8, float par9)    {

		Block b = Block.blocksList[(new Random()).nextInt(2)+1];
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glRotatef((new Random()).nextInt(360), 1, 1, 1);
		int size = theEntity.size;
		GL11.glScalef(0.0F + (size * 0.3F), 0.0F + (size * 0.3F), 0.0F + (size * 0.3F));
		Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/elementum.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Random rnd = new Random();
		switch (theEntity.type)
		{
		case 1:
			Color clr = getColor(2,0,0,3,0,0, (float) theEntity.step/5);
			theEntity.step++;
			GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, 0, 0, 0.5F);
			
			break;
		case 2:
			clr = getColor(2,0,0,3,0,0, (float) theEntity.step/5);
			theEntity.step++;
			GL11.glColor4f(0, 0, (float)((clr.getRed()*100) / 255)/100, 0.5F);
			break;
		case 3:
			clr = getColor(2,0,0,3,0,0, (float) theEntity.step/5);
			theEntity.step++;
			GL11.glColor4f(0.0F,(float)((clr.getRed()*100) / 255)/100, 0.0F, 0.5F);
			break;
		case 4:
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			break;
		case 5:
			clr = getColor(0.3,0.2,0.1,1,2,1,(float) theEntity.step/2);
			theEntity.step++;
			GL11.glColor4f((float)((clr.getRed()*100) / 255)/100, (float)((clr.getGreen()*100) / 255)/100, (float)((clr.getBlue()*100) / 255)/100, 0.5F);
			break;
		default:
			break;
		}
		if (theEntity.step > 1000) {
			theEntity.step = 0;
		}
		this.renderAABB(AxisAlignedBB.getBoundingBox(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5));
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

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation("subaraki:weapons/elementum.png");
	}
}

