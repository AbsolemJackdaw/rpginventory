package RpgInventory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import RpgInventory.AARpgBaseClass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpg extends Item {

	public ItemRpg(int par1) {
		super(par1);
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
		if(par1ItemStack.itemID == AARpgBaseClass.magecloth.itemID || par1ItemStack.itemID == AARpgBaseClass.wizardBook.itemID)
		{
			return true;
		}
        return false;
    }
	
	 public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	    {
		 if( par1ItemStack.itemID == AARpgBaseClass.wizardBook.itemID)
			{
			 AARpgBaseClass.proxy.openGUI(player, 2);
			}
	        return par1ItemStack;
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public int getColorFromItemStack(ItemStack is, int par2)
	    {
		 if(is.getItem() == AARpgBaseClass.tanHide)
		 {
			 return 0xa24203;
		 }
		 if(is.getItem() == AARpgBaseClass.magecloth)
		 {
			 return 0x000080;
		 }
		 if(is.getItem() == AARpgBaseClass.animalskin)
		 {
			 return 0x71544f;
		 }
	        return 16777215;
	    }
}
