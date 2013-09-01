/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgRogueBeast.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rpgInventory.EnumRpgClass;
import rpgInventory.IPet;
import rpgInventory.mod_RpgInventory;
import rpgInventory.entity.EntityPetXP;
import rpgInventory.gui.rpginv.RpgInv;
import rpgNecroPaladin.minions.CustomMinionEntitySelector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    boolean jumping = false;
    public double speedBonus;
    private boolean checked = false;
    private int xpThrottle = 5;
    int healthregen = 0;
    int levelcheck = 0;
    int prevLevel = 0;
    int jumpTicks;
    protected float moveSpeed;

    //CONSTRUCTORS START
    private BMPetImpl(World par1World) {
        super(par1World);
        this.moveSpeed = 0.35F;
        this.getNavigator().setAvoidsWater(true);
        this.setPathToEntity((PathEntity) null);
        this.setAttackTarget((EntityLiving) null);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.34F));
        this.tasks.addTask(2, new EntityAIFollowOwner(this, this.moveSpeed, 15.0F, 2.0F));
        this.tasks.addTask(3, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        //This allows the pets to stand and look at the player if they have food.
        for (Item item : Item.itemsList) {
            if (item instanceof ItemFood) {
                //This pet, speed, item, get startled by movement.
                this.tasks.addTask(8, new EntityAITempt(this, this.moveSpeed, item.itemID, false));
            }
        }
        this.targetTasks.addTask(1, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        //this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, IMob.class, 16.0F, 0, true));
    }

    public BMPetImpl(World par1World, int mobType, EntityPlayer owner, ItemStack is) {
        this(par1World);
        boolean sizeSet = false;
        this.mobType = mobType;
        if (owner != null) {
            try {
                if (is != null) {
                    if (is.getTagCompound().hasKey("RPGPetInfo")) {
                        readEntityFromNBT(is.getTagCompound().getCompoundTag("RPGPetInfo"));
                    }
                    if (is.getTagCompound().hasKey("PetLevel")) {
                        int level = is.getTagCompound().getInteger("PetLevel");
                        setSize(getBaseWidth() + ((level / 200) * 2.0F), getBaseHeight() + ((level / 200) * 2.0F));
                        sizeSet = true;
                    }
                }
            } catch (Throwable ex) {
            }
            this.setOwner(owner.username);
            IPet.playersWithActivePets.put(owner.username, new PetID(this.dimension, this.entityId));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, new CustomMinionEntitySelector(owner)));
        }
        if (!sizeSet) {
            setSize(getBaseWidth(), getBaseHeight());
        }
    }
    //CONSTRUCTORS END

    //ABSTRACTS START
    public abstract float getMountedSpeed();

    public abstract int getAttackDamage();

    public abstract double getMountedYOffset();

    protected abstract float getBaseWidth();

    protected abstract float getBaseHeight();

    public abstract ResourceLocation getTexture();

    protected abstract float jumpHeight();

    public abstract int regenDelay();

    @SideOnly(Side.CLIENT)
    public abstract ModelBase getModel();

    public abstract String getDefaultName();
    //ABSTRACTS END

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    public void setLevel(int Level) {
        levelcheck = Level;
        this.dataWatcher.updateObject(LEVELID, Level);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackDamage());
    }

    public boolean canBeSteered() {
        return true;
    }

    public boolean isDead() {
        //Required! must chain up this check or will return false even when dead.
        return super.isDead;
    }

    @Override
    public void onLivingUpdate() {
        if (!worldObj.isRemote) {
            EntityPlayer player = (EntityPlayer)getOwner();
            if ((player == null || !EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.BEASTMASTER))) {
                try {
                    RpgInv inv = mod_RpgInventory.proxy.getInventory(getOwnerName());
                    inv.setInventorySlotContents(6, writePetToItemStack());
                } catch (Throwable ex) {
                }
                this.setDead();
                return;
            }
            if ((!IPet.playersWithActivePets.containsKey(this.getOwnerName()) || this.dimension != getOwner().dimension)) {
                this.setDead();
                return;
            }
        }

        if (healthregen-- <= 0) {
            this.heal(1);
            this.healthregen = regenDelay();
//            if (!worldObj.isRemote) {
//                this.dataWatcher.updateObject(HP, this.func_110143_aJ());
//            } else {
//                this.health = this.dataWatcher.getWatchableObjectInt(HP);
//            }
        }

            prevLevel = this.dataWatcher.getWatchableObjectInt(LEVELID);

        if (prevLevel != levelcheck) {
            if (!worldObj.isRemote) {
                prevLevel = levelcheck;
                this.dataWatcher.updateObject(LEVELID, levelcheck);
            }
            levelcheck = prevLevel;
            setSize(getBaseWidth() + ((levelcheck / 200.0F) * (1.0F + getBaseWidth())), getBaseHeight() + ((levelcheck / 200.0F) * (1.0F + getBaseHeight())));
        }

        if (sprintToggleTimer > 0) { //used to determine if sprinting should be activated.
            sprintToggleTimer--;
        }
        if (jumpTicks > 0) //used to limit how long the mount will rise while jumping
        {
            jumpTicks--;
        }
        List<EntityPetXP> xps = worldObj.getEntitiesWithinAABB(EntityPetXP.class, boundingBox.copy().expand(0.5D, 0.5D, 0.5D));
        if (xps != null && xps.size() > 0) {
            if (--xpThrottle <= 0) {
               for (EntityPetXP xp : xps) {
                  xpThrottle = 5;
                   this.giveXP(xp.getXpValue());
                    xp.setDead();
                }
            }
        }
        super.onLivingUpdate();

    }

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
                f = Block.blocksList[j].slipperiness * 0.5F;
            }
            //This is currently bugged so temporarily disabled.
            speedBonus = 1F;
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
            //super.moveEntity(d, d1, d2);
            setSprinting(entityRider.isSprinting());
            setSneaking(entityRider.isSneaking());
            if (isSprinting() && inWater || isSprinting() && isInWeb) {
                setSprinting(false);
            }
            //Autojump
            if (isCollidedHorizontally) {
                jump(false);
            }
            //Initiate jumping while ridden via keybind
            if (((EntityPlayer) riddenByEntity).isAirBorne) { //hijacking the preset 'jump' input.
                jump(true); //this method is seen overridden in here
            }
            super.moveEntity(motionX, motionY, motionZ);
        } else {
            super.moveEntity(d, d1, d2);
        }
    }

    protected boolean onIce() { //just a check.
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(posY);
        int k = MathHelper.floor_double(posZ);

        if (worldObj.getBlockId(i, j - 1, k) == Block.ice.blockID) {
            return true;
        }
        return false;
    }

    public void jump(Boolean flag) { //boolean. true = 2.5-high jump. false = normal jump.
        if (onGround && jumpTicks == 0) {
            super.jump();
            if (flag) {
                motionY += (jumpHeight() / 200) * getLevel(); //makes mount jump higher. Do not use big values!
            }
            jumpTicks = 10;
        }
    }

    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        if (!this.isDead()) {
            ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
            if (var2 != null) {
                if (this.func_110143_aJ() < this.func_110138_aP()) {
                    if (Item.itemsList[var2.itemID] instanceof ItemFood) {
                        ItemFood var3 = (ItemFood) Item.itemsList[var2.itemID];
                        //This is confusing, people dont know pet is saddled.
                        //if (!par1EntityPlayer.capabilities.isCreativeMode) {
                        if (--var2.stackSize == 0) {
                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack) null);
                        }
                        //}
                        if (!worldObj.isRemote) {
                            this.heal(var3.getHealAmount());
                        }
                        return true;
                    }
                }
                if (!getSaddled() && this.getLevel() >= 50) {
                    if (var2.itemID == Item.saddle.itemID) {
                        this.setSaddled(true);
                        //Confusing
                        //if (!par1EntityPlayer.capabilities.isCreativeMode) {
                        --var2.stackSize;
                        //}
                        if (var2.stackSize <= 0) {
                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack) null);
                        }
                    }
                }
                if (par1EntityPlayer.getCurrentEquippedItem() != null && par1EntityPlayer.getCurrentEquippedItem().itemID == mod_RpgInventory.petCandy.itemID) {
                    addExperienceLevel(1);
                    par1EntityPlayer.getCurrentEquippedItem().stackSize--;
                }
            } else if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer)) {
                par1EntityPlayer.mountEntity(this);
                return true;
            }
        }
        return super.interact(par1EntityPlayer);
    }

    @Override
    public EntityLivingBase getOwner() {
        EntityPlayer player;
        if (worldObj.isRemote) {
            player = this.worldObj.getPlayerEntityByName(this.getOwnerName());
        } else {
            player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(this.getOwnerName());
        }
        return player;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (riddenByEntity != null) {
            //stops up-and-down head movement
            rotationPitch = 0;
            //Control where the pet is facing (doesn't work while standing still)
            rotationYaw = prevRotationYaw = riddenByEntity.rotationYaw;
        }
    }

    /*protected void updateAITick() {
     //DON'T UPDATE WATCHABLES HERE, IT WILL PACKET FLOOD
     //this.dataWatcher.updateObject(HP, Integer.valueOf(this.getHealth()));
     }*/
    @Override
    public void onKillEntity(EntityLivingBase par1EntityLiving) {
        super.onKillEntity(par1EntityLiving);
    }

    @Override
    public void setDead() {
        if (IPet.playersWithActivePets.containsKey(this.getOwnerName())) {
            RpgInv rpginv = mod_RpgInventory.proxy.getInventory(getOwnerName());
            ItemStack itemizedPet = writePetToItemStack(new ItemStack(mod_RpgInventory.crystal));
            rpginv.setInventorySlotContents(6, itemizedPet);
            IPet.playersWithActivePets.remove(this.getOwnerName());
        }
        super.setDead();
    }

    @Override
    public boolean canAttackClass(Class par1Class) {
        return true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        if (this.func_110143_aJ() <= 0) {
            this.heal(1);
        }
        this.dataWatcher.addObject(LEVELID, (int) 0);
        this.dataWatcher.addObject(NAME, getDefaultName());
//        this.dataWatcher.addObject(HP, this.health);
        this.dataWatcher.addObject(TOTALXP, (int) 0);
        this.dataWatcher.addObject(NEXTLEVEL, String.valueOf(0.0F));
        this.dataWatcher.addObject(SADDLE, (byte) 0);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
    //each pet class returns this value, this just forces them to.

    public float getMaxHealth(){
    	return this.func_110138_aP();
    }

    @Override
    public int getLevel() {
        return levelcheck;
    }

    public int xpBarCap() {
        int exp = getLevel();
        return exp >= 30 ? 62 + (exp - 30) * 7 : (exp >= 15 ? 17 + (exp - 15) * 3 : 17);
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

                for (this.experienceTotal += amount; this.experience >= 1.0F; this.experience /= (float) this.xpBarCap()) {
                    this.experience = (this.experience - 1.0F) * (float) this.xpBarCap();
                    this.addExperienceLevel(1);
                }
                this.dataWatcher.updateObject(NEXTLEVEL, String.valueOf(this.experience));
                this.dataWatcher.updateObject(TOTALXP, this.experienceTotal);
            }
            if (this.getLevel() >= 200) {
                this.dataWatcher.updateObject(LEVELID, 200);
            }
        }
    }

    public void addExperienceLevel(int numLevels) {
        //If the mob is reduced a level(on death) he drops his currently
        //collected xp. This only drops the exp earned since the last earned level.
        //If this is a level gain, experience value is 0(to make sure xp drops only
        //when the level is reduced.
        this.experienceValue = numLevels < 0 ? xpBarCap() - this.experienceTotal : 0;
        int exp = getLevel();
        exp += numLevels;

        if (exp < 0) {
            exp = 0;
            this.experience = 0.0F;
            this.experienceTotal = 0;
        } else {
            heal(200);
        }

        if (numLevels > 0 && exp % 5 == 0 && (float) this.prevTicksExisted < (float) this.ticksExisted - 100.0F) {
            float var2 = exp > 30 ? 1.0F : (float) exp / 30.0F;
            this.worldObj.playSoundAtEntity(this, "random.levelup", var2 * 0.75F, 1.0F);
            this.prevTicksExisted = this.ticksExisted;
        }
        if (!worldObj.isRemote) {
            this.setLevel(exp);
        }

    }
//This is so you can't accidently hurt your pet while riding him.

    public boolean func_85031_j(Entity par1Entity) {
        try {
            if (((EntityPlayer) par1Entity).isRiding() && this.getOwnerName().equals(((EntityPlayer) par1Entity).username)) {
                return true;
            }
        } catch (Throwable ex) {
        }

        return super.func_85031_j(par1Entity);

    }

    @Override
    public int getTotalXP() {
        return this.dataWatcher.getWatchableObjectInt(TOTALXP);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
        //How much experience this mob drops.
        return this.experienceValue;
    }

    @Override
    public ItemStack writePetToItemStack(ItemStack is) {
        return writePetToItemStack();
    }

    @Override
    public ItemStack writePetToItemStack() {
        NBTTagCompound petnbt = new NBTTagCompound();
        NBTTagCompound itemstacknbt = new NBTTagCompound();
        writeEntityToNBT(petnbt);
        itemstacknbt.setCompoundTag("RPGPetInfo", petnbt);
        itemstacknbt.setInteger("PetLevel", this.dataWatcher.getWatchableObjectInt(LEVELID));
        itemstacknbt.setString("PetName", getEntityName());
        itemstacknbt.setString("OwnerName", getOwnerName());
        itemstacknbt.setInteger("PetAttack", getAttackDamage());
        itemstacknbt.setFloat("PetMaxHealth", getMaxHealth());
        itemstacknbt.setFloat("PetHealth", func_110143_aJ());
        itemstacknbt.setBoolean("isSaddled", getSaddled());
        ItemStack newIteamstack = new ItemStack(mod_RpgInventory.crystal, 1, getType());
        newIteamstack.setTagCompound(itemstacknbt);
        newIteamstack.setItemName(getEntityName());
        return newIteamstack;
    }

    @Override
    public void setName(String name) {
        if (!worldObj.isRemote) {
            this.dataWatcher.updateObject(NAME, name);
        }
    }

    @Override
    public String getEntityName() {
        return this.dataWatcher.getWatchableObjectString(NAME);
    }

    @Override
    public abstract float getPetSize();

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
//        if (!worldObj.isRemote) {
//            this.dataWatcher.updateObject(HP, this.health);
//        } else {
//            this.health = this.dataWatcher.getWatchableObjectInt(HP);
//        }
        super.writeEntityToNBT(par1NBTTagCompound);
        //prevents natural despawning
        par1NBTTagCompound.setBoolean("PersistanceRequired", true);
        //Our Cutom Data below
        par1NBTTagCompound.setString("Name", this.dataWatcher.getWatchableObjectString(NAME));
        //Convert string to float
        try {
            par1NBTTagCompound.setFloat("PercentToNextLevel", Float.valueOf(this.dataWatcher.getWatchableObjectString(NEXTLEVEL)));
        } catch (Throwable ex) {
        }
        par1NBTTagCompound.setInteger("XpLevel", this.dataWatcher.getWatchableObjectInt(LEVELID));
        par1NBTTagCompound.setInteger("XpTotal", this.dataWatcher.getWatchableObjectInt(TOTALXP));
        par1NBTTagCompound.setInteger("prevTicksExisted", this.prevTicksExisted);
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
    }

    public final void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.dataWatcher.updateObject(NAME, par1NBTTagCompound.getString("Name"));

        //Convert float to string
        this.experience = par1NBTTagCompound.getFloat("PercentToNextLevel");
        this.dataWatcher.updateObject(NEXTLEVEL, String.valueOf(this.experience));

        this.dataWatcher.updateObject(LEVELID, par1NBTTagCompound.getInteger("XpLevel"));

        this.experienceTotal = par1NBTTagCompound.getInteger("XpTotal");
        this.dataWatcher.updateObject(TOTALXP, this.experienceTotal);

        this.prevTicksExisted = par1NBTTagCompound.getInteger("prevTicksExisted");
        this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
    }

    public boolean getSaddled() {
        //false if set to 0, true otherwise
        return (this.dataWatcher.getWatchableObjectByte(SADDLE)) != 0;
    }

    /**
     * Set or remove the saddle of the pet.
     */
    public void setSaddled(boolean par1) {
        if (!worldObj.isRemote) {
            this.dataWatcher.updateObject(SADDLE, Byte.valueOf(par1 ? (byte) 1 : (byte) 0));
        }
    }

//    @Override
    public float getHealth() {
       return this.func_110143_aJ();
    }

    @Override
    public float getHP() {
        return this.func_110143_aJ();
    }

    
    public EntityAIControlledByPlayer getAIControlledByPlayer() {
        return this.aiControlledByPlayer;
    }

    @Override
    public int getType() {
        return mobType;
    }
}
