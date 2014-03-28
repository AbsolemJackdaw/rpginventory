package rpgInventory.handlers.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import rpgInventory.mod_RpgInventory;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class Server {

	public static void sendData(EntityPlayerMP player, int index, Object... obs) {
		try {
			mod_RpgInventory.Channel.sendTo(new FMLProxyPacket(getBytes(index, obs),"RpgInv"), player);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendToAllAround(Entity entity, int index, Object... obs) {
		try {
			TargetPoint point = new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 60);
			mod_RpgInventory.Channel.sendToAllAround(new FMLProxyPacket(getBytes(index, obs),"RpgInv"), point);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void sendToAll(int index, Object... obs) {
		try {
			mod_RpgInventory.Channel.sendToAll(new FMLProxyPacket(getBytes(index, obs),"RpgInv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ByteBuf getBytes(int index, Object... obs) throws IOException{
		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);
		out.writeInt(index);
		
		for(Object ob : obs){
			if(ob == null)
				continue;
			if(ob instanceof Map){
				Map<String,Integer> map = (Map<String, Integer>) ob;

				out.writeInt(map.size());
				for(String key : map.keySet()){
					int value = map.get(key);
					out.writeInt(value);
					out.writeUTF(key);
				}
			}
			else if(ob instanceof MerchantRecipeList)
				((MerchantRecipeList)ob).func_151391_a(new PacketBuffer(buf));
			else if(ob instanceof List){
				List<String> list = (List<String>) ob;
				out.writeInt(list.size());
				for(String s : list)
					out.writeUTF(s);
			}
			else if(ob instanceof Enum)
				out.writeInt(((Enum<?>) ob).ordinal());
			else if(ob instanceof Integer)
				out.writeInt((Integer) ob);
			else if(ob instanceof Boolean)
				out.writeBoolean((Boolean) ob);
			else if(ob instanceof String)
				out.writeUTF((String) ob);
			else if(ob instanceof Float)
				out.writeFloat((Float) ob);
			else if(ob instanceof Long)
				out.writeLong((Long) ob);
			else if(ob instanceof Double)
				out.writeDouble((Double) ob);
			else if(ob instanceof NBTTagCompound)
				CompressedStreamTools.write((NBTTagCompound) ob, out);
		}
		out.close();
        return buf;
	}
	
}
