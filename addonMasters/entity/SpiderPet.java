/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import addonMasters.entity.models.ModelSpiderB;

/**
 * 
 * @author Home
 */
public class SpiderPet extends BMPetImpl {

	float petSize = 0.5F;
	ModelSpiderB model = new ModelSpiderB();

	ResourceLocation normal = new ResourceLocation("subaraki:mobs/spider.png");
	ResourceLocation saddled = new ResourceLocation(
			"subaraki:mobs/spider_saddled.png");

	public SpiderPet(World par1World) {
		super(par1World, 2, null, null);
		// this.moveSpeed = 0.45F;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setAvoidSun(true);
		// this.getNavigator().setSpeed(this.moveSpeed);
		// Spider Cant Swim
		this.getNavigator().setCanSwim(false);
	}

	public SpiderPet(World par1World, EntityPlayer owner, ItemStack is) {
		super(par1World, 2, owner, is);
		// These get called for both, server calls this one, client gets the
		// other.
		// this.moveSpeed = 0.45F;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setAvoidSun(true);
		// this.getNavigator().setSpeed(this.moveSpeed);
		// Spider Cant Swim
		this.getNavigator().setCanSwim(false);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if (par1Entity instanceof EntityLiving) {
				int level = getLevel();
				if (level > 10) {
					((EntityLiving) par1Entity)
							.addPotionEffect(new PotionEffect(Potion.poison.id,
									200, level < 50 ? 1 : 2));
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int getAttackDamage() {
		// 6 Base Damage
		// 25 Damage at level 200
		return (6 + MathHelper.floor_double(((getLevel()) * 1.0D) / (10.5263D)));
	}

	@Override
	protected float getBaseHeight() {
		return 0.45F;
	}

	@Override
	public float getBaseWidth() {
		return 0.8F;
	}

	@Override
	public String getDefaultName() {
		return "Spider Pet";
	}

	@Override
	public ModelBase getModel() {
		return model;
	}

	@Override
	public float getMountedSpeed() {
		return 0.6F;
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
		return 0.3F;
	}

	// @Override TODO
	// public float getMaxHealth() {
	// //150 health at level 200
	// return 25 + MathHelper.floor_double((((double) getLevel()) * 1.0D) /
	// 1.6D);
	// }

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getLevel() <= 200) {
			petSize = 0.5F + (((getLevel()) / 200.0F) * 1.5F);
		} else {
			setLevel(200);
			petSize = 2.0F;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (previousLevel < getLevel()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
					.setBaseValue(
							25D + MathHelper
									.floor_double(((getLevel()) * 1.0D) / 1.6D));
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
					.setBaseValue(0.1D + (getLevel() / 400D));
			previousLevel = getLevel();
		}
	}

	@Override
	public int regenDelay() {
		return 50;
	}

	@Override
	protected void updateAITick() {
		super.updateAITick();
	}
}
