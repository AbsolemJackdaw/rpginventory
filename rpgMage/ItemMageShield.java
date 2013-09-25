package rpgMage;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.Book;
import rpgInventory.models.shields.MainShield;

public class ItemMageShield extends ItemRpgInvArmor {


	public ItemMageShield(int par1, int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par1, par4, maxDamage, name, resourcelocation);
	}

	private Book book = new Book();

	@Override
	public MainShield getShieldModel() {
		if(this.equals(mod_RpgMageSet.archBook))
			return book;
		return super.getShieldModel();
	}
}
