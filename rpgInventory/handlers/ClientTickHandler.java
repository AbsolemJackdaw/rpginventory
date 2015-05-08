package rpgInventory.handlers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import rpgInventory.gui.ButtonInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientTickHandler /* implements ITickHandler */{

	boolean added = false;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void clientTick(TickEvent.ClientTickEvent ev) {

//		net.minecraft.entity.player.EntityPlayer p = Minecraft.getMinecraft().thePlayer;
//		System.out.println(p.getDisplayName() + " " +rpgInventory.gui.rpginv.PlayerRpgInventory.get(p).armorSlots.toString());
//		
//		System.out.println(rpgInventory.gui.rpginv.PlayerRpgInventory.get(p).getShield());
		
		// This will only inject our buttons into the existing GuiInventory
		// object.
		// The button prevents calls to the parent GUI if clicked, and calls our
		// packet
		// instead. I see no incompatibilies.
		if ((Minecraft.getMinecraft().currentScreen != null)
				&& (Minecraft.getMinecraft().theWorld != null)
				&& (Minecraft.getMinecraft().thePlayer != null)) {
			if ((Minecraft.getMinecraft().currentScreen instanceof GuiInventory)
					|| (Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative)) {
				if (!added) {
					added = true;
					GuiScreen gui = Minecraft.getMinecraft().currentScreen;
					List currentButtons = null;
					for (Field f : GuiScreen.class.getDeclaredFields()) {
						f.setAccessible(true);
						try {
							// "controlList" is the name deobfuscated. "i" is
							// the name obfustcated.
							// This adds compatibility when developing AND on
							// release.
							if (f.getName().equals("buttonList")
									|| f.getName().equals("field_146292_n")) {
								Field modfield = Field.class
										.getDeclaredField("modifiers");
								modfield.setAccessible(true);
								modfield.setInt(f, f.getModifiers()
										& ~Modifier.PROTECTED);
								currentButtons = (List) f.get(gui);
								int offsetx = 80;
								int offsety = 93;
								int posX = (Minecraft.getMinecraft().currentScreen.width) / 2;
								int posY = (Minecraft.getMinecraft().currentScreen.height) / 2;
								currentButtons.add(new ButtonInventory(
										currentButtons.size() + 1, posX
										- offsetx, posY + offsety, 80,
										20, "Rpg Inventory"));
								currentButtons.add(new ButtonInventory(
										currentButtons.size() + 1,
										((posX - offsetx) + 80 + 15), posY
										+ offsety, 80, 20, "Close"));
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
		} else {
			added = false;
		}
	}
}