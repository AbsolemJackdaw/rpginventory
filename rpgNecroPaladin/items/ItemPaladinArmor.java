package rpgNecroPaladin.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import rpgNecroPaladin.mod_RpgPlus;

public class ItemPaladinArmor extends AbstractArmor {

	public ItemPaladinArmor( ArmorMaterial enumArmorMaterial,
			int par3, int par4) {
		super(par3, par4, enumArmorMaterial);
	}

	@Override
	public String armorClassName() {
		// TODO Auto-generated method stub
		return mod_RpgPlus.CLASSPALADIN;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if (stack != null) {
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3)) {
					armorModel = mod_RpgPlus.proxy.getArmorModel(2);
				} else {
					armorModel = mod_RpgPlus.proxy.getArmorModel(3);
				}
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.getItem() == mod_RpgPlus.palaHelm)
				|| (itemstack.getItem() == mod_RpgPlus.palaChest)
				|| (itemstack.getItem() == mod_RpgPlus.palaBoots)) {
			return "armor:pal_1.png";
		}
		if (itemstack.getItem() == mod_RpgPlus.palaLeggings) {
			return "armor:pal_2.png";
		}
		return null;
	}

}
