package addonArchmage;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityElementalBlock extends EntityThrowable implements IEntityAdditionalSpawnData {

	public String owner;

	public int size = 0;
	public int type = 1;
	public int contacts = 0;
	public int sizeLimit = 10;
	public int step = 0;

	public EntityElementalBlock(World world) {
		super(world);
	}

	public EntityElementalBlock(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6);
	}

	public EntityElementalBlock(World w, EntityLivingBase e, float p, int type,
			int sizeLimit) {
		super(w, e);
		this.sizeLimit = sizeLimit;
		this.type = type;
		this.noClip = true;

	}

	@Override
	public float getGravityVelocity() {
		return 0F;
	}

	public float getRadius() {
		return this.size * 0.3F;
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		specialAttack(var1, this.type);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.size++;
		if (this.size >= this.sizeLimit) {
			MovingObjectPosition pos = new MovingObjectPosition((int) this.posX, (int) this.posY, (int) this.posZ, 4,Vec3.createVectorHelper(this.posX, this.posY, this.posZ));
			this.onImpact(pos);
			this.setDead();
		}
		this.setSize(this.getRadius(), this.getRadius());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.size = nbt.getInteger("size_");
		this.sizeLimit = nbt.getInteger("sizeLimit_");

	}

	@Override
	public void readSpawnData(ByteBuf data) {
		this.size = data.readInt();
		this.type = data.readInt();
		this.step = data.readInt();
		this.sizeLimit = data.readInt();
	}

	public void specialAttack(MovingObjectPosition var1, int type) {

		EntityLivingBase p = this.getThrower();

		if (p == null) {
			this.setDead();
			return;
		}


		switch (type) {
		case 1:
			AxisAlignedBB pool = AxisAlignedBB.getBoundingBox(
					var1.hitVec.xCoord - getRadius(),
					var1.hitVec.yCoord - getRadius(),
					var1.hitVec.zCoord - getRadius(),
					var1.hitVec.xCoord + getRadius(),
					var1.hitVec.yCoord + getRadius(),
					var1.hitVec.zCoord + this.size);
			List<EntityLivingBase> entl = this.worldObj.getEntitiesWithinAABB(
					EntityLivingBase.class, pool);
			if ((entl != null) && (entl.size() > 0)) {
				for (EntityLivingBase el : entl) {
					if ((el != null) && (el != p)) {
						el.setFire(30);
						if (this.getThrower() instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) this.getThrower()), 1);
							el.attackEntityFrom(DamageSource.onFire, 2);
						} else {
							el.attackEntityFrom(DamageSource.onFire, 2);
						}

					}
				}
			}
			if (var1.typeOfHit == MovingObjectType.BLOCK) {
			} else if (var1.typeOfHit == MovingObjectType.ENTITY) {
				if (!(var1.entityHit instanceof EntityLivingBase)) {
					break;
				}
				if (var1.entityHit == p) {
					break;
				}
				var1.entityHit.setFire(30);
				if (p instanceof EntityPlayer) {
					var1.entityHit
					.attackEntityFrom(DamageSource
							.causePlayerDamage((EntityPlayer) p), 1);
					var1.entityHit.attackEntityFrom(DamageSource.onFire, 2);
				} else {
					var1.entityHit.attackEntityFrom(DamageSource.onFire, 2);
				}
			}
			break;
		case 2:
			if (!worldObj.isRemote) {
				AxisAlignedBB pool1 = AxisAlignedBB.getBoundingBox(
						var1.hitVec.xCoord - getRadius(),
						var1.hitVec.yCoord - getRadius(),
						var1.hitVec.zCoord - getRadius(),
						var1.hitVec.xCoord + getRadius(),
						var1.hitVec.yCoord + getRadius(),
						var1.hitVec.zCoord + this.size);
				List<EntityLivingBase> entl1 = this.worldObj.getEntitiesWithinAABB(
						EntityLivingBase.class, pool1);
				if ((entl1 != null) && (entl1.size() > 0)) {
					for (EntityLivingBase el : entl1) {
						if ((el != null) && (el != p)) {
							if (el.isEntityUndead()) {
								el.addPotionEffect(new PotionEffect(
										Potion.moveSlowdown.id, 120, 2));
								el.attackEntityFrom(DamageSource.magic, 4);
							} else {
								el.addPotionEffect(new PotionEffect(
										Potion.poison.id, 120, 2));
								el.addPotionEffect(new PotionEffect(
										Potion.moveSlowdown.id, 120, 2));
							}
							el.addPotionEffect(new PotionEffect(
									Potion.weakness.id, 120, 5));
							if (p instanceof EntityPlayer) {
								el.attackEntityFrom(DamageSource
										.causePlayerDamage((EntityPlayer) p), 1);
								el.attackEntityFrom(DamageSource.drown, 2);
							} else {
								el.attackEntityFrom(DamageSource.drown, 2);
							}

						}
					}
				}
				if (var1.typeOfHit == MovingObjectType.BLOCK) {
				} else if (var1.typeOfHit == MovingObjectType.ENTITY) {
					if (var1.entityHit == p) {
						break;
					}
					if (var1.entityHit instanceof EntityLivingBase) {
						EntityLivingBase el = (EntityLivingBase) var1.entityHit;
						if (el.isEntityUndead()) {
							if (!worldObj.isRemote) {
								el.addPotionEffect(new PotionEffect(
										Potion.moveSlowdown.id, 120, 2));
								el.attackEntityFrom(DamageSource.magic, 4);
							}
						} else if (!worldObj.isRemote) {
							el.addPotionEffect(new PotionEffect(
									Potion.poison.id, 120, 2));
							el.addPotionEffect(new PotionEffect(
									Potion.moveSlowdown.id, 120, 2));
						}
						if (!worldObj.isRemote) {
							el.addPotionEffect(new PotionEffect(
									Potion.weakness.id, 120, 5));
						}
						if (p instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource
									.causePlayerDamage((EntityPlayer) p), 1);
							el.attackEntityFrom(DamageSource.drown, 2);
						} else {
							el.attackEntityFrom(DamageSource.drown, 2);
						}
					}
				}
			}
			break;
		case 3:
			int dmg = (int) (5 + Math.floor(getRadius()));
			pool = AxisAlignedBB.getBoundingBox(
					var1.hitVec.xCoord - getRadius(),
					var1.hitVec.yCoord - getRadius(),
					var1.hitVec.zCoord - getRadius(),
					var1.hitVec.xCoord + getRadius(),
					var1.hitVec.yCoord + getRadius(),
					var1.hitVec.zCoord + this.size);
			entl = this.worldObj
					.getEntitiesWithinAABB(EntityLivingBase.class, pool);
			if ((entl != null) && (entl.size() > 0)) {
				for (EntityLivingBase el : entl) {
					if ((el != null) && (el != p)) {
						if (p instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource
									.causePlayerDamage((EntityPlayer) p), dmg);
						} else {
							el.attackEntityFrom(DamageSource.anvil, dmg);
						}
						if (!worldObj.isRemote) {
							el.addPotionEffect(new PotionEffect(
									Potion.blindness.id, 120, 2));
						}
					}
				}
			}
			if (var1.typeOfHit == MovingObjectType.ENTITY) {
				if (!(var1.entityHit instanceof EntityLivingBase)) {
					break;
				}
				EntityLivingBase el = (EntityLivingBase) var1.entityHit;
				if ((el != null) && (el != p)) {
					if (p instanceof EntityPlayer) {
						el.attackEntityFrom(DamageSource
								.causePlayerDamage((EntityPlayer) p), dmg);
					} else {
						el.attackEntityFrom(DamageSource.anvil, dmg);
					}
					if (!worldObj.isRemote) {
						el.addPotionEffect(new PotionEffect(
								Potion.blindness.id, 120, 2));
					}
				}
			}

			break;
		case 4:
			pool = AxisAlignedBB.getBoundingBox(
					var1.hitVec.xCoord - getRadius(),
					var1.hitVec.yCoord - getRadius(),
					var1.hitVec.zCoord - getRadius(),
					var1.hitVec.xCoord + getRadius(),
					var1.hitVec.yCoord + getRadius(),
					var1.hitVec.zCoord + this.size);
			entl = this.worldObj
					.getEntitiesWithinAABB(EntityLivingBase.class, pool);
			if ((entl != null) && (entl.size() > 0)) {
				for (EntityLivingBase el : entl) {
					if ((el != null) && (el != p)) {
						try {
							double xdir = el.posX - this.getThrower().posX;
							double zdir = el.posZ - this.getThrower().posZ;
							el.motionX = xdir * 0.1F;
							el.motionY = 0.1F;
							el.motionZ = zdir * 0.1F;
						} catch (Throwable ex) {
						}
					}
				}
			}
			if (var1.typeOfHit == MovingObjectType.ENTITY) {
				if (!(var1.entityHit instanceof EntityLivingBase)) {
					break;
				}
				EntityLivingBase el = (EntityLivingBase) var1.entityHit;
				if ((el != null) && (el != p)) {
					try {
						double xdir = el.posX - this.getThrower().posX;
						double zdir = el.posZ - this.getThrower().posZ;
						el.motionX = xdir * 0.1F;
						el.motionY = 0.1F;
						el.motionZ = zdir * 0.1F;
					} catch (Throwable ex) {
					}
				}
			}

			if ((p != null) && (p instanceof EntityPlayer)){

				EntityPlayer pl = (EntityPlayer)p;
				if ((pl.getDistanceToEntity(this) < 3) && ((pl.rotationPitch > 70) && (pl.rotationPitch < 110))) {
					System.out.println("wee");
					//this prints ... odd.
					double ydir = pl.posY - this.posY;
					pl.motionX = pl.motionX * 5F;
					pl.motionY = ydir * 0.5F;
					pl.motionZ = pl.motionZ * 5F;
					if (pl.motionX < -5) {
						pl.motionX = -4;
					}
					if (pl.motionX > 5) {
						pl.motionX = 4;
					}
					if (pl.motionY < -5) {
						pl.motionY = -4;
					}
					if (pl.motionY > 5) {
						pl.motionY = 4;
					}
					if (pl.motionZ < -5) {
						pl.motionZ = -4;
					}
					if (pl.motionZ > 5) {
						pl.motionZ = 4;
					}
				}
			}
			break;
		case 5:
			specialAttack(var1, 1);
			specialAttack(var1, 2);
			specialAttack(var1, 3);
			specialAttack(var1, 4);
			break;
		default:
			break;
		}
		this.setDead();
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeInt(this.size);
		data.writeInt(this.type);
		data.writeInt(this.step);
		data.writeInt(this.sizeLimit);
	}


	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("creator", this.owner);
		nbt.setInteger("size_", this.size);
		nbt.setInteger("sizeLimit_", this.sizeLimit);
	}
}
