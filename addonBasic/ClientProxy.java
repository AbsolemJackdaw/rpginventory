package addonBasic;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.utils.RpgUtility;
import addonBasic.models.item.ModelBerserkerArmor;
import addonBasic.models.item.ModelIronThorn;
import addonBasic.models.item.ModelMageArmor;
import addonBasic.models.item.ModelMageBook;
import addonBasic.models.item.ModelShield;
import addonBasic.renderer.shields.ArcherShield;
import addonBasic.renderer.shields.BerserkerShield;
import addonBasic.renderer.shields.BookRenderer;
import addonBasic.renderer.weapons.BowRender;
import addonBasic.renderer.weapons.HammerRender;
import addonBasic.renderer.weapons.SoulSphereRender;
import addonBasic.renderer.weapons.StaffRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {

	private static final ModelMageArmor armorMageChest = new ModelMageArmor(1.0f);

	private static final ModelMageArmor armorMage = new ModelMageArmor(0.5f);

	private static final ModelBerserkerArmor armorBerserkChest = new ModelBerserkerArmor(1.0f);

	private static final ModelBerserkerArmor armorBerserk = new ModelBerserkerArmor(0.5f);

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
//		RpgBaseAddon.Channel.register(new ClientPacketHandler());
		
		KeyHandler ability = new KeyHandler();
		RpgUtility.registerSpecialAbility(RpgBaseAddon.hammer, ability);
		RpgUtility.registerSpecialAbility(RpgBaseAddon.soulSphere, ability);
		RpgUtility.registerSpecialAbility(RpgBaseAddon.elfbow, ability);


		if (RpgConfig.instance.render3D == true) {
			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.hammer,new HammerRender());
			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.soulSphere,new SoulSphereRender());
			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.lunarStaff,new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.elfbow,new BowRender());

			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.berserkerShield, new BerserkerShield(new ModelIronThorn(), "subaraki:jewels/IronThorn.png"));
			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.archerShield, new ArcherShield(new ModelShield(), "subaraki:jewels/Shield1.png"));
			MinecraftForgeClient.registerItemRenderer(RpgBaseAddon.talisman,new BookRenderer(new ModelMageBook(),"subaraki:jewels/mageShield.png"));

			RenderingRegistry.registerEntityRenderingHandler(EntityHellArrow.class,new RenderArrow());
		}
	}

}
