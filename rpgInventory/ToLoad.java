package rpgInventory;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.RpgUtility;
import cpw.mods.fml.common.registry.GameRegistry;

public class ToLoad {

	public static void loadGameRecipes(){
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakWhite, 1), new Object[] { "SS","II", "II", 'I', new ItemStack(Blocks.wool,1,0), 'S', Items.string });

		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakBlack, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakRed, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakGreen, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakBrown, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakBlue, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakPurple, 1)	, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakCyan, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakSilver, 1)	, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 7) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakGray, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 8) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakPink, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 9) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakLime, 1)		, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakYellow, 1)	, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 11) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakLightblue, 1)	, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 12) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakMagenta, 1)	, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 13) });
		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.cloakOrange, 1)	, new Object[] {"PPP", "PCP", "PPP", 'C', RpgInventoryMod.cloakWhite, 'P',new ItemStack(Items.dye, 1, 14) });

		GameRegistry.addRecipe(new ItemStack(RpgInventoryMod.forgeBlock, 1), new Object[] {"BBB", "BOB", "BBB", 'B', Blocks.brick_block, 'O',Blocks.obsidian });

	}



	public static void loadMoldRecipes(){

		RpgUtility.addFuel(new ItemStack(Items.coal), 100);
		RpgUtility.addFuel(new ItemStack(Items.lava_bucket), 3200);
		RpgUtility.addFuel(new ItemStack(Items.blaze_rod), 1600);
		RpgUtility.addFuel(new ItemStack(Blocks.coal_block), 800);


		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.ringmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(RpgInventoryMod.ringgold));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.ringmold, Items.diamond,Blocks.gold_block, new ItemStack(RpgInventoryMod.ringdia));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.ringmold, Items.dye, Blocks.gold_block,new ItemStack(RpgInventoryMod.ringlap));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.ringmold, Items.emerald,Blocks.gold_block, new ItemStack(RpgInventoryMod.ringem));

		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.colmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(RpgInventoryMod.neckgold));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.colmold, Items.diamond,Blocks.gold_block, new ItemStack(RpgInventoryMod.neckdia));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.colmold, Items.dye, Blocks.gold_block,new ItemStack(RpgInventoryMod.necklap));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.colmold, Items.emerald,Blocks.gold_block, new ItemStack(RpgInventoryMod.neckem));

		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.wantmold, Items.gold_ingot, Blocks.gold_block, new ItemStack(RpgInventoryMod.glovesbutter));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.wantmold, Items.diamond,Blocks.gold_block, new ItemStack(RpgInventoryMod.glovesdia));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.wantmold, Items.dye, Blocks.gold_block,new ItemStack(RpgInventoryMod.gloveslap));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.wantmold, Items.emerald,Blocks.gold_block, new ItemStack(RpgInventoryMod.glovesem));


		RpgUtility.addCatalistBlock(Blocks.iron_block);
		RpgUtility.addForgeMoldMineral(Item.getItemFromBlock(Blocks.coal_block));

		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.glovesbutter, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(RpgInventoryMod.wantmold));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.ringgold, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(RpgInventoryMod.ringmold));
		RpgUtility.addForgeMoldRecipe(RpgInventoryMod.neckgold, Item.getItemFromBlock(Blocks.coal_block), Blocks.iron_block, new ItemStack(RpgInventoryMod.colmold));


	}
}
