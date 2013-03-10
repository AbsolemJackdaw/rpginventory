/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.beastmaster;

import RpgInventory.EntityPetXP;
import RpgInventory.IPet;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.mod_RpgInventory;
import RpgPlusPlus.minions.CustomMinionEntitySelector;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public abstract class BMPetImpl extends EntityTameable implements IPet {

    public int mobType;
    private float experience;
    private int experienceTotal;
    private int prevTicksExisted = 0;
    private final EntityAIControlledByPlayer aiControlledByPlayer;
    int sprintToggleTimer;
    int jumpTicks;
    public double speedBonus;

    private BMPetImpl(World par1World) {
        super(par1World);
        this.moveSpeed = 0.35F;
        this.Following();
        this.getNavigator().setAvoidsWater(true);
        this.setPathToEntity((PathEntity) null);
        this.setAttackTarget((EntityLiving) null);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.7F));
        this.tasks.addTask(3, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));
        this.tasks.addTask(4, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(6, new EntityAIAttackOnCollide(this, EntityLiving.class, this.moveSpeed, true));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, Entity.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(9, this.aiSit);
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        ignoreFrustumCheck = true;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackDamage());
    }

    public BMPetImpl(World par1World, int mobType, EntityPlayer owner, ItemStack is) {
        this(par1World);
        this.mobType = mobType;
        if (owner != null) {
            try {
                if (is != null) {
                    if (is.getTagCompound().hasKey("RPGPetInfo")) {
                        readEntityFromNBT(is.getTagCompound().getCompoundTag("RPGPetInfo"));
                    }
                }
            } catch (Throwable ex) {
            }
            this.setOwner(owner.username);
            IPet.playersWithActivePets.put(owner.username, this);
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, true, false, new CustomMinionEntitySelector(owner)));
            this.Following();
        }
    }

    public abstract int getAttackDamage();

    public abstract AxisAlignedBB getCollisionBox(Entity par1Entity);

    public abstract double getMountedYOffset();

    public boolean canBeSteered() {
        return true;
    }

    public boolean isDead() {
        //Required! must chain up this check or will return false even when dead.
        return super.isDead;
    }

    @Override
    public void onLivingUpdate() {
        //Check if player has crystal equipped.
        //RpgInventory rpginv = mod_RpgInventory.proxy.getInventory(this.getOwnerName());
        this.width = getSize() * 2;
        IPet.playersWithActivePets.put(this.getOwnerName(), this);
        if (sprintToggleTimer > 0) { //used to determine if sprinting should be activated.
            sprintToggleTimer--;
        }
        if (jumpTicks > 0) //used to limit how long the mount will rise while jumping
        {
            jumpTicks--;
        }

        if (riddenByEntity != null) {
            //stops up-and-down head movement
            rotationPitch = 0;

            //Control where the pet is facing (doesn't work while standing still)
            EntityPlayer entityRider = (EntityPlayer) riddenByEntity;
            rotationYaw = prevRotationYaw = entityRider.rotationYaw;
        }
        super.onLivingUpdate();
    }

    public void moveEntity(double d, double d1, double d2) {

        if (riddenByEntity != null) {
            /**
             * initiate sprinting while ridden via keybind. Basically, if the
             * player has tapped once, it begins the timer which counts down
             * from seven, slightly less than a quarter second in realtime. If
             * the player taps forward before the countdown, sprinting is
             * initiated
             */
            if (onGround && !isSprinting() && !isInWeb) //if forward is tapped...
            {
                if (sprintToggleTimer == 0) //... if it is tapped after the countdown reach zero...
                {
                    sprintToggleTimer = 7; //... reset...
                }
            }

            int j = worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
            float f = 1;
            if (j == Block.ice.blockID) {
                f = Block.blocksList[j].slipperiness * 0.3F;
            }
            //Initiate jumping while ridden via keybind
            if (((EntityPlayer) riddenByEntity).isJumping) { //hijacking the preset 'jump' input.
                jump(true); //this method is seen overridden in here
            }
            if (isSprinting() && onGround) { //if sprinting on the ground
                motionX += riddenByEntity.motionX * speedBonus * f;
                motionZ += riddenByEntity.motionZ * speedBonus * f;
            } else if (onGround && !onIce()) {
                motionX += riddenByEntity.motionX * 7D * f;
                motionZ += riddenByEntity.motionZ * 7D * f;
            } else { //jumping while walking normally
                motionX += riddenByEntity.motionX;
                motionZ += riddenByEntity.motionZ;
            }

            if (isSprinting() && inWater || isSprinting() && isInWeb) {
                setSprinting(false);
            }
            //Autojump
            if (isCollidedHorizontally) {

                jump(false);
            }
            super.moveEntity(motionX, motionY, motionZ);
        } else {
            super.moveEntity(d, d1, d2);
        }
    }

    public void updateRidden() {
        if (ridingEntity == null) {
            return;
        }
        if (ridingEntity.isDead) {
            ridingEntity = null;
            return;
        }
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        onUpdate();
    }

    public void jump(Boolean flag) { //boolean. true = 2.5-high jump. false = normal jump.
        if (onGround && jumpTicks == 0) {
            super.jump();
            if (flag) {
                motionY += 0.2; //makes mount jump higher. Do not use big values!
            }
            jumpTicks = 10;
        }
    }

    protected boolean onIce() { //just a check.
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(posY);
        int k = MathHelper.floor_double(posZ);

        if (worldObj.getBlockId(i, j, k) == Block.ice.blockID) {
            return true;
        }
        return false;
    }

    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null) {
            if (this.dataWatcher.getWatchableObjectInt(HP) < this.getMaxHealth()) {
                if (Item.itemsList[var2.itemID] instanceof ItemFood) {
                    ItemFood var3 = (ItemFood) Item.itemsList[var2.itemID];

                    if (!par1EntityPlayer.capabilities.isCreativeMode) {
                        --var2.stackSize;
                    }

                    this.heal(var3.getHealAmount());

                    if (var2.stackSize <= 0) {
                        par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack) null);
                    }

                    return true;
                }
            }
            if (!getSaddled()) {
                if (var2.itemID == Item.saddle.itemID) {
                    this.setSaddled(true);
                    if (!par1EntityPlayer.capabilities.isCreativeMode) {
                        --var2.stackSize;
                    }
                    if (var2.stackSize <= 0) {
                        par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack) null);
                    }
                }
            }
        } else if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer)) {
            par1EntityPlayer.mountEntity(this);
            return true;
        }
        return super.interact(par1EntityPlayer);
    }

    @Override
    public EntityLiving getOwner() {
        return MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(this.getOwnerName());
    }

    public boolean isAIEnabled() {
        return true;
    }
    private int xpThrottle = 10;

    protected void updateAITick() {
        if (riddenByEntity != null) {
            //stops up-and-down head movement
            rotationPitch = 0;

            //Control where the pet is facing (doesn't work while standing still)
            EntityPlayer entityRider = (EntityPlayer) riddenByEntity;
            rotationYaw = prevRotationYaw = entityRider.rotationYaw;
        }
        
        this.dataWatcher.updateObject(HP, Integer.valueOf(this.getHealth()));
        List<EntityPetXP> xps = worldObj.getEntitiesWithinAABB(EntityPetXP.class, boundingBox.expand(0.4D, 0.4D, 0.4D));
        if (xps != null && xps.size() > 0) {
            if (xpThrottle <= 0) {
                for (EntityPetXP xp : xps) {
                    this.giveXP(xp.getXpValue());
                    xp.setDead();
                }
            }
        }

    }

    @Override
    public void onKillEntity(EntityLiving par1EntityLiving) {
        super.onKillEntity(par1EntityLiving);
    }

    @Override
    public boolean canAttackClass(Class par1Class) {
        return true;
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            RpgInv rpginv = mod_RpgInventory.proxy.getInventory(getOwnerName());
            ItemStack itemizedPet = writePetToItemStack(new ItemStack(mod_RpgInventory.crystal));
            rpginv.setInventorySlotContents(6, itemizedPet);
            IPet.playersWithActivePets.remove(this.getOwnerName());
        }
        setDead();
    }

    @Override
    public abstract String getTexture();

    private void Following() {
        float var1 = 18.0F;

        if (getOwner() != null) {
            var1 = this.getDistanceToEntity(getOwner());

            if (var1 > 5.0F && var1 < 18.0F) {
                PathEntity var2 = this.worldObj.getPathEntityToEntity(this, getOwner(), 16.0F, true, false, false, true);
                this.setPathToEntity(var2);
            } else {
                this.setPathToEntity((PathEntity) null);
            }
        }
    }

    public abstract String getDefaultName();

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(LEVELID, (int) 0);
        this.dataWatcher.addObject(NAME, getDefaultName());
        this.dataWatcher.addObject(HP, new Integer(getHealth()));
        this.dataWatcher.addObject(SADDLE, Byte.valueOf((byte) 0));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }

    @Override
    public abstract int getMaxHealth();

    @Override
    public int getLevel() {
        return this.dataWatcher.getWatchableObjectInt(LEVELID);
    }

    public int xpBarCap() {
        int exp = getLevel();
        return exp >= 30 ? 62 + (exp - 30) * 7 : (exp >= 15 ? 17 + (exp - 15) * 3 : 17);
    }

    @Override
    public void giveXP(int amount) {
        if (this.getLevel() < 200) {
            int var2 = Integer.MAX_VALUE - this.experienceTotal;

            if (amount > var2) {
                amount = var2;
            }

            this.experience += (float) amount / (float) this.xpBarCap();

            for (this.experienceTotal += amount; this.experience >= 1.0F; this.experience /= (float) this.xpBarCap()) {
                this.experience = (this.experience - 1.0F) * (float) this.xpBarCap();
                this.addExperienceLevel(1);
            }
        }
    }

    public void addExperienceLevel(int numLevels) {
        int exp = getLevel();
        exp += numLevels;

        if (exp < 0) {
            exp = 0;
            this.experience = 0.0F;
            this.experienceTotal = 0;
        }

        if (numLevels > 0 && exp % 5 == 0 && (float) this.prevTicksExisted < (float) this.ticksExisted - 100.0F) {
            float var2 = exp > 30 ? 1.0F : (float) exp / 30.0F;
            this.worldObj.playSoundAtEntity(this, "random.levelup", var2 * 0.75F, 1.0F);
            this.prevTicksExisted = this.ticksExisted;
        }
        this.dataWatcher.updateObject(LEVELID, exp);
    }

    @Override
    public int getTotalXP() {
        return experienceTotal;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
        return 0;
    }

    @Override
    public ItemStack writePetToItemStack(ItemStack is) {
        return writePetToItemStack();
    }

    @Override
    public ItemStack writePetToItemStack() {
        NBTTagCompound petnbt = new NBTTagCompound();
        NBTTagCompound itemstacknbt = new NBTTagCompound("tag");
        writeEntityToNBT(petnbt);
        itemstacknbt.setCompoundTag("RPGPetInfo", petnbt);
        itemstacknbt.setInteger("PetLevel", getLevel());
        itemstacknbt.setString("PetName", getEntityName());
        itemstacknbt.setString("OwnerName", getOwnerName());
        itemstacknbt.setInteger("PetAttack", getAttackDamage());
        itemstacknbt.setInteger("PetHealth", getMaxHealth());
        itemstacknbt.setInteger("PetPrevHealth", this.dataWatcher.getWatchableObjectInt(HP));
        itemstacknbt.setBoolean("isSaddled", getSaddled());
        ItemStack newIteamstack = new ItemStack(mod_RpgInventory.crystal, 1, getType());
        newIteamstack.setTagCompound(itemstacknbt);
        return newIteamstack;
    }

    @Override
    public void setName(String name) {
        this.dataWatcher.updateObject(NAME, name);
    }

    @Override
    public String getEntityName() {
        return this.dataWatcher.getWatchableObjectString(NAME);
    }

    @Override
    public abstract float getSize();

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setString("Name", this.dataWatcher.getWatchableObjectString(NAME));
        par1NBTTagCompound.setFloat("Xp", this.experience);
        par1NBTTagCompound.setInteger("XpLevel", this.dataWatcher.getWatchableObjectInt(LEVELID));
        par1NBTTagCompound.setInteger("XpTotal", this.experienceTotal);
        par1NBTTagCompound.setInteger("prevTicksExisted", this.prevTicksExisted);
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());

    }

    public final void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(NAME, par1NBTTagCompound.getString("Name"));
        this.experience = par1NBTTagCompound.getFloat("Xp");
        this.dataWatcher.updateObject(LEVELID, par1NBTTagCompound.getInteger("XpLevel"));
        this.experienceTotal = par1NBTTagCompound.getInteger("XpTotal");
        this.prevTicksExisted = par1NBTTagCompound.getInteger("prevTicksExisted");
        this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));

    }

    public boolean getSaddled() {

        return (this.dataWatcher.getWatchableObjectByte(SADDLE)) != 0;
    }

    /**
     * Set or remove the saddle of the pet.
     */
    public void setSaddled(boolean par1) {
        this.dataWatcher.updateObject(SADDLE, Byte.valueOf(par1 ? (byte) 1 : 0));
    }

    @Override
    public int getHP() {
        return this.dataWatcher.getWatchableObjectInt(HP);
    }

    public EntityAIControlledByPlayer getAIControlledByPlayer() {
        return this.aiControlledByPlayer;
    }

    @Override
    public int getType() {
        return mobType;
    }
}
