package rpgInventory.gui.rpginv;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import rpgInventory.mod_RpgInventory;
import rpgInventory.handlers.RPGEventHooks;
import rpgInventory.handlers.oldpackets.PacketInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import cpw.mods.fml.common.FMLLog;

public class PlayerRpgInventory implements IInventory,
		IExtendedEntityProperties {

	public ItemStack[] armorSlots = new ItemStack[7];
	// public String playername;
	// public EnumSet<EnumRpgClass> classSets;
	// public LinkedList classSets;
	public EntityPlayer player;

	public final static String EXT_PROP_NAME = "RpgInventory";

	private static final String tagName = "RpgInventory";

	public static final PlayerRpgInventory get(EntityPlayer p) {
		return (PlayerRpgInventory) p.getExtendedProperties(EXT_PROP_NAME);
	}

	/* =====SAVING ENTITY DATA ===== */

	public static final void register(EntityPlayer player) {
		if (player != null) {
			player.registerExtendedProperties(EXT_PROP_NAME,
					new PlayerRpgInventory(player));
			FMLLog.getLogger().info("Player properties registered");
		} else
			System.out.println("NOPE. player was null");
	}

	// PacketInventory pa = new PacketInventory();

	public PlayerRpgInventory(EntityPlayer p) {
		if (p != null)
			player = p;
	}

	@Override
	public void closeInventory() {
		// ExtendedPlayer props = ExtendedPlayer.get(player);
		// props.addEntry(this);

		// TODO
		System.out.println("send packet here for closed inventory");
		// PacketInventory.sendPacket(player, this);

		if (!player.worldObj.isRemote)
			PacketInventory.sendPacket((EntityPlayerMP) player, this);
		// PacketInventory pack = new PacketInventory();
		// PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
		// pipe.sendTo(pack, (EntityPlayerMP) player);
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
				// onInventoryChanged();
				return var3;
			} else {
				var3 = armorSlots[par1].splitStack(par2);

				if (armorSlots[par1].stackSize == 0)
					armorSlots[par1] = null;

				// onInventoryChanged();
				return var3;
			}
		} else
			return null;
	}

	/**
	 * called upon player's death. Will drop Jewels in the world
	 */
	public void dropJewels(EntityPlayer player) {
		PlayerRpgInventory rpg = this;// props.getInventory();
		int var1;
		for (var1 = 0; var1 < rpg.armorSlots.length; ++var1)
			if (rpg.armorSlots[var1] != null) {
				player.dropPlayerItemWithRandomChoice(rpg.armorSlots[var1],
						true);
				rpg.armorSlots[var1] = null;
			}
	}

	/**
	 * Returns a slot index in main inventory containing a specific itemID
	 */
	private int findJewel(Item par1) {
		for (int var2 = 0; var2 < armorSlots.length; ++var2)
			if ((armorSlots[var2] != null)
					&& (armorSlots[var2].getItem() == par1))
				return var2;

		return -1;
	}

	public ItemStack getCloak() {
		return armorSlots[2];
	}

	public ItemStack getCrystal() {
		return armorSlots[6];
	}

	public ItemStack getGloves() {
		return armorSlots[3];
	}

	@Override
	public String getInventoryName() {
		return "RpgInventory";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	public boolean getJewel(Item par1) {
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

	public ItemStack getJewelInSlot(int par1) {
		return armorSlots[par1];
	}

	public ItemStack getNecklace() {
		return armorSlots[0];
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public ItemStack getRing1() {
		return armorSlots[4];
	}

	public ItemStack getRing2() {
		return armorSlots[5];
	}

	public ItemStack getShield() {
		return armorSlots[1];
	}

	@Override
	public int getSizeInventory() {
		return armorSlots.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		if ((par1 >= 0) && (par1 < armorSlots.length))
			return armorSlots[par1];
		return null;
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		// mod_RpgInventory.proxy.addEntry(playername, this);
		// TODO
		System.out.println("fill packet here slotclosed");
		// PacketInventory.sendPacket(player, this);

		if (!player.worldObj.isRemote)
			PacketInventory.sendPacket((EntityPlayerMP) player, this);
		// PacketInventory pack = new PacketInventory();
		// PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
		// pipe.sendTo(pack, (EntityPlayerMP) player);
		return null;
	}

	// @Override
	// public boolean isInventoryNameLocalized() {
	// return false;
	// }

	/* =====INVENTORY===== */
	public boolean hasClass(String rpgenum) {
		// if (EnumRpgClass.getPlayerClasses(player).contains(rpgenum)) {
		// return true;
		// }
		if (rpgenum.equals(mod_RpgInventory.playerClass))
			return true;
		return false;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void init(Entity entity, World world) {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if ((itemstack.getItem() instanceof ItemRpgInvArmor)) {
			ItemRpgInvArmor tmp = (ItemRpgInvArmor) itemstack.getItem();
			switch (i) {
			case 0:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.NECKLACE)
					return true;
				return false;
			case 1:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.SHIELD)
					return true;
				return false;
			case 2:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.CLOAK)
					return true;
				return false;
			case 3:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.GLOVES)
					return true;
				return false;
			case 4:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.RING)
					return true;
				return false;
			case 5:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.RING)
					return true;
				return false;
			case 6:
				if (tmp.armorType == mod_RpgInventory.ITEMTYPE.CRYSTAL)
					if (itemstack.getItemDamage() > 0)
						return true;
				return false;
			default:
				// System.out.println("Unknown RPG Inventory type:" +
				// slotIndex);
				return false;
			}
		}
		return false;
	}

	// /**
	// * Writes the inventory out as a list of compound tags. This is where the
	// * slot indices are used (+100 for armor, +80 for crafting).
	// */
	// public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
	// NBTTagList var2 = new NBTTagList();
	// for (int var3 = 0; var3 < armorSlots.length; ++var3) {
	// if (armorSlots[var3] != null) {
	// NBTTagCompound compoundSlot = new NBTTagCompound();
	// compoundSlot.setByte("SlotNum", (byte) var3);
	// armorSlots[var3].writeToNBT(compoundSlot);
	// var2.appendTag(compoundSlot);
	// }
	// }
	// par1NBTTagCompound.setTag("Slot", var2);
	// return par1NBTTagCompound;
	// }
	//
	// /**
	// * Reads from the given tag list and fills the slots in the inventory with
	// * the correct items.
	// */
	// public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
	// NBTTagList var2 = par1NBTTagCompound.getTagList("Slot");
	// armorSlots = new ItemStack[getSizeInventory()];
	// for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
	// NBTTagCompound compoundSlot = (NBTTagCompound) var2.tagAt(var3);
	// byte var5 = compoundSlot.getByte("SlotNum");
	// if (var5 >= 0 && var5 < armorSlots.length) {
	// try {
	// armorSlots[var5] = ItemStack.loadItemStackFromNBT(compoundSlot);
	// } catch (Throwable ex) {
	// ex.printStackTrace();
	// }
	// }
	// }
	// }

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		EntityPlayer player = MinecraftServer.getServer()
				.getConfigurationManager()
				.getPlayerForUsername(par1EntityPlayer.getCommandSenderName());
		return player.isDead ? false : par1EntityPlayer
				.getDistanceSqToEntity(player) <= 64.0D;
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		readFromNBT(compound);
	}

	@Override
	// onInventoryChanged
	public void markDirty() {
		try {

			// TODO
			// System.out.println("send packet here updated as unclean");
			// PacketInventory.sendPacket(player, this);

			if (!player.worldObj.isRemote)
				PacketInventory.sendPacket((EntityPlayerMP) player, this);

			EntityPlayer player = MinecraftServer.getServer()
					.getConfigurationManager()
					.getPlayerForUsername(this.player.getCommandSenderName());
			// classSets = EnumRpgClass.getPlayerClasses(player);
			// this to-do is done ! moved that line ^ to eventhooks so it gets
			// updated
			// properly.
			boolean addtoticks[] = new boolean[3];

			// TODO place this elsewhere. the bow and magestaff ar no longer
			// part of rpg inventory
			// if (mod_RpgInventory.playerClass
			// .contains(mod_RpgInventory.CLASSARCHERSHIELD)) {
			// if ((player.inventory.getCurrentItem() != null)
			// && (player.inventory.getCurrentItem().getItem() != null)) {
			// if (player.inventory.getCurrentItem().getItem()
			// .equals(mod_RpgInventory.elfbow)
			// || (player.inventory.getCurrentItem().getItem() instanceof
			// ItemBow)) {
			// addtoticks[0] = true;
			// }
			// }
			// } else if (mod_RpgInventory.playerClass
			// .contains(mod_RpgInventory.CLASSMAGESHIELD)) {
			// if (player.getCurrentEquippedItem() != null) {
			// if (player.getCurrentEquippedItem().getItem()
			// .equals(mod_RpgInventory.staf)) {
			// addtoticks[1] = true;
			// }
			// }
			// }

			if (((getNecklace() != null) && (getNecklace().getItem() == mod_RpgInventory.neckdia))
					|| ((getRing1() != null) && (getRing1().getItem() == mod_RpgInventory.ringdia))
					|| ((getRing2() != null) && (getRing2().getItem() == mod_RpgInventory.ringdia))
					|| ((getGloves() != null) && (getGloves().getItem() == mod_RpgInventory.glovesdia)))
				addtoticks[2] = true;

			// TODO move to baseAddon
			// if (addtoticks[0]) {
			// if (!RPGEventHooks.ArcherRepairTick.containsKey(player
			// .getCommandSenderName()))
			// RPGEventHooks.ArcherRepairTick.put(
			// player.getCommandSenderName(), 0);
			// } else
			// // keep the cooldown hashmap clean
			// RPGEventHooks.ArcherRepairTick.remove(player
			// .getCommandSenderName());

			if (addtoticks[1]) {
				if (!RPGEventHooks.HealerTick.containsKey(player
						.getCommandSenderName()))
					RPGEventHooks.HealerTick.put(player.getCommandSenderName(),
							0);
			} else
				// keep the cooldown hashmap clean
				RPGEventHooks.HealerTick.remove(player.getCommandSenderName());

			if (addtoticks[2]) {
				if (!RPGEventHooks.DiamondTick.containsKey(player
						.getCommandSenderName()))
					RPGEventHooks.DiamondTick.put(
							player.getCommandSenderName(), 0);
			} else
				// keep the cooldown hashmap clean
				RPGEventHooks.DiamondTick.remove(player.getCommandSenderName());
		} catch (Throwable ex) {
		}
	}

	@Override
	public void openInventory() {
	}

	public void readFromNBT(NBTTagCompound tagcompound) {
		NBTTagList nbttaglist = tagcompound.getTagList(tagName, 10);
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if ((b0 >= 0) && (b0 < this.getSizeInventory()))
				this.setInventorySlotContents(b0,
						ItemStack.loadItemStackFromNBT(nbttagcompound1));
		}
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		writeToNBT(compound);
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		this.armorSlots[par1] = par2ItemStack;

		// TODO
		// if (!player.worldObj.isRemote) {
		// PacketInventory.sendPacket((EntityPlayerMP) player, this);
		// System.out.println("fill packet here slot content set remotely");

		// PacketInventory pack = new PacketInventory();
		// PacketPipeline17 pipe = mod_RpgInventory.PIPELINE;
		// pipe.sendTo(pack, (EntityPlayerMP) player);
		// }
	}

	public void writeToNBT(NBTTagCompound tagcompound) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		// We're storing our items in a custom tag list using our 'tagName' from
		// above
		// to prevent potential conflicts
		tagcompound.setTag(tagName, nbttaglist);
	}
}
