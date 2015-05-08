package rpgInventory.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class GloveRenderer implements IItemRenderer {
	
	ModelBase model;
	ResourceLocation texture;

	public GloveRenderer (ModelBase model, String texture) {
		this.model = model;
		this.texture = new ResourceLocation(texture);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return (type == ItemRenderType.EQUIPPED_FIRST_PERSON);
	}
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			
			GL11.glTranslatef(0f, -1.2f, -0.6f);
			GL11.glScalef(1.F, 1.f, 1.F);
			
			model.render(null, 0, 0, 0, 0, 0, 0.0625f);
		}
		
	}

}
