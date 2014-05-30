package addonDread.items;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonDread.RpgDreadAddon;
import addonDread.models.NecroShield;
import addonDread.models.PalaShield;

public class ItemRpgInvArmorPlus extends ItemRpgInvArmor {

	private NecroShield nec = new NecroShield();

	private PalaShield pala = new PalaShield();

	public ItemRpgInvArmorPlus(int par4, int maxDamage, String name, String loc) {
		super(par4, maxDamage, -1, loc);
	}

	@Override
	public String bindShieldToArmorClass() {

		if (this.equals(RpgDreadAddon.necro_shield))
			return RpgDreadAddon.CLASSNECRO;
		if (this.equals(RpgDreadAddon.pala_shield))
			return RpgDreadAddon.CLASSPALADIN;
		return super.bindShieldToArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(RpgDreadAddon.necro_shield))
			return nec;
		if (this.equals(RpgDreadAddon.pala_shield))
			return pala;
		return super.getShieldModel();
	}

	@Override
	public String shieldClass() {
		if (this.equals(RpgDreadAddon.necro_shield))
			return RpgDreadAddon.CLASSNECROSHIELD;
		if (this.equals(RpgDreadAddon.pala_shield))
			return RpgDreadAddon.CLASSPALADINSHIELD;
		return super.shieldClass();
	}
}