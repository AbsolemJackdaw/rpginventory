package rpgInventory.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpgInventory.block.te.MoldRecipes;
import rpgInventory.block.te.TEMold;
import rpgInventory.block.te.slot.GoldBlockSlot;
import rpgInventory.block.te.slot.SlotMineral;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.handlers.ServerTickHandler;

public class RpgUtility {

	/**Use registerSpecialAbility to fill this list*/
	public static List<ISpecialAbility> allAbilities = new ArrayList();


	/**add a block that can be used to be molten in the mold*/
	public static void addCatalistBlock(Block b){
		GoldBlockSlot.addCatalist(Item.getItemFromBlock(b));
	}

	/**add an item that can be used to be molten in the mold*/
	public static void addCatalistBlock(Item i){
		GoldBlockSlot.addCatalist(i);
	}

	/**add a block that can be fused to form a jewel */
	public static void addForgeMoldMineral(Block b){
		SlotMineral.addAllowedItem(Item.getItemFromBlock(b));
	}

	/**add an item that can be fused to form a jewel */
	public static void addForgeMoldMineral(Item i){
		SlotMineral.addAllowedItem(i);
	}

	/**
	 * mold : extend itemmold to make it working
	 * mineral : any new can be registered with addForgeMoldMineral
	 * catalist : block that will be molten : any new can be registered with addCatalistBlock
	 * result : the resulting item after all melting is done
	 */
	public static void addForgeMoldRecipe(Item mold, Item mineral, Block catalist, ItemStack result){
		MoldRecipes.addRecipe(mold, mineral, result, catalist );
	}

	/**ItemStack : item or block to burn
	 * int time : how long it burns
	 * */
	public static void addFuel(ItemStack is, int time){
		if(is != null) {
			if(is.getItem() != null){
				if(is.getItem() instanceof Item) {
					TEMold.addFuelItem(is.getItem(), time);
				}
				if(Block.getBlockFromItem(is.getItem())!= null) {
					TEMold.addFuelBlock(Block.getBlockFromItem(is.getItem()), time);
				}
			}
		}
	}

	@Deprecated
	/**extend ItemMold*/
	public static void addMold(Item i){
		System.out.println("[MESSAGE]~ Extend your item with ItemMold so it can be recognized by the Mold Furnace");
	}

	@Deprecated
	public static boolean canSpecial(EntityPlayer p, Item specialWeapon){
		ItemStack stack = p.getCurrentEquippedItem();
		if(stack != null){
			Item i = stack.getItem();
			if(ISpecialAbility.abilityMap.containsKey(i) && i.equals(specialWeapon)) {
				return true;
			}
		}
		return false;
	}

	/**use this to check if the player can launch the special attack.*/
	//	public static boolean canSpecial(Item specialWeapon){
	//		return canSpecial(null, specialWeapon);
	//	}

	public static PlayerRpgInventory getInventory(EntityPlayer p){
		return PlayerRpgInventory.get(p);
	}

	/**Register the weapon you want to use your special ability with here. If you don't, your ISpecalAbility will not be fired*/
	public static void registerAbilityWeapon(Item item){
		if(item == null) {
			return;
		}
		ISpecialAbility.abilityMap.put(item, 0);
	}

	/**Use this method if you want to register more then one weapon
	 * Item : item to be registered to use the special ability with
	 * packetId : used to switch between packets.
	 * example :
	 * switch(packetId)
	 * case 0 : PacketWeapon1();
	 * case 1 : PacketWeapon2();
	 * */
	public static void registerAbilityWeapon(Item item, int packetId){
		if(item == null) {
			return;
		}
		if(ISpecialAbility.abilityMap.containsKey(item)) {
			return;
		}
		ISpecialAbility.abilityMap.put(item, packetId);
	}


	/**Register a class that implements ISpecialAbility. Registering that class here will allow you to hook up to the "F" special ability key*/
	public static void registerSpecialAbility(ISpecialAbility keyhandler) {
		if(!allAbilities.contains(keyhandler))
			allAbilities.add(keyhandler);
	}

	/**Method that spawns in particles in a certain radius of the player
	 * 
	 * @param p EntityPlayer the player to spawn particles from
	 * @param radius the radius in blocks
	 * @param particle the particle to be rendered
	 * */
	public static void spawnParticle(EntityPlayer p, int radius, String particle, int velocitySpeed, int yOffset){
		int x = (int)p.posX;
		int y = (int)p.posY;
		int z = (int)p.posZ;

		int X = radius, Z = radius;
		int x2=0, z2=0, dx = 0, dz = -1;
		int t = Math.max(X,Z);
		int maxI = t*t;
		boolean flag = false;

		for (int i=0; i < maxI; i++){

			if (((-X/2) <= x2) && (x2 <= (X/2)) && ((-Z/2) <= z2) && (z2 <= (Z/2))) {
				p.worldObj.spawnParticle(particle, x+x2 - 1, p.posY-yOffset, z+z2-1, (double)((double)(x2/(double)velocitySpeed)), 0.0D, (double)((double)z2/(double)velocitySpeed));
				flag = true;
				//break;
			}

			if( (x2 == z2) || ((x2 < 0) && (x2 == -z2)) || ((x2 > 0) && (x2 == (1-z2)))) {
				t=dx; dx=-dz; dz=t;
			}
			x2+=dx; z2+=dz;
		}		if(ServerTickHandler.globalCooldownMap.get(p.getDisplayName()) / 20 <= 0){
		}
	}
}
