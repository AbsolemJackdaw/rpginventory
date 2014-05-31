package addonDread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DreadEventHooks {
	public static Map<String, Integer> CustomPotionList = new ConcurrentHashMap();

	@SubscribeEvent
	public void PlayerUpdate(PlayerEvent.LivingUpdateEvent evt) {

		try {
			if (evt.entityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) evt.entityLiving;
				if (p != null) {
					// TODO place potion effects under addonDread
					if ((p.getActivePotionEffects() != null)
							&& (p.getActivePotionEffects().size() > 0)) {
						PotionEffect decompose = p
								.getActivePotionEffect(RpgDreadAddon.decomposePotion);
						PotionEffect machicism = p
								.getActivePotionEffect(RpgDreadAddon.masochismPotion);
						if ((decompose != null) && (machicism != null)) {
							p.removePotionEffect(RpgDreadAddon.decomposePotion.id);
							p.removePotionEffect(RpgDreadAddon.masochismPotion.id);
							CustomPotionList.remove(p.getCommandSenderName());
						} else if (decompose != null) {
							if (decompose.getDuration() == 0) {
								p.removePotionEffect(RpgDreadAddon.decomposePotion.id);
								CustomPotionList.remove(p
										.getCommandSenderName());
							} else if (!CustomPotionList.containsKey(p
									.getCommandSenderName())) {
								CustomPotionList.put(p.getCommandSenderName(),
										decompose.getDuration());
							}
						} else if (machicism != null) {
							if (machicism.getDuration() == 0) {
								p.removePotionEffect(RpgDreadAddon.masochismPotion.id);
								CustomPotionList.remove(p
										.getCommandSenderName());
							} else if (!CustomPotionList.containsKey(p
									.getCommandSenderName())) {
								CustomPotionList.put(p.getCommandSenderName(),
										machicism.getDuration());
							}
						}
					}
				}
			}
		} catch (Throwable e) {

		}
	}
}
