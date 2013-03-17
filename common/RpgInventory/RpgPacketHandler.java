package RpgInventory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.weapons.bow.EntityHellArrow;
import RpgRB.EntityTeleportStone;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

public class RpgPacketHandler implements IPacketHandler {

    private double xx;
    private double yy;
    private double zz;
    public static final int OPENRPGINV = 1;
    public static final int PETGUI = 2;
    public static final int MAGE1 = 3;
    public static final int BERSERKER = 4;
    public static final int ARCHER = 5;
    public static final int MAGE2 = 7;
    private Random rand = new Random(5);

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

        if (packet.channel.equals("RpgInv")) {
            handleRandom(packet, player);
        } else if (packet.channel.equals("RpgRawInv")) {
            handleRawInventory(packet, (EntityPlayer) player);
        }

    }

    private void handleRawInventory(Packet250CustomPayload packet, EntityPlayer p) {
        try {
            NBTTagCompound nbt = CompressedStreamTools.decompress(packet.data);
            RpgInv inv = new RpgInv(nbt.getString("username"));
            NBTTagList list = nbt.getTagList("items");
            for (int i = 0; i < inv.armorSlots.length; i++) {
                NBTTagCompound tc = (NBTTagCompound) list.tagAt(i);
                if (tc != null) {
                    inv.armorSlots[i] = ItemStack.loadItemStackFromNBT(tc);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleRandom(Packet250CustomPayload packet, Player player) {


        EntityPlayer p = (EntityPlayer) player;
        World world = p.worldObj;
        int x = (int) p.posX;
        int y = (int) p.posY;
        int z = (int) p.posZ;

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        //System.out.println("Packet was send and recieved.");
        try {
            int guiId = dis.readInt();
            switch (guiId) {
                //Gui open request packet
                case 1:
                    if (!world.isRemote) {
                        FMLNetworkHandler.openGui(p, mod_RpgInventory.instance, 1, world, x, y, z);
                        //System.out.println("gui launched ");
                    }
                    break;
                //Packet from server containing players RPGInventory info

                case 2:
                    try {
                        System.out.println("Packet Recieved!");
                        RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
                        ItemStack crystal = inv.getCrystal();
                        NBTTagCompound nbtCrystal = (NBTTagCompound)crystal.getTagCompound().copy();
                        NBTTagCompound nbtPet = (NBTTagCompound)nbtCrystal.getCompoundTag("RPGPetInfo").copy();
                        String petname = dis.readUTF();
                        nbtCrystal.setString("PetName", petname);
                        nbtPet.setString("Name", petname);
                        crystal.setItemName(petname);
                        short PetLevel = dis.readShort();
                        nbtCrystal.setInteger("PetLevel", PetLevel);
                        nbtPet.setInteger("XpLevel", PetLevel);
                        short currentHP = dis.readShort();
                        nbtCrystal.setInteger("PetHealth", currentHP);
                        nbtPet.setShort("Health", currentHP);
                        nbtCrystal.setInteger("PetMaxHealth", dis.readShort());
                        nbtCrystal.setInteger("PetAttack", dis.readShort());
                        
                        nbtCrystal.setCompoundTag("RPGPetInfo", nbtPet);
                        int playerLevelsLost = dis.readShort();
                        //currently unused
                        int petLevelsAdded = dis.readShort();
                        int petcandyConsumed = dis.readShort();
                        
                        p.addExperienceLevel(-playerLevelsLost);
                        ItemStack newcrystal = new ItemStack(mod_RpgInventory.crystal, 1, crystal.getItemDamage());
                        newcrystal.setTagCompound(nbtCrystal);
                        newcrystal.setItemName(petname);
                        inv.setInventorySlotContents(6, newcrystal);
                        mod_RpgInventory.proxy.addEntry(p.username, inv);
                    } catch (Throwable ex) {
                    }
                    break;
                case 3:
                    if (!world.isRemote) {
                        ItemStack item = p.getCurrentEquippedItem();
                        ItemStack var3 = p.inventory.armorItemInSlot(3);
                        ItemStack var2 = p.inventory.armorItemInSlot(2);
                        ItemStack var1 = p.inventory.armorItemInSlot(1);
                        ItemStack var0 = p.inventory.armorItemInSlot(0);
                        if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                            if (item == null || var3 == null || var2 == null || var1 == null || var0 == null) {
                                break;
                            }
                            if (item.getItem() != mod_RpgInventory.staf || var3.getItem() != mod_RpgInventory.magehood || var2.getItem() != mod_RpgInventory.magegown || var1.getItem() != mod_RpgInventory.magepants || var0.getItem() != mod_RpgInventory.mageboots) {
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
                            if (item.getItemDamage() + 3 >= item.getMaxDamage()) {
                                //Trigger item break stuff
                                item.damageItem(item.getMaxDamage() - item.getItemDamage() + 1, p);
                                //delete the item
                                p.renderBrokenItemStack(item);
                                p.setCurrentItemOrArmor(0, (ItemStack) null);
                            } else {
                                if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                    item.damageItem(3, p);
                                }
                            }
                            AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - 4.0F, p.posY - 4.0F, p.posZ - 4.0F, p.posX + 4.0F, p.posY + 4.0F, p.posZ + 4.0F);
                            List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
                            if (entl != null && entl.size() > 0) {
                                for (EntityLiving el : entl) {
                                    if (el != null) {
                                        double dist = ((EntityPlayer) player).getDistanceSqToEntity(el);
                                        double potstrength = 1.0D - Math.sqrt(dist) / 4.0D;
                                        Potion.heal.affectEntity((EntityLiving) player, el, 2, potstrength);

                                    }
                                }
                            }
                        } else {
                            p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
                        }
                    }
                    break;
                case 4:
                    if (!world.isRemote) {
                        //System.out.println("Hammer time!");
                        ItemStack item1 = p.getCurrentEquippedItem();
                        ItemStack var31 = p.inventory.armorItemInSlot(3);
                        ItemStack var21 = p.inventory.armorItemInSlot(2);
                        ItemStack var11 = p.inventory.armorItemInSlot(1);
                        ItemStack var01 = p.inventory.armorItemInSlot(0);

                        if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                            if (item1 == null || var31 == null || var21 == null || var11 == null || var01 == null) {
                                break;
                            }
                            if (item1.getItem() != mod_RpgInventory.hammer || var31.getItem() != mod_RpgInventory.berserkerHood || var21.getItem() != mod_RpgInventory.berserkerChest || var11.getItem() != mod_RpgInventory.berserkerLegs || var01.getItem() != mod_RpgInventory.berserkerBoots) {
                                break;
                            }
                        }
                        if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 0);
                        }
                        if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 7 * 20);
                            if (item1.getItemDamage() + 3 >= item1.getMaxDamage()) {
                                //Trigger item break stuff
                                //Only damage what is left
                                if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                    item1.damageItem(item1.getMaxDamage() - item1.getItemDamage(), p);
                                }
                                //Do the break item stuff
                                p.renderBrokenItemStack(item1);
                                //delete the item
                                p.setCurrentItemOrArmor(0, (ItemStack) null);
                            } else {
                                if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                    item1.damageItem(3, p);
                                }
                            }
                            AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - 4.0F, p.posY - 4.0F, p.posZ - 4.0F, p.posX + 4.0F, p.posY + 4.0F, p.posZ + 4.0F);
                            List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);
                            if (entl != null && entl.size() > 0) {
                                for (EntityLiving el : entl) {
                                    if (el != null && el != player) {
                                        try {
                                            double xdir = el.posX - p.posX;
                                            double zdir = el.posZ - p.posZ;
                                            el.motionX = xdir * 1.5F;
                                            el.motionY = 1.5F;
                                            el.motionZ = zdir * 1.5F;
                                        } catch (Throwable ex) {
                                        }
                                        el.attackEntityFrom(DamageSource.causePlayerDamage(p), 10);
                                    }
                                }
                            }
                        } else {
                            p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
                        }


                        break;
                    }
                case 5:
                    if (!world.isRemote) {
                        boolean self = dis.readBoolean();
                        if (self == true) {
                            xx = (int) p.posX;
                            yy = (int) p.posY;
                            zz = (int) p.posZ;
                        } else {
                            xx = dis.readInt();
                            yy = dis.readInt();
                            zz = dis.readInt();
                        }
                        System.out.println(self + " " + xx + " " + yy + " " + zz);
                        ItemStack bow = p.getCurrentEquippedItem();
                        ItemStack top = p.inventory.armorItemInSlot(3);
                        ItemStack middle = p.inventory.armorItemInSlot(2);
                        ItemStack middle2 = p.inventory.armorItemInSlot(1);
                        ItemStack bottom = p.inventory.armorItemInSlot(0);
                        if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                            if (bow == null || top == null || middle == null || middle2 == null || bottom == null) {
                                break;
                            }
                            if (bow.getItem() != mod_RpgInventory.elfbow || top.getItem() != mod_RpgInventory.archerhood || middle.getItem() != mod_RpgInventory.archerchest || middle2.getItem() != mod_RpgInventory.archerpants || bottom.getItem() != mod_RpgInventory.archerboots) {
                                break;
                            }
                        }
                        if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 0);
                        }
                        if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 30 * 20);
                            if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                bow.damageItem(10, p);
                            }
                            for (int x1 = (int) -10; x1 < 10; x1++) {

                                for (int z1 = (int) -10; z1 < 10; z1++) {

                                    Vec3 posStart = Vec3.createVectorHelper(xx, yy, zz);
                                    Vec3 posArrow = posStart.addVector(x1, 0, z1);
                                    //System.out.println(posArrow);
                                    Double dist = posStart.distanceTo(posArrow);
                                    if (dist < 10) {
                                        if (self) {
                                            if (dist > 2) {
                                                if ((dist < 10 + 5.0F)) {
                                                    EntityHellArrow var8 = new EntityHellArrow(p.worldObj, xx + x1, yy + 100, zz + z1);
                                                    var8.setIsCritical(true);
                                                    var8.setDamage(10);
                                                    var8.setKnockbackStrength(5);
                                                    var8.setFire(20);
                                                    var8.canBePickedUp = 2;
                                                    Random itemRand = new Random();
                                                    //p.worldObj.playSoundAtEntity(p, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 100);
                                                    if (!p.worldObj.isRemote) {
                                                        p.worldObj.spawnEntityInWorld(var8);
                                                    }
                                                }
                                            }
                                        } else {
                                            if ((dist < 10 + 5.0F)) {
                                                EntityHellArrow var8 = new EntityHellArrow(p.worldObj, xx + x1, yy + 100, zz + z1);
                                                var8.setIsCritical(true);
                                                var8.setDamage(10);
                                                var8.setKnockbackStrength(5);
                                                var8.setFire(20);
                                                var8.canBePickedUp = 2;
                                                Random itemRand = new Random();
                                                //p.worldObj.playSoundAtEntity(p, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 100);
                                                if (!p.worldObj.isRemote) {
                                                    p.worldObj.spawnEntityInWorld(var8);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
                        }
                    }
                    break;
                case 7:
                    if (!world.isRemote) {
                        ItemStack wand = p.getCurrentEquippedItem();
                        ItemStack var311 = p.inventory.armorItemInSlot(3);
                        ItemStack var211 = p.inventory.armorItemInSlot(2);
                        ItemStack var111 = p.inventory.armorItemInSlot(1);
                        ItemStack var011 = p.inventory.armorItemInSlot(0);
                        if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                            if (wand == null || var311 == null || var211 == null || var111 == null || var011 == null) {
                                break;
                            }
                            if (wand.getItem() != mod_RpgInventory.wand || var311.getItem() != mod_RpgInventory.magehood || var211.getItem() != mod_RpgInventory.magegown || var111.getItem() != mod_RpgInventory.magepants || var011.getItem() != mod_RpgInventory.mageboots) {
                                break;
                            }
                        }
                        if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 0);
                        }
                        if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 7 * 20);
                            if (wand.getItemDamage() + 3 >= wand.getMaxDamage()) {
                                //Trigger item break stuff
                                //Only damage what is left
                                wand.damageItem(wand.getMaxDamage() - wand.getItemDamage(), p);
                                //Do the break item stuff
                                p.renderBrokenItemStack(wand);
                                //delete the item
                                p.setCurrentItemOrArmor(0, (ItemStack) null);
                            } else {
                                if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                    wand.damageItem(3, p);
                                }
                            }
                            AxisAlignedBB pool = AxisAlignedBB.getAABBPool().getAABB(p.posX - 20.0F, p.posY - 20.0F, p.posZ - 20.0F, p.posX + 20.0F, p.posY + 20.0F, p.posZ + 20.0F);
                            List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABBExcludingEntity(p, pool);//p.worldObj.getEntitiesWithinAABB(EntityLiving.class, pool);

                            if (entl != null && entl.size() > 0) {
                                for (Entity el : entl) {
                                    if (el != null && el != player) {

                                        int var4;

                                        var4 = 10;
                                        if (entl instanceof EntityLiving) {
                                            var4 += EnchantmentHelper.getKnockbackModifier(p, (EntityLiving) el);
                                        }
                                        if (var4 > 0) {
                                            try {
                                                Vec3 posPlayer = Vec3.createVectorHelper(el.posX, el.posY, el.posZ);
                                                Vec3 posEntity = Vec3.createVectorHelper(p.posX, p.posY, p.posZ);
                                                Vec3 posFinal = posPlayer.myVec3LocalPool.getVecFromPool(posEntity.xCoord - posPlayer.xCoord, posEntity.yCoord - posPlayer.yCoord, posEntity.zCoord - posPlayer.zCoord).normalize();
                                                //System.out.println(posFinal);
                                                el.setVelocity(posFinal.xCoord * 4, posFinal.yCoord * 4, posFinal.zCoord * 4);
                                            } catch (Throwable ex) {
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
                        }
                        break;
                    }
                case 8:
                    if (!world.isRemote) {
                        FMLNetworkHandler.openGui(p, mod_RpgInventory.instance, 2, world, x, y, z);
                    }
                    break;

                //case 9 used for Paladin
                case 10:
                    break;
                case 11:
                    int entityid = dis.readInt();
                    if (entityid == 0) {
                        p.attackEntityFrom(DamageSource.magic, 1);
                    } else {
                        Entity e = p.worldObj.getEntityByID(entityid);
                        if (e != null) {
                            if (e instanceof EntityPig) {
                                ItemStack is = new ItemStack(mod_RpgInventory.crystal, 1, 1);
                                e.entityDropItem(is, 0.5F);
                                e.setDead();
                            } else if (e instanceof EntitySpider || e instanceof EntityCaveSpider) {
                                ItemStack is = new ItemStack(mod_RpgInventory.crystal, 1, 2);
                                e.entityDropItem(is, 0.5F);
                                e.setDead();
                            } else if (e instanceof EntityCow) {
                                ItemStack is = new ItemStack(mod_RpgInventory.crystal, 1, 3);
                                e.entityDropItem(is, 0.5F);
                                e.setDead();
                            }
                        }
                    }
                    break;
                case 12:
                    //unused
                    break;
                case 13:
                    mod_RpgInventory.proxy.candy(p);
                    break;
                case 14:
                    if (!world.isRemote) {
                        ItemStack dagger = p.getCurrentEquippedItem();
                        ItemStack var311 = p.inventory.armorItemInSlot(3);
                        ItemStack var211 = p.inventory.armorItemInSlot(2);
                        ItemStack var111 = p.inventory.armorItemInSlot(1);
                        ItemStack var011 = p.inventory.armorItemInSlot(0);
                        if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                            if (dagger == null || var311 == null || var211 == null || var111 == null || var011 == null) {
                                break;
                            }
                            if (dagger.getItem() != mod_RpgInventory.daggers || var311.getItem() != mod_RpgInventory.rogueHood || var211.getItem() != mod_RpgInventory.rogueChest || var111.getItem() != mod_RpgInventory.rogueLegs || var011.getItem() != mod_RpgInventory.rogueBoots) {
                                break;
                            }
                        }
                        if (!CommonTickHandler.globalCooldownMap.containsKey(p.username)) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 0);
                        }
                        if (CommonTickHandler.globalCooldownMap.get(p.username) <= 0) {
                            CommonTickHandler.globalCooldownMap.put(p.username, 5 * 20);
                            if (dagger.getItemDamage() + 3 >= dagger.getMaxDamage()) {
                                dagger.damageItem(dagger.getMaxDamage() - dagger.getItemDamage(), p);
                                p.renderBrokenItemStack(dagger);
                                p.setCurrentItemOrArmor(0, (ItemStack) null);
                            } else {
                                if (!mod_RpgInventory.developers.contains(p.username.toLowerCase())) {
                                    dagger.damageItem(3, p);
                                }
                            }
                            p.worldObj.spawnEntityInWorld(new EntityTeleportStone(p.worldObj, p));
                            double d0 = this.rand.nextGaussian() * 0.02D;
                            double d1 = this.rand.nextGaussian() * 0.02D;
                            double d2 = this.rand.nextGaussian() * 0.02D;
                            p.worldObj.spawnParticle("largesmoke", p.posX + (double) (this.rand.nextFloat() * p.width * 2.0F) - (double) p.width, p.posY + 0.5D + (double) (this.rand.nextFloat() * p.height), p.posZ + (double) (this.rand.nextFloat() * p.width * 2.0F) - (double) p.width, d0, d1, d2);

                        } else {
                            p.sendChatToPlayer("You must wait for energy to replenish, left: " + Math.floor(1 + CommonTickHandler.globalCooldownMap.get(p.username) / 20) + " seconds");
                        }
                    }
                    break;
                default:
                    break;
            }
            dis.close();
        } catch (IOException e) {
            System.out.println("failed");

            e.printStackTrace();
        }
    }
}
