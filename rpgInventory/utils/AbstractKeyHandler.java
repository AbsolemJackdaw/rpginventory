package rpgInventory.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public abstract class AbstractKeyHandler extends KeyBindingRegistry.KeyHandler {

	private static KeyBinding keyInventory = new KeyBinding("RPG Inventory Key", Keyboard.KEY_END);
	private static KeyBinding keySpecial = new KeyBinding("RPG Special Ability", Keyboard.KEY_F);
	private static boolean [] reps = new boolean[]{false, false};

	private static KeyBinding[] bindKeys = new KeyBinding[]{keyInventory, keySpecial};
	
	public AbstractKeyHandler(KeyBinding[] keyBindings, boolean[] repeatings) {
//		super( bindKeys,  reps);
		this();
	}
	
	public AbstractKeyHandler (){
		super(bindKeys, reps);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Rpg Inventory KeyHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
		// TODO Auto-generated method stub

	}
	
	protected abstract void specialAbility(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, ItemStack item);

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (!tickEnd) {
			return;
		}
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (kb.keyDescription.equals("RPG Special Ability")) {
				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
				if (guiscreen == null && !(item == null)) {
					specialAbility(types, kb, tickEnd, item);
				}
			}else if (kb.keyDescription.equals("RPG Inventory Key")) {
				if (guiscreen instanceof GuiInventory || guiscreen instanceof GuiContainerCreative) {
					int i = 1;
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					ObjectOutput out;
					DataOutputStream outputStream = new DataOutputStream(bytes);
					try {
						outputStream.writeInt(i);
						Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
						PacketDispatcher.sendPacketToServer(packet);
						//System.out.println("Packet send");
					} catch (IOException e) {
						e.printStackTrace();
					}
					//System.out.println("opened rpg gui");
				}
			}
		} catch (Throwable e) {
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
