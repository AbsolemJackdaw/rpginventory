package RpgInventory.weapons.bow;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import RpgInventory.AARpgBaseClass;

public class ItemArcherBow extends Item
{
	public ItemArcherBow(int par1)
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(2000);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	/**
	 * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
	 */
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		var6 = event.charge;

		boolean var5 = par3EntityPlayer.capabilities.isCreativeMode;

		ItemStack var3 = par3EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var2 = par3EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var1 = par3EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var0 = par3EntityPlayer.inventory.armorItemInSlot(0);

		if (var3 !=null && var2 !=null && var1 != null && var0 !=null)
		{
			Item item = var3.getItem();
			Item item1 = var2.getItem();
			Item item2 = var1.getItem();
			Item item3 = var0.getItem();

			if(item.equals(AARpgBaseClass.archerhood) && item1.equals(AARpgBaseClass.archerchest)
					&& item2.equals(AARpgBaseClass.archerpants)&& item3.equals(AARpgBaseClass.archerboots))
			{
				float var7 = (float)var6 / 10.0F;

				var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

				if ((double)var7 < 0.1D)
				{
					return;
				}

				if (var7 > 1.0F)
				{
					var7 = 1.0F;
				}

				EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);


				if(par2World.isRemote)
				{
					var8.setIsCritical(true);
					var8.setDamage(var8.getDamage() + (double)1);
					var8.setKnockbackStrength(1);
					var8.setFire(10);
					var8.canBePickedUp = 2;   

					par1ItemStack.damageItem(1, par3EntityPlayer);
					par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

					if (!par2World.isRemote)
					{
						par2World.
						spawnEntityInWorld(var8);
						par1ItemStack.damageItem(2, par3EntityPlayer);
					}
				}
				else
				{
					var8.setIsCritical(true);
					var8.setDamage(var8.getDamage() + (double)2 * 0.5D + 0.5D);
					var8.setKnockbackStrength(2);
					var8.setFire(30);
					var8.canBePickedUp = 2;   

					par1ItemStack.damageItem(1, par3EntityPlayer);
					par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

					if (!par2World.isRemote)
					{
						par2World.spawnEntityInWorld(var8);
						par1ItemStack.damageItem(2, par3EntityPlayer);
					}
				}
			}
		}
	}


	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{

		ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return event.result;
		}

		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(AARpgBaseClass.elfbow.itemID))
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return -1;
	}

	public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		int k = usingItem.getMaxItemUseDuration() - useRemaining;

		System.out.println(k);

		if (usingItem != null && usingItem.getItem().itemID == AARpgBaseClass.elfbow.itemID )
		{
			if (k >=  18 && k != 72000) return 25;
			if (k >   1  && k != 72000) return 24;
			if (k >   0) return 23;
			
		}
		
		return getIconIndex(stack);
	}

	public String getTextureFile()
	{
		return "/subaraki/RPGinventoryTM.png";
	}
}
