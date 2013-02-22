package RpgInventory.item.armor;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import RpgInventory.AARpgBaseClass;

public class BonusArmor extends ItemArmor implements IArmorTextureProvider
{

	public BonusArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}
	
	
	 public String getTextureFile()
	    {
	            return "/subaraki/RPGinventoryTM.png";
	    }
	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		if(itemstack.itemID == AARpgBaseClass.magehood.itemID || itemstack.itemID == AARpgBaseClass.magegown.itemID || itemstack.itemID == AARpgBaseClass.mageboots.itemID)
        {
                return "/armor/mage_1.png";
        }
        if(itemstack.itemID == AARpgBaseClass.magepants.itemID )
        {
                return "/armor/mage_2.png";
        }
        
        if( itemstack.itemID == AARpgBaseClass.archerboots.itemID || itemstack.itemID == AARpgBaseClass.archerchest.itemID || itemstack.itemID == AARpgBaseClass.archerhood.itemID)
        {
                return "/armor/arch_1.png";
        }
        if( itemstack.itemID == AARpgBaseClass.archerpants.itemID)
        {
                return "/armor/arch_2.png";
        }
        if( itemstack.itemID == AARpgBaseClass.ramboband.itemID || itemstack.itemID == AARpgBaseClass.rambobody.itemID || itemstack.itemID == AARpgBaseClass.rambofeet.itemID)
        {
                return "/armor/berserk_1.png";
        }
        if( itemstack.itemID == AARpgBaseClass.rambolegs.itemID)
        {
                return "/armor/berserk_2.png";
        }
        return "/armor/ac_1.png";
	}	   
}
