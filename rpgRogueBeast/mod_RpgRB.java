package rpgRogueBeast;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import rpgInventory.mod_RpgInventory;
import rpgInventory.mod_RpgInventory.ITEMTYPE;
import rpgInventory.config.RpgConfig;
import rpgInventory.item.ItemCandy;
import rpgInventory.item.ItemCrystal;
import rpgRogueBeast.entity.BoarPet;
import rpgRogueBeast.entity.BullPet;
import rpgRogueBeast.entity.EntityPetXP;
import rpgRogueBeast.entity.EntityTeleportStone;
import rpgRogueBeast.entity.SpiderPet;
import rpgRogueBeast.items.ItemBeastAxe;
import rpgRogueBeast.items.ItemBeastMasterArmor;
import rpgRogueBeast.items.ItemRBMats;
import rpgRogueBeast.items.ItemRBMats2;
import rpgRogueBeast.items.ItemRogueArmor;
import rpgRogueBeast.items.ItemRpgInvArmorRB;
import rpgRogueBeast.items.PetExpPotion;
import rpgRogueBeast.packets.RpgRBPacketHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RPGRB", name = "Rogue and BeastMaster Patch", version = "RpgInv8.4", dependencies="required-after:rpginventorymod")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"RpgRBPacket"}, packetHandler = RpgRBPacketHandler.class))
public class mod_RpgRB {

	@SidedProxy(serverSide = "rpgRogueBeast.RBCommonProxy", clientSide = "rpgRogueBeast.RBClientProxy")
	public static RBCommonProxy proxy;
	public static CreativeTabs tab;

	public static String CLASSBEASTMASTER = "beastMaster";
	public static String CLASSBEASTMASTERSHIELDED = "shieldedBeastMaster";

	public static String CLASSROGUE = "rogue";
	public static String CLASSROGUESHIELDED = "Ninja";

	private String[][] recipePatterns;
	private Object[][] recipeItems;

	public static Item  
	beastShield,
	daggers,beastAxe,
	beastHood, beastChest, beastLegs, beastBoots,
	rogueHood, rogueChest, rogueLegs, rogueBoots,
	rogueLeather, beastLeather,
	crystal, whistle,petCandy, tangledBrench, PetXPBottle;

	public  final static EnumArmorMaterial rogueArmor = EnumHelper.addArmorMaterial("rogue", 20, new int[]{3, 5, 4, 3}, 5);
	public  final static EnumArmorMaterial beastMaster = EnumHelper.addArmorMaterial("beastmaster", 20, new int[]{4, 5, 4, 3}, 5);

	EnumToolMaterial BeastAxeMaterial = EnumHelper.addToolMaterial("BeastAxe", 4, 1280, 6.0F, 3, 22);

	@EventHandler
	public void load(FMLInitializationEvent event) {

		FMLLog.info("Rpg++ Rogue and BeastMaster Installed. Renderers can be Used");


		tab = new RBTab(CreativeTabs.getNextID(), "RBTab");

		daggers = new ItemRpgInvArmorRB(RpgConfig.instance.daggersID, 1, 800, "", "").setUnlocalizedName("dagger");
		beastAxe = new ItemBeastAxe(RpgConfig.instance.beastAxe, BeastAxeMaterial).setFull3D().setUnlocalizedName("forestAxe");

		rogueLeather = new ItemRBMats(RpgConfig.instance.rogueLeatherID).setUnlocalizedName("r.leather");
		beastLeather = new ItemRBMats(RpgConfig.instance.beastLeatherID).setUnlocalizedName("b.leather");
		beastShield = new ItemRpgInvArmorRB(RpgConfig.instance.beastShield, 1, 150, "", "subaraki:jewels/lion.png").setUnlocalizedName("shieldBeastMaster");

		rogueHood = new ItemRogueArmor(RpgConfig.instance.rogueHoodID, rogueArmor, 4, 0).setUnlocalizedName("rogue1");
		rogueChest = new ItemRogueArmor(RpgConfig.instance.rogueChestID, rogueArmor, 4, 1).setUnlocalizedName("rogue2");
		rogueLegs = new ItemRogueArmor(RpgConfig.instance.rogueLegsID, rogueArmor, 4, 2).setUnlocalizedName("rogue3");
		rogueBoots = new ItemRogueArmor(RpgConfig.instance.rogueBootsID, rogueArmor, 4, 3).setUnlocalizedName("rogue4");

		beastHood = new ItemBeastMasterArmor(RpgConfig.instance.beastHoodID, beastMaster, 4, 0).setUnlocalizedName("beast1");
		beastChest = new ItemBeastMasterArmor(RpgConfig.instance.beastChestID, beastMaster, 4, 1).setUnlocalizedName("beast2");
		beastLegs = new ItemBeastMasterArmor(RpgConfig.instance.beastLegsID, beastMaster, 4, 2).setUnlocalizedName("beast3");
		beastBoots = new ItemBeastMasterArmor(RpgConfig.instance.beastBootsID, beastMaster, 4, 3).setUnlocalizedName("beast4");

		whistle = new ItemRBMats2(RpgConfig.instance.whistleID).setUnlocalizedName("whistle");

		petCandy = new ItemCandy(RpgConfig.instance.candy).setUnlocalizedName("petCandy");
		tangledBrench = new ItemCandy(RpgConfig.instance.brench).setUnlocalizedName("tangledBrench");
		PetXPBottle = new PetExpPotion(RpgConfig.instance.petxppotion).setUnlocalizedName("PetXPBottle");

		crystal = new ItemCrystal(RpgConfig.instance.crystalID, ITEMTYPE.CRYSTAL, -1, "").setUnlocalizedName("petCrystal");

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
		LanguageRegistry.addName(beastShield, "BeastMaster Shield");
		LanguageRegistry.addName(beastAxe, "BeastMaster Forest Axe");
		LanguageRegistry.addName(petCandy, "Rare Pet Candy");
		LanguageRegistry.addName(tangledBrench, "Tangled Brench");
		LanguageRegistry.addName(PetXPBottle, "Bottle 'O Pet");

		LanguageRegistry.addName(new ItemStack(crystal, 1, 0), "Pet Crystal");
		LanguageRegistry.addName(new ItemStack(crystal, 1, 1), "Boar");
		LanguageRegistry.addName(new ItemStack(crystal, 1, 2), "Spider");
		LanguageRegistry.addName(new ItemStack(crystal, 1, 3), "Bull");

		GameRegistry.addRecipe(new ItemStack(daggers,1), new Object [] {" ei","eie","se ", 'i', Item.ingotIron, 'e',Item.spiderEye, 's',Item.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(whistle), new Object[]{Item.stick, Item.reed, Item.reed});
		GameRegistry.addRecipe(new ItemStack(beastLeather), new Object[]{"LLL", "LVL", "LLL", 'L', Block.leaves, 'V', Item.leather});
		GameRegistry.addRecipe(new ItemStack(rogueLeather), new Object[]{"DSD", "SLS", "DSD", 'S', Item.silk, 'L', Item.leather, 'D', new ItemStack(Item.dyePowder, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(beastShield), new Object[]{"III", "IDI", " I ", 'I', beastLeather, 'D', Block.wood});
		GameRegistry.addRecipe(new ItemStack(beastAxe), new Object[]{" IW", " SI", "S  ", 'S', tangledBrench, 'I', Block.blockIron, 'W', Block.wood});
		GameRegistry.addShapelessRecipe(new ItemStack(tangledBrench), new Object[]{Item.stick, Item.stick, Item.silk, Item.silk, Item.silk, Item.silk});

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
		mod_RpgInventory.instance.addChestLoot(new ItemStack(PetXPBottle), 1, 1, 40, "Pet Drinks");
		mod_RpgInventory.instance.addCandyChestLoot(new ItemStack(petCandy), 1, 6, 20, "Easter Egg");

		daggers.setCreativeTab(tab);
		beastAxe.setCreativeTab(tab);

		rogueLeather.setCreativeTab(tab);
		beastShield.setCreativeTab(tab);
		beastLeather.setCreativeTab(tab);

		rogueHood.setCreativeTab(tab);
		rogueChest.setCreativeTab(tab);
		rogueLegs.setCreativeTab(tab);
		rogueBoots.setCreativeTab(tab);

		beastHood.setCreativeTab(tab);
		beastChest.setCreativeTab(tab);
		beastLegs.setCreativeTab(tab);
		beastBoots.setCreativeTab(tab);

		petCandy.setCreativeTab(tab);
		tangledBrench.setCreativeTab(tab);
		PetXPBottle.setCreativeTab(tab);

		crystal.setCreativeTab(tab);

		whistle.setCreativeTab(tab);

		proxy.registerRendering();

		EntityRegistry.registerGlobalEntityID(BullPet.class, "BullPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SpiderPet.class, "SpiderPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(BoarPet.class, "BoarPet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityTeleportStone.class, "TelePortStone", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(BullPet.class, "BullPet", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(SpiderPet.class, "SpiderPet", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(BoarPet.class, "BoarPet", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityPetXP.class, "PetXP", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityTeleportStone.class, "TelePortStone", mod_RpgInventory.instance.getUniqueID(), this, 80, 1, true);


	}
}
