package rpgRogueBeast.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import rpgRogueBeast.mod_RpgRB;
import rpgRogueBeast.models.ModelBeastArmor;

public class ItemBeastMasterArmor extends AbstractArmor{


	public ItemBeastMasterArmor(int par1,
			EnumArmorMaterial enumArmorMaterial, int par3, int par4) {
		super(par1, enumArmorMaterial, par3, par4);
	}


	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if (itemstack.itemID == mod_RpgRB.beastBoots.itemID || itemstack.itemID == mod_RpgRB.beastChest.itemID || itemstack.itemID == mod_RpgRB.beastHood.itemID) {
			return "armor:beast_1.png";
		}
		if (itemstack.itemID == mod_RpgRB.beastLegs.itemID) {
			return "armor:beast_2.png";
		}
		return super.getArmorTexture(itemstack, entity, slot, type);
	}

	private static final ModelBeastArmor armorBeastChest = new ModelBeastArmor(1.0f);
	private static final ModelBeastArmor armorBeast = new ModelBeastArmor(0.5f);

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if(stack != null){
			if(stack.getItem() instanceof ItemArmor){

				int type = ((ItemArmor)stack.getItem()).armorType;

				if(type == 1 || type == 3){

						armorModel = armorBeastChest;
				}else{
						armorModel = armorBeast;
				}
			}
		}
	}


	@Override
	public String armorClassName() {
		// TODO Auto-generated method stub
		return mod_RpgRB.CLASSBEASTMASTER;
	}
}
