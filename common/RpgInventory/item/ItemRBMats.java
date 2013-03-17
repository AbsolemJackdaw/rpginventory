package RpgInventory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import RpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRBMats extends Item{


	public ItemRBMats(int par1) {
		super(par1);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2)
	{
		if(is.getItem() == mod_RpgInventory.beastLeather)
		{
			return 0x0b910d;
		}
		if(is.getItem() == mod_RpgInventory.rogueLeather)
		{
			return 0xae1fe1;
		}
		return 16777215;
	}
	
}
