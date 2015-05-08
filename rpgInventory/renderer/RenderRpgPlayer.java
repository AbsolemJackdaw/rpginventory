package rpgInventory.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

import rpgInventory.RpgInventoryMod;
import rpgInventory.config.RpgConfig;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgInvArmor;
import rpgInventory.models.GloveLeft;
import rpgInventory.models.GloveRight;
import rpgInventory.models.MainShield;
import rpgInventory.models.ModelNecklace;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderRpgPlayer {

	private GloveRight rightglove = new GloveRight();
	private GloveLeft leftglove = new GloveLeft();
	private ModelNecklace necklace = new ModelNecklace();

	private final Minecraft mc = Minecraft.getMinecraft();
	ModelBiped main;

	float rotation = 0;

	float i = 0;

	@SubscribeEvent
	public void PlayerFPRenderer(RenderHandEvent e){

		ItemStack shield = PlayerRpgInventory.get(mc.thePlayer).getShield();
		ItemStack glove = PlayerRpgInventory.get(mc.thePlayer).getGloves();

		if (shield != null || glove != null){

			float par1 = e.partialTicks;
			int par2 = e.renderPass;

			if (mc.entityRenderer.debugViewDirection <= 0)
			{
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				float f1 = 0.07F;

				if (this.mc.gameSettings.anaglyph) {
					GL11.glTranslatef((-((par2 * 2) - 1)) * f1, 0.0F, 0.0F);
				}

				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();

				if (this.mc.gameSettings.anaglyph) {
					GL11.glTranslatef(((par2 * 2) - 1) * 0.1F, 0.0F, 0.0F);
				}

				GL11.glPushMatrix();
				hurtCameraEffect(par1);

				if (this.mc.gameSettings.viewBobbing) {
					setupViewBobbing(par1);
				}

				if ((this.mc.gameSettings.thirdPersonView == 0) && !this.mc.renderViewEntity.isPlayerSleeping() && !this.mc.gameSettings.hideGUI && !this.mc.playerController.enableEverythingIsScrewedUpMode())
				{

					if(shield != null ){
						ItemRpgInvArmor theShield = ((ItemRpgInvArmor) shield.getItem());
						mc.renderEngine.bindTexture(theShield.getTexture());

						renderFPShield(shield, par1);

						if (theShield.isMantle() && mc.thePlayer.isSneaking() && PlayerRpgInventory.get(mc.thePlayer).isBlocking()) {
							renderMantle(mc.thePlayer, ((ItemRpgInvArmor) shield.getItem()).getMantleTexture(), true);
						}

					}
					if(glove != null && mc.thePlayer.getHeldItem() == null){

						//						Render render = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
						//		                biped = ((RenderPlayer)render).modelBipedMain;
						//		                replacement  = (ModelRenderer) rightglove.boxList.get(0);
						////		                ((ItemRpgInvArmor) glove.getItem()).getTexture()

						if(RpgConfig.instance.glovesFprendering){
							e.setCanceled(true);
							mc.renderEngine.bindTexture(((ItemRpgInvArmor) glove.getItem()).getTexture());
							renderFPSGlove(rightglove, par1);
						}

						//						renderHand(glove);
					}
				}

				GL11.glPopMatrix();
			}
		}
	}

	public ModelBiped biped;
	public ModelRenderer replacement;

	public float progress;

	private void renderHand(ItemStack glove)
	{
		if(replacement != null)
		{
			float f = 1.0F;

			Minecraft.getMinecraft().getTextureManager().bindTexture(((ItemRpgInvArmor) glove.getItem()).getTexture()); //try func_110776_a

			GL11.glColor4f(f, f, f, progress);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			ModelRenderer arm = (ModelRenderer) rightglove.boxList.get(0);
			biped.bipedRightArm = replacement;

			float rotX = replacement.rotationPointX;
			float rotY = replacement.rotationPointY;
			float rotZ = replacement.rotationPointZ;

			float angX = replacement.rotateAngleX;
			float angY = replacement.rotateAngleY;
			float angZ = replacement.rotateAngleZ;

			for (int i = 0; i < leftglove.parts.size(); i++) {
				rightglove.parts.get(i).rotateAngleX = angX;
				rightglove.parts.get(i).rotateAngleY = angY;
				rightglove.parts.get(i).rotateAngleZ = angZ;
				rightglove.parts.get(i).rotationPointX = rotX;
				rightglove.parts.get(i).rotationPointY = rotY;
				rightglove.parts.get(i).rotationPointZ = rotZ;
			}

			GL11.glColor4f(1,1,1,1);
			rightglove.renderRightGlove(0.0625f);

		}
	}

	@SubscribeEvent
	public void playerPreRendering(RenderPlayerEvent.Pre evt){

		ItemStack cloak = PlayerRpgInventory.get(evt.entityPlayer).getCloak();

		if(cloak != null) {
			if(cloak.getItem() == RpgInventoryMod.cloakI) {
				evt.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void PlayerPostrenderer(RenderPlayerEvent.Post evt) {

		/* ===== RENDERING SHIELDS===== */
		ItemStack shield = PlayerRpgInventory.get(evt.entityPlayer).getShield();
		if (shield != null) {
			if (shield.getItem() instanceof ItemRpgInvArmor) {
				ItemRpgInvArmor theShield = ((ItemRpgInvArmor) shield.getItem());
				if (theShield.isMantle() && evt.entityPlayer.isSneaking() && PlayerRpgInventory.get(evt.entityPlayer).isBlocking()) {
					renderMantle(evt.entityPlayer, ((ItemRpgInvArmor) shield.getItem()).getMantleTexture(), false);
				}
			}
		}
	}

	@SubscribeEvent
	public void PlayerRender(RenderPlayerEvent.SetArmorModel evt) {

		if(main == null){
			RenderPlayer r = evt.renderer;
			//yay, my pull request :3 modelBipedMain got set to public
			main = r.modelBipedMain;
		}

		PlayerRpgInventory inventory = PlayerRpgInventory.get(evt.entityPlayer);
		ItemStack cloak = inventory.getCloak();
		ItemStack gloves = inventory.getGloves();
		ItemStack necklace = inventory.getNecklace();
		ItemStack shield = inventory.getShield();

		/* ===== RENDERING CLOAK===== */
		if ((cloak != null) && (cloak.getItem() != RpgInventoryMod.cloakI)) {
			rendercape(evt.entityPlayer, cloak, evt.partialRenderTick);
		}

		/* ===== RENDERING GLOVES===== */
		if (gloves != null) {
			mc.renderEngine.bindTexture(((ItemRpgInvArmor) gloves.getItem()).getTexture());

			renderGloves();
		}

		/* ===== RENDERING NECKLACE===== */
		if (necklace != null) {
			mc.renderEngine.bindTexture(((ItemRpgInvArmor) necklace.getItem()).getTexture());
			renderNecklace(evt.entityPlayer);
		}


		/* ===== RENDERING SHIELDS===== */
		if (shield != null) {
			mc.renderEngine.bindTexture(((ItemRpgInvArmor) shield.getItem()).getTexture());
			if((main != null) && shield.getItem() != null) {
				renderShield(shield, evt.entityPlayer, inventory);
			}
		}
	}

	private void rendercape(EntityPlayer player, ItemStack cloak,
			float partialTick) {
		if ((cloak != null) && !player.getHideCape()) {
			if ((cloak.getItem() != null)) {
				GL11.glPushMatrix();

				mc.renderEngine.bindTexture(((ItemRpgInvArmor) cloak.getItem()).getTexture());

				GL11.glTranslatef(0.0F, 0.0F, 0.125F);
				double var22 = (player.field_71091_bM + ((player.field_71094_bP - player.field_71091_bM) * partialTick))- (player.prevPosX + ((player.posX - player.prevPosX) * partialTick));
				double var24 = (player.field_71096_bN + ((player.field_71095_bQ - player.field_71096_bN) * partialTick))- (player.prevPosY + ((player.posY - player.prevPosY) * partialTick));
				double var9 = (player.field_71097_bO + ((player.field_71085_bR - player.field_71097_bO) * partialTick))- (player.prevPosZ + ((player.posZ - player.prevPosZ) * partialTick));
				float var11 = player.prevRenderYawOffset+ ((player.renderYawOffset - player.prevRenderYawOffset) * partialTick);
				double var12 = MathHelper.sin((var11 * (float) Math.PI) / 180.0F);
				double var14 = (-MathHelper.cos((var11 * (float) Math.PI) / 180.0F));
				float var16 = (float) var24 * 10.0F;

				if (var16 < -6.0F) {
					var16 = -6.0F;
				}

				if (var16 > 32.0F) {
					var16 = 32.0F;
				}

				float var17 = (float) ((var22 * var12) + (var9 * var14)) * 100.0F;
				float var18 = (float) ((var22 * var14) - (var9 * var12)) * 100.0F;

				if (var17 < 0.0F) {
					var17 = 0.0F;
				}

				float var19 = player.prevCameraYaw+ ((player.cameraYaw - player.prevCameraYaw) * partialTick);
				var16 += MathHelper.sin((player.prevDistanceWalkedModified + ((player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTick)) * 6.0F)* 32.0F * var19;

				if (player.isSneaking()) {
					var16 += 25.0F;
				}
				GL11.glRotatef(6.0F + (var17 / 2.0F) + var16, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var18 / 2.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-var18 / 2.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glColor4f(1,1,1,1);
				this.main.renderCloak(0.0625F);
				GL11.glPopMatrix();
			}
		}
	}

	private void renderFPShield(ItemStack stack, float par1) {

		//TODO somehow put arm movement in here
		ItemRpgInvArmor armor = (ItemRpgInvArmor) stack.copy().getItem();

		MainShield shieldModel = null;

		try {
			shieldModel = armor.getShieldModel().getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		GL11.glPushMatrix();

		EntityClientPlayerMP entityclientplayermp = this.mc.thePlayer;
		float f2 = entityclientplayermp.prevRotationPitch + ((entityclientplayermp.rotationPitch - entityclientplayermp.prevRotationPitch) * par1);
		GL11.glPushMatrix();
		GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(entityclientplayermp.prevRotationYaw + ((entityclientplayermp.rotationYaw - entityclientplayermp.prevRotationYaw) * par1), 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		EntityPlayerSP entityplayersp = entityclientplayermp;
		float f3 = entityplayersp.prevRenderArmPitch + ((entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * par1);
		float f4 = entityplayersp.prevRenderArmYaw + ((entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * par1);
		GL11.glRotatef((entityclientplayermp.rotationPitch - f3) * 0.1F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef((entityclientplayermp.rotationYaw - f4) * 0.1F, 0.0F, 1.0F, 0.0F);

		float f = 0.8f;
		GL11.glScalef(f, f+0.5f, f);

		if(i < 360){
			GL11.glRotatef(-90, 0, 1, 0);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(0, 0, 0, 1);
		} else {
			i =0f;
		}

		if(PlayerRpgInventory.get(entityclientplayermp).isBlocking() && entityclientplayermp.isSneaking() && !armor.isMantle())
			GL11.glTranslatef(0f, 0.2f, -0.8f);
		else
			GL11.glTranslatef(0f, .5f, -1.1f);

		if(shieldModel!=null && shieldModel.getClass().getCanonicalName().contains("LionHead"))
			GL11.glRotatef(90, 0, 1, 0);

		GL11.glColor4f(1,1,1,1);

		if(shieldModel != null)
			shieldModel.renderShield(0.0625f);

		GL11.glPopMatrix();
	}

	private void renderFPSGlove(GloveRight armor, float par1) {
		GL11.glPushMatrix();

		EntityClientPlayerMP entityclientplayermp = this.mc.thePlayer;
		float f2 = entityclientplayermp.prevRotationPitch + ((entityclientplayermp.rotationPitch - entityclientplayermp.prevRotationPitch) * par1);
		GL11.glPushMatrix();
		GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(entityclientplayermp.prevRotationYaw + ((entityclientplayermp.rotationYaw - entityclientplayermp.prevRotationYaw) * par1), 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		EntityPlayerSP entityplayersp = entityclientplayermp;
		float f3 = entityplayersp.prevRenderArmPitch + ((entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * par1);
		float f4 = entityplayersp.prevRenderArmYaw + ((entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * par1);
		GL11.glRotatef((entityclientplayermp.rotationPitch - f3) * 0.1F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef((entityclientplayermp.rotationYaw - f4) * 0.1F, 0.0F, 1.0F, 0.0F);

		float f = 1f;
		GL11.glScalef(f, f+0.9f, f);


		if(i < 360){
			GL11.glRotatef(50, 0, 1, 0);
			GL11.glRotatef(-155, 1, 0, 0);
			GL11.glRotatef(-45, 0, 0, 1);
		} else {
			i =0f;
		}

		GL11.glTranslatef(-0.25f, 1f, -0.75f);

		GL11.glScalef(-1f, -1f, -1f);


		GL11.glColor4f(1,1,1,1);
		armor.renderRightGlove(0.0625f);
		GL11.glPopMatrix();
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
		GL11.glColor4f(1,1,1,1);
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
		GL11.glColor4f(1,1,1,1);
		rightglove.renderRightGlove(0.0625f);
		GL11.glPopMatrix();
	}

	private void renderMantle(EntityPlayer player, ResourceLocation texture, boolean isFirstPerson) {
		rotation += 1f;
		if (rotation == 360) {
			rotation = 0;
		}

		mc.renderEngine.bindTexture(texture);
		//		System.out.println(texture);

		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPushMatrix();
		GL11.glScalef(3F, 3F, 3F);
		if(isFirstPerson)
			GL11.glTranslatef(-0.5F, -0.5F, -0.3F);
		else
			GL11.glTranslatef(-0.5F, -0.65F, -0.5F);

		if (player == Minecraft.getMinecraft().thePlayer) {
			if (!(Minecraft.getMinecraft().currentScreen instanceof GuiScreen) ){ //&&(RenderManager.instance.playerViewY == 180.0F)

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
				GL11.glRotatef(rotation, 1F, 1F, 1F);
				GL11.glCallList(RpgInventoryMod.proxy.getSphereID(isFirstPerson));

			}
		} else {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			GL11.glRotatef(rotation, 1F, 1F, 1F);
			GL11.glCallList(RpgInventoryMod.proxy.getSphereID(isFirstPerson));
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
		if (chest != null) {
			GL11.glTranslatef(0F, 0F, -0.06F);
		} else {
			GL11.glTranslatef(0F, 0F, 0.0F);
		}
		GL11.glColor4f(1,1,1,1);
		necklace.renderNecklace(0.0625f);
		GL11.glPopMatrix();
	}

	private void renderShield(ItemStack stack, EntityPlayer player, PlayerRpgInventory inv) {

		ItemRpgInvArmor armor = (ItemRpgInvArmor) stack.getItem();

		GL11.glPushMatrix();

		if(this.mc.gameSettings.thirdPersonView != 0)
			for (int i = 0; i < armor.getShieldModel().parts.size(); i++) {
				if(inv.isBlocking() && player.isSneaking() && !armor.isMantle()){
					armor.getShieldModel().parts.get(i).rotateAngleX = 0;
					armor.getShieldModel().parts.get(i).rotateAngleY = 0;
					armor.getShieldModel().parts.get(i).rotateAngleZ = 0;
				}else{
					armor.getShieldModel().parts.get(i).rotateAngleX = main.bipedLeftArm.rotateAngleX;
					armor.getShieldModel().parts.get(i).rotateAngleY = main.bipedLeftArm.rotateAngleY;
					armor.getShieldModel().parts.get(i).rotateAngleZ = main.bipedLeftArm.rotateAngleZ;
				}

				armor.getShieldModel().parts.get(i).rotationPointX = main.bipedLeftArm.rotationPointX;
				armor.getShieldModel().parts.get(i).rotationPointY = main.bipedLeftArm.rotationPointY;
				armor.getShieldModel().parts.get(i).rotationPointZ = main.bipedLeftArm.rotationPointZ;
			}
		if (armor.shieldClass().contains("vanilla")) {
			armor.getShieldModel().parts.get(64 - 1).rotateAngleZ = main.bipedLeftArm.rotateAngleZ + 0.356f;
		}
		if(inv.isBlocking() && player.isSneaking() && !armor.isMantle()){

			System.out.println(player.getDisplayName() + " " +
					armor + 
					" " + PlayerRpgInventory.get(player).isBlocking());

			System.out.println(player.getDisplayName() + " " +
					(stack != null ? stack : "") + 
					" " + PlayerRpgInventory.get(player).isBlocking());

			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(-0.2f, 0f, 0f);

		}

		GL11.glColor4f(1,1,1,1);
		armor.getShieldModel().renderShield(0.0625f);
		GL11.glPopMatrix();
	}


	private void setupViewBobbing(float par1)
	{
		if (this.mc.renderViewEntity instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)this.mc.renderViewEntity;
			float f1 = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
			float f2 = -(entityplayer.distanceWalkedModified + (f1 * par1));
			float f3 = entityplayer.prevCameraYaw + ((entityplayer.cameraYaw - entityplayer.prevCameraYaw) * par1);
			GL11.glTranslatef(-MathHelper.cos(f2 * (float)Math.PI) * f3 * 0.3F, -Math.abs(MathHelper.cos(f2 * (float)Math.PI) * f3), 0.0F);
		}
	}

	private void hurtCameraEffect(float par1)
	{
		EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
		float f1 = entitylivingbase.hurtTime - par1;
		float f2;

		if (entitylivingbase.getHealth() <= 0.0F)
		{
			f2 = entitylivingbase.deathTime + par1;
			GL11.glRotatef(40.0F - (8000.0F / (f2 + 200.0F)), 0.0F, 0.0F, 1.0F);
		}

		if (f1 >= 0.0F)
		{
			f1 /= entitylivingbase.maxHurtTime;
			f1 = MathHelper.sin(f1 * f1 * f1 * f1 * (float)Math.PI);
			f2 = entitylivingbase.attackedAtYaw;
			GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-f1 * 14.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
		}
	}
}
