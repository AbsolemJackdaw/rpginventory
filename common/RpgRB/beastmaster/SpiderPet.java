/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.beastmaster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class SpiderPet extends BMPetImpl {

    public SpiderPet(World par1World) {
        super(par1World, 2, null, null);
    }

    public SpiderPet(World par1World, EntityPlayer owner, ItemStack is) {
        super(par1World, 2, owner, is);
    }

    public AxisAlignedBB getCollisionBox(Entity par1Entity) {
        return par1Entity.boundingBox;
    }

    public double getMountedYOffset() {
        return (double) this.height * (double) 0.5F + ((((float) getLevel()) / 200.0F) * 1.5F) - 0.7;
    }

    @Override
    protected void updateAITick() {
        super.updateAITick();
    }

    @Override
    public int getAttackDamage() {
        return this.getLevel() <= 100 ? 4 + this.getLevel() / 10 : 9 + this.getLevel() / 20;
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

    @Override
    public float getSize() {
        if (getLevel() <= 200) {
            return 0.5F + ((((float) getLevel()) / 200.0F) * 1.5F);
        } else {
            return 2.0F;
        }
    }
}
