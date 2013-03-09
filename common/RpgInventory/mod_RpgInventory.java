package RpgInventory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import RpgInventory.Configuration.RpgConfig;
import RpgInventory.PotionEffects.DecomposePotion;
import RpgInventory.PotionEffects.MasochismPotion;
import RpgInventory.forge.BlockForge;
import RpgInventory.forge.TEMold;
import RpgInventory.gui.RpgInventoryTab;
import RpgRB.ItemCrystal;
import RpgInventory.item.ItemMats;
import RpgInventory.item.ItemRageFood;
import RpgInventory.item.ItemRpg;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgInventory.item.armor.ItemRpgPlusPlusArmor;
import RpgInventory.weapons.ItemGrandSword;
import RpgInventory.weapons.ItemNecroSkull;
import RpgInventory.weapons.bow.BowRender;
import RpgInventory.weapons.bow.EntityHellArrow;
import RpgInventory.weapons.bow.ItemArcherBow;
import RpgInventory.weapons.claymore.ClaymoreRenderer;
import RpgInventory.weapons.claymore.ItemClaymore;
import RpgInventory.weapons.hammer.HammerRender;
import RpgInventory.weapons.hammer.ItemHammer;
import RpgInventory.weapons.staf.ItemStaf;
import RpgInventory.weapons.staf.StafRender;
import RpgInventory.weapons.wand.ItemMageWand;
import RpgInventory.weapons.wand.SoulSphereRender;
import RpgMageSet.weapons.ItemElementalStaff;
import RpgRB.ItemRBMats;
import RpgRB.ItemRBMats2;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.src.ModLoader;

@Mod(modid = "RPGInventoryMod", name = "RPG Inventory Mod", version = "147.70")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv", "RpgRawInv"}, packetHandler = RpgPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgInv"}, packetHandler = RpgPacketHandler.class))
public class mod_RpgInventory {

    public static mod_RpgInventory instance;
    @SidedProxy(serverSide = "RpgInventory.CommonProxy", clientSide = "RpgInventory.ClientProxy")
    public static CommonProxy proxy;
    public static Item neckgold;
    public static Item neckdia;
    public static Item neckem;
    public static Item necklap;
    public static Item glovesbutter;
    public static Item glovesdia;
    public static Item glovesem;
    public static Item gloveslap;
    public static Item ringgold;
    public static Item ringdia;
    public static Item ringem;
    public static Item ringlap;
    public static Item archersShield;
    public static Item berserkerShield;
    public static Item talisman;
    public static Item pala_shield;
    public static Item necro_shield;
    public static Item shieldWood;
    public static Item shieldIron;
    public static Item shieldGold;
    public static Item shieldDiamond;
    public static Item cloak;
    public static Item cloakI;
    public static Item cloakSub;
    public static Item cloakRed;
    public static Item cloakYellow;
    public static Item cloakGreen;
    public static Item cloakBlue;
    public static Item elfbow;			//  goes with Archers.
    public static Item claymore;		// goes with Berserkers
    public static Item hammer;
    public static Item wand;			// goes with Mages
    public static Item staf;
    public static Item rageSeed;
    public static Item magehood;
    public static Item magegown;
    public static Item magepants;
    public static Item mageboots;
    public static Item archerhood;
    public static Item archerchest;
    public static Item archerpants;
    public static Item archerboots;
    public static Item berserkerHood;
    public static Item berserkerChest;
    public static Item berserkerLegs;
    public static Item berserkerBoots;
    public static Item beastHood;
    public static Item beastChest;
    public static Item beastLegs;
    public static Item beastBoots;
    public static Item rogueHood;
    public static Item rogueChest;
    public static Item rogueLegs;
    public static Item rogueBoots;
    public static Item necroHood;
    public static Item necroChestplate;
    public static Item necroLeggings;
    public static Item necroBoots;
    public static Item palaHelm;
    public static Item palaChest;
    public static Item palaLeggings;
    public static Item palaBoots;
    public static Item pala_weapon;
    public static Item animalskin;
    public static Item tanHide;
    public static Item magecloth;
    public static Item wizardBook;
    public static Item colmold;
    public static Item ringmold;
    public static Item wantmold;
    public static Item necro_weapon;
    public static Item necro_skin;
    public static Item pala_steel;
    public static Item daggers;
    public static Item crystal;
    public static Item rogueLeather;
    public static Item beastLeather;
    public static Item whistle;
    public static Item fireStaff;
    public static Item frostStaff;
    public static Item archBook;
    public static Item beastShield;
    public static Item earthStaff;
    public static Item windStaff;
    public static Item ultimateStaff;
    public static Block forgeBlock;
    //Die bitches.
    public static List<String> developers = new ArrayList<String>();
    private String[][] recipePatterns;
    private Object[][] recipeItems;
    public static boolean hasRpg;
    public static boolean hasShields;
    public static boolean hasRogue;
    public static boolean hasMage;
    private static int uniqueID = 0;
    public static Potion decomposePotion;
    public static Potion masochismPotion;

    public mod_RpgInventory() {
        instance = this;
    }

    public int getUniqueID() {
        return uniqueID++;
    }
    EnumArmorMaterial robes = EnumHelper.addArmorMaterial("magerobes", 20, new int[]{2, 2, 2, 1}, 5);	// use of Magic Tools
    EnumArmorMaterial hides = EnumHelper.addArmorMaterial("hides", 20, new int[]{2, 3, 2, 2}, 5);		// use of Bows
    EnumArmorMaterial armoury = EnumHelper.addArmorMaterial("armoury", 20, new int[]{2, 4, 3, 2}, 5);
    EnumArmorMaterial rogueArmor = EnumHelper.addArmorMaterial("rogue", 20, new int[]{3, 5, 4, 3}, 5);
    EnumArmorMaterial beastMaster = EnumHelper.addArmorMaterial("beast", 20, new int[]{4, 5, 4, 3}, 5);
    EnumToolMaterial clay = EnumHelper.addToolMaterial("claymore", 0, 1024, 5F, 6, 0);
    EnumToolMaterial stone = EnumHelper.addToolMaterial("RageBreaker", 0, 750, 5F, 4, 0);
    public EnumToolMaterial NecroToolMaterial;
    public EnumToolMaterial PalaToolMaterial;
    public static CreativeTabs tab;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        RpgConfig.instance.loadConfig(event.getSuggestedConfigurationFile());
    }

    @Init
    public void load(FMLInitializationEvent event) {
        // NOTHING BEFORE THE GOD DAMN TAB !
        tab = new RpgInventoryTab(CreativeTabs.getNextID(), "RpgTab");


        try {

            Class.forName("RpgPlusPlus.mod_RpgPlus");
            System.out.println("Rpg++ Necromancer and Paladin is installed. Renderers can be Used");
            hasRpg = true;
        } catch (Throwable e) {
            System.out.println("Rpg++ Necromancer and Paladin has not been detected. Renderers for Rpg++ Excluded");
            hasRpg = false;
        }
        //look for Vanilla Shields
        try {
            Class.forName("RpgShields.mod_VanillaShields");
            System.out.println("Rpg++ Vanilla Shields is installed. Renderers can be Used");
            hasShields = true;
        } catch (Throwable e) {
            System.out.println("Rpg++ Vanilla Shields has not been detected. Renderers for Vanilla Shields Excluded");
            hasShields = false;
        }
        try {
            Class.forName("RpgRB.mod_RpgRB");
            System.out.println("Rpg++ Rogue and BeastMaster Installed. Renderers can be Used");
            hasRogue = true;
        } catch (Throwable e) {
            System.out.println("Rpg++ Rogue and BeastMaster not detected. Renderers for Vanilla Shields Excluded");
            hasRogue = false;
        }
        try {
            Class.forName("RpgMageSet.mod_RpgMageSet");
            System.out.println("Rpg++ ArchMage Installed. Renderers can be Used");
            hasMage = true;
        } catch (Throwable e) {
            System.out.println("Rpg++ ArchMage not detected. Renderers Excluded");
            hasMage = false;
        }

        developers.add("Unjustice");
        developers.add("artix_all_mighty");

        forgeBlock = new BlockForge(RpgConfig.instance.forgeblockID, Material.rock).setHardness(5f).setBlockName("MoldForge").setCreativeTab(tab);

        neckgold = new ItemRpgArmor(RpgConfig.instance.neckgoldID, 0, -1, "").setIconIndex(0).setItemName("goldnecklace").setCreativeTab(tab);
        neckdia = new ItemRpgArmor(RpgConfig.instance.neckdiaID, 0, -1, "").setIconIndex(0 + 16).setItemName("neckwithdia").setCreativeTab(tab);
        neckem = new ItemRpgArmor(RpgConfig.instance.neckemID, 0, -1, "").setIconIndex(0 + 32).setItemName("neckwithem").setCreativeTab(tab);
        necklap = new ItemRpgArmor(RpgConfig.instance.necklapID, 0, -1, "").setIconIndex(0 + 48).setItemName("neckwithlap").setCreativeTab(tab);

        ringgold = new ItemRpgArmor(RpgConfig.instance.ringgoldID, 4, -1, "").setIconIndex(1).setItemName("goldring").setCreativeTab(tab);
        ringdia = new ItemRpgArmor(RpgConfig.instance.ringdiaID, 4, -1, "").setIconIndex(1 + 16).setItemName("ringwithdia").setCreativeTab(tab);
        ringem = new ItemRpgArmor(RpgConfig.instance.ringemID, 4, -1, "").setIconIndex(1 + 32).setItemName("ringwithem").setCreativeTab(tab);
        ringlap = new ItemRpgArmor(RpgConfig.instance.ringlapID, 4, -1, "").setIconIndex(1 + 48).setItemName("ringwithlapis").setCreativeTab(tab);

        glovesbutter = new ItemRpgArmor(RpgConfig.instance.glovesbutterID, 3, -1, "").setIconIndex(2).setItemName("goldrrgloves").setCreativeTab(tab);
        glovesdia = new ItemRpgArmor(RpgConfig.instance.glovesdiaID, 3, -1, "").setIconIndex(2 + 16).setItemName("glovesdia").setCreativeTab(tab);
        glovesem = new ItemRpgArmor(RpgConfig.instance.glovesemID, 3, -1, "").setIconIndex(2 + 32).setItemName("glovesem").setCreativeTab(tab);
        gloveslap = new ItemRpgArmor(RpgConfig.instance.gloveslapID, 3, -1, "").setIconIndex(2 + 48).setItemName("gloveslap").setCreativeTab(tab);

        archersShield = new ItemRpgArmor(RpgConfig.instance.archersShieldID, 1, 200, "").setIconIndex(3).setItemName("woodshield").setCreativeTab(tab);
        berserkerShield = new ItemRpgArmor(RpgConfig.instance.berserkerShieldID, 1, 350, "").setIconIndex(4).setItemName("ironshield").setCreativeTab(tab);
        talisman = new ItemRpgArmor(RpgConfig.instance.talismanID, 1, 200, "").setIconIndex(5).setItemName("talisman").setCreativeTab(tab);

        cloak = new ItemRpgArmor(RpgConfig.instance.cloakID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloak").setCreativeTab(tab);
        cloakI = new ItemRpgArmor(RpgConfig.instance.cloakIID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("InvisibilityCloak").setCreativeTab(tab);

        magehood = new BonusArmor(RpgConfig.instance.magehoodID, robes, 4, 0).setItemName("magehoody").setIconIndex(15).setCreativeTab(tab);
        magegown = new BonusArmor(RpgConfig.instance.magegownID, robes, 4, 1).setItemName("magegowny").setIconIndex(31).setCreativeTab(tab);
        magepants = new BonusArmor(RpgConfig.instance.magepantsID, robes, 4, 2).setItemName("magepants").setIconIndex(47).setCreativeTab(tab);
        mageboots = new BonusArmor(RpgConfig.instance.magebootsID, robes, 4, 3).setItemName("mageshoes").setIconIndex(63).setCreativeTab(tab);

        archerhood = new BonusArmor(RpgConfig.instance.archerhoodID, hides, 4, 0).setItemName("archerhoody").setIconIndex(14).setCreativeTab(tab);
        archerchest = new BonusArmor(RpgConfig.instance.archerchestID, hides, 4, 1).setItemName("archerdhide").setIconIndex(30).setCreativeTab(tab);
        archerpants = new BonusArmor(RpgConfig.instance.archerpantsID, hides, 4, 2).setItemName("archerpants").setIconIndex(46).setCreativeTab(tab);
        archerboots = new BonusArmor(RpgConfig.instance.archerbootsID, hides, 4, 3).setItemName("archerboots").setIconIndex(62).setCreativeTab(tab);

        berserkerHood = new BonusArmor(RpgConfig.instance.berserkerHoodID, armoury, 4, 0).setItemName("rambo1").setIconIndex(13).setCreativeTab(tab);
        berserkerChest = new BonusArmor(RpgConfig.instance.berserkerChestID, armoury, 4, 1).setItemName("rambo2").setIconIndex(29).setCreativeTab(tab);
        berserkerLegs = new BonusArmor(RpgConfig.instance.berserkerLegsID, armoury, 4, 2).setItemName("rambo3").setIconIndex(45).setCreativeTab(tab);
        berserkerBoots = new BonusArmor(RpgConfig.instance.berserkerBootsID, armoury, 4, 3).setItemName("rambo4").setIconIndex(61).setCreativeTab(tab);

        claymore = new ItemClaymore(RpgConfig.instance.claymoreID, clay).setFull3D().setMaxDamage(1024).setItemName("Daggerkiller").setIconIndex(20).setCreativeTab(tab);
        wand = new ItemMageWand(RpgConfig.instance.wandID).setFull3D().setMaxDamage(400).setItemName("MagesWand").setIconIndex(21).setCreativeTab(tab);
        elfbow = new ItemArcherBow(RpgConfig.instance.elfbowID).setFull3D().setMaxDamage(350).setItemName("ArcherBow").setIconIndex(22).setCreativeTab(tab);

        animalskin = new ItemRpg(RpgConfig.instance.animalskinID).setItemName("animal skin").setIconIndex(103).setCreativeTab(tab);
        tanHide = new ItemRpg(RpgConfig.instance.tanHideID).setItemName("tanned hide").setIconIndex(103).setCreativeTab(tab);
        magecloth = new ItemRpg(RpgConfig.instance.mageclothID).setItemName("mage cloth").setIconIndex(103).setCreativeTab(tab);

        wizardBook = new ItemRpg(RpgConfig.instance.wizardBookID).setItemName("wizardbook").setIconCoord(11, 11).setCreativeTab(tab);

        hammer = new ItemHammer(RpgConfig.instance.hammerID, stone).setItemName("BerserkerHammer").setMaxDamage(750).setIconIndex(26).setCreativeTab(tab);
        staf = new ItemStaf(RpgConfig.instance.stafID).setItemName("MageStaff").setMaxStackSize(1).setMaxDamage(1500).setIconIndex(37).setCreativeTab(tab);

        rageSeed = new ItemRageFood(RpgConfig.instance.rageSeedID, 0, 0f, false).setAlwaysEdible().setIconCoord(9, 0).setItemName("RageSeed").setMaxStackSize(8).setCreativeTab(tab);

        cloakRed = new ItemRpgArmor(RpgConfig.instance.cloakRedID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakred").setCreativeTab(tab);
        cloakYellow = new ItemRpgArmor(RpgConfig.instance.cloakYellowID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakyellow").setCreativeTab(tab);
        cloakGreen = new ItemRpgArmor(RpgConfig.instance.cloakGreenID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakgreen").setCreativeTab(tab);
        cloakBlue = new ItemRpgArmor(RpgConfig.instance.cloakBlueID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloaksaint").setCreativeTab(tab);
        cloakSub = new ItemRpgArmor(RpgConfig.instance.cloakSubID, 2, -1, "").setFull3D().setIconIndex(3 + 16).setItemName("cloakSub").setCreativeTab(tab);

        colmold = new ItemMold(RpgConfig.instance.colmoldID).setIconIndex(64).setItemName("colmold").setCreativeTab(tab);
        ringmold = new ItemMold(RpgConfig.instance.ringmoldID).setIconIndex(65).setItemName("ringmold").setCreativeTab(tab);
        wantmold = new ItemMold(RpgConfig.instance.wantmoldID).setIconIndex(66).setItemName("wantmold").setCreativeTab(tab);

        crystal = new ItemCrystal(RpgConfig.instance.crystalID, ITEMTYPE.CRYSTAL, -1, "").setIconIndex(52).setItemName("petCrystal").setCreativeTab(tab);
        if (hasRpg == true) {
            EnumArmorMaterial necroArmor = EnumHelper.addArmorMaterial("necro_material", 20, new int[]{2, 5, 1, 1}, 5);	// use of Souls
            EnumArmorMaterial paladin = EnumHelper.addArmorMaterial("pala_material", 20, new int[]{4, 7, 2, 1}, 5);	// use of Steel
            NecroToolMaterial = EnumHelper.addToolMaterial("souls", 0, 1024, 5F, 1, 0);
            PalaToolMaterial = EnumHelper.addToolMaterial("steel", 0, 1024, 5F, 1, 0);

            necroHood = new BonusArmor(RpgConfig.instance.necroHoodID, necroArmor, 4, 0).setIconIndex(12).setItemName("Necro Helmet").setCreativeTab(mod_RpgInventory.tab);
            necroChestplate = new BonusArmor(RpgConfig.instance.necroChestplateID, necroArmor, 4, 1).setIconIndex(12 + 16).setItemName("Necro Chestplate").setCreativeTab(mod_RpgInventory.tab);
            necroLeggings = new BonusArmor(RpgConfig.instance.necroLeggingsID, necroArmor, 4, 2).setIconIndex(12 + 32).setItemName("Necro Leggings").setCreativeTab(mod_RpgInventory.tab);
            necroBoots = new BonusArmor(RpgConfig.instance.necroBootsID, necroArmor, 4, 3).setIconIndex(12 + 48).setItemName("Necro Boots").setCreativeTab(mod_RpgInventory.tab);

            palaHelm = new BonusArmor(RpgConfig.instance.palaHelmID, paladin, 4, 0).setIconIndex(11).setItemName("Pala Helmet").setCreativeTab(mod_RpgInventory.tab);
            palaChest = new BonusArmor(RpgConfig.instance.palaChestID, paladin, 4, 1).setIconIndex(11 + 16).setItemName("Pala Chestplate").setCreativeTab(mod_RpgInventory.tab);
            palaLeggings = new BonusArmor(RpgConfig.instance.palaLeggingsID, paladin, 4, 2).setIconIndex(11 + 32).setItemName("Pala Leggins").setCreativeTab(mod_RpgInventory.tab);
            palaBoots = new BonusArmor(RpgConfig.instance.palaBootsID, paladin, 4, 3).setIconIndex(11 + 48).setItemName("Pala Boots").setCreativeTab(mod_RpgInventory.tab);

            necro_shield = new ItemRpgPlusPlusArmor(RpgConfig.instance.necro_shieldID, 1, 250, "necro").setIconIndex(254).setItemName("Necro Shield").setCreativeTab(tab);
            necro_weapon = new ItemNecroSkull(RpgConfig.instance.necro_weaponID, NecroToolMaterial).setFull3D().setItemName("Necro Staff").setIconIndex(253).setCreativeTab(mod_RpgInventory.tab);

            pala_shield = new ItemRpgPlusPlusArmor(RpgConfig.instance.pala_shieldID, 1, 450, "pala").setIconIndex(255).setItemName("Pala Shield").setCreativeTab(tab);
            pala_weapon = new ItemGrandSword(RpgConfig.instance.pala_weaponID, PalaToolMaterial).setFull3D().setIconIndex(252).setItemName("Pala Sword").setCreativeTab(mod_RpgInventory.tab);

            necro_skin = new ItemMats(RpgConfig.instance.necro_skinID).setIconIndex(103).setItemName("NecroSkin").setCreativeTab(mod_RpgInventory.tab);
            pala_steel = new ItemMats(RpgConfig.instance.pala_steelID).setIconIndex(23).setItemName("PalaSteel").setCreativeTab(mod_RpgInventory.tab);

            LanguageRegistry.addName(necro_shield, "NecroMancer Shield");
            LanguageRegistry.addName(pala_shield, "Paladin Shield");
            LanguageRegistry.addName(necroHood, "NecroMancer Hood");
            LanguageRegistry.addName(necroChestplate, "NecroMancer Vest");
            LanguageRegistry.addName(necroLeggings, "NecroMancer Pants");
            LanguageRegistry.addName(necroBoots, "NecroMancer Boots");
            LanguageRegistry.addName(palaHelm, "Paladin Helmet");
            LanguageRegistry.addName(palaChest, "Paladin Chestplate");
            LanguageRegistry.addName(palaLeggings, "Paladin Leggings");
            LanguageRegistry.addName(palaBoots, "Paladin Boots");
            LanguageRegistry.addName(necro_weapon, "NecroMancer Skull");
            LanguageRegistry.addName(pala_weapon, "Paladin's Pride");
            LanguageRegistry.addName(pala_steel, "Paladin's Steel");
            LanguageRegistry.addName(necro_skin, "Necromancer's Cloth");

        }

        if (hasShields == true) {
            shieldWood = new ItemRpgArmor(RpgConfig.instance.shieldWoodID, 1, 50, "wood").setIconIndex(67).setItemName("vanillaWood").setCreativeTab(tab);
            shieldIron = new ItemRpgArmor(RpgConfig.instance.shieldIronID, 1, 125, "iron").setIconIndex(68).setItemName("vanillaIron").setCreativeTab(tab);
            shieldGold = new ItemRpgArmor(RpgConfig.instance.shieldGoldID, 1, 250, "gold").setIconIndex(69).setItemName("vanillaGold").setCreativeTab(tab);
            shieldDiamond = new ItemRpgArmor(RpgConfig.instance.shieldDiamondID, 1, 500, "diamond").setIconIndex(70).setItemName("vanillaDiamond").setCreativeTab(tab);

            LanguageRegistry.addName(shieldWood, "Wooden Shield");
            LanguageRegistry.addName(shieldIron, "Iron Shield");
            LanguageRegistry.addName(shieldGold, "Golden Shield");
            LanguageRegistry.addName(shieldDiamond, "Diamond Shield");
        }
        if (hasRogue == true) {

            // axe index = 39
            daggers = new ItemRpgArmor(RpgConfig.instance.daggersID, 1, 0, "").setItemName("daggerRogue").setIconCoord(6, 2).setCreativeTab(tab);
            rogueLeather = new ItemRBMats(RpgConfig.instance.rogueLeatherID).setItemName("rogue leather").setIconIndex(103).setCreativeTab(tab);
            beastLeather = new ItemRBMats(RpgConfig.instance.beastLeatherID).setItemName("beast leather").setIconIndex(103).setCreativeTab(tab);
            beastShield = new ItemRpgArmor(RpgConfig.instance.beastShield, 1, 0, "").setItemName("beastShield").setIconCoord(3, 5).setCreativeTab(tab);

            rogueHood = new BonusArmor(RpgConfig.instance.rogueHoodID, rogueArmor, 4, 0).setItemName("rogue1").setIconCoord(12, 4).setCreativeTab(tab);
            rogueChest = new BonusArmor(RpgConfig.instance.rogueChestID, rogueArmor, 4, 1).setItemName("rogue2").setIconCoord(12, 5).setCreativeTab(tab);
            rogueLegs = new BonusArmor(RpgConfig.instance.rogueLegsID, rogueArmor, 4, 2).setItemName("rogue3").setIconCoord(12, 6).setCreativeTab(tab);
            rogueBoots = new BonusArmor(RpgConfig.instance.rogueBootsID, rogueArmor, 4, 3).setItemName("rogue4").setIconCoord(12, 7).setCreativeTab(tab);

            beastHood = new BonusArmor(RpgConfig.instance.beastHoodID, beastMaster, 4, 0).setItemName("beast1").setIconCoord(11, 4).setCreativeTab(tab);
            beastChest = new BonusArmor(RpgConfig.instance.beastChestID, beastMaster, 4, 1).setItemName("beast2").setIconCoord(11, 5).setCreativeTab(tab);
            beastLegs = new BonusArmor(RpgConfig.instance.beastLegsID, beastMaster, 4, 2).setItemName("beast3").setIconCoord(11, 6).setCreativeTab(tab);
            beastBoots = new BonusArmor(RpgConfig.instance.beastBootsID, beastMaster, 4, 3).setItemName("beast4").setIconCoord(11, 7).setCreativeTab(tab);

            whistle = new ItemRBMats2(RpgConfig.instance.whistleID).setIconIndex(40).setItemName("whistle").setCreativeTab(tab);

            LanguageRegistry.addName(daggers, "Rogue Daggers");
            LanguageRegistry.addName(rogueLeather, "Rogue Leather");
            LanguageRegistry.addName(beastLeather, "BeastMaster Leather");
            LanguageRegistry.addName(rogueHood, "Rogue Hood");
            LanguageRegistry.addName(rogueChest, "Rogue Breast Plate");
            LanguageRegistry.addName(rogueLegs, "Rogue Chaps");
            LanguageRegistry.addName(rogueBoots, "Rogue Boots");
            LanguageRegistry.addName(beastHood, "BeastMaster Hood");
            LanguageRegistry.addName(beastChest, "BeastMaster Body Protection");
            LanguageRegistry.addName(beastLegs, "BeastMaster Leg Protection");
            LanguageRegistry.addName(beastBoots, "BeastMaster Shoes");
            LanguageRegistry.addName(whistle, "Pet Whistle");
            LanguageRegistry.addName(whistle, "Pet Whistle");
            LanguageRegistry.addName(beastShield, "BeastMaster Shield");


            GameRegistry.addShapelessRecipe(new ItemStack(whistle), new Object[]{Item.stick, Item.reed, Item.reed});

            recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
            recipeItems = new Object[][]{{rogueLeather, beastLeather}, {rogueHood, beastHood},
                {rogueChest, beastChest}, {rogueLegs, beastLegs}, {rogueBoots, beastBoots}};

            for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
                Object var3 = this.recipeItems[0][var2];

                for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
                    Item var5 = (Item) this.recipeItems[var4 + 1][var2];
                    GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
                }
            }
        }
        if (hasMage == true) {
            fireStaff = new ItemElementalStaff(RpgConfig.instance.fireStaff, 1).setItemName("FireStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(53).setCreativeTab(mod_RpgInventory.tab);
            frostStaff = new ItemElementalStaff(RpgConfig.instance.frostStaff, 2).setItemName("FrostStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(54).setCreativeTab(mod_RpgInventory.tab);
            earthStaff = new ItemElementalStaff(RpgConfig.instance.staffEarth, 3).setItemName("EarthStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(55).setCreativeTab(mod_RpgInventory.tab);
            windStaff = new ItemElementalStaff(RpgConfig.instance.staffWind, 4).setItemName("WindStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(56).setCreativeTab(mod_RpgInventory.tab);
            ultimateStaff = new ItemElementalStaff(RpgConfig.instance.staffUltimate, 5).setItemName("UltimateStaff").setMaxStackSize(1).setMaxDamage(150).setIconIndex(57).setCreativeTab(mod_RpgInventory.tab);
            archBook = new ItemRpgArmor(RpgConfig.instance.archBook, 1, 300, "").setIconCoord(4, 5).setCreativeTab(tab);

            LanguageRegistry.addName(fireStaff, "Fire Staff");
            LanguageRegistry.addName(frostStaff, "Frost Staff");
            LanguageRegistry.addName(earthStaff, "Earth Staff");
            LanguageRegistry.addName(windStaff, "Wind Staff");
            LanguageRegistry.addName(ultimateStaff, "Ultimate Staff");
            LanguageRegistry.addName(archBook, "ArchMage Book");
        }

        proxy.registerRenderInformation();

        addChestLoot(new ItemStack(mod_RpgInventory.colmold), 1, 1, 40, "Necklace Mold");
        addChestLoot(new ItemStack(mod_RpgInventory.ringmold), 1, 1, 30, "Ring Mold");
        addChestLoot(new ItemStack(mod_RpgInventory.wantmold), 1, 1, 40, "Gloves Mold");

        GameRegistry.registerTileEntity(TEMold.class, "temold");

        LanguageRegistry.addName(forgeBlock, "Mold Forge");
        GameRegistry.registerBlock(forgeBlock, "MoldForge");

        LanguageRegistry.addName(cloakRed, "Red Cape");
        LanguageRegistry.addName(cloakYellow, "Yellow Cape");
        LanguageRegistry.addName(cloakGreen, "Green Cape");
        LanguageRegistry.addName(cloakBlue, "Blue Cape");
        LanguageRegistry.addName(cloakSub, "Black Cape");

        LanguageRegistry.addName(neckgold, "Gold Necklace");
        LanguageRegistry.addName(neckdia, "Health Necklace");
        LanguageRegistry.addName(neckem, " Buffed Necklace");
        LanguageRegistry.addName(necklap, " Strength Necklace");

        LanguageRegistry.addName(glovesbutter, "Gold Gloves");
        LanguageRegistry.addName(glovesdia, "Health Gloves");
        LanguageRegistry.addName(glovesem, "Buffed Gloves");
        LanguageRegistry.addName(gloveslap, "Strength Gloves");

        LanguageRegistry.addName(ringgold, "Golden Ring");
        LanguageRegistry.addName(ringdia, "Health Ring");
        LanguageRegistry.addName(ringem, "Buffed Ring");
        LanguageRegistry.addName(ringlap, "Strength Ring");

        LanguageRegistry.addName(archersShield, "Small Archer Shield");
        LanguageRegistry.addName(berserkerShield, "Berserker's Iron Thorn");
        LanguageRegistry.addName(talisman, "Wizard's Talisman");

        LanguageRegistry.addName(cloak, "Cloak");
        LanguageRegistry.addName(cloakI, "Invisibility Cloak");

        LanguageRegistry.addName(magehood, "Mage Hood");
        LanguageRegistry.addName(magegown, "Mage Gown");
        LanguageRegistry.addName(magepants, "Mage Under Gown");
        LanguageRegistry.addName(mageboots, "Mage Shoes");

        LanguageRegistry.addName(archerhood, "Archer Hood");
        LanguageRegistry.addName(archerchest, "Archer Chest");
        LanguageRegistry.addName(archerpants, "Archer Leggings");
        LanguageRegistry.addName(archerboots, "Archer Boots");

        LanguageRegistry.addName(berserkerHood, "Berserker's Head Protection");
        LanguageRegistry.addName(berserkerChest, "Berserker's Body Protection");
        LanguageRegistry.addName(berserkerLegs, "Berserker's Leg Protection");
        LanguageRegistry.addName(berserkerBoots, "Berserker's Feet Protection");

        LanguageRegistry.addName(wand, "Soul Sphere");
        LanguageRegistry.addName(claymore, "Berserker's Claymore");
        LanguageRegistry.addName(elfbow, "Archer's Bow of Birch");

        LanguageRegistry.addName(animalskin, "Animal Skin");
        LanguageRegistry.addName(tanHide, "Tanned Hide");
        LanguageRegistry.addName(magecloth, "Mage Cloth");

        LanguageRegistry.addName(wizardBook, "Wizard's Knowledge, Volume I");
        LanguageRegistry.addName(hammer, "Berserker's Rage Breaker");
        LanguageRegistry.addName(staf, "Lunar Staff");
        LanguageRegistry.addName(rageSeed, "Rage Seeds");

        LanguageRegistry.addName(colmold, "Mold");
        LanguageRegistry.addName(ringmold, "Mold");
        LanguageRegistry.addName(wantmold, "Mold");
        LanguageRegistry.addName(new ItemStack(crystal, 1, 0), "Pet Crystal");
        LanguageRegistry.addName(new ItemStack(crystal, 1, 1), "Boar");
        LanguageRegistry.addName(new ItemStack(crystal, 1, 2), "Spider");
        LanguageRegistry.addName(new ItemStack(crystal, 1, 3), "Bull");

        MinecraftForge.addGrassSeed(new ItemStack(rageSeed, 1), 1);

        //SKINS
        GameRegistry.addRecipe(new ItemStack(animalskin, 1), new Object[]{"WWW", "WLW", "WWW", 'W', new ItemStack(Block.cloth, 1, 12), 'L', Item.leather});
        GameRegistry.addShapelessRecipe(new ItemStack(tanHide, 1), new Object[]{Item.leather, Item.silk, Item.silk, Item.silk, Item.silk});
        GameRegistry.addRecipe(new ItemStack(magecloth, 1), new Object[]{"WWW", "WLW", "WWW", 'W', new ItemStack(Item.dyePowder, 1, 4), 'L', Item.leather});
        //WEAPONRY
        GameRegistry.addRecipe(new ItemStack(elfbow, 1), new Object[]{"EPP", "P S", "PS ", 'E', Item.emerald, 'S', Item.silk, 'P', new ItemStack(Block.wood, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(wand, 1), new Object[]{"GGG", "GLG", "GGG", 'L', Block.blockLapis, 'G', Item.ingotGold});
        GameRegistry.addRecipe(new ItemStack(staf, 1), new Object[]{" ML", " SM", "S  ", 'M', Item.speckledMelon, 'S', Item.stick, 'L', new ItemStack(Item.dyePowder, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(claymore, 1), new Object[]{" S ", " S ", "LIL", 'I', Item.ingotIron, 'S', Block.stone, 'L', Item.leather});
        GameRegistry.addRecipe(new ItemStack(hammer, 1), new Object[]{"SSS", "LI ", "LI ", 'I', Item.ingotIron, 'S', Block.blockSteel, 'L', Item.leather});

        //SHIELDS
        GameRegistry.addRecipe(new ItemStack(archersShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.wood});
        GameRegistry.addRecipe(new ItemStack(berserkerShield, 1), new Object[]{"WWW", "WBW", " W ", 'W', Item.ingotIron, 'B', Block.blockSteel});
        GameRegistry.addRecipe(new ItemStack(talisman, 1), new Object[]{"WWW", "WBW", " W ", 'W', new ItemStack(Item.dyePowder, 1, 4), 'B', Block.blockLapis});

        //CLOAK
        GameRegistry.addRecipe(new ItemStack(cloak, 1), new Object[]{"SS", "II", "II", 'I', Block.cloth, 'S', Item.silk});
        GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.potion, 1, 8206)});
        GameRegistry.addRecipe(new ItemStack(cloakI, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.potion, 1, 8270)});

        GameRegistry.addRecipe(new ItemStack(cloakRed, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(cloakYellow, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 11)});
        GameRegistry.addRecipe(new ItemStack(cloakGreen, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(cloakBlue, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 12)});
        GameRegistry.addRecipe(new ItemStack(cloakSub, 1), new Object[]{"PPP", "PCP", "PPP", 'C', cloak, 'P', new ItemStack(Item.dyePowder, 1, 0)});

        GameRegistry.addRecipe(new ItemStack(forgeBlock, 1), new Object[]{"BBB", "BOB", "BBB", 'B', Block.brick, 'O', Block.obsidian});

        //MageBook
        GameRegistry.addShapelessRecipe(new ItemStack(wizardBook, 1), new Object[]{magecloth, Item.paper, Item.paper, Item.paper});

        //ARMOR
        recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
        recipeItems = new Object[][]{{animalskin, tanHide, magecloth}, {berserkerHood, archerhood, magehood},
            {berserkerChest, archerchest, magegown}, {berserkerLegs, archerpants, magepants},
            {berserkerBoots, archerboots, mageboots}};

        for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
            Object var3 = this.recipeItems[0][var2];

            for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
                Item var5 = (Item) this.recipeItems[var4 + 1][var2];
                GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
            }
        }
        if (hasRpg == true) {
            recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
            recipeItems = new Object[][]{{pala_steel, necro_skin}, {palaHelm, necroHood},
                {palaChest, necroChestplate}, {palaLeggings, necroLeggings},
                {palaBoots, necroBoots}};

            for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
                Object var3 = this.recipeItems[0][var2];

                for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
                    Item var5 = (Item) this.recipeItems[var4 + 1][var2];
                    GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
                }
            }
        }

        if (RpgConfig.instance.render3DClaymore == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.claymore.itemID, (IItemRenderer) new ClaymoreRenderer());
        }
        if (RpgConfig.instance.render3DHammer == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.hammer.itemID, (IItemRenderer) new HammerRender());
        }
        if (RpgConfig.instance.render3DSoulSphere == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.wand.itemID, (IItemRenderer) new SoulSphereRender());
        }
        if (RpgConfig.instance.render3DStaff == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.staf.itemID, (IItemRenderer) new StafRender());
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.frostStaff.itemID, (IItemRenderer) new StafRender());
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.fireStaff.itemID, (IItemRenderer) new StafRender());
            MinecraftForgeClient.registerItemRenderer(earthStaff.itemID, (IItemRenderer) new StafRender());
            MinecraftForgeClient.registerItemRenderer(windStaff.itemID, (IItemRenderer) new StafRender());
            MinecraftForgeClient.registerItemRenderer(ultimateStaff.itemID, (IItemRenderer) new StafRender());
        }
        if (RpgConfig.instance.render3DBow == true) {
            MinecraftForgeClient.registerItemRenderer(mod_RpgInventory.elfbow.itemID, (IItemRenderer) new BowRender());
        }


        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
        GameRegistry.registerPlayerTracker(new PlayerTracker());
        TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
        MinecraftForge.EVENT_BUS.register(new RPGEventHooks());
        EntityRegistry.registerModEntity(EntityHellArrow.class, "hellArrow", getUniqueID(), this, 250, 1, true);


        //hack to increase the number of potion types allowed
        if (Potion.potionTypes.length < 256) {
            Potion[] potionTypes = null;

            for (Field f : Potion.class.getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    if (f.getName().equals("potionTypes") || f.getName().equals("a")) {
                        Field modfield = Field.class.getDeclaredField("modifiers");
                        modfield.setAccessible(true);
                        modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                        potionTypes = (Potion[]) f.get(null);
                        final Potion[] newPotionTypes = new Potion[256];
                        System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                        f.set(null, newPotionTypes);
                    }
                } catch (Exception e) {
                    System.err.println("Severe error, please report this to the mod author:");
                    System.err.println(e);
                }
            }
        }
        //0-32 is used by vanilla
        //Check to make sure this hack wasnt already applied and >32 potions
        //already exist. insert only when null
        for (int pos = 32; pos < Potion.potionTypes.length; pos++) {
            if (Potion.potionTypes[pos] == null) {
                if (decomposePotion == null) {
                    decomposePotion = new DecomposePotion(pos);
                    Potion.potionTypes[pos] = decomposePotion;
                } else if (masochismPotion == null) {
                    masochismPotion = new MasochismPotion(pos);
                    Potion.potionTypes[pos] = masochismPotion;
                } else {
                    break;
                }
            }
        }
        RPGEventHooks.negativeEffects.add(2);
        RPGEventHooks.negativeEffects.add(4);
        RPGEventHooks.negativeEffects.add(9);
        RPGEventHooks.negativeEffects.add(15);
        RPGEventHooks.negativeEffects.add(17);
        RPGEventHooks.negativeEffects.add(18);
        RPGEventHooks.negativeEffects.add(19);
        RPGEventHooks.negativeEffects.add(20);
        RPGEventHooks.negativeEffects.add(decomposePotion.id);

    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent evt) {
        proxy.registerLate();
        //All mods should be initialized now, check what potion effects are installed
        //and list the bad ones for later reference.
    }

    public static class ITEMTYPE {

        public static final int NECKLACE = 0;
        public static final int SHIELD = 1;
        public static final int CLOAK = 2;
        public static final int GLOVES = 3;
        public static final int RING = 4;
        public static final int CRYSTAL = 5;
    }

    public void addChestLoot(ItemStack is, int min, int max, int rarity, String item) {
        System.out.println("Added " + item + " to chests");
        WeightedRandomChestContent chestGen = new WeightedRandomChestContent(is.copy(), min, max, rarity);

        ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
        ChestGenHooks.getInfo("villageBlacksmith").addItem(chestGen);
        ChestGenHooks.getInfo("pyramidJungleChest").addItem(chestGen);
        ChestGenHooks.getInfo("pyramidDesertyChest").addItem(chestGen);
        ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);

    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
        commandManager.registerCommand(new RpgInventory.CommandPanel());
        RpgInventory.CommandPanel.init();
    }
}
