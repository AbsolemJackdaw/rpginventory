/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity.pet;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import addonMasters.entity.BeastMasterPet;

/**
 *
 * @author Home
 */
public class SpiderPet extends BeastMasterPet {

	private static final ResourceLocation spiderTexture = new ResourceLocation("textures/entity/spider/cave_spider.png");
	private static final ResourceLocation normal = new ResourceLocation("subaraki:mob/spider.png");
	private static final ResourceLocation saddled = new ResourceLocation("subaraki:mob/spider_saddled.png");

	public SpiderPet(World par1World) {
		super(par1World, 2, null, null);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setAvoidSun(true);
		this.getNavigator().setCanSwim(false);
	}

	public SpiderPet(World par1World, EntityPlayer owner, ItemStack is) {
		super(par1World, 2, owner, is);
		// These get called for both, server calls this one, client gets the
		// other.
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setAvoidSun(true);
		this.getNavigator().setCanSwim(false);
	}


	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		if(par1Entity instanceof EntityLiving){
			EntityLiving el = (EntityLiving) par1Entity;
			el.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)getOwner()), par2);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if (par1Entity instanceof EntityLiving) {
				if (getLevel() > 10) {
					if(rand.nextInt(10) ==0)
						((EntityLiving) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id,200, getLevel() < 50 ? 1 : 2));
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
		return getLevel() < 50 ? getModel(0) : getModel(1);
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
		return getLevel() < 50 ? spiderTexture : !isSaddled() ? normal: saddled;
	}

	@Override
	protected float jumpHeight() {
		return 0.3F;
	}

	@Override
	public void onLivingUpdate(){
		super.onLivingUpdate();
		if (getLevel() <= 200) {
			petSize = 0.5F + (((getLevel()) / 200.0F) * 1.5F);
		} else {
			setLevel(200);
			petSize = 2.0F;
		}
	}

	@Override
	public double getHealthIncreaseForLeveling() {
		return 25D + MathHelper.floor_double(((getLevel()) * 1.0D) / 1.6D);
	}

	@Override
	public double getSpeedIncreaseForLeveling() {
		return 0.2D + (getLevel() / 400D);
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
