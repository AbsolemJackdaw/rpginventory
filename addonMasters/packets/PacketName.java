package addonMasters.packets;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.entity.BMPetImpl;
import addonMasters.entity.IPet;

public class PacketName {

	public PacketName(DataInputStream dis, EntityPlayer p) {

		String newName;
		try {
			newName = dis.readUTF();
			setPetName(p, newName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPetName(EntityPlayer p, String newName) {

		ItemStack petCrystal = PlayerRpgInventory.get(p).getCrystal();
		BMPetImpl thePet = null;
		if (IPet.playersWithActivePets.containsKey(p.getDisplayName()))
			thePet = (BMPetImpl) IPet.playersWithActivePets.get(
					p.getDisplayName()).getPet();
		if ((thePet != null) && !((EntityLiving) thePet).isDead)
			thePet.setName(newName);
		petCrystal.getTagCompound().setString("PetName", newName);
		((NBTTagCompound) petCrystal.getTagCompound().getTag("RPGPetInfo"))
		.setString("Name", newName);
	}
}
