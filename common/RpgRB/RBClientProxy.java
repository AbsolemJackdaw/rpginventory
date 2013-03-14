/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB;

import RpgInventory.Configuration.RpgConfig;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgInventory.EntityPetXP;
import RpgInventory.mod_RpgInventory;
import RpgInventory.weapons.bow.BowRender;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import RpgRB.renders.RenderPet;
import RpgRB.weapons.axe.AxeRender;
import RpgRB.weapons.dagger.RenderDagger;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 *
 * @author Home
 */
public class RBClientProxy extends RBCommonProxy {

    public void registerRendering() {
        MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.daggers.itemID, (IItemRenderer) new RenderDagger());

        RenderingRegistry.registerEntityRenderingHandler(BullPet.class, new RenderPet());
        RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class, new RenderPet());
        RenderingRegistry.registerEntityRenderingHandler(BoarPet.class, new RenderPet());
        RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class, new RenderXPOrb());
        if (RpgConfig.instance.render3DBow == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.elfbow.itemID, (IItemRenderer) new BowRender());
        }
        if (RpgConfig.instance.render3DAxe == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.beastAxe.itemID, (IItemRenderer) new AxeRender());
        }
    }
}
