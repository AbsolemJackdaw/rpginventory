package rpgAddonMasters.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgAddonMasters.mod_RpgRB;
import rpgInventory.item.ItemMats;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRBMats extends ItemMats {

	public ItemRBMats() {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		if (is.getItem() == mod_RpgRB.beastLeather) {
			return 0x0b910d;
		}
		if (is.getItem() == mod_RpgRB.rogueLeather) {
			return 0xae1fe1;
		}
		return 16777215;
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:" + itemName;
		return this;
	}
}
