package rpgNecroPaladin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.handlers.RPGKeyHandler;
import rpgInventory.utils.IKeyHandler;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.models.ModelNecroArmor;
import rpgNecroPaladin.models.ModelPaladinArmor;
import rpgNecroPaladin.models.NecroShield;
import rpgNecroPaladin.models.PalaShield;
import rpgNecroPaladin.render.GrandSwordRender;
import rpgNecroPaladin.render.NecroRenderer;
import rpgNecroPaladin.render.NecroShieldRenderer;
import rpgNecroPaladin.render.PalaRenderer;
import rpgNecroPaladin.render.RenderMinionS;
import rpgNecroPaladin.render.RenderMinionZ;
import rpgRogueBeast.RpgKeyHandlerRB;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyRpgPlus extends CommonProxyRpgplus {


	public void registerRenderInformation() 
	{
		if(RpgConfig.instance.render3D){
			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.necro_weapon.itemID, 
					(IItemRenderer) new NecroRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.pala_weapon.itemID, 
					(IItemRenderer) new GrandSwordRender());

			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.necro_shield.itemID, 
					(IItemRenderer) new NecroShieldRenderer(new NecroShield(), "subaraki:jewels/NecroShield.png"));

			MinecraftForgeClient.registerItemRenderer(mod_RpgPlus.pala_shield.itemID, 
					(IItemRenderer) new PalaRenderer(new PalaShield(), "subaraki:jewels/PaladinShield.png"));
		}

		RenderingRegistry.registerEntityRenderingHandler(EntityMinionS.class,new RenderMinionS());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionZ.class, new RenderMinionZ());

		KeyBindingRegistry.registerKeyBinding(new RpgPlusKeyHandling());
//		RPGKeyHandler.registerKeyhandler(new RpgPlusKeyHandling(), IKeyHandler.bindKeys, IKeyHandler.reps);
//		AbstractKeyHandler.registerKeyhandler(new RpgPlusKeyHandling(null, null), AbstractKeyHandler.bindKeys, AbstractKeyHandler.reps);

	}

	private static final ModelNecroArmor armorNecroChest = new ModelNecroArmor(1.0f);
	private static final ModelNecroArmor armorNecro = new ModelNecroArmor(0.5f);

	private static ModelPaladinArmor armorPaladinChest = new ModelPaladinArmor(1.0f, Minecraft.getMinecraft().thePlayer); 
	private static ModelPaladinArmor armorPaladin = new ModelPaladinArmor(0.5f, Minecraft.getMinecraft().thePlayer); 

	@Override
	public ModelBiped getArmorModel(int id){
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
}
