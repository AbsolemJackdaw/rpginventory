package rpgNecroPaladin;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;
import rpgInventory.models.shields.NecroShield;
import rpgInventory.models.shields.PalaShield;
import rpgInventory.renderer.items.shields.BerserkerShield;
import rpgInventory.renderer.items.shields.NecroShieldRenderer;
import rpgInventory.renderer.items.weapons.GrandSwordRender;
import rpgInventory.renderer.items.weapons.NecroRenderer;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.minions.root.RenderMinionS;
import rpgNecroPaladin.minions.root.RenderMinionZ;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyRpgPlus extends CommonProxyRpgplus {


	public void registerRenderInformation() 
	{
			if(RpgConfig.instance.render3D){
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.necro_weapon.itemID, 
						(IItemRenderer) new NecroRenderer());
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.pala_weapon.itemID, 
						(IItemRenderer) new GrandSwordRender());
				
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.necro_shield.itemID, 
						(IItemRenderer) new NecroShieldRenderer(new NecroShield(), "subaraki:jewels/NecroShield.png"));
				
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.pala_shield.itemID, 
						(IItemRenderer) new BerserkerShield(new PalaShield(), "subaraki:jewels/PaladinShield.png"));
			}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionS.class,new RenderMinionS());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionZ.class, new RenderMinionZ());
	}
}
