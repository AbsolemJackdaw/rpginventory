/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgInventory.weapons;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class ItemRpgWeapon extends Item{
    public ItemRpgWeapon(int par1) {
        super(par1);
        this.maxStackSize = 1;
    }

    @Override
    public void updateIcons(IconRegister par1IconRegister) {
        String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
        this.iconIndex = par1IconRegister.registerIcon(texture);
    }
}
