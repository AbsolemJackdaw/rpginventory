package rpgInventory.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import rpgInventory.gui.rpginv.PlayerRpgInventory;
import rpgInventory.item.armor.ItemRpgArmor;
import rpgInventory.models.jewels.GloveLeft;
import rpgInventory.models.jewels.GloveRight;

public class RenderPlayerHandler {

	private GloveRight rightglove = new GloveRight();
	private GloveLeft leftglove = new GloveLeft();

	Minecraft mc= Minecraft.getMinecraft();
	ModelBiped main = new ModelBiped();
	@ForgeSubscribe
	public void PlayerRender(RenderPlayerEvent.Pre evt ){
		EntityPlayer player = evt.entityPlayer;
		evt.renderer.setRenderPassModel(main);
		
		boolean render = true; 
		if(player.getCurrentEquippedItem() != null && player.getItemInUseCount() > 2 &&
				player.getCurrentEquippedItem().getItemUseAction().toString().equals("bow"))
			render = false;
		
		ItemStack stack = PlayerRpgInventory.get(player).getGloves();
		if(stack != null){

			mc.renderEngine.func_110577_a(((ItemRpgArmor)stack.getItem()).getTexture());
			GL11.glPushMatrix();
			GL11.glScalef(1.125F, 1.125F, 1.125F);
			GL11.glTranslatef(-0.05F, 0F, 0.05F);
			GL11.glRotatef(this.main.bipedLeftArm.rotateAngleX * 50, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.main.bipedLeftArm.rotateAngleY * 50, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.main.bipedLeftArm.rotateAngleZ * 50, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.main.bipedLeftArm.rotationPointX, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.main.bipedLeftArm.rotationPointY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.main.bipedLeftArm.rotationPointZ, 0.0F, 0.0F, 1.0F);		
			if(render)
				leftglove.renderLeftGlove(0.0625f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1.125f, 1.125F, 1.125F);
			GL11.glTranslatef(0.05F, 0F, 0.05F);
			if(render)
				rightglove.renderRightGlove(0.0625f);
			GL11.glPopMatrix();
		}		
	}

}
