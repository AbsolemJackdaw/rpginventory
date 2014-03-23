package rpgInventory.item;

import net.minecraft.item.Item;

public class ItemCandy extends Item {

	public ItemCandy(int par1) {
		super();
		this.maxStackSize = 6;
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:"+ itemName;
		return this;
	}
}
