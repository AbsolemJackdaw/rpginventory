package WWBS.wwbs.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

/**
 *
 * @author Home
 */
public class Config {

    public static Config instance = new Config();
    
    
    final int iID_OFFSET = 5900;
    final int bID_OFFSET = 451;
   
    public static int bankBlock;
    public static int MEBlock;


    //This is to prevent accidintally creating a new instance.
    private Config() {}

    public void loadConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();
//        loadItems(config);
        loadBlocks(config);
        config.save();
    }

    private void loadItems(Configuration config){
        int itemnum = iID_OFFSET;
//        neckgoldID = config.getItem("neckgold", itemnum).getInt(itemnum);
//        itemnum++;
       
    }
    
    private void loadBlocks(Configuration config){
        int blocknum = bID_OFFSET;
        bankBlock = config.getBlock("BankBlock", blocknum).getInt(blocknum);
        blocknum++;
        MEBlock = config.getBlock("Massive Exchange Block", blocknum).getInt(blocknum);
        blocknum++;
    }
}
