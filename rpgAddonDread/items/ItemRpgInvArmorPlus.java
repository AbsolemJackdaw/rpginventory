package rpgAddonDread.items;

import rpgAddonDread.mod_RpgPlus;
import rpgAddonDread.models.NecroShield;
import rpgAddonDread.models.PalaShield;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;

public class ItemRpgInvArmorPlus extends ItemRpgInvArmor {

	private NecroShield nec = new NecroShield();

	private PalaShield pala = new PalaShield();

	public ItemRpgInvArmorPlus(int par4, int maxDamage, String name, String loc) {
		super(par4, maxDamage, name, loc);
	}

	@Override
	public String boundArmorClass() {

		if (this.equals(mod_RpgPlus.necro_shield)) {
			return mod_RpgPlus.CLASSNECRO;
		}
		if (this.equals(mod_RpgPlus.pala_shield)) {
			return mod_RpgPlus.CLASSPALADIN;
		}
		return super.boundArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(mod_RpgPlus.necro_shield)) {
			return nec;
		}
		if (this.equals(mod_RpgPlus.pala_shield)) {
			return pala;
		}
		return super.getShieldModel();
	}

	@Override
	public String shieldClass() {
		if (this.equals(mod_RpgPlus.necro_shield)) {
			return mod_RpgPlus.CLASSNECROSHIELD;
		}
		if (this.equals(mod_RpgPlus.pala_shield)) {
			return mod_RpgPlus.CLASSPALADINSHIELD;
		}
		return super.shieldClass();
	}

}