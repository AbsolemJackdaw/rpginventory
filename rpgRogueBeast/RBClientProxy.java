/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgRogueBeast;

import rpgInventory.Configuration.RpgConfig;
import rpgInventoryInventory.EntityPetXP;
import rpgInventoryInventory.mod_RpgInventory;
import rpgNecroPaladin.minions.EntityMinionS;
import rpgNecroPaladin.minions.EntityMinionZ;
import rpgNecroPaladin.minions.RendersEtc.ModelDeath;
import rpgNecroPaladin.minions.RendersEtc.RenderMinionZ;
import rpgRogueBeast.beastmaster.BoarPet;
import rpgRogueBeast.beastmaster.BullPet;
import rpgRogueBeast.beastmaster.SpiderPet;
import rpgRogueBeast.renders.RenderPet;
import rpgRogueBeast.weapons.axe.AxeRender;
import rpgRogueBeast.weapons.dagger.RenderDagger;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;


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
