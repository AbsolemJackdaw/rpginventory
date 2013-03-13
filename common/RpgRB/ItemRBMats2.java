package RpgRB;

import RpgInventory.IPet;
import RpgInventory.gui.inventory.RpgInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;

public class ItemRBMats2 extends Item {

    public ItemRBMats2(int par1) {
        super(par1);
    }

    public ItemStack onItemRightClick(ItemStack whistle, World world, EntityPlayer player) {
        if (!world.isRemote && player.ridingEntity == null) {
            try {
                RpgInv rpginv = mod_RpgInventory.proxy.getInventory(player.username);
                ItemStack stack = rpginv.getCrystal();
                if (stack != null) {
                    //Pet is in the world.
                    if (IPet.playersWithActivePets.containsKey(player.username)) {
                        EntityLiving e = (EntityLiving) IPet.playersWithActivePets.get(player.username).getPet();
                        IPet.playersWithActivePets.remove(player.username);
                        if (e != null && !((EntityLiving) e).isDead) {
                            stack = ((IPet) e).writePetToItemStack();
                            rpginv.setInventorySlotContents(6, stack);
                            ((EntityLiving) e).setDead();
                            System.out.println("Put Away");
                            return whistle;
                        }
                    //pet is not in the world
                    } else {
                        switch (stack.getItemDamage()) {
                            case 1:
                                BoarPet Boar = new BoarPet(world, player, stack);
                                Boar.setPosition(player.posX, player.posY, player.posZ);
                                Boar.setTamed(true);
                                if (Boar.getHealth() <= 0) {
                                    Boar.setEntityHealth(1);
                                }
                                world.spawnEntityInWorld(Boar);
                                break;
                            case 2:
                                SpiderPet spider = new SpiderPet(world, player, stack);
                                spider.setPosition(player.posX, player.posY, player.posZ);
                                spider.setOwner(player.username);
                                spider.setTamed(true);
                                if (spider.getHealth() <= 0) {
                                    spider.setEntityHealth(1);
                                }
                                world.spawnEntityInWorld(spider);
                                break;
                            case 3:
                                BullPet bull = new BullPet(world, player, stack);
                                bull.setPosition(player.posX, player.posY, player.posZ);
                                bull.setTamed(true);
                                if (bull.getHealth() <= 0) {
                                    bull.setEntityHealth(1);
                                }
                                world.spawnEntityInWorld(bull);
                                break;
                        }
                        rpginv.setInventorySlotContents(6, stack);
                    }
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return whistle;
    }

    public String getTextureFile() {
        return "/subaraki/RPGinventoryTM.png";
    }
}
