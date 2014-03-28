package addonBasic.items;

import rpgInventory.mod_RpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.IronThorn;
import rpgInventory.models.shields.MainShield;
import rpgInventory.models.shields.ModelShield;
import rpgInventory.models.shields.bookMage;
import addonBasic.mod_addonBase;

public class ItemRpgInventoryItem extends ItemRpgInvArmor {

	private IronThorn it = new IronThorn();

	private ModelShield arch = new ModelShield();

	private bookMage book = new bookMage();

	public ItemRpgInventoryItem(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, name, resourcelocation);

		if (this.equals(mod_addonBase.talisman)) {
			isMantle = true;
		}
	}

	@Override
	public String boundArmorClass() {
		if (this.equals(mod_addonBase.berserkerShield)) {
			return mod_RpgInventory.CLASSBERSERKER;
		}
		if (this.equals(mod_addonBase.archerShield)) {
			return mod_RpgInventory.CLASSARCHER;
		}
		if (this.equals(mod_addonBase.talisman)) {
			return mod_RpgInventory.CLASSMAGE;
		}
		return super.boundArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(mod_addonBase.berserkerShield)) {
			return it;
		}
		if (this.equals(mod_addonBase.archerShield)) {
			return arch;
		}
		if (this.equals(mod_addonBase.talisman)) {
			return book;
		}
		return super.getShieldModel();
	}

	@Override
	public String shieldClass() {
		if (this.equals(mod_addonBase.berserkerShield)) {
			return mod_RpgInventory.CLASSBERSERKERSHIELD;
		}
		if (this.equals(mod_addonBase.archerShield)) {
			return mod_RpgInventory.CLASSARCHERSHIELD;
		}
		if (this.equals(mod_addonBase.talisman)) {
			return mod_RpgInventory.CLASSMAGESHIELD;
		}
		return super.shieldClass();
	}
}
