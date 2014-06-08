/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity.pet;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import addonMasters.entity.BeastMasterPet;
import addonMasters.entity.models.ModelBull;

/**
 *
 * @author Home
 */
public class BullPet extends BeastMasterPet {

	private static final ResourceLocation normal = new ResourceLocation("rpginventorymod:pet/bull.png");
	private static final ResourceLocation saddled = new ResourceLocation("rpginventorymod:pet/bull_saddled.png");
	private static final ResourceLocation cowTexture = new ResourceLocation("textures/entity/cow/cow.png");

	private ModelBull model = new ModelBull();
	private ModelCow cow = new ModelCow();

	public BullPet(World par1World) {
		super(par1World, 3, null, null);
		this.getNavigator().setAvoidsWater(false);
		this.getNavigator().setCanSwim(true);
	}

	public BullPet(World par1World, EntityPlayer owner, ItemStack is) {
		super(par1World, 3, owner, is);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(true);
	}

	@Override
	public int getAttackDamage() {
		// 4 Base Damage
		// 15 Damage at level 200
		return (4 + MathHelper.floor_double(((getLevel()) * 1.0D) / 14.18D));
	}

	@Override
	protected float getBaseHeight() {
		return 0.7F;
	}

	@Override
	public float getBaseWidth() {
		return 0.5F;
	}

	@Override
	public String getDefaultName() {
		return "Bull Pet";
	}

	@Override
	public ModelBase getModel() {
		return getLevel() < 50 ? cow : model;
	}

	@Override
	public float getMountedSpeed() {
		return 0.02F;
	}

	@Override
	public double getMountedYOffset() {
		return this.height + (((getLevel()) * 1.0D) / 350.0D);
	}

	@Override
	public float getPetSize() {
		return petSize;
	}

	@Override
	public ResourceLocation getTexture() {
		return getLevel() < 50 ? cowTexture : !isSaddled() ? normal
				: saddled;
	}

	@Override
	protected float jumpHeight() {
		return 0.48F;
	}

	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		if (getLevel() <= 200) {
			petSize = 0.5F + (((getLevel()) / 200.0F) * 1.5F);
		} else {
			petSize = 2.0F;
		}
	}

	@Override
	public double getHealthIncreaseForLeveling() {
		return 30D + MathHelper.floor_double((getLevel()) / 1.538D);
	}

	@Override
	public double getSpeedIncreaseForLeveling() {
		return 0.2D + (getLevel() / 500D);
	}

	@Override
	public int regenDelay() {
		return 40;
	}

	@Override
	protected void updateAITick() {
		super.updateAITick();
	}
}
