package RpgInventory;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemCandy extends Item {

    public ItemCandy(int par1) {
        super(par1);
        this.maxStackSize = 6;
    }

    @Override
    public void updateIcons(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
        this.iconIndex = par1IconRegister.registerIcon("RPGInventoryMod:" + itemName);
    }
}
