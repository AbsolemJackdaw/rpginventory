package rpgInventory.item;

import net.minecraft.item.Item;

public class ItemMold extends Item {

	public ItemMold() {
		super();
		this.maxStackSize = 1;
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:" + itemName;
		return this;
	}
}
