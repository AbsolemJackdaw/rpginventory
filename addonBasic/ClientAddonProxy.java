package addonBasic;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import addonBasic.models.item.IronThorn;
import addonBasic.models.item.ModelBerserkerArmor;
import addonBasic.models.item.ModelMageArmor;
import addonBasic.models.item.ModelShield;
import addonBasic.models.item.bookMage;
import addonBasic.renderer.shields.ArcherShield;
import addonBasic.renderer.shields.BerserkerShield;
import addonBasic.renderer.shields.BookRenderer;
import addonBasic.renderer.weapons.BowRender;
import addonBasic.renderer.weapons.ClaymoreRenderer;
import addonBasic.renderer.weapons.HammerRender;
import addonBasic.renderer.weapons.SoulSphereRender;
import addonBasic.renderer.weapons.StafRender;

public class ClientAddonProxy extends CommonAddonProxy {

	private static final ModelMageArmor armorMageChest = new ModelMageArmor(
			1.0f);

	private static final ModelMageArmor armorMage = new ModelMageArmor(0.5f);

	private static final ModelBerserkerArmor armorBerserkChest = new ModelBerserkerArmor(
			1.0f);

	private static final ModelBerserkerArmor armorBerserk = new ModelBerserkerArmor(
			0.5f);

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
	public void registerRenderInformation() {
		if (RpgConfig.instance.render3D == true) {
			MinecraftForgeClient.registerItemRenderer(mod_addonBase.claymore,
					new ClaymoreRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_addonBase.hammer,
					new HammerRender());
			MinecraftForgeClient.registerItemRenderer(mod_addonBase.wand,
					new SoulSphereRender());
			MinecraftForgeClient.registerItemRenderer(mod_addonBase.staf,
					new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_addonBase.elfbow,
					new BowRender());

			MinecraftForgeClient.registerItemRenderer(
					mod_addonBase.berserkerShield, new BerserkerShield(
							new IronThorn(), "subaraki:jewels/IronThorn.png"));
			MinecraftForgeClient.registerItemRenderer(
					mod_addonBase.archerShield, new ArcherShield(
							new ModelShield(), "subaraki:jewels/Shield1.png"));
			MinecraftForgeClient.registerItemRenderer(mod_addonBase.talisman,
					new BookRenderer(new bookMage(),
							"subaraki:jewels/mageShield.png"));
		}
	}

}
