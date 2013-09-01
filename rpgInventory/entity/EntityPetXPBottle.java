/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class EntityPetXPBottle extends EntityExpBottle{

    public EntityPetXPBottle(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityPetXPBottle(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityPetXPBottle(World par1World) {
        super(par1World);
    }
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
            this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), 0);
            int i = 3 + this.worldObj.rand.nextInt(5) + this.worldObj.rand.nextInt(5);

            while (i > 0)
            {
                int j = EntityPetXP.getXPSplit(i);
                i -= j;
                this.worldObj.spawnEntityInWorld(new EntityPetXP(this.worldObj, this.posX, this.posY, this.posZ, j));
            }

            this.setDead();
        }
    }
}
