package addonMasters.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import addonMasters.entity.models.ModelRooster;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChickenPet extends BMPetImpl {

	private static final ModelRooster model = new ModelRooster();
	
	ResourceLocation normal = new ResourceLocation("rpginventorymod:pet/rooster.png");
	ResourceLocation saddled = new ResourceLocation("rpginventorymod:pet/rooster_saddled.png");
	
	public ChickenPet(World par1World, EntityPlayer owner,ItemStack is) {
		super(par1World, 4, owner, is);
		this.getNavigator().setCanSwim(true);
	}
	
	public ChickenPet(World par1World) {
		super(par1World, 2, null, null);
		this.getNavigator().setCanSwim(true);
	}

	@Override
	public int getAttackDamage() {
		return (2 + MathHelper.floor_double(((getLevel()) * 1.0D) / 14.25D));
	}

	@Override
	protected float getBaseHeight() {
		return 0.5f;
	}

	@Override
	protected float getBaseWidth() {
		return 0.5f;
	}

	@Override
	public String getDefaultName() {
		return "Chicken Pet";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel() {
		return model;
	}

	@Override
	public float getMountedSpeed() {
		return 0;
	}

	@Override
	public double getMountedYOffset() {
		//TODO test and change
		return this.height + (getLevel() / 400);
	}

	@Override
	public float getPetSize() {
		return petSize;
	}

	@Override
	public ResourceLocation getTexture() {
		return dataWatcher.getWatchableObjectByte(SADDLE) == 0 ? normal: saddled;
	}

	@Override
	protected float jumpHeight() {
		return 0.2f;
	}

	@Override
	public int regenDelay() {
		return 30;
	}

	@Override
	public double getHealthIncreaseForLeveling() {
		return 15D + (MathHelper.floor_double(getLevel() * 1.0D) / 2.35D);
	}

	@Override
	public double getSpeedIncreaseForLeveling() {
		return 0.3D + ((double)getLevel()*1.0D)/250D;
	}

	
	public float field_70886_e;
    public float destPos;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;
    
    @Override
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);

        if (this.destPos < 0.0F)
        {
            this.destPos = 0.0F;
        }

        if (this.destPos > 1.0F)
        {
            this.destPos = 1.0F;
        }

        if (!this.onGround && this.field_70889_i < 1.0F)
        {
            this.field_70889_i = 1.0F;
        }

        this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.field_70886_e += this.field_70889_i * 2.0F;
    }
	
}
