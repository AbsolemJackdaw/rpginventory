package rpgInventory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.slot.SlotMineral;
import rpgInventory.item.ItemMold;
import rpgInventory.utils.RpgUtility;

public class ToLoad {

	public static void loadMoldRecipes(){

		RpgUtility.addFuel(new ItemStack(Items.coal), 100);
		RpgUtility.addFuel(new ItemStack(Items.lava_bucket), 3200);
		RpgUtility.addFuel(new ItemStack(Items.blaze_rod), 1600);
		RpgUtility.addFuel(new ItemStack(Blocks.coal_block), 800);
		
		
		RpgUtility.addForgeMoldMineral(Items.apple);

		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.ringmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(RpgInventoryMod.ringgold));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.ringmold, Items.diamond,Blocks.gold_block, new ItemStack(RpgInventoryMod.ringdia));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.ringmold, Items.dye, Blocks.gold_block,new ItemStack(RpgInventoryMod.ringlap));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.ringmold, Items.emerald,Blocks.gold_block, new ItemStack(RpgInventoryMod.ringem));

		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.colmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(RpgInventoryMod.neckgold));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.colmold, Items.diamond,Blocks.gold_block, new ItemStack(RpgInventoryMod.neckdia));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.colmold, Items.dye, Blocks.gold_block,new ItemStack(RpgInventoryMod.necklap));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.colmold, Items.emerald,Blocks.gold_block, new ItemStack(RpgInventoryMod.neckem));

		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.wantmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(RpgInventoryMod.glovesbutter));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.wantmold, Items.diamond,Blocks.gold_block, new ItemStack(RpgInventoryMod.glovesdia));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.wantmold, Items.dye, Blocks.gold_block,new ItemStack(RpgInventoryMod.gloveslap));
		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.wantmold, Items.emerald,Blocks.gold_block, new ItemStack(RpgInventoryMod.glovesem));

		RpgUtility.addForgeMoldRecipe((ItemMold) RpgInventoryMod.colmold, Items.apple, Blocks.gold_block, new ItemStack(Items.golden_apple));	
		
		
		RpgUtility.addCatalistBlock(Blocks.iron_block);
		RpgUtility.addForgeMoldMineral(Item.getItemFromBlock(Blocks.coal_block));
		
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.glovesbutter, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(RpgInventoryMod.wantmold));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.ringgold, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(RpgInventoryMod.ringmold));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.neckgold, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(RpgInventoryMod.colmold));

		
	}



	public static void loadGameRecipes(){
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloak, 1), new Object[] { "SS","II", "II", 'I', Blocks.wool, 'S', Items.string });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakI, 1), new Object[] { "PPP","PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.potionitem, 1, 8206) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakI, 1), new Object[] { "PPP","PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.potionitem, 1, 8270) });

		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakRed, 1), new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.dye, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakYellow, 1), new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.dye, 1, 11) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakGreen, 1), new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.dye, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakBlue, 1), new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.dye, 1, 12) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakSub, 1), new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloak, 'P',new ItemStack(Items.dye, 1, 0) });

		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.forgeBlock, 1), new Object[] {"BBB", "BOB", "BBB", 'B', Blocks.brick_block, 'O',Blocks.obsidian });

	}
}
