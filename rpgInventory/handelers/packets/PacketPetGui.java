package rpgInventory.handelers.packets;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;

public class PacketPetGui {

	
	public PacketPetGui(DataInputStream dis, EntityPlayer p){
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
             nbtCrystal.setFloat("PetHealth", currentHP);
             nbtPet.setShort("Health", currentHP);
             nbtCrystal.setFloat("PetMaxHealth", dis.readShort());
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
	}
}
