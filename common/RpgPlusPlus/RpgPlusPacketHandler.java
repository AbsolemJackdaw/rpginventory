/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RpgPlusPlus;

import RpgInventory.CommonTickHandler;
import RpgInventory.EnumRpgClass;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.mod_RpgInventory;
import RpgPlusPlus.minions.EntityMinionS;
import RpgPlusPlus.minions.EntityMinionZ;
import RpgPlusPlus.minions.IMinion;
import RpgPlusPlus.minions.MinionRegistry;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 *
 * @author Home
 */
public class RpgPlusPacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        //
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        int weaponID = 0;
        EntityPlayer p = (EntityPlayer) player;
        try {
            weaponID = dis.readInt();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ItemStack weapon = p.getCurrentEquippedItem();
        RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
        if (inv != null) {
            switch (weaponID) {
                case WEAPONIDS.SKULLRCLICK:
                    try {
                        dis.close();
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                    if (weapon.getItem().equals(mod_RpgInventory.necro_weapon) && inv.hasClass(EnumRpgClass.NECRO)) {
                        if (!CommonTickHandlerRpgPlus.rpgPluscooldownMap.containsKey(p.username)) {
                            CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p.username, 0);
                        }
                        if (CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p.username) <= 0) {
                            //2 second cooldown
                            CommonTickHandlerRpgPlus.rpgPluscooldownMap.put(p.username, 20 * 2);
                            //System.out.println("SpawnMob");
                            //Allow staff/hammer to perform one last aoe then break the weapon if its damaged enough.
                            if (weapon.getItemDamage() + 2 >= weapon.getMaxDamage()) {
                                //Trigger item break stuff
                                weapon.damageItem(weapon.getMaxDamage() - weapon.getItemDamage() + 1, p);
                                //delete the item
                                p.renderBrokenItemStack(weapon);
                                p.setCurrentItemOrArmor(0, (ItemStack) null);
                            } else {
                                weapon.damageItem(2, p);
                            }
                            World world = p.worldObj;
                            if (inv.hasClass(EnumRpgClass.SHIELDEDNECRO)) {
                                if (!world.isRemote) {
                                    EntityMinionS var4 = new EntityMinionS(world, p);
                                    if (var4 != null) {
                                        var4.setPosition(p.posX, p.posY, p.posZ);
                                        world.spawnEntityInWorld(var4);
                                        var4.setTamed(true);
                                        var4.setOwner(p.username);
                                    }
                                }
                            } else {
                                if (!world.isRemote) {
                                    EntityMinionZ var4 = new EntityMinionZ(world, p);
                                    if (var4 != null) {
                                        var4.setPosition(p.posX, p.posY, p.posZ);
                                        world.spawnEntityInWorld(var4);
                                        var4.setTamed(true);
                                        var4.setOwner(p.username);
                                    }
                                }
                            }
                        } else {
                            p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandlerRpgPlus.rpgPluscooldownMap.get(p.username) / 20) + " seconds");
                        }

                    }
                    break;
                case WEAPONIDS.NECROSPECIAL:
                    try {
                        dis.close();
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                    if (weapon.getItem().equals(mod_RpgInventory.necro_weapon) && inv.hasClass(EnumRpgClass.NECRO)) {
                        if (MinionRegistry.playerMinions.containsKey(p.username)) {
                            List<IMinion> list = MinionRegistry.playerMinions.get(p.username);
                            for (IMinion minion : list) {
                                minion.Harvest();
                            }
                        }
                    }

                case WEAPONIDS.PALADINSPECIAL:
                    try {
                        dis.close();
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                    inv.onInventoryChanged();
                    if (!mod_RpgInventory.developers.contains(p.username.toLowerCase()) || weapon == null) {
                        if (!inv.hasClass(EnumRpgClass.PALADIN)) {
                            break;
                        }
                    }

                    if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
                        CommonTickHandler.globalCooldownMap.put(p.username, 0);
                    }
                    if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
                        CommonTickHandler.globalCooldownMap.put(p.username, 5 * 20);
                        //System.out.println("Healing time!");
                        //Allow staff/hammer to perform one last aoe then break the weapon if its damaged enough.
                        if (weapon.getItemDamage() + 3 >= weapon.getMaxDamage()) {
                            //Trigger item break stuff
                            weapon.damageItem(weapon.getMaxDamage() - weapon.getItemDamage() + 1, p);
                            //delete the item
                            p.renderBrokenItemStack(weapon);
                            p.setCurrentItemOrArmor(0, (ItemStack) null);
                        } else {
                            if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                weapon.damageItem(3, p);
                            }
                        }
                        float rad = 6.0f;
                        AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - rad, p.posY - rad, p.posZ - rad, p.posX + rad, p.posY + rad, p.posZ + rad);
                        List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
                        if (entl != null && entl.size() > 0) {
                            for (EntityLiving el : entl) {
                                if (el != null) {
                                    double dist = ((EntityPlayer) player).getDistanceSqToEntity(el);
                                    double potstrength = 1.0D - Math.sqrt(dist) / 4.0D;
                                    Potion.heal.affectEntity((EntityLiving) player, el, 2, potstrength * 2);
                                }
                            }
                        }
                    } else {
                        p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static class WEAPONIDS {

        public static final int SKULLRCLICK = 5;
        public static final int NECROSPECIAL = 6;
        public static final int PALADINSPECIAL = 9;
    }
}
