package addonMasters;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import rpgInventory.config.RpgConfig;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.utils.RpgUtility;
import addonMasters.entity.EntityPetXP;
import addonMasters.entity.EntityTeleportStone;
import addonMasters.entity.models.ModelBoar;
import addonMasters.entity.models.ModelBull;
import addonMasters.entity.models.ModelRooster;
import addonMasters.entity.models.ModelRpgSpider;
import addonMasters.entity.models.ModelSpiderDub;
import addonMasters.entity.pet.BoarPet;
import addonMasters.entity.pet.BullPet;
import addonMasters.entity.pet.ChickenPet;
import addonMasters.entity.pet.SpiderPet;
import addonMasters.entity.renderers.RenderPet;
import addonMasters.models.LionHead;
import addonMasters.models.ModelBeastArmor;
import addonMasters.models.ModelRogueArmor;
import addonMasters.render.AxeRender;
import addonMasters.render.LionHeadRenderer;
import addonMasters.render.RenderDagger;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class RBClientProxy extends RBCommonProxy {

	private static final ModelRpgSpider spiderRpg = new ModelRpgSpider();
	private static final ModelSpiderDub spider = new ModelSpiderDub();
	private static final ModelRooster rooster = new ModelRooster();
	private static final ModelChicken chick = new ModelChicken();
	private static final ModelBull bull = new ModelBull();
	private static final ModelCow cow = new ModelCow();
	private static final ModelBoar boar = new ModelBoar();
	private static final ModelPig pig = new ModelPig();
	
	
	private static final ModelRogueArmor armorRogueChest = new ModelRogueArmor(
			1.0f);

	private static final ModelRogueArmor armorRogue = new ModelRogueArmor(0.5f);
	private static final ModelBeastArmor armorBeastChest = new ModelBeastArmor(
			1.0f);
	private static final ModelBeastArmor armorBeast = new ModelBeastArmor(0.5f);

	
	@Override
	public ModelBase getpetmodel(int i) {
		if(i == 0)
			return spider;
		if(i == 1)
			return spiderRpg;
		
		if(i == 2)
			return chick;
		if(i == 3)
			return rooster;
		
		if(i == 4)
			return cow;
		if(i == 5)
			return bull;
		
		if(i == 6)
			return pig;
		if(i == 7)
			return boar;
		
		return null;
	}
	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		case 0:
			return armorRogueChest;
		case 1:
			return armorRogue;
		case 2:
			return armorBeastChest;
		case 3:
			return armorBeast;
		default:
			break;
		}
		return null;
	}

	@Override
	public void openGUI(EntityPlayer p1, PlayerRpgInventory inv) {
		Minecraft.getMinecraft().displayGuiScreen(
				new addonMasters.PetGui(p1, inv));
	}

	@Override
	public void registerRendering() {

		if (RpgConfig.instance.render3D == true) {
			MinecraftForgeClient.registerItemRenderer(RpgMastersAddon.beastAxe,new AxeRender());
			MinecraftForgeClient.registerItemRenderer(RpgMastersAddon.daggers,new RenderDagger());

			MinecraftForgeClient.registerItemRenderer(RpgMastersAddon.beastShield,new LionHeadRenderer(new LionHead(),"subaraki:jewels/lion.png"));
		}

		RenderingRegistry.registerEntityRenderingHandler(BullPet.class,new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(SpiderPet.class,new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(BoarPet.class,new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(ChickenPet.class,new RenderPet());
		RenderingRegistry.registerEntityRenderingHandler(EntityPetXP.class,new RenderXPOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportStone.class,new RenderSnowball(Items.feather, 1));

		MinecraftForge.EVENT_BUS.register(new SoundManager());

		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());

		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		
		WeaponAbility ability = new WeaponAbility();
		RpgUtility.registerSpecialAbility(RpgMastersAddon.beastAxe, ability);
		RpgUtility.registerSpecialAbility(RpgMastersAddon.daggers, ability);
	}
}
