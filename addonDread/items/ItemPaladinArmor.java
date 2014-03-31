package addonDread.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import addonDread.RpgDreadAddon;

public class ItemPaladinArmor extends AbstractArmor {

	public ItemPaladinArmor(ArmorMaterial enumArmorMaterial, int par3, int par4) {
		super(par3, par4, enumArmorMaterial);
	}

	@Override
	public String armorClassName() {
		// TODO Auto-generated method stub
		return RpgDreadAddon.CLASSPALADIN;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if (stack != null)
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3))
					armorModel = RpgDreadAddon.proxy.getArmorModel(2);
				else
					armorModel = RpgDreadAddon.proxy.getArmorModel(3);
			}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.getItem() == RpgDreadAddon.palaHelm)
				|| (itemstack.getItem() == RpgDreadAddon.palaChest)
				|| (itemstack.getItem() == RpgDreadAddon.palaBoots))
			return "armor:pal_1.png";
		if (itemstack.getItem() == RpgDreadAddon.palaLeggings)
			return "armor:pal_2.png";
		return null;
	}

}
