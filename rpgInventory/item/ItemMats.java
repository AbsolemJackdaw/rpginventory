package rpgInventory.item;

import net.minecraft.item.Item;

public class ItemMats extends Item {

	public ItemMats() {
		super();
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:"+ itemName;
		return this;
	}
}
