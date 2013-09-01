package rpgInventory.item.weapons;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;

public class ItemMageSphere extends ItemRpgWeapon{

	public ItemMageSphere(int par1) {
		super(par1);
	}

	// Player can : Heal, with speckled melon, and sneaking
	//				Change time, with glowstonedust and sneaking
	//				Call forth a fireball with a blazepowder
	//				change ores with gold ingot

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		ItemStack var33 = par3EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var32 = par3EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var31 = par3EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var30 = par3EntityPlayer.inventory.armorItemInSlot(0);

		if (var33 !=null && var32 !=null && var31 != null && var30 !=null)
		{
			Item item  = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if(item.equals(mod_RpgInventory.magehood) && item1.equals(mod_RpgInventory.magegown)
					&& item2.equals(mod_RpgInventory.magepants)&& item3.equals(mod_RpgInventory.mageboots))
			{
				if(par3EntityPlayer.inventory.hasItem(Item.blazePowder.itemID))
				{

					double var18 = 2.0D;
					Vec3 var20 = par3EntityPlayer.getLook(1.0F);
					EntityLargeFireball var17 = new EntityLargeFireball(par2World);
					var17.posX = par3EntityPlayer.posX + var20.xCoord*var18;
					var17.posY = par3EntityPlayer.posY +  var20.yCoord+1.5;
					var17.posZ = par3EntityPlayer.posZ + var20.zCoord*var18;
					if(!par2World.isRemote)
					{
						par2World.spawnEntityInWorld(var17);
					}
					par1ItemStack.damageItem(5, par3EntityPlayer);
				}
			}
		}
		return par1ItemStack;
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		ItemStack var33 = par2EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var32 = par2EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var31 = par2EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var30 = par2EntityPlayer.inventory.armorItemInSlot(0);

		if (var33 !=null && var32 !=null && var31 != null && var30 !=null)
		{
			Item item  = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if(item.equals(mod_RpgInventory.magehood) && item1.equals(mod_RpgInventory.magegown)
					&& item2.equals(mod_RpgInventory.magepants)&& item3.equals(mod_RpgInventory.mageboots))
			{

				if (RpgConfig.instance.useSpell == true && par2EntityPlayer.inventory.hasItem(Item.glowstone.itemID) && par2EntityPlayer.isSneaking())
				{
					if(par3World.isDaytime())
					{
						par3World.setWorldTime(13000);
						par1ItemStack.damageItem(1, par2EntityPlayer);
					}
					else
					{
						par3World.setWorldTime(300);
						par1ItemStack.damageItem(1, par2EntityPlayer);
					}
				}

				if(RpgConfig.instance.useSpell == false)
				{
					par2EntityPlayer.addChatMessage("You can't use the Day/Night Cycle Spell on this Server !");
				}


				if (par2EntityPlayer.inventory.hasItem(Item.speckledMelon.itemID) && par2EntityPlayer.isSneaking())
				{
					if(!par3World.isRemote){
						par2EntityPlayer.inventory.consumeInventoryItem(Item.speckledMelon.itemID);
						par2EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id,200,0));
					}
				}

				if(par2EntityPlayer.inventory.hasItem(Item.ingotGold.itemID))
				{
					int var1 = (int)par2EntityPlayer.posX;
					int var2 = (int)par2EntityPlayer.posY;
					int var3 = (int)par2EntityPlayer.posZ;      
					int var13 = par3World.getBlockId(par4, par5, par6);

					for ( int x = 0; x < 1; x++ )
					{

						int var12 = par3World.getBlockId(par4 , par5 , par6);

						if(par5 > var2 )
						{
							par3World.getBlockId(par4, par5, par6);
						}

						if(par5 < var2 )
						{
							par3World.getBlockId(par4, par5, par6);
						}
						if(par4 < var1  && par6 == var3 && par5 == var2)
						{
							par3World.getBlockId(par4 - x, par5    , par6);
						}
						if(par4 > var1 && par6 == var3 && par5 == var2)
						{
							par3World.getBlockId(par4 + x, par5,    par6);
						}
						if(par6 < var3 && par5 == var2 )
						{
							par3World.getBlockId(par4, par5,     par6 - x);
						}
						if(par6 > var3 && par5 == var2 )
						{
							par3World.getBlockId(par4, par5,    par6 + x);
						}

						if (var12 == Block.oreIron.blockID || var12 == Block.oreCoal.blockID || var12 == Block.oreDiamond.blockID)
						{
							par3World.setBlock(par4, par5, par6, Block.oreGold.blockID);
							par1ItemStack.damageItem(4, par2EntityPlayer);
						}
						if( var12 == Block.oreLapis.blockID)
						{
							par3World.setBlock(par4, par5, par6, Block.blockLapis.blockID);
							par1ItemStack.damageItem(151, par2EntityPlayer);
						}

					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
		par1ItemStack.damageItem(1, par2EntityLiving);

		EntityPlayer player = (EntityPlayer)par3EntityLiving;

		ItemStack var33 = player.inventory.armorItemInSlot(3);
		ItemStack var32 = player.inventory.armorItemInSlot(2);
		ItemStack var31 = player.inventory.armorItemInSlot(1);
		ItemStack var30 = player.inventory.armorItemInSlot(0);

		if (var33 !=null && var32 !=null && var31 != null && var30 !=null)
		{
			Item item  = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if(item.equals(mod_RpgInventory.magehood) && item1.equals(mod_RpgInventory.magegown)
					&& item2.equals(mod_RpgInventory.magepants)&& item3.equals(mod_RpgInventory.mageboots))
			{
				int k = this.itemRand.nextInt(5);
				switch(k)
				{
				case 0: par2EntityLiving.setFire(40);
				break;
				case 1: par2EntityLiving.motionY = 0.8f;
				break;
				case 3: par2EntityLiving.attackEntityFrom(DamageSource.magic, 4);
				break;
				default:
				}
			}
		}

		return false;

	}
}
