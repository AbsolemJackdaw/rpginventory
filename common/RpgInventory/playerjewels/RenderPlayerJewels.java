package RpgInventory.playerjewels;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import RpgInventory.mod_RpgInventory;
import RpgInventory.gui.inventory.RpgGui;
import RpgInventory.gui.inventory.RpgInv;
import RpgInventory.playerjewels.models.shields.Book;
import RpgInventory.playerjewels.models.shields.LionHead;
import RpgInventory.playerjewels.models.weapons.ModelDaggerL;
import RpgInventory.playerjewels.shields.IronThorn;
import RpgInventory.playerjewels.shields.ModelShield;
import RpgInventory.playerjewels.shields.NecroShield;
import RpgInventory.playerjewels.shields.NecroSkull;
import RpgInventory.playerjewels.shields.PalaShield;
import RpgInventory.playerjewels.shields.VanillaShield;

public class RenderPlayerJewels extends RenderPlayer {

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
	public ItemStack col;
	public ItemStack shield;
	public ItemStack cloak;
	public ItemStack want;
	RpgInv rpg;

	public RenderPlayerJewels(ModelBase model) {// the name of this class sounds so wrong ... ._.

		super();
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
	}

	protected void renderSpecialsRpg(EntityPlayer player, float par2) {
		ItemStack chest = player.inventory.armorItemInSlot(2);
		ItemStack hoed = player.inventory.armorItemInSlot(3);

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
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/ACCape.png", 0);
					 } else if (player.username.equals("Unjustice")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/UnCape.png", 0);
					 } else if (player.username.equals("NyhmsQuest")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/NymCape.png", 0);
					 } else if (player.username.equals("Joebuz")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/BuzCape.png", 0);
					 } else if (player.username.equals("superv20")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/MRSCape.png", 0);
					 } else if (player.username.equals("VIruS_Ex")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/PreCape.png", 0);
					 } else if (player.username.equals("AbrarSyed")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/AbrCape.png", 0);
					 } else if (player.username.equals("rich1051414")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/TarCape.png", 0);
					 } else if (player.username.equals("LegendaryKoala")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/CoaCape.png", 0);
					 } else if (player.username.equals("TheCowTheory")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/CowCape.png", 0);
					 } else if (player.username.equals("Zxapa") || player.username.equals("Spineripper64 ")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/ConCape.png", 0);
					 } else if (player.username.equals("Artix_all_mighty")) {
						 ForgeHooksClient.bindTexture("/subaraki/devcapes/SubCape.png", 0);
					 } else // if none of the names match, it should return a grey cape texture.
					 {
						 ForgeHooksClient.bindTexture("/subaraki/capes/GreyCape.png", 0);
					 }
				} else if (cloak.getItem() == mod_RpgInventory.cloakYellow) {
					ForgeHooksClient.bindTexture("/subaraki/capes/GoldCape.png", 0);
				} else if (cloak.getItem() == mod_RpgInventory.cloakRed) {
					ForgeHooksClient.bindTexture("/subaraki/capes/RedCape.png", 0);
				} else if (cloak.getItem() == mod_RpgInventory.cloakGreen) {
					ForgeHooksClient.bindTexture("/subaraki/capes/GreenCape.png", 0);
				} else if (cloak.getItem() == mod_RpgInventory.cloakBlue) {
					ForgeHooksClient.bindTexture("/subaraki/capes/SkyCape.png", 0);
				} else if (cloak.getItem() == mod_RpgInventory.cloakSub) {
					ForgeHooksClient.bindTexture("/subaraki/capes/BlaCape.png", 0);
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
				ForgeHooksClient.bindTexture("/subaraki/jewels/Glove.png", 0);
			} else if (want.getItem() == mod_RpgInventory.glovesdia) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/GloveDia.png", 0);
			} else if (want.getItem() == mod_RpgInventory.glovesem) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/GloveEm.png", 0);
			} else if (want.getItem() == mod_RpgInventory.gloveslap) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/GloveLap.png", 0);
			}

			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointX, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointZ, 0.0F, 0.0F, 1.0F);

			GL11.glScalef(1.125F, 1.125F, 1.125F);
			GL11.glTranslatef(-0.05F, 0F, -0.04F);
			if (!modelBipedMain.aimedBow) {
				leftglove.renderLeftGlove(0.0625f);
			}

			GL11.glPopMatrix();

			GL11.glPushMatrix();
			if (want.getItem() == mod_RpgInventory.glovesbutter) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/Glove.png", 0);
			} else if (want.getItem() == mod_RpgInventory.glovesdia) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/GloveDia.png", 0);
			} else if (want.getItem() == mod_RpgInventory.glovesem) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/GloveEm.png", 0);
			} else if (want.getItem() == mod_RpgInventory.gloveslap) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/GloveLap.png", 0);
			}
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleX * scale, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleY * scale, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleZ * scale, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointX, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointZ, 0.0F, 0.0F, 1.0F);

			GL11.glScalef(1.125f, 1.125F, 1.125F);
			GL11.glTranslatef(0.05F, 0F, 0.05F);
			if (!modelBipedMain.aimedBow) {
				rightglove.renderRightGlove(0.0625f);
			}

			GL11.glPopMatrix();

		}

		if (col != null) {
			if (col.getItem() == mod_RpgInventory.neckdia || col.getItem() == mod_RpgInventory.neckgold
					|| col.getItem() == mod_RpgInventory.necklap || col.getItem() == mod_RpgInventory.neckem) {
				GL11.glPushMatrix();

				if (col.getItem() == mod_RpgInventory.neckdia) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/NeckDia.png", 0);
				} else if (col.getItem() == mod_RpgInventory.neckem) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/NeckEm.png", 0);
				} else if (col.getItem() == mod_RpgInventory.neckgold) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/NeckGold.png", 0);
				} else if (col.getItem() == mod_RpgInventory.necklap) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/NeckLap.png", 0);
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

		float var3 = 1.0F;
		GL11.glColor3f(var3, var3, var3);
		super.renderEquippedItems(player, par2);
		super.renderArrowsStuckInEntity(player, par2);
		ItemStack var4 = player.inventory.armorItemInSlot(3);

		if (var4 != null) {
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			float var5;

			if (var4 != null && var4.getItem() instanceof ItemBlock) {
				IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(var4, EQUIPPED);
				boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, var4, BLOCK_3D));

				if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
					var5 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var5, -var5, -var5);
				}
				this.renderManager.itemRenderer.renderItem(player, var4, 0);
			} else if (var4.getItem().itemID == Item.skull.itemID) {
				var5 = 1.0625F;
				GL11.glScalef(var5, -var5, -var5);
				String var6 = "";

				if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner")) {
					var6 = var4.getTagCompound().getString("SkullOwner");
				}

				TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemDamage(), var6);
			}

			GL11.glPopMatrix();
		}

		float var7;
		float var8;

		if (player.username.equals("deadmau5") && this.loadDownloadableImageTexture(player.skinUrl, (String) null)) {
			for (int var20 = 0; var20 < 2; ++var20) {
				float var25 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * par2 - (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2);
				var7 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * par2;
				GL11.glPushMatrix();
				GL11.glRotatef(var25, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(var7, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0.375F * (float) (var20 * 2 - 1), 0.0F, 0.0F);
				GL11.glTranslatef(0.0F, -0.375F, 0.0F);
				GL11.glRotatef(-var7, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-var25, 0.0F, 1.0F, 0.0F);
				var8 = 1.3333334F;
				GL11.glScalef(var8, var8, var8);
				this.modelBipedMain.renderEars(0.0625F);
				GL11.glPopMatrix();
			}
		}
		//SHIELD
		if (shield != null) {

			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

			GL11.glPushMatrix();
			if (mod_RpgInventory.hasRogue == true && shield.getItem() == mod_RpgInventory.daggers) {

				if (player.worldObj.getWorldTime() < 12500) {
					ForgeHooksClient.bindTexture("/subaraki/weapons/dagger.png", 0);
				} else {
					ForgeHooksClient.bindTexture("/subaraki/weapons/daggerNight.png", 0);
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
			} else if (mod_RpgInventory.hasRogue == true && shield.getItem() == mod_RpgInventory.beastShield) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/lion.png", 0);

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1F, 1F, 1F);
				GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(180, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
				GL11.glTranslatef(-0F, 0.2F, -0.9F);
				if (!modelBipedMain.aimedBow) {
					lionhead.render(0.0625f);
				}

			} else if (shield.getItem() == mod_RpgInventory.archersShield) {

				ForgeHooksClient.bindTexture("/subaraki/jewels/Shield1.png", 0);

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glTranslatef(-0.16F, -0.1F, 0F);

				if (!modelBipedMain.aimedBow) {
					modelShield.renderShield(0.0625f);
				}

			} else if (shield.itemID == mod_RpgInventory.talisman.itemID) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/talisman.png", 0);

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
						GL11.glCallList(mod_RpgInventory.proxy.getSphereID());
					}
				} else {
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
					GL11.glCallList(mod_RpgInventory.proxy.getSphereID());
				}
			} // bypass import for mod_RpgPlus
			else if (mod_RpgInventory.hasRpg && shield.getItem() == mod_RpgInventory.necro_shield) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/NecroShield.png", 0);

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);

				GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);

				float x = 0.8f;
				GL11.glScalef(x, x, x);
				GL11.glTranslatef(0F, 0.8F, -0.85F);

				if (!modelBipedMain.aimedBow) {
					necroshield.renderShield(0.0625f);
				}

			} else if (mod_RpgInventory.hasRpg && shield.getItem() == mod_RpgInventory.pala_shield) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/PaladinShield.png", 0);

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1.2F, 1.2F, 1.2F);
				GL11.glTranslatef(-0.05F, 0F, 0.0F);

				if (!modelBipedMain.aimedBow) {
					palashield.renderShield(0.0625f);
				}

			} else if (shield.getItem() == mod_RpgInventory.berserkerShield) {
				ForgeHooksClient.bindTexture("/subaraki/jewels/IronThorn.png", 0);

				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(1F, 1F, 1F);
				GL11.glTranslatef(0.018F, -0.1F, 0F);

				if (!modelBipedMain.aimedBow) {
					ironthorn.renderShield(0.0625f);
				}
			} else if (mod_RpgInventory.hasShields == true) {
				if (shield.getItem() == mod_RpgInventory.shieldWood) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/ShieldWood.png", 0);

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					if (!modelBipedMain.aimedBow) {
						vanillaShield.renderShield(0.0625f);
					}
				} else if (shield.getItem() == mod_RpgInventory.shieldIron) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/ShieldIron.png", 0);

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					if (!modelBipedMain.aimedBow) {
						vanillaShield.renderShield(0.0625f);
					}
				} else if (shield.getItem() == mod_RpgInventory.shieldGold) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/ShieldGold.png", 0);

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					if (!modelBipedMain.aimedBow) {
						vanillaShield.renderShield(0.0625f);
					}
				} else if (shield.getItem() == mod_RpgInventory.shieldDiamond) {
					ForgeHooksClient.bindTexture("/subaraki/jewels/ShieldDiamond.png", 0);

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glTranslatef(-0.02F, 0.05F, 0F);

					if (!modelBipedMain.aimedBow) {
						vanillaShield.renderShield(0.0625f);
					}
				}
			}
			if(mod_RpgInventory.hasMage == true)
			{
				if(shield.getItem() == mod_RpgInventory.archBook)
				{
					ForgeHooksClient.bindTexture("/subaraki/jewels/book.png", 0);

					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
					GL11.glScalef(1F, 1F, 1F);
					GL11.glRotatef(65, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
					//GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(-0.35F, -0.25F, 0.65F);

					if (!modelBipedMain.aimedBow)
					{
						book.render(0.0625f);
					}
				}
			}
			ForgeHooksClient.unbindTexture();
			GL11.glPopMatrix();
			GL11.glPopAttrib();
		}
	}

	@Override
	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
		this.renderSpecialsRpg((EntityPlayer) par1EntityLiving, par2);
	}
}
