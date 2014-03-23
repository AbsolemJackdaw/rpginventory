/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgRogueBeast.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgRogueBeast.entity.EntityPetXPBottle;

/**
 * 
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class PetExpPotion extends ItemExpBottle {

	public PetExpPotion() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
				0.4F / ((itemRand.nextFloat() * 0.4F) + 0.8F));

		if (!par2World.isRemote) {
			par2World.spawnEntityInWorld(new EntityPetXPBottle(par2World,
					par3EntityPlayer));
		}

		return par1ItemStack;
	}

	@Override
	public Item setTextureName(String s) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().indexOf(".") + 1);
		this.iconString = "rpginventorymod:" + itemName;
		return this;
	}
}
