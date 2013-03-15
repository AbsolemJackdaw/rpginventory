package RpgInventory;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemMold extends Item {

    public ItemMold(int par1) {
        super(par1);
        this.maxStackSize = 1;
    }

    @Override
    public void func_94581_a(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
        this.iconIndex = par1IconRegister.func_94245_a("RPGInventoryMod:" + itemName);
    }
}
