package addonMasters.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import addonMasters.RpgMastersAddon;

public class ItemRogueArmor extends AbstractArmor {

	public ItemRogueArmor(ArmorMaterial enumArmorMaterial, int par3, int par4) {

		super(par3, par4, enumArmorMaterial);

	}

	@Override
	public String armorClassName() {
		return RpgMastersAddon.CLASSROGUE;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {

		if (stack != null)
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3))
					armorModel = RpgMastersAddon.proxy.getArmorModel(0);
				else
					armorModel = RpgMastersAddon.proxy.getArmorModel(1);
			}

	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if ((itemstack.getItem() == RpgMastersAddon.rogueHood)
				|| (itemstack.getItem() == RpgMastersAddon.rogueChest)
				|| (itemstack.getItem() == RpgMastersAddon.rogueBoots))
			return "armor:rogue_1.png";
		if (itemstack.getItem() == RpgMastersAddon.rogueLegs)
			return "armor:rogue_2.png";
		return super.getArmorTexture(itemstack, entity, slot, type);
	}

}
