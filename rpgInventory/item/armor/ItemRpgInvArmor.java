package rpgInventory.item.armor;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
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

	private final ResourceLocation TEXTURE;

	private final int colorState;
	/**
	 * second string : name, isn't used.
	 * @param color is used for cape colors
	 * @param armorType (shields, gloves,...)
	 * @param maxDamage only used in shields
	 * @param resourcelocation for texture. used in rendering. path string
	 */
	public ItemRpgInvArmor(int armorType, int maxDamage, int color,
			String resourcelocation) {
		super();
		colorState = color;
		this.armorType = armorType;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxDamage(maxDamage);
		TEXTURE = new ResourceLocation(resourcelocation);
	}

	/**armor type only. can be used for rings*/
	public ItemRpgInvArmor(int armorType){
		this(armorType, 0 , -1, null);
	}

	public ItemRpgInvArmor(int armortype, int maxdamage, String resourcelocation){
		this(armortype, maxdamage, -1, resourcelocation);
	}

	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 */
	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list,
			boolean yesno) {

		switch (((ItemRpgInvArmor)stack.getItem()).armorType) {
		case SHIELD:
			list.add(StatCollector.translateToLocal(""));
			list.add(StatCollector.translateToLocal("Max damage : " + getMaxDamage()));
		default:
			break;
		}

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
				|| (stack.getItem() == RpgInventoryMod.neckgold)) {
			list.add(StatCollector.translateToLocal("Speed + 12.5%"));
		}

		if ((stack.getItem() == RpgInventoryMod.ringlap)
				|| (stack.getItem() == RpgInventoryMod.gloveslap)
				|| (stack.getItem() == RpgInventoryMod.necklap)) {
			list.add(StatCollector.translateToLocal("Strength"));
			if (stack.getItem() == RpgInventoryMod.ringlap) {
				list.add(StatCollector.translateToLocal("+0.5"));
			}
			if (stack.getItem() == RpgInventoryMod.gloveslap) {
				list.add(StatCollector.translateToLocal("+1"));
			}
			if (stack.getItem() == RpgInventoryMod.necklap) {
				list.add(StatCollector.translateToLocal("+1.5"));
			}
			list.add(StatCollector
					.translateToLocal("-1 Dmg on Held Weapon every 20s"));
		}
	}

	/**
	 * 'Bounds' the shield to an armor class. this should be overridden in child
	 * mods ! If the string is left/set to "none", it will not check for class
	 * armor and can be used by anyone (like Vanilla Shields)
	 */
	public String bindShieldToArmorClass() {

		return "none";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {

		if((colorState < 16) && (colorState >= 0)){
			return ItemDye.field_150922_c[colorState];
		}

		return 0xffffff;
	}

	public ResourceLocation getMantleTexture() {
		return null;
	}

	/**used by shields to determine a chance of blocking 100% of incoming damage*/
	public int getBlockChance(){
		return 0;
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
		if (par1ItemStack.getItem() == RpgInventoryMod.cloakI) {
			return true;
		}
		return false;
	}

	public boolean isMantle() {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		PlayerRpgInventory inv = PlayerRpgInventory.get(par3EntityPlayer);

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) == null) {
				if (inv.isItemValidForSlot(i, par1ItemStack)) {
					inv.setInventorySlotContents(i, par1ItemStack);
					par3EntityPlayer.destroyCurrentEquippedItem();
					break;
					// break, or right-clicking a ring will set both ring slots.
				}
			}
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

//	public boolean isBlocking(ItemStack stack){
//		return stack != null ? stack.hasTagCompound() ? stack.getTagCompound().getBoolean("blocking") : false : false;
//	}
//
//	public void setBlocking(ItemStack stack, boolean b){
//		if(stack != null){
//			NBTTagCompound tag = new NBTTagCompound();
//			tag.setBoolean("blocking", b);
//			stack.setTagCompound(tag);
//		}
//	}
}
