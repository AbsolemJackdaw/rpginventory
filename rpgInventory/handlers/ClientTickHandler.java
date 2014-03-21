package rpgInventory.handlers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.ButtonInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.utils.AbstractArmor;

public class ClientTickHandler implements ITickHandler {

	boolean added = false;

	public static final int BOOTS = 36;
	public static final int LEGS = 37;
	public static final int CHEST = 38;
	public static final int HELM = 39;

	@Override
	public String getLabel() {
		return "rpgInventory";
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		/**
		 * This checks wether the player wears class armor, and a shield, or
		 * just a shield (like vanilla shields)
		 */
		if (Minecraft.getMinecraft().thePlayer != null) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			boolean skip = false;
			for (ItemStack is : player.inventory.armorInventory) {
				if (is == null) {
					if (PlayerRpgInventory.get(player).getShield() == null) {
						skip = true;
						mod_RpgInventory.playerClass = "none";
					}
				} else {
					// if there is one item that is no AbstractArmor, skip the
					// setting of the playerclass
					if (!(is.getItem() instanceof AbstractArmor)
							&& (PlayerRpgInventory.get(player).getShield() == null)) {
						skip = true;
						mod_RpgInventory.playerClass = "none";
					}
				}
			}
			if (!skip) {
				if ((player.inventory.getStackInSlot(HELM) != null)
						&& ((player.inventory.getStackInSlot(HELM).getItem()) instanceof AbstractArmor)
						&& (player.inventory.getStackInSlot(CHEST) != null)
						&& ((player.inventory.getStackInSlot(CHEST).getItem()) instanceof AbstractArmor)
						&& (player.inventory.getStackInSlot(LEGS) != null)
						&& ((player.inventory.getStackInSlot(LEGS).getItem()) instanceof AbstractArmor)
						&& (player.inventory.getStackInSlot(BOOTS) != null)
						&& ((player.inventory.getStackInSlot(BOOTS).getItem()) instanceof AbstractArmor)) {

					// this could be any piece of armor, given
					String classname = ((AbstractArmor) player.inventory
							.getStackInSlot(HELM).getItem()).armorClassName();

					mod_RpgInventory.playerClass = classname;

					if (PlayerRpgInventory.get(player).getShield() != null) {
						if (((ItemRpgInvArmor) PlayerRpgInventory.get(player)
								.getShield().getItem()).boundArmorClass()
								.equals(classname)) {
							mod_RpgInventory.playerClass = classname
									+ ((ItemRpgInvArmor) PlayerRpgInventory
											.get(player).getShield().getItem())
											.shieldClass();
						}
					}
				} else {
					mod_RpgInventory.playerClass = "none";
					if (((ItemRpgInvArmor) PlayerRpgInventory.get(player)
							.getShield().getItem()).boundArmorClass().equals(
							"none")) {
						if (PlayerRpgInventory.get(player).getShield() != null) {
							mod_RpgInventory.playerClass = ((ItemRpgInvArmor) PlayerRpgInventory
									.get(player).getShield().getItem())
									.shieldClass();
						}
					}
				}
			}
		}

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
									|| f.getName().equals("field_73887_h")) {
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
												- offsetx, posY + offsety,
										"Rpg Inventory"));
								currentButtons.add(new ButtonInventory(
										currentButtons.size() + 1,
										(posX - offsetx) + 80 + 15, posY
												+ offsety, "Close"));
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

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}
}