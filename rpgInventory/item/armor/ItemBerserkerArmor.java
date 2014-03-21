package rpgInventory.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.utils.AbstractArmor;

public class ItemBerserkerArmor extends AbstractArmor {

	public final int renderIndex;

	public ItemBerserkerArmor(int par1,
			EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		this.renderIndex = par3;

	}

	@Override
	public String armorClassName() {
		return mod_RpgInventory.CLASSBERSERKER;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if (stack != null) {
			if (stack.getItem() instanceof AbstractArmor) {

				int type = ((AbstractArmor) stack.getItem()).armorType;

				if ((type == 1) || (type == 3)) {
					armorModel = mod_RpgInventory.proxy.getArmorModel(3);

				} else {
					armorModel = mod_RpgInventory.proxy.getArmorModel(2);
				}
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if ((itemstack.itemID == mod_RpgInventory.berserkerHood.itemID)
				|| (itemstack.itemID == mod_RpgInventory.berserkerChest.itemID)
				|| (itemstack.itemID == mod_RpgInventory.berserkerBoots.itemID)) {
			return "armor:berserk_1.png";
		}
		if (itemstack.itemID == mod_RpgInventory.berserkerLegs.itemID) {
			return "armor:berserk_2.png";
		}
		return null;
	}
}
