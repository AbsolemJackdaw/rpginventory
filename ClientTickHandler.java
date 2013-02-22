package RpgInventory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ClientTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		//System.out.println("Class ClientTickHandler called");


	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		//System.out.println("Class ClientTickHandler called > tickEnd");

		Minecraft mc = Minecraft.getMinecraft();
		WorldClient world = Minecraft.getMinecraft().theWorld;
		GuiScreen guiscreen = mc.currentScreen;

		EntityPlayer player = (EntityPlayer)ModLoader.getMinecraftInstance().thePlayer;
		if(type.contains(TickType.CLIENT))
		{	

			if(guiscreen instanceof GuiInventory || guiscreen instanceof GuiContainerCreative)
			{
				GuiContainerCreative gui2 = (GuiContainerCreative) guiscreen;
				//System.out.println(gui2.);
				if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
					int i = 1;
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					ObjectOutput out;
					DataOutputStream outputStream = new DataOutputStream(bytes);

					try
					{
						outputStream.writeInt(i);
						Packet250CustomPayload packet = new Packet250CustomPayload("RPGInv", bytes.toByteArray());
						PacketDispatcher.sendPacketToServer(packet);
						System.out.println("Packet send");

					}
					catch (IOException e)
					{
						e.printStackTrace();
					}	

					System.out.println("opened rpg gui");
				}
//								if((guiscreen instanceof RpgGui))
//								{
//									AARpgBaseClass.proxy.openGUI(player, 1);
//								}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "RpgInventory";
	}

}
