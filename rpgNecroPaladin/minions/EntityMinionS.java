package rpgNecroPaladin.minions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMinionS extends EntityTameable implements IRangedAttackMob, IMob, IMinion {

	private EntityPlayer player;
	public boolean invulnerable = false;
	public EntityMinionS(World var1, EntityPlayer player) {
		this(var1);
		this.player = player;
		this.setSize(1.0f, 1.0f);
		//All attack requests are filtered through this task to ensure it really should attack.
		//The minion will attack all aggressives that get close enough, but will
		//never attack the owner, or other minions of his owner. He will attack other
		//minions of other owners.
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, new CustomMinionEntitySelector(player)));
		if (!MinionRegistry.playerMinions.containsKey(player.username)) {
			MinionRegistry.playerMinions.put(player.username, new ArrayList<IMinion>());
		}
		List<IMinion> list = MinionRegistry.playerMinions.get(player.username);
		if (!list.contains(this)) {
			list.add(this);
		}
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	@Override
	public boolean isEntityInvulnerable() {
		return invulnerable;
	}





	public EntityMinionS(World var1) {
		super(var1);
//		this.texture = "/mob/skeleton.png";
//		this.moveSpeed = 0.32F;
		this.Following();
		this.setPathToEntity((PathEntity) null);
		this.setAttackTarget((EntityLiving) null);
		//Teach the minion how to swim, he will drown without this.
		this.tasks.addTask(1, new EntityAISwimming(this));
		//Follow around the owner
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 0.32f, 15.0F, 2.0F));
		//allows the minion to walk around a bit and attack aggressives that get too close.
		this.tasks.addTask(3, new EntityAIWander(this, 0.32f));
		//This would cause the skeletons to move in too close, they are ranged, they need to keep their distance
		//this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(5, new EntityAIArrowAttack(this, 0.32f, 10, 5.0F));
		//This lets the minions look around at any living entity mobs, to see if they need to attack it or not. Also makes minions appear curious ;)
		this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 8.0F));
		//let the ai shutdown for 1-2 seconds(random) if no tasks are currently active
		//adds realism and is a performance tweak.
		this.tasks.addTask(7, new EntityAILookIdle(this));

		//Something hurt the owner, this tells the minion to attack it.
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));

		//The owner attacked something, this tells the minions to help.
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));

		//Something hurt the minion, this tells the minion to attack it.
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

		//give minion a bow
		//slot 0 is what is in their hand, 1-4 are armor slots.
		this.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
		this.setSize(0.7F, 1.7F);
	}

	public void Harvest() {
		this.damageEntity(DamageSource.magic, this.func_110143_aJ());
		if (player.func_110143_aJ() + 2 <= player.func_110138_aP()) {
			player.heal(2);
		} else {
			player.setEntityHealth(player.func_110138_aP());
		}
	}

	public EntityPlayer getMaster() {
		return player;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public void setAge(int par1) {
		this.entityAge = par1;
		this.dataWatcher.updateObject(20, Integer.valueOf(par1));
	}

	public int getAge() {
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, new Integer(0));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.player != null) {
			int age = getAge();
			if (age >= 0 && age < 700 + (player.experienceLevel * 20)) {
				++age;
				this.setAge(age);
			} else if (age >= 700 + (player.experienceLevel * 20)) {
				this.attackEntityFrom(DamageSource.outOfWorld, this.func_110143_aJ());
				age = 0;
			}
		} else {
			//player logged out, kill his minions.
			this.attackEntityFrom(DamageSource.outOfWorld, this.func_110143_aJ());
			this.setAge(0);
		}

	}

	private void Following() {
		float var1 = 18.0F;

		if (this.player != null) {
			var1 = this.getDistanceToEntity(this.player);

			if (var1 > 5.0F && var1 < 18.0F) {
				PathEntity var2 = this.worldObj.getPathEntityToEntity(this, this.player, 16.0F, true, false, false, true);
				this.setPathToEntity(var2);
			} else {
				this.setPathToEntity((PathEntity) null);
			}
		}
	}

//	public int getMaxHealth() {
//
//		return 12;
//	}

	public boolean isAIEnabled() {
		return true;
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setInteger("Exist", this.getGrowingAge());

	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.setGrowingAge(var1.getInteger("Exist"));

	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal var1) {
		return null;
	}

	protected boolean canDespawn() {
		return false;
	}

	protected int getDropItemId() {
		return -1;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {
		if (player != null) {
			if (MinionRegistry.playerMinions.containsKey(player.username)) {
				List<IMinion> list = MinionRegistry.playerMinions.get(player.username);
				if (list.contains(this)) {
					list.remove(this);
				}
			}
		}
		super.onDeath(par1DamageSource);
	}

//	@Override
//	public void func_82196_d(EntityLivingBase par1EntityLiving) {
//		EntityArrow var2 = new EntityArrow(this.worldObj, this, par1EntityLiving, 1.6F, 12.0F);
//
//		this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
//		this.worldObj.spawnEntityInWorld(var2);
//	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase,
			float f) {
		EntityArrow var2 = new EntityArrow(this.worldObj, this, entitylivingbase, 1.6F, 12.0F);

		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(var2);		
	}
}
