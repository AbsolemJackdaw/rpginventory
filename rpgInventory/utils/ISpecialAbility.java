package rpgInventory.utils;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ISpecialAbility {
		
	public void specialAbility(ItemStack item);
	public static Map<Item, Integer> abilityMap = new HashMap();

 }
