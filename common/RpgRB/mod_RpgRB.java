package RpgRB;

import RpgInventory.Configuration.RpgConfig;
import RpgInventory.EntityPetXP;
import RpgInventory.ItemCandy;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgInventory.mod_RpgInventory;
import RpgInventory.weapons.bow.BowRender;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import RpgRB.weapons.axe.AxeRender;
import RpgRB.weapons.axe.ItemBeastAxe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.EnumHelper;

@Mod(modid = "RPGRB", name = "Rogue and BeastMaster Patch", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class))
public class mod_RpgRB {

    @SidedProxy(serverSide = "RpgRB.RBCommonProxy", clientSide = "RpgRB.RBClientProxy")
    public static RBCommonProxy proxy;
//	@SidedProxy(serverSide = "RpgPlusPlus.CommonProxyRpgplus", clientSide = "RpgPlusPlus.ClientProxyRpgPlus")
//	public static CommonProxyRpgplus proxy;
    // Armor Materials
    private static int uniqueLocalID = 0;

    public int getNextUniqueID() {
        return uniqueLocalID++;
    }

    @Init
    public void load(FMLInitializationEvent event) {
        EnumArmorMaterial beastMaster = EnumHelper.addArmorMaterial("beast", 20, new int[]{4, 5, 4, 3}, 5);
        EnumArmorMaterial rogueArmor = EnumHelper.addArmorMaterial("rogue", 20, new int[]{3, 5, 4, 3}, 5);
        mod_RpgInventory.crystal = new ItemCrystal(RpgConfig.instance.crystalID, mod_RpgInventory.ITEMTYPE.CRYSTAL, -1, "").setIconIndex(52).setItemName("petCrystal").setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.daggers = new ItemRpgArmor(RpgConfig.instance.daggersID, 1, 0, "").setItemName("daggerRogue").setIconCoord(6, 2).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.beastAxe = new ItemBeastAxe(RpgConfig.instance.beastAxe).setFull3D().setIconIndex(39).setItemName("beastmasteraxe").setCreativeTab(mod_RpgInventory.tab);

        mod_RpgInventory.rogueLeather = new ItemRBMats(RpgConfig.instance.rogueLeatherID).setItemName("rogue leather").setIconIndex(103).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.beastLeather = new ItemRBMats(RpgConfig.instance.beastLeatherID).setItemName("beast leather").setIconIndex(103).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.beastShield = new ItemRpgArmor(RpgConfig.instance.beastShield, 1, 0, "").setItemName("beastShield").setIconCoord(3, 5).setCreativeTab(mod_RpgInventory.tab);

        mod_RpgInventory.rogueHood = new BonusArmor(RpgConfig.instance.rogueHoodID, rogueArmor, 4, 0).setItemName("rogue1").setIconCoord(12, 4).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.rogueChest = new BonusArmor(RpgConfig.instance.rogueChestID, rogueArmor, 4, 1).setItemName("rogue2").setIconCoord(12, 5).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.rogueLegs = new BonusArmor(RpgConfig.instance.rogueLegsID, rogueArmor, 4, 2).setItemName("rogue3").setIconCoord(12, 6).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.rogueBoots = new BonusArmor(RpgConfig.instance.rogueBootsID, rogueArmor, 4, 3).setItemName("rogue4").setIconCoord(12, 7).setCreativeTab(mod_RpgInventory.tab);

        mod_RpgInventory.beastHood = new BonusArmor(RpgConfig.instance.beastHoodID, beastMaster, 4, 0).setItemName("beast1").setIconCoord(11, 4).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.beastChest = new BonusArmor(RpgConfig.instance.beastChestID, beastMaster, 4, 1).setItemName("beast2").setIconCoord(11, 5).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.beastLegs = new BonusArmor(RpgConfig.instance.beastLegsID, beastMaster, 4, 2).setItemName("beast3").setIconCoord(11, 6).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.beastBoots = new BonusArmor(RpgConfig.instance.beastBootsID, beastMaster, 4, 3).setItemName("beast4").setIconCoord(11, 7).setCreativeTab(mod_RpgInventory.tab);

        mod_RpgInventory.whistle = new ItemRBMats2(RpgConfig.instance.whistleID).setIconIndex(40).setItemName("whistle").setCreativeTab(mod_RpgInventory.tab);

        mod_RpgInventory.petCandy = new ItemCandy(RpgConfig.instance.candy).setItemName("RarePetCandy").setIconCoord(5, 5).setCreativeTab(mod_RpgInventory.tab);
        mod_RpgInventory.tangledBrench = new ItemCandy(RpgConfig.instance.brench).setItemName("tangledBrench").setIconCoord(6, 5).setCreativeTab(mod_RpgInventory.tab);
        
        GameRegistry.addShapelessRecipe(new ItemStack(mod_RpgInventory.whistle), new Object[]{Item.stick, Item.reed, Item.reed});
        GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.beastLeather), new Object[]{"LLL", "LVL", "LLL", 'L', Block.leaves, 'V', Item.leather});
        GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.rogueLeather), new Object[]{"DSD", "SLS", "DSD", 'S', Item.silk, 'L', Item.leather, 'D', new ItemStack(Item.dyePowder, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.beastShield), new Object[]{"III", "IDI", " I ", 'I', mod_RpgInventory.beastLeather, 'D', Block.wood});
        GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.beastAxe), new Object[]{" IW", " SI", "S  ", 'S', mod_RpgInventory.tangledBrench, 'I', Block.blockSteel, 'W', Block.wood});
        GameRegistry.addShapelessRecipe(new ItemStack(mod_RpgInventory.tangledBrench), new Object[]{Item.stick, Item.stick, Item.silk, Item.silk, Item.silk, Item.silk});
        GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.daggers, 1), new Object[]{"  i", " ie", "se ", 'i', Item.ingotIron, 'e', Item.spiderEye, 's', Item.stick});
        GameRegistry.addRecipe(new ItemStack(mod_RpgInventory.crystal, 1, 0), new Object[]{" G ", "GDG", " G ", 'G', Block.glass, 'D', Block.blockDiamond});
        
        mod_RpgInventory.recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
        mod_RpgInventory.recipeItems = new Object[][]{{mod_RpgInventory.rogueLeather, mod_RpgInventory.beastLeather}, {mod_RpgInventory.rogueHood, mod_RpgInventory.beastHood},
            {mod_RpgInventory.rogueChest, mod_RpgInventory.beastChest}, {mod_RpgInventory.rogueLegs, mod_RpgInventory.beastLegs}, {mod_RpgInventory.rogueBoots, mod_RpgInventory.beastBoots}};
        LanguageRegistry.addName(mod_RpgInventory.daggers, "Rogue Daggers");
        LanguageRegistry.addName(mod_RpgInventory.rogueLeather, "Rogue Leather");
        LanguageRegistry.addName(mod_RpgInventory.beastLeather, "BeastMaster Leather");
        LanguageRegistry.addName(mod_RpgInventory.rogueHood, "Rogue Hood");
        LanguageRegistry.addName(mod_RpgInventory.rogueChest, "Rogue Breast Plate");
        LanguageRegistry.addName(mod_RpgInventory.rogueLegs, "Rogue Chaps");
        LanguageRegistry.addName(mod_RpgInventory.rogueBoots, "Rogue Boots");
        LanguageRegistry.addName(mod_RpgInventory.beastHood, "BeastMaster Hood");
        LanguageRegistry.addName(mod_RpgInventory.beastChest, "BeastMaster Body Protection");
        LanguageRegistry.addName(mod_RpgInventory.beastLegs, "BeastMaster Leg Protection");
        LanguageRegistry.addName(mod_RpgInventory.beastBoots, "BeastMaster Shoes");
        LanguageRegistry.addName(mod_RpgInventory.whistle, "Pet Whistle");
        LanguageRegistry.addName(mod_RpgInventory.whistle, "Pet Whistle");
        LanguageRegistry.addName(mod_RpgInventory.beastShield, "BeastMaster Shield");
        LanguageRegistry.addName(mod_RpgInventory.beastAxe, "BeastMaster Forest Axe");
        LanguageRegistry.addName(mod_RpgInventory.petCandy, "Rare Pet Candy");
        LanguageRegistry.addName(mod_RpgInventory.tangledBrench, "Tangled Brench");
        LanguageRegistry.addName(new ItemStack(mod_RpgInventory.crystal, 1, 0), "Pet Crystal");
        LanguageRegistry.addName(new ItemStack(mod_RpgInventory.crystal, 1, 1), "Boar");
        LanguageRegistry.addName(new ItemStack(mod_RpgInventory.crystal, 1, 2), "Spider");
        LanguageRegistry.addName(new ItemStack(mod_RpgInventory.crystal, 1, 3), "Bull");

        for (int var2 = 0; var2 < mod_RpgInventory.recipeItems[0].length; ++var2) {
            Object var3 = mod_RpgInventory.recipeItems[0][var2];

            for (int var4 = 0; var4 < mod_RpgInventory.recipeItems.length - 1; ++var4) {
                Item var5 = (Item) mod_RpgInventory.recipeItems[var4 + 1][var2];
                GameRegistry.addRecipe(new ItemStack(var5), new Object[]{mod_RpgInventory.recipePatterns[var4], 'X', var3});
            }
        }

        EntityRegistry.registerGlobalEntityID(BullPet.class, "BullPet", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(SpiderPet.class, "SpiderPet", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(BoarPet.class, "BoarPet", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(BullPet.class, "BullPet", getNextUniqueID(), this, 100, 2, true);
        EntityRegistry.registerModEntity(SpiderPet.class, "SpiderPet", getNextUniqueID(), this, 100, 2, true);
        EntityRegistry.registerModEntity(BoarPet.class, "BoarPet", getNextUniqueID(), this, 100, 2, true);
        EntityRegistry.registerModEntity(EntityPetXP.class, "PetXP", getNextUniqueID(), this, 100, 2, true);

        mod_RpgInventory.addCandyChestLoot(new ItemStack(mod_RpgInventory.petCandy), 1, 6, 20, "Easter Egg");

        proxy.registerRendering();
    }
}
