package addonMasters.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChickenPet extends BMPetImpl {

	public ChickenPet(World par1World, int mobType, EntityPlayer owner,
			ItemStack is) {
		super(par1World, mobType, owner, is);
		
	}

	@Override
	public int getAttackDamage() {
		return 0;
	}

	@Override
	protected float getBaseHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected float getBaseWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDefaultName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getMountedSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMountedYOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPetSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResourceLocation getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected float jumpHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int regenDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

}
