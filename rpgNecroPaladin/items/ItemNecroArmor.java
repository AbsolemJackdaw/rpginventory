package rpgNecroPaladin.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import rpgNecroPaladin.mod_RpgPlus;

public class ItemNecroArmor extends AbstractArmor {

	public ItemNecroArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
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

		if ((itemstack.itemID == mod_RpgPlus.necroHood.itemID)
				|| (itemstack.itemID == mod_RpgPlus.necroChestplate.itemID)
				|| (itemstack.itemID == mod_RpgPlus.necroBoots.itemID)) {
			return "armor:necro_1.png";
		}
		if (itemstack.itemID == mod_RpgPlus.necroLeggings.itemID) {
			return "armor:necro_2.png";
		}
		return null;
	}
}
