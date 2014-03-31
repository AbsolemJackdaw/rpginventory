package addonDread.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import addonDread.RpgDreadAddon;

public class ItemNecroArmor extends AbstractArmor {

	public ItemNecroArmor(ArmorMaterial par2EnumArmorMaterial, int par3,
			int par4) {
		super(par3, par4, par2EnumArmorMaterial);
	}

	@Override
	public String armorClassName() {
		return RpgDreadAddon.CLASSNECRO;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {

		if (stack != null)
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3))
					armorModel = RpgDreadAddon.proxy.getArmorModel(0);
				else
					armorModel = RpgDreadAddon.proxy.getArmorModel(1);
			}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.getItem() == RpgDreadAddon.necroHood)
				|| (itemstack.getItem() == RpgDreadAddon.necroChestplate)
				|| (itemstack.getItem() == RpgDreadAddon.necroBoots))
			return "armor:necro_1.png";
		if (itemstack.getItem() == RpgDreadAddon.necroLeggings)
			return "armor:necro_2.png";
		return null;
	}
}
