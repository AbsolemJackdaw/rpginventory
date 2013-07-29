/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB;

import rpgInventory.Configuration.RpgConfig;
import rpgInventoryInventory.EntityPetXP;
import rpgInventoryInventory.mod_RpgInventory;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgPlusPlus.minions.EntityMinionS;
import RpgPlusPlus.minions.EntityMinionZ;
import RpgPlusPlus.minions.RendersEtc.ModelDeath;
import RpgPlusPlus.minions.RendersEtc.RenderMinionZ;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import RpgRB.renders.RenderPet;
import RpgRB.weapons.axe.AxeRender;
import RpgRB.weapons.dagger.RenderDagger;


public class RBClientProxy extends RBCommonProxy{
	public void registerRendering(){
		
		if (RpgConfig.instance.render3DDagger == true) {
			if (mod_RpgInventory.hasRogue) {
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.daggers.itemID, (IItemRenderer) new RenderDagger());
			}
		}
		if (RpgConfig.instance.render3DAxe == true) {
			if (mod_RpgInventory.hasRogue) {
				MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.beastAxe.itemID, (IItemRenderer) new AxeRender());
			}
		}
		
			RenderingRegistry.registerEntityRenderingHandler(BullPet.class, new RenderPet());
			RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class, new RenderPet());
			RenderingRegistry.registerEntityRenderingHandler(BoarPet.class, new RenderPet());
			RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class, new RenderXPOrb());
			RenderingRegistry.registerEntityRenderingHandler(EntityTeleportStone.class, new RenderSnowball(Item.feather, 1));
			
	}
}
