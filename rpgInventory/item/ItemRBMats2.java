package rpgInventory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rpgInventory.EnumRpgClass;
import rpgInventory.IPet;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import rpgRogueBeast.entity.BoarPet;
import rpgRogueBeast.entity.BullPet;
import rpgRogueBeast.entity.SpiderPet;

public class ItemRBMats2 extends Item {

    public ItemRBMats2(int par1) {
        super(par1);
    }

    public ItemStack onItemRightClick(ItemStack whistle, World world, EntityPlayer player) {
        if (EnumRpgClass.getPlayerClasses(player).contains(EnumRpgClass.BEASTMASTER)) {
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
                                    Boar.setPosition(player.posX, player.posY + 0.5F, player.posZ);
                                    Boar.setTamed(true);
                                    try {
                                        Boar.setName(stack.stackTagCompound.getString("PetName"));
                                        Boar.setLevel(stack.stackTagCompound.getInteger("PetLevel"));
                                        Boar.setEntityHealth(stack.stackTagCompound.getFloat("PetHealth"));
                                        if (Boar.getHealth() <= 0) {
                                            Boar.setEntityHealth(1);
                                        }
                                    } catch (Throwable ex) {
                                    }
                                    world.spawnEntityInWorld(Boar);
                                    break;
                                case 2:
                                    SpiderPet spider = new SpiderPet(world, player, stack);
                                    spider.setPosition(player.posX, player.posY + 0.5F, player.posZ);
                                    spider.setOwner(player.username);
                                    spider.setTamed(true);
                                    try {
                                        spider.setName(stack.stackTagCompound.getString("PetName"));
                                        spider.setLevel(stack.stackTagCompound.getInteger("PetLevel"));
                                        spider.setEntityHealth(stack.stackTagCompound.getFloat("PetHealth"));
                                        if (spider.getHealth() <= 0) {
                                            spider.setEntityHealth(1);
                                        }
                                    } catch (Throwable ex) {
                                    }
                                    world.spawnEntityInWorld(spider);
                                    break;
                                case 3:
                                    BullPet bull = new BullPet(world, player, stack);
                                    bull.setPosition(player.posX, player.posY + 0.5F, player.posZ);
                                    bull.setTamed(true);
                                    try {
                                        bull.setName(stack.stackTagCompound.getString("PetName"));
                                        bull.setLevel(stack.stackTagCompound.getInteger("PetLevel"));
                                        bull.setEntityHealth(stack.stackTagCompound.getFloat("PetHealth"));
                                        if (bull.getHealth() <= 0) {
                                            bull.setEntityHealth(1);
                                        }
                                    } catch (Throwable ex) {
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
        }
        return whistle;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
        this.itemIcon = par1IconRegister.registerIcon("rpginventorymod:" + itemName);
    }
}
