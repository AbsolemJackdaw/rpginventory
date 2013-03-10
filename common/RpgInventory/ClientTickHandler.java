//package RpgInventory;
//
//import java.util.EnumSet;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.player.EntityPlayer;
//import RpgInventory.gui.inventory.RpgInventory;
//import cpw.mods.fml.common.ITickHandler;
//import cpw.mods.fml.common.TickType;
//
//public class ClientTickHandler implements ITickHandler {
//
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {
//		//System.out.println("Class ClientTickHandler called");
//
//	}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
//
//		EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().thePlayer;
//		RpgInventory rpg = AARpgBaseClass.proxy.getInventory(player.username);
//
//		if(player.getHealth() <= 0)
//		{ 
//			if (!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
//			{
//				rpg.dropJewels(player);
//
//			}
//		}
//		if(player.isDead)
//		{ 
//			if (!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
//			{
//				rpg.dropJewels(player);
//
//			}
//		}
//
//	}
//
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.CLIENT);
//	}
//
//	@Override
//	public String getLabel() {
//		return "RpgInventory";
//	}
//
//}
