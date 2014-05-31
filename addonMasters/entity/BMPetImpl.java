/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.RpgMastersAddon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BMPetImpl extends EntityTameable implements IPet {

	public int mobType;
	private float experience;
	private int experienceTotal;
	private int prevTicksExisted = 0;
	private final EntityAIControlledByPlayer aiControlledByPlayer;
	int sprintToggleTimer;
	boolean jumping = false;
	public double speedBonus;
	private int xpThrottle = 5;
	int healthregen = 0;
	int levelcheck = 0;
	int prevLevel = 0;
	int jumpTicks;
	protected float moveSpeed;

	protected int previousLevel = 0;

	// CONSTRUCTORS START
	private BMPetImpl(World par1World) {
		super(par1World);
		this.moveSpeed = 0.35F;

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 5.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAITempt(this, 0.5D, RpgMastersAddon.whistle,
				false));
		this.tasks.addTask(9, new EntityAIWatchClosest(this,
				EntityLivingBase.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

		this.tasks.addTask(1,
				this.aiControlledByPlayer = new EntityAIControlledByPlayer(
						this, 0.34F));

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				IMob.class, 0, true));
	}

	public BMPetImpl(World par1World, int mobType, EntityPlayer owner,
			ItemStack is) {
		this(par1World);
		boolean sizeSet = false;
		this.mobType = mobType;
		if (owner != null) {
			try {
				if (is != null) {
					if (is.getTagCompound().hasKey("RPGPetInfo")) {
						readEntityFromNBT(is.getTagCompound().getCompoundTag(
								"RPGPetInfo"));
					}
					if (is.getTagCompound().hasKey("PetLevel")) {
						int level = is.getTagCompound().getInteger("PetLevel");
						setSize(getBaseWidth() + ((level / 200) * 2.0F),
								getBaseHeight() + ((level / 200) * 2.0F));
						sizeSet = true;
					}
				}
			} catch (Throwable ex) {
			}
			this.setOwner(owner.getDisplayName());
			IPet.playersWithActivePets.put(owner.getDisplayName(), new PetID(
					this.dimension, this.getEntityId()));
		}
		if (!sizeSet) {
			setSize(getBaseWidth(), getBaseHeight());
		}
	}

	// CONSTRUCTORS END

	@Override
	public void addExperienceLevel(int numLevels) {
		// If the mob is reduced a level(on death) he drops his currently
		// collected xp. This only drops the exp earned since the last earned
		// level.
		// If this is a level gain, experience value is 0(to make sure xp drops
		// only
		// when the level is reduced.
		this.experienceValue = numLevels < 0 ? xpBarCap()
				- this.experienceTotal : 0;
		int exp = getLevel();
		exp += numLevels;

		if (exp < 0) {
			exp = 0;
			this.experience = 0.0F;
			this.experienceTotal = 0;
		} else {
			heal(200);
		}

		if ((numLevels > 0) && ((exp % 5) == 0)
				&& (this.prevTicksExisted < (this.ticksExisted - 100.0F))) {
			float var2 = exp > 30 ? 1.0F : exp / 30.0F;
			this.worldObj.playSoundAtEntity(this, "random.levelup",
					var2 * 0.75F, 1.0F);
			this.prevTicksExisted = this.ticksExisted;
		}
		if (!worldObj.isRemote) {
			this.setLevel(exp);
		}

	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
		.setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
		.setBaseValue(0.22499999403953552D);
		// this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.22499999403953552D);

	}
	// This is so you can't accidently hurt your pet while riding him.

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if(par1Entity instanceof EntityLiving){
			EntityLiving el = (EntityLiving) par1Entity;
			el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)getOwner()), getAttackDamage());
		}
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this),
				0);
	}

	@Override
	public boolean canAttackClass(Class par1Class) {
		return true;
	}

	@Override
	public boolean canBeSteered() {
		return true;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	// each pet class returns this value, this just forces them to.

	@Override
	protected void entityInit() {
		super.entityInit();
		if (this.getHealth() <= 0) {
			this.heal(1);
		}
		this.dataWatcher.addObject(LEVELID, 0);
		this.dataWatcher.addObject(NAME, getDefaultName());
		// this.dataWatcher.addObject(HP, this.health);
		this.dataWatcher.addObject(TOTALXP, 0);
		this.dataWatcher.addObject(NEXTLEVEL, String.valueOf(0.0F));
		this.dataWatcher.addObject(SADDLE, (byte) 0);
	}

	public abstract int getAttackDamage();

	protected abstract float getBaseHeight();

	protected abstract float getBaseWidth();

	public abstract String getDefaultName();

	// ABSTRACTS END

	// @Override
	public String getEntityName() {
		return this.dataWatcher.getWatchableObjectString(NAME);
	}

	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
		// How much experience this mob drops.
		return this.experienceValue;
	}

	@Override
	public float getHP() {
		return this.getHealth();
	}

	@Override
	public int getLevel() {
		return levelcheck;
	}

	@SideOnly(Side.CLIENT)
	public abstract ModelBase getModel();

	// ABSTRACTS START
	public abstract float getMountedSpeed();

	@Override
	public abstract double getMountedYOffset();

	@Override
	public EntityLivingBase getOwner() {
		EntityPlayer player;
		if (worldObj.isRemote) {
			player = this.worldObj.getPlayerEntityByName(this.getOwnerName());
		} else {
			player = MinecraftServer.getServer().getConfigurationManager()
					.getPlayerForUsername(this.getOwnerName());
		}
		return player;
	}

	@Override
	public abstract float getPetSize();

	@Override
	public boolean getSaddled() {
		return (this.dataWatcher.getWatchableObjectByte(SADDLE)) != 0;
	}

	public abstract ResourceLocation getTexture();

	@Override
	public int getTotalXP() {
		return this.dataWatcher.getWatchableObjectInt(TOTALXP);
	}

	@Override
	public int getType() {
		return mobType;
	}

	@Override
	public void giveXP(int amount) {
		if (!worldObj.isRemote) {
			if (this.getLevel() < 200) {
				int var2 = Integer.MAX_VALUE - this.experienceTotal;

				if (amount > var2) {
					amount = var2;
				}
				this.experience += (float) amount / (float) this.xpBarCap();

				for (this.experienceTotal += amount; this.experience >= 1.0F; this.experience /= this
						.xpBarCap()) {
					this.experience = (this.experience - 1.0F)
							* this.xpBarCap();
					this.addExperienceLevel(1);
				}
				this.dataWatcher.updateObject(NEXTLEVEL,
						String.valueOf(this.experience));
				this.dataWatcher.updateObject(TOTALXP, this.experienceTotal);
			}
			if (this.getLevel() >= 200) {
				this.dataWatcher.updateObject(LEVELID, 200);
			}
		}
	}

	@Override
	public boolean hitByEntity(Entity par1Entity) {
		try {
			if (((EntityPlayer) par1Entity).isRiding()
					&& this.getOwnerName().equals(
							((EntityPlayer) par1Entity).getDisplayName())) {
				return true;
			}
		} catch (Throwable ex) {
		}

		return super.hitByEntity(par1Entity);

	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (!this.isDead()) {
			ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
			if (var2 != null) {
				if (this.getHealth() < this.getMaxHealth()) {
					if (var2.getItem() instanceof ItemFood) {
						ItemFood var3 = (ItemFood) var2.getItem();
						// This is confusing, people dont know pet is saddled.
						// if (!par1EntityPlayer.capabilities.isCreativeMode) {
						if (--var2.stackSize == 0) {
							par1EntityPlayer.inventory
							.setInventorySlotContents(
									par1EntityPlayer.inventory.currentItem,
									(ItemStack) null);
						}
						// }
						if (!worldObj.isRemote) {
							this.heal(var3.func_150905_g(var2));
						}
						return true;
					}
				}
				if (!getSaddled() && (this.getLevel() >= 50)) {
					if (var2.getItem() == Items.saddle) {
						this.setSaddled(true);
						// Confusing
						// if (!par1EntityPlayer.capabilities.isCreativeMode) {
						--var2.stackSize;
						// }
						if (var2.stackSize <= 0) {
							par1EntityPlayer.inventory
							.setInventorySlotContents(
									par1EntityPlayer.inventory.currentItem,
									(ItemStack) null);
						}
					}
				}
				if ((par1EntityPlayer.getCurrentEquippedItem() != null)
						&& (par1EntityPlayer.getCurrentEquippedItem().getItem() == RpgMastersAddon.petCandy)) {
					addExperienceLevel(1);
					par1EntityPlayer.getCurrentEquippedItem().stackSize--;
				}
			} else if (this.getSaddled()
					&& !this.worldObj.isRemote
					&& ((this.riddenByEntity == null) || (this.riddenByEntity == par1EntityPlayer))) {
				par1EntityPlayer.mountEntity(this);
				return true;
			}
		}
		return super.interact(par1EntityPlayer);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean isDead() {
		// Required! must chain up this check or will return false even when
		// dead.
		return super.isDead;
	}

	@Override
	protected boolean isMovementBlocked() {
		return false;
	}

	public void jump(Boolean flag) { // boolean. true = 2.5-high jump. false =
		// normal jump.
		if (onGround && (jumpTicks == 0)) {
			super.jump();
			if (flag)
			{
				motionY += (jumpHeight() / 200) * getLevel(); // makes mount
			}
			// jump higher.
			// Do not use
			// big values!
			jumpTicks = 10;
		}
	}

	protected abstract float jumpHeight();

	@Override
	public void moveEntity(double d, double d1, double d2) {
		if (riddenByEntity != null) {
			EntityPlayer entityRider = (EntityPlayer) riddenByEntity;
			entityRider.prevCameraYaw = rotationYaw = prevRotationYaw = entityRider.rotationYaw;
			/**
			 * initiate sprinting while ridden via keybind. Basically, if the
			 * player has tapped once, it begins the timer which counts down
			 * from seven, slightly less than a quarter second in realtime. If
			 * the player taps forward before the countdown, sprinting is
			 * initiated
			 */
			if (onGround && !isSprinting() && !isInWeb)
			{
				if (sprintToggleTimer == 0)
				{
					sprintToggleTimer = 7; // ... reset...
				}
			}

			Block j = worldObj.getBlock(MathHelper.floor_double(posX),
					MathHelper.floor_double(boundingBox.minY) - 1,
					MathHelper.floor_double(posZ));
			float f = 1;
			if (j == Blocks.ice) {
				f = j.slipperiness * 0.5F;
			}
			// This is currently bugged so temporarily disabled.
			speedBonus = 1F;
			if (isSprinting() && onGround) { // if sprinting on the ground
				motionX += riddenByEntity.motionX * speedBonus * f;
				motionZ += riddenByEntity.motionZ * speedBonus * f;
			} else if (onGround && !onIce()) {
				motionX += riddenByEntity.motionX * 7D * f;
				motionZ += riddenByEntity.motionZ * 7D * f;
			} else { // jumping while walking normally
				motionX += riddenByEntity.motionX;
				motionZ += riddenByEntity.motionZ;
			}
			// super.moveEntity(d, d1, d2);
			setSprinting(entityRider.isSprinting());
			setSneaking(entityRider.isSneaking());
			if ((isSprinting() && inWater) || (isSprinting() && isInWeb)) {
				setSprinting(false);
			}
			// Autojump
			if (isCollidedHorizontally) {
				jump(false);
			}
			// Initiate jumping while ridden via keybind
			if (((EntityPlayer) riddenByEntity).isAirBorne)
			{
				// preset 'jump'
				// input.
				jump(true); // this method is seen overridden in here
			}
			super.moveEntity(motionX, motionY, motionZ);
		} else {
			super.moveEntity(d, d1, d2);
		}
	}

	@Override
	public void moveEntityWithHeading(float par1, float par2) {

		if ((this.riddenByEntity != null)
				&& (this.dataWatcher.getWatchableObjectByte(SADDLE) == 1)) {
			this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
			this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			par1 = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F;
			par2 = ((EntityLivingBase) this.riddenByEntity).moveForward;

			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (!this.worldObj.isRemote) {
				this.setAIMoveSpeed((float) this.getEntityAttribute(
						SharedMonsterAttributes.movementSpeed)
						.getAttributeValue());
				super.moveEntityWithHeading(par1, par2);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d0 = this.posX - this.prevPosX;
			double d1 = this.posZ - this.prevPosZ;
			float f4 = MathHelper.sqrt_double((d0 * d0) + (d1 * d1)) * 4.0F;

			if (f4 > 1.0F) {
				f4 = 1.0F;
			}

			this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		} else {
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(par1, par2);
		}
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {

		super.onDeath(par1DamageSource);
	}

	protected boolean onIce() { // just a check.
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(posY);
		int k = MathHelper.floor_double(posZ);

		if (worldObj.getBlock(i, j - 1, k) == Blocks.ice) {
			return true;
		}
		return false;
	}

	/*
	 * protected void updateAITick() { //DON'T UPDATE WATCHABLES HERE, IT WILL
	 * PACKET FLOOD //this.dataWatcher.updateObject(HP,
	 * Integer.valueOf(this.getHealth())); }
	 */
	@Override
	public void onKillEntity(EntityLivingBase par1EntityLiving) {
		super.onKillEntity(par1EntityLiving);
	}

	@Override
	public void onLivingUpdate() {

		EntityPlayer player = (EntityPlayer) getOwner();
		if(!RpgInventoryMod.playerClass.contains(RpgMastersAddon.CLASSBEASTMASTER)) {
			if (!worldObj.isRemote) {
				if (((player == null))) {
					try {
						PlayerRpgInventory inv = PlayerRpgInventory.get(player);
						inv.setInventorySlotContents(6, writePetToItemStack());
					} catch (Throwable ex) {
					}
					this.setDead();
					return;
				}
			}
		}

		if (!worldObj.isRemote) {
			if ((!IPet.playersWithActivePets.containsKey(this.getOwnerName()) || (this.dimension != getOwner().dimension))) {
				this.setDead();
				return;
			}
		}

		if (healthregen-- <= 0) {
			this.heal(1);
			this.healthregen = regenDelay();
			// if (!worldObj.isRemote) {
			// this.dataWatcher.updateObject(HP, this.getHealth());
			// } else {
			// this.health = this.dataWatcher.getWatchableObjectInt(HP);
			// }
		}

		prevLevel = this.dataWatcher.getWatchableObjectInt(LEVELID);

		if (prevLevel != levelcheck) {
			if (!worldObj.isRemote) {
				prevLevel = levelcheck;
				this.dataWatcher.updateObject(LEVELID, levelcheck);
			}
			levelcheck = prevLevel;
			setSize(getBaseWidth()
					+ ((levelcheck / 200.0F) * (1.0F + getBaseWidth())),
					getBaseHeight()
					+ ((levelcheck / 200.0F) * (1.0F + getBaseHeight())));
		}

		if (sprintToggleTimer > 0) {
			// activated.
			sprintToggleTimer--;
		}
		if (jumpTicks > 0) {
			jumpTicks--;
		}

		List<EntityPetXP> xps = worldObj.getEntitiesWithinAABB(EntityPetXP.class, boundingBox.copy().expand(0.5D, 0.5D, 0.5D));
		if ((xps != null) && (xps.size() > 0)) {
			if (--xpThrottle <= 0) {
				for (EntityPetXP xp : xps) {
					xpThrottle = 5;
					this.giveXP(xp.getXpValue());
					xp.setDead();
				}
			}
		}


		List<EntityXPOrb> a = this.worldObj.getEntitiesWithinAABB(EntityXPOrb.class,  boundingBox.copy().expand(0.5D, 0.5D, 0.5D));
		int totalXP = 0;

		for(EntityXPOrb orb : a){
			totalXP += orb.getXpValue();
			orb.setDead();
		}
		while ((totalXP > 0) && !worldObj.isRemote) {
			int partialXP = EntityXPOrb.getXPSplit(totalXP);
			totalXP -= partialXP;
			giveXP(partialXP);
			//			this.worldObj.spawnEntityInWorld(new EntityPetXP(
			//					this.worldObj, this.posX, this.posY,
			//					this.posZ, partialXP));
		}
		super.onLivingUpdate();

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (riddenByEntity != null) {
			// stops up-and-down head movement
			rotationPitch = 0;
			// Control where the pet is facing (doesn't work while standing
			// still)
			rotationYaw = prevRotationYaw = riddenByEntity.rotationYaw;
		}
	}

	@Override
	public final void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		this.dataWatcher.updateObject(NAME,
				par1NBTTagCompound.getString("Name"));

		// Convert float to string
		this.experience = par1NBTTagCompound.getFloat("PercentToNextLevel");
		this.dataWatcher.updateObject(NEXTLEVEL,
				String.valueOf(this.experience));

		this.dataWatcher.updateObject(LEVELID,
				par1NBTTagCompound.getInteger("XpLevel"));

		this.experienceTotal = par1NBTTagCompound.getInteger("XpTotal");
		this.dataWatcher.updateObject(TOTALXP, this.experienceTotal);

		this.prevTicksExisted = par1NBTTagCompound
				.getInteger("prevTicksExisted");
		this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
	}


	public abstract int regenDelay();
	@Override
	public void setDead() {

		if (IPet.playersWithActivePets.containsKey(this.getOwnerName())) {
			PlayerRpgInventory inv = PlayerRpgInventory
					.get((EntityPlayer) getOwner());

			ItemStack itemizedPet = writePetToItemStack(new ItemStack(
					RpgMastersAddon.crystal));
			inv.setInventorySlotContents(6, itemizedPet);
			IPet.playersWithActivePets.remove(this.getOwnerName());
		}
		super.setDead();
	}

	@Override
	public void setLevel(int Level) {
		levelcheck = Level;
		this.dataWatcher.updateObject(LEVELID, Level);
	}

	@Override
	public void setName(String name) {
		if (!worldObj.isRemote) {
			this.dataWatcher.updateObject(NAME, name);
		}
	}

	/**
	 * Set or remove the saddle of the pet.
	 */
	@Override
	public void setSaddled(boolean par1) {
		if (!worldObj.isRemote) {
			this.dataWatcher.updateObject(SADDLE,
					Byte.valueOf(par1 ? (byte) 1 : (byte) 0));
		}
	}

	@Override
	public boolean shouldRiderSit() {
		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {

		super.writeEntityToNBT(par1NBTTagCompound);
		// prevents natural despawning
		par1NBTTagCompound.setBoolean("PersistanceRequired", true);
		// Our Cutom Data below
		par1NBTTagCompound.setString("Name",
				this.dataWatcher.getWatchableObjectString(NAME));
		// Convert string to float
		try {
			par1NBTTagCompound.setFloat("PercentToNextLevel", Float
					.valueOf(this.dataWatcher
							.getWatchableObjectString(NEXTLEVEL)));
		} catch (Throwable ex) {
		}
		par1NBTTagCompound.setInteger("XpLevel",
				this.dataWatcher.getWatchableObjectInt(LEVELID));
		par1NBTTagCompound.setInteger("XpTotal",
				this.dataWatcher.getWatchableObjectInt(TOTALXP));
		par1NBTTagCompound
		.setInteger("prevTicksExisted", this.prevTicksExisted);
		par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
	}

	@Override
	public ItemStack writePetToItemStack() {
		NBTTagCompound petnbt = new NBTTagCompound();
		NBTTagCompound itemstacknbt = new NBTTagCompound();
		writeEntityToNBT(petnbt);
		itemstacknbt.setTag("RPGPetInfo", petnbt);
		itemstacknbt.setInteger("PetLevel",
				this.dataWatcher.getWatchableObjectInt(LEVELID));
		itemstacknbt.setString("PetName", getEntityName());
		itemstacknbt.setString("OwnerName", getOwnerName());
		itemstacknbt.setInteger("PetAttack", getAttackDamage());
		itemstacknbt.setFloat("PetMaxHealth", getMaxHealth());
		itemstacknbt.setFloat("PetHealth", getHealth());
		itemstacknbt.setBoolean("isSaddled", getSaddled());
		ItemStack newIteamstack = new ItemStack(RpgMastersAddon.crystal, 1, getType());
		newIteamstack.setTagCompound(itemstacknbt);
		newIteamstack.setStackDisplayName(getEntityName());
		return newIteamstack;
	}

	@Override
	public ItemStack writePetToItemStack(ItemStack is) {
		return writePetToItemStack();
	}

	public int xpBarCap() {
		int exp = getLevel();
		return exp >= 30 ? 62 + ((exp - 30) * 7)
				: (exp >= 15 ? 17 + ((exp - 15) * 3) : 17);
	}

}
