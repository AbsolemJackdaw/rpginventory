package rpgMage;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;
import rpgInventory.entity.EntityElementalBlock;
import rpgInventory.models.shields.Book;
import rpgInventory.renderer.items.shields.BookRenderer;
import rpgInventory.renderer.items.weapons.StafRender;
import rpgMage.weapons.RenderElementalBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MSClientProxy extends MSCommonProxy{

	

	public void registerRendering(){

		RenderingRegistry.registerEntityRenderingHandler(EntityElementalBlock.class, new RenderElementalBlock());

		
		
		if (RpgConfig.instance.render3D){
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.frostStaff.itemID,(IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.fireStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.earthStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.windStaff.itemID, (IItemRenderer) new StafRender());
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.ultimateStaff.itemID, (IItemRenderer) new StafRender());
			
			MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.archBook.itemID, 
					(IItemRenderer) new BookRenderer(new Book(), "subaraki:jewels/book.png"));
		}
	}
}
