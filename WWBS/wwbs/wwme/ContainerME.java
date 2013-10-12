package WWBS.wwbs.wwme;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerME extends Container{

	private WwmeTE tile;

	public ContainerME(InventoryPlayer inv, WwmeTE te)
	{
		tile = te;
	
		for (int i = 0; i <2; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				addSlotToContainer(new Slot(te, k + i, 12 + k * 60, -28 + i * 59 +18));
			}
		}		
		
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(inv, k + i * 9 + 9, 13 + k * 18, 104+7 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(inv, j, 13 + j * 18, 162+7));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
}
