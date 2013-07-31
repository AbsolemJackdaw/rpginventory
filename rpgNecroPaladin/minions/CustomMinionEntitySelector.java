/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgNecroPaladin.minions;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Home
 */
public class CustomMinionEntitySelector implements IEntitySelector {

    EntityPlayer owner;

    public CustomMinionEntitySelector(EntityPlayer owner) {
        this.owner = owner;
    }

    @Override
    public boolean isEntityApplicable(Entity var1) {
        //This class lets the minion know if they should attack the mob they are looking at or not.
        //Never attack the owner, even if he attacks them.
        
        if (var1 != owner) {
            if (var1 instanceof EntityTameable) {
                if (((EntityTameable) var1).getOwner() != owner) {
                    return true;
                } else {
                    return false;
                }
            }
            if (var1 instanceof IMob) {
                return true;
            }
        }
        return false;
    }
}
