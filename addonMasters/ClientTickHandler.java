package addonMasters;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import addonMasters.packets.RBServerPacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.handlers.packets.ServerPacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class ClientTickHandler {

	boolean added = false;

	@SubscribeEvent
	public void tickEnd(TickEvent.ClientTickEvent ev) {
		// Put away pet when gui is opened. This is a nerf and a fix for lag
		// induced duping.
		try {
			if (Minecraft.getMinecraft().currentScreen instanceof RpgGui) {

				ByteBuf buf = Unpooled.buffer();
				ByteBufOutputStream out = new ByteBufOutputStream(buf);
				out.writeInt(RBServerPacketHandler.STOREPET);
				RpgInventoryMod.Channel.sendToServer(new FMLProxyPacket(buf, "R_BChannel"));
				out.close();

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

									for (int i = 0; i < currentButtons.size(); i++) {
										if (currentButtons.get(i) instanceof ButtonPetGui) {
											return;
										}
									}

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
				} else {
					added = false;
				}
			}
			else {
				added = false;
			}

		} catch (Exception e) {

		}
	}
}
