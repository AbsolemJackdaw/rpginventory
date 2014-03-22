package rpgMage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;

public class ItemMageARmor extends AbstractArmor {

	public ItemMageARmor(ItemArmor.ArmorMaterial enumArmorMaterial, int par3,
			int par4) {
		super(par3, par4, enumArmorMaterial);

	}

	@Override
	public String armorClassName() {
		return mod_RpgMageSet.CLASSARCHMAGE;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {

		int type = ((ItemArmor) stack.getItem()).armorType;
		if ((type == 1) || (type == 3)) {
			armorModel = mod_RpgMageSet.proxy.getArmorModel(0);

		} else {
			armorModel = mod_RpgMageSet.proxy.getArmorModel(1);
		}

	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if ((itemstack.getItem() == mod_RpgMageSet.archmageHood)
				|| (itemstack.getItem() == mod_RpgMageSet.archmageChest)
				|| (itemstack.getItem() == mod_RpgMageSet.archMageBoots)) {
			return "armor:archMage_1.png";
		}
		if (itemstack.getItem() == mod_RpgMageSet.archmageLegs) {
			return "armor:archMage_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}

}
