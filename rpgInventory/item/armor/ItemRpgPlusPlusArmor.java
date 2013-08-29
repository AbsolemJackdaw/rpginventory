package rpgInventory.item.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;

public class ItemRpgPlusPlusArmor extends ItemRpgArmor {

	public ItemRpgPlusPlusArmor(int par1, int par4, int maxDamage, String name)
	{
		super(par1, par4, maxDamage, name);
		this.armorType = par4;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	/**
	 *Checks the Players armor and gives him special Abilities when the Player is wearing the full Armor Set
	 */
	@Override
	public void armorEffects(ItemStack is, EntityPlayer player)
	{
		RpgInv rpg = mod_RpgInventory.proxy.getInventory(player.username);

		ItemStack slot0 = player.inventory.armorItemInSlot(0);
		ItemStack slot1 = player.inventory.armorItemInSlot(1);
		ItemStack slot2 = player.inventory.armorItemInSlot(2);
		ItemStack slot3 = player.inventory.armorItemInSlot(3);


		ItemStack shield = rpg.getJewelInSlot(1);

		/**
		 *Checks for the full Paladin Armor
		 */
		if(shield != null && shield.getItem() == mod_RpgInventory.pala_shield)
		{
			if( slot0 != null && slot0.getItem() == mod_RpgInventory.palaBoots
					&& slot1 != null && slot1.getItem() == mod_RpgInventory.palaLeggings
					&& slot2 != null && slot2.getItem() == mod_RpgInventory.palaChest
					&& slot3 != null && slot3.getItem() == mod_RpgInventory.palaHelm)
			{
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,20, 1));
			}
//			player. = -60;	

		}
		else
		{
			if( slot0 != null && slot0.getItem() == mod_RpgInventory.palaBoots
					&& slot1 != null && slot1.getItem() == mod_RpgInventory.palaLeggings
					&& slot2 != null && slot2.getItem() == mod_RpgInventory.palaChest
					&& slot3 != null && slot3.getItem() == mod_RpgInventory.palaHelm)
			{
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,20, 1));
//				player.carryoverDamage = -55;	
			}
		}
		if(shield != null && shield.getItem() == mod_RpgInventory.necro_shield)
		{
//			player.carryoverDamage = -55;	
		}
	}
}