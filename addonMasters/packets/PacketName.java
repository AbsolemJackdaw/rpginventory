package addonMasters.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import addonMasters.entity.BeastMasterPet;
import addonMasters.entity.IPet;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketName implements IMessage{

	public PacketName() {
	}
	
	public String newName;
	
	public PacketName(String newName) {
		this.newName = newName;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		newName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, newName);
	}

	public static class HandlerPacketName implements IMessageHandler<PacketName, IMessage>{

		@Override
		public IMessage onMessage(PacketName message, MessageContext ctx) {
			
			EntityPlayer p = ctx.getServerHandler().playerEntity;
			ItemStack petCrystal = PlayerRpgInventory.get(p).getCrystal();
			
			BeastMasterPet thePet = null;
			
			if (IPet.playersWithActivePets.containsKey(p.getDisplayName())) {
				thePet = (BeastMasterPet) IPet.playersWithActivePets.get(p.getDisplayName()).getPet();
			}
			if ((thePet != null) && !((EntityLiving) thePet).isDead) {
				thePet.setName(message.newName);
			}
			
			petCrystal.getTagCompound().setString("PetName", message.newName);
			((NBTTagCompound) petCrystal.getTagCompound().getTag("RPGPetInfo")).setString("Name", message.newName);
			
			return null;
		}
	}
}
