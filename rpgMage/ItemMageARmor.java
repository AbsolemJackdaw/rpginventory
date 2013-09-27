package rpgMage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;

public class ItemMageARmor extends AbstractArmor{

	public ItemMageARmor(int par1, EnumArmorMaterial enumArmorMaterial,
			int par3, int par4) {
		super(par1, enumArmorMaterial, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if (itemstack.itemID == mod_RpgMageSet.archmageHood.itemID || itemstack.itemID == mod_RpgMageSet.archmageChest.itemID || itemstack.itemID == mod_RpgMageSet.archMageBoots.itemID) {
			return "armor:archMage_1.png";
		}
		if (itemstack.itemID == mod_RpgMageSet.archmageLegs.itemID) {
			return "armor:archMage_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {

		int type = ((ItemArmor)stack.getItem()).armorType;
		if(type == 1 || type == 3){
			armorModel = mod_RpgMageSet.proxy.getArmorModel(0);

		}else{
			armorModel = mod_RpgMageSet.proxy.getArmorModel(1);
		}

	}

	@Override
	public String armorClassName() {
		// TODO Auto-generated method stub
		return mod_RpgMageSet.CLASSARCHMAGE;
	}

}
