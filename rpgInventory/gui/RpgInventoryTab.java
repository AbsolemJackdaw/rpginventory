package rpgInventory.gui;

import net.minecraft.creativetab.CreativeTabs;
import rpgInventory.mod_RpgInventory;
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
             return mod_RpgInventory.ringem.itemID;
     }

     public String getTranslatedTabLabel()
     {
      return "RPG Armoury";
     }

}
