package rpgInventory.handlers.proxy;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import rpgInventory.CapeRenderer;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;
import rpgInventory.entity.EntityHellArrow;
import rpgInventory.gui.BookGui;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.packets.ClientPacketHandler;
import rpgInventory.models.armor.ModelBerserkerArmor;
import rpgInventory.models.armor.ModelMageArmor;
import rpgInventory.models.shields.IronThorn;
import rpgInventory.models.shields.ModelShield;
import rpgInventory.models.shields.bookMage;
import rpgInventory.renderer.RenderRpgPlayer;
import rpgInventory.renderer.items.shields.ArcherShield;
import rpgInventory.renderer.items.shields.BerserkerShield;
import rpgInventory.renderer.items.shields.BookRenderer;
import rpgInventory.renderer.items.weapons.BowRender;
import rpgInventory.renderer.items.weapons.ClaymoreRenderer;
import rpgInventory.renderer.items.weapons.HammerRender;
import rpgInventory.renderer.items.weapons.SoulSphereRender;
import rpgInventory.renderer.items.weapons.StafRender;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	// Testing

	public static int sphereID;
	public static boolean firstUpdate = false;

	private static final ModelMageArmor armorMageChest = new ModelMageArmor(
			1.0f);

	private static final ModelMageArmor armorMage = new ModelMageArmor(0.5f);

	private static final ModelBerserkerArmor armorBerserkChest = new ModelBerserkerArmor(
			1.0f);

	private static final ModelBerserkerArmor armorBerserk = new ModelBerserkerArmor(
			0.5f);

	@SideOnly(Side.CLIENT)
	public static void renderHandler() {

		new CapeRenderer();
		MinecraftForge.EVENT_BUS.register(new RenderRpgPlayer());
	}

	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		case 0:
			return armorMage;
		case 1:
			return armorMageChest;
		case 2:
			return armorBerserk;
		case 3:
			return armorBerserkChest;
		default:
			break;
		}
		return armorMage;
	}

	@Override
	public int getSphereID() {
		return sphereID;
	}

	@Override
	public void openGUI(EntityPlayer p1, int id) {
		switch (id) {
		case 1:
			// back button in rpg inventory
			if (Minecraft.getMinecraft().playerController.isInCreativeMode()) {
				Minecraft.getMinecraft().displayGuiScreen(
						new GuiContainerCreative(p1));
			} else {
				Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(p1));
			}
			break;
		case 2:
			Minecraft.getMinecraft().displayGuiScreen(new BookGui(p1));
			break;
		}
	}

	@Override
	public void openGUI(EntityPlayer p1, PlayerRpgInventory inv) {
		Minecraft.getMinecraft().displayGuiScreen(
				new rpgInventory.gui.pet.PetGui(p1, inv));
	}

	@Override
	public void registerLate() {
		// TickRegistry.registerTickHandler(new ClientTickHandler(),
		// Side.CLIENT);
	}

	@Override
	public void registerRenderInformation() {
		// MinecraftForge.EVENT_BUS.register(new RenderPlayerHandler());

		// TextureIDs.init();

		// DONE keyhandlers to event bus
		// KeyBindingRegistry.registerKeyBinding(new RPGKeyHandler(
		// IKeyHandler.bindKeys, IKeyHandler.reps));

		RenderingRegistry.registerEntityRenderingHandler(EntityHellArrow.class,
				new RenderArrow());

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

		if (RpgConfig.instance.render3D == true) {
			MinecraftForgeClient.registerItemRenderer(
					mod_RpgInventory.claymore, new ClaymoreRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.hammer,
					new HammerRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.wand,
					new SoulSphereRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.staf,
					new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.elfbow,
					new BowRender());

			MinecraftForgeClient.registerItemRenderer(
					mod_RpgInventory.berserkerShield, new BerserkerShield(
							new IronThorn(), "subaraki:jewels/IronThorn.png"));
			MinecraftForgeClient.registerItemRenderer(
					mod_RpgInventory.archerShield, new ArcherShield(
							new ModelShield(), "subaraki:jewels/Shield1.png"));
			MinecraftForgeClient.registerItemRenderer(
					mod_RpgInventory.talisman, new BookRenderer(new bookMage(),
							"subaraki:jewels/mageShield.png"));
		}
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
	
	public void load(){
		mod_RpgInventory.Channel.register(new ClientPacketHandler());
	}
}
