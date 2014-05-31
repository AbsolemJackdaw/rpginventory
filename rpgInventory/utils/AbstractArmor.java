package rpgInventory.utils;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbstractArmor extends ItemArmor {

	public ModelBiped armorModel;

	public static final int BOOTS = 36;
	public static final int LEGS = 37;
	public static final int CHEST = 38;
	public static final int HELM = 39;

	public AbstractArmor(int par1, int par2, ItemArmor.ArmorMaterial mats) {
		super(mats, par1, par2);
	}

	/** returns the name of the class from this full set of armor */
	public abstract String armorClassName();

	/**
	 * Called to set the 3D armor model. set models here, not in
	 * getArmorModel(...) !*/
	@SideOnly(Side.CLIENT)
	protected abstract void get3DArmorModel(EntityLivingBase elb,
			ItemStack stack, int armorSlot);

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving,
			ItemStack itemStack, int armorSlot) {
		if (itemStack != null) {
			get3DArmorModel(entityLiving, itemStack, armorSlot);
			if (armorModel != null) {
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = (armorSlot == 1)
						|| (armorSlot == 2);
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = (armorSlot == 2)
						|| (armorSlot == 3);
				armorModel.bipedLeftLeg.showModel = (armorSlot == 2)
						|| (armorSlot == 3);

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1
						: 0;
				if (entityLiving instanceof EntityPlayer) {
					armorModel.aimedBow = ((EntityPlayer) entityLiving)
							.getItemInUseDuration() > 2;
				}
				return armorModel;
			}
		}
		return null;
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().lastIndexOf(".") + 1);
		this.iconString = "rpginventorymod:" + itemName;

		return this;
	}
}
