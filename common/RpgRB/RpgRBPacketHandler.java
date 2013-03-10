package RpgRB;

import RpgInventory.IPet;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BMPetImpl;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import RpgRB.beastmaster.BoarPet;
import RpgRB.beastmaster.BullPet;
import RpgRB.beastmaster.SpiderPet;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RpgRBPacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {

        EntityPlayer p = (EntityPlayer) player;
        World world = p.worldObj;
        int x = (int) p.posX;
        int y = (int) p.posY;
        int z = (int) p.posZ;
        ObjectInputStream dis;
        try {
            dis = new ObjectInputStream(new ByteArrayInputStream(packet.data));
            String newName = dis.readUTF();
            setPetName(p, newName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("nameRecievingFailed");
        }
    }

    public void setPetName(EntityPlayer p, String newName) {
        try {
            System.out.println(newName);
            ItemStack petCrystal = mod_RpgInventory.proxy.getInventory(p.username).getCrystal();
            BMPetImpl thePet = null;
            if (IPet.playersWithActivePets.containsKey(p.username)) {
                thePet = (BMPetImpl) IPet.playersWithActivePets.get(p.username);
            }
            if (thePet != null && !((EntityLiving) thePet).isDead) {
                thePet.setName(newName);
            }
            petCrystal.getTagCompound().setString("PetName", newName);
            ((NBTTagCompound) petCrystal.getTagCompound().getTag("RPGPetInfo")).setString("Name", newName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
