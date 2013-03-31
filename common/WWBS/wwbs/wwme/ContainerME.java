package WWBS.wwbs.wwme;

import java.util.ArrayList;
import java.util.List;

import WWBS.wwbs.wwbs.GuiBS;
import WWBS.wwbs.wwbs.WwbsTe;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerME extends Container{

	private WwmeTE tile;
    public List itemList = new ArrayList();

	public ContainerME(InventoryPlayer par1InventoryPlayer, WwmeTE te)
	{
		
		tile = te;
	
		for (int i = 0; i <2; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				addSlotToContainer(new Slot(te, k + i * 9 + 9, 12 + k * 60, -28 + i * 59 +18));
			}
		}
//		this.addSlotToContainer(new Slot(te, 0, 48, 72));
		
		
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(par1InventoryPlayer, k + i * 9 + 9, 13 + k * 18, 93 + i * 18 +18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(par1InventoryPlayer, j, 13 + j * 18, 151 +18));
		}
	}

	public boolean hasMoreThan1PageOfItemsInList()
	{
		return this.itemList.size() > 45;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
