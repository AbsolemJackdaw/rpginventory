package addonBasic.items.weapons;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.item.ItemRpgWeapon;
import addonBasic.RpgBaseAddon;

public class ItemSoulSphere extends ItemRpgWeapon {

	Random rand = new Random();

	public ItemSoulSphere() {
		super();
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
			Entity entity) {

		if(entity instanceof EntityPlayer){
			EntityPlayer other = (EntityPlayer)entity;
			other.addPotionEffect(new PotionEffect(Potion.resistance.id, 800, 1));
			stack.damageItem(10, player);
			return true;
		}

		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
		par1ItemStack.damageItem(1, par2EntityLiving);

		EntityPlayer player = (EntityPlayer) par3EntityLiving;
		if(RpgInventoryMod.playerClass.equals(RpgBaseAddon.CLASSALCHEMIST)){

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

		return false;

	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,EntityPlayer par3EntityPlayer) {

		if(RpgInventoryMod.playerClass.equals(RpgBaseAddon.CLASSALCHEMIST)){

			if (par3EntityPlayer.inventory.hasItem(Items.blaze_powder)) {

				Vec3 look = par3EntityPlayer.getLookVec();
				EntitySmallFireball ball = new EntitySmallFireball(par2World, par3EntityPlayer, 1, 1, 1);
				ball.setPosition(par3EntityPlayer.posX + (look.xCoord * 1),par3EntityPlayer.posY + (look.yCoord * 1) + 1.5,par3EntityPlayer.posZ + (look.zCoord * 1));
				ball.accelerationX = look.xCoord * 0.1;
				ball.accelerationY = look.yCoord * 0.1;
				ball.accelerationZ = look.zCoord * 0.1;

				if (!par2World.isRemote) {
					par2World.spawnEntityInWorld(ball);
				}
				par1ItemStack.damageItem(1, par3EntityPlayer);

				if(!par3EntityPlayer.capabilities.isCreativeMode || !RpgInventoryMod.donators.contains(par3EntityPlayer.getCommandSenderName()))
					par3EntityPlayer.inventory.consumeInventoryItem(Items.blaze_powder);
			}
		}
		return par1ItemStack;
	}

	@Override
	public boolean onItemUse(ItemStack stack,EntityPlayer player, World world, int x, int y,int z, int par7, float par8, float par9, float par10) {

		if(RpgInventoryMod.playerClass.equals(RpgBaseAddon.CLASSALCHEMIST)){
			Block b = world.getBlock(x, y, z);

			if(b instanceof BlockOre){
				if(b.equals(Blocks.gold_ore))
					return true;

				world.setBlockToAir(x, y, z);
				if(b.equals(Blocks.lapis_ore))
					world.setBlock(x, y, z, Blocks.lapis_block);
				else
					world.setBlock(x, y, z, Blocks.gold_ore);

				world.markBlockForUpdate(x, y, z);
				stack.damageItem(7, player);
			}
		}

		return true;
	}
}
