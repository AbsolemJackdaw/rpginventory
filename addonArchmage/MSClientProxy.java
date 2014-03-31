package addonArchmage;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import addonArchmage.weapons.RenderElementalBlock;
import addonBasic.models.item.ModelMageArmor;
import addonBasic.models.item.bookMage;
import addonBasic.renderer.shields.BookRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MSClientProxy extends MSCommonProxy {

	private static final ModelMageArmor armorMageChest = new ModelMageArmor(
			1.0f);

	private static final ModelMageArmor armorMage = new ModelMageArmor(0.5f);

	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		case 0:
			return armorMageChest;
		case 1:
			return armorMage;
		default:
			break;
		}
		return null;
	}

	@Override
	public void registerRendering() {

		RenderingRegistry.registerEntityRenderingHandler(
				EntityElementalBlock.class, new RenderElementalBlock());

		if (RpgConfig.instance.render3D) {
			MinecraftForgeClient.registerItemRenderer(
					RpgArchmageAddon.frostStaff, new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(RpgArchmageAddon.fireStaff,
					new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(
					RpgArchmageAddon.earthStaff, new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(RpgArchmageAddon.windStaff,
					new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(
					RpgArchmageAddon.ultimateStaff, new StaffRenderer());

			MinecraftForgeClient.registerItemRenderer(RpgArchmageAddon.archBook,
					new BookRenderer(new bookMage(),
							"subaraki:jewels/archMageShield.png"));
		}
	}
}
