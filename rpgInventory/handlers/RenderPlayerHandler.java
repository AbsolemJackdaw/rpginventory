package rpgInventory.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import rpgInventory.mod_RpgInventory;
import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.models.jewels.GloveLeft;
import rpgInventory.models.jewels.GloveRight;
import cpw.mods.fml.common.FMLLog;

public class RenderPlayerHandler {

	private GloveRight rightglove = new GloveRight();
	private GloveLeft leftglove = new GloveLeft();
	
	Minecraft mc= Minecraft.getMinecraft();
	
	@ForgeSubscribe
	public void PlayerRender(RenderPlayerEvent.Specials.Pre evt ){
		EntityPlayer player = evt.entityPlayer;
		
		ItemStack stack = PlayerRpgInventory.get(player).getGloves();
//		FMLLog.getLogger().info(""+ PlayerRpgInventory.get(player).getGloves());
		if(stack != null){
			FMLLog.getLogger().info(" rendering ");
		}
		GL11.glPushMatrix();
		mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/Glove.png"));
//		GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
//		
//		GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointX, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointY, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedLeftArm.rotationPointZ, 0.0F, 0.0F, 1.0F);

		GL11.glScalef(1.125F, 1.125F, 1.125F);
		GL11.glTranslatef(-0.05F, 0F, -0.04F);

		boolean render = true; 
		if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 &&
				player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){
			render = false;
			}if(render)
		{
			leftglove.renderLeftGlove(0.0625f);
		}

		GL11.glPopMatrix();

		GL11.glPushMatrix();
			mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:jewels/Glove.png"));
//		GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleX * scale, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleY * scale, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotateAngleZ * scale, 0.0F, 0.0F, 1.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointX, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointY, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(this.modelBipedMain.bipedRightArm.rotationPointZ, 0.0F, 0.0F, 1.0F);

		GL11.glScalef(1.125f, 1.125F, 1.125F);
		GL11.glTranslatef(0.05F, 0F, 0.05F);
		if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 && player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow")){render = false;}if(render)
		{
			rightglove.renderRightGlove(0.0625f);
		}

		GL11.glPopMatrix();
	}
}
