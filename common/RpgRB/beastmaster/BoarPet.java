/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.beastmaster;

import RpgRB.models.ModelBoar;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class BoarPet extends BMPetImpl {
    boolean checked = false;
    public BoarPet(World par1World) {
        this(par1World, null, null);
    }

    public BoarPet(World par1World, EntityPlayer owner, ItemStack is) {
        super(par1World, 1, owner, is);
        this.moveSpeed = 0.50F;
        this.getNavigator().setAvoidsWater(false);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setSpeed(0.50F);
    }

    public double getMountedYOffset() {
        return (double) this.height * ((float) getPetSize())- getLevel()/100;
    }

    @Override
    public int getAttackDamage() {
        return this.getLevel() <= 100 ? 6 + this.getLevel() / 10 : 12 + this.getLevel() / 20;
    }

    @Override
    public String getTexture() {
        return "/subaraki/mobs/boar.png";
    }

    @Override
    public String getDefaultName() {
        return "Boar Pet";
    }

    @Override
    public int getMaxHealth() {
        return 15 + MathHelper.floor_float(((float) getLevel()) / 2.5F);
    }

    @Override
    public float getPetSize() {
        if (getLevel() <= 200) {
            return 0.5F + ((((float) getLevel()) / 200.0F) * 1.5F);
        } else {
            return 2.0F;
        }
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
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public float getMountedSpeed() {
        return 0.7F;
    }

    @Override
    public ModelBase getModel() {
        return new ModelBoar();
    }
}