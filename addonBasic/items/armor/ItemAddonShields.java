package addonBasic.items.armor;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;
import addonBasic.mod_addonBase;
import addonBasic.models.item.IronThorn;
import addonBasic.models.item.ModelShield;
import addonBasic.models.item.bookMage;
import addonBasic.renderer.shields.ArcherShield;

public class ItemAddonShields extends ItemRpgInvArmor{

	private IronThorn it = new IronThorn();
	private ModelShield ms= new ModelShield();
	private bookMage bm = new bookMage();


	public ItemAddonShields(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, name, resourcelocation);
	}

	@Override
	public String boundArmorClass() {
		if(this.equals(mod_addonBase.berserkerShield))
			return mod_addonBase.CLASSBERSERKER;
		if(this.equals(mod_addonBase.archerShield))
			return mod_addonBase.CLASSARCHER;
		if(this.equals(mod_addonBase.talisman))
			return mod_addonBase.CLASSMAGE;

		return super.boundArmorClass();
	}

	@Override
	public MainShield getShieldModel() {
		if(this.equals(mod_addonBase.berserkerShield))
			return it;
		if(this.equals(mod_addonBase.archerShield))
			return ms;
		if(this.equals(mod_addonBase.talisman))
			return bm;
		return super.getShieldModel();
	}
	
	@Override
	public String shieldClass() {
		if(this.equals(mod_addonBase.berserkerShield))
			return mod_addonBase.CLASSBERSERKERSHIELD;
		if(this.equals(mod_addonBase.archerShield))
			return mod_addonBase.CLASSARCHERSHIELD;
		if(this.equals(mod_addonBase.talisman))
			return mod_addonBase.CLASSMAGESHIELD;
		return super.shieldClass();
	}
	
	@Override
	public boolean isMantle() {
		// TODO Auto-generated method stub
		return this.equals(mod_addonBase.talisman);
	}
}
