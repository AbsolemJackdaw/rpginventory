/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.beastmaster;

import RpgRB.models.ModelBull;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class BullPet extends BMPetImpl {

    float petSize = 0.5F;

    public BullPet(World par1World) {
        super(par1World, 3, null, null);
    }

    public BullPet(World par1World, EntityPlayer owner, ItemStack is) {
        super(par1World, 3, owner, is);
    }

    public double getMountedYOffset() {
        return (double) this.height * ((float) getPetSize()) - getLevel() / 200;
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
        return 0.5F;
    }

    @Override
    public int getAttackDamage() {
        return this.getLevel() <= 100 ? 4 + this.getLevel() / 10 : 8 + this.getLevel() / 20;
    }

    @Override
    public String getTexture() {
        return "/subaraki/mobs/modelBull.png";
    }

    @Override
    public String getDefaultName() {
        return "Bull Pet";
    }

    @Override
    public int getMaxHealth() {
        return 20 + MathHelper.floor_float(((float) getLevel()) / 2F);
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
        return 0.6F;
    }

    @Override
    public ModelBase getModel() {
        return new ModelBull();
    }
}
