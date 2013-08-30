package rpgInventory.item.weapons;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;

public class ItemClaymore extends ItemRpgWeapon{

	private EnumToolMaterial toolMaterial;
	private int weaponDamage;
	Random rand = new Random();

	public ItemClaymore(int par1, EnumToolMaterial mat) {
		super(par1);
		this.toolMaterial = mat;
		this.maxStackSize = 1;
		this.setMaxDamage(mat.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.weaponDamage = (int) (4 + mat.getDamageVsEntity());
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		ItemStack var3 = par3EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var2 = par3EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var1 = par3EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var0 = par3EntityPlayer.inventory.armorItemInSlot(0);

		if (var3 !=null && var2 !=null && var1 != null && var0 !=null)
		{
			Item item  = var3.getItem();
			Item item1 = var2.getItem();
			Item item2 = var1.getItem();
			Item item3 = var0.getItem();

			if(item.equals(mod_RpgInventory.berserkerHood) && item1.equals(mod_RpgInventory.berserkerChest)
					&& item2.equals(mod_RpgInventory.berserkerLegs)&& item3.equals(mod_RpgInventory.berserkerBoots))
			{
				par2World.playSoundEffect(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, "ambient.weather.thunder", 100.0F, 0.8F + rand.nextFloat() * 0.2F);
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 40, 3));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 40, 1));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400,1));
				par1ItemStack.damageItem(5, par3EntityPlayer);


			}
			else
			{
				par3EntityPlayer.removePotionEffect(Potion.moveSlowdown.id);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);
		}
		return par1ItemStack;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase mob, EntityLivingBase player)
	{
		is.damageItem(1, mob);
		mob.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 8);

		if(mob instanceof EntityPlayer)
		{
			String name = ((EntityPlayer)mob).username;
			ItemStack skull = new ItemStack(Item.skull.itemID,1,3);

			if( skull.stackTagCompound == null ) {
				skull.setTagCompound( new NBTTagCompound() );
			}
			if( !skull.stackTagCompound.hasKey( "SkullOwner" ) ) {
				skull.stackTagCompound.setString( "SkullOwner", name );
			}

			if(mob.func_110143_aJ() < 1)
			{
				EntityItem entityitem = new EntityItem(mob.worldObj, mob.posX,mob.posY,mob.posZ, skull);
				mob.worldObj.spawnEntityInWorld(entityitem);
			}
		}

		return false;
	}
}
