package rpgInventory.handelers.proxy;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;

public class CommonProxy {

	public void spawnParticle(World world, EntityLiving el, Random rng) {
	}
	public static final String RPG_DIR = "RPG_Inventories";

	public int getSphereID() {
		return 0;
	}

	public void spawnCharmParticle(World world, EntityLiving el, Random rng, boolean success) {
	}

	public void addEntry(String username, RpgInv inv) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);

			if (player != null) {
				if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
				} else {
					player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG));
					player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound("RpgInv")));
				}
			}
		}
	}

	public void registerLate() {
	}

	public void registerRenderInformation() {
	}

	public void openGUI(EntityPlayer player, int id) {
	}

	public void openGUI(EntityPlayer player, RpgInv inv) {
	}

	public void playerLevel(EntityPlayer player, int amount) {
		player.addExperienceLevel(-amount);
	}

	public void consumeItem(EntityPlayer player, int itemID) {
		player.inventory.consumeInventoryItem(itemID);
	}

	public RpgInv getInventory(String username) {
		RpgInv inv = new RpgInv(username);
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).hasKey("RpgInv")) {
					inv.readFromNBT(player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getCompoundTag("RpgInv"));
				} else {
					if (!player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("RpgInv", inv.writeToNBT(new NBTTagCompound()));
				}
			}
		}
		return inv;
	}

	public void candy(EntityPlayer p) {
		p.inventory.consumeInventoryItem(mod_RpgInventory.petCandy.itemID);
	}
}
