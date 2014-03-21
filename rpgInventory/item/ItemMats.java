package rpgInventory.item;

import net.minecraft.item.Item;

public class ItemMats extends Item {

	public ItemMats(int par1) {
		super(par1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(
				getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}
}
