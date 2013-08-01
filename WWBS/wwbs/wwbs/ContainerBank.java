package WWBS.wwbs.wwbs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBank extends Container{

	private WwbsTe tile;
    public List itemList = new ArrayList();

	public ContainerBank(InventoryPlayer par1InventoryPlayer, WwbsTe te)
	{
		
		tile = te;
	
		for (int i = 0; i < 5; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(te, k + i * 9 + 9, 8 + k * 18, -30 + i * 18 +18));
			}
		}
		
		
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(par1InventoryPlayer, k + i * 9 + 9, 8 + k * 18, 92 + i * 18 +18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(par1InventoryPlayer, j, 8 + j * 18, 150 +18));
		}
	}
	
	 public void scrollTo(float par1)
	    {
	        int i = this.itemList.size() / 9 - 5 + 1;
	        int j = (int)((double)(par1 * (float)i) + 0.5D);

	        if (j < 0)
	        {
	            j = 0;
	        }

	        for (int k = 0; k < 5; ++k)
	        {
	            for (int l = 0; l < 9; ++l)
	            {
	                int i1 = l + (k + j) * 9;

	                if (i1 >= 0 && i1 < this.itemList.size())
	                {
	                    GuiBS.getInventory().setInventorySlotContents(l + k * 9, (ItemStack)this.itemList.get(i1));
	                }
	                else
	                {
	                	GuiBS.getInventory().setInventorySlotContents(l + k * 9, (ItemStack)null);
	                }
	            }
	        }
	    }

	public boolean hasMoreThan1PageOfItemsInList()
	{
		return this.itemList.size() > 54;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
