package rpgInventory.gui.rpginv;

import java.util.EnumSet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import rpgInventory.EnumRpgClass;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.handlers.packets.PacketInventory;
import cpw.mods.fml.common.FMLLog;

public class PlayerRpgInventory implements IInventory, IExtendedEntityProperties {

	public ItemStack[] armorSlots = new ItemStack[7];
	public String playername;
	public EnumSet<EnumRpgClass> classSets;
	private EntityPlayer player;
	
	public final static String EXT_PROP_NAME = "RpgInventory";
	
	private static final String tagName = "RpgInventory";

	public PlayerRpgInventory(EntityPlayer p) {
		playername = p.username;
		player = p;
		classSets = EnumSet.noneOf(EnumRpgClass.class);
	}

	/*=====SAVING ENTITY DATA =====*/
	
	public static final void register(EntityPlayer player){
		player.registerExtendedProperties(EXT_PROP_NAME, new PlayerRpgInventory(player));
		FMLLog.getLogger().info("Player properties registered" );
	}
	
	public static final PlayerRpgInventory get(EntityPlayer p){
		return (PlayerRpgInventory) p.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		writeToNBT(compound);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		readFromNBT(compound);
	}

	@Override
	public void init(Entity entity, World world) {		
	}
	
	/*=====INVENTORY=====*/
	public boolean hasClass(EnumRpgClass rpgenum) {
		if (classSets.contains(rpgenum)) {
			return true;
		}
		return false;
	}

	@Override
	public int getSizeInventory() {
		return armorSlots.length;
	}

	public ItemStack getJewelInSlot(int par1) {
		return armorSlots[par1];
	}

	/**
	 * Returns a slot index in main inventory containing a specific itemID
	 */
	private int findJewel(int par1) {
		for (int var2 = 0; var2 < armorSlots.length; ++var2) {
			if (armorSlots[var2] != null && armorSlots[var2].itemID == par1) {
				return var2;
			}
		}

		return -1;
	}

	public ItemStack getShield() {
		return armorSlots[1];
	}

	public ItemStack getCloak() {
		return armorSlots[2];
	}

	public ItemStack getNecklace() {
		return armorSlots[0];
	}

	public ItemStack getGloves() {
		return armorSlots[3];
	}

	public ItemStack getRing1() {
		return armorSlots[4];
	}

	public ItemStack getRing2() {
		return armorSlots[5];
	}

	public ItemStack getCrystal() {
		return armorSlots[6];
	}

	public boolean getJewel(int par1) {
		int var2 = findJewel(par1);
		return var2 >= 0;
	}

	public ItemStack getJewelFromStack(int par1) {
		ItemStack[] var2 = armorSlots;

		if (par1 >= var2.length) {
			par1 -= var2.length;
			var2 = armorSlots;
		}

		return var2[par1];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (armorSlots[par1] != null) {
			ItemStack var3;

			if (armorSlots[par1].stackSize <= par2) {
				var3 = armorSlots[par1];
				armorSlots[par1] = null;
				//onInventoryChanged();
				return var3;
			} else {
				var3 = armorSlots[par1].splitStack(par2);

				if (armorSlots[par1].stackSize == 0) {
					armorSlots[par1] = null;
				}

				//onInventoryChanged();
				return var3;
			}
		} else {
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		//        mod_RpgInventory.proxy.addEntry(playername, this);
		PacketInventory.sendPacket(player, this);
		return null;
	}

	/**
	 * called upon player's death. Will drop Jewels in the world
	 */
	public void dropJewels(EntityPlayer player) {
		PlayerRpgInventory rpg= this;//props.getInventory();
		int var1;
		for (var1 = 0; var1 < rpg.armorSlots.length; ++var1) {
			if (rpg.armorSlots[var1] != null) {
				player.dropPlayerItemWithRandomChoice(rpg.armorSlots[var1], true);
				rpg.armorSlots[var1] = null;
			}
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.armorSlots[par1] = par2ItemStack;
		PacketInventory.sendPacket(player, this);
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		if (par1 >= 0 && par1 < armorSlots.length) {
			return armorSlots[par1];
		}
		return null;
	}

	@Override
	public String getInvName() {
		return "RpgInventory";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void onInventoryChanged() {
		try {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(playername);
			//classSets = EnumRpgClass.getPlayerClasses(player);
			//TODO is done ! moved that line ^ to eventhooks so it gets updated properly.
			boolean addtoticks[] = new boolean[3];
			if (hasClass(EnumRpgClass.SHIELDEDARCHER)) {
				if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() != null) {
					if (player.inventory.getCurrentItem().getItem().equals(mod_RpgInventory.elfbow) || player.inventory.getCurrentItem().getItem() instanceof ItemBow) {
						addtoticks[0] = true;
					}
				}
			} else if (hasClass(EnumRpgClass.SHIELDEDMAGE)) {
				if (player.getCurrentEquippedItem() != null) {
					if (player.getCurrentEquippedItem().getItem().equals(mod_RpgInventory.staf)) {
						addtoticks[1] = true;
					}
				}

			}
			if ((getNecklace() != null && getNecklace().itemID == mod_RpgInventory.neckdia.itemID)
					|| (getRing1() != null && getRing1().itemID == mod_RpgInventory.ringdia.itemID)
					|| (getRing2() != null && getRing2().itemID == mod_RpgInventory.ringdia.itemID)
					|| (getGloves() != null && getGloves().itemID == mod_RpgInventory.glovesdia.itemID)) {
				addtoticks[2] = true;
			}


			if (addtoticks[0]) {
				if (!RPGEventHooks.ArcherRepairTick.containsKey(player.username)) {
					RPGEventHooks.ArcherRepairTick.put(player.username, 0);
				}
			} else {
				//keep the cooldown hashmap clean
				RPGEventHooks.ArcherRepairTick.remove(player.username);
			}


			if (addtoticks[1]) {
				if (!RPGEventHooks.HealerTick.containsKey(player.username)) {
					RPGEventHooks.HealerTick.put(player.username, 0);
				}
			} else {
				//keep the cooldown hashmap clean
				RPGEventHooks.HealerTick.remove(player.username);
			}

			if (addtoticks[2]) {
				if (!RPGEventHooks.DiamondTick.containsKey(player.username)) {
					RPGEventHooks.DiamondTick.put(player.username, 0);
				}
			} else {
				//keep the cooldown hashmap clean
				RPGEventHooks.DiamondTick.remove(player.username);
			}
		} catch (Throwable ex) {
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(playername);
		return player.isDead ? false : par1EntityPlayer.getDistanceSqToEntity(player) <= 64.0D;
	}

	public void writeToNBT(NBTTagCompound tagcompound)
	{
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.getStackInSlot(i) != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		// We're storing our items in a custom tag list using our 'tagName' from above
		// to prevent potential conflicts
		tagcompound.setTag(tagName, nbttaglist);
	}

	public void readFromNBT(NBTTagCompound tagcompound)
	{
		NBTTagList nbttaglist = tagcompound.getTagList(tagName);
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.getSizeInventory())
			{
				this.setInventorySlotContents(b0, ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}
	}

	//	/**
	//	 * Writes the inventory out as a list of compound tags. This is where the
	//	 * slot indices are used (+100 for armor, +80 for crafting).
	//	 */
	//	public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
	//		NBTTagList var2 = new NBTTagList();
	//		for (int var3 = 0; var3 < armorSlots.length; ++var3) {
	//			if (armorSlots[var3] != null) {
	//				NBTTagCompound compoundSlot = new NBTTagCompound();
	//				compoundSlot.setByte("SlotNum", (byte) var3);
	//				armorSlots[var3].writeToNBT(compoundSlot);
	//				var2.appendTag(compoundSlot);
	//			}
	//		}
	//		par1NBTTagCompound.setTag("Slot", var2);
	//		return par1NBTTagCompound;
	//	}
	//
	//	/**
	//	 * Reads from the given tag list and fills the slots in the inventory with
	//	 * the correct items.
	//	 */
	//	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
	//		NBTTagList var2 = par1NBTTagCompound.getTagList("Slot");
	//		armorSlots = new ItemStack[getSizeInventory()];
	//		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
	//			NBTTagCompound compoundSlot = (NBTTagCompound) var2.tagAt(var3);
	//			byte var5 = compoundSlot.getByte("SlotNum");
	//			if (var5 >= 0 && var5 < armorSlots.length) {
	//				try {
	//					armorSlots[var5] = ItemStack.loadItemStackFromNBT(compoundSlot);
	//				} catch (Throwable ex) {
	//					ex.printStackTrace();
	//				}
	//			}
	//		}
	//	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
//		ExtendedPlayer props = ExtendedPlayer.get(player);
//		props.addEntry(this);
		
		PacketInventory.sendPacket(player, this);

	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}
