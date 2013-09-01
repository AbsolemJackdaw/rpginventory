package rpgInventory.handelers.packets;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgInv;
import cpw.mods.fml.common.FMLLog;

public class PacketPetGui {


	public PacketPetGui(DataInputStream dis, EntityPlayer p){
		try {

			if(p.worldObj.isRemote)
				System.out.println("Packet petgui C Recieved!");
			else
				System.out.println("Packet petgui S Recieved!");

			String petname = dis.readUTF();
			short PetLevel = dis.readShort();
			short currentHP = dis.readShort();
			short hpmax = dis.readShort();
			short atk = dis.readShort();
			int playerLevelsLost = dis.readShort();
			
			FMLLog.getLogger().info(""+ petname + " "+ PetLevel + " "+currentHP + " "+hpmax+ " "+atk+" "+playerLevelsLost);
			//currently unused
			int petLevelsAdded = dis.readShort();
			int petcandyConsumed = dis.readShort();
			
			
			RpgInv inv = mod_RpgInventory.proxy.getInventory(p.username);
			ItemStack crystal = inv.getCrystal();
			NBTTagCompound nbtCrystal = (NBTTagCompound)crystal.getTagCompound().copy();
			NBTTagCompound nbtPet = (NBTTagCompound)nbtCrystal.getCompoundTag("RPGPetInfo").copy();

			nbtCrystal.setString("PetName", petname);
			nbtPet.setString("Name", petname);
			crystal.setItemName(petname);

			nbtCrystal.setInteger("PetLevel", PetLevel);
			nbtPet.setInteger("XpLevel", PetLevel);

			nbtCrystal.setFloat("PetHealth", currentHP);
			nbtPet.setShort("Health", currentHP);

			nbtCrystal.setFloat("PetMaxHealth", hpmax);

			nbtCrystal.setInteger("PetAttack", atk);

			nbtCrystal.setCompoundTag("RPGPetInfo", nbtPet);
			
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
