package addonArchmage;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonBasic.models.item.bookMage;

public class ItemMageShield extends ItemRpgInvArmor {

	private bookMage book = new bookMage();

	public ItemMageShield(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, -1, resourcelocation);
	}

	@Override
	public String bindShieldToArmorClass() {
		return RpgArchmageAddon.CLASSARCHMAGE;
	}

	@Override
	public String getMantleTexture() {
		return "subaraki:jewels/magemantle.png";
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(RpgArchmageAddon.archBook)) {
			return book;
		}
		return super.getShieldModel();
	}

	@Override
	public boolean isMantle() {
		return true;
	}

	@Override
	public String shieldClass() {
		return RpgArchmageAddon.CLASSARCHMAGESHIELD;
	}
}
