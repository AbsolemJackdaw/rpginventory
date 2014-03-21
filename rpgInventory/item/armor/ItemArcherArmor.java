package rpgInventory.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.utils.AbstractArmor;

public class ItemArcherArmor extends AbstractArmor {

	public ItemArcherArmor(int par1, EnumArmorMaterial enumArmorMaterial,
			int par3, int par4) {
		super(par1, enumArmorMaterial, par3, par4);
	}

	@Override
	public String armorClassName() {
		return mod_RpgInventory.CLASSARCHER;
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
		if ((itemstack.itemID == mod_RpgInventory.archerboots.itemID)
				|| (itemstack.itemID == mod_RpgInventory.archerchest.itemID)
				|| (itemstack.itemID == mod_RpgInventory.archerhood.itemID)) {
			return "armor:arch_1.png";
		}
		if (itemstack.itemID == mod_RpgInventory.archerpants.itemID) {
			return "armor:arch_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}
}
