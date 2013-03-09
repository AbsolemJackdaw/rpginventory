package RpgInventory;

import RpgInventory.item.ItemMats;
import RpgInventory.item.ItemRpg;
import RpgInventory.item.armor.BonusArmor;
import RpgInventory.item.armor.ItemRpgArmor;
import RpgInventory.item.armor.ItemRpgPlusPlusArmor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;

public class CommandPanel extends CommandBase {

    public static Map<String, List<ItemStack>> sets = new HashMap();

    public static void init() {
        sets.put("jewels", new ArrayList());
        sets.put("mats", new ArrayList());
        sets.put("armor", new ArrayList());
        sets.put("weapons", new ArrayList());
        sets.put("mage", new ArrayList());
        sets.put("berserker", new ArrayList());
        sets.put("archer", new ArrayList());
        sets.put("necro", new ArrayList());
        sets.put("paladin", new ArrayList());
        sets.put("vanillashields", new ArrayList());

        for (Item item : Item.itemsList) {
            if (item != null) {
                if (item instanceof ItemRpgArmor || item instanceof ItemRpgPlusPlusArmor) {
                    sets.get("jewels").add(new ItemStack(item));
                }
            }
            if (item instanceof ItemRpg || item instanceof ItemMats || item instanceof ItemMold) {
                sets.get("mats").add(new ItemStack(item, item.getItemStackLimit()));
            }
            if (item instanceof BonusArmor) {
                sets.get("armor").add(new ItemStack(item, item.getItemStackLimit()));
            }
        }
        sets.get("mats").add(new ItemStack(Block.blockGold, 64));
        sets.get("mats").add(new ItemStack(mod_RpgInventory.forgeBlock, 64));
        sets.get("mats").add(new ItemStack(Item.ingotGold, 64));
        sets.get("mats").add(new ItemStack(Item.blazeRod, 64));
        sets.get("mats").add(new ItemStack(Item.dyePowder, 64, 4));
        sets.get("mats").add(new ItemStack(Item.emerald, 64));
        sets.get("mats").add(new ItemStack(Item.diamond, 64));

        sets.get("weapons").add(new ItemStack(mod_RpgInventory.hammer));
        sets.get("weapons").add(new ItemStack(mod_RpgInventory.claymore));
        sets.get("weapons").add(new ItemStack(mod_RpgInventory.staf));
        sets.get("weapons").add(new ItemStack(mod_RpgInventory.wand));
        sets.get("weapons").add(new ItemStack(mod_RpgInventory.elfbow));
        sets.get("weapons").add(new ItemStack(mod_RpgInventory.necro_weapon));
        sets.get("weapons").add(new ItemStack(mod_RpgInventory.pala_weapon));

        sets.get("mage").add(new ItemStack(mod_RpgInventory.staf));
        sets.get("mage").add(new ItemStack(mod_RpgInventory.wand));
        sets.get("mage").add(new ItemStack(mod_RpgInventory.talisman));
        sets.get("mage").add(new ItemStack(mod_RpgInventory.magehood));
        sets.get("mage").add(new ItemStack(mod_RpgInventory.magegown));
        sets.get("mage").add(new ItemStack(mod_RpgInventory.magepants));
        sets.get("mage").add(new ItemStack(mod_RpgInventory.mageboots));

        sets.get("berserker").add(new ItemStack(mod_RpgInventory.claymore));
        sets.get("berserker").add(new ItemStack(mod_RpgInventory.hammer));
        sets.get("berserker").add(new ItemStack(mod_RpgInventory.berserkerShield));
        sets.get("berserker").add(new ItemStack(mod_RpgInventory.berserkerHood));
        sets.get("berserker").add(new ItemStack(mod_RpgInventory.berserkerChest));
        sets.get("berserker").add(new ItemStack(mod_RpgInventory.berserkerLegs));
        sets.get("berserker").add(new ItemStack(mod_RpgInventory.berserkerBoots));

        sets.get("archer").add(new ItemStack(mod_RpgInventory.elfbow));
        sets.get("archer").add(new ItemStack(mod_RpgInventory.archersShield));
        sets.get("archer").add(new ItemStack(mod_RpgInventory.archerhood));
        sets.get("archer").add(new ItemStack(mod_RpgInventory.archerchest));
        sets.get("archer").add(new ItemStack(mod_RpgInventory.archerpants));
        sets.get("archer").add(new ItemStack(mod_RpgInventory.archerboots));

        sets.get("necro").add(new ItemStack(mod_RpgInventory.necro_weapon));
        sets.get("necro").add(new ItemStack(mod_RpgInventory.necro_shield));
        sets.get("necro").add(new ItemStack(mod_RpgInventory.necroHood));
        sets.get("necro").add(new ItemStack(mod_RpgInventory.necroChestplate));
        sets.get("necro").add(new ItemStack(mod_RpgInventory.necroLeggings));
        sets.get("necro").add(new ItemStack(mod_RpgInventory.necroBoots));

        sets.get("paladin").add(new ItemStack(mod_RpgInventory.pala_weapon));
        sets.get("paladin").add(new ItemStack(mod_RpgInventory.pala_shield));
        sets.get("paladin").add(new ItemStack(mod_RpgInventory.palaHelm));
        sets.get("paladin").add(new ItemStack(mod_RpgInventory.palaChest));
        sets.get("paladin").add(new ItemStack(mod_RpgInventory.palaLeggings));
        sets.get("paladin").add(new ItemStack(mod_RpgInventory.palaBoots));

        sets.get("vanillashields").add(new ItemStack(mod_RpgInventory.shieldWood));
        sets.get("vanillashields").add(new ItemStack(mod_RpgInventory.shieldGold));
        sets.get("vanillashields").add(new ItemStack(mod_RpgInventory.shieldIron));
        sets.get("vanillashields").add(new ItemStack(mod_RpgInventory.shieldDiamond));
    }

    @Override
    public String getCommandName() {
        // TODO Auto-generated method stub
        return "rpginv";
    }

    @Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] args) {
        List<String> returnList = new ArrayList();
        if (args == null){
            returnList.add("dev");
        }else if (args.length == 1) {
            if(args[0].trim().length() == 0){
                returnList.add("dev");
            }else if("dev".startsWith(args[0].toLowerCase().trim())){
                returnList.add("dev");
            }
        }else if (args.length == 2 && args[0].toLowerCase().equals("dev")) {
            for(String s: sets.keySet()){
                if(args[1].trim().length() == 0){
                    returnList.add(s);
                }else if(s.startsWith(args[1].toLowerCase().trim())){
                    returnList.add(s);
                }
            }
        }
        return returnList;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        if (args.length > 0) {
            if (args[0].matches("dev")) {
                if (mod_RpgInventory.developers.contains(sender.getCommandSenderName().toLowerCase())) {
                    if (args.length >= 2) {
                        if (sets.containsKey(args[1])) {
                            EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(sender.getCommandSenderName());
                            for (ItemStack is : sets.get(args[1])) {
                                player.inventory.addItemStackToInventory(is);
                            }
                        }
                        if (args[1].matches("pet"))
                        {
                            if (args[2].matches("level"))
                            {
                            	int level = parseIntWithMin(sender, args[3], 1);
                            	EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(sender.getCommandSenderName());
                            	NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
                            	if (nbt.hasKey("RPGPetInfo")) {
                            	NBTTagCompound nbt2 = nbt.getCompoundTag("RPGPetInfo");
                            	nbt2.setInteger("XpLevel", nbt2.getInteger("XpLevel") + level);
                            	nbt.setCompoundTag("RPGPetInfo", nbt2);
                            	nbt.setInteger("PetLevel", nbt.getInteger("PetLevel") + level);
                            	player.getCurrentEquippedItem().setTagCompound(nbt);
                            	} else {
                            		sender.sendChatToPlayer("Sorry Master, I am afraid this crystal is empty...");
                            	}
                            }	
                        }
                    } else {
                        sender.sendChatToPlayer("What do you wish to do master?");
                    }
                } else {
                    sender.sendChatToPlayer("You do not have permission to use this command.");
                }
                if (args.length >= 2) {
                    //int i = parseIntWithMin(sender, args[1], 1);
                    //stats.setStrengthPoints((EntityPlayer)sender, i);
                }
            } else {
                sender.sendChatToPlayer("Looking for something here?");
            }
        } else {
            sender.sendChatToPlayer("Looking for something here?");
        }

    }
}