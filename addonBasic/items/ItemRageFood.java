package addonBasic.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRageFood extends ItemFood {

	public ItemRageFood(int par2, float par3, boolean par4) {
		super(par2, par3, par4);
	}


	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer p) {
		if (p.getFoodStats().getFoodLevel() > 0)
			p.getFoodStats().addStats(p.getFoodStats().getFoodLevel() * -1,
					0.0F);
		par1ItemStack.stackSize--;
	}

	// @Override
	// public Item setTextureName(String s) {
	// String itemName = getUnlocalizedName().substring(
	// getUnlocalizedName().indexOf(".") + 1);
	// this.iconString = "rpginventorymod:" + itemName;
	// return this;
	// }
}