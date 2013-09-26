///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package rpgInventory;
//
//import java.util.HashMap;
//import java.util.LinkedList;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import rpgInventory.item.armor.ItemRpgInvArmor;
//import rpgInventory.utils.AbstractArmor;
//
//
//public class CopyOfEnumRpgClass_backup {
//
//
//	/***/
//	private static LinkedList<String> map = new LinkedList<String>();
//	
//	private static final String none = "none";
//	
//	public static LinkedList getPlayerClasses(EntityPlayer p){
//
//		if(map.size() > 3){
//			map = new LinkedList<String>();
//		}
//		
//		String playerClass = none;
//		String shieldClass = none;
//
//		if (p != null && p.inventory != null && p.inventory.armorInventory != null) {
//			for (ItemStack is : p.inventory.armorInventory) {
//				if (is == null) {
//					//if any armorslots are empty, the class is incomplete, and no further checks needed.
//					playerClass = none;
//					//tmp represents the base class enum, break the for loop instead of return
//					//so we can check for shields
//					break;
//				}
//				else if(is.getItem() instanceof AbstractArmor){
//					if( ((AbstractArmor)is.getItem()).fullEquiped(p, ((AbstractArmor)is.getItem()).mat)){
//						playerClass = ((AbstractArmor)is.getItem()).armorClassName();
//					}else{
//						playerClass = none;
//					}
//				}
//			}
//			PlayerRpgInventory inv = PlayerRpgInventory.get(p); 
//			//This means character has a class enum
//			if (inv != null) {
//				ItemStack shield = inv.getShield();
//				if (shield != null) {
//					if (playerClass != null) {
//						if(shield.getItem() instanceof ItemRpgInvArmor){
//							ItemRpgInvArmor item = (ItemRpgInvArmor)shield.getItem();
//
//							//upgrades class armor to shielded class armor
//							if(!playerClass.equals(none)){
//								if(item.boundArmorClass().equals(playerClass)){
//									shieldClass = item.shieldClass();
//								}
//							}
//							
//							//allows vanilla shields (unbound to any rpg class) to
//							//to be used whenever
//							else{
//								if(item.boundArmorClass().equals(playerClass)){
//									shieldClass = item.shieldClass();
//								}
//							}
//						}
//					}
//				}
//			}else{
//				shieldClass = none;
//			}
//			if (playerClass != null) {
//				map.remove(0);
//				map.set(0, playerClass);
//			}
//			if (shieldClass != null) {
//				map.remove(1);
//				map.set(1, shieldClass);
//			}
//			if (inv.getCrystal() != null) {
//				map.remove(2);
//				map.set(2, "crystal");
//			}
//		}
//		return map;
//	}
//}
