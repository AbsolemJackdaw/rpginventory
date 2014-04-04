package rpgInventory.item.armor;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.models.MainShield;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRpgInvArmor extends Item {

	/**
	 * Holds the 'base' maxDamage that each armorType have.
	 */
	private final int[] maxDamageArray = new int[] { 30, 20, 50, 20, 30, 30 };

	public static final int NECKLACE = 0;
	public static final int SHIELD = 1;
	public static final int CLOAK = 2;
	public static final int GLOVES = 3;
	public static final int RING = 4;

	/**
	 * Stores the armor type: 0 is necklace, 2 is cloak, 1 is shield, 3 is
	 * gloves, 4 are rings. types can be found at RpgInventoryMod.ITEMTYPES.xxx
	 */
	public int armorType;

	private ResourceLocation TEXTURE;

	/**
	 * second string : name, isn't used.
	 * 
	 * ItemID ArmorType (aka shield,gloves, cloak, ... ) 
	 * the maximum damage this item can take. only used for shields
	 * name is unused
	 * location for texture
	 */
	public ItemRpgInvArmor(int par4, int maxDamage, String name,
			String resourcelocation) {
		super();
		this.armorType = par4;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxDamage(maxDamage);
		TEXTURE = new ResourceLocation(resourcelocation);
	}

	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 */
	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list,
			boolean yesno) {
		if (stack.getItem() == RpgInventoryMod.ringem) {
			list.add(StatCollector
					.translateToLocal("Left: Dispell Negative Effects"));
			list.add(StatCollector
					.translateToLocal("Right: Increased Mining Speed x4"));
		}

		if (stack.getItem() == RpgInventoryMod.neckem) {
			list.add(StatCollector.translateToLocal("Water Breathing"));
			list.add(StatCollector.translateToLocal("x2"));
		}

		if (stack.getItem() == RpgInventoryMod.glovesem) {
			list.add(StatCollector.translateToLocal("Resistance"));
			list.add(StatCollector
					.translateToLocal("Reduces damage taken by 20%"));
		}

		if ((stack.getItem() == RpgInventoryMod.ringdia)
				|| (stack.getItem() == RpgInventoryMod.glovesdia)
				|| (stack.getItem() == RpgInventoryMod.neckdia)) {
			list.add(StatCollector.translateToLocal("Healing"));
			list.add(StatCollector.translateToLocal("+15% Heal Speed"));
		}

		if ((stack.getItem() == RpgInventoryMod.ringgold)
				|| (stack.getItem() == RpgInventoryMod.glovesbutter)
				|| (stack.getItem() == RpgInventoryMod.neckgold))
			list.add(StatCollector.translateToLocal("Speed + 12.5%"));

		if ((stack.getItem() == RpgInventoryMod.ringlap)
				|| (stack.getItem() == RpgInventoryMod.gloveslap)
				|| (stack.getItem() == RpgInventoryMod.necklap)) {
			list.add(StatCollector.translateToLocal("Strength"));
			if (stack.getItem() == RpgInventoryMod.ringlap)
				list.add(StatCollector.translateToLocal("+0.5"));
			if (stack.getItem() == RpgInventoryMod.gloveslap)
				list.add(StatCollector.translateToLocal("+1"));
			if (stack.getItem() == RpgInventoryMod.necklap)
				list.add(StatCollector.translateToLocal("+1.5"));
			list.add(StatCollector
					.translateToLocal("-1 Dmg on Held Weapon every 20s"));
		}
	}

	/**
	 * 'Bounds' the shield to an armor class. this should be overridden in child
	 * mods ! If the string is left/set to "none", it will not check for class
	 * armor and can be used by anyone (like Vanilla Shields)
	 */
	public String boundArmorClass() {

		return "none";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakRed)
			return 0xd2120e;
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakGreen)
			return 0x0fb15d;
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakYellow)
			return 0xf7cd09;
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakSub)
			return 0x440001;
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakBlue)
			return 0x291ef6;

		return 0xffffff;
	}

	/**
	 * Returns the 'max damage' factor array for the armor, each piece of armor
	 * have a durability factor (that gets multiplied by armor material factor)
	 */
	int[] getMaxDamageArray() {
		return maxDamageArray;
	}

	public MainShield getShieldModel() {

		return new MainShield();
	}

	/**
	 * Gets the String initially set in the item constructor and turns it into a
	 * use-able resource-location
	 */
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakI)
			return true;
		return false;
	}

	public boolean isMantle() {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		PlayerRpgInventory inv = PlayerRpgInventory.get(par3EntityPlayer);

		for (int i = 0; i < inv.getSizeInventory(); i++)
			if (inv.getStackInSlot(i) == null)
				if (inv.isItemValidForSlot(i, par1ItemStack)) {
					inv.setInventorySlotContents(i, par1ItemStack);
					par3EntityPlayer.destroyCurrentEquippedItem();
					break;
					// break, or right-clicking a ring will set both ring slots.
				}

		return super.onItemRightClick(par1ItemStack, par2World,
				par3EntityPlayer);
	}

	/**
	 * Used to upgrade the player's class to 'playerClass' + 'shieldedClass'
	 * Only works if the shield finds the matching class with boundArmorClass()
	 */
	public String shieldClass() {

		return "none";
	}

	public String getMantleTexture() {
		return "";
	}
}
