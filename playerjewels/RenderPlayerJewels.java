package RpgInventory.playerjewels;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import RpgInventory.AARpgBaseClass;
import RpgInventory.gui.inventory.RpgInventory;

public class RenderPlayerJewels extends RenderPlayer
{
	private ModelBiped modelBipedMain;
	//private ModelBiped modelJewels;
	private ModelBiped modelArmor;
	private ModelBiped modelArmorChestplate;

	public ItemStack col;
	public ItemStack shield;
	public ItemStack cloak;
	public ItemStack want;

	RpgInventory  rpg;

	public RenderPlayerJewels(ModelBase model)
	{// the name of this class sounds so wrong ... ._.

		super();
		this.modelBipedMain = (ModelBiped) this.mainModel;
		//this.modelJewels = new ModelBiped(0.4F);
		this.modelArmor = new ModelBiped(0.8F);
		this.modelArmorChestplate = new ModelBiped(0.4F);


	}
	/**
	 * Set the specified armor model as the player model. Args: player, armorSlot, partialTick
	 */
	protected int jewelRendering(EntityPlayer player, int par2, float par3)
	{
		ItemStack var4 = player.inventory.armorItemInSlot(3 - par2);

		rpg = AARpgBaseClass.proxy.getInventory(player.username);

		if(rpg != null)
		{
			col = rpg.getJewelInSlot(0);
			shield = rpg.getJewelInSlot(1);
			cloak = rpg.getJewelInSlot(2);
			want = rpg.getJewelInSlot(3);
		}

		if (var4 != null)
		{
			Item var5 = var4.getItem();

			if (var5 instanceof ItemArmor)
			{
				ItemArmor var6 = (ItemArmor) var5;
				if (want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.neckem
						|| want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.neckdia
						|| want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.necklap
						|| want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.neckgold
						|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.neckem
						|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.neckdia
						|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.necklap
						|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.neckgold
						|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.neckem
						|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.neckdia
						|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.necklap
						|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.neckgold
						|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.neckem
						|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.neckdia
						|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.necklap
						|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.neckgold)
				{
					this.loadTexture(ForgeHooksClient.getArmorTexture(var4, "/subaraki/armor/nb" + armorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + ".png"));
				}
				else if (col != null && col.getItem() == AARpgBaseClass.neckem
						|| col != null && col.getItem() == AARpgBaseClass.neckdia
						|| col != null && col.getItem() == AARpgBaseClass.necklap
						|| col != null && col.getItem() == AARpgBaseClass.neckgold)
				{
					this.loadTexture(ForgeHooksClient.getArmorTexture(var4, "/subaraki/armor/n" + armorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + ".png"));
				}
				else if (want != null && want.getItem() == AARpgBaseClass.glovesem
						|| want != null && want.getItem() == AARpgBaseClass.glovesdia
						|| want != null && want.getItem() == AARpgBaseClass.gloveslap
						|| want != null && want.getItem() == AARpgBaseClass.glovesbutter)
				{
					this.loadTexture(ForgeHooksClient.getArmorTexture(var4, "/subaraki/armor/b" + armorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + ".png"));

				}

				else
				{
					this.loadTexture(ForgeHooksClient.getArmorTexture(var4, "/armor/" + armorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + ".png"));
				}
				ModelBiped var7 = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
				var7.bipedHead.showModel = par2 == 0;
				var7.bipedHeadwear.showModel = par2 == 0;
				var7.bipedBody.showModel = par2 == 1 || par2 == 2;
				var7.bipedRightArm.showModel = par2 == 1;
				var7.bipedLeftArm.showModel = par2 == 1;
				var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
				var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
				this.setRenderPassModel(var7);

				if (var7 != null)
				{
					var7.onGround = this.mainModel.onGround;
				}

				if (var7 != null)
				{
					var7.isRiding = this.mainModel.isRiding;
				}

				if (var7 != null)
				{
					var7.isChild = this.mainModel.isChild;
				}

				float var8 = 1.0F;

				if (var6.getArmorMaterial() == EnumArmorMaterial.CLOTH)
				{
					int var9 = var6.getColor(var4);
					float var10 = (float) (var9 >> 16 & 255) / 255.0F;
					float var11 = (float) (var9 >> 8 & 255) / 255.0F;
					float var12 = (float) (var9 & 255) / 255.0F;
					GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);

					if (var4.isItemEnchanted())
					{
						return 31;
					}

					return 16;
				}

				GL11.glColor3f(var8, var8, var8);

				if (var4.isItemEnchanted())
				{
					return 15;
				}

				return 1;
			}
		}

		else if (col != null || want != null)
		{
			//System.out.println("wearing no armor");
			if (want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.neckem
					|| want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.neckdia
					|| want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.necklap
					|| want != null && want.getItem() == AARpgBaseClass.glovesem && col != null && col.getItem() == AARpgBaseClass.neckgold
					|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.neckem
					|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.neckdia
					|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.necklap
					|| want != null && want.getItem() == AARpgBaseClass.glovesdia && col != null && col.getItem() == AARpgBaseClass.neckgold
					|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.neckem
					|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.neckdia
					|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.necklap
					|| want != null && want.getItem() == AARpgBaseClass.gloveslap && col != null && col.getItem() == AARpgBaseClass.neckgold
					|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.neckem
					|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.neckdia
					|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.necklap
					|| want != null && want.getItem() == AARpgBaseClass.glovesbutter && col != null && col.getItem() == AARpgBaseClass.neckgold)
			{
				this.loadTexture(ForgeHooksClient.getArmorTexture(col, "/subaraki/armor/nbjewel.png"));
			}
			else if (col != null && col.getItem() == AARpgBaseClass.neckem
					|| col != null && col.getItem() == AARpgBaseClass.neckdia
					|| col != null && col.getItem() == AARpgBaseClass.necklap
					|| col != null && col.getItem() == AARpgBaseClass.neckgold)
			{
				this.loadTexture(ForgeHooksClient.getArmorTexture(col, "/subaraki/armor/njewel.png"));
			}

			else if (want != null && want.getItem() == AARpgBaseClass.glovesem
					|| want != null && want.getItem() == AARpgBaseClass.glovesdia
					|| want != null && want.getItem() == AARpgBaseClass.gloveslap
					|| want != null && want.getItem() == AARpgBaseClass.glovesbutter)
			{
				this.loadTexture(ForgeHooksClient.getArmorTexture(want, "/subaraki/armor/bjewel.png"));
			}
			ModelBiped var7 = modelArmor;
			var7.bipedHead.showModel = par2 == 0;
			var7.bipedHeadwear.showModel = par2 == 0;
			var7.bipedBody.showModel = par2 == 1 || par2 == 2;
			var7.bipedRightArm.showModel = par2 == 1;
			var7.bipedLeftArm.showModel = par2 == 1;
			var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
			var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
			this.setRenderPassModel(var7);

			if (var7 != null)
			{
				var7.onGround = this.mainModel.onGround;
			}

			if (var7 != null)
			{
				var7.isRiding = this.mainModel.isRiding;
			}

			if (var7 != null)
			{
				var7.isChild = this.mainModel.isChild;
			}

			return 1;
		}
		return -1;

	}

	protected void renderSpecialsRpg(EntityPlayer player, float par2)
	{
		rpg = AARpgBaseClass.proxy.getInventory(player.username);
		float var11;

		//INSERT CODE HERE
		if(rpg != null)
		{
			ItemStack cloak = rpg.getJewelInSlot(2);
		}

		if (cloak != null)
		{
			if (cloak.getItem() == AARpgBaseClass.cloak
					|| cloak.getItem() == AARpgBaseClass.cloakYellow
					|| cloak.getItem() == AARpgBaseClass.cloakRed
					|| cloak.getItem() == AARpgBaseClass.cloakBlue
					|| cloak.getItem() == AARpgBaseClass.cloakGreen
					|| cloak.getItem() == AARpgBaseClass.cloakSub)
			{
				if (cloak.getItem() == AARpgBaseClass.cloak)
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/capes/GreyCape.png", 0);
				}
				if (cloak.getItem() == AARpgBaseClass.cloakYellow)
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/capes/GoldCape.png", 0);
				}
				if (cloak.getItem() == AARpgBaseClass.cloakRed)
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/capes/RedCape.png", 0);
				}
				if (cloak.getItem() == AARpgBaseClass.cloakGreen)
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/capes/GreenCape.png", 0);
				}
				if (cloak.getItem() == AARpgBaseClass.cloakBlue)
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/capes/SkyCape.png", 0);
				}
				if (cloak.getItem() == AARpgBaseClass.cloakSub)
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/capes/SubCape.png", 0);
				}

				/** Dev Capes */
				if (player.username.equals("TheCodyMaverick"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/ACCape.png", 0);
				}
				if (player.username.equals("Unjustice"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/UnCape.png", 0);
				}
				if (player.username.equals("NyhmsQuest"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/NymCape.png", 0);
				}
				if (player.username.equals("Joebuz"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/BuzCape.png", 0);
				}

				if (player.username.equals("superv20"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/MRSCape.png", 0);
				}

				if (player.username.equals("VIruS_Ex"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/PreCape.png", 0);
				}
				if (player.username.equals("AbrarSyed"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/AbrCape.png", 0);
				}
				if (player.username.equals("rich1051414"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/TarCape.png", 0);
				}
				if (player.username.equals("LegendaryKoala"))
				{
					GL11.glPushMatrix();
					ForgeHooksClient.bindTexture("/subaraki/devcapes/CoaCape.png", 0);
				}

				GL11.glTranslatef(0.0F, 0.0F, 0.125F);
				double var22 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * (double) par2 - (player.prevPosX + (player.posX - player.prevPosX) * (double) par2);
				double var24 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * (double) par2 - (player.prevPosY + (player.posY - player.prevPosY) * (double) par2);
				double var9 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * (double) par2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double) par2);
				var11 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2;
				double var12 = (double) MathHelper.sin(var11 * (float) Math.PI / 180.0F);
				double var14 = (double) (-MathHelper.cos(var11 * (float) Math.PI / 180.0F));
				float var16 = (float) var24 * 10.0F;

				if (var16 < -6.0F)
				{
					var16 = -6.0F;
				}

				if (var16 > 32.0F)
				{
					var16 = 32.0F;
				}

				float var17 = (float) (var22 * var12 + var9 * var14) * 100.0F;
				float var18 = (float) (var22 * var14 - var9 * var12) * 100.0F;

				if (var17 < 0.0F)
				{
					var17 = 0.0F;
				}

				float var19 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * par2;
				var16 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * var19;

				if (player.isSneaking())
				{
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
		float var3 = 1.0F;
		GL11.glColor3f(var3, var3, var3);
		super.renderEquippedItems(player, par2);
		super.renderArrowsStuckInEntity(player, par2);
		ItemStack var4 = player.inventory.armorItemInSlot(3);

		if (var4 != null)
		{
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			float var5;

			if (var4 != null && var4.getItem() instanceof ItemBlock)
			{
				IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(var4, EQUIPPED);
				boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, var4, BLOCK_3D));

				if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType()))
				{
					var5 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var5, -var5, -var5);
				}

				this.renderManager.itemRenderer.renderItem(player, var4, 0);
			}
			else if (var4.getItem().itemID == Item.skull.itemID)
			{
				var5 = 1.0625F;
				GL11.glScalef(var5, -var5, -var5);
				String var6 = "";

				if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner"))
				{
					var6 = var4.getTagCompound().getString("SkullOwner");
				}

				TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemDamage(), var6);
			}

			GL11.glPopMatrix();
		}

		float var7;
		float var8;

		if (player.username.equals("deadmau5") && this.loadDownloadableImageTexture(player.skinUrl, (String) null))
		{
			for (int var20 = 0; var20 < 2; ++var20)
			{
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

		ItemStack var21 = player.inventory.getCurrentItem();

		if (var21 != null)
		{
			GL11.glPushMatrix();
			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

			if (player.fishEntity != null)
			{
				var21 = new ItemStack(Item.stick);
			}

			EnumAction var23 = null;

			if (player.getItemInUseCount() > 0)
			{
				var23 = var21.getItemUseAction();
			}

			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(var21, EQUIPPED);
			boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, var21, BLOCK_3D));

			if (var21.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[var21.itemID].getRenderType())))
			{
				var7 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				var7 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-var7, -var7, var7);
			}
			else if (var21.itemID == Item.bow.itemID)
			{
				var7 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var7, -var7, var7);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else if (Item.itemsList[var21.itemID].isFull3D())
			{
				var7 = 0.625F;

				if (Item.itemsList[var21.itemID].shouldRotateAroundWhenRendering())
				{
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}

				if (player.getItemInUseCount() > 0 && var23 == EnumAction.block)
				{
					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
					GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
				}

				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(var7, -var7, var7);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else
			{
				var7 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(var7, var7, var7);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}

			float var10;
			int var27;
			float var28;

			if (var21.getItem().requiresMultipleRenderPasses())
			{
				for (var27 = 0; var27 < var21.getItem().getRenderPasses(var21.getItemDamage()); ++var27)
				{
					int var26 = var21.getItem().getColorFromItemStack(var21, var27);
					var28 = (float) (var26 >> 16 & 255) / 255.0F;
					var10 = (float) (var26 >> 8 & 255) / 255.0F;
					var11 = (float) (var26 & 255) / 255.0F;
					GL11.glColor4f(var28, var10, var11, 1.0F);
					this.renderManager.itemRenderer.renderItem(player, var21, var27);
				}
			}
			else
			{
				var27 = var21.getItem().getColorFromItemStack(var21, 0);
				var8 = (float) (var27 >> 16 & 255) / 255.0F;
				var28 = (float) (var27 >> 8 & 255) / 255.0F;
				var10 = (float) (var27 & 255) / 255.0F;
				GL11.glColor4f(var8, var28, var10, 1.0F);
				this.renderManager.itemRenderer.renderItem(player, var21, 0);
			}

			GL11.glPopMatrix();
		}
	}

	@Override
	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
	{
		return this.jewelRendering((EntityPlayer) par1EntityLiving, par2, par3);
	}

	@Override
	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
	{
		this.renderSpecialsRpg((EntityPlayer) par1EntityLiving, par2);
	}
}
