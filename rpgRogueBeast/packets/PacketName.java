package rpgRogueBeast.packets;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgRogueBeast.entity.BMPetImpl;
import rpgRogueBeast.entity.IPet;

public class PacketName {

	public PacketName(DataInputStream dis, EntityPlayer p) {

		try {
			String newName = dis.readUTF();
			setPetName(p, newName);
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("nameRecievingFailed");
		}
	}

	public void setPetName(EntityPlayer p, String newName) {
		try {
			// System.out.println(newName);
			ItemStack petCrystal = PlayerRpgInventory.get(p).getCrystal();
			BMPetImpl thePet = null;
			if (IPet.playersWithActivePets.containsKey(p.getDisplayName())) {
				thePet = (BMPetImpl) IPet.playersWithActivePets.get(
						p.getDisplayName()).getPet();
			}
			if ((thePet != null) && !((EntityLiving) thePet).isDead) {
				thePet.setName(newName);
			}
			petCrystal.getTagCompound().setString("PetName", newName);
			((NBTTagCompound) petCrystal.getTagCompound().getTag("RPGPetInfo"))
					.setString("Name", newName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
