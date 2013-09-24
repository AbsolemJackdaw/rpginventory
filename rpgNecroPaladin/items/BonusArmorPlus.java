package rpgNecroPaladin.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.utils.AbstractArmor;
import rpgNecroPaladin.mod_RpgPlus;
import rpgNecroPaladin.models.ModelNecroArmor;
import rpgNecroPaladin.models.ModelPaladinArmor;

public class BonusArmorPlus extends AbstractArmor {

	public BonusArmorPlus(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {

		if (itemstack.itemID == mod_RpgPlus.necroHood.itemID || itemstack.itemID == mod_RpgPlus.necroChestplate.itemID || itemstack.itemID == mod_RpgPlus.necroBoots.itemID) {
			return "armor:necro_1.png";
		}
		if (itemstack.itemID == mod_RpgPlus.necroLeggings.itemID) {
			return "armor:necro_2.png";
		}

		if (itemstack.itemID == mod_RpgPlus.palaHelm.itemID || itemstack.itemID == mod_RpgPlus.palaChest.itemID || itemstack.itemID == mod_RpgPlus.palaBoots.itemID) {
			return "armor:pal_1.png";
		}
		if (itemstack.itemID == mod_RpgPlus.palaLeggings.itemID) {
			return "armor:pal_2.png";
		}
		return null;
	}


	private static ModelPaladinArmor armorPaladinChest; 
	private static ModelPaladinArmor armorPaladin;

	private static final ModelNecroArmor armorNecroChest = new ModelNecroArmor(1.0f);
	private static final ModelNecroArmor armorNecro = new ModelNecroArmor(0.5f);

	boolean done = false;

	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if(!done){
			armorPaladinChest = new ModelPaladinArmor(1.0f, elb);
			armorPaladin = new ModelPaladinArmor(0.5f, elb);
			done = true;
		}
		if(stack != null)
			if(stack.getItem() instanceof ItemArmor){

				int type = ((ItemArmor)stack.getItem()).armorType;
				EnumArmorMaterial mat = this.mat;

				if(type == 1 || type == 3){
					if(mat.equals(mod_RpgPlus.necroArmor))
						armorModel = armorNecroChest;

					else if (mat.equals(mod_RpgPlus.paladin))
						armorModel = armorPaladinChest;
				}else{
					if(mat.equals(mod_RpgPlus.necroArmor))
						armorModel = armorNecro;
					else if (mat.equals(mod_RpgPlus.paladin))
						armorModel = armorPaladin;
				}
			}		
	}
}
