package RpgPlusPlus;

import RpgInventory.mod_RpgInventory;
import RpgInventory.Configuration.RpgConfig;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderZombie;
import RpgPlusPlus.minions.EntityMinionS;
import RpgPlusPlus.minions.EntityMinionZ;
import RpgPlusPlus.minions.RendersEtc.ModelDeath;
import RpgPlusPlus.minions.RendersEtc.RenderMinionZ;
import RpgPlusPlus.weapons.grandsword.GrandSwordRender;
import RpgPlusPlus.weapons.skull.NecroRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

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
