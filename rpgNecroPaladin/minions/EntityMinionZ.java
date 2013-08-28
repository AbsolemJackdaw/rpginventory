package rpgNecroPaladin.minions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
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
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMinionZ extends EntityTameable implements IMob, IMinion {

    private EntityPlayer player;
    boolean invulnerable = false;
    @Override
    public void setInvulnerable(boolean val) {
        invulnerable = val;
    }

    @Override
    public boolean isEntityInvulnerable() {
        return invulnerable;
    }

    public EntityMinionZ(World var1, EntityPlayer player) {
        this(var1);
        this.player = player;
        this.setSize(1.0f, 1.0f);
        //This makes minions aggressive to aggressive mobs and players, except for their owner.
        //They will not provoke creepers, so they dont blow themselves up.
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, new CustomMinionEntitySelector(player)));
        //they tend to wander off sometimes if they get too excited, but they will snap back to the player if they wander too far.
        if (!MinionRegistry.playerMinions.containsKey(player.username)) {
            MinionRegistry.playerMinions.put(player.username, new ArrayList<IMinion>());
        }
        List<IMinion> list = MinionRegistry.playerMinions.get(player.username);
        if (!list.contains(this)) {
            list.add(this);
        }
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

    public void Harvest() {
        this.damageEntity(DamageSource.magic, this.func_110143_aJ());
        if (player.func_110143_aJ() + 2 <= player.func_110138_aP()) {
            player.heal(2);
        } else {
            player.setEntityHealth(player.func_110138_aP());
        }
    }

    public EntityMinionZ(World var1) {
        super(var1);
//        this.texture = "/mob/zombie.png";
//        this.moveSpeed = 0.35F;
        this.Following();
        this.getNavigator().setAvoidsWater(true);

        this.setPathToEntity((PathEntity) null);
        this.setAttackTarget((EntityLiving) null);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIFollowOwner(this, 0.32f, 10.0F, 2.0F));
        this.tasks.addTask(3, new EntityAIWander(this, 0.32f));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackOnCollide(this, 0.32f, true));
        //This lets the mobs look around at any living entity mobs, to see if they need to attack it or not. Also makes minions appear curious ;)
        this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.tasks.addTask(2, this.aiSit);
        this.player = this.worldObj.getPlayerEntityByName(this.getOwnerName());
this.setSize(0.7F, 1.7F);

    }

    public EntityPlayer getMaster() {
        return player;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    public int getAge() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setAge(int par1) {
        this.dataWatcher.updateObject(20, Integer.valueOf(par1));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, new Integer(0));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.player != null) {
            int age = this.getAge();

            if (age >= 0 && age < 700 + (player.experienceLevel * 30)) {
                ++age;
                this.setAge(age);
            } else if (age >= 700 + (player.experienceLevel * 30)) {
                this.attackEntityFrom(DamageSource.outOfWorld, this.func_110143_aJ());
                age = 0;
            }
        } else {
            //player logged out, kill his minions.
            this.attackEntityFrom(DamageSource.outOfWorld, this.func_110143_aJ());
            this.setAge(0);
        }

    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
        Entity var3 = par1DamageSource.getEntity();

        if (var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof EntityArrow)) {
            par2 = (par2 + 1) / 2;
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        int var2 = this.isTamed() ? 4 : 2;
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
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

//    public int getMaxHealth() {
//        //12 health is reasonable given the sheer number of minions.
//        return 12;
//    }

    public boolean isAIEnabled() {
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
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

    public boolean interact(EntityPlayer par1EntityPlayer) {
        //player right clicked minion
        //example of use:
        /*
         if(par1EntityPlayer == player){
         //this executes an AI task
         this.aiSit.setSitting(true);
         return true;
         }
         */

        return false;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
}
