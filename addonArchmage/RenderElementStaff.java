package addonArchmage;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import addonArchmage.weapons.ItemElementalStaff;
import addonBasic.renderer.weapons.StaffRenderer;

public class RenderElementStaff extends StaffRenderer {


	@Override
	public void blockLoop(Entity p, float repeat, ItemStack item) {
		mc.renderEngine.bindTexture(texture);

		if (item != null) {
			try {
				if (item.getItem() instanceof ItemElementalStaff) {
					switch (((ItemElementalStaff) item.getItem()).type) {
					case 1:/* fire */
					{
						Color clr = getColor(2, 0, 0, 3, 0, 0,(float) this.step / 50);
						this.step++;
						GL11.glColor4f((float) ((clr.getRed() * 100) / 255) / 100, 0,0, 1.0f);
					}
					break;
					case 2:/* ice */
					{
						Color clr = getColor(2, 0, 0, 3, 0, 0,(float) this.step / 50);
						this.step++;
						GL11.glColor4f(0.0F, 0.0F,(float) ((clr.getRed() * 100) / 255) / 100,1.0f);
					}
					break;
					case 3:/* earth */
					{
						Color clr = getColor(2, 0, 0, 3, 0, 0,(float) this.step / 50);
						this.step++;
						GL11.glColor4f(0.0F,(float) ((clr.getRed() * 100) / 255) / 100,0.0F, 1.0f);
					}
					break;
					case 4:/* wind */
					{
						Color clr = getColor(2, 0, 0, 3, 0, 0,(float) this.step / 50);
						this.step++;
						GL11.glColor4f((float) ((clr.getRed() * 100) / 255) / 100,(float) ((clr.getRed() * 100) / 255) / 100,
								(float) ((clr.getRed() * 100) / 255) / 100,
								1.0f);
					}
					break;
					case 5:/* ultimate */
					{
						Color clr = getColor(.1, .2, .3, 0, 0, 0,(float) this.step / 10);
						this.step++;
						GL11.glColor4f((float) ((clr.getRed() * 100) / 255) / 100,(float) ((clr.getGreen() * 100) / 255) / 100,(float) ((clr.getBlue() * 100) / 255) / 100,1.0f);
					}
					break;
					default: {
						GL11.glColor4f(0, 0, 0, 1F);
					}
					break;
					}
				} else {
					GL11.glColor4f(0, 0.2f, 0.7f, 1F);
				}
			} catch (Exception e) {
			}
		}

		if (this.step > 1000) {
			this.step = 0;
		}

		rotate += 0.0003f;

		GL11.glScalef(2F, 2F, 2F);
		GL11.glTranslatef(0.0F, 0.355F, -0.015F);
		for (float var1 = 0f; var1 < 0.5f; var1 += 0.1F) {
			staffModel.sphere(p, var1 + rotate, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}	
	}
}
