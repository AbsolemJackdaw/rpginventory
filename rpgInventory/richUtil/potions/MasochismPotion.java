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
public class MasochismPotion extends Potion{
    public boolean regen2 = false;
    public MasochismPotion(int id){
        super(id, false, 0x440404);
        this.setIconIndex(7, 0);
        this.setPotionName("Masochism");
        this.setEffectiveness(0.25D);
    }
}
