//package rpgInventory.handlers;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.minecraft.block.Block;
//import net.minecraft.command.CommandBase;
//import net.minecraft.command.ICommandSender;
//import net.minecraft.command.NumberInvalidException;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.util.ChatComponentText;
//import rpgInventory.mod_RpgInventory;
//import rpgInventory.item.ItemMats;
//import rpgInventory.item.ItemMold;
//import rpgInventory.item.ItemRpg;
//import rpgInventory.item.armor.ItemRpgInvArmor;
//import rpgInventory.utils.AbstractArmor;
//
//public class CommandPanel extends CommandBase {
//
//	public static Map<String, List<ItemStack>> sets = new HashMap();
//
//	public static void init() {
//		sets.put("jewels", new ArrayList());
//		sets.put("mats", new ArrayList());
//		sets.put("armor", new ArrayList());
//		sets.put("weapons", new ArrayList());
//		sets.put("mage", new ArrayList());
//		sets.put("berserker", new ArrayList());
//		sets.put("archer", new ArrayList());
//		sets.put("necro", new ArrayList());
//		sets.put("paladin", new ArrayList());
//		sets.put("vanillashields", new ArrayList());
//		sets.put("archmage", new ArrayList());
//		sets.put("beast", new ArrayList());
//		sets.put("rogue", new ArrayList());
//
////		for (Item item : Item.itemList) {
////			if (item != null) {
////				if (item instanceof ItemRpgInvArmor) {
////					sets.get("jewels").add(new ItemStack(item));
////				}
////			}
////			if ((item instanceof ItemRpg) || (item instanceof ItemMats)
////					|| (item instanceof ItemMold)) {
////				sets.get("mats").add(
////						new ItemStack(item, item.getItemStackLimit()));
////			}
////			if (item instanceof AbstractArmor) {
////				sets.get("armor").add(
////						new ItemStack(item, item.getItemStackLimit()));
////			}
////		}
//		// sets.get("mats").add(new ItemStack(Blocks.blockGold, 64));
//		// sets.get("mats").add(new ItemStack(mod_RpgInventory.forgeBlock, 64));
//		// sets.get("mats").add(new ItemStack(Items.ingotGold, 64));
//		// sets.get("mats").add(new ItemStack(Items.blazeRod, 64));
//		// sets.get("mats").add(new ItemStack(Items.dyePowder, 64, 4));
//		// sets.get("mats").add(new ItemStack(Items.emerald, 64));
//		// sets.get("mats").add(new ItemStack(Items.diamond, 64));
//
//		sets.get("weapons").add(new ItemStack(mod_RpgInventory.hammer));
//		sets.get("weapons").add(new ItemStack(mod_RpgInventory.claymore));
//		sets.get("weapons").add(new ItemStack(mod_RpgInventory.staf));
//		sets.get("weapons").add(new ItemStack(mod_RpgInventory.wand));
//		sets.get("weapons").add(new ItemStack(mod_RpgInventory.elfbow));
//
//		// if(mod_RpgInventory.hasRpg)
//		// {
//		// try {
//		// sets.get("weapons").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necro_weapon));
//		// sets.get("weapons").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.pala_weapon));
//		//
//		// sets.get("necro").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necro_weapon));
//		// sets.get("necro").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necro_shield));
//		// sets.get("necro").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necroHood));
//		// sets.get("necro").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necroChestplate));
//		// sets.get("necro").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necroLeggings));
//		// sets.get("necro").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.necroBoots));
//		//
//		// sets.get("paladin").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.pala_weapon));
//		// sets.get("paladin").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.pala_shield));
//		// sets.get("paladin").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.palaHelm));
//		// sets.get("paladin").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.palaChest));
//		// sets.get("paladin").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.palaLeggings));
//		// sets.get("paladin").add(new
//		// ItemStack(rpgNecroPaladin.mod_RpgPlus.palaBoots));
//		// } catch (Exception e) {
//		// // TODO: handle exception
//		// }
//		//
//		// }
//		// if(mod_RpgInventory.hasMage)
//		// {
//		// try {
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.archBook));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.archMageBoots));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.archmageChest));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.archmageHood));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.archmageLegs));
//		//
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.fireStaff));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.frostStaff));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.earthStaff));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.windStaff));
//		// sets.get("archmage").add(new
//		// ItemStack(rpgMage.mod_RpgMageSet.ultimateStaff));
//		// } catch (Exception e) {
//		// // TODO: handle exception
//		// }
//		//
//		// }
//		// if(mod_RpgInventory.hasRogue)
//		// {
//		// try {
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.beastShield));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.beastAxe));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.beastBoots));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.beastLegs));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.beastChest));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.beastHood));
//		//
//		// sets.get("beast").add(new ItemStack(rpgRogueBeast.mod_RpgRB.crystal,
//		// 1, 1));
//		// sets.get("beast").add(new ItemStack(rpgRogueBeast.mod_RpgRB.crystal,
//		// 1, 2));
//		// sets.get("beast").add(new ItemStack(rpgRogueBeast.mod_RpgRB.crystal,
//		// 1, 3));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.whistle));
//		// sets.get("beast").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.petCandy));
//		//
//		// sets.get("rogue").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.rogueBoots));
//		// sets.get("rogue").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.rogueLegs));
//		// sets.get("rogue").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.rogueChest));
//		// sets.get("rogue").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.rogueHood));
//		// sets.get("rogue").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.daggers));
//		// sets.get("rogue").add(new
//		// ItemStack(rpgRogueBeast.mod_RpgRB.daggers));
//		// } catch (Exception e) {
//		// // TODO: handle exception
//		// }
//		//
//		// }
//		// if(mod_RpgInventory.hasShields)
//		// {
//		// try {
//		// sets.get("vanillashields").add(new
//		// ItemStack(rpgVanillaShields.mod_VanillaShields.shieldWood));
//		// sets.get("vanillashields").add(new
//		// ItemStack(rpgVanillaShields.mod_VanillaShields.shieldGold));
//		// sets.get("vanillashields").add(new
//		// ItemStack(rpgVanillaShields.mod_VanillaShields.shieldIron));
//		// sets.get("vanillashields").add(new
//		// ItemStack(rpgVanillaShields.mod_VanillaShields.shieldDiamond));
//		//
//		// } catch (Exception e) {
//		// // TODO: handle exception
//		// }
//		//
//		// }
//
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.staf));
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.wand));
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.talisman));
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.magehood));
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.magegown));
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.magepants));
//		sets.get("mage").add(new ItemStack(mod_RpgInventory.mageboots));
//
//		sets.get("berserker").add(new ItemStack(mod_RpgInventory.claymore));
//		sets.get("berserker").add(new ItemStack(mod_RpgInventory.hammer));
//		sets.get("berserker").add(
//				new ItemStack(mod_RpgInventory.berserkerShield));
//		sets.get("berserker")
//				.add(new ItemStack(mod_RpgInventory.berserkerHood));
//		sets.get("berserker").add(
//				new ItemStack(mod_RpgInventory.berserkerChest));
//		sets.get("berserker")
//				.add(new ItemStack(mod_RpgInventory.berserkerLegs));
//		sets.get("berserker").add(
//				new ItemStack(mod_RpgInventory.berserkerBoots));
//
//		sets.get("archer").add(new ItemStack(mod_RpgInventory.elfbow));
//		sets.get("archer").add(new ItemStack(mod_RpgInventory.archerShield));
//		sets.get("archer").add(new ItemStack(mod_RpgInventory.archerhood));
//		sets.get("archer").add(new ItemStack(mod_RpgInventory.archerchest));
//		sets.get("archer").add(new ItemStack(mod_RpgInventory.archerpants));
//		sets.get("archer").add(new ItemStack(mod_RpgInventory.archerboots));
//
//	}
//
//	@Override
//	public List addTabCompletionOptions(ICommandSender par1ICommandSender,
//			String[] args) {
//		List<String> returnList = new ArrayList();
//		if (args == null) {
//			returnList.add("dev");
//		} else if (args.length == 1) {
//			if (args[0].trim().length() == 0) {
//				returnList.add("dev");
//			} else if ("dev".startsWith(args[0].toLowerCase().trim())) {
//				returnList.add("dev");
//			}
//		} else if ((args.length == 2) && args[0].toLowerCase().equals("dev")) {
//			for (String s : sets.keySet()) {
//				if (args[1].trim().length() == 0) {
//					returnList.add(s);
//				} else if (s.startsWith(args[1].toLowerCase().trim())) {
//					returnList.add(s);
//				}
//			}
//		}
//		return returnList;
//	}
//
//	@Override
//	public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
//		return mod_RpgInventory.developers
//				.contains(((EntityPlayer) commandSender).getDisplayName()
//						.toLowerCase());
//	}
//
//	@Override
//	public int compareTo(Object arg0) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public String getCommandName() {
//		// TODO Auto-generated method stub
//		return "rpginv";
//	}
//
//	@Override
//	public String getCommandUsage(ICommandSender par1ICommandSender) {
//		return "Dev Only";
//	}
//
//	@Override
//	public int getRequiredPermissionLevel() {
//		return 0;
//	}
//
//	@Override
//	public void processCommand(ICommandSender sender, String[] args) {
//		if (args.length > 0) {
//			if (args[0].matches("dev")) {
//				if (mod_RpgInventory.developers.contains(sender
//						.getCommandSenderName().toLowerCase())) {
//					if (args.length >= 2) {
//						if (sets.containsKey(args[1])) {
//							EntityPlayer player = MinecraftServer
//									.getServer()
//									.getConfigurationManager()
//									.getPlayerForUsername(
//											sender.getCommandSenderName());
//							for (ItemStack is : sets.get(args[1])) {
//								// player.sendChatToPlayer(player.worldObj.isRemote?"Client":"Server");
//								// player.inventory.addItemStackToInventory(is);
//								ItemStack item = is.copy();
//								EntityItem drop = player.dropItem(item.getItem(), item.stackSize);
//								drop.delayBeforeCanPickup = 0;
//							}
//						}
//						if (args[1].matches("pet")) {
//							if (args[2].matches("level")) {
//								int level = parseIntWithMin(sender, args[3], 1);
//								EntityPlayer player = MinecraftServer
//										.getServer()
//										.getConfigurationManager()
//										.getPlayerForUsername(
//												sender.getCommandSenderName());
//								NBTTagCompound nbt = player
//										.getCurrentEquippedItem()
//										.getTagCompound();
//								if (nbt.hasKey("RPGPetInfo")) {
//									NBTTagCompound nbt2 = nbt
//											.getCompoundTag("RPGPetInfo");
//									nbt2.setInteger("XpLevel",
//											nbt2.getInteger("XpLevel") + level);
//									nbt.setTag("RPGPetInfo", nbt2);
//									nbt.setInteger("PetLevel",
//											nbt.getInteger("PetLevel") + level);
//									player.getCurrentEquippedItem()
//											.setTagCompound(nbt);
//								} else {
//									((EntityPlayer) sender)
//											.addChatMessage(new ChatComponentText("Sorry Master, I am afraid this crystal is empty..."));
//								}
//							}
//							if (args[2].matches("name")) {
//
//							}
//							if (args[2].matches("heal")) {
//								EntityPlayer player = MinecraftServer
//										.getServer()
//										.getConfigurationManager()
//										.getPlayerForUsername(
//												sender.getCommandSenderName());
//								NBTTagCompound nbt = player
//										.getCurrentEquippedItem()
//										.getTagCompound();
//								if (nbt.hasKey("RPGPetInfo")) {
//									nbt.setFloat("PetPrevHealth",
//											nbt.getFloat("PetHealth"));
//									player.getCurrentEquippedItem()
//											.setTagCompound(nbt);
//								} else {
//									((EntityPlayer) sender)
//											.addChatMessage(new ChatComponentText("Sorry Master, I am afraid this crystal is empty..."));
//								}
//							}
//						}
//						// if (args[1].matches("activepet")){
//						// if (args[2].matches("level")){
//						// EntityPlayer player = (EntityPlayer)sender;
//						// if(rpgRogueBeast.entity.IPet.playersWithActivePets.containsKey(player.username)){
//						// rpgRogueBeast.entity.IPet.playersWithActivePets.get(player.username).getPet().addExperienceLevel(Integer.valueOf(args[3]));
//						// }
//						// }else if(args[2].matches("name")){
//						// EntityPlayer player = (EntityPlayer)sender;
//						// if(rpgRogueBeast.entity.IPet.playersWithActivePets.containsKey(player.username)){
//						// rpgRogueBeast.entity.IPet.playersWithActivePets.get(player.username).getPet().setName(args[3]);
//						// }
//						// }else if(args[2].matches("saddled")){
//						// EntityPlayer player = (EntityPlayer)sender;
//						// if(rpgRogueBeast.entity.IPet.playersWithActivePets.containsKey(player.username)){
//						// rpgRogueBeast.entity.IPet.playersWithActivePets.get(player.username).getPet().setSaddled(Boolean.valueOf(args[3]));
//						// }
//						// }
//						// }
//
//						if (args[1].matches("get")) {
//							// System.out.println(args);
//							int id = 1;
//							int count = 1;
//							int meta = 0;
//							if (Item.itemsList[id] == null) {
//								throw new NumberInvalidException(
//										"commands.give.notFound",
//										new Object[] { Integer.valueOf(id) });
//							} else {
//								if (args[2] != null) {
//									id = parseIntWithMin(sender, args[2], 1);
//								}
//								if (args[3] != null) {
//									count = parseIntBounded(sender, args[3], 1,
//											64);
//								}
//								if (args[4] != null) {
//									meta = parseInt(sender, args[4]);
//								}
//								ItemStack item = new ItemStack(id, count, meta);
//								EntityPlayer player = MinecraftServer
//										.getServer()
//										.getConfigurationManager()
//										.getPlayerForUsername(
//												sender.getCommandSenderName());
//								// player.inventory.addItemStackToInventory(item);
//								EntityItem drop = player.dropItem(item.getItem(), item.stackSize);
//								drop.delayBeforeCanPickup = 0;
//							}
//						}
//					} else {
//						((EntityPlayer) sender)
//								.addChatMessage(new ChatComponentText("What do you wish to do master?"));
//					}
//				} else {
//					((EntityPlayer) sender)
//							.addChatMessage(new ChatComponentText("You do not have permission to use this command."));
//				}
//				if (args.length >= 2) {
//					// int i = parseIntWithMin(sender, args[1], 1);
//					// stats.setStrengthPoints((EntityPlayer)sender, i);
//				}
//			} else {
//				((EntityPlayer) sender)
//						.addChatMessage(new ChatComponentText("Looking for something here?"));
//			}
//		} else {
//			((EntityPlayer) sender)
//					.addChatMessage(new ChatComponentText("Looking for something here?"));
//		}
//	}
// }
