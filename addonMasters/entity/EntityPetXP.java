/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class EntityPetXP extends EntityXPOrb {

	IPet closestPet;

	public EntityPetXP(World par1World) {
		super(par1World);
	}

	public EntityPetXP(World par1World, double x, double y, double z, int amount) {
		super(par1World, x, y, z, amount);
	}

	public IPet getClosestPet(World world, double par1, double par3, double par5) {
		double oldDistance = -1.0D;
		double searchdistance = 20;
		IPet var11 = null;
		List<IPet> pets = world.getEntitiesWithinAABB(IPet.class,
				boundingBox.expand(14, 14, 14));
		for (IPet thisPet : pets) {
			double newDistance = ((EntityLiving) thisPet).getDistanceSq(par1,
					par3, par5);
			if (((searchdistance < 0.0D) || (newDistance < (searchdistance * searchdistance)))
					&& ((oldDistance == -1.0D) || (newDistance < oldDistance))) {
				oldDistance = newDistance;
				var11 = thisPet;
			}
		}
		return var11;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		// Do Nothing
	}

	@Override
	public void onUpdate() {
		this.xpColor = 0xFF2222;
		if (this.field_70532_c > 0)
			--this.field_70532_c;

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.029999999329447746D;

		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX),
				MathHelper.floor_double(this.posY),
				MathHelper.floor_double(this.posZ)).getMaterial() == Material.lava) {
			this.motionY = 0.20000000298023224D;
			this.motionX = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
			this.motionZ = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
			this.playSound("random.fizz", 0.4F,
					2.0F + (this.rand.nextFloat() * 0.4F));
		}

		// this.()(this.posX,
		// (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D,
		// this.posZ);
		double var1 = 8.0D;

		if ((this.closestPet == null)
				|| (((EntityLiving) this.closestPet)
						.getDistanceSqToEntity(this) > (var1 * var1)))
			this.closestPet = getClosestPet(worldObj, this.posX, this.posY,
					this.posZ);

		if (this.closestPet != null) {
			double var3 = (((EntityLiving) this.closestPet).posX - this.posX)
					/ var1;
			double var5 = ((((EntityLiving) this.closestPet).posY + ((EntityLiving) this.closestPet)
					.getEyeHeight()) - this.posY) / var1;
			double var7 = (((EntityLiving) this.closestPet).posZ - this.posZ)
					/ var1;
			double var9 = Math.sqrt((var3 * var3) + (var5 * var5)
					+ (var7 * var7));
			double var11 = 1.0D - var9;

			if (var11 > 0.0D) {
				var11 *= var11;
				this.motionX += (var3 / var9) * var11 * 0.4D;
				this.motionY += (var5 / var9) * var11 * 0.1D;
				this.motionZ += (var7 / var9) * var11 * 0.4D;
			}
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		float var13 = 0.98F;

		if (this.onGround) {
			var13 = 0.58800006F;
			Block var4 = this.worldObj.getBlock(
					MathHelper.floor_double(this.posX),
					MathHelper.floor_double(this.boundingBox.minY) - 1,
					MathHelper.floor_double(this.posZ));

			if (var4 != Blocks.air)
				var13 = var4.slipperiness * 0.98F;
		}

		this.motionX *= var13;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= var13;

		if (this.onGround)
			this.motionY *= -0.8999999761581421D;

		++this.xpColor;
		++this.xpOrbAge;

		if (this.xpOrbAge >= 6000)
			this.setDead();
	}
}
