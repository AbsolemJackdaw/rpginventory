package rpgVanillaShields;

import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;

public class ItemRpgInvShields extends ItemRpgInvArmor{

	public ItemRpgInvShields(int par1, int par4, int maxDamage, String name,
			String resourcelocation) {
		super(par1, par4, maxDamage, name, resourcelocation);
		// TODO Auto-generated constructor stub
	}

	private VanillaShield van = new VanillaShield();

	public MainShield getShieldModel(){
		
		if(this.equals(mod_VanillaShields.shieldDiamond)||this.equals(mod_VanillaShields.shieldGold)
				||this.equals(mod_VanillaShields.shieldIron)||this.equals(mod_VanillaShields.shieldWood))
			return van;

		return new MainShield();
	}
	
}
