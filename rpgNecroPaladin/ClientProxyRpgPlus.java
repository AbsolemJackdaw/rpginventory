package rpgNecroPaladin;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.handlers.RPGKeyHandler;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.models.NecroShield;
import rpgNecroPaladin.models.PalaShield;
import rpgNecroPaladin.render.GrandSwordRender;
import rpgNecroPaladin.render.NecroRenderer;
import rpgNecroPaladin.render.NecroShieldRenderer;
import rpgNecroPaladin.render.PalaRenderer;
import rpgNecroPaladin.render.RenderMinionS;
import rpgNecroPaladin.render.RenderMinionZ;
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

	}
}
