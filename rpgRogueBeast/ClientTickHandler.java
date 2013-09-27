package rpgRogueBeast;

import java.util.EnumSet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.handlers.packets.PacketInventory;
import rpgRogueBeast.entity.IPet;
import rpgRogueBeast.packets.RpgRBPacketHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ClientTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		//Put away pet when gui is opened. This is a nerf and a fix for lag induced duping.
		try{
			if(Minecraft.getMinecraft().currentScreen instanceof RpgGui){
			
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeInt(RpgRBPacketHandler.STOREPET);
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("RpgRBPacket", out.toByteArray()));
			}

		}catch(Exception e){

		}		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "pet syncher";
	}



}
