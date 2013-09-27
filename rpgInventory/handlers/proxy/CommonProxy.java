package rpgInventory.handlers.proxy;

import java.util.Random;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
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
	
	public ModelBiped getArmorModel(int id){
		return null;
	}

	
	public void spawnCharmParticle(World world, EntityLivingBase p, Random rng, boolean success) {
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

//	public void candy(EntityPlayer p) {
//		p.inventory.consumeInventoryItem(mod_RpgInventory.petCandy.itemID);
//	}
}
