package addonBasic.items;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonBasic.RpgBaseAddon;
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
	public String boundArmorClass() {
		if (this.equals(RpgBaseAddon.berserkerShield))
			return RpgBaseAddon.CLASSBERSERKER;
		if (this.equals(RpgBaseAddon.archerShield))
			return RpgBaseAddon.CLASSARCHER;
		if (this.equals(RpgBaseAddon.talisman))
			return RpgBaseAddon.CLASSMAGE;
		return super.boundArmorClass();
	}

	@Override
	public String getMantleTexture() {
		return "subaraki:jewels/talisman.png";
	}

	@Override
	public MainShield getShieldModel() {
		if (this.equals(RpgBaseAddon.berserkerShield))
			return it;
		if (this.equals(RpgBaseAddon.archerShield))
			return arch;
		if (this.equals(RpgBaseAddon.talisman))
			return book;
		return super.getShieldModel();
	}

	@Override
	public boolean isMantle() {

		if(this.equals(RpgBaseAddon.talisman))
			return true;
		return false;
	}

	@Override
	public String shieldClass() {
		if (this.equals(RpgBaseAddon.berserkerShield))
			return RpgBaseAddon.CLASSBERSERKERSHIELD;
		if (this.equals(RpgBaseAddon.archerShield))
			return RpgBaseAddon.CLASSARCHERSHIELD;
		if (this.equals(RpgBaseAddon.talisman))
			return RpgBaseAddon.CLASSMAGESHIELD;
		return super.shieldClass();
	}
}
