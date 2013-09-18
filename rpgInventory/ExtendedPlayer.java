package rpgInventory;

import rpgInventory.gui.rpginv.RpgInv;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "ExtendedPlayer";

	private final EntityPlayer player;

	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
	}

	/**
	 * Used to register these extended properties for the player during EntityConstructing event
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}

	/**
	 * Returns ExtendedPlayer properties for player
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}


	@Override
	public void saveNBTData(NBTTagCompound compound) {

		compound.setString("test", "testString");

		NBTTagList var2 = new NBTTagList();
		if(player != null){
			RpgInv inv = new RpgInv(player.username);
			for (int var3 = 0; var3 < inv.armorSlots.length; ++var3) {
				if (inv.armorSlots[var3] != null) {
					NBTTagCompound compoundSlot = new NBTTagCompound();
					compoundSlot.setByte("SlotNum", (byte) var3);
					inv.armorSlots[var3].writeToNBT(compoundSlot);
					var2.appendTag(compoundSlot);
				}
			}
			compound.setTag("Slot", var2);
		}
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		
		NBTTagList var2 = compound.getTagList("Slot");
		
		if(player != null){
			RpgInv inv = new RpgInv(player.username);
			inv.armorSlots = new ItemStack[inv.getSizeInventory()];
			for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
				NBTTagCompound compoundSlot = (NBTTagCompound) var2.tagAt(var3);
				byte var5 = compoundSlot.getByte("SlotNum");
				if (var5 >= 0 && var5 < inv.armorSlots.length) {
					try {
						inv.armorSlots[var5] = ItemStack.loadItemStackFromNBT(compoundSlot);
					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void init(Entity entity, World world) {

//		if(entity instanceof EntityPlayer){
//			player = (EntityPlayer)entity;
//		}
	}

}
