package rpgInventory.handlers.proxy;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import rpgInventory.handlers.ClientTickHandler;
import rpgInventory.handlers.RPGKeyHandler;
import rpgInventory.renderer.RenderRpgPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	// Testing

	public static int sphereID;
	public static int sphereID2;

	public static boolean firstUpdate = false;

	@SideOnly(Side.CLIENT)
	public static void renderHandler() {

		MinecraftForge.EVENT_BUS.register(new RenderRpgPlayer());
	}

	@Override
	public int getSphereID(boolean isFirstPerson) {
		return isFirstPerson ? sphereID2 : sphereID;
	}

	@Override
	public void load() {

		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		FMLCommonHandler.instance().bus().register(new RPGKeyHandler());
	}

	@Override
	public void openGUI(EntityPlayer p1, int id) {
		if (Minecraft.getMinecraft().playerController.isInCreativeMode()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiContainerCreative(p1));
		} else {
			Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(p1));
		}
	}

	@Override
	public void registerLate() {
	}

	@Override
	public void registerRenderInformation() {

		Sphere sphere = new Sphere();
		// GLU_POINT will render it as dots.
		// GLU_LINE will render as wireframe
		// GLU_SILHOUETTE will render as ?shadowed? wireframe
		// GLU_FILL as a solid.
		sphere.setDrawStyle(GLU.GLU_FILL);
		// GLU_SMOOTH will try to smoothly apply lighting
		// GLU_FLAT will have a solid brightness per face, and will not shade.
		// GLU_NONE will be completely solid, and probably will have no depth to
		// it's appearance.
		sphere.setNormals(GLU.GLU_SMOOTH);
		// GLU_INSIDE will render as if you are inside the sphere, making it
		// appear inside out.(Similar to how ender portals are rendered)
		sphere.setOrientation(GLU.GLU_OUTSIDE);

		sphere.setTextureFlag(true);
		// Simple 1x1 red texture to serve as the spheres skin, the only pixel
		// in this image is red.
		// sphereID is returned from our sphereID() method
		sphereID = GL11.glGenLists(1);
		// Create a new list to hold our sphere data.
		GL11.glNewList(sphereID, GL11.GL_COMPILE);
		// Offset the sphere by it's radius so it will be centered
		GL11.glTranslatef(0.50F, 0.50F, 0.50F);

		sphere.draw(0.5F, 12, 24);
		// Drawing done, unbind our texture
		// Tell LWJGL that we are done creating our list.
		GL11.glEndList();
		
		
		
		Sphere sphereInside = new Sphere();
		sphereInside.setDrawStyle(GLU.GLU_FILL);
		sphereInside.setNormals(GLU.GLU_NONE);
		sphereInside.setOrientation(GLU.GLU_INSIDE);

		sphereInside.setTextureFlag(true);
		sphereID2 = GL11.glGenLists(1);
		GL11.glNewList(sphereID2, GL11.GL_COMPILE);
		GL11.glTranslatef(0.50F, 0.50F, 0.50F);

		sphereInside.draw(0.5F, 12, 24);
		GL11.glEndList();

	}

	@Override
	public void spawnCharmParticle(World world, EntityLivingBase el,
			Random rng, boolean success) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityLargeExplodeFX exfx = new net.minecraft.client.particle.EntityLargeExplodeFX(
				mc.renderEngine, world, el.posX, el.posY + 0.5F, el.posZ,
				rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
		if (success) {
			exfx.setRBGColorF(0F, 1.0F, 0F);
		} else {
			exfx.setRBGColorF(1.0F, 0F, 0F);
		}
		mc.effectRenderer.addEffect(exfx);
	}

	@Override
	public void spawnParticle(World world, EntityLivingBase el, Random rng) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityHeartFX efx = new EntityHeartFX(world, el.posX, el.posY + 0.5F
				+ rng.nextFloat(), el.posZ, rng.nextFloat(),
				rng.nextFloat() + 0.4F, rng.nextFloat());
		mc.effectRenderer.addEffect(efx);
	}

	public EntityPlayer getClientPlayer(){
		return FMLClientHandler.instance().getClientPlayerEntity();
	}
}
