package addonBasic.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import addonBasic.mod_addonBase;

public class ItemBerserkerArmor extends AbstractArmor {

	public ItemBerserkerArmor(ArmorMaterial arm, int par3, int par4) {
		super(par3, par4, arm);
	}

	@Override
	public String armorClassName() {
		return mod_addonBase.CLASSBERSERKER;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if (stack != null)
			if (stack.getItem() instanceof AbstractArmor) {

				int type = ((AbstractArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3))
					armorModel = mod_addonBase.proxy.getArmorModel(3);
				else
					armorModel = mod_addonBase.proxy.getArmorModel(2);
			}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.getItem() == mod_addonBase.berserkerHood)
				|| (itemstack.getItem() == mod_addonBase.berserkerChest)
				|| (itemstack.getItem() == mod_addonBase.berserkerBoots))
			return "armor:berserk_1.png";
		if (itemstack.getItem() == mod_addonBase.berserkerLegs)
			return "armor:berserk_2.png";
		return null;
	}
}
