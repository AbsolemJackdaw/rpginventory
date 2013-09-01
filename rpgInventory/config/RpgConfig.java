/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

/**
 *
 * @author Home
 */
public class RpgConfig {

    public static RpgConfig instance = new RpgConfig();
    
    
    final int iID_OFFSET = 5780;
    final int bID_OFFSET = 450;
    public int neckgoldID;
    public int neckdiaID;
    public int neckemID;
    public int necklapID;
    public int glovesbutterID;
    public int glovesdiaID;
    public int glovesemID;
    public int gloveslapID;
    public int ringgoldID;
    public int ringdiaID;
    public int ringemID;
    public int ringlapID;
    public int archersShieldID;
    public int berserkerShieldID;
    public int talismanID;
    public int pala_shieldID;
    public int necro_shieldID;
    public int shieldWoodID;
    public int shieldIronID;
    public int shieldGoldID;
    public int shieldDiamondID;
    public int cloakID;
    public int cloakIID;
    public int cloakSubID;
    public int cloakRedID;
    public int cloakYellowID;
    public int cloakGreenID;
    public int cloakBlueID;
    public int elfbowID;
    public int claymoreID;
    public int hammerID;
    public int wandID;
    public int stafID;
    public int rageSeedID;
    public int magehoodID;
    public int magegownID;
    public int magepantsID;
    public int magebootsID;
    public int archerhoodID;
    public int archerchestID;
    public int archerpantsID;
    public int archerbootsID;
    public int berserkerHoodID;
    public int berserkerChestID;
    public int berserkerLegsID;
    public int berserkerBootsID;
    public int necroHoodID;
    public int necroChestplateID;
    public int necroLeggingsID;
    public int necroBootsID;
    public int palaHelmID;
    public int palaChestID;
    public int palaLeggingsID;
    public int palaBootsID;
    public int pala_weaponID;
    public int animalskinID;
    public int tanHideID;
    public int mageclothID;
    public int wizardBookID;
    public int colmoldID;
    public int ringmoldID;
    public int wantmoldID;
    public int necro_weaponID;
    public int necro_skinID;
    public int pala_steelID;
    public int daggersID;
    public int crystalID;
    public int rogueLeatherID;
    public int beastLeatherID;
    public int forgeblockID;
    public int beastHoodID;
    public int beastChestID;
    public int beastLegsID;
    public int beastBootsID;
    public int rogueHoodID;
    public int rogueChestID;
    public int rogueLegsID;
    public int rogueBootsID;
    public int whistleID;
    public int frostStaff;
    public int fireStaff;
    public int archBook;
    public int beastShield;
    public int staffEarth;
    public int staffWind;
    public int staffUltimate;
    public int archmageHood;
    public int archmageChest;
    public int archmageLegs;
    public int archmageBoots;
    public int beastAxe;
    public int candy;
    public int brench;
    public int petxppotion;

    //Initially set to defaults
    public boolean render3D = true;

    
    public boolean useSpell = true;

    public boolean DefaultRotation = true;


    //This is to prevent accidintally creating a new instance.
    private RpgConfig() {}

    public void loadConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();
        loadItems(config);
        loadBlocks(config);
        loadSettings(config);
        config.save();
    }
    private void loadSettings(Configuration config){
        config.addCustomCategoryComment("Rendering", "Set these settings to false to disable fancy item rendering.");

        render3D = config.get("Rendering", "Render 3D Items", true).getBoolean(true);

        config.addCustomCategoryComment("Rotation", "Set to false for alternative PetGui Pet Rotation if default fails.");
        DefaultRotation = config.get("Rotation", "Default Rotation", true).getBoolean(true);

        useSpell = config.get(Configuration.CATEGORY_GENERAL, "Allow Day/Night Cycle Spell", true,"Disable the spell that toggles the day night cycle.").getBoolean(true);
    }
    private void loadItems(Configuration config){
        int itemnum = iID_OFFSET;
        neckgoldID = config.getItem("neckgold", itemnum).getInt(itemnum);
        itemnum++;
        neckdiaID = config.getItem("neckdia", itemnum).getInt(itemnum);
        itemnum++;
        neckemID = config.getItem("neckem", itemnum).getInt(itemnum);
        itemnum++;
        necklapID = config.getItem("necklap", itemnum).getInt(itemnum);
        itemnum++;
        glovesbutterID = config.getItem("glovesbutter", itemnum).getInt(itemnum);
        itemnum++;
        glovesdiaID = config.getItem("glovesdia", itemnum).getInt(itemnum);
        itemnum++;
        glovesemID = config.getItem("glovesem", itemnum).getInt(itemnum);
        
        itemnum++;
        gloveslapID = config.getItem("gloveslap", itemnum).getInt(itemnum);
        
        itemnum++;
        ringgoldID = config.getItem("ringgold", itemnum).getInt(itemnum);
        
        itemnum++;
        ringdiaID = config.getItem("ringdia", itemnum).getInt(itemnum);
        
        itemnum++;
        ringemID = config.getItem("ringem", itemnum).getInt(itemnum);
        
        itemnum++;
        ringlapID = config.getItem("ringlap", itemnum).getInt(itemnum);
        
        itemnum++;
        archersShieldID = config.getItem("archersShield", itemnum).getInt(itemnum);
        
        itemnum++;
        berserkerShieldID = config.getItem("berserkerShield", itemnum).getInt(itemnum);
        
        itemnum++;
        talismanID = config.getItem("talisman", itemnum).getInt(itemnum);
        
        itemnum++;
        pala_shieldID = config.getItem("pala_shield", itemnum).getInt(itemnum);
        
        itemnum++;
        necro_shieldID = config.getItem("necro_shield", itemnum).getInt(itemnum);
        
        itemnum++;
        shieldWoodID = config.getItem("shieldWood", itemnum).getInt(itemnum);
        
        itemnum++;
        shieldIronID = config.getItem("shieldIron", itemnum).getInt(itemnum);
        
        itemnum++;
        shieldGoldID = config.getItem("shieldGold", itemnum).getInt(itemnum);
        
        itemnum++;
        shieldDiamondID = config.getItem("shieldDiamond", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakID = config.getItem("cloak", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakIID = config.getItem("cloakI", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakSubID = config.getItem("cloakSub", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakRedID = config.getItem("cloakRed", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakYellowID = config.getItem("cloakYellow", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakGreenID = config.getItem("cloakGreen", itemnum).getInt(itemnum);
        
        itemnum++;
        cloakBlueID = config.getItem("cloakBlue", itemnum).getInt(itemnum);
        
        itemnum++;
        elfbowID = config.getItem("elfbow", itemnum).getInt(itemnum);
        itemnum++;
        claymoreID = config.getItem("claymore", itemnum).getInt(itemnum);
        itemnum++;
        hammerID = config.getItem("hammer", itemnum).getInt(itemnum);
        itemnum++;
        wandID = config.getItem("wand", itemnum).getInt(itemnum);
        itemnum++;
        stafID = config.getItem("staf", itemnum).getInt(itemnum);
        itemnum++;
        rageSeedID = config.getItem("rageSeed", itemnum).getInt(itemnum);
        itemnum++;
        magehoodID = config.getItem("magehood", itemnum).getInt(itemnum);
        itemnum++;
        magegownID = config.getItem("magegown", itemnum).getInt(itemnum);
        itemnum++;
        magepantsID = config.getItem("magepants", itemnum).getInt(itemnum);
        itemnum++;
        magebootsID = config.getItem("mageboots", itemnum).getInt(itemnum);
        itemnum++;
        archerhoodID = config.getItem("archerhood", itemnum).getInt(itemnum);
        itemnum++;
        archerchestID = config.getItem("archerchest", itemnum).getInt(itemnum);
        itemnum++;
        archerpantsID = config.getItem("archerpants", itemnum).getInt(itemnum);
        itemnum++;
        archerbootsID = config.getItem("archerboots", itemnum).getInt(itemnum);
        itemnum++;
        berserkerHoodID = config.getItem("berserkerHood", itemnum).getInt(itemnum);
        itemnum++;
        berserkerChestID = config.getItem("berserkerChest", itemnum).getInt(itemnum);
        itemnum++;
        berserkerLegsID = config.getItem("berserkerLegs", itemnum).getInt(itemnum);
        itemnum++;
        berserkerBootsID = config.getItem("berserkerBoots", itemnum).getInt(itemnum);
        itemnum++;
        necroHoodID = config.getItem("necroHood", itemnum).getInt(itemnum);
        itemnum++;
        necroChestplateID = config.getItem("necroChestplate", itemnum).getInt(itemnum);
        itemnum++;
        necroLeggingsID = config.getItem("necroLeggings", itemnum).getInt(itemnum);
        itemnum++;
        necroBootsID = config.getItem("necroBoots", itemnum).getInt(itemnum);
        itemnum++;
        palaHelmID = config.getItem("palaHelm", itemnum).getInt(itemnum);
        itemnum++;
        palaChestID = config.getItem("palaChest", itemnum).getInt(itemnum);
        itemnum++;
        palaLeggingsID = config.getItem("palaLeggings", itemnum).getInt(itemnum);
        itemnum++;
        palaBootsID = config.getItem("palaBoots", itemnum).getInt(itemnum);
        itemnum++;
        pala_weaponID = config.getItem("pala_weapon", itemnum).getInt(itemnum);
        itemnum++;
        animalskinID = config.getItem("animalskin", itemnum).getInt(itemnum);
        itemnum++;
        tanHideID = config.getItem("tanHide", itemnum).getInt(itemnum);
        itemnum++;
        mageclothID = config.getItem("magecloth", itemnum).getInt(itemnum);
        itemnum++;
        wizardBookID = config.getItem("wizardBook", itemnum).getInt(itemnum);
        itemnum++;
        colmoldID = config.getItem("colmold", itemnum).getInt(itemnum);
        itemnum++;
        ringmoldID = config.getItem("ringmold", itemnum).getInt(itemnum);
        itemnum++;
        wantmoldID = config.getItem("wantmold", itemnum).getInt(itemnum);
        itemnum++;
        necro_weaponID = config.getItem("necro_weapon", itemnum).getInt(itemnum);
        itemnum++;
        necro_skinID = config.getItem("necro_skin", itemnum).getInt(itemnum);
        itemnum++;
        pala_steelID = config.getItem("pala_steel", itemnum).getInt(itemnum);
        itemnum++;
        daggersID = config.getItem("daggers", itemnum).getInt(itemnum);
        itemnum++;
        crystalID = config.getItem("crystal", itemnum).getInt(itemnum);
        itemnum++;
        rogueLeatherID= config.getItem("rogueLeather", itemnum).getInt(itemnum);
        itemnum++;
        beastLeatherID= config.getItem("beastLeather", itemnum).getInt(itemnum);
        itemnum++;
        beastHoodID= config.getItem("beastHood", itemnum).getInt(itemnum);
        itemnum++;
        beastChestID= config.getItem("beastChest", itemnum).getInt(itemnum);
        itemnum++;
        beastLegsID= config.getItem("beastLegs", itemnum).getInt(itemnum);
        itemnum++;
        beastBootsID= config.getItem("beastBoots", itemnum).getInt(itemnum);
        itemnum++;
        rogueHoodID= config.getItem("rogueHood", itemnum).getInt(itemnum);
        itemnum++;
        rogueChestID= config.getItem("rogueChest", itemnum).getInt(itemnum);
        itemnum++;
        rogueLegsID= config.getItem("rogueLegs", itemnum).getInt(itemnum);
        itemnum++;
        rogueBootsID= config.getItem("rogueBoots", itemnum).getInt(itemnum);
        itemnum++;
        beastAxe= config.getItem("axe", itemnum).getInt(itemnum);
        itemnum++;
        brench= config.getItem("TangledBrench", itemnum).getInt(itemnum);
        itemnum++;
        whistleID= config.getItem("whistle", itemnum).getInt(itemnum);
        itemnum++;
        candy= config.getItem("Easter Egg Item", itemnum).getInt(itemnum);
        itemnum++;
        fireStaff = config.getItem("fireStaff", itemnum).getInt(itemnum);
        itemnum++;
        frostStaff = config.getItem("frostStaff", itemnum).getInt(itemnum);
        itemnum++;
        staffEarth = config.getItem("staffEarth", itemnum).getInt(itemnum);
        itemnum++;
        staffUltimate = config.getItem("staffUltimate", itemnum).getInt(itemnum);
        itemnum++;
        staffWind = config.getItem("staffWind", itemnum).getInt(itemnum);
        itemnum++;
        archBook = config.getItem("archBook", itemnum).getInt(itemnum);
        itemnum++;
        beastShield = config.getItem("beastShield", itemnum).getInt(itemnum);
        itemnum++;
        archmageHood= config.getItem("archmageHood", itemnum).getInt(itemnum);
        itemnum++;
        archmageChest= config.getItem("archmageChest", itemnum).getInt(itemnum);
        itemnum++;
        archmageLegs= config.getItem("archmageLegs", itemnum).getInt(itemnum);
        itemnum++;
        archmageBoots= config.getItem("archmageBoots", itemnum).getInt(itemnum);
        itemnum++;
        petxppotion = config.getItem("PetXPPotion", itemnum).getInt(itemnum);
        itemnum++;
    }
    
    private void loadBlocks(Configuration config){
        int blocknum = bID_OFFSET;
        forgeblockID = config.getBlock("forgeblock", blocknum).getInt(blocknum);
        blocknum++;
    }
}
