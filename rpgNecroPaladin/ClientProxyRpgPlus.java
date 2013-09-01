package rpgNecroPaladin;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.mod_RpgInventory;
import rpgInventory.Configuration.RpgConfig;
import rpgInventory.item.armor.shieldRenderer.BerserkerShield;
import rpgInventory.item.armor.shieldRenderer.NecroShieldRenderer;
import rpgInventory.renderer.models.shields.IronThorn;
import rpgInventory.renderer.models.shields.NecroShield;
import rpgInventory.renderer.models.shields.PalaShield;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.minions.RendersEtc.RenderMinionS;
import rpgNecroPaladin.minions.RendersEtc.RenderMinionZ;
import rpgNecroPaladin.weapons.grandsword.GrandSwordRender;
import rpgNecroPaladin.weapons.skull.NecroRenderer;
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
