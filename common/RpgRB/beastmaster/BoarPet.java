/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgRB.beastmaster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class BoarPet extends BMPetImpl {

    public BoarPet(World par1World) {
        super(par1World, 1, null, null);
        this.moveSpeed = 0.50F;
        this.getNavigator().setAvoidsWater(false);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setSpeed(0.50F);
    }

    public BoarPet(World par1World, EntityPlayer owner, ItemStack is) {
        super(par1World, 1, owner, is);
    }

    public AxisAlignedBB getCollisionBox(Entity par1Entity) {
        return par1Entity.boundingBox;
    }

    public double getMountedYOffset() {
        return (double) this.height * (double) 0.5F + ((((float) getLevel()) / 200.0F) * 1.5F) - 0.5;
    }

    @Override
    public int getAttackDamage() {
        return this.getLevel() <= 100 ? 5 + this.getLevel() / 10 : 9 + this.getLevel() / 20;
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
    public float getSize() {
        if (getLevel() <= 200) {
            return 0.5F + ((((float) getLevel()) / 200.0F));
        } else {
            return 2.0F;
        }
    }
}