package rpgNecroPaladin.items;

import net.minecraft.creativetab.CreativeTabs;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.shields.MainShield;
import rpgNecroPaladin.mod_RpgPlus;
import rpgNecroPaladin.models.NecroShield;
import rpgNecroPaladin.models.PalaShield;

public class ItemRpgInvArmorPlus extends ItemRpgInvArmor {

	public ItemRpgInvArmorPlus(int par1, int par4, int maxDamage, String name, String loc)
	{
		super(par1, par4, maxDamage, name, loc);
		this.armorType = par4;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	
	private NecroShield nec = new NecroShield();
	private PalaShield pala = new PalaShield();
	
	@Override
	public MainShield getShieldModel(){
		if(this.equals(mod_RpgPlus.necro_shield))
			return nec;
		if(this.equals(mod_RpgPlus.pala_shield))
			return pala;
		return super.getShieldModel();
	}
	
}