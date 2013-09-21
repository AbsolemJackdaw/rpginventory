package rpgInventory.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.models.armor.ModelBeastArmor;
import rpgInventory.models.armor.ModelBerserkerArmor;
import rpgInventory.models.armor.ModelMageArmor;
import rpgInventory.models.armor.ModelNecroArmor;
import rpgInventory.models.armor.ModelRogueArmor;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BonusArmor extends ItemArmor{

	public final int renderIndex;
	private EnumArmorMaterial mat;
	public BonusArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		mat = par2EnumArmorMaterial;
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
		// RPG++
		if (mod_RpgInventory.hasRpg) {
			if (itemstack.itemID == mod_RpgInventory.necroHood.itemID || itemstack.itemID == mod_RpgInventory.necroChestplate.itemID || itemstack.itemID == mod_RpgInventory.necroBoots.itemID) {
				return "armor:necro_1.png";
			}
			if (itemstack.itemID == mod_RpgInventory.necroLeggings.itemID) {
				return "armor:necro_2.png";
			}

			if (itemstack.itemID == mod_RpgInventory.palaHelm.itemID || itemstack.itemID == mod_RpgInventory.palaChest.itemID || itemstack.itemID == mod_RpgInventory.palaBoots.itemID) {
				return "armor:pal_1.png";
			}
			if (itemstack.itemID == mod_RpgInventory.palaLeggings.itemID) {
				return "armor:pal_2.png";
			}
		}
		//Rpg Rogue Beastmaster
		if (mod_RpgInventory.hasRogue) {
			if (itemstack.itemID == mod_RpgInventory.beastBoots.itemID || itemstack.itemID == mod_RpgInventory.beastChest.itemID || itemstack.itemID == mod_RpgInventory.beastHood.itemID) {
				return "armor:beast_1.png";
			}
			if (itemstack.itemID == mod_RpgInventory.beastLegs.itemID) {
				return "armor:beast_2.png";
			}

			if (itemstack.itemID == mod_RpgInventory.rogueHood.itemID || itemstack.itemID == mod_RpgInventory.rogueChest.itemID || itemstack.itemID == mod_RpgInventory.rogueBoots.itemID) {
				return "armor:rogue_1.png";
			}
			if (itemstack.itemID == mod_RpgInventory.rogueLegs.itemID) {
				return "armor:rogue_2.png";
			}
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

	@Override @SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int par2 /*armor slot*/)
	{

		/* TODO
		 * this works. you basicly reset the armor model.
		 * walking around works
		 * crouching, riding etc doesnt. easy remedy for that : bip.crouch = player.crouch
		 * same goes for holding items, and other actions
		 * */ 
		
		ModelBiped var7 = null; 
		// no float means it has the exact same size as the player. good for testing
		//if any model ever looks like that, you know you need to rework it. 

		if(itemStack != null)
			if(itemStack.getItem() instanceof BonusArmor){

				int type = ((BonusArmor)itemStack.getItem()).armorType;
				EnumArmorMaterial mat = ((BonusArmor)itemStack.getItem()).mat;
				
				if(type == 1 || type == 3){

					if(mat.equals(mod_RpgInventory.instance.beastMaster))
						var7 = new ModelBeastArmor(1.0f);

					else if(mat.equals(mod_RpgInventory.instance.berserker))
						var7 = new ModelBerserkerArmor(1.0f);

					else if(mat.equals(mod_RpgInventory.instance.mage) ||mat.equals(mod_RpgInventory.instance.archMage))
							var7 = new ModelMageArmor(1.0f);
					
					else if(mat.equals(mod_RpgInventory.instance.necroArmor))
						var7 = new ModelNecroArmor(1.0f);
					
					else if(mat.equals(mod_RpgInventory.instance.rogueArmor))
						var7 = new ModelRogueArmor(1.0f);
					
					/*Archers dont have custom armor*/
					else if(mat.equals(mod_RpgInventory.instance.archer)|| mat.equals(mod_RpgInventory.instance.paladin))
						var7 = null;// null will enable default armor rendering

					else{
						var7 = null; // null will enable default armor rendering
						FMLLog.info("[WARNING] No Custom Model Set for " + itemStack.getDisplayName() + ". Rendering might be terribly messed up.");
					}
				}else{
					if(mat.equals(mod_RpgInventory.instance.beastMaster))
						var7 = new ModelBeastArmor(0.5f);

					else if(mat.equals(mod_RpgInventory.instance.berserker))
						var7 = new ModelBerserkerArmor(0.5f);

					else if(mat.equals(mod_RpgInventory.instance.mage) || 
							mat.equals(mod_RpgInventory.instance.archMage))
							var7 = new ModelMageArmor(0.5f);
					
					else if(mat.equals(mod_RpgInventory.instance.necroArmor))
						var7 = new ModelNecroArmor(0.5f);
					
					else if(mat.equals(mod_RpgInventory.instance.rogueArmor))
						var7 = new ModelRogueArmor(0.5f);
					
					/*Archers dont have custom armor*/
					else if(mat.equals(mod_RpgInventory.instance.archer) || mat.equals(mod_RpgInventory.instance.paladin))
						var7 = null;// null will enable default armor rendering
					
					else{
						var7 = null;// null will enable default armor rendering
						FMLLog.info("[WARNING] No Custom Model Set for " + itemStack.getDisplayName() + ". Rendering might be terribly messed up.");
					}
				}
			}
		if(var7 != null){
			var7.bipedHead.showModel = par2 == 0;
			var7.bipedHeadwear.showModel = par2 == 0;
			var7.bipedBody.showModel = par2 == 1 || par2 == 2;
			var7.bipedRightArm.showModel = par2 == 1;
			var7.bipedLeftArm.showModel = par2 == 1;
			var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
			var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;

			var7.isSneak = entityLiving.isSneaking();
			var7.isRiding = entityLiving.isRiding();
			var7.isChild = entityLiving.isChild();
			var7.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;
			if(entityLiving instanceof EntityPlayer){
				var7.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
			}
		}


		return var7;
	}
}
