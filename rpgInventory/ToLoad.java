package rpgInventory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.slot.SlotMineral;
import rpgInventory.utils.RpgRegistry;

public class ToLoad {

	public static void loadMoldRecipes(){
		

		RpgRegistry.addForgeMoldMineral(Items.apple);
		
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.ringmold, Items.gold_ingot, new ItemStack(mod_RpgInventory.ringgold));
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.ringmold, Items.diamond, new ItemStack(mod_RpgInventory.ringdia));
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.ringmold, Items.dye, new ItemStack(mod_RpgInventory.ringlap));
		RpgRegistry.addForgeMoldRecipe(mod_RpgInventory.ringmold, Items.emerald, new ItemStack(mod_RpgInventory.ringem));

		MoldRecipes.addRecipe(mod_RpgInventory.colmold, Items.gold_ingot, new ItemStack(
				mod_RpgInventory.neckgold), 0.7F);
		MoldRecipes.addRecipe(mod_RpgInventory.colmold, Items.emerald, new ItemStack(
				mod_RpgInventory.neckem), 0.7F);
		MoldRecipes.addRecipe(mod_RpgInventory.colmold, Items.diamond, new ItemStack(
				mod_RpgInventory.neckdia), 0.7F);
		MoldRecipes.addRecipe(mod_RpgInventory.colmold, Items.dye, new ItemStack(
				mod_RpgInventory.necklap), 0.7F);

		MoldRecipes.addRecipe(mod_RpgInventory.wantmold, Items.gold_ingot, new ItemStack(
				mod_RpgInventory.glovesbutter), 0.7F);
		MoldRecipes.addRecipe(mod_RpgInventory.wantmold, Items.emerald, new ItemStack(
				mod_RpgInventory.glovesem), 0.7F);
		MoldRecipes.addRecipe(mod_RpgInventory.wantmold, Items.diamond, new ItemStack(
				mod_RpgInventory.glovesdia), 0.7F);
		MoldRecipes.addRecipe(mod_RpgInventory.wantmold, Items.dye, new ItemStack(
				mod_RpgInventory.gloveslap), 0.7F);
		
		MoldRecipes.addRecipe(mod_RpgInventory.wantmold, Items.apple, new ItemStack(
				Items.golden_apple), 0.7F);		
	}
	
	
	
	public static void loadGameRecipes(){
		GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloak, 1), new Object[] { "SS",
			"II", "II", 'I', Blocks.wool, 'S', Items.string });
	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakI, 1), new Object[] { "PPP",
			"PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.potionitem, 1, 8206) });
	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakI, 1), new Object[] { "PPP",
			"PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.potionitem, 1, 8270) });

	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakRed, 1), new Object[] {
			"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.dye, 1, 1) });
	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakYellow, 1), new Object[] {
			"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.dye, 1, 11) });
	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakGreen, 1), new Object[] {
			"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.dye, 1, 2) });
	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakBlue, 1), new Object[] {
			"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.dye, 1, 12) });
	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.cloakSub, 1), new Object[] {
			"PPP", "PCP", "PPP", 'C', mod_RpgInventory.cloak, 'P',
			new ItemStack(Items.dye, 1, 0) });

	GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.forgeBlock, 1), new Object[] {
			"BBB", "BOB", "BBB", 'B', Blocks.brick_block, 'O',
			Blocks.obsidian });

	}
}
