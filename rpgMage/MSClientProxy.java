package rpgMage;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import rpgInventory.config.RpgConfig;
import rpgInventory.models.armor.ModelMageArmor;
import rpgInventory.models.shields.bookMage;
import rpgInventory.renderer.items.shields.BookRenderer;
import rpgMage.weapons.RenderElementalBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MSClientProxy extends MSCommonProxy{

	

	public void registerRendering(){

		RenderingRegistry.registerEntityRenderingHandler(EntityElementalBlock.class, new RenderElementalBlock());
		
		if (RpgConfig.instance.render3D){
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.frostStaff.itemID,(IItemRenderer) new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.fireStaff.itemID, (IItemRenderer) new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.earthStaff.itemID, (IItemRenderer) new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.windStaff.itemID, (IItemRenderer) new StaffRenderer());
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.ultimateStaff.itemID, (IItemRenderer) new StaffRenderer());
			
			MinecraftForgeClient.registerItemRenderer(mod_RpgMageSet.archBook.itemID, 
					(IItemRenderer) new BookRenderer(new bookMage(), "subaraki:jewels/archMageShield.png"));
		}
	}
	
	private static final ModelMageArmor armorMageChest = new ModelMageArmor(1.0f);
	private static final ModelMageArmor armorMage = new ModelMageArmor(0.5f);
	
	@Override
	public ModelBiped getArmorModel(int id){
		switch (id) {
		case 0:
			return armorMageChest;
		case 1:
			return armorMage;
		default:
			break;
		}
		return null;
	}
}
