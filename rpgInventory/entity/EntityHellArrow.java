package rpgInventory.entity;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHellArrow extends EntityArrow {

    World world;
	public EntityHellArrow(World par1World){
            super(par1World);
            world = par1World;
        }
	public EntityHellArrow(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
                world = par1World;
	}

	 public void onUpdate()
	    {
	        super.onUpdate();
                if(!world.isRemote){
                if(!world.isAirBlock( MathHelper.floor_double(posX),  MathHelper.floor_double(posY),  MathHelper.floor_double(posZ))){
                    setDead();
                }else if(!world.isAirBlock( MathHelper.floor_double(posX),  MathHelper.floor_double(posY) - 1,  MathHelper.floor_double(posZ))){
                    setDead();
                }}
               
	    }

}
