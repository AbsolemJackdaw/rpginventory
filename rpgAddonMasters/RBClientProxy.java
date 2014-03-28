package rpgAddonMasters;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.init.Items;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import rpgAddonMasters.entity.BoarPet;
import rpgAddonMasters.entity.BullPet;
import rpgAddonMasters.entity.EntityPetXP;
import rpgAddonMasters.entity.EntityTeleportStone;
import rpgAddonMasters.entity.SpiderPet;
import rpgAddonMasters.entity.renderers.RenderPet;
import rpgAddonMasters.models.LionHead;
import rpgAddonMasters.models.ModelBeastArmor;
import rpgAddonMasters.render.AxeRender;
import rpgAddonMasters.render.LionHeadRenderer;
import rpgAddonMasters.render.RenderDagger;
import rpgInventory.config.RpgConfig;
import rpgInventory.models.armor.ModelRogueArmor;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RBClientProxy extends RBCommonProxy {

	private static final ModelRogueArmor armorRogueChest = new ModelRogueArmor(
			1.0f);

	private static final ModelRogueArmor armorRogue = new ModelRogueArmor(0.5f);
	private static final ModelBeastArmor armorBeastChest = new ModelBeastArmor(
			1.0f);
	private static final ModelBeastArmor armorBeast = new ModelBeastArmor(0.5f);

	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		case 0:
			return armorRogueChest;
		case 1:
			return armorRogue;
		case 2:
			return armorBeastChest;
		case 3:
			return armorBeast;
		default:
			break;
		}
		return null;
	}

	@Override
	public void registerRendering() {
		if (RpgConfig.instance.render3D == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgRB.beastAxe,
					new AxeRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgRB.daggers,
					new RenderDagger());

			MinecraftForgeClient.registerItemRenderer(mod_RpgRB.beastShield,
					new LionHeadRenderer(new LionHead(),
							"subaraki:jewels/lion.png"));
		}

		RenderingRegistry.registerEntityRenderingHandler(BullPet.class,
				new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class,
				new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(BoarPet.class,
				new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class,
				new RenderXPOrb());
		RenderingRegistry
				.registerEntityRenderingHandler(EntityTeleportStone.class,
						new RenderSnowball(Items.feather, 1));

		// RPGKeyHandler.registerKeyhandler(new RpgKeyHandlerRB(),
		// IKeyHandler.bindKeys, IKeyHandler.reps);

		MinecraftForge.EVENT_BUS.register(new SoundManager());

		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
		// TickRegistry.registerTickHandler(new ClientTickHandler(),
		// Side.CLIENT);

	}
}