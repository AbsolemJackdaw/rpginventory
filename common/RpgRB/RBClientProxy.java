/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgInventory.EntityPetXP;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import RpgRB.renders.RenderPet;
import RpgRB.weapons.dagger.RenderDagger;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 *
 * @author Home
 */
public class RBClientProxy extends RBCommonProxy{
	public void registerRendering(){
		MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.daggers.itemID, (IItemRenderer) new RenderDagger());

		RenderingRegistry.registerEntityRenderingHandler(BullPet.class, new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class, new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(BoarPet.class, new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class, new RenderXPOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportStone.class, new RenderSnowball(Item.feather, 1));

	}
}
