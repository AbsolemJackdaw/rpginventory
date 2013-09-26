package rpgVanillaShields;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;

public class ItemRpgInvShields extends ItemRpgInvArmor{

	public ItemRpgInvShields(int par1, int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par1, par4, maxDamage, name, resourcelocation);

	}

	private VanillaShield van = new VanillaShield();

	@Override
	public String shieldClass(){
		if(this.equals(mod_VanillaShields.shieldWood)){
			return mod_VanillaShields.WOODENSHIELD;
		}if(this.equals(mod_VanillaShields.shieldIron)){
			return mod_VanillaShields.IRONSHIELD;
		}if(this.equals(mod_VanillaShields.shieldGold)){
			return mod_VanillaShields.GOLDENSHIELD;
		}if(this.equals(mod_VanillaShields.shieldDiamond)){
			return mod_VanillaShields.DIAMONDSHIELD;
		}
			return super.shieldClass();
	}

	@Override
	public String boundArmorClass() {
		return "none";
	}

	public MainShield getShieldModel(){

		if(this.equals(mod_VanillaShields.shieldDiamond)||this.equals(mod_VanillaShields.shieldGold)
				||this.equals(mod_VanillaShields.shieldIron)||this.equals(mod_VanillaShields.shieldWood))
			return van;

		return new MainShield();
	}

}
