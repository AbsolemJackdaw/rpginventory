/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.beastmaster;

import RpgRB.models.ModelSpiderB;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class SpiderPet extends BMPetImpl {

    float petSize = 0.5F;

    public SpiderPet(World par1World) {
        super(par1World, 2, null, null);
    }

    @Override
    public float getPetSize() {
        return petSize;
    }

    public SpiderPet(World par1World, EntityPlayer owner, ItemStack is) {
        super(par1World, 2, owner, is);
    }

    public double getMountedYOffset() {
        return (double) this.height * ((float) getPetSize()) - getLevel() / 100;
    }

    @Override
    protected void updateAITick() {
        super.updateAITick();
    }

    @Override
    public ModelBase getModel() {
        return new ModelSpiderB();
    }

    @Override
    public int getAttackDamage() {
        return this.getLevel() <= 100 ? 5 + this.getLevel() / 10 : 10 + this.getLevel() / 20;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (super.attackEntityAsMob(par1Entity)) {
            if (par1Entity instanceof EntityLiving) {
                ((EntityLiving) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
            }
            return true;
        }
        return false;
    }

    @Override
    public String getTexture() {
        return "/subaraki/mobs/ModelSpider.png";
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
    public float getBaseWidth() {
        return 0.8F;
    }

    @Override
    protected float getBaseHeight() {
        return 0.45F;
    }

    @Override
    public String getDefaultName() {
        return "Spider Pet";
    }

    @Override
    public int getMaxHealth() {
        return 18 + MathHelper.floor_float(((float) getLevel()) / 2.2F);
    }

    @Override
    public float getMountedSpeed() {
        return 0.7F;
    }
}
