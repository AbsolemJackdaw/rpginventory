package RpgInventory.weapons.staf;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import RpgInventory.AARpgBaseClass;

public class ItemStaf extends Item {

	private int field_70846_g;

	protected boolean hasAttacked = false;

	public ItemStaf(int par1) {
		super(par1);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		ItemStack var33 = ((EntityPlayer)par3EntityLiving).inventory.armorItemInSlot(3);
		ItemStack var32 = ((EntityPlayer)par3EntityLiving).inventory.armorItemInSlot(2);
		ItemStack var31 = ((EntityPlayer)par3EntityLiving).inventory.armorItemInSlot(1);
		ItemStack var30 = ((EntityPlayer)par3EntityLiving).inventory.armorItemInSlot(0);

		if (var33 !=null && var32 !=null && var31 != null && var30 !=null)
		{
			Item item  = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if(item.equals(AARpgBaseClass.magehood) && item1.equals(AARpgBaseClass.magegown)
					&& item2.equals(AARpgBaseClass.magepants)&& item3.equals(AARpgBaseClass.mageboots))
			{
				if(par2EntityLiving instanceof EntityPlayer && ((EntityPlayer)par3EntityLiving).experienceLevel < 30)
				{
					((EntityPlayer)par2EntityLiving).heal(5);
					((EntityPlayer)par2EntityLiving).addChatMessage(((EntityPlayer)par3EntityLiving).username +" healed you !");

					double var2 = par2EntityLiving.worldObj.rand.nextGaussian() * 0.02D;
					double var4 = par2EntityLiving.worldObj.rand.nextGaussian() * 0.02D;
					double var6 = par2EntityLiving.worldObj.rand.nextGaussian() * 0.02D;
					((EntityPlayer)par2EntityLiving).worldObj.spawnParticle("heart", par2EntityLiving.posX + (double)(par2EntityLiving.worldObj.rand.nextFloat() * par2EntityLiving.width * 2.0F) - (double)par2EntityLiving.width, par2EntityLiving.posY + 0.5D + (double)(par2EntityLiving.worldObj.rand.nextFloat() * par2EntityLiving.height), par2EntityLiving.posZ + (double)(par2EntityLiving.worldObj.rand.nextFloat() * par2EntityLiving.width * 2.0F) - (double)par2EntityLiving.width, var2, var4, var6);
				}
				else if(par2EntityLiving instanceof EntityPlayer && ((EntityPlayer)par3EntityLiving).experienceLevel > 30)
				{
					((EntityPlayer)par2EntityLiving).addPotionEffect(new PotionEffect(Potion.regeneration.id,80,1));
					((EntityPlayer)par2EntityLiving).addChatMessage(((EntityPlayer)par3EntityLiving).username +" gave you Regeneration !");

					double var2 = par2EntityLiving.worldObj.rand.nextGaussian() * 0.02D;
					double var4 = par2EntityLiving.worldObj.rand.nextGaussian() * 0.02D;
					double var6 = par2EntityLiving.worldObj.rand.nextGaussian() * 0.02D;
					((EntityPlayer)par2EntityLiving).worldObj.spawnParticle("heart", par2EntityLiving.posX + (double)(par2EntityLiving.worldObj.rand.nextFloat() * par2EntityLiving.width * 2.0F) - (double)par2EntityLiving.width, par2EntityLiving.posY + 0.5D + (double)(par2EntityLiving.worldObj.rand.nextFloat() * par2EntityLiving.height), par2EntityLiving.posZ + (double)(par2EntityLiving.worldObj.rand.nextFloat() * par2EntityLiving.width * 2.0F) - (double)par2EntityLiving.width, var2, var4, var6);
				}
				else
				{
					par2EntityLiving.attackEntityFrom(DamageSource.magic, 7);
				}
			}
		}
		return false;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer p, int par4)
	{
		int time = this.getMaxItemUseDuration(par1ItemStack) - par4;

		//System.out.println(time);

		if(p.isSneaking())
		{
			if(time > 0 && time <= 100)
			{
				p.spawnExplosionParticle();
				double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
				p.worldObj.spawnParticle("heart", p.posX + (double)(p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double)p.width, p.posY + 0.5D + (double)(p.worldObj.rand.nextFloat() * p.height), p.posZ + (double)(p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double)p.width, var2, var4, var6);
				p.heal((int)time/50);
			}
			if(time > 100)
			{
				p.spawnExplosionParticle();
				double var2 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var4 = p.worldObj.rand.nextGaussian() * 0.02D;
				double var6 = p.worldObj.rand.nextGaussian() * 0.02D;
				p.worldObj.spawnParticle("heart", p.posX + (double)(p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double)p.width, p.posY + 0.5D + (double)(p.worldObj.rand.nextFloat() * p.height), p.posZ + (double)(p.worldObj.rand.nextFloat() * p.width * 2.0F) - (double)p.width, var2, var4, var6);
				p.heal((int)time/30);
			}
		}
		//		else
		//		{
		//			attackEntity(p, (float)time, p);
		//			System.out.println(time);
		//		}
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer p)
	{
		ItemStack var33 = p.inventory.armorItemInSlot(3);
		ItemStack var32 = p.inventory.armorItemInSlot(2);
		ItemStack var31 = p.inventory.armorItemInSlot(1);
		ItemStack var30 = p.inventory.armorItemInSlot(0);

		if (var33 !=null && var32 !=null && var31 != null && var30 !=null)
		{
			Item item  = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if(item.equals(AARpgBaseClass.magehood) && item1.equals(AARpgBaseClass.magegown)
					&& item2.equals(AARpgBaseClass.magepants)&& item3.equals(AARpgBaseClass.mageboots))
			{
				p.setItemInUse(is, this.getMaxItemUseDuration(is));
			}
		}
		return is;
	}

	//	protected void attackEntity(Entity par1Entity, float par2, EntityPlayer p)
	//	{
	//		if (par2 < 30.0F)
	//		{
	//			double var3 = par1Entity.posX - p.posX;
	//			double var5 = par1Entity.boundingBox.minY + (double)(par1Entity.height / 2.0F) - (p.posY + (double)(p.height / 2.0F));
	//			double var7 = par1Entity.posZ - p.posZ;
	//
	//			float var9 = MathHelper.sqrt_float(par2) * 0.5F;
	//			p.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)p.posX, (int)p.posY, (int)p.posZ, 0);
	//
	//			for (int var10 = 0; var10 < 1; ++var10)
	//			{
	//				EntitySmallFireball var11 = new EntitySmallFireball(p.worldObj,p, 0d,0d,0d);
	//				var11.posY = p.posY + (double)(p.height / 2.0F) + 0.5D;
	//				p.worldObj.spawnEntityInWorld(var11);
	//			}
	//		}
	//		hasAttacked = true;
	//}

	public int getStrenght(EntityPlayer p)
	{
		return p.experienceLevel == 0 ? 1 : (int)p.experienceLevel;
	}
	public String getTextureFile()
	{
		return "/subaraki/RPGinventoryTM.png";
	}
}
