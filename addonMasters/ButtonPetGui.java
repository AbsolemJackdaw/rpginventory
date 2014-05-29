package addonMasters;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import rpgInventory.gui.rpginv.PlayerRpgContainer;
import rpgInventory.gui.rpginv.RpgGui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author Richard Smith
 */
@SideOnly(Side.CLIENT)
public class ButtonPetGui extends GuiButton {

	private EntityPlayer player;
	private GuiScreen gui;

	public ButtonPetGui(int par1, int par2, int par3, int x, int y,
			GuiScreen gui, String par4Str) {
		super(par1, par2, par3, par4Str);
		player = Minecraft.getMinecraft().thePlayer;
		this.width = x;
		this.height = y;
		this.field_146123_n /* drawButton boolean */= true;
		this.enabled = true;
		this.gui = gui;
	}

	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {

		if ((par2 >= this.xPosition) && (par2 <= (this.xPosition + this.width)))
			if ((par3 >= this.yPosition)
					&& (par3 <= (this.yPosition + this.height))) {
				EntityPlayer p = Minecraft.getMinecraft().thePlayer;
				PlayerRpgContainer container = ((PlayerRpgContainer) ((RpgGui) gui).inventorySlots);
				// todo send packet
				if (container.inventory.getCrystal() != null)
					if (container.inventory.getCrystal().getItemDamage() > 0)
						RpgMastersAddon.proxy.openGUI(p, container.inventory);

				return false;
			}
		return super.mousePressed(par1Minecraft, par2, par3);
	}

}
