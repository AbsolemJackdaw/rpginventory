package rpgInventory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpg extends Item {

	public ItemRpg() {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		if (is.getItem() == mod_RpgInventory.tanHide) {
			return 0xa24203;
		}
		if (is.getItem() == mod_RpgInventory.magecloth) {
			return 0x000080;
		}
		if (is.getItem() == mod_RpgInventory.animalskin) {
			return 0x71544f;
		}
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		if ((par1ItemStack.getItem() == mod_RpgInventory.magecloth)
				|| (par1ItemStack.getItem() == mod_RpgInventory.wizardBook)) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer player) {
		if (par1ItemStack.getItem() == mod_RpgInventory.wizardBook) {
			mod_RpgInventory.proxy.openGUI(player, 2);
		}
		return par1ItemStack;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(
				getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}
}
