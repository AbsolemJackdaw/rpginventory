package rpgInventory.item;

import net.minecraft.item.Item;

public class ItemMold extends Item {

	public ItemMold() {
		super();
		this.maxStackSize = 1;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("RPGInventoryMod:"
				+ itemName);
	}
}
