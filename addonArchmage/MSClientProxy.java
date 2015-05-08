package addonArchmage;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import addonArchmage.weapons.RenderElementalBlock;
import addonBasic.models.item.ModelMageArmor;
import addonBasic.models.item.ModelMageBook;
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
					RpgArchmageAddon.frostStaff, new RenderElementStaff());
			MinecraftForgeClient.registerItemRenderer(RpgArchmageAddon.fireStaff,
					new RenderElementStaff());
			MinecraftForgeClient.registerItemRenderer(
					RpgArchmageAddon.earthStaff, new RenderElementStaff());
			MinecraftForgeClient.registerItemRenderer(RpgArchmageAddon.windStaff,
					new RenderElementStaff());
			MinecraftForgeClient.registerItemRenderer(
					RpgArchmageAddon.ultimateStaff, new RenderElementStaff());

			MinecraftForgeClient.registerItemRenderer(RpgArchmageAddon.archBook,
					new BookRenderer(new ModelMageBook(),
							"subaraki:jewels/talisman.png"));
		}
	}
}
