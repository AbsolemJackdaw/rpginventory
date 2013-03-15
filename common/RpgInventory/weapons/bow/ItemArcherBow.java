package RpgInventory.weapons.bow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import RpgInventory.mod_RpgInventory;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.weapons.ItemRpgWeapon;

public class ItemArcherBow extends ItemRpgWeapon
{
	public static final String[] field_94601_a = new String[] {"elmBow1", "elmBow2", "elmBow3"};
	@SideOnly(Side.CLIENT)
	private Icon[] field_94600_b;

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
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer player, int par4)
	{
		int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;

		RpgInv rpg = mod_RpgInventory.proxy.getInventory(player.username);

		ItemStack shield = rpg.getJewelInSlot(1);

		ArrowLooseEvent event = new ArrowLooseEvent(player, par1ItemStack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		var6 = event.charge;

		boolean var5 = player.capabilities.isCreativeMode;

		ItemStack var3 = player.inventory.armorItemInSlot(3);
		ItemStack var2 = player.inventory.armorItemInSlot(2);
		ItemStack var1 = player.inventory.armorItemInSlot(1);
		ItemStack var0 = player.inventory.armorItemInSlot(0);

		if (var3 !=null && var2 !=null && var1 != null && var0 !=null)
		{
			Item item = var3.getItem();
			Item item1 = var2.getItem();
			Item item2 = var1.getItem();
			Item item3 = var0.getItem();

			if(item.equals(mod_RpgInventory.archerhood) && item1.equals(mod_RpgInventory.archerchest)
					&& item2.equals(mod_RpgInventory.archerpants)&& item3.equals(mod_RpgInventory.archerboots))
			{
				if(player.inventory.hasItem(Item.arrow.itemID) || shield != null && shield.itemID == mod_RpgInventory.archersShield.itemID)
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

					EntityArrow var8 = new EntityArrow(par2World, player, var7 * 2.0F);


					if(par2World.isRemote)
					{
						var8.setIsCritical(true);
						var8.setDamage(var8.getDamage() + (double)1);
						var8.setKnockbackStrength(1);
						var8.setFire(10);
						var8.canBePickedUp = 2;   

						par1ItemStack.damageItem(1, player);
						par2World.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

						if (!par2World.isRemote)
						{
							par2World.
							spawnEntityInWorld(var8);
							par1ItemStack.damageItem(2, player);

							if(shield != null && shield.itemID == mod_RpgInventory.archersShield.itemID)
							{

							}
							else if(player.inventory.hasItem(Item.arrow.itemID))
							{
								player.inventory.consumeInventoryItem(Item.arrow.itemID);
							}

						}
					}
					else
					{
						var8.setIsCritical(true);
						var8.setDamage(var8.getDamage() + (double)2 * 0.5D + 0.5D);
						var8.setKnockbackStrength(2);
						var8.setFire(30);
						var8.canBePickedUp = 2;   

						par1ItemStack.damageItem(1, player);
						par2World.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

						if (!par2World.isRemote)
						{
							par2World.spawnEntityInWorld(var8);
							par1ItemStack.damageItem(2, player);

							if(shield != null && shield.itemID == mod_RpgInventory.archersShield.itemID)
							{

							}
							else if(player.inventory.hasItem(Item.arrow.itemID))
							{
								player.inventory.consumeInventoryItem(Item.arrow.itemID);
							}
						}
					}
				}
			}
		}
	}


	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer player)
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
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{

		ArrowNockEvent event = new ArrowNockEvent(player, par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return event.result;
		}

		if (player.capabilities.isCreativeMode || player.inventory.hasItem(mod_RpgInventory.elfbow.itemID))
		{
			player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
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
	
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		super.func_94581_a(par1IconRegister);
		this.field_94600_b = new Icon[field_94601_a.length];

		for (int i = 0; i < this.field_94600_b.length; ++i)
		{
			this.field_94600_b[i] = par1IconRegister.func_94245_a(field_94601_a[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon func_94599_c(int par1)
	{
		return this.field_94600_b[par1];
	}

	public String getTextureFile()
	{
		return "/subaraki/RPGinventoryTM.png";
	}
}
