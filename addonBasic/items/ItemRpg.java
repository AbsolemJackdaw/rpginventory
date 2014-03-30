package addonBasic.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import addonBasic.mod_addonBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpg extends Item {

	public ItemRpg() {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		if (is.getItem() == mod_addonBase.tanHide)
			return 0xa24203;
		if (is.getItem() == mod_addonBase.magecloth)
			return 0x000080;
		if (is.getItem() == mod_addonBase.animalskin)
			return 0x71544f;
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		if ((par1ItemStack.getItem() == mod_addonBase.magecloth)
				|| (par1ItemStack.getItem() == mod_addonBase.wizardBook))
			return true;
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer player) {
		if (par1ItemStack.getItem() == mod_addonBase.wizardBook)
			mod_RpgInventory.proxy.openGUI(player, 2);
		return par1ItemStack;
	}

}
