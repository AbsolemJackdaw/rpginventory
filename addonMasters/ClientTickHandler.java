package addonMasters;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.RpgGui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientTickHandler {

	boolean added = false;

	@SubscribeEvent
	public void tickEnd(TickEvent.ClientTickEvent ev) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		// Put away pet when gui is opened. This is a nerf and a fix for lag
		// induced duping.
		try {
			if (Minecraft.getMinecraft().currentScreen instanceof RpgGui) {

				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				// out.writeInt(RpgRBPacketHandler.STOREPET);

				// TODO sendpacket
				// PacketDispatcher.sendPacketToServer(new
				// Packet250CustomPayload(
				// "RpgRBPacket", out.toByteArray()));


				// This will only inject our buttons into the existing
				// GuiInventory
				// object.
				// The button prevents calls to the parent GUI if clicked, and
				// calls our
				// packet
				// instead. I see no incompatibilies.
				// added = false;

				if ((Minecraft.getMinecraft().currentScreen != null)
						&& (Minecraft.getMinecraft().theWorld != null)
						&& (Minecraft.getMinecraft().thePlayer != null)) {
					if ((Minecraft.getMinecraft().currentScreen instanceof RpgGui)) {
						// if (!added) {
						added = true;
						GuiScreen gui = Minecraft.getMinecraft().currentScreen;
						List currentButtons = null;
						for (Field f : GuiScreen.class.getDeclaredFields()) {
							f.setAccessible(true);
							try {
								// "controlList" is the name deobfuscated. "i"
								// is
								// the name obfustcated.
								// This adds compatibility when developing AND
								// on
								// release.
								if (f.getName().equals("buttonList")
										|| f.getName().equals("field_146292_n")) {
									Field modfield = Field.class
											.getDeclaredField("modifiers");
									modfield.setAccessible(true);
									modfield.setInt(f, f.getModifiers()
											& ~Modifier.PROTECTED);
									currentButtons = (List) f.get(gui);

									for (int i = 0; i < currentButtons.size(); i++)
										if (currentButtons.get(i) instanceof ButtonPetGui)
											return;

									int offsetx = 80;
									int offsety = 93;
									int posX = (Minecraft.getMinecraft().currentScreen.width) / 2;
									int posY = (Minecraft.getMinecraft().currentScreen.height) / 2;
									currentButtons.add(new ButtonPetGui(
											currentButtons.size() + 1,
											posX + 42, posY - 60, 50, 20, gui,
											"Pet Gui"));
								}
							} catch (Exception e) {
								System.err
										.println("Severe error, please report this to the mod author:");
								System.err.println(e);
							}
						}
					}
				} else
					added = false;
			} else
				added = false;
			// }

		} catch (Exception e) {

		}
	}
}
