/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Home
 */
public interface IPet {
    public final int LEVELID = 20;
    public final int NAME = 21;
    public final int HP = 22;
    public final int SADDLE = 23;

    public static HashMap<String, IPet> playersWithActivePets = new HashMap();
    public boolean isDead();
    /**
     *
     * @return Returns the pet type(item damage) for this pet.
     */
    public int getType();
    /**
     *
     * @return The current level of this pet.
     */
    public int getLevel();

    /**@return returns rather the pet is saddled or not */
    public boolean getSaddled();
    
    /**@return self explanatory */
    public void setSaddled(boolean set);
    
    /**
    *
    * @return Returns the current health. not max.
    */
   public int getHP();
    /**
     *
     * @param amount The amount of xp to give to this pet.
     * 
     */
    public void giveXP(int amount);
    
    /**
     *
     * @return The total xp this mob has.
     */
    public int getTotalXP();
    
    /**
     * <em>Deprecated!</em> Use the other version, the new method will automatically
     * set the correct damage values and item type information for simplicity.
     * 
     * <s>Writes the pet's information to the provided ItemStack</s>
     * Functions exactly like writePetToItemStack()
     * 
     * @param is The itemStack to save the pet to.
     * @return The ItemStack with the pet written to it.
     */
    @Deprecated
    public ItemStack writePetToItemStack(ItemStack is);
    public ItemStack writePetToItemStack();
    /**
     *
     * @param name The name to attempt to set as the pets name.
     * 
     */
    public void setName(String name);
    
    /**
     *
     * @return How large this pet should be. This returns the scale factor.
     */
    public float getSize();
    
}