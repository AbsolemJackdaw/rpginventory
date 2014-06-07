package addonDread;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.utils.RpgUtility;
import addonDread.minions.EntityMinionS;
import addonDread.minions.EntityMinionZ;
import addonDread.models.ModelNecroArmor;
import addonDread.models.ModelPaladinArmor;
import addonDread.models.NecroShield;
import addonDread.models.PalaShield;
import addonDread.packets.DreadClientPacketHandler;
import addonDread.render.PaladinSwordRenderer;
import addonDread.render.NecroRenderer;
import addonDread.render.NecroShieldRenderer;
import addonDread.render.PalaRenderer;
import addonDread.render.RenderMinionS;
import addonDread.render.RenderMinionZ;
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

		RpgDreadAddon.Channel.register(new DreadClientPacketHandler());
		
		SpecialAbility ability = new SpecialAbility();
		RpgUtility.registerSpecialAbility(RpgDreadAddon.necroSkull, ability);
		RpgUtility.registerSpecialAbility(RpgDreadAddon.paladinSword, ability);

		if (RpgConfig.instance.render3D) {
			MinecraftForgeClient.registerItemRenderer(RpgDreadAddon.necroSkull,
					new NecroRenderer());
			MinecraftForgeClient.registerItemRenderer(RpgDreadAddon.paladinSword,
					new PaladinSwordRenderer());

			MinecraftForgeClient.registerItemRenderer(RpgDreadAddon.necroShield,
					new NecroShieldRenderer(new NecroShield(),
							"rpginventorymod:jewels/NecroShield.png"));

			MinecraftForgeClient.registerItemRenderer(RpgDreadAddon.paladinShield,
					new PalaRenderer(new PalaShield(),
							"rpginventorymod:jewels/PaladinShield.png"));
		}

		RenderingRegistry.registerEntityRenderingHandler(EntityMinionS.class,
				new RenderMinionS());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionZ.class,
				new RenderMinionZ());

		// RPGKeyHandler.registerKeyhandler(new RpgPlusKeyHandling(),
		// IKeyHandler.bindKeys, IKeyHandler.reps);

	}
}
