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
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgRogueBeast.mod_RpgRB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTeleportStone extends EntityThrowable {
	public EntityTeleportStone(World par1World) {
		super(par1World);
	}

	@SideOnly(Side.CLIENT)
	public EntityTeleportStone(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}

	public EntityTeleportStone(World par1World,
			EntityLivingBase par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			par1MovingObjectPosition.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, this.getThrower()), 0);

			if (this.getThrower() != null) {
				PlayerRpgInventory inv = PlayerRpgInventory
						.get((EntityPlayer) getThrower());

				if ((inv.getShield() != null)
						&& (inv.getShield().getItem() == mod_RpgRB.daggers)) {
					if (par1MovingObjectPosition.entityHit instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) par1MovingObjectPosition.entityHit;
						player.addPotionEffect(new PotionEffect(
								Potion.blindness.id, mod_RpgInventory.donators
										.contains(player.username) ? 5 * 20
										: 3 * 20, 2));
					}
				}
			}

		}

		for (int i = 0; i < 32; ++i) {
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle("largesmoke",
					(this.posX + (this.rand.nextFloat() * this.width * 2.0F))
							- this.width,
					this.posY + 0.5D + (this.rand.nextFloat() * this.height),
					(this.posZ + (this.rand.nextFloat() * this.width * 2.0F))
							- this.width, d0, d1, d2);
		}

		if (!this.worldObj.isRemote) {
			if ((this.getThrower() != null)
					&& (this.getThrower() instanceof EntityPlayerMP)) {
				EntityPlayerMP entityplayermp = (EntityPlayerMP) this
						.getThrower();

				if (!entityplayermp.playerNetServerHandler.connectionClosed
						&& (entityplayermp.worldObj == this.worldObj)) {
					this.getThrower().setPositionAndUpdate(this.posX,
							this.posY, this.posZ);
					this.getThrower().fallDistance = 0.0F;

				}
			}

			this.setDead();
		}
	}
}
