/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonMasters.entity;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * 
 * @author Richard Smith <rich1051414@gmail.com>
 */
public interface IPet {

	/**
	 * New helper to ensure we are always retrieving the correct pet instance.
	 */
	public class PetID {

		private final int DIM, EID;

		public PetID(int dimension, int entityID) {
			this.DIM = dimension;
			this.EID = entityID;
		}

		public IPet getPet() {
			try {
				World world = MinecraftServer.getServer()
						.worldServerForDimension(DIM);
				if (world == null) {
					return null;
				}
				Entity test = world.getEntityByID(EID);
				if ((test == null) || !(test instanceof IPet)) {
					return null;
				}
				return (IPet) test;
			} catch (Throwable ex) {
				return null;
			}
		}
	}

	public final int LEVELID = 20;
	public final int NAME = 21;
	// public final int HP = 22;
	public final int SADDLE = 23;
	public final int TOTALXP = 24;
	public final int NEXTLEVEL = 25;

	public static HashMap<String, PetID> playersWithActivePets = new HashMap();

	/**
	 * 
	 * @return Returns the pet type(item damage) for this pet.
	 */
	public void addExperienceLevel(int numLevels);

	/**
	 * 
	 * @return Returns the current health. not max.
	 */
	public float getHP();

	/**
	 * 
	 * @return The current level of this pet.
	 */
	public int getLevel();

	/**
	 * 
	 * @return How large this pet should be. This returns the scale factor.
	 */
	public float getPetSize();

	/**
	 * @return returns rather the pet is saddled or not
	 */
	public boolean getSaddled();

	/**
	 * 
	 * @return The total xp this mob has.
	 */
	public int getTotalXP();

	public int getType();

	/**
	 * 
	 * @param amount
	 *            The amount of xp to give to this pet.
	 * 
	 */
	public void giveXP(int amount);

	public boolean isDead();

	public void setLevel(int level);

	/**
	 * 
	 * @param name
	 *            The name to attempt to set as the pets name.
	 * 
	 */
	public void setName(String name);

	/**
	 * @return self explanatory
	 */
	public void setSaddled(boolean set);

	public ItemStack writePetToItemStack();

	/**
	 * <em>Deprecated!</em> Use the other version, the new method will
	 * automatically set the correct damage values and item type information for
	 * simplicity.
	 * 
	 * <s>Writes the pet's information to the provided ItemStack</s> Functions
	 * exactly like writePetToItemStack()
	 * 
	 * @param is
	 *            The itemStack to save the pet to.
	 * @return The ItemStack with the pet written to it.
	 */
	@Deprecated
	public ItemStack writePetToItemStack(ItemStack is);
}
