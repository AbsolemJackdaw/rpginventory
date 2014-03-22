package rpgRogueBeast.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import rpgRogueBeast.mod_RpgRB;

public class ItemRogueArmor extends AbstractArmor {

	public ItemRogueArmor(ArmorMaterial enumArmorMaterial,
			int par3, int par4) {
	
		super(par3, par4, enumArmorMaterial);
	
	}

	@Override
	public String armorClassName() {
		return mod_RpgRB.CLASSROGUE;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {

		if (stack != null) {
			if (stack.getItem() instanceof ItemArmor) {

				int type = ((ItemArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3)) {
					armorModel = mod_RpgRB.proxy.getArmorModel(0);
				} else {
					armorModel = mod_RpgRB.proxy.getArmorModel(1);
				}
			}
		}

	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if ((itemstack.getItem() == mod_RpgRB.rogueHood)
				|| (itemstack.getItem() == mod_RpgRB.rogueChest)
				|| (itemstack.getItem() == mod_RpgRB.rogueBoots)) {
			return "armor:rogue_1.png";
		}
		if (itemstack.getItem() == mod_RpgRB.rogueLegs) {
			return "armor:rogue_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}

}
