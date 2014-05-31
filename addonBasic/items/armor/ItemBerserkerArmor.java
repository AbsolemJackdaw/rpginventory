package addonBasic.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import addonBasic.RpgBaseAddon;

public class ItemBerserkerArmor extends AbstractArmor {

	public ItemBerserkerArmor(ArmorMaterial arm, int par3, int par4) {
		super(par3, par4, arm);
	}

	@Override
	public String armorClassName() {
		return RpgBaseAddon.CLASSBERSERKER;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if (stack != null) {
			if (stack.getItem() instanceof AbstractArmor) {

				int type = ((AbstractArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3)) {
					armorModel = RpgBaseAddon.proxy.getArmorModel(3);
				} else {
					armorModel = RpgBaseAddon.proxy.getArmorModel(2);
				}
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.getItem() == RpgBaseAddon.berserkerHood)
				|| (itemstack.getItem() == RpgBaseAddon.berserkerChest)
				|| (itemstack.getItem() == RpgBaseAddon.berserkerBoots)) {
			return "armor:berserk_1.png";
		}
		if (itemstack.getItem() == RpgBaseAddon.berserkerLegs) {
			return "armor:berserk_2.png";
		}
		return null;
	}
}
