package addonBasic.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import addonBasic.mod_addonBase;

public class ItemArcherArmor extends AbstractArmor {

	public ItemArcherArmor(ArmorMaterial arm, int par3, int par4) {
		super(par3, par4, arm);
	}

	@Override
	public String armorClassName() {
		return mod_addonBase.CLASSARCHER;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		// archers dont have a custom model
		this.armorModel = null;
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if ((itemstack.getItem() == mod_addonBase.archerboots)
				|| (itemstack.getItem() == mod_addonBase.archerchest)
				|| (itemstack.getItem() == mod_addonBase.archerhood))
			return "armor:arch_1.png";
		if (itemstack.getItem() == mod_addonBase.archerpants)
			return "armor:arch_2.png";
		return super.getArmorTexture(itemstack, entity, slot, type);
	}
}
