package WWBS.wwbs.config;

import java.io.File;

/**
 * 
 * @author Home
 */
public class Config {

	public static Config instance = new Config();

	final int bID_OFFSET = 451;

	public static int bankBlock;
	public static int MEBlock;

	// This is to prevent accidintally creating a new instance.
	private Config() {
	}

	private void loadBlocks(Configuration config) {
		int blocknum = bID_OFFSET;
		bankBlock = config.getBlock("BankBlock", blocknum).getInt(blocknum);
		blocknum++;
		MEBlock = config.getBlock("Massive Exchange Block", blocknum).getInt(
				blocknum);
		blocknum++;
	}

	public void loadConfig(File file) {
		Configuration config = new Configuration(file);
		config.load();
		loadBlocks(config);
		config.save();
	}
}
