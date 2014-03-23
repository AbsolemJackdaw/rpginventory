package rpgRogueBeast;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.RpgGui;
import rpgRogueBeast.packets.RpgRBPacketHandler;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientTickHandler {

//	@Override
//	public String getLabel() {
//		return "pet syncher";
//	}

	@SubscribeEvent
	public void tickEnd(TickEvent.ClientTickEvent ev) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		// Put away pet when gui is opened. This is a nerf and a fix for lag
		// induced duping.
		try {
			if (Minecraft.getMinecraft().currentScreen instanceof RpgGui) {

				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeInt(RpgRBPacketHandler.STOREPET);
				
				//TODO sendpacket
//				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(
//						"RpgRBPacket", out.toByteArray()));
				
				System.out.println("todo : send packet");
			}

		} catch (Exception e) {

		}
	}

//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.CLIENT);
//	}
//
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {
//		// TODO Auto-generated method stub
//
//	}

}
