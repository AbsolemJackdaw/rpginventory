package rpgRogueBeast.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTeleportStone extends EntityThrowable
{
	public EntityTeleportStone(World par1World)
	{
		super(par1World);
	}

	public EntityTeleportStone(World par1World, EntityLivingBase par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	@SideOnly(Side.CLIENT)
	public EntityTeleportStone(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		if (par1MovingObjectPosition.entityHit != null)
		{
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);

			if (this.getThrower() != null)
			{
				RpgInv rpg = mod_RpgInventory.proxy.getInventory(((EntityPlayer)getThrower()).username);

				if(rpg.getShield() != null && rpg.getShield().getItem() == mod_RpgInventory.daggers)
				{
					if(par1MovingObjectPosition.entityHit instanceof EntityPlayer)
					{
						EntityPlayer player = (EntityPlayer) par1MovingObjectPosition.entityHit;
						player.addPotionEffect(new PotionEffect(Potion.blindness.id, 5*20 ,2));
					}
				}
			}

		}

		for (int i = 0; i < 32; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle("largesmoke", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
		}

		if (!this.worldObj.isRemote)
		{
			if (this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP)
			{
				EntityPlayerMP entityplayermp = (EntityPlayerMP)this.getThrower();

				if (!entityplayermp.playerNetServerHandler.connectionClosed && entityplayermp.worldObj == this.worldObj)
				{
					this.getThrower().setPositionAndUpdate(this.posX, this.posY, this.posZ);
					this.getThrower().fallDistance = 0.0F;

				}
			}

			this.setDead();
		}
	}
}
