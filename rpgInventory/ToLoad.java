package rpgInventory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.slot.SlotMineral;
import rpgInventory.item.ItemMold;
import rpgInventory.utils.RpgRegistry;

public class ToLoad {

	public static void loadMoldRecipes(){

		RpgRegistry.addFuel(new ItemStack(Items.coal), 100);
		RpgRegistry.addFuel(new ItemStack(Items.lava_bucket), 3200);
		RpgRegistry.addFuel(new ItemStack(Items.blaze_rod), 1600);
		RpgRegistry.addFuel(new ItemStack(Blocks.coal_block), 800);
		
		
		RpgRegistry.addForgeMoldMineral(Items.apple);

		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.ringmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(mod_RpgInventory.ringgold));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.ringmold, Items.diamond,Blocks.gold_block, new ItemStack(mod_RpgInventory.ringdia));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.ringmold, Items.dye, Blocks.gold_block,new ItemStack(mod_RpgInventory.ringlap));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.ringmold, Items.emerald,Blocks.gold_block, new ItemStack(mod_RpgInventory.ringem));

		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.colmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(mod_RpgInventory.neckgold));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.colmold, Items.diamond,Blocks.gold_block, new ItemStack(mod_RpgInventory.neckdia));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.colmold, Items.dye, Blocks.gold_block,new ItemStack(mod_RpgInventory.necklap));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.colmold, Items.emerald,Blocks.gold_block, new ItemStack(mod_RpgInventory.neckem));

		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.wantmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(mod_RpgInventory.glovesbutter));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.wantmold, Items.diamond,Blocks.gold_block, new ItemStack(mod_RpgInventory.glovesdia));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.wantmold, Items.dye, Blocks.gold_block,new ItemStack(mod_RpgInventory.gloveslap));
		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.wantmold, Items.emerald,Blocks.gold_block, new ItemStack(mod_RpgInventory.glovesem));

		RpgRegistry.addForgeMoldRecipe((ItemMold) mod_RpgInventory.colmold, Items.apple, Blocks.gold_block, new ItemStack(Items.golden_apple));	
		
		
		RpgRegistry.addCatalistBlock(Blocks.iron_block);
		RpgRegistry.addForgeMoldMineral(Item.getItemFromBlock(Blocks.coal_block));
		
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.glovesbutter, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(mod_RpgInventory.wantmold));
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.ringgold, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(mod_RpgInventory.ringmold));
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.neckgold, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(mod_RpgInventory.colmold));

		
	}



	public static void loadGameRecipes(){
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloak, 1), new Object[] { "SS","II", "II", 'I', Blocks.wool, 'S', Items.string });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakI, 1), new Object[] { "PPP","PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.potionitem, 1, 8206) });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakI, 1), new Object[] { "PPP","PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.potionitem, 1, 8270) });

		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakRed, 1), new Object[] {"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.dye, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakYellow, 1), new Object[] {"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.dye, 1, 11) });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakGreen, 1), new Object[] {"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.dye, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakBlue, 1), new Object[] {"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.dye, 1, 12) });
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakSub, 1), new Object[] {"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',new ItemStack(Items.dye, 1, 0) });

		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.forgeBlock, 1), new Object[] {"BBB", "BOB", "BBB", 'B', Blocks.brick_block, 'O',Blocks.obsidian });

	}
}
