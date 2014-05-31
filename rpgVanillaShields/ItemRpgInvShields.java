package rpgVanillaShields;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.MainShield;

public class ItemRpgInvShields extends ItemRpgInvArmor {

	private VanillaShield van = new VanillaShield();

	public ItemRpgInvShields(int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par4, maxDamage, 17, resourcelocation);

	}

	@Override
	public String bindShieldToArmorClass() {
		return "none";
	}

	@Override
	public MainShield getShieldModel() {

		if (this.equals(RpgVanillaShields.shieldDiamond)
				|| this.equals(RpgVanillaShields.shieldGold)
				|| this.equals(RpgVanillaShields.shieldIron)
				|| this.equals(RpgVanillaShields.shieldWood)) {
			return van;
		}

		return new MainShield();
	}

	@Override
	public String shieldClass() {
		if (this.equals(RpgVanillaShields.shieldWood)) {
			return RpgVanillaShields.WOODENSHIELD;
		}
		if (this.equals(RpgVanillaShields.shieldIron)) {
			return RpgVanillaShields.IRONSHIELD;
		}
		if (this.equals(RpgVanillaShields.shieldGold)) {
			return RpgVanillaShields.GOLDENSHIELD;
		}
		if (this.equals(RpgVanillaShields.shieldDiamond)) {
			return RpgVanillaShields.DIAMONDSHIELD;
		}
		return super.shieldClass();
	}

}
