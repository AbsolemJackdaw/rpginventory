package RpgInventory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRageFood extends ItemFood {

	public ItemRageFood(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3, par4);
	}
	
	
	@Override
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer p) {
		
	p.getFoodStats().setFoodLevel(0);
	p.getFoodStats().setFoodSaturationLevel(0);
		
		return super.onFoodEaten(par1ItemStack, par2World, p);
	}

	
}
