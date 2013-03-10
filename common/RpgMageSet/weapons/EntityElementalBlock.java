package RpgMageSet.weapons;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityElementalBlock extends EntityThrowable implements IEntityAdditionalSpawnData {

	private EntityLiving shootingEntity;
	public String owner;

	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inTile = 0;
	private boolean inGround = false;
	private int ticksAlive;
	private int ticksInAir = 0;
	public int size = 0;
	public int type = 1;
	public int contacts = 0;

	public EntityElementalBlock(World world)
	{
		super(world);
	}

	public EntityElementalBlock(World w, EntityLiving e, float p, int type)
	{
		super(w,e);
		this.type = type;
		this.noClip = true;
		this.owner = e.getEntityName();
		this.shootingEntity = e;

	}

	public EntityElementalBlock(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.size++;
		if (this.size >= 10) {
			MovingObjectPosition pos = new MovingObjectPosition((int)this.posX, (int)this.posY, (int)this.posZ, 4, Vec3.createVectorHelper(this.posX, this.posY, this.posZ));
			this.onImpact(pos);
			//this.setDead();
		}
		//System.out.println);
		this.setSize(this.size, this.size);
	}

	public int getRadius() {
		return this.size/2;
	}

	public void specialAttack(MovingObjectPosition var1, int type) {
		EntityLiving p = this.worldObj.getPlayerEntityByName(this.owner);
		switch (type) {
		case 1:
			AxisAlignedBB pool = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(var1.hitVec.xCoord - getRadius(), var1.hitVec.yCoord - getRadius(), var1.hitVec.zCoord - getRadius(), var1.hitVec.xCoord +  getRadius(), var1.hitVec.yCoord + getRadius(), var1.hitVec.zCoord + this.size);
			List<EntityLiving> entl = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
			if (entl != null && entl.size() > 0) {
				for (EntityLiving el : entl) {
					if (el != null && el != p) {
						el.setFire(30);
						if (this.getThrower() instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this.getThrower()), 1);
							el.attackEntityFrom(DamageSource.onFire, 2);
						} else {
							el.attackEntityFrom(DamageSource.onFire, 2);
						}

					}
				}
			}
			if (var1.typeOfHit == EnumMovingObjectType.TILE) {
			} else if (var1.typeOfHit == EnumMovingObjectType.ENTITY) {
				if (var1.entityHit == p) break;
				var1.entityHit.setFire(30);
				if (p instanceof EntityPlayer) {
					var1.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)p), 1);
					var1.entityHit.attackEntityFrom(DamageSource.onFire, 2);
				} else {
					var1.entityHit.attackEntityFrom(DamageSource.onFire, 2);
				}
			}
			break;
		case 2:
			AxisAlignedBB pool1 = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(var1.hitVec.xCoord - getRadius(), var1.hitVec.yCoord - getRadius(), var1.hitVec.zCoord - getRadius(), var1.hitVec.xCoord +  getRadius(), var1.hitVec.yCoord + getRadius(), var1.hitVec.zCoord + this.size);
			List<EntityLiving> entl1 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool1);
			if (entl1 != null && entl1.size() > 0) {
				for (EntityLiving el : entl1) {
					if (el != null && el != p) {
						if (el.isEntityUndead()) {
							el.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 2));	
							el.attackEntityFrom(DamageSource.magic, 4);
						} else {
							el.addPotionEffect(new PotionEffect(Potion.poison.id, 120, 2));	
							el.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 2));	
						}
						el.addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 5));
						if (p instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)p), 1);
							el.attackEntityFrom(DamageSource.drown, 2);
						} else {
							el.attackEntityFrom(DamageSource.drown, 2);
						}

					}
				}
			}
			if (var1.typeOfHit == EnumMovingObjectType.TILE) {
			} else if (var1.typeOfHit == EnumMovingObjectType.ENTITY) {
				if (var1.entityHit == p) break;
				if (var1.entityHit.isEntityAlive()) {
					EntityLiving el = (EntityLiving) var1.entityHit;
					if (el.isEntityUndead()) {
						el.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 2));	
						el.attackEntityFrom(DamageSource.magic, 4);
					} else {
						el.addPotionEffect(new PotionEffect(Potion.poison.id, 120, 2));	
						el.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 2));	
					}
					el.addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 5));
					if (p instanceof EntityPlayer) {
						el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)p), 1);
						el.attackEntityFrom(DamageSource.drown, 2);
					} else {
						el.attackEntityFrom(DamageSource.drown, 2);
					}
				}
			}
			break;
		case 3:
			pool = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(var1.hitVec.xCoord - getRadius(), var1.hitVec.yCoord - getRadius(), var1.hitVec.zCoord - getRadius(), var1.hitVec.xCoord +  getRadius(), var1.hitVec.yCoord + getRadius(), var1.hitVec.zCoord + this.size);
			entl = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
			if (entl != null && entl.size() > 0) {
				for (EntityLiving el : entl) {
					if (el != null && el != p) {
						if (p instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) p), 5);
						} else {
							el.attackEntityFrom(DamageSource.anvil, 5);
						}
						el.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 2));	
					}
				}
			}
			if (var1.typeOfHit == EnumMovingObjectType.ENTITY) {
				if (var1.entityHit.isEntityAlive()) {
					EntityLiving el = (EntityLiving) var1.entityHit;
					if (el != null && el != p) {
						if (p instanceof EntityPlayer) {
							el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) p), 5);
						} else {
							el.attackEntityFrom(DamageSource.anvil, 5);
						}
						el.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 2));
					}
				}
			}
			break;
		case 4:
			pool = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(var1.hitVec.xCoord - getRadius(), var1.hitVec.yCoord - getRadius(), var1.hitVec.zCoord - getRadius(), var1.hitVec.xCoord +  getRadius(), var1.hitVec.yCoord + getRadius(), var1.hitVec.zCoord + this.size);
			entl = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
			if (entl != null && entl.size() > 0) {
				for (EntityLiving el : entl) {
					if (el != null && el != p) {
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
			if (var1.typeOfHit == EnumMovingObjectType.ENTITY) {
				if (var1.entityHit.isEntityAlive()) {
					EntityLiving el = (EntityLiving) var1.entityHit;
					if (el != null && el != p) {
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

			if (p != null) {
				if (p.getDistanceToEntity(this) < 3) {
					double xdir = p.posX - this.posX;
					double ydir = p.posY - this.posY;
					double zdir = p.posZ - this.posZ;
					p.motionX = xdir * 0.5F;
					p.motionY = ydir * 0.5F;
					p.motionZ = zdir * 0.5F;
					System.out.println(p.motionX + " " +  p.motionY + " " +  p.motionZ);
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
	protected void onImpact(MovingObjectPosition var1) {
		specialAttack(var1,this.type);

		/*
		 * Be sure this will make mess...
		double xx = this.posX; double yy = this.posY; double zz = this.posZ;
		for (int x = (int)-getRadius(); x < getRadius(); x++)
		{
			for (int y = (int)-getRadius(); y < getRadius(); y++)
			{
				for (int z = (int)-getRadius(); z < getRadius(); z++)
				{
					Vec3 position = Vec3.createVectorHelper(xx, yy, zz);
					Vec3 targetPosition = position.addVector(x, y, z);
					double dist = position.distanceTo(targetPosition);

					if (dist < getRadius())
					{
						if ((dist < getRadius() + 5.0F))
						{	
							if (this.worldObj.getBlockId((int)targetPosition.xCoord,(int) targetPosition.yCoord,(int) targetPosition.zCoord) == 0){
								this.worldObj.setBlockWithNotify((int)targetPosition.xCoord,(int) targetPosition.yCoord,(int) targetPosition.zCoord, Block.stone.blockID);
							}
						}
					}
				}
			}
		}
		 */
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeInt(this.size);
		data.writeInt(this.type);
		data.writeUTF(this.owner);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		this.size = data.readInt();
		this.type = data.readInt();	
		this.owner = data.readUTF();
	}
}
