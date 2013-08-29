package rpgInventory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemMold extends Item {

    public ItemMold(int par1) {
        super(par1);
        this.maxStackSize = 1;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon("RPGInventoryMod:" + itemName);
    }
}
