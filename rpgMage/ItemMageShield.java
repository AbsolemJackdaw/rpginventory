package rpgMage;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;
import rpgInventory.models.shields.bookMage;

public class ItemMageShield extends ItemRpgInvArmor {

	private bookMage book = new bookMage();

	public ItemMageShield(int par4, int maxDamage, String name,
			String resourcelocation) {

		super(par4, maxDamage, name, resourcelocation);

	}

	@Override
	public String boundArmorClass() {
		return mod_RpgMageSet.CLASSARCHMAGE;
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(mod_RpgMageSet.archBook)) {
			return book;
		}
		return super.getShieldModel();
	}

	@Override
	public String shieldClass() {
		return mod_RpgMageSet.CLASSARCHMAGESHIELD;
	}
}
