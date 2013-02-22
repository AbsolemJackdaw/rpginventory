package RpgInventory.gui;

import net.minecraft.creativetab.CreativeTabs;
import RpgInventory.AARpgBaseClass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RpgInventoryTab extends CreativeTabs {
	
	 public RpgInventoryTab(int par1, String par2Str)
     {
             super(par1, par2Str);
     }
     @SideOnly(Side.CLIENT)
     public int getTabIconItemIndex()
     {
             return AARpgBaseClass.ringem.itemID;
     }

     public String getTranslatedTabLabel()
     {
      return "RPG Armoury";
     }

}
