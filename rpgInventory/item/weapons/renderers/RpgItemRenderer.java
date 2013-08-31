package rpgInventory.item.weapons.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public abstract class RpgItemRenderer implements IItemRenderer{

	
	Minecraft mc = Minecraft.getMinecraft();

	float scale = 1f;
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		switch(type){
		case INVENTORY:
			return true;
		default:
			break;
		}
		return false;

	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
	}

}
