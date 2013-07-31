/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.PotionEffects;

import rpgNecroPaladin.minions.IMinion;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

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
