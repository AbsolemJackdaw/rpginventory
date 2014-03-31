package rpgInventory.handlers.proxy;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import rpgInventory.RpgInventoryMod;
import rpgInventory.handlers.CommonTickHandler;
import rpgInventory.handlers.packets.ServerPacketHandler;

public class CommonProxy {

	public static final String RPG_DIR = "RPG_Inventories";

	public static void renderHandler() {
	}

	public void consumeItem(EntityPlayer player, Item itemID) {
		player.inventory.consumeInventoryItem(itemID);
	}

	public int getSphereID() {
		return 0;
	}

	public void load() {

	}

	public void openGUI(EntityPlayer player, int id) {
	}

	public void playerLevel(EntityPlayer player, int amount) {
		player.addExperienceLevel(-amount);
	}

	public void registerLate() {
	}

	public void registerRenderInformation() {
	}

	public void spawnCharmParticle(World world, EntityLivingBase p, Random rng,
			boolean success) {
	}

	public void spawnParticle(World world, EntityLivingBase el, Random rng) {

	}
}
