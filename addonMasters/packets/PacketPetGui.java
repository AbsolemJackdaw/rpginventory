package addonMasters.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.RpgMastersAddon;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketPetGui implements IMessage {
	
	public PacketPetGui() {
	}
	
	public String name;
	public short level;
	public short hp;
	public short hpMax;
	public short atk;
	public int levelsLost;
	
	public PacketPetGui(String petName, short petLevel, short currentHp, short hpmax, short atk, int playerLevelsLost) {
		name = petName;
		level = petLevel;
		hp = currentHp;
		hpMax = hpmax;
		this.atk = atk;
		levelsLost = playerLevelsLost;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		level = buf.readShort();
		hp = buf.readShort();
		hpMax = buf.readShort();
		atk = buf.readShort();
		levelsLost = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(level);
		buf.writeShort(hp);
		buf.writeShort(hpMax);
		buf.writeShort(atk);
		buf.writeInt(levelsLost);
		ByteBufUtils.writeUTF8String(buf, name);
		
	}
	
	public static class HandlerPacketPetGui implements IMessageHandler<PacketPetGui, IMessage>{

		@Override
		public IMessage onMessage(PacketPetGui message, MessageContext ctx) {
			
			EntityPlayer p = ctx.getServerHandler().playerEntity;
			
			PlayerRpgInventory inv = PlayerRpgInventory.get(p);
			ItemStack crystal = inv.getCrystal();

			NBTTagCompound nbtCrystal = (NBTTagCompound) crystal.getTagCompound().copy();
			NBTTagCompound nbtPet = (NBTTagCompound) nbtCrystal.getCompoundTag("RPGPetInfo").copy();

			nbtCrystal.setString("PetName", message.name);
			nbtPet.setString("Name", message.name);
			crystal.setStackDisplayName(message.name);

			nbtCrystal.setInteger("PetLevel", message.level);
			nbtPet.setInteger("XpLevel", message.level);

			nbtCrystal.setFloat("PetHealth", message.hp);
			nbtPet.setShort("Health", message.hp);

			nbtCrystal.setFloat("PetMaxHealth", message.hpMax);

			nbtCrystal.setInteger("PetAttack", message.atk);

			nbtCrystal.setTag("RPGPetInfo", nbtPet);

			p.addExperienceLevel(-message.levelsLost);
			ItemStack newcrystal = new ItemStack(RpgMastersAddon.crystal, 1,crystal.getItemDamage());
			newcrystal.setTagCompound(nbtCrystal);
			newcrystal.setStackDisplayName(message.name);
			inv.setInventorySlotContents(6, newcrystal);
			
			return null;
		}
		
	}
}
