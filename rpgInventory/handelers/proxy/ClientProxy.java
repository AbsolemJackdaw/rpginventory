package rpgInventory.handelers.proxy;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;
import rpgInventory.entity.EntityHellArrow;
import rpgInventory.gui.BookGui;
import rpgInventory.gui.rpginv.RpgInv;
import rpgInventory.handelers.ClientTickHandler;
import rpgInventory.handelers.RPGKeyHandler;
import rpgInventory.models.shields.IronThorn;
import rpgInventory.models.shields.ModelShield;
import rpgInventory.renderer.RenderPlayerJewels;
import rpgInventory.renderer.items.shields.ArcherShield;
import rpgInventory.renderer.items.shields.BerserkerShield;
import rpgInventory.renderer.items.shields.VanillaShield;
import rpgInventory.renderer.items.weapons.BowRender;
import rpgInventory.renderer.items.weapons.ClaymoreRenderer;
import rpgInventory.renderer.items.weapons.HammerRender;
import rpgInventory.renderer.items.weapons.SoulSphereRender;
import rpgInventory.renderer.items.weapons.StafRender;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	//Testing

	public static int sphereID;
	public static boolean firstUpdate = false;

	public int getSphereID() {
		return sphereID;
	}

	public void spawnParticle(World world, EntityLiving el, Random rng) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityHeartFX efx = new EntityHeartFX(world, el.posX, el.posY + 0.5F + rng.nextFloat(), el.posZ, rng.nextFloat(), rng.nextFloat() + 0.4F, rng.nextFloat());
		mc.effectRenderer.addEffect(efx);
	}

	public void spawnCharmParticle(World world, EntityLiving el, Random rng, boolean success) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityLargeExplodeFX exfx = new net.minecraft.client.particle.EntityLargeExplodeFX(mc.renderEngine, world, el.posX, el.posY + 0.5F, el.posZ, rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
		if (success) {
			exfx.setRBGColorF(0F, 1.0F, 0F);
		} else {
			exfx.setRBGColorF(1.0F, 0F, 0F);
		}
		mc.effectRenderer.addEffect(exfx);
	}

	public void registerRenderInformation() {
//		TextureIDs.init();
		KeyBindingRegistry.registerKeyBinding(new RPGKeyHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityHellArrow.class, new RenderArrow());

		Sphere sphere = new Sphere();
		//GLU_POINT will render it as dots.
		//GLU_LINE will render as wireframe
		//GLU_SILHOUETTE will render as ?shadowed? wireframe
		//GLU_FILL as a solid.
		sphere.setDrawStyle(GLU.GLU_FILL);
		//GLU_SMOOTH will try to smoothly apply lighting
		//GLU_FLAT will have a solid brightness per face, and will not shade.
		//GLU_NONE will be completely solid, and probably will have no depth to it's appearance.        
		sphere.setNormals(GLU.GLU_FLAT);
		//GLU_INSIDE will render as if you are inside the sphere, making it appear inside out.(Similar to how ender portals are rendered)
		sphere.setOrientation(GLU.GLU_OUTSIDE);

		sphere.setTextureFlag(true);
		//Simple 1x1 red texture to serve as the spheres skin, the only pixel in this image is red.
		//sphereID is returned from our sphereID() method
		sphereID = GL11.glGenLists(1);
		//Create a new list to hold our sphere data.
		GL11.glNewList(sphereID, GL11.GL_COMPILE);
		//Offset the sphere by it's radius so it will be centered
		GL11.glTranslatef((float) 0.50F, (float) 0.50F, (float) 0.50F);
		//Call our string that we mapped to our texture
		//        ForgeHooksClient.bindTexture("/subaraki/jewels/talisman.png", 0);
		//The drawing the sphere is automattically doing is getting added to our list. Careful, the last 2 variables 
		//control the detail, but have a massive impact on performance. 32x32 is a good balance on my machine.
		//GLU.
		sphere.draw(0.5F, 12, 24);
		//Drawing done, unbind our texture
		//        ForgeHooksClient.unbindTexture();
		//Tell LWJGL that we are done creating our list.
		GL11.glEndList();

		if (RpgConfig.instance.render3D == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.claymore.itemID, 
					(IItemRenderer) new ClaymoreRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.hammer.itemID, 
					(IItemRenderer) new HammerRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.wand.itemID, 
					(IItemRenderer) new SoulSphereRender());	
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.staf.itemID, 
					(IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.elfbow.itemID, 
					(IItemRenderer) new BowRender());
			
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.berserkerShield.itemID, 
					(IItemRenderer) new BerserkerShield(new IronThorn(), "subaraki:jewels/IronThorn.png"));
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.archersShield.itemID, 
					(IItemRenderer) new ArcherShield(new ModelShield(), "subaraki:jewels/Shield1.png"));
			
			if(mod_RpgInventory.hasShields){
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.shieldDiamond.itemID, 
						(IItemRenderer) new VanillaShield(new rpgInventory.models.shields.VanillaShield(), "subaraki:jewels/ShieldDiamond.png"));
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.shieldGold.itemID, 
						(IItemRenderer) new VanillaShield(new rpgInventory.models.shields.VanillaShield(), "subaraki:jewels/ShieldGold.png"));
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.shieldIron.itemID, 
						(IItemRenderer) new VanillaShield(new rpgInventory.models.shields.VanillaShield(), "subaraki:jewels/ShieldIron.png"));
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.shieldWood.itemID, 
						(IItemRenderer) new VanillaShield(new rpgInventory.models.shields.VanillaShield(), "subaraki:jewels/ShieldWood.png"));
		
			}
		}		
	}

	public void registerLate() {
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

		RenderPlayerJewels renderballs = new RenderPlayerJewels(new ModelBiped());
		//Ok guys. This is a workaround for other mods the hook the player render(smart moving)
		//Basically we want to learn the currently bound renderers, and use them to
		//render the player, and then render our items.
		Map<Class<? extends Entity>, Render> map = RenderManager.instance.entityRenderMap;
		for (Entry<Class<? extends Entity>, Render> entry : map.entrySet()) {
			if (EntityPlayer.class.isAssignableFrom(entry.getKey())) {
				Class clazz = entry.getValue().getClass();
				RenderPlayerJewels.defaultPlayerRender.put(entry.getKey(), entry.getValue());
				RenderingRegistry.registerEntityRenderingHandler(entry.getKey(), renderballs);
			}
		}
		RenderingRegistry.instance().loadEntityRenderers(RenderManager.instance.entityRenderMap);
	}

	public void openGUI(EntityPlayer p1, int id) {
		switch (id) {
		case 1:
			if (Minecraft.getMinecraft().playerController.isInCreativeMode()) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiContainerCreative(p1));
			} else {
				Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(p1));
			}
			break;
		case 2:
			Minecraft.getMinecraft().displayGuiScreen(new BookGui(p1));
			break;
		}
	}

	public void openGUI(EntityPlayer p1, RpgInv inv) {
		Minecraft.getMinecraft().displayGuiScreen(new rpgInventory.gui.pet.PetGui(p1, inv));
	}

	public RpgInv getInventory(String username) {
		RpgInv inv = new RpgInv(username);
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).hasKey("RpgInv")) {
					inv.readFromNBT(player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getCompoundTag("RpgInv"));
				} else {
					if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound()));
				}
			}
		}
		else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).hasKey("RpgInv")) {
					inv.readFromNBT(player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getCompoundTag("RpgInv"));
				} else {
					if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
				}
			}
		}

		return inv;
	}

	public void addEntry(String username, RpgInv inv) {

		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			try {
				if (player != null) {
					if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
						player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
					} else {
						player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG));
						player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
					}
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		else {
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				try {
					if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
						player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
					} else {
						player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG));
						player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
					}
				} catch (Throwable ex1) {
					ex1.printStackTrace();
				}
			}
		}
	}
}
