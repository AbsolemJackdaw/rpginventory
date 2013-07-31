/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgNecroPaladin.minions;

import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Home
 */
public interface IMinion {
    public void Harvest();
    public EntityPlayer getMaster();
    public void setInvulnerable(boolean val);
}
