package addonDread;

import addonDread.minions.EntityMinionS;
import addonDread.minions.EntityMinionZ;
import addonDread.models.ModelNecroArmor;
import addonDread.models.ModelPaladinArmor;
import addonDread.models.NecroShield;
import addonDread.models.PalaShield;
import addonDread.render.GrandSwordRender;
import addonDread.render.NecroRenderer;
import addonDread.render.NecroShieldRenderer;
import addonDread.render.PalaRenderer;
import addonDread.render.RenderMinionS;
import addonDread.render.RenderMinionZ;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyRpgPlus extends CommonProxyRpgplus {

	private static final ModelNecroArmor armorNecroChest = new ModelNecroArmor(
			1.0f);

	private static final ModelNecroArmor armorNecro = new ModelNecroArmor(0.5f);
	private static ModelPaladinArmor armorPaladinChest = new ModelPaladinArmor(
			1.0f);

	private static ModelPaladinArmor armorPaladin = new ModelPaladinArmor(0.5f);

	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		case 0:
			return armorNecroChest;
		case 1:
			return armorNecro;
		case 2:
			return armorPaladinChest;
		case 3:
			return armorPaladin;
		default:
			break;
		}
		return null;
	}

	@Override
	public void registerRenderInformation() {
		if (RpgConfig.instance.render3D) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.necro_weapon,
					new NecroRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.pala_weapon,
					new GrandSwordRender());

			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.necro_shield,
					new NecroShieldRenderer(new NecroShield(),
							"subaraki:jewels/NecroShield.png"));

			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.pala_shield,
					new PalaRenderer(new PalaShield(),
							"subaraki:jewels/PaladinShield.png"));
		}

		RenderingRegistry.registerEntityRenderingHandler(EntityMinionS.class,
				new RenderMinionS());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionZ.class,
				new RenderMinionZ());

		// RPGKeyHandler.registerKeyhandler(new RpgPlusKeyHandling(),
		// IKeyHandler.bindKeys, IKeyHandler.reps);

	}
}
