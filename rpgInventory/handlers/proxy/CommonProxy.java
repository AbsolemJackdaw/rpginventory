package rpgInventory.handlers.proxy;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class CommonProxy {

	public void spawnParticle(World world, EntityLiving el, Random rng) {
	}
	public static final String RPG_DIR = "RPG_Inventories";

	public int getSphereID() {
		return 0;
	}
	
	public static void renderHandler() {
		
	}
	
	public void spawnCharmParticle(World world, EntityLiving el, Random rng, boolean success) {
	}


	public void registerLate() {
	}

	public void registerRenderInformation() {
	}

	public void openGUI(EntityPlayer player, int id) {
	}

	public void openGUI(EntityPlayer player, PlayerRpgInventory inv) {
	}

	public void playerLevel(EntityPlayer player, int amount) {
		player.addExperienceLevel(-amount);
	}

	public void consumeItem(EntityPlayer player, int itemID) {
		player.inventory.consumeInventoryItem(itemID);
	}

	public void candy(EntityPlayer p) {
		p.inventory.consumeInventoryItem(mod_RpgInventory.petCandy.itemID);
	}
}
