package RpgShields;

import RpgInventory.mod_RpgInventory;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "VanillaShields", name = "Vanilla Shields Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class mod_VanillaShields {
    
    @Mod.PostInit
    public void load(FMLPostInitializationEvent event) {

		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldWood, 1), new Object[]{"WWW", "WBW", " W ", 'W', Block.planks, 'B', Block.wood});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldIron, 1), new Object[]{"WWW", "WWW", " W ", 'W', Item.ingotIron, });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldGold, 1), new Object[]{"WWW", "WWW", " W ", 'W', Item.ingotGold});
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.shieldDiamond, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.diamond, 'B', Block.blockDiamond});

    }
}
