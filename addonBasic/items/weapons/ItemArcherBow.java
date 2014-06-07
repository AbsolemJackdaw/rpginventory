package addonBasic.items.weapons;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonBasic.RpgBaseAddon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcherBow extends Item {

	public static final String[] ItemNameArray = new String[] { "elmBow",
		"elmBow2", "elmBow3", "elmBow4" };

	public int usingItem = 0;
	@SideOnly(Side.CLIENT)
	private IIcon[] IconArray;

	public ItemArcherBow() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(1000);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player,
			ItemStack usingItem, int useRemaining) {
		if (stack == usingItem) {
			if ((usingItem != null) && (usingItem.getItem() == RpgBaseAddon.elfbow)) {
				if (useRemaining > 21) {
					return IconArray[3];
				} else if (useRemaining > 14) {
					return IconArray[2];
				} else if (useRemaining > 7) {
					return IconArray[1];
				}
			}
		}
		return IconArray[0];
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
		return this.IconArray[0];
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	@Override
	public int getItemEnchantability() {
		return -1;
	}

	/**
	 * used to cycle through icons based on their used duration, i.e. for the
	 * bow
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getItemIconForUseDuration(int par1) {
		return this.IconArray[par1];
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,EntityPlayer player) {

		player.setItemInUse(par1ItemStack,this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	/**
	 * called when the player releases the use item button. Args: itemstack,
	 * world, entityplayer, itemInUseCount
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World par2World,EntityPlayer player, int par4) {
		super.onPlayerStoppedUsing(stack, par2World, player, par4);
		usingItem = 0;
		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
		ItemStack shield = inv.getJewelInSlot(1);

		int j = this.getMaxItemUseDuration(stack) - par4;

		if(RpgInventoryMod.playerClass.equals(RpgBaseAddon.CLASSARCHER)) {
			boolean flag = ((shield != null) && (shield.getItem() == RpgBaseAddon.archerShield))
					|| player.capabilities.isCreativeMode;
			if (player.inventory.hasItem(Items.arrow) || flag) {

				float f = j / 20.0F;
				f = ((f * f) + (f * 2.0F)) / 3.0F;

				if (f < 0.5d) {
					return;
				}

				EntityArrow entityarrow = new EntityArrow(par2World,player, f * 2.0F);
				boolean crit = RpgInventoryMod.donators.contains(player.getCommandSenderName()) ? true : false;
				entityarrow.setIsCritical(crit);

				if (f >= 1.0F && f <1.5F) {
					entityarrow.setIsCritical(true);
				}
				
				if (f > 1.0F) {
					f = 1.0F;
				}

				entityarrow.setDamage(entityarrow.getDamage()+ (flag ? 2D : 1D));
				entityarrow.setKnockbackStrength(RpgInventoryMod.donators.contains(player.getCommandSenderName()) ? 2 : 1);
				entityarrow.setFire(RpgInventoryMod.donators.contains(player.getCommandSenderName()) ? 10 : 5);

				if (flag) {
					entityarrow.canBePickedUp = 2;
				} else {
					player.inventory.consumeInventoryItem(Items.arrow);
				}
				if (!par2World.isRemote) {
					par2World.spawnEntityInWorld(entityarrow);
				}
				stack.damageItem(1, player);
				par2World.playSoundAtEntity(player, "random.bow", 1.0F,(1.0F / ((itemRand.nextFloat() * 0.4F) + 1.2F))+ (f * 0.5F));
			}
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		player.setItemInUse(stack, count);
		usingItem++;
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		this.IconArray = new IIcon[ItemNameArray.length];

		for (int i = 0; i < this.IconArray.length; ++i) {
			String prefix = "rpginventorymod:";
			this.IconArray[i] = par1IconRegister.registerIcon(prefix+ ItemNameArray[i]);
		}
		this.itemIcon = this.IconArray[0];
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return false;
	}
}
