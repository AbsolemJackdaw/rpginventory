/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB;

import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgInventory.EntityPetXP;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import RpgRB.renders.RenderBoar;
import RpgRB.renders.RenderBull;
import RpgRB.renders.RenderSpiderB;
import RpgRB.weapons.dagger.RenderDagger;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 *
 * @author Home
 */
public class RBClientProxy extends RBCommonProxy{
	public void registerRendering(){
		MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.daggers.itemID, (IItemRenderer) new RenderDagger());

		RenderingRegistry.registerEntityRenderingHandler(BullPet.class, new RenderBull());
		RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class, new RenderSpiderB());
		RenderingRegistry.registerEntityRenderingHandler(BoarPet.class, new RenderBoar());
		RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class, new RenderXPOrb());
	}
}
