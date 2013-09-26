///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package rpgInventory;
//
//import java.util.EnumSet;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import rpgInventory.item.armor.ItemClassArmor;
//import rpgMage.mod_RpgMageSet;
//import rpgNecroPaladin.mod_RpgPlus;
//import rpgRogueBeast.mod_RpgRB;
//
///**
// *
// * @author Home
// */
//public enum EnumRpgClass {
//
//	ARCHER, MAGE, BERSERKER, NECRO, PALADIN, ARCHMAGE,
//	SHIELDEDARCHER, SHIELDEDMAGE, SHIELDEDBERSERKER, SHIELDEDNECRO, SHIELDEDPALADIN, SHIELDEDARCHMAGE,
//	WOOD, IRON, GOLD, DIAMOND, CRYSTAL,
//	ROGUE, BEASTMASTER, LION, NINJA;
//
//	public static EnumSet<EnumRpgClass> getPlayerClasses(EntityPlayer p) {
//		EnumRpgClass classenum = null;
//		EnumRpgClass shieldenum = null;
//
//		EnumSet<EnumRpgClass> classEnum = EnumSet.noneOf(EnumRpgClass.class);
//		if (p != null && p.inventory != null && p.inventory.armorInventory != null) {
//			for (ItemStack is : p.inventory.armorInventory) {
//				if (is == null) {
//					//if any armorslots are empty, the class is incomplete, and no further checks needed.
//					classenum = null;
//					//tmp represents the base class enum, break the for loop instead of return
//					//so we can check for shields
//					break;
//				}
//				if (is.getItem() instanceof ItemClassArmor) {
//					if (is.getItem().equals(mod_RpgInventory.archerhood) || is.getItem().equals(mod_RpgInventory.archerchest)
//							|| is.getItem().equals(mod_RpgInventory.archerpants) || is.getItem().equals(mod_RpgInventory.archerboots)) {
//						if (classenum == null) {
//							classenum = ARCHER;
//						}
//						//This means it has armor pieces from other classes.
//						if (classenum != ARCHER) {
//							//tmp represents the base class enum, break the for loop instead of return
//							//so we can check for shields
//							classenum = null;
//							break;
//						}
//					} else if (is.getItem().equals(mod_RpgInventory.magehood) || is.getItem().equals(mod_RpgInventory.magegown)
//							|| is.getItem().equals(mod_RpgInventory.magepants) || is.getItem().equals(mod_RpgInventory.mageboots)) {
//						if (classenum == null) {
//							classenum = MAGE;
//						}
//						//This means it has armor pieces from other classes.
//						if (classenum != MAGE) {
//							//tmp represents the base class enum, break the for loop instead of return
//							//so we can check for shields
//							classenum = null;
//							break;
//						}
//					} else if (is.getItem().equals(mod_RpgInventory.berserkerHood) || is.getItem().equals(mod_RpgInventory.berserkerChest)
//							|| is.getItem().equals(mod_RpgInventory.berserkerLegs) || is.getItem().equals(mod_RpgInventory.berserkerBoots)) {
//						if (classenum == null) {
//							classenum = BERSERKER;
//						}
//						//This means it has armor pieces from other classes.
//						if (classenum != BERSERKER) {
//							//tmp represents the base class enum, break the for loop instead of return
//							//so we can check for shields
//							classenum = null;
//							break;
//						}
//					}
//					else if( mod_RpgInventory.hasRpg) {
//
//						if (is.getItem().equals(mod_RpgPlus.palaHelm) || is.getItem().equals(mod_RpgPlus.palaChest)
//								|| is.getItem().equals(mod_RpgPlus.palaLeggings) || is.getItem().equals(mod_RpgPlus.palaBoots)) {
//							if (classenum == null) {
//								classenum = PALADIN;
//							}
//							//This means it has armor pieces from other classes.
//							if (classenum != PALADIN) {
//								//tmp represents the base class enum, break the for loop instead of return
//								//so we can check for shields
//								classenum = null;
//								break;
//							}
//						} 
//						else if (is.getItem().equals(mod_RpgPlus.necroHood) || is.getItem().equals(mod_RpgPlus.necroChestplate)
//								|| is.getItem().equals(mod_RpgPlus.necroLeggings) || is.getItem().equals(mod_RpgPlus.necroBoots)) {
//							if (classenum == null) {
//								classenum = NECRO;
//							}
//							//This means it has armor pieces from other classes.
//							if (classenum != NECRO) {
//								//tmp represents the base class enum, break the for loop instead of return
//								//so we can check for shields
//								classenum = null;
//								break;
//							}
//						}
//					}
//
//					else if (mod_RpgInventory.hasMage){ 
//						if (is.getItem().equals(mod_RpgMageSet.archmageHood) || is.getItem().equals(mod_RpgMageSet.archmageChest)
//								|| is.getItem().equals(mod_RpgMageSet.archmageLegs) || is.getItem().equals(mod_RpgMageSet.archMageBoots)) {
//							if (classenum == null) {
//								classenum = ARCHMAGE;
//							}
//							if (classenum != ARCHMAGE) {
//								classenum = null;
//								break;
//							}
//						}
//					} else if(mod_RpgInventory.hasRogue) {
//
//						if (is.getItem().itemID == mod_RpgRB.beastHood.itemID || is.getItem().equals(mod_RpgRB.beastChest)
//								|| is.getItem().equals(mod_RpgRB.beastLegs) || is.getItem().equals(mod_RpgRB.beastBoots)) {
//							if (classenum == null) {
//								classenum = BEASTMASTER;
//							}
//							if (classenum != BEASTMASTER) {
//								classenum = null;
//								break;
//							}
//						} else if (is.getItem().equals(mod_RpgRB.rogueHood) || is.getItem().equals(mod_RpgRB.rogueChest)
//								|| is.getItem().equals(mod_RpgRB.rogueLegs) || is.getItem().equals(mod_RpgRB.rogueBoots)) {
//							if (classenum == null) {
//								classenum = ROGUE;
//							}
//							if (classenum != ROGUE) {
//								classenum = null;
//								break;
//							}
//						}
//					} else {
//						//tmp represents the base class enum, break the for loop instead of return
//						//so we can check for shields
//						classenum = null;
//						break;
//					}
//				}
//			}
//			PlayerRpgInventory inv = PlayerRpgInventory.get(p); 
//			//This means character has a class enum
//			if (inv != null) {
//				ItemStack shield = inv.getShield();
//				if (shield != null) {
//					if (classenum != null) {
//						if (shield.getItem().equals(mod_RpgInventory.archerShield) && classenum == ARCHER) {
//							shieldenum = SHIELDEDARCHER;
//						} else if (shield.getItem().equals(mod_RpgInventory.talisman) && classenum == MAGE) {
//							shieldenum = SHIELDEDMAGE;
//						} else if (shield.getItem().equals(mod_RpgInventory.berserkerShield) && classenum == BERSERKER) {
//							shieldenum = SHIELDEDBERSERKER;
//						} 
//						else if(mod_RpgInventory.hasRpg) 
//							if (shield.getItem().equals(mod_RpgPlus.pala_shield) && classenum == PALADIN) {
//								shieldenum = SHIELDEDPALADIN;
//							} else if (shield.getItem().equals(mod_RpgPlus.necro_shield) && classenum == NECRO) {
//								shieldenum = SHIELDEDNECRO;
//							}
//					}
//					else if (shield.getItem().equals(mod_RpgInventory.archBook) && classenum == ARCHMAGE) {
//						shieldenum = SHIELDEDARCHMAGE;
//					} else if (shield.getItem().equals(mod_RpgInventory.beastShield) && classenum == BEASTMASTER) {
//						shieldenum = LION;
//					} else if (shield.getItem().equals(mod_RpgInventory.daggers) && classenum == ROGUE) {
//						shieldenum = NINJA;
//					}
//				}
//				//So classes can use the vanilla armor too if they want
//				if (shieldenum == null) {
//					if (shield.getItem().equals(mod_RpgInventory.shieldWood)) {
//						shieldenum = WOOD;
//					} else if (shield.getItem().equals(mod_RpgInventory.shieldIron)) {
//						shieldenum = IRON;
//					} else if (shield.getItem().equals(mod_RpgInventory.shieldGold)) {
//						shieldenum = GOLD;
//					} else if (shield.getItem().equals(mod_RpgInventory.shieldDiamond)) {
//						shieldenum = DIAMOND;
//					}
//				}
//			}
//		}
//		if (classenum != null) {
//			classEnum.add(classenum);
//		}
//		if (shieldenum != null) {
//			classEnum.add(shieldenum);
//		}
//		if (inv.getCrystal() != null) {
//			classEnum.add(CRYSTAL);
//		}
//	}
//	return classEnum;
//}
//}
