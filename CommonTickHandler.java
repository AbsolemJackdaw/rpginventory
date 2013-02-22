package RpgInventory;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import RpgInventory.gui.inventory.RpgInventory;
import RpgInventory.item.armor.ItemRpgArmor;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;


public class CommonTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		List<EntityPlayer> players = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).playerEntityList;
		for(EntityPlayer player : players)
		{
			RpgInventory inv = AARpgBaseClass.proxy.getInventory(player.username);
			for(ItemStack is : inv.armorSlots)
			{
				if(is == null)
					continue;
				ItemRpgArmor item = (ItemRpgArmor) is.getItem();
				item.armorEffects(is,player, inv);

			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return "RpgInventoryServ";
	}

}
