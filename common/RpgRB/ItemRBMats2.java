package RpgRB;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import RpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRBMats2 extends Item{


	public ItemRBMats2(int par1) {
		super(par1);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		if( par1ItemStack.itemID == mod_RpgInventory.wizardBook.itemID)
		{
			mod_RpgInventory.proxy.openGUI(player, 2);
		}
		return par1ItemStack;
	}

	public String getTextureFile() {
		return "/subaraki/RPGinventoryTM.png";
	}
}
