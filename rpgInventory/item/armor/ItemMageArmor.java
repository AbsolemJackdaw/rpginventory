package rpgInventory.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.utils.AbstractArmor;

public class ItemMageArmor extends AbstractArmor {

	public ItemMageArmor(int par1, int par3, int par4) {
		super(par1, par3, par4);
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
		if ((itemstack.itemID == mod_RpgInventory.magehood.itemID)
				|| (itemstack.itemID == mod_RpgInventory.magegown.itemID)
				|| (itemstack.itemID == mod_RpgInventory.mageboots.itemID)) {
			return "armor:mage_1.png";
		}
		if (itemstack.itemID == mod_RpgInventory.magepants.itemID) {
			return "armor:mage_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}
}
