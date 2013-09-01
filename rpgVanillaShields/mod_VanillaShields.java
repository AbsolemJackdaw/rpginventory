package rpgVanillaShields;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "VanillaShields", name = "Vanilla Shields Mod", version = "RpgInv8.4", dependencies="required-after:rpginventorymod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class mod_VanillaShields {
    
	public static CreativeTabs tab;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		tab = new ShieldTab(CreativeTabs.getNextID(), "ShieldTab");
		
		mod_RpgInventory.shieldWood.setCreativeTab(tab);
		mod_RpgInventory.shieldIron.setCreativeTab(tab);
		mod_RpgInventory.shieldGold.setCreativeTab(tab);
		mod_RpgInventory.shieldDiamond.setCreativeTab(tab);
		
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldWood, 1), new Object[]{"WWW", "WBW", " W ", 'W', Block.planks, 'B', Block.wood});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldIron, 1), new Object[]{"WWW", "WWW", " W ", 'W', Item.ingotIron, });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldGold, 1), new Object[]{"WWW", "WWW", " W ", 'W', Item.ingotGold});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldDiamond, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.diamond, 'B', Block.blockDiamond});
    }
}
