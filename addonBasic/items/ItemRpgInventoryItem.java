package addonBasic.items;

import rpgInventory.mod_RpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;
import addonBasic.mod_addonBase;
import addonBasic.models.item.IronThorn;
import addonBasic.models.item.ModelShield;
import addonBasic.models.item.bookMage;

public class ItemRpgInventoryItem extends ItemRpgInvArmor {

	private IronThorn it = new IronThorn();

	private ModelShield arch = new ModelShield();

	private bookMage book = new bookMage();

	public ItemRpgInventoryItem(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, name, resourcelocation);
	}
	
	@Override
	public boolean isMantle() {
		return this.equals(mod_addonBase.talisman) ? true : false;
	}

	@Override
	public String boundArmorClass() {
		if (this.equals(mod_addonBase.berserkerShield))
			return mod_addonBase.CLASSBERSERKER;
		if (this.equals(mod_addonBase.archerShield))
			return mod_addonBase.CLASSARCHER;
		if (this.equals(mod_addonBase.talisman))
			return mod_addonBase.CLASSMAGE;
		return super.boundArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(mod_addonBase.berserkerShield))
			return it;
		if (this.equals(mod_addonBase.archerShield))
			return arch;
		if (this.equals(mod_addonBase.talisman))
			return book;
		return super.getShieldModel();
	}

	@Override
	public String shieldClass() {
		if (this.equals(mod_addonBase.berserkerShield))
			return mod_addonBase.CLASSBERSERKERSHIELD;
		if (this.equals(mod_addonBase.archerShield))
			return mod_addonBase.CLASSARCHERSHIELD;
		if (this.equals(mod_addonBase.talisman))
			return mod_addonBase.CLASSMAGESHIELD;
		return super.shieldClass();
	}
}
