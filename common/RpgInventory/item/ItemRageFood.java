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
        if (p.getFoodStats().getFoodLevel() > 0) {
            p.getFoodStats().addStats(p.getFoodStats().getFoodLevel() * -1, 0.0F);
        }
        par1ItemStack.stackSize--;
        return par1ItemStack;
    }
}