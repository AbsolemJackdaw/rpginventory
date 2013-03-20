/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgInventory.weapons;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class ItemRpgSword extends ItemSword {

    public ItemRpgSword(int par1, EnumToolMaterial etm) {
        super(par1, etm);
        this.maxStackSize = 1;
    }

    @Override
    public void func_94581_a(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
        this.iconIndex = par1IconRegister.func_94245_a("RPGInventoryMod:" + itemName);
    }
}
