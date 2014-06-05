package addonBasic.items.weapons;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import rpgInventory.item.ItemRpgSword;
import addonBasic.RpgBaseAddon;

public class ItemClaymore extends ItemRpgSword {

	Random rand = new Random();

	public ItemClaymore(ToolMaterial mat) {
		super(mat);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase mob,
			EntityLivingBase player) {
		super.hitEntity(is, mob, player);
		
		if (mob instanceof EntityPlayer) {
			String name = ((EntityPlayer) mob).getCommandSenderName();
			ItemStack skull = new ItemStack(Items.skull, 1, 3);

			if (skull.stackTagCompound == null) {
				skull.setTagCompound(new NBTTagCompound());
			}
			if (!skull.stackTagCompound.hasKey("SkullOwner")) {
				skull.stackTagCompound.setString("SkullOwner", name);
			}

			if (mob.getHealth() < 1) {
				EntityItem entityitem = new EntityItem(mob.worldObj, mob.posX,
						mob.posY, mob.posZ, skull);
				mob.worldObj.spawnEntityInWorld(entityitem);
			}
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		ItemStack var3 = par3EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var2 = par3EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var1 = par3EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var0 = par3EntityPlayer.inventory.armorItemInSlot(0);

		if ((var3 != null) && (var2 != null) && (var1 != null)
				&& (var0 != null)) {
			Item item = var3.getItem();
			Item item1 = var2.getItem();
			Item item2 = var1.getItem();
			Item item3 = var0.getItem();

			if (item.equals(RpgBaseAddon.berserkerHood)
					&& item1.equals(RpgBaseAddon.berserkerChest)
					&& item2.equals(RpgBaseAddon.berserkerLegs)
					&& item3.equals(RpgBaseAddon.berserkerBoots)) {
				par2World.playSoundEffect(par3EntityPlayer.posX,
						par3EntityPlayer.posY, par3EntityPlayer.posZ,
						"ambient.weather.thunder", 100.0F,
						0.8F + (rand.nextFloat() * 0.2F));
				par3EntityPlayer.addPotionEffect(new PotionEffect(
						Potion.damageBoost.id, 40, 3));
				par3EntityPlayer.addPotionEffect(new PotionEffect(
						Potion.resistance.id, 40, 1));
				par3EntityPlayer.addPotionEffect(new PotionEffect(
						Potion.moveSlowdown.id, 400, 1));
				par1ItemStack.damageItem(5, par3EntityPlayer);

			} else {
				par3EntityPlayer.removePotionEffect(Potion.moveSlowdown.id);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);
		}
		return par1ItemStack;
	}
}
