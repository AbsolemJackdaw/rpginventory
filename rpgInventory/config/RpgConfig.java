/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 *
 * @author Home
 */
public class RpgConfig {

	public static RpgConfig instance = new RpgConfig();

	// Initially set to defaults
	public boolean render3D = true;

	public boolean useSpell = true;

	public boolean DefaultRotation = true;
	
	public boolean lapisWeaponRepair = true;
	
	public boolean glovesFprendering = false;


	// This is to prevent accidintally creating a new instance.
	private RpgConfig() {
	}


	public void loadConfig(File file) {
		Configuration config = new Configuration(file);
		config.load();
		loadSettings(config);
		config.save();
	}


	private void loadSettings(Configuration config) {
		config.addCustomCategoryComment("Rendering",
				"Set these settings to false to disable fancy item rendering.");

		render3D = config.get("Rendering", "Render 3D Items", true).getBoolean(
				true);

		config.addCustomCategoryComment("Rotation",
				"Set to false for alternative PetGui Pet Rotation if default fails.");
		
		DefaultRotation = config.get("Rotation", "Default Rotation", true)
				.getBoolean(true);
		
		config.addCustomCategoryComment("Weapon Repair",
				"set to false to disable lapis jewelery from repairing held weapons.");
		
		lapisWeaponRepair = config.get("Weapon Repair", "Lapis Jewelery Repairs Weapons", true)
				.getBoolean(true);
		
		config.addCustomCategoryComment("Experimental First person Gloves",
				"sets rendering of first person gloves if any are worn. warning : experimental version and unfinished");
		
		glovesFprendering = config.get("Experimental First person Gloves", "Experimental Glove Rendering", false)
				.getBoolean(false);
		
	}
}
