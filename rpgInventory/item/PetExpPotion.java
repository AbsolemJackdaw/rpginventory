/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgInventory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.entity.EntityPetXPBottle;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class PetExpPotion extends ItemExpBottle {

    public PetExpPotion(int par1) {
        super(par1);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par3EntityPlayer.capabilities.isCreativeMode) {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote) {
            par2World.spawnEntityInWorld(new EntityPetXPBottle(par2World, par3EntityPlayer));
        }

        return par1ItemStack;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon("RPGInventoryMod:" + itemName);
    }
}
