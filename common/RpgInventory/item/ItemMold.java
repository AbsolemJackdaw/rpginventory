package RpgInventory.item;
import net.minecraft.item.Item;



public class ItemMold extends Item {

	public ItemMold(int par1) {
		super(par1);
		this.maxStackSize = 1;
	}
	
	public String getTextureFile()
	{
		return "/subaraki/RPGinventoryTM.png";
	}
}
