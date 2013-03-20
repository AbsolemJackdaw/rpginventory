package WWBS.wwbs.wwbs;

import RpgInventory.forge.GoldBlockSlot;
import RpgInventory.forge.MoldSlot;
import RpgInventory.forge.SlotFuel;
import RpgInventory.forge.SlotMineral;
import RpgInventory.forge.SlotMold;
import RpgInventory.forge.TEMold;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBank extends Container{

	private WwbsTe tile;
	public ContainerBank(InventoryPlayer par1InventoryPlayer, WwbsTe te)
	{
		
		tile = te;
		this.addSlotToContainer(new Slot(te, 0, 48, 72));
		this.addSlotToContainer(new Slot(te, 1, 48, 32)); 
		this.addSlotToContainer(new Slot(te, 2, 96, 51)); 
		this.addSlotToContainer(new Slot(te, 3, 96, 32)); 
		
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(par1InventoryPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18 +18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(par1InventoryPlayer, j, 8 + j * 18, 142 +18));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
