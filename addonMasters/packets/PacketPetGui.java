package addonMasters.packets;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.RpgMastersAddon;

public class PacketPetGui {

	public PacketPetGui(ByteBufInputStream dis, EntityPlayer p) {
		try {
			String petname = dis.readUTF();
			short PetLevel = dis.readShort();
			short currentHP = dis.readShort();
			short hpmax = dis.readShort();
			short atk = dis.readShort();
			int playerLevelsLost = dis.readShort();
			// currently unused
			int petLevelsAdded = dis.readShort();
			int petcandyConsumed = dis.readShort();

			PlayerRpgInventory inv = PlayerRpgInventory.get(p);
			ItemStack crystal = inv.getCrystal();

			NBTTagCompound nbtCrystal = (NBTTagCompound) crystal
					.getTagCompound().copy();
			NBTTagCompound nbtPet = (NBTTagCompound) nbtCrystal.getCompoundTag(
					"RPGPetInfo").copy();

			nbtCrystal.setString("PetName", petname);
			nbtPet.setString("Name", petname);
			crystal.setStackDisplayName(petname);

			nbtCrystal.setInteger("PetLevel", PetLevel);
			nbtPet.setInteger("XpLevel", PetLevel);

			nbtCrystal.setFloat("PetHealth", currentHP);
			nbtPet.setShort("Health", currentHP);

			nbtCrystal.setFloat("PetMaxHealth", hpmax);

			nbtCrystal.setInteger("PetAttack", atk);

			nbtCrystal.setTag("RPGPetInfo", nbtPet);

			p.addExperienceLevel(-playerLevelsLost);
			ItemStack newcrystal = new ItemStack(RpgMastersAddon.crystal, 1,
					crystal.getItemDamage());
			newcrystal.setTagCompound(nbtCrystal);
			newcrystal.setStackDisplayName(petname);
			inv.setInventorySlotContents(6, newcrystal);

		} catch (Throwable ex) {
		}
	}
 }
