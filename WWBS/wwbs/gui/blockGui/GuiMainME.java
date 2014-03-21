package WWBS.wwbs.gui.blockGui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMainME extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 176;
	private int ySize = 90;

	int posX, posY, posZ;
	public static String hi = "World Wide Massive Exchanging";
	public static String hi2 = "System";

	public GuiMainME(EntityPlayer player, int x, int y, int z) {
		super();
		posX = x;
		posY = y;
		posZ = z;

	}

	@Override
	public void actionPerformed(GuiButton button) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		if (button.id == 0) {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutput out;
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(0);
				outputStream.writeInt(posX);
				outputStream.writeInt(posY);
				outputStream.writeInt(posZ);

				Packet250CustomPayload packet = new Packet250CustomPayload(
						"wwbsData", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (button.id == 1) {
			mc.thePlayer.closeScreen();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.xSize_lo = par1;
		this.ySize_lo = par2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine
				.bindTexture(new ResourceLocation("subaraki:MainGui.png"));
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawTexturedModalRect((posX * 2) - 15, posY, 50, 90, 80, ySize);
		drawString(fontRenderer, hi, (this.width / 2) - 84,
				(this.height / 2) - 40, 0xffffff);
		drawString(fontRenderer, hi2, (this.width / 2) - 84,
				(this.height / 2) - 30, 0xffffff);

		super.drawScreen(par1, par2, par3);
	}

	@Override
	public void initGui() {
		// super.initGui();
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(0, posX + 15, posY + 30, 80, 20,
				"Acces the M.E.S"));
		this.buttonList.add(new GuiButton(1, posX + 15, posY + 55, 80, 20,
				"Exit M.E.S"));

	}
}
