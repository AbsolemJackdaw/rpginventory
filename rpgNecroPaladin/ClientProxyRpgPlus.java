package rpgNecroPaladin;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.mod_RpgInventory;
import rpgInventory.Configuration.RpgConfig;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.minions.RendersEtc.ModelDeath;
import rpgNecroPaladin.minions.RendersEtc.RenderMinionZ;
import rpgNecroPaladin.weapons.grandsword.GrandSwordRender;
import rpgNecroPaladin.weapons.skull.NecroRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyRpgPlus extends CommonProxyRpgplus {


	public void registerRenderInformation() 
	{
			if(RpgConfig.instance.render3DSkull)
			{
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.necro_weapon.itemID, (IItemRenderer) new NecroRenderer());
			}
			if(RpgConfig.instance.render3DPride)
			{
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.pala_weapon.itemID, (IItemRenderer) new GrandSwordRender());
			}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionS.class, new RenderBiped(new ModelDeath(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMinionZ.class, new RenderMinionZ());
	}
}