//package rpgInventory.utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutput;
//import java.util.ArrayList;
//import java.util.EnumSet;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.GuiScreen;
//import net.minecraft.client.gui.inventory.GuiContainerCreative;
//import net.minecraft.client.gui.inventory.GuiInventory;
//import net.minecraft.client.settings.KeyBinding;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.packet.Packet250CustomPayload;
//
//import org.lwjgl.input.Keyboard;
//
//import cpw.mods.fml.client.registry.KeyBindingRegistry;
//import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
//import cpw.mods.fml.common.TickType;
//import cpw.mods.fml.common.network.PacketDispatcher;
//
//public class AbstractKeyHandler extends KeyBindingRegistry.KeyHandler {
//
//	protected static KeyBinding keyInventory = new KeyBinding("RPG Inventory Key", Keyboard.KEY_END);
//	protected static KeyBinding keySpecial = new KeyBinding("RPG Special Ability", Keyboard.KEY_F);
//	public static boolean [] reps = new boolean[]{false, false};
//
//	public static KeyBinding[] bindKeys = new KeyBinding[]{keyInventory, keySpecial};
//
//	public static boolean hasRegistered = false;
//
//	public AbstractKeyHandler(KeyBinding[] keyBindings, boolean[] repeatings) {
//		super(registeredKeyBinds.toArray(new KeyBinding[registeredKeyBinds.size()]), new boolean[registeredKeyBinds.size()]);
//	}
//
//	public static Map<KeyBinding, List<AbstractKeyHandler>> keyHandlers = new HashMap();
//	public static List<KeyBinding> registeredKeyBinds = new ArrayList();
//
//	public static void registerKeyhandler(AbstractKeyHandler keyhandler, KeyBinding[] keyBindings, boolean[] repeatings) {
//		for(KeyBinding thisKB: keyBindings){
//			if(!registeredKeyBinds.contains(thisKB)){
//				registeredKeyBinds.add(thisKB);
//			}
//			List<AbstractKeyHandler> keylist = keyHandlers.get(thisKB);
//			if(keylist == null){
//				keylist = new ArrayList();
//				keyHandlers.put(thisKB,keylist );
//			}
//			keylist.add(keyhandler);
//		}
//	}
//
////	public static void keybindEvent1Called(KeyBinding keybinding){
////		List<KeyHandler> keyhandlers = keyHandlers.get(keybinding);
////		if(keyhandlers != null && keyhandlers.size() > 0){
////			for(KeyHandler thisKH: keyhandlers){
////				keybindEvent1Called(keybinding);
////			}
////		}
////	}
//
//	@Override
//	public String getLabel() {
//		return "Rpg Inventory KeyHandler";
//	}
//
//	@Override
//	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
//			boolean tickEnd, boolean isRepeat) {
//
//	}
//
//
//	/**this method gets called when pressed the special ability button. use this to add your*/
//	protected void specialAbility(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, ItemStack item){
//
//	}
//
//	@Override
//	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
//		if (!tickEnd) {
//			return;
//		}
//		try {
//			Minecraft mc = Minecraft.getMinecraft();
//			GuiScreen guiscreen = mc.currentScreen;
//			if (kb.keyDescription.equals("RPG Special Ability")) {
//				ItemStack item = mc.thePlayer.getCurrentEquippedItem();
//				if (guiscreen == null && !(item == null)) {
//					specialAbility(types, kb, tickEnd, item);
//				}
//			}else if (kb.keyDescription.equals("RPG Inventory Key")) {
//				if (guiscreen instanceof GuiInventory || guiscreen instanceof GuiContainerCreative) {
//					int i = 1;
//					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//					ObjectOutput out;
//					DataOutputStream outputStream = new DataOutputStream(bytes);
//					try {
//						outputStream.writeInt(i);
//						Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
//						PacketDispatcher.sendPacketToServer(packet);
//						//System.out.println("Packet send");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					//System.out.println("opened rpg gui");
//				}
//			}
//		} catch (Throwable e) {
//		}
//	}
//
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.CLIENT);
//	}
//
// }
