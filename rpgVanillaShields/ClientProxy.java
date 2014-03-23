package rpgVanillaShields;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldDiamond, new VanillaShieldRenderer(
						new VanillaShield(),
						"subaraki:jewels/ShieldDiamond.png"));
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldGold, new VanillaShieldRenderer(
						new VanillaShield(), "subaraki:jewels/ShieldGold.png"));
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldIron, new VanillaShieldRenderer(
						new VanillaShield(), "subaraki:jewels/ShieldIron.png"));
		MinecraftForgeClient.registerItemRenderer(
				mod_VanillaShields.shieldWood, new VanillaShieldRenderer(
						new VanillaShield(), "subaraki:jewels/ShieldWood.png"));
	}
}
