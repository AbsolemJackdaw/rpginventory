package RpgInventory.weapons.hammer;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import RpgInventory.AARpgBaseClass;

public class ItemHammer extends Item{

	private EnumToolMaterial toolMaterial;
	private int weaponDamage;
	Random rand = new Random();
	int damage;
	public ItemHammer(int par1, EnumToolMaterial mat) {
		super(par1);
		this.toolMaterial = mat;
		this.maxStackSize = 1;
		this.setMaxDamage(mat.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.weaponDamage = 4 + mat.getDamageVsEntity();
			damage =4;

	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		ItemStack var3 = player.inventory.armorItemInSlot(3);
		ItemStack var2 = player.inventory.armorItemInSlot(2);
		ItemStack var1 = player.inventory.armorItemInSlot(1);
		ItemStack var0 = player.inventory.armorItemInSlot(0);

		if (var3 !=null && var2 !=null && var1 != null && var0 !=null)
		{
			Item item  = var3.getItem();
			Item item1 = var2.getItem();
			Item item2 = var1.getItem();
			Item item3 = var0.getItem();

			if(item.equals(AARpgBaseClass.ramboband) && item1.equals(AARpgBaseClass.rambobody)
					&& item2.equals(AARpgBaseClass.rambolegs)&& item3.equals(AARpgBaseClass.rambofeet))
			{
				if(player.getFoodStats().getFoodLevel() < 4 
						||player.getHealth() < 4)
				{

					player.addPotionEffect(new PotionEffect(Potion.damageBoost.id,200,3));
					damage = 10;
				}
			}
			par1ItemStack.damageItem(1, player);
		}
		return par1ItemStack;
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		par1ItemStack.damageItem(2, par2EntityLiving);
		EntityPlayer player = (EntityPlayer)par3EntityLiving;



		ItemStack var3 = player.inventory.armorItemInSlot(3);
		ItemStack var2 = player.inventory.armorItemInSlot(2);
		ItemStack var1 = player.inventory.armorItemInSlot(1);
		ItemStack var0 = player.inventory.armorItemInSlot(0);

		if (var3 !=null && var2 !=null && var1 != null && var0 !=null)
		{
			Item item  = var3.getItem();
			Item item1 = var2.getItem();
			Item item2 = var1.getItem();
			Item item3 = var0.getItem();

			if(item.equals(AARpgBaseClass.ramboband) && item1.equals(AARpgBaseClass.rambobody)
					&& item2.equals(AARpgBaseClass.rambolegs)&& item3.equals(AARpgBaseClass.rambofeet))
			{
				int var4;

				var4 =5;
				var4 += EnchantmentHelper.getKnockbackModifier(par3EntityLiving, par2EntityLiving);
				if (var4 > 0)
				{
					par2EntityLiving.addVelocity((double)(-MathHelper.sin(par3EntityLiving.rotationYaw * (float)Math.PI / 180.0F) * (float)var4 * 0.5F), 0.1D, (double)(MathHelper.cos(par3EntityLiving.rotationYaw * (float)Math.PI / 180.0F) * (float)var4 * 0.5F));
					par3EntityLiving.motionX *= 0.6D;
					par3EntityLiving.motionZ *= 0.6D;
					par2EntityLiving.attackEntityFrom(DamageSource.cactus, damage);
				}
			}
		}
		return false;
	}
	public String getTextureFile()
	{
		return "/subaraki/RPGinventoryTM.png";
	}
}
