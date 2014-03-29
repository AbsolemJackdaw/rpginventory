package rpgVanillaShields;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;

public class ItemRpgInvShields extends ItemRpgInvArmor {

	private VanillaShield van = new VanillaShield();

	public ItemRpgInvShields(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, name, resourcelocation);

	}

	@Override
	public String boundArmorClass() {
		return "none";
	}

	@Override
	public MainShield getShieldModel() {

		if (this.equals(mod_VanillaShields.shieldDiamond)
				|| this.equals(mod_VanillaShields.shieldGold)
				|| this.equals(mod_VanillaShields.shieldIron)
				|| this.equals(mod_VanillaShields.shieldWood))
			return van;

		return new MainShield();
	}

	@Override
	public String shieldClass() {
		if (this.equals(mod_VanillaShields.shieldWood))
			return mod_VanillaShields.WOODENSHIELD;
		if (this.equals(mod_VanillaShields.shieldIron))
			return mod_VanillaShields.IRONSHIELD;
		if (this.equals(mod_VanillaShields.shieldGold))
			return mod_VanillaShields.GOLDENSHIELD;
		if (this.equals(mod_VanillaShields.shieldDiamond))
			return mod_VanillaShields.DIAMONDSHIELD;
		return super.shieldClass();
	}

}
