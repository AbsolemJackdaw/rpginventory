package rpgNecroPaladin.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import rpgNecroPaladin.mod_RpgPlus;

public class ItemPaladinArmor extends AbstractArmor {

	public ItemPaladinArmor(int par1, EnumArmorMaterial enumArmorMaterial,
			int par3, int par4) {
		super(par1, enumArmorMaterial, par3, par4);
	}

	@Override
	public String armorClassName() {
		// TODO Auto-generated method stub
		return mod_RpgPlus.CLASSPALADIN;
	}
	
	
	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if(stack != null)
			if(stack.getItem() instanceof ItemArmor){

				int type = ((ItemArmor)stack.getItem()).armorType;

				if(type == 1 || type == 3){
						armorModel = mod_RpgPlus.proxy.getArmorModel(2);
				}else{
						armorModel = mod_RpgPlus.proxy.getArmorModel(3);
				}
			}	
	}

}
