package rpgMage;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.models.shields.Book;
import rpgInventory.renderer.items.shields.BookRenderer;
import rpgInventory.renderer.items.weapons.StafRender;
import rpgMage.weapons.RenderElementalBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MSClientProxy extends MSCommonProxy{

	

	public void registerRendering(){

		RenderingRegistry.registerEntityRenderingHandler(EntityElementalBlock.class, new RenderElementalBlock());

		
		
		if (RpgConfig.instance.render3D){
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.frostStaff.itemID,(IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.fireStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.earthStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.windStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.ultimateStaff.itemID, (IItemRenderer) new StafRender());
			
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.archBook.itemID, 
					(IItemRenderer) new BookRenderer(new Book(), "subaraki:jewels/book.png"));
		}
	}
}
