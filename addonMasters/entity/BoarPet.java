/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import addonMasters.entity.models.ModelBoar;

/**
 * 
 * @author Home
 */
public class BoarPet extends BMPetImpl {

	boolean checked = false;
	float petSize = 0.5F;
	ModelBoar model = new ModelBoar();

	ResourceLocation normal = new ResourceLocation("subaraki:mobs/boar.png");
	ResourceLocation saddled = new ResourceLocation(
			"subaraki:mobs/boar_saddled.png");

	public BoarPet(World par1World) {
		this(par1World, null, null);
		this.moveSpeed = 0.58F;
		// Doesn't like water, but can swim.
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setCanSwim(true);
	}

	public BoarPet(World par1World, EntityPlayer owner, ItemStack is) {
		super(par1World, 1, owner, is);
		this.moveSpeed = 0.58F;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setSpeed(this.moveSpeed);
		this.getNavigator().setCanSwim(true);
	}

	@Override
	public int getAttackDamage() {
		// 7 Base Damage
		// 30 Damage at level 200
		return (7 + MathHelper.floor_double(((getLevel()) * /*
															 * Fix for math
															 * errors
															 */1.0D) / 9.52D));
	}

	@Override
	protected float getBaseHeight() {
		return 0.5F;
	}

	@Override
	public float getBaseWidth() {
		return 0.5F;
	}

	@Override
	public String getDefaultName() {
		return "Boar Pet";
	}

	@Override
	public ModelBase getModel() {
		return model;
	}

	@Override
	public float getMountedSpeed() {
		return 0.7F;
	}

	@Override
	public double getMountedYOffset() {
		return this.height + (getLevel() / 400);
	}

	@Override
	public float getPetSize() {
		return petSize;
	}

	@Override
	public ResourceLocation getTexture() {
		return dataWatcher.getWatchableObjectByte(SADDLE) == 0 ? normal
				: saddled;
	}

	@Override
	protected float jumpHeight() {
		return 0.7F;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getLevel() <= 200)
			petSize = 0.5F + (((getLevel()) / 200.0F) * 1.5F);
		else
			petSize = 2.0F;
	}

	// @Override
	// TODO !!
	// public float setMaxHealth() {
	// //115 Health at level 200
	// return 20 + MathHelper.floor_float(((float) getLevel()) / 2.5F);
	// }
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (previousLevel < getLevel()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
					.setBaseValue(
							20D + MathHelper.floor_double((getLevel()) / 2.5D));
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
					.setBaseValue(getLevel() / 600D);

			previousLevel = getLevel();
		}
	}

	@Override
	public int regenDelay() {
		return 60;
	}
}