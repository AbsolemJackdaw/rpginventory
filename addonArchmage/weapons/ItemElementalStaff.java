package addonArchmage.weapons;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.item.ItemRpgSword;
import addonArchmage.EntityElementalBlock;
import addonArchmage.RpgArchmageAddon;

public class ItemElementalStaff extends ItemRpgSword {

	protected boolean hasAttacked = false;
	public int type = 1;
	public int maxUse = 150;

	public ItemElementalStaff(int type, int maxUse) {
		super(ToolMaterial.WOOD);
		this.type = type;
		this.maxUse = maxUse;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list,
			boolean yesno) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			stack.setTagCompound(baseNBT());
		}
		nbt = stack.getTagCompound();
		if (nbt.getBoolean("ReCharging")) {
			list.add("Recharging");
		}
		list.add("Overflow: " + Math.floor(nbt.getFloat("EnergyCharge")) + "/"
				+ this.maxUse);
	}

	public NBTTagCompound baseNBT() {
		NBTTagCompound nbt = new NBTTagCompound(); // stack.getTagCompound();
		nbt.setFloat("EnergyCharge", 0.0F);
		nbt.setBoolean("ReCharging", false);
		return nbt;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	public int getStrenght(EntityPlayer p) {
		return p.experienceLevel == 0 ? 1 : (int) p.experienceLevel;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
		// par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
			Block b, int par4, int par5, int par6,
			EntityLivingBase par7EntityLiving) {

		if (b.getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			// par1ItemStack.damageItem(2, par7EntityLiving);
		}

		return true;
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer p) {
		stack.setTagCompound(baseNBT());
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World par2World,
			EntityPlayer p) {
		if (RpgInventoryMod.playerClass.contains(RpgArchmageAddon.CLASSARCHMAGE)
				|| RpgInventoryMod.developers.contains(p.getDisplayName()
						.toLowerCase())) {
			if (p.isUsingItem()) {
				p.stopUsingItem();
			} else {
				p.setItemInUse(is, this.getMaxItemUseDuration(is));
			}
		}
		return is;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityPlayer p, int count) {
	}

	@Override
	public void onUpdate(ItemStack stack, World w, Entity e, int par4,
			boolean par5) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			stack.setTagCompound(baseNBT());
		}
		nbt = stack.getTagCompound();
		if (nbt.getBoolean("ReCharging")) {
			nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge") - 0.05F);
		} else {
			nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge") - 0.025F);
		}

		if (nbt.getFloat("EnergyCharge") <= 0) {
			nbt.setBoolean("ReCharging", false);
			nbt.setFloat("EnergyCharge", 0.0F);
		}
		stack.setTagCompound(nbt);
	}

	/**
	 * Called each tick while using an item.
	 *
	 * @param stack
	 *            The Item being used
	 * @param player
	 *            The Player using the item
	 * @param count
	 *            The amount of time in tick the item has been used for
	 *            continuously
	 */

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer p, int count) {
		int time = this.getMaxItemUseDuration(stack) - count;
		NBTTagCompound nbt = stack.getTagCompound();
		if (!p.worldObj.isRemote) {
			if ((nbt.getFloat("EnergyCharge") < this.maxUse)
					&& !nbt.getBoolean("ReCharging")) {
				float var7 = time / 20.0F;
				var7 = ((var7 * var7) + (var7 * 2.0F)) / 3.0F;
				int limit = ((p.getDisplayName().toLowerCase()
						.matches("unjustice")) ? 35 : ((this.type == 5) ? 10
								: 7));
				EntityElementalBlock var9 = new EntityElementalBlock(
						p.worldObj, p, var7 * 2, this.type, limit);
				p.worldObj.spawnEntityInWorld(var9);
				if (!RpgInventoryMod.developers.contains(p.getDisplayName()
						.toLowerCase())) {
					nbt.setFloat("EnergyCharge",
							nbt.getFloat("EnergyCharge") + 1.0F);
				}
			} else {
				if (!nbt.getBoolean("ReCharging")) {
					stack.damageItem(5, p);
				}
				nbt.setBoolean("ReCharging", true);
				p.stopUsingItem();
			}
		}
		stack.setTagCompound(nbt);
	}
}
