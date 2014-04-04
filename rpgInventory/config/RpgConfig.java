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

		useSpell = config.get(Configuration.CATEGORY_GENERAL,
				"Allow Day/Night Cycle Spell", true,
				"Disable the spell that toggles the day night cycle.")
				.getBoolean(true);
	}
}
