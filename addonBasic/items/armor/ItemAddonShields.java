package addonBasic.items.armor;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonBasic.RpgBaseAddon;
import addonBasic.models.item.IronThorn;
import addonBasic.models.item.ModelShield;
import addonBasic.models.item.bookMage;

public class ItemAddonShields extends ItemRpgInvArmor{

	private IronThorn it = new IronThorn();
	private ModelShield ms= new ModelShield();
	private bookMage bm = new bookMage();

	public ItemAddonShields(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, -1, resourcelocation);
	}

	@Override
	public String getMantleTexture() {
		return "subaraki:jewels/talisman.png";
	}

	@Override
	public String bindShieldToArmorClass() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return RpgBaseAddon.CLASSBERSERKER;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return RpgBaseAddon.CLASSARCHER;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return RpgBaseAddon.CLASSMAGE;
		}

		return super.bindShieldToArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return it;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return ms;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return bm;
		}
		return super.getShieldModel();
	}

	@Override
	public boolean isMantle() {
		return this.equals(RpgBaseAddon.talisman);
	}

	@Override
	public String shieldClass() {
		if(this.equals(RpgBaseAddon.berserkerShield)) {
			return RpgBaseAddon.CLASSBERSERKERSHIELD;
		}
		if(this.equals(RpgBaseAddon.archerShield)) {
			return RpgBaseAddon.CLASSARCHERSHIELD;
		}
		if(this.equals(RpgBaseAddon.talisman)) {
			return RpgBaseAddon.CLASSMAGESHIELD;
		}
		return super.shieldClass();
	}
}
