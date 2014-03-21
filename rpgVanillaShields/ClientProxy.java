package rpgVanillaShields;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldDiamond.itemID,
				(IItemRenderer) new VanillaShieldRenderer(new VanillaShield(),
						"subaraki:jewels/ShieldDiamond.png"));
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldGold.itemID,
				(IItemRenderer) new VanillaShieldRenderer(new VanillaShield(),
						"subaraki:jewels/ShieldGold.png"));
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldIron.itemID,
				(IItemRenderer) new VanillaShieldRenderer(new VanillaShield(),
						"subaraki:jewels/ShieldIron.png"));
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldWood.itemID,
				(IItemRenderer) new VanillaShieldRenderer(new VanillaShield(),
						"subaraki:jewels/ShieldWood.png"));
	}
}
