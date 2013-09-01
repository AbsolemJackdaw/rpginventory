package rpgInventory.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.gui.rpginv.RpgInv;
import rpgInventory.item.armor.BonusArmor;
import rpgInventory.models.armor.ModelBeastArmor;
import rpgInventory.models.armor.ModelBerserkerArmor;
import rpgInventory.models.armor.ModelMageArmor;
import rpgInventory.models.armor.ModelNecroArmor;
import rpgInventory.models.armor.ModelRogueArmor;
import rpgInventory.models.jewels.GloveLeft;
import rpgInventory.models.jewels.GloveRight;
import rpgInventory.models.jewels.ModelNecklace;
import rpgInventory.models.shields.Book;
import rpgInventory.models.shields.IronThorn;
import rpgInventory.models.shields.LionHead;
import rpgInventory.models.shields.ModelShield;
import rpgInventory.models.shields.NecroShield;
import rpgInventory.models.shields.PalaShield;
import rpgInventory.models.shields.VanillaShield;
import rpgInventory.models.weapons.ModelDaggerL;
import rpgInventory.models.weapons.NecroSkull;



public class RenderPlayerJewels extends RenderPlayer {

	public static Map<Class<? extends Entity>, Render> defaultPlayerRender = new HashMap();

	public static RenderPlayerJewels instance;
	private ModelBiped modelBipedMain;
	private ModelShield modelShield;
	private IronThorn ironthorn;
	private NecroShield necroshield;
	private PalaShield palashield;
	private VanillaShield vanillaShield;
	private static NecroSkull skull;
	private GloveRight rightglove;
	private GloveLeft leftglove;
	private ModelNecklace necklace;
	private ModelDaggerL dagger;
	private LionHead lionhead;
	private Book book;
	public ModelBeastArmor beastarmor;
	public ModelBeastArmor beastarmorChest;
	public ModelMageArmor mageArmor;
	public ModelMageArmor mageArmorChest;
	public ModelNecroArmor necroArmor;
	public ModelNecroArmor necroArmorChest;
	public ModelRogueArmor rogueArmor;
	public ModelRogueArmor rogueArmorChest;
	public ModelBerserkerArmor berserkArmor;
	public ModelBerserkerArmor berserkarmorChest;
	public ItemStack col;
	public ItemStack shield;
	public ItemStack cloak;
	public ItemStack want;
	public int rotation = 0;
	private Minecraft mc;
	RpgInv rpg;

	public RenderPlayerJewels(ModelBase model) {// the name of this class sounds so wrong ... ._.

		super();
		instance = this;
		this.modelBipedMain = (ModelBiped) this.mainModel;
		this.modelShield = new ModelShield();
		this.rightglove = new GloveRight();
		this.leftglove = new GloveLeft();
		this.necklace = new ModelNecklace();
		this.ironthorn = new IronThorn();
		this.necroshield = new NecroShield();
		this.palashield = new PalaShield();
		this.vanillaShield = new VanillaShield();
		this.dagger = new ModelDaggerL();
		this.lionhead = new LionHead();
		this.book = new Book();
		if (mod_RpgInventory.hasRpg) {
			this.skull = new NecroSkull();
		}
		mc = Minecraft.getMinecraft();

		beastarmor = new ModelBeastArmor(0.5F, 0.0F, 64, 32);
		beastarmorChest = new ModelBeastArmor(1.0F, 0.0F, 64, 32);
		mageArmor = new ModelMageArmor(0.5f, 0.0f, 65, 64);
		mageArmorChest = new ModelMageArmor(1.0f, 0.0f, 65, 64);
		necroArmor = new ModelNecroArmor(0.5f, 0.0f, 65, 64);
		necroArmorChest = new ModelNecroArmor(1.0f, 0.0f, 65, 64);
		rogueArmor = new ModelRogueArmor(0.5f, 0.0f, 65, 64);
		rogueArmorChest = new ModelRogueArmor(1.0f, 0.0f, 65, 64);
		berserkArmor = new ModelBerserkerArmor(0.5f, 0.0f, 65, 64);
		berserkarmorChest = new ModelBerserkerArmor(1.0f, 0.0f, 65, 64);

	}

	protected void renderSpecialsRpg(EntityPlayer player, float par2) {
		ItemStack chest = player.inventory.armorItemInSlot(2);
		ItemStack hoed = player.inventory.armorItemInSlot(3);
		GL11.glColor4f(1, 1, 1, 1);
		this.modelBipedMain.heldItemLeft = 0;
		rpg = mod_RpgInventory.proxy.getInventory(player.username);
		float var11;

		//INSERT CODE HERE
		if (rpg != null) {
			col = rpg.getNecklace();
			shield = rpg.getShield();
			cloak = rpg.getCloak();
			want = rpg.getGloves();
		}

		if (cloak != null && !player.getHideCape()) {
			if (cloak.getItem() == mod_RpgInventory.cloak
					|| cloak.getItem() == mod_RpgInventory.cloakYellow
					|| cloak.getItem() == mod_RpgInventory.cloakRed
					|| cloak.getItem() == mod_RpgInventory.cloakBlue
					|| cloak.getItem() == mod_RpgInventory.cloakGreen
					|| cloak.getItem() == mod_RpgInventory.cloakSub) {
				GL11.glPushMatrix();

				if (cloak.getItem() == mod_RpgInventory.cloak) {
					/**
					 * Dev Capes
					 */
					if (player.username.equals("TheCodyMaverick")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/ACCape.png"));
					}else if (player.username.equals("ClaireBearKitty ")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/ponyCape.png"));
					}else if (player.username.equals("Unjustice")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/UnCape.png"));
					} else if (player.username.equals("NyhmsQuest")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/NymCape.png"));
					} else if (player.username.equals("Joebuz")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/BuzCape.png"));
					} else if (player.username.equals("superv20")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/MRSCape.png"));
					} else if (player.username.equals("VIruS_Ex")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/PreCape.png"));
					} else if (player.username.equals("AbrarSyed")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/AbrCape.png"));
					} else if (player.username.equals("rich1051414")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/TarCape.png"));
					} else if (player.username.equals("LegendaryKoala")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/CoaCape.png"));
					} else if (player.username.equals("TheCowTheory")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/CowCape.png"));
					}else if (player.username.equals("4wad")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/ModelCape.png"));
					}else if (player.username.equals("Zxapa") || player.username.equals("spineripper64 ")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/ConCape.png"));
					} else if (player.username.equals("Artix_all_mighty")) {
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:devcapes/SubCape.png"));
					} else{ // if none of the names match, it should return a grey cape texture.
						mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:capes/GreyCape.png"));
					}
				} else if (cloak.getItem() == mod_RpgInventory.cloakYellow) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:capes/GoldCape.png"));
				} else if (cloak.getItem() == mod_RpgInventory.cloakRed) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:capes/RedCape.png"));
				} else if (cloak.getItem() == mod_RpgInventory.cloakGreen) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:capes/GreenCape.png"));
				} else if (cloak.getItem() == mod_RpgInventory.cloakBlue) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:capes/SkyCape.png"));
				} else if (cloak.getItem() == mod_RpgInventory.cloakSub) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:capes/BlaCape.png"));
				}


				GL11.glTranslatef(0.0F, 0.0F, 0.125F);
				double var22 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * (double) par2 - (player.prevPosX + (player.posX - player.prevPosX) * (double) par2);
				double var24 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * (double) par2 - (player.prevPosY + (player.posY - player.prevPosY) * (double) par2);
				double var9 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * (double) par2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double) par2);
				var11 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2;
				double var12 = (double) MathHelper.sin(var11 * (float) Math.PI / 180.0F);
				double var14 = (double) (-MathHelper.cos(var11 * (float) Math.PI / 180.0F));
				float var16 = (float) var24 * 10.0F;

				if (var16 < -6.0F) {
					var16 = -6.0F;
				}

				if (var16 > 32.0F) {
					var16 = 32.0F;
				}

				float var17 = (float) (var22 * var12 + var9 * var14) * 100.0F;
				float var18 = (float) (var22 * var14 - var9 * var12) * 100.0F;

				if (var17 < 0.0F) {
					var17 = 0.0F;
				}

				float var19 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * par2;
				var16 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * var19;

				if (player.isSneaking()) {
					var16 += 25.0F;
				}
				GL11.glRotatef(6.0F + var17 / 2.0F + var16, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var18 / 2.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-var18 / 2.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				this.modelBipedMain.renderCloak(0.0625F);
				GL11.glPopMatrix();
			}
		}


		float scale = 55f;

		// GLOVES
		if (want != null) {
			GL11.glPushMatrix();
			if (want.getItem() == mod_RpgInventory.glovesbutter) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/Glove.png"));
			} else if (want.getItem() == mod_RpgInventory.glovesdia) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/GloveDia.png"));
			} else if (want.getItem() == mod_RpgInventory.glovesem) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/GloveEm.png"));
			} else if (want.getItem() == mod_RpgInventory.gloveslap) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/GloveLap.png"));
			}

			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointX, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointZ, 0.0F, 0.0F, 1.0F);

			GL11.glScalef(1.125F, 1.125F, 1.125F);
			GL11.glTranslatef(-0.05F, 0F, -0.04F);

			boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render)
			{
				leftglove.renderLeftGlove(0.0625f);
			}

			GL11.glPopMatrix();

			GL11.glPushMatrix();
			if (want.getItem() == mod_RpgInventory.glovesbutter) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/Glove.png"));
			} else if (want.getItem() == mod_RpgInventory.glovesdia) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/GloveDia.png"));
			} else if (want.getItem() == mod_RpgInventory.glovesem) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/GloveEm.png"));
			} else if (want.getItem() == mod_RpgInventory.gloveslap) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/GloveLap.png"));
			}
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleX * scale, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleY * scale, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleZ * scale, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointX, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointZ, 0.0F, 0.0F, 1.0F);

			GL11.glScalef(1.125f, 1.125F, 1.125F);
			GL11.glTranslatef(0.05F, 0F, 0.05F);
			if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render)
			{
				rightglove.renderRightGlove(0.0625f);
			}

			GL11.glPopMatrix();

		}

		if (col != null) {
			if (col.getItem() == mod_RpgInventory.neckdia || col.getItem() == mod_RpgInventory.neckgold
					|| col.getItem() == mod_RpgInventory.necklap || col.getItem() == mod_RpgInventory.neckem) {
				GL11.glPushMatrix();

				if (col.getItem() == mod_RpgInventory.neckdia) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/NeckDia.png"));
				} else if (col.getItem() == mod_RpgInventory.neckem) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/NeckEm.png"));
				} else if (col.getItem() == mod_RpgInventory.neckgold) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/NeckGold.png"));
				} else if (col.getItem() == mod_RpgInventory.necklap) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/NeckLap.png"));
				}
				GL11.glRotatef(this.modelBipedMain.bipedBody.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedBody.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedBody.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);

				//System.out.println(chest);
				if (chest != null) {
					GL11.glTranslatef(0F, 0F, -0.06F);
				} else {
					GL11.glTranslatef(0F, 0F, 0.01F);
				}
				GL11.glScalef(1F, 1F, 1F);
				necklace.renderNecklace(0.0625f);

				GL11.glPopMatrix();
			}
		}

		//SHIELD
		if (shield != null) {
			this.modelBipedMain.heldItemLeft = 0;

			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

			GL11.glPushMatrix();
			if (mod_RpgInventory.hasRogue == true && shield.getItem() == mod_RpgInventory.daggers) {

				if (player.worldObj.isDaytime()) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/dagger.png"));
				} else {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:weapons/daggerNight.png"));
				}
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);

				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glTranslatef(0.8F, 1.2F, 0.3F);

				GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-90F, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(50F, 0.0f, 0.0f, 1.0f);

				dagger.renderDagger(0.0625f);
				this.modelBipedMain.heldItemLeft = 1;
				//this.setRenderPassModel(dagger);

			} else if (mod_RpgInventory.hasRogue == true && shield.getItem() == mod_RpgInventory.beastShield) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/lion.png"));

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);

				GL11.glScalef(1F, 1F, 1F);
				GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(180, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
				GL11.glTranslatef(-0F, 0.2F, -0.9F);
				boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
					lionhead.render(0.0625f);
				}

			} else if (shield.getItem() == mod_RpgInventory.archersShield) {

				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/Shield1.png"));

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glTranslatef(-0.16F, -0.1F, 0F);

				boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
					modelShield.renderShield(0.0625f);
				}

			} else if (shield.itemID == mod_RpgInventory.talisman.itemID) {
				rotation++;
				if (rotation == 360) {
					rotation = 0;
				}
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/talisman.png"));

				GL11.glScalef(3F, 3F, 3F);
				GL11.glTranslatef(-0.5F, -0.4F, -0.5F);
				if (player == Minecraft.getMinecraft().thePlayer) {
					if (!((Minecraft.getMinecraft().currentScreen instanceof GuiInventory
							|| Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative
							|| Minecraft.getMinecraft().currentScreen instanceof RpgGui)
							&& RenderManager.instance.playerViewY == 180.0F)) {
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glDisable(GL11.GL_LIGHTING);
						GL11.glEnable(GL11.GL_TEXTURE_2D);
						GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
						GL11.glRotatef(rotation, 1.5F, 1.5F, 1.5F);
						GL11.glCallList(mod_RpgInventory.proxy.getSphereID());
					}
				} else {
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
					GL11.glRotatef(rotation, 1.5F, 1.5F, 1.5F);
					GL11.glCallList(mod_RpgInventory.proxy.getSphereID());
				}
			} // bypass import for mod_RpgPlus
			else if (mod_RpgInventory.hasRpg && shield.getItem() == mod_RpgInventory.necro_shield) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/NecroShield.png"));

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);

				GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);

				float x = 0.8f;
				GL11.glScalef(x, x, x);
				GL11.glTranslatef(0F, 0.8F, -0.85F);

				boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
					necroshield.renderShield(0.0625f);
				}

			} else if (mod_RpgInventory.hasRpg && shield.getItem() == mod_RpgInventory.pala_shield) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/PaladinShield.png"));

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1.2F, 1.2F, 1.2F);
				GL11.glTranslatef(-0.05F, 0F, 0.0F);

				boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
					palashield.renderShield(0.0625f);
				}

			} else if (shield.getItem() == mod_RpgInventory.berserkerShield) {
				mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/IronThorn.png"));

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1F, 1F, 1F);
				GL11.glTranslatef(0.018F, -0.1F, 0F);

				boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
					ironthorn.renderShield(0.0625f);
				}
			} else if (mod_RpgInventory.hasShields == true) {
				if (shield.getItem() == mod_RpgInventory.shieldWood) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/ShieldWood.png"));

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
						vanillaShield.renderShield(0.0625f);
					}
				} else if (shield.getItem() == mod_RpgInventory.shieldIron) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/ShieldIron.png"));

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
						vanillaShield.renderShield(0.0625f);
					}
				} else if (shield.getItem() == mod_RpgInventory.shieldGold) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/ShieldGold.png"));

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
						vanillaShield.renderShield(0.0625f);
					}
				} else if (shield.getItem() == mod_RpgInventory.shieldDiamond) {
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/ShieldDiamond.png"));

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					if (modelBipedMain.aimedBow == false) 
					{
						vanillaShield.renderShield(0.0625f);
					}
				}
			}
			if (mod_RpgInventory.hasMage == true) {
				if (shield.getItem() == mod_RpgInventory.archBook) {
					rotation++;
					if (rotation == 360) {
						rotation = 0;
					}
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/book.png"));
					GL11.glPushMatrix();
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glRotatef(65, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
					GL11.glTranslatef(-0.35F, -0.25F, 0.65F);

					boolean render = true; if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render) {
						book.render(0.0625f);
					}
					GL11.glPopMatrix();
					mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/magemantle.png"));

					GL11.glScalef(3F, 3F, 3F);
					GL11.glTranslatef(-0.5F, -0.4F, -0.5F);
					if (player == Minecraft.getMinecraft().thePlayer) {
						if (!((Minecraft.getMinecraft().currentScreen instanceof GuiInventory
								|| Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative
								|| Minecraft.getMinecraft().currentScreen instanceof RpgGui)
								&& RenderManager.instance.playerViewY == 180.0F)) {
							GL11.glEnable(GL11.GL_BLEND);
							GL11.glDisable(GL11.GL_LIGHTING);
							GL11.glEnable(GL11.GL_TEXTURE_2D);
							GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
							GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);
							GL11.glRotatef(rotation, 1.5F, 1.5F, 1.5F);
							GL11.glCallList(mod_RpgInventory.proxy.getSphereID());
						}
					} else {
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
						GL11.glRotatef(rotation, 1.5F, 1.5F, 1.5F);
						GL11.glCallList(mod_RpgInventory.proxy.getSphereID());
					}
				}
			}
			GL11.glColor4f(1, 1, 1, 1);
			GL11.glPopMatrix();
			GL11.glPopAttrib();
		}
	}

	@Override
	protected void renderSpecials(AbstractClientPlayer par1EntityPlayer, float par2) {
		this.renderSpecialsRpg(par1EntityPlayer, par2);
	}

	@Override
	protected int setArmorModel(AbstractClientPlayer par1EntityPlayer, int par2, float par3) {
		//Always set the armor pieces to not show before testing each render pass.
		//Our custom armor render only has non-renderinging models, to serve
		//as a parent for our rendering, which are attached as children to them.
		this.beastarmor.showBeastHelmet(false);
		this.beastarmor.showBeastSpaulders(false);
		this.mageArmor.showMageHat(false);
		this.mageArmor.showMageChest(false);
		this.mageArmor.showMageArms(false);
		this.mageArmor.showMageFeet(false);
		this.rogueArmor.showBoots(false);
		this.rogueArmor.showKnee(false);
		this.berserkArmor.showHorns(false);
		this.berserkArmor.showSpaulders(false);

		ItemStack var4 = par1EntityPlayer.inventory.armorItemInSlot(3 - par2);
		if (var4 != null) {
			Item var5 = var4.getItem();
			if (var5 instanceof BonusArmor) {
				BonusArmor var6 = (BonusArmor) var5;
				//BeastMaster
				if (var6.itemID == mod_RpgInventory.beastHood.itemID) {
					//here we can load our custom texture file
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:beast_3.png"));
					//here we can turn on this rendering for this armor
					this.beastarmor.showBeastHelmet(true);
				} else if (var6.itemID == mod_RpgInventory.beastChest.itemID) {
					//here we can load our custom texture file
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:beast_3.png"));
					//here we can turn on this rendering for this armor
					this.beastarmor.showBeastSpaulders(true);
				}
				//Mage
				else if (var6.itemID == mod_RpgInventory.magehood.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:mage_3.png"));
					this.mageArmor.showMageHat(true);
				} else if (var6.itemID == mod_RpgInventory.magegown.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:mage_3.png"));
					this.mageArmor.showMageChest(true);
					this.mageArmor.showMageArms(true);
				} else if (var6.itemID == mod_RpgInventory.mageboots.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:mage_3.png"));
					this.mageArmor.showMageFeet(true);
				}
				//ArchMage
				else if (var6.itemID == mod_RpgInventory.archmageHood.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:archMage_3.png"));
					this.mageArmor.showMageHat(true);
				} else if (var6.itemID == mod_RpgInventory.archmageChest.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:archMage_3.png"));
					this.mageArmor.showMageChest(true);
					this.mageArmor.showMageArms(true);
				} else if (var6.itemID == mod_RpgInventory.archMageBoots.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:archMage_3.png"));
					this.mageArmor.showMageFeet(true);
				}
				//Necro
				else if (var6.itemID == mod_RpgInventory.necroChestplate.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:necro_3.png"));
					this.necroArmor.showTie(true);
					this.necroArmor.showGrasps(true);
				} else if (var6.itemID == mod_RpgInventory.necroBoots.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:necro_3.png"));
					this.necroArmor.showBoots(true);
				}
				// Rogue
				else if (var6.itemID == mod_RpgInventory.rogueLegs.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:rogue_3.png"));
					this.rogueArmor.showKnee(true);

				} else if (var6.itemID == mod_RpgInventory.rogueBoots.itemID) {
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:rogue_3.png"));
					this.rogueArmor.showBoots(true);
				}
				//Berserk
				else if (var6.itemID == mod_RpgInventory.berserkerHood.itemID){
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:berserk_3.png"));
					this.berserkArmor.showHorns(true);
				}else if (var6.itemID == mod_RpgInventory.berserkerChest.itemID){
					mc.renderEngine.func_110577_a(new ResourceLocation("armor:berserk_3.png"));
					this.berserkArmor.showSpaulders(true);

				}else{
					return -1;
				}
				//Binds our model.
				//This does nothing unless a custom armor rendering bonusarmor is
				//equipped.
				ModelBiped var7 = null;

				if(var6.itemID == mod_RpgInventory.mageboots.itemID|| var6.itemID == mod_RpgInventory.magepants.itemID
						||var6.itemID == mod_RpgInventory.magegown.itemID||var6.itemID == mod_RpgInventory.magehood.itemID
						|| var6.itemID == mod_RpgInventory.archMageBoots.itemID ||var6.itemID == mod_RpgInventory.archmageChest.itemID
						||var6.itemID == mod_RpgInventory.archmageLegs.itemID || var6.itemID == mod_RpgInventory.archmageHood.itemID)
				{
					var7 = par2 == 2 ? this.mageArmor : this.mageArmorChest;
				}

				else if(var6.itemID == mod_RpgInventory.beastBoots.itemID|| var6.itemID == mod_RpgInventory.beastChest.itemID
						||var6.itemID == mod_RpgInventory.beastLegs.itemID||var6.itemID == mod_RpgInventory.beastHood.itemID)
				{
					var7 = par2 == 2 ? this.beastarmor : this.beastarmorChest;
				}
				else if(var6.itemID == mod_RpgInventory.necroBoots.itemID|| var6.itemID == mod_RpgInventory.necroChestplate.itemID
						||var6.itemID == mod_RpgInventory.necroLeggings.itemID||var6.itemID == mod_RpgInventory.necroHood.itemID)
				{
					var7 = par2 == 2 ? this.necroArmor : this.necroArmorChest;
				}
				else if(var6.itemID == mod_RpgInventory.rogueBoots.itemID|| var6.itemID == mod_RpgInventory.rogueLegs.itemID
						||var6.itemID == mod_RpgInventory.rogueChest.itemID||var6.itemID == mod_RpgInventory.rogueBoots.itemID)
				{
					var7 = par2 == 2 ? this.rogueArmor : this.rogueArmorChest;
				}
				else if(var6.itemID == mod_RpgInventory.berserkerHood.itemID|| var6.itemID == mod_RpgInventory.berserkerChest.itemID
						||var6.itemID == mod_RpgInventory.berserkerLegs.itemID||var6.itemID == mod_RpgInventory.berserkerBoots.itemID)
				{
					var7 = par2 == 2 ? this.berserkArmor : this.berserkarmorChest;
				}

				var7.bipedHead.showModel = par2 == 0;
				var7.bipedHeadwear.showModel = par2 == 0;
				var7.bipedBody.showModel = par2 == 1 || par2 == 2;
				var7.bipedRightArm.showModel = par2 == 1;
				var7.bipedLeftArm.showModel = par2 == 1;
				var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
				var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
				this.setRenderPassModel(var7);
				if (var7 != null) {
					var7.onGround = this.mainModel.onGround;
				}

				if (var7 != null) {
					var7.isRiding = this.mainModel.isRiding;
				}

				if (var7 != null) {
					var7.isChild = this.mainModel.isChild;
				}

				//let the 'hacked' armor sneak when the player does
				berserkArmor.isSneak = berserkarmorChest.isSneak =
						necroArmor.isSneak = necroArmorChest.isSneak =
						rogueArmor.isSneak= rogueArmorChest.isSneak= 
						beastarmor.isSneak= beastarmorChest.isSneak =
						mageArmor.isSneak = mageArmorChest.isSneak =
						par1EntityPlayer.isSneaking();
				//Render the armor with a small inclination when player has an item in his hand
				if(par1EntityPlayer.inventory.getCurrentItem() != null)
					berserkArmor.heldItemRight = berserkarmorChest.heldItemRight =
					rogueArmor.heldItemRight = rogueArmorChest.heldItemRight =
					necroArmor.heldItemRight = necroArmorChest.heldItemRight =
					beastarmor.heldItemRight = beastarmorChest.heldItemRight = 
					mageArmor.heldItemRight = mageArmorChest.heldItemRight
					=1;
				else
					berserkArmor.heldItemRight = berserkarmorChest.heldItemRight =
					rogueArmor.heldItemRight = rogueArmorChest.heldItemRight =
					necroArmor.heldItemRight = necroArmorChest.heldItemRight =
					beastarmor.heldItemRight = beastarmorChest.heldItemRight = 
					mageArmor.heldItemRight = mageArmorChest.heldItemRight 
					=0;
				// holding bow is still missing
				// Most likely doesnt need to be added for armor.
				float var8 = 1.0F;

				if (var6.getArmorMaterial() == EnumArmorMaterial.CLOTH) {
					int var9 = var6.getColor(var4);
					float var10 = (float) (var9 >> 16 & 255) / 255.0F;
					float var11 = (float) (var9 >> 8 & 255) / 255.0F;
					float var12 = (float) (var9 & 255) / 255.0F;
					GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);

					if (var4.isItemEnchanted()) {
						return 31;
					}

					return 16;
				}

				GL11.glColor3f(var8, var8, var8);

				if (var4.isItemEnchanted()) {
					return 15;
				}
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				return 1;
			}
		}
		return -1;
	}

	//	@Override
	//	protected void renderModel(AbstractClientPlayer par1EntityLiving, float par2, float par3, float par4, float par5, float par6, float par7) {
	//		//Do No Player Model rendering, let the vanilla render do this.
	//	}

	int countdown = 100;
	@Override
	public void doRender(Entity par1EntityPlayer, double par2, double par4, double par6, float par8, float par9) {
		Field fieldPlayerModel = null;
		try {
			Field modfield = Field.class.getDeclaredField("modifiers");
			modfield.setAccessible(true);
			for (Field f : RenderPlayer.class.getDeclaredFields()) {
				f.setAccessible(true);
				try {
					if (f.getName().equals("modelBipedMain") || f.getName().equals("a")|| f.getName().equals("field_77109_a")) {
						fieldPlayerModel = f;
						modfield.setInt(fieldPlayerModel, fieldPlayerModel.getModifiers() & ~Modifier.PRIVATE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		Class clazz = par1EntityPlayer.getClass();
		Render render = defaultPlayerRender.get(clazz);
		//This cycles through the classpath until it finds the closest match for
		//this playertype. This is allows smart moving to work with the different
		//player classes.
		while (render == null) {
			clazz = clazz.getSuperclass();
			render = defaultPlayerRender.get(clazz);
		}
		//let the other render render.
		render.doRender(par1EntityPlayer, par2, par4, par6, par8, par9);
		//Grab the player model and assign it here to get rotational data.
		try {
			ModelBiped privatebiped = (ModelBiped) fieldPlayerModel.get(render);
			this.modelBipedMain = (ModelBiped) privatebiped;
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		super.doRender((EntityPlayer) par1EntityPlayer, par2, par4, par6, par8, par9);
	}
}
