package rpgInventory.renderer;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import rpgInventory.CapeRenderer;
import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.gui.rpginv.RpgGui;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.GloveLeft;
import rpgInventory.models.GloveRight;
import rpgInventory.models.ModelNecklace;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class RenderRpgPlayer {

	private GloveRight rightglove = new GloveRight();
	private GloveLeft leftglove = new GloveLeft();
	private ModelNecklace necklace = new ModelNecklace();

	Minecraft mc = Minecraft.getMinecraft();
	ModelBiped main;

	float rotation = 0;


	@SubscribeEvent
	public void PlayerFPRenderer(RenderHandEvent e){

		ItemStack shield = PlayerRpgInventory.get(mc.thePlayer).getShield();
		if (shield != null){

			float par1 = e.partialTicks;
			int par2 = e.renderPass;

			if (mc.entityRenderer.debugViewDirection <= 0)
			{
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				float f1 = 0.07F;

				if (this.mc.gameSettings.anaglyph)
				{
					GL11.glTranslatef((float)(-(par2 * 2 - 1)) * f1, 0.0F, 0.0F);
				}

				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();

				if (this.mc.gameSettings.anaglyph)
				{
					GL11.glTranslatef((float)(par2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
				}

				GL11.glPushMatrix();
				hurtCameraEffect(par1);

				if (this.mc.gameSettings.viewBobbing)
				{
					setupViewBobbing(par1);
				}

				if (this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.isPlayerSleeping() && !this.mc.gameSettings.hideGUI && !this.mc.playerController.enableEverythingIsScrewedUpMode())
				{
					//mc.entityRenderer.enableLightmap((double)par1);
					mc.renderEngine.bindTexture(((ItemRpgInvArmor) shield.getItem()).getTexture());
					renderFPShield((ItemRpgInvArmor) shield.getItem(), par1);
					//mc.entityRenderer.disableLightmap((double)par1);
				}

				GL11.glPopMatrix();
			}
		}
	}

	private void hurtCameraEffect(float par1)
	{
		EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
		float f1 = (float)entitylivingbase.hurtTime - par1;
		float f2;

		if (entitylivingbase.getHealth() <= 0.0F)
		{
			f2 = (float)entitylivingbase.deathTime + par1;
			GL11.glRotatef(40.0F - 8000.0F / (f2 + 200.0F), 0.0F, 0.0F, 1.0F);
		}

		if (f1 >= 0.0F)
		{
			f1 /= (float)entitylivingbase.maxHurtTime;
			f1 = MathHelper.sin(f1 * f1 * f1 * f1 * (float)Math.PI);
			f2 = entitylivingbase.attackedAtYaw;
			GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-f1 * 14.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
		}
	}

	private void setupViewBobbing(float par1)
	{
		if (this.mc.renderViewEntity instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)this.mc.renderViewEntity;
			float f1 = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
			float f2 = -(entityplayer.distanceWalkedModified + f1 * par1);
			float f3 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * par1;
			float f4 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * par1;
			GL11.glTranslatef(-MathHelper.cos(f2 * (float)Math.PI) * f3 * 0.3F, -Math.abs(MathHelper.cos(f2 * (float)Math.PI) * f3), 0.0F);
			//			GL11.glRotatef(MathHelper.sin(f2 * (float)Math.PI) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
			//			GL11.glRotatef(Math.abs(MathHelper.cos(f2 * (float)Math.PI - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
			//			GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);

			//original
			//			GL11.glTranslatef(MathHelper.sin(f2 * (float)Math.PI) * f3 * 0.5F, -Math.abs(MathHelper.cos(f2 * (float)Math.PI) * f3), 0.0F);
			//			GL11.glRotatef(MathHelper.sin(f2 * (float)Math.PI) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
			//			GL11.glRotatef(Math.abs(MathHelper.cos(f2 * (float)Math.PI - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
			//			GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
		}
	}

	@SubscribeEvent
	public void PlayerPrerenderer(RenderPlayerEvent.Post evt) {
		/* ===== RENDERING SHIELDS===== */
		ItemStack shield = PlayerRpgInventory.get(evt.entityPlayer).getShield();
		if (shield != null)
			// this is an exception towards all other rendering.
			// I do not have a hook for it yet, but I hope I soon will.
			if (shield.getItem() instanceof ItemRpgInvArmor) {
				if (((ItemRpgInvArmor) shield.getItem()).shieldClass()
						.toLowerCase().contains("archmage"))
					renderMantle(evt.entityPlayer, ((ItemRpgInvArmor) shield.getItem()).getMantleTexture());

				if (((ItemRpgInvArmor) shield.getItem()).isMantle())
					renderMantle(evt.entityPlayer, ((ItemRpgInvArmor) shield.getItem()).getMantleTexture());
			}
	}

	@SubscribeEvent
	public void PlayerRender(RenderPlayerEvent.SetArmorModel evt) {

		EntityPlayer player = evt.entityPlayer;
		EntityPlayer p = evt.entityPlayer;
		//			p.worldObj.spawnParticle("portal", p.posX + (p.worldObj.rand.nextDouble() - 0.5D) * (double)p.width, p.posY + p.worldObj.rand.nextDouble() * (double)p.height - 0.25D, p.posZ + (p.worldObj.rand.nextDouble() - 0.5D) * (double)p.width, 
		//            		(p.worldObj.rand.nextDouble() - 0.5D) * 2.0D, -p.worldObj.rand.nextDouble(), (p.worldObj.rand.nextDouble() - 0.5D) * 2.0D);




		try {

			Field f = ReflectionHelper.findField(evt.renderer.getClass(), "modelBipedMain");
			main = ReflectionHelper.getPrivateValue(RenderPlayer.class, evt.renderer, "modelBipedMain");
			//			if(RpgInventoryMod.isDev){
			//				f = evt.renderer.getClass().getDeclaredField("modelBipedMain");
			//				f.setAccessible(true);
			//			}else
			//				for(Field f : evt.renderer.getClass().getDeclaredFields()){ 
			//					if(f.equals("field_77109_a") || f.equals("modelBipedMain")){
			//						f.setAccessible(true);
			//						main = (ModelBiped) f.get(evt.renderer);
			//					}
			//				}

			//			try {
			//				main = evt.renderer.modelBipedMain:
			//			} catch (Exception e) {
			//				// TODO: handle exception
			//			}

		}
		//		catch (NoSuchFieldException e) {
		//			e.printStackTrace();
		//			System.out
		//			.println("Something went wrong accesing the modelbipedmain ! Index 1");
		//		}
		catch (SecurityException e) {
			e.printStackTrace();
			System.out
			.println("Something went wrong accesing the modelbipedmain ! Index 2");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out
			.println("Something went wrong accesing the modelbipedmain ! Index 3");
		}
		//		catch (IllegalAccessException e) {
		//			e.printStackTrace();
		//			System.out
		//			.println("Something went wrong accesing the modelbipedmain ! Index 4");
		//		}

		// all fields get set to public when
		// forge compiles them
		// or they used to. that's why this worked before :)

		/* ===== RENDERING CLOAK===== */
		ItemStack cloak = PlayerRpgInventory.get(player).getCloak();
		if (cloak != null)
			rendercape(player, cloak, evt.partialRenderTick);

		/* ===== RENDERING GLOVES===== */
		ItemStack gloves = PlayerRpgInventory.get(player).getGloves();
		if (gloves != null) {
			mc.renderEngine.bindTexture(((ItemRpgInvArmor) gloves.getItem())
					.getTexture());
			renderGloves();
		}

		/* ===== RENDERING NECKLACE===== */
		ItemStack necklace = PlayerRpgInventory.get(player).getNecklace();
		if (necklace != null) {
			mc.renderEngine.bindTexture(((ItemRpgInvArmor) necklace.getItem())
					.getTexture());
			renderNecklace(evt.entityPlayer);
		}


		/* ===== RENDERING SHIELDS===== */
		ItemStack shield = PlayerRpgInventory.get(player).getShield();
		if (shield != null) {


			mc.renderEngine.bindTexture(((ItemRpgInvArmor) shield.getItem())
					.getTexture());

			if(main != null && (ItemRpgInvArmor) shield.getItem() != null)
				renderShield((ItemRpgInvArmor) shield.getItem());
		}

	}



	private void rendercape(EntityPlayer player, ItemStack cloak,
			float partialTick) {
		float var11;
		if ((cloak != null) && !player.getHideCape())
			if ((cloak.getItem() == RpgInventoryMod.cloak)
					|| (cloak.getItem() == RpgInventoryMod.cloakYellow)
					|| (cloak.getItem() == RpgInventoryMod.cloakRed)
					|| (cloak.getItem() == RpgInventoryMod.cloakBlue)
					|| (cloak.getItem() == RpgInventoryMod.cloakGreen)
					|| (cloak.getItem() == RpgInventoryMod.cloakSub)) {
				GL11.glPushMatrix();

				mc.renderEngine.bindTexture(((ItemRpgInvArmor) cloak.getItem())
						.getTexture());

				/**
				 * Dev Capes
				 */
				if (cloak.getItem() == RpgInventoryMod.cloak)
					if (CapeRenderer.capes != null)
						if (CapeRenderer.playersWithCapes.contains(player
								.getCommandSenderName()))
							mc.renderEngine.bindTexture(CapeRenderer
									.getLocationCape(player
											.getCommandSenderName()));// new
				// ResourceLocation("subaraki/playerCapes/"+player.username+".png"));

				GL11.glTranslatef(0.0F, 0.0F, 0.125F);
				double var22 = (player.field_71091_bM + ((player.field_71094_bP - player.field_71091_bM) * partialTick))
						- (player.prevPosX + ((player.posX - player.prevPosX) * partialTick));
				double var24 = (player.field_71096_bN + ((player.field_71095_bQ - player.field_71096_bN) * partialTick))
						- (player.prevPosY + ((player.posY - player.prevPosY) * partialTick));
				double var9 = (player.field_71097_bO + ((player.field_71085_bR - player.field_71097_bO) * partialTick))
						- (player.prevPosZ + ((player.posZ - player.prevPosZ) * partialTick));
				var11 = player.prevRenderYawOffset
						+ ((player.renderYawOffset - player.prevRenderYawOffset) * partialTick);
				double var12 = MathHelper
						.sin((var11 * (float) Math.PI) / 180.0F);
				double var14 = (-MathHelper
						.cos((var11 * (float) Math.PI) / 180.0F));
				float var16 = (float) var24 * 10.0F;

				if (var16 < -6.0F)
					var16 = -6.0F;

				if (var16 > 32.0F)
					var16 = 32.0F;

				float var17 = (float) ((var22 * var12) + (var9 * var14)) * 100.0F;
				float var18 = (float) ((var22 * var14) - (var9 * var12)) * 100.0F;

				if (var17 < 0.0F)
					var17 = 0.0F;

				float var19 = player.prevCameraYaw
						+ ((player.cameraYaw - player.prevCameraYaw) * partialTick);
				var16 += MathHelper
						.sin((player.prevDistanceWalkedModified + ((player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTick)) * 6.0F)
						* 32.0F * var19;

				if (player.isSneaking())
					var16 += 25.0F;
				GL11.glRotatef(6.0F + (var17 / 2.0F) + var16, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var18 / 2.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-var18 / 2.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				this.main.renderCloak(0.0625F);
				GL11.glPopMatrix();
			}
	}

	private void renderGloves() {
		GL11.glPushMatrix();
		for (int i = 0; i < leftglove.parts.size(); i++) {
			leftglove.parts.get(i).rotateAngleX = main.bipedLeftArm.rotateAngleX;
			leftglove.parts.get(i).rotateAngleY = main.bipedLeftArm.rotateAngleY;
			leftglove.parts.get(i).rotateAngleZ = main.bipedLeftArm.rotateAngleZ;
			leftglove.parts.get(i).rotationPointX = main.bipedLeftArm.rotationPointX;
			leftglove.parts.get(i).rotationPointY = main.bipedLeftArm.rotationPointY;
			leftglove.parts.get(i).rotationPointZ = main.bipedLeftArm.rotationPointZ;
		}
		leftglove.renderLeftGlove(0.0625f);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		for (int i = 0; i < leftglove.parts.size(); i++) {
			rightglove.parts.get(i).rotateAngleX = main.bipedRightArm.rotateAngleX;
			rightglove.parts.get(i).rotateAngleY = main.bipedRightArm.rotateAngleY;
			rightglove.parts.get(i).rotateAngleZ = main.bipedRightArm.rotateAngleZ;
			rightglove.parts.get(i).rotationPointX = main.bipedRightArm.rotationPointX;
			rightglove.parts.get(i).rotationPointY = main.bipedRightArm.rotationPointY;
			rightglove.parts.get(i).rotationPointZ = main.bipedRightArm.rotationPointZ;
		}
		rightglove.renderRightGlove(0.0625f);
		GL11.glPopMatrix();
	}

	private void renderMantle(EntityPlayer player, String s) {
		rotation += 1f;
		if (rotation == 360)
			rotation = 0;

		mc.renderEngine.bindTexture(new ResourceLocation(s));


		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPushMatrix();
		GL11.glScalef(3F, 3F, 3F);
		GL11.glTranslatef(-0.5F, -0.65F, -0.5F);
		if (player == Minecraft.getMinecraft().thePlayer) {
			if (!(((Minecraft.getMinecraft().currentScreen instanceof GuiInventory)
					|| (Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || (Minecraft
							.getMinecraft().currentScreen instanceof RpgGui)) && (RenderManager.instance.playerViewY == 180.0F))) {

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
				GL11.glRotatef(rotation, 1F, 1F, 1F);
				GL11.glCallList(RpgInventoryMod.proxy.getSphereID());

			}
		} else {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			GL11.glRotatef(rotation, 1F, 1F, 1F);
			GL11.glCallList(RpgInventoryMod.proxy.getSphereID());
		}
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}

	private void renderNecklace(EntityPlayer player) {
		ItemStack chest = player.inventory.armorItemInSlot(2);
		GL11.glPushMatrix();
		for (int i = 0; i < necklace.parts.size(); i++) {
			necklace.parts.get(i).rotateAngleX = main.bipedBody.rotateAngleX;
			necklace.parts.get(i).rotateAngleY = main.bipedBody.rotateAngleY;
			necklace.parts.get(i).rotateAngleZ = main.bipedBody.rotateAngleZ;
			necklace.parts.get(i).rotationPointX = main.bipedBody.rotationPointX;
			necklace.parts.get(i).rotationPointY = main.bipedBody.rotationPointY;
			necklace.parts.get(i).rotationPointZ = main.bipedBody.rotationPointZ;
		}
		if (chest != null)
			GL11.glTranslatef(0F, 0F, -0.06F);
		else
			GL11.glTranslatef(0F, 0F, 0.0F);
		necklace.renderNecklace(0.0625f);
		GL11.glPopMatrix();
	}

	private void renderShield(ItemRpgInvArmor armor) {
		GL11.glPushMatrix();

		for (int i = 0; i < armor.getShieldModel().parts.size(); i++) {
			armor.getShieldModel().parts.get(i).rotateAngleX = main.bipedLeftArm.rotateAngleX;
			armor.getShieldModel().parts.get(i).rotateAngleY = main.bipedLeftArm.rotateAngleY;
			armor.getShieldModel().parts.get(i).rotateAngleZ = main.bipedLeftArm.rotateAngleZ;
			armor.getShieldModel().parts.get(i).rotationPointX = main.bipedLeftArm.rotationPointX;
			armor.getShieldModel().parts.get(i).rotationPointY = main.bipedLeftArm.rotationPointY;
			armor.getShieldModel().parts.get(i).rotationPointZ = main.bipedLeftArm.rotationPointZ;
		}
		try {
			if (armor.shieldClass().contains("vanilla"))
				armor.getShieldModel().parts.get(64 - 1).rotateAngleZ = main.bipedLeftArm.rotateAngleZ + 0.356f;
		} catch (Exception e) {
		}

		armor.getShieldModel().renderShield(0.0625f);
		GL11.glPopMatrix();
	}

	float i = 0;
	private void renderFPShield(ItemRpgInvArmor armor, float par1) {
		GL11.glPushMatrix();

		//		if(main != null)
		//			for (int i = 0; i < armor.getShieldModel().parts.size(); i++) {
		//				armor.getShieldModel().parts.get(i).rotateAngleX = main.bipedLeftArm.rotateAngleX;
		//				armor.getShieldModel().parts.get(i).rotateAngleY = main.bipedLeftArm.rotateAngleY;
		//				armor.getShieldModel().parts.get(i).rotateAngleZ = main.bipedLeftArm.rotateAngleZ;
		//				armor.getShieldModel().parts.get(i).rotationPointX = main.bipedLeftArm.rotationPointX;
		//				armor.getShieldModel().parts.get(i).rotationPointY = main.bipedLeftArm.rotationPointY;
		//				armor.getShieldModel().parts.get(i).rotationPointZ = main.bipedLeftArm.rotationPointZ;
		//			}
		//		try {
		//			if (armor.shieldClass().contains("vanilla"))
		//				armor.getShieldModel().parts.get(64 - 1).rotateAngleZ = main.bipedLeftArm.rotateAngleZ + 0.356f;
		//		} catch (Exception e) {
		//		} mains is always null at this point :/


		float f1 = 1.0f;
		EntityClientPlayerMP entityclientplayermp = this.mc.thePlayer;
		float f2 = entityclientplayermp.prevRotationPitch + (entityclientplayermp.rotationPitch - entityclientplayermp.prevRotationPitch) * par1;
		GL11.glPushMatrix();
		GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(entityclientplayermp.prevRotationYaw + (entityclientplayermp.rotationYaw - entityclientplayermp.prevRotationYaw) * par1, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		EntityPlayerSP entityplayersp = (EntityPlayerSP)entityclientplayermp;
		float f3 = entityplayersp.prevRenderArmPitch + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * par1;
		float f4 = entityplayersp.prevRenderArmYaw + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * par1;
		GL11.glRotatef((entityclientplayermp.rotationPitch - f3) * 0.1F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef((entityclientplayermp.rotationYaw - f4) * 0.1F, 0.0F, 1.0F, 0.0F);

		float f = 0.8f;
		GL11.glScalef(f, f+0.5f, f);

		if(i < 360){
			GL11.glRotatef(-90, 0, 1, 0);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(0, 0, 0, 1);
		}
		else i =0f;

		GL11.glTranslated(0f, 0.5f, -1.1f);
		armor.getShieldModel().renderShield(0.0625f);
		GL11.glPopMatrix();
	}
}
