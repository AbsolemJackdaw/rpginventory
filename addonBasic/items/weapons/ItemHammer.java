package addonBasic.items.weapons;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.ItemRpgSword;
import addonBasic.RpgBaseAddon;

public class ItemHammer extends ItemRpgSword {

	Random rand = new Random();

	public ItemHammer(ToolMaterial mat) {
		super(ToolMaterial.STONE);
		this.maxStackSize = 1;
		this.setMaxDamage(ToolMaterial.IRON.getMaxUses() + 300);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer player) {
		PlayerRpgInventory inv = PlayerRpgInventory.get(player);

		if (RpgInventoryMod.playerClass.contains(RpgBaseAddon.CLASSBERSERKER))
			if (RpgInventoryMod.playerClass
					.contains(RpgBaseAddon.CLASSBERSERKERSHIELD)) {
				if ((player.getFoodStats().getFoodLevel() < 6)
						|| (player.getHealth() < 6))
					player.addPotionEffect(new PotionEffect(
							Potion.damageBoost.id, 200, 1));
				else if ((player.getFoodStats().getFoodLevel() < 3)
						|| (player.getHealth() < 3))
					player.addPotionEffect(new PotionEffect(
							Potion.damageBoost.id, 200, 2));
			} else if ((player.getFoodStats().getFoodLevel() < 4)
					|| (player.getHealth() < 4))
				player.addPotionEffect(new PotionEffect(Potion.damageBoost.id,
						200, 1));
		par1ItemStack.damageItem(1, player);
		if (par1ItemStack.getItemDamage() > par1ItemStack.getMaxDamage())
			par1ItemStack = null;
		return par1ItemStack;
	}
}
