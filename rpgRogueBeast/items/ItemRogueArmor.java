package rpgRogueBeast.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.models.armor.ModelRogueArmor;
import rpgInventory.utils.AbstractArmor;
import rpgRogueBeast.mod_RpgRB;

public class ItemRogueArmor extends AbstractArmor{

	public ItemRogueArmor(int par1, EnumArmorMaterial enumArmorMaterial,
			int par3, int par4) {
		super(par1, enumArmorMaterial, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if (itemstack.itemID == mod_RpgRB.rogueHood.itemID || itemstack.itemID == mod_RpgRB.rogueChest.itemID || itemstack.itemID == mod_RpgRB.rogueBoots.itemID) {
			return "armor:rogue_1.png";
		}
		if (itemstack.itemID == mod_RpgRB.rogueLegs.itemID) {
			return "armor:rogue_2.png";
		}		
		return super.getArmorTexture(itemstack, entity, slot, type);
	}
	
	@Override
	public String armorClassName() {
		return mod_RpgRB.CLASSROGUE;
	}


	private static final ModelRogueArmor armorRogueChest = new ModelRogueArmor(1.0f);
	private static final ModelRogueArmor armorRogue = new ModelRogueArmor(0.5f);
	
	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		
		if(stack != null){
			if(stack.getItem() instanceof ItemArmor){

				int type = ((ItemArmor)stack.getItem()).armorType;

				if(type == 1 || type == 3){
						armorModel = armorRogueChest;
				}else{
						armorModel = armorRogue;
				}
			}
		}
		
	}

}
