package addonBasic.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import addonBasic.RpgBaseAddon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpg extends Item {

	public ItemRpg() {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		if (is.getItem() == RpgBaseAddon.tanHide) {
			return 0xa24203;
		}
		if (is.getItem() == RpgBaseAddon.alchemistcloth) {
			return 0x000080;
		}
		if (is.getItem() == RpgBaseAddon.animalskin) {
			return 0x71544f;
		}
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		if ((par1ItemStack.getItem() == RpgBaseAddon.alchemistcloth)) {
			return true;
		}
		return false;
	}
}
