package rpgMage;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;
import rpgInventory.models.shields.bookMage;

public class ItemMageShield extends ItemRpgInvArmor {


	public ItemMageShield(int par1, int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par1, par4, maxDamage, name, resourcelocation);
	}

	private bookMage book = new bookMage();
	
	@Override
	public String boundArmorClass() {
		return mod_RpgMageSet.CLASSARCHMAGE;
	}
	
	@Override
	public String shieldClass() {
		return mod_RpgMageSet.CLASSARCHMAGESHIELD;
	}

	@Override
	public MainShield getShieldModel() {
		if(this.equals(mod_RpgMageSet.archBook))
			return book;
		return super.getShieldModel();
	}
}
