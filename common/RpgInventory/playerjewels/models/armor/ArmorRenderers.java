//package RpgInventory.playerjewels.models.armor;
//
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.entity.RenderPlayer;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//
//import org.lwjgl.opengl.GL11;
//
//import RpgInventory.mod_RpgInventory;
//import RpgInventory.playerjewels.RenderPlayerJewels;
//
//public class ArmorRenderers extends RenderPlayer {
//
//	public static ArmorRenderers renderInstance;
//	
//	public ArmorRenderers(ModelBase par1ModelBase, float par2) {
//		super();
//		renderInstance = this;
//
//	}
//	
//	@Override
//	public void renderModel(EntityLiving par1EntityLiving, float par2, float par3, float par4, float par5, float par6, float par7)
//	{
//		float scale = 2.25f;
//		float scaleHead = 1.6f;
//		//always sets models to false, except when worn
//		RenderPlayerJewels.instance.beastarmor.setShowModel(false);
//		RenderPlayerJewels.instance.beastarmorHead.setShowModel(false);
//
//		ItemStack test2 = ((EntityPlayer)par1EntityLiving).inventory.armorInventory[0];
//		if(test2 != null && test2.itemID == mod_RpgInventory.beastBoots.itemID)
//		{
//			
//			GL11.glPushMatrix();
//			this.loadTexture("/armor/beast_1.png");
//			GL11.glScalef(scale,scale,scale);
//			GL11.glTranslatef(0f, -0.755f, 0f);
//			RenderPlayerJewels.instance.beastarmor.bipedLeftLeg.showModel = false;
//			RenderPlayerJewels.instance.beastarmor.bipedRightLeg.showModel = false;
//			
//			RenderPlayerJewels.instance.beastarmor.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
//			GL11.glPopMatrix();
//		}	
//		if(test2 != null && test2.itemID == mod_RpgInventory.beastChest.itemID)
//		{
//			GL11.glPushMatrix();
//			this.loadTexture("/armor/beast_1.png");
//			GL11.glScalef(scale,scale,scale);
//			GL11.glTranslatef(0f, -0.755f, 0f);
//			RenderPlayerJewels.instance.beastarmor.bipedBody.showModel = true;
//			RenderPlayerJewels.instance.beastarmor.bipedLeftArm.showModel = true;
//			RenderPlayerJewels.instance.beastarmor.bipedRightArm.showModel = true;
//			RenderPlayerJewels.instance.beastarmor.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
//			GL11.glPopMatrix();
//		}
//		if(test2 != null && test2.itemID == mod_RpgInventory.beastLegs.itemID)
//		{
//			GL11.glPushMatrix();
//			this.loadTexture("/armor/beast_1.png");
//			GL11.glScalef(scale,scale,scale);
//			GL11.glTranslatef(0f, -0.755f, 0f);
//			RenderPlayerJewels.instance.beastarmor.bipedHeadwear.showModel = false;
//			RenderPlayerJewels.instance.beastarmor.bipedHead.showModel = false;
//			RenderPlayerJewels.instance.beastarmor.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
//			GL11.glPopMatrix();
//		}
//		if(test2 != null && test2.itemID == mod_RpgInventory.beastHood.itemID)
//		{
//			GL11.glPushMatrix();
//			this.loadTexture("/armor/beast_1.png");
//			GL11.glScalef(scale,scale,scale);
//			GL11.glTranslatef(0f, -0.755f, 0f);
//			RenderPlayerJewels.instance.beastarmor.bipedHeadwear.showModel = false;
//			RenderPlayerJewels.instance.beastarmor.bipedHead.showModel = false;
//			RenderPlayerJewels.instance.beastarmor.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
//			GL11.glPopMatrix();
//			
//			GL11.glPushMatrix();
//			this.loadTexture("/armor/beast_1.png");
//			GL11.glScalef(scaleHead,scaleHead,scaleHead);
//			GL11.glTranslatef(0f, -0.755f, 0f);
//			RenderPlayerJewels.instance.beastarmorHead.bipedHeadwear.showModel = false;
//			RenderPlayerJewels.instance.beastarmorHead.bipedHead.showModel = true;
//			RenderPlayerJewels.instance.beastarmorHead.bipedBody.showModel = false;
//			RenderPlayerJewels.instance.beastarmorHead.bipedLeftArm.showModel = false;
//			RenderPlayerJewels.instance.beastarmorHead.bipedLeftLeg.showModel = false;
//			RenderPlayerJewels.instance.beastarmorHead.bipedRightArm.showModel = false;
//			RenderPlayerJewels.instance.beastarmorHead.bipedRightLeg.showModel = false;
//			RenderPlayerJewels.instance.beastarmorHead.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
//			GL11.glPopMatrix();
//		}
//		this.loadDownloadableImageTexture(((EntityPlayer)par1EntityLiving).skinUrl, par1EntityLiving.getTexture());
//		this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
//	} 
//}
