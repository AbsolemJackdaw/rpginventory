package rpgAddonDread.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgAddonDread.mod_RpgPlus;
import rpgInventory.utils.AbstractArmor;

public class ItemNecroArmor extends AbstractArmor {

	public ItemNecroArmor(ArmorMaterial par2EnumArmorMaterial, int par3,
			int par4) {
		super(par3, par4, par2EnumArmorMaterial);
	}

	@Override
	public String armorClassName() {
		return mod_RpgPlus.CLASSNECRO;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {

		if (stack != null) {
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3)) {
					armorModel = mod_RpgPlus.proxy.getArmorModel(0);
				} else {
					armorModel = mod_RpgPlus.proxy.getArmorModel(1);
				}
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.getItem() == mod_RpgPlus.necroHood)
				|| (itemstack.getItem() == mod_RpgPlus.necroChestplate)
				|| (itemstack.getItem() == mod_RpgPlus.necroBoots)) {
			return "armor:necro_1.png";
		}
		if (itemstack.getItem() == mod_RpgPlus.necroLeggings) {
			return "armor:necro_2.png";
		}
		return null;
	}
}
