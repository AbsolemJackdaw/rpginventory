/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgRogueBeast.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rpgRogueBeast.entity.models.ModelBull;

/**
 *
 * @author Home
 */
public class BullPet extends BMPetImpl {

    float petSize = 0.5F;
    ModelBull model = new ModelBull();
    public BullPet(World par1World) {
        super(par1World, 3, null, null);
        this.moveSpeed = 0.38F;
        //Likes to swim.
        this.getNavigator().setAvoidsWater(false);
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setSpeed(this.moveSpeed);
    }

    public BullPet(World par1World, EntityPlayer owner, ItemStack is) {
        super(par1World, 3, owner, is);
        this.moveSpeed = 0.38F;
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setSpeed(this.moveSpeed);
    }

    public double getMountedYOffset() {
        return this.height + (((double)getLevel()) * 1.0D) / 350.0D;
    }

    @Override
    protected void updateAITick() {
        super.updateAITick();
    }

    @Override
    public float getBaseWidth() {
        return 0.5F;
    }

    @Override
    protected float getBaseHeight() {
        return 0.7F;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation("subaraki:mobs/bull.png");
    }

    @Override
    public String getDefaultName() {
        return "Bull Pet";
    }
    @Override
    public int getAttackDamage() {
        //4 Base Damage
        //15 Damage at level 200
        return (4 + MathHelper.floor_double((((double) getLevel()) * /*Fix for math errors*/1.0D) / 18.18D));
    }
    @Override
    public float getMaxHealth() {
        //200 HP at level 200
        return 30 + MathHelper.floor_float(((float) getLevel()) / 1.538F);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (getLevel() <= 200) {
            petSize = 0.5F + ((((float) getLevel()) / 200.0F) * 1.5F);
        } else {
            petSize = 2.0F;
        }
    }

    @Override
    public float getPetSize() {
        return petSize;
    }

    @Override
    public float getMountedSpeed() {
        return 0.02F;
    }
    @Override
    public ModelBase getModel() {
        return model;
    }

    @Override
    protected float jumpHeight() {
        return 0.48F;
    }
    @Override
    public int regenDelay() {
        return 40;
    }
}
