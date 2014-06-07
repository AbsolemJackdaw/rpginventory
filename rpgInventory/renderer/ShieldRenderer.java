package rpgInventory.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public abstract class ShieldRenderer implements IItemRenderer {

	ModelBase model;
	ResourceLocation texture;

	public ShieldRenderer(ModelBase model, String texture) {
		this.model = model;
		this.texture = new ResourceLocation(texture);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return (type != ItemRenderType.EQUIPPED)
				|| (type != ItemRenderType.EQUIPPED_FIRST_PERSON);
	}

	public abstract void renderEntity();

	public abstract void renderEquipped();

	public abstract void renderEquippedFP();

	public abstract void renderInventory();

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		renderScale();

		if (type == ItemRenderType.INVENTORY) {
			renderInventory();
			model.render(null, 0, 0, 0, 0, 0, 0.0625f);
		}
		if (type == ItemRenderType.ENTITY) {
			renderEntity();
			model.render(null, 0, 0, 0, 0, 0, 0.0625f);

		}
		if (type == ItemRenderType.EQUIPPED) {
			renderEquipped();
			model.render(null, 0, 0, 0, 0, 0, 0.0625f);

		}
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			renderEquippedFP();
			model.render(null, 0, 0, 0, 0, 0, 0.0625f);
		}
	}

	public abstract void renderScale();

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}
}
