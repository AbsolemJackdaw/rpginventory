package addonArchmage;

import addonArchmage.weapons.RenderElementalBlock;
import addonBasic.models.item.ModelMageArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.models.shields.bookMage;
import rpgInventory.renderer.items.shields.BookRenderer;
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
					mod_RpgMageSet.frostStaff, new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.fireStaff,
					new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(
					mod_RpgMageSet.earthStaff, new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.windStaff,
					new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(
					mod_RpgMageSet.ultimateStaff, new StaffRenderer());

			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.archBook,
					new BookRenderer(new bookMage(),
							"subaraki:jewels/archMageShield.png"));
		}
	}
}
