/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.richUtil.potions;

import net.minecraft.potion.Potion;

/**
 *
 * @author Home
 */
public class DecomposePotion extends Potion {

    public DecomposePotion(int id) {
        super(id, true, 0x000000);
        //Wither Icon
        this.setIconIndex(3, 1);
        this.setPotionName("Decompose");
    }
    
}
