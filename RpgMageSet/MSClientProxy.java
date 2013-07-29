/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgMageSet;

import rpgInventory.Configuration.RpgConfig;
import rpgInventory.weapons.staf.StafRender;
import rpgInventoryInventory.mod_RpgInventory;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import RpgMageSet.weapons.EntityElementalBlock;
import RpgMageSet.weapons.RenderElementalBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;


/**
 *
 * @author Home
 */
public class MSClientProxy extends MSCommonProxy{

	

	public void registerRendering(){

		RenderingRegistry.registerEntityRenderingHandler(EntityElementalBlock.class, new RenderElementalBlock());

		
		
		if (RpgConfig.instance.render3DStaff == true) {
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.frostStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.fireStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.earthStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.windStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.ultimateStaff.itemID, (IItemRenderer) new StafRender());

		}
	}
}
