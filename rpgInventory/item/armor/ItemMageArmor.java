package rpgInventory.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.utils.AbstractArmor;

public class ItemMageArmor extends AbstractArmor {

	public ItemMageArmor(ArmorMaterial arm, int par1, int par2) {
		super(par1, par2, arm);
	}

	@Override
	public String armorClassName() {
		return mod_RpgInventory.CLASSMAGE;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if (stack != null) {
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3)) {
					armorModel = mod_RpgInventory.proxy.getArmorModel(1);
				} else {
					armorModel = mod_RpgInventory.proxy.getArmorModel(0);
				}
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if ((itemstack.getItem() == mod_RpgInventory.magehood)
				|| (itemstack.getItem() == mod_RpgInventory.magegown)
				|| (itemstack.getItem() == mod_RpgInventory.mageboots)) {
			return "armor:mage_1.png";
		}
		if (itemstack.getItem() == mod_RpgInventory.magepants) {
			return "armor:mage_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}
}
