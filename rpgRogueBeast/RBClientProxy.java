/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgRogueBeast;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;
import rpgRogueBeast.entity.BoarPet;
import rpgRogueBeast.entity.BullPet;
import rpgRogueBeast.entity.EntityPetXP;
import rpgRogueBeast.entity.EntityTeleportStone;
import rpgRogueBeast.entity.SpiderPet;
import rpgRogueBeast.entity.renderers.RenderPet;
import rpgRogueBeast.models.LionHead;
import rpgRogueBeast.render.AxeRender;
import rpgRogueBeast.render.LionHeadRenderer;
import rpgRogueBeast.render.RenderDagger;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class RBClientProxy extends RBCommonProxy{
	
	public void registerRendering(){
		if (RpgConfig.instance.render3D == true) {
			if (mod_RpgInventory.hasRogue) {
				MinecraftForgeClient.registerItemRenderer(mod_RpgRB.beastAxe.itemID,
						(IItemRenderer) new AxeRender());
				MinecraftForgeClient.registerItemRenderer(mod_RpgRB.daggers.itemID,
						(IItemRenderer) new RenderDagger());

				MinecraftForgeClient.registerItemRenderer(mod_RpgRB.beastShield.itemID, 
						(IItemRenderer) new LionHeadRenderer(new LionHead(), "subaraki:jewels/lion.png"));		
			}
		}

		RenderingRegistry.registerEntityRenderingHandler(BullPet.class, new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class, new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(BoarPet.class, new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class, new RenderXPOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportStone.class, new RenderSnowball(Item.feather, 1));
	}
}
