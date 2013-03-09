package RpgMageSet.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemFireStaff extends ItemSword {

	protected boolean hasAttacked = false;

	public ItemFireStaff(int par1) {
		super(par1, EnumToolMaterial.STONE);
	}

	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingItemTick(ItemStack stack, EntityPlayer p, int count)
	{
		int time = this.getMaxItemUseDuration(stack) - count;
		if (!p.worldObj.isRemote) {
            float var7 = (float)time / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
		EntityElementalBlock var9 = new EntityElementalBlock(p.worldObj, p, var7 * 2, 1);
		p.worldObj.spawnEntityInWorld(var9);
		}
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer p, int count) {
		int time = this.getMaxItemUseDuration(stack) - count;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 5000;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer p) {
		p.setItemInUse(is, this.getMaxItemUseDuration(is));
		return is;
	}

	public int getStrenght(EntityPlayer p) {
		return p.experienceLevel == 0 ? 1 : (int) p.experienceLevel;
	}

	public String getTextureFile() {
		return "/subaraki/RPGinventoryTM.png";
	}
}
