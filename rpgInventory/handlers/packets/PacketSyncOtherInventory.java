package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncOtherInventory implements IMessage {

///////*This packet is viewed by another player, getting your data to know what you have, so otherUser is you*//////
	
	public int otherUser;
	public ItemStack stack[] = new ItemStack[7];
	public boolean blocking;
	
	public PacketSyncOtherInventory() {
		//default constructor is needed else the game crashes
	}

	public PacketSyncOtherInventory(EntityPlayer player) {
		PlayerRpgInventory inv = PlayerRpgInventory.get(player);
		
		otherUser = player.getEntityId();
		
		blocking = inv.isBlocking();
		
		for (int i = 0; i < 7; i++){
			stack[i] = inv.getJewelInSlot(i);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		otherUser = buf.readInt();

		blocking = buf.readBoolean();
		
		for (int i = 0; i < 7; i++)
			stack[i] = ByteBufUtils.readItemStack(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(otherUser);
		
		buf.writeBoolean(blocking);
		
		for (int i = 0; i < stack.length; i++)
			ByteBufUtils.writeItemStack(buf, stack[i]);
		

	}

	public static class HandlerSyncOtherInventory implements IMessageHandler<PacketSyncOtherInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOtherInventory message,MessageContext ctx) {

			EntityPlayer other = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(message.otherUser);

			if(other != null){
				PlayerRpgInventory rpg = PlayerRpgInventory.get(other);
				if(rpg != null){
					rpg.setBlocking(message.blocking);
					for (int i = 0; i < 7; i++){
						rpg.setInventorySlotContents(i,message.stack[i]);
					}
				}else{
					FMLLog.getLogger().info("packet info. 'inventory' was null. dropping packet");
				}
			}else{
				FMLLog.getLogger().info("packet info. 'other' was null. dropping packet");
			}
			
			return null;
		}
	}
}
