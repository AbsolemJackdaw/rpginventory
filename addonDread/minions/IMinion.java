/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addonDread.minions;

import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Home
 */
public interface IMinion {
	public EntityPlayer getMaster();

	public void Harvest();

	public void setInvulnerable(boolean val);
}
