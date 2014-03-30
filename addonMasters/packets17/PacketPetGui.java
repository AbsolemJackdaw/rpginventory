//package addonMasters.packets17;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import rpgInventory.gui.rpginv.PlayerRpgInventory;
//import addonMasters.mod_RpgRB;
//import cpw.mods.fml.common.network.ByteBufUtils;
//
//public class PacketPetGui extends RpgRBAbstractPacket {
//
//	String petName;
//	short petLevel, currentHP, hpMax, atk, playerLevelsLost, petLevelsAdded,
//			petLevelsConsumed;
//
//	public PacketPetGui() {
//	}
//
//	public PacketPetGui(String petName, short petLevel, short currentHP,
//			short hpMax, short atk, short playerLevelsLost,
//			short petLevelsAdded, short petLevelsConsumed) {
//		this.petName = petName;
//		this.petLevel = petLevel;
//		this.currentHP = currentHP;
//		this.hpMax = hpMax;
//		this.atk = atk;
//		this.playerLevelsLost = playerLevelsLost;
//		this.petLevelsAdded = petLevelsAdded;
//		this.petLevelsConsumed = petLevelsConsumed;
//	}
//
//	@Override
//	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//
//		this.petName = ByteBufUtils.readUTF8String(buffer);
//		this.petLevel = buffer.readShort();
//		this.currentHP = buffer.readShort();
//		this.hpMax = buffer.readShort();
//		this.atk = buffer.readShort();
//		this.playerLevelsLost = buffer.readShort();
//		this.petLevelsAdded = buffer.readShort();
//		this.petLevelsConsumed = buffer.readShort();
//	}
//
//	@Override
//	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//
//		ByteBufUtils.writeUTF8String(buffer, petName);
//		buffer.writeShort(petLevel);
//		buffer.writeShort(currentHP);
//		buffer.writeShort(hpMax);
//		buffer.writeShort(atk);
//		buffer.writeShort(playerLevelsLost);
//		buffer.writeShort(petLevelsAdded);
//		buffer.writeShort(petLevelsConsumed);
//	}
//
//	@Override
//	public void handleClientSide(EntityPlayer player) {
//	}
//
//	@Override
//	public void handleServerSide(EntityPlayer player) {
//
//		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
//		ItemStack crystal = inv.getCrystal();
//
//		NBTTagCompound nbtCrystal = (NBTTagCompound) crystal.getTagCompound()
//				.copy();
//		NBTTagCompound nbtPet = (NBTTagCompound) nbtCrystal.getCompoundTag(
//				"RPGPetInfo").copy();
//
//		nbtCrystal.setString("PetName", petName);
//		nbtPet.setString("Name", petName);
//		crystal.setStackDisplayName(petName);
//
//		nbtCrystal.setInteger("PetLevel", petLevel);
//		nbtPet.setInteger("XpLevel", petLevel);
//
//		nbtCrystal.setFloat("PetHealth", currentHP);
//		nbtPet.setShort("Health", currentHP);
//
//		nbtCrystal.setFloat("PetMaxHealth", hpMax);
//
//		nbtCrystal.setInteger("PetAttack", atk);
//
//		nbtCrystal.setTag("RPGPetInfo", nbtPet);
//
//		player.addExperienceLevel(-playerLevelsLost);
//		ItemStack newcrystal = new ItemStack(mod_RpgRB.crystal, 1,
//				crystal.getItemDamage());
//		newcrystal.setTagCompound(nbtCrystal);
//		newcrystal.setStackDisplayName(petName);
//		inv.setInventorySlotContents(6, newcrystal);
//
//		System.out.println("Send packet here");
//	}
//}
