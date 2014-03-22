package rpgInventory.item.weapons;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.config.RpgConfig;

public class ItemMageSphere extends ItemRpgWeapon {

	Random rand = new Random();

	// Player can : Heal, with speckled melon, and sneaking
	// Change time, with glowstonedust and sneaking
	// Call forth a fireball with a blazepowder
	// change ores with gold ingot

	public ItemMageSphere() {
		super();
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
		par1ItemStack.damageItem(1, par2EntityLiving);

		EntityPlayer player = (EntityPlayer) par3EntityLiving;

		ItemStack var33 = player.inventory.armorItemInSlot(3);
		ItemStack var32 = player.inventory.armorItemInSlot(2);
		ItemStack var31 = player.inventory.armorItemInSlot(1);
		ItemStack var30 = player.inventory.armorItemInSlot(0);

		if ((var33 != null) && (var32 != null) && (var31 != null)
				&& (var30 != null)) {
			Item item = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if (item.equals(mod_RpgInventory.magehood)
					&& item1.equals(mod_RpgInventory.magegown)
					&& item2.equals(mod_RpgInventory.magepants)
					&& item3.equals(mod_RpgInventory.mageboots)) {
				int k = Item.itemRand.nextInt(5);
				switch (k) {
				case 0:
					par2EntityLiving.setFire(40);
					break;
				case 1:
					par2EntityLiving.motionY = 0.8f;
					break;
				case 3:
					par2EntityLiving.attackEntityFrom(DamageSource.magic, 4);
					break;
				default:
				}
			}
		}

		return false;

	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		ItemStack var33 = par3EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var32 = par3EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var31 = par3EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var30 = par3EntityPlayer.inventory.armorItemInSlot(0);

		if ((var33 != null) && (var32 != null) && (var31 != null)
				&& (var30 != null)) {
			Item item = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if (item.equals(mod_RpgInventory.magehood)
					&& item1.equals(mod_RpgInventory.magegown)
					&& item2.equals(mod_RpgInventory.magepants)
					&& item3.equals(mod_RpgInventory.mageboots)) {
				if (par3EntityPlayer.inventory.hasItem(Items.blaze_powder)
						|| mod_RpgInventory.donators.contains(par3EntityPlayer
								.getDisplayName())) {

					Vec3 look = par3EntityPlayer.getLookVec();
					EntitySmallFireball ball = new EntitySmallFireball(
							par2World, par3EntityPlayer, 1, 1, 1);
					ball.setPosition(par3EntityPlayer.posX + (look.xCoord * 1),
							par3EntityPlayer.posY + (look.yCoord * 1) + 1.5,
							par3EntityPlayer.posZ + (look.zCoord * 1));
					ball.accelerationX = look.xCoord * 0.1;
					ball.accelerationY = look.yCoord * 0.1;
					ball.accelerationZ = look.zCoord * 0.1;

					if (!par2World.isRemote) {
						par2World.spawnEntityInWorld(ball);
					}
					par1ItemStack.damageItem(5, par3EntityPlayer);
				}
			}
		}
		return par1ItemStack;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		ItemStack var33 = par2EntityPlayer.inventory.armorItemInSlot(3);
		ItemStack var32 = par2EntityPlayer.inventory.armorItemInSlot(2);
		ItemStack var31 = par2EntityPlayer.inventory.armorItemInSlot(1);
		ItemStack var30 = par2EntityPlayer.inventory.armorItemInSlot(0);

		if ((var33 != null) && (var32 != null) && (var31 != null)
				&& (var30 != null)) {
			Item item = var33.getItem();
			Item item1 = var32.getItem();
			Item item2 = var31.getItem();
			Item item3 = var30.getItem();

			if (item.equals(mod_RpgInventory.magehood)
					&& item1.equals(mod_RpgInventory.magegown)
					&& item2.equals(mod_RpgInventory.magepants)
					&& item3.equals(mod_RpgInventory.mageboots)) {

				if ((RpgConfig.instance.useSpell == true)
						&& par2EntityPlayer.inventory
								.hasItem(Items.glowstone_dust)
						&& par2EntityPlayer.isSneaking()) {
					if (par3World.isDaytime()) {
						par3World.setWorldTime(13000);
						par1ItemStack.damageItem(1, par2EntityPlayer);
					} else {
						par3World.setWorldTime(300);
						par1ItemStack.damageItem(1, par2EntityPlayer);
					}
				}

				if (RpgConfig.instance.useSpell == false) {
					// TODO
					// par2EntityPlayer
					// .addChatMessage("You can't use the Day/Night Cycle Spell on this Server !");
				}

				if (par2EntityPlayer.inventory.hasItem(Items.speckled_melon)
						&& par2EntityPlayer.isSneaking()) {
					if (!par3World.isRemote) {
						par2EntityPlayer.inventory
								.consumeInventoryItem(Items.speckled_melon);
						par2EntityPlayer.addPotionEffect(new PotionEffect(
								Potion.regeneration.id, 200, 0));
					}
				}

				if (par2EntityPlayer.inventory.hasItem(Items.gold_ingot)) {
					int var1 = (int) par2EntityPlayer.posX;
					int var2 = (int) par2EntityPlayer.posY;
					int var3 = (int) par2EntityPlayer.posZ;
					Block var13 = par3World.getBlock(par4, par5, par6);

					for (int x = 0; x < 1; x++) {

						Block var12 = par3World.getBlock(par4, par5, par6);

						if (par5 > var2) {
							par3World.getBlock(par4, par5, par6);
						}

						if (par5 < var2) {
							par3World.getBlock(par4, par5, par6);
						}
						if ((par4 < var1) && (par6 == var3) && (par5 == var2)) {
							par3World.getBlock(par4 - x, par5, par6);
						}
						if ((par4 > var1) && (par6 == var3) && (par5 == var2)) {
							par3World.getBlock(par4 + x, par5, par6);
						}
						if ((par6 < var3) && (par5 == var2)) {
							par3World.getBlock(par4, par5, par6 - x);
						}
						if ((par6 > var3) && (par5 == var2)) {
							par3World.getBlock(par4, par5, par6 + x);
						}

						if ((var12 == Blocks.iron_ore)
								|| (var12 == Blocks.coal_ore)
								|| (var12 == Blocks.diamond_ore)) {
							par3World.setBlock(par4, par5, par6,
									Blocks.gold_ore);
							par1ItemStack.damageItem(4, par2EntityPlayer);
						}
						if (var12 == Blocks.lapis_ore) {
							par3World.setBlock(par4, par5, par6,
									Blocks.lapis_block);
							par1ItemStack.damageItem(
									mod_RpgInventory.donators
											.contains(par2EntityPlayer
													.getDisplayName()) ? 50
											: 151, par2EntityPlayer);
						}

					}
				}
			}
		}
		return false;
	}

	private void setThrowableHeading(EntityLargeFireball ball, double par1,
			double par3, double par5, float par7, float par8) {
		float f2 = MathHelper.sqrt_double((par1 * par1) + (par3 * par3)
				+ (par5 * par5));
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		par1 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1)
				* 0.007499999832361937D * par8;
		par3 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1)
				* 0.007499999832361937D * par8;
		par5 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1)
				* 0.007499999832361937D * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		ball.motionX = par1;
		ball.motionY = par3;
		ball.motionZ = par5;
		float f3 = MathHelper.sqrt_double((par1 * par1) + (par5 * par5));
		ball.prevRotationYaw = ball.rotationYaw = (float) ((Math.atan2(par1,
				par5) * 180.0D) / Math.PI);
		ball.prevRotationPitch = ball.rotationPitch = (float) ((Math.atan2(
				par3, f3) * 180.0D) / Math.PI);
	}
}
