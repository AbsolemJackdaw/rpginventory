package rpgInventory.item.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.models.armor.ModelBerserkerArmor;
import rpgInventory.models.armor.ModelMageArmor;
import rpgInventory.utils.AbstractArmor;
import cpw.mods.fml.common.FMLLog;

public class ItemClassArmor extends AbstractArmor{

	public final int renderIndex;
	public ItemClassArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		this.renderIndex = par3;

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:" + itemName);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot,
			String type) {
		if (itemstack.itemID == mod_RpgInventory.magehood.itemID || itemstack.itemID == mod_RpgInventory.magegown.itemID || itemstack.itemID == mod_RpgInventory.mageboots.itemID) {
			return "armor:mage_1.png";
		}
		if (itemstack.itemID == mod_RpgInventory.magepants.itemID) {
			return "armor:mage_2.png";
		}

		if (itemstack.itemID == mod_RpgInventory.archerboots.itemID || itemstack.itemID == mod_RpgInventory.archerchest.itemID || itemstack.itemID == mod_RpgInventory.archerhood.itemID) {
			return "armor:arch_1.png";
		}
		if (itemstack.itemID == mod_RpgInventory.archerpants.itemID) {
			return "armor:arch_2.png";
		}
		if (itemstack.itemID == mod_RpgInventory.berserkerHood.itemID || itemstack.itemID == mod_RpgInventory.berserkerChest.itemID || itemstack.itemID == mod_RpgInventory.berserkerBoots.itemID) {
			return "armor:berserk_1.png";
		}
		if (itemstack.itemID == mod_RpgInventory.berserkerLegs.itemID) {
			return "armor:berserk_2.png";
		}
		//Rpg Rogue Beastmaster
		if (mod_RpgInventory.hasRogue) {

		}
		if (mod_RpgInventory.hasMage) {
			if (itemstack.itemID == mod_RpgInventory.archmageHood.itemID || itemstack.itemID == mod_RpgInventory.archmageChest.itemID || itemstack.itemID == mod_RpgInventory.archMageBoots.itemID) {
				return "armor:archMage_1.png";
			}
			if (itemstack.itemID == mod_RpgInventory.archmageLegs.itemID) {
				return "armor:archMage_2.png";
			}
		}
		return null;
	}


	
	private static final ModelBerserkerArmor armorBerserkChest = new ModelBerserkerArmor(1.0f);
	private static final ModelBerserkerArmor armorBerserk = new ModelBerserkerArmor(0.5f);
	
	private static final ModelMageArmor armorMageChest = new ModelMageArmor(1.0f);
	private static final ModelMageArmor armorMage = new ModelMageArmor(0.5f);


	@Override
	protected void get3DArmorModel(EntityLivingBase elb, ItemStack stack,
			int armorSlot) {
		if(stack != null)
			if(stack.getItem() instanceof ItemArmor){

				int type = ((ItemArmor)stack.getItem()).armorType;
				EnumArmorMaterial mat = this.mat;

				if(type == 1 || type == 3){

					if(mat.equals(mod_RpgInventory.instance.berserker))
						armorModel = armorBerserkChest;

					else if(mat.equals(mod_RpgInventory.instance.mage) ||mat.equals(mod_RpgInventory.instance.archMage))
						armorModel = armorMageChest;


					/*Archers dont have custom armor*/
					else if(mat.equals(mod_RpgInventory.instance.archer))
						armorModel = null;// null will enable default armor rendering

					else{
						armorModel = null; // null will enable default armor rendering
						FMLLog.info("[WARNING] No Custom Model Set for " + stack.getDisplayName() + ". Rendering might be terribly messed up.");
					}
				}else{
					if(mat.equals(mod_RpgInventory.instance.berserker))
						armorModel = armorBerserk;

					else if(mat.equals(mod_RpgInventory.instance.mage) || 
							mat.equals(mod_RpgInventory.instance.archMage))
						armorModel = armorMage;

					/*Archers dont have custom armor*/
					else if(mat.equals(mod_RpgInventory.instance.archer))
						armorModel = null;// null will enable default armor rendering

					else{
						armorModel = null;// null will enable default armor rendering
						FMLLog.info("[WARNING] No Custom Model Set for " + stack.getDisplayName() + ". Rendering might be terribly messed up.");
					}
				}
			}		
	}
}
