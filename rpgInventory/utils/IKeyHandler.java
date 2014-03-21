package rpgInventory.utils;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public interface IKeyHandler {

	static KeyBinding keyInventory = new KeyBinding("RPG Inventory Key",
			Keyboard.KEY_END);
	static KeyBinding keySpecial = new KeyBinding("RPG Special Ability",
			Keyboard.KEY_F);
	public static boolean[] reps = new boolean[] { false, false };

	public static KeyBinding[] bindKeys = new KeyBinding[] { keyInventory,
			keySpecial };

	public String getLabel();

	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat);

	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd);

	public void specialAbility(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, ItemStack item);

	public EnumSet<TickType> ticks();
}
