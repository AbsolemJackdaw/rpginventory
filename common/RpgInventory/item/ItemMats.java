package RpgInventory.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import RpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMats extends Item{

	public ItemMats(int par1) {
		super(par1);
	}

	 @SideOnly(Side.CLIENT)
	    public int getColorFromItemStack(ItemStack is, int par2)
	    {
		 if(is.getItem() == mod_RpgInventory.necro_skin)
		 {
			 return 0xee0e1d;
		 }
		 if(is.getItem() == mod_RpgInventory.pala_steel)
		 {
			 return 0xf9f925;
		 }
	        return 16777215;
	    }
}