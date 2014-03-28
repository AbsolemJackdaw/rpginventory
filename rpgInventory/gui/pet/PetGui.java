package rpgInventory.gui.pet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rpgAddonMasters.entity.BMPetImpl;
import rpgAddonMasters.entity.BoarPet;
import rpgAddonMasters.entity.BullPet;
import rpgAddonMasters.entity.IPet;
import rpgAddonMasters.entity.SpiderPet;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class PetGui extends GuiScreen {

	BMPetImpl thePet;
	int petType; /* Spider 2, bull 3, boar 1 */

	EntityPlayer p;
	ItemStack petCrystal;
	PlayerRpgInventory inv;

	private GuiTextField textfield;
	public int xSizeOfTexture = 181;
	public int ySizeOfTexture = 166;
	public String petStats = "Stats :";
	public String info = "Additional Info";
	public short petAtk;
	public short currentHP;
	public short totalHP;
	public String saddle;
	public String levelInfo;
	public short PetLevel;
	public String PetName;
	public String Name = "Name";
	public short playerLevel;
	private short rotationCounter = 0;
	private short petcandyConsumed = 0;
	private short petLevelsAdded = 0;
	private short playerLevelsLost = 0;
	Class clazz;
	RenderLiving ren;
	EntityLiving fake;
	private static final int SUBMIT_BUTTON = 1;

	private static final int CLOSE_BUTTON = 2;
	private static final int BACK_BUTTON = 3;
	private static final int IMBUE_BUTTON = 4;

	public PetGui(EntityPlayer p1, PlayerRpgInventory inv) {
		p = p1;
		this.inv = inv;
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.id == SUBMIT_BUTTON) {
			PetName = textfield.getText();
		}
		if (guibutton.id == CLOSE_BUTTON) {
			this.mc.thePlayer.closeScreen();
		}
		if (guibutton.id == BACK_BUTTON) {
			this.mc.thePlayer.closeScreen();

			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream outputStream = new DataOutputStream(bytes);
			// TODO
			System.out.println("todo : send packet");

			// try {
			// outputStream.writeInt(RpgPacketHandler.OPENRPGINV);
			// Packet250CustomPayload packet = new Packet250CustomPayload(
			// "RpgInv", bytes.toByteArray());
			// PacketDispatcher.sendPacketToServer(packet);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		if (guibutton.id == IMBUE_BUTTON) {
			// levelInfo = "Imbue to next level : " + (PetLevel/2) +
			// " Player Levels";
			if ((playerLevel < ((PetLevel / 2) + 1)) && (PetLevel < 200)) {
				levelInfo = "You need "
						+ (MathHelper.floor_float(PetLevel / 2) + 1)
						+ " levels to level your pet.";
			} else if (PetLevel >= 200) {
				PetLevel = 200;
				levelInfo = "Pet has reached maximum level.";

			} else if (playerLevel >= ((PetLevel / 2) + 1)) {

				// So first levelup is not free;
				playerLevel -= MathHelper.floor_float(PetLevel / 2.0F) + 1;

				levelInfo = "Added 1 pet Level. Cost: "
						+ (MathHelper.floor_float(PetLevel / 2.0F) + 1)
						+ " player levels. " + "Your current level : "
						+ playerLevel;

				PetLevel += 1;
				currentHP = totalHP = (short) (petType == 1 ? 15 + MathHelper
						.floor_float((PetLevel) / 2.5F)
						: petType == 2 ? 18 + MathHelper
								.floor_float((PetLevel) / 2.2F)
								: 20 + MathHelper.floor_float((PetLevel) / 2F));
				petLevelsAdded++;
				playerLevelsLost += (Math.floor(PetLevel) / 2.0F) + 1.0F;
			}
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		try {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture(new ResourceLocation(
					"rpginventorymod:textures/gui/petgui.png"));
			int posX = (this.width - xSizeOfTexture) / 2;
			int posY = (this.height - ySizeOfTexture) / 2;
			drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture,
					ySizeOfTexture);
		} finally {
			textfield.drawTextBox();
		}
		drawString(fontRendererObj, Name + ": " + PetName,
				(this.width / 2) - 20, (this.height / 2) - 79, 0xffffff);
		drawString(fontRendererObj, petStats, (this.width / 2) - 85,
				(this.height / 2) - 10, 0x00ffff);
		drawString(fontRendererObj, "Lvl : " + PetLevel, (this.width / 2) - 80,
				this.height / 2, 0xffffff);
		drawString(fontRendererObj, "Atk : " + petAtk, (this.width / 2) - 80,
				(this.height / 2) + 10, 0xffffff);
		drawString(fontRendererObj, "HP : " + currentHP + "/" + totalHP,
				(this.width / 2) - 80, (this.height / 2) + 20, 0xffffff);

		drawString(fontRendererObj, info, (this.width / 2) - 85,
				(this.height / 2) + 45, 0xff00ff);
		// Shadow
		fontRendererObj.drawSplitString(saddle, ((this.width / 2) - 80) + 1,
				(this.height / 2) + 55 + 1, xSizeOfTexture - 9, 0x444444);
		// text
		fontRendererObj.drawSplitString(saddle, (this.width / 2) - 80,
				(this.height / 2) + 55, xSizeOfTexture - 10, 0xffffff);
		// drawString(fontRendererObj, saddle, this.width / 2 - 80, this.height
		// / 2
		// + 55, 0xffffff);
		// Shadow
		fontRendererObj.drawSplitString(levelInfo, (this.width / 2) + 95 + 1,
				(this.height / 2) + 65 + 1,
				(this.width - ((this.width / 2) + 95)) + 1, 0x444444);
		// text
		fontRendererObj.drawSplitString(levelInfo, (this.width / 2) + 95,
				(this.height / 2) + 65, this.width - ((this.width / 2) + 95),
				0xffffff);
		// drawString(fontRendererObj, levelInfo, this.width / 2 + 95,
		// this.height
		// / 2 + 65, 0xffffff);
		// drawString(fontRendererObj, levelInfo2, this.width / 2 + 95,
		// this.height
		// / 2 + 75, 0xffffff);
		if (PetLevel >= 200) {
			PetLevel = 200;
			drawString(fontRendererObj, "Maxed out.", (this.width / 2) - 30,
					this.height / 2, 0x00ff00);
		}
		super.drawScreen(i, j, f);
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((this.width / 2) - 52, (this.height / 2) - 23, 40);
			GL11.glRotatef(180, 0f, 1f, 0f);
			GL11.glRotatef(180, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter++, 0, 1, 0);
			fake.setPositionAndRotation(0, 0, 0, 0, 0);
			GL11.glScaled(40, 40, -40);
			ren.doRender(fake, 0, 0, 0, 0, 1);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void initGui() {
		petCrystal = this.inv.getCrystal();
		if (IPet.playersWithActivePets.containsKey(p.getCommandSenderName())) {
			thePet = (BMPetImpl) IPet.playersWithActivePets.get(
					p.getCommandSenderName()).getPet();
			if (thePet != null) {
				// make sure crystal is updated with the mob info
				petCrystal = thePet.writePetToItemStack();
				this.inv.setInventorySlotContents(6, petCrystal);
			}
		}
		petType = petCrystal.getItemDamage();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(
				"rpginventorymod:textures/gui/petgui.png"));

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		buttonList.clear();
		buttonList.add(new GuiButton(SUBMIT_BUTTON, posX + 70, posY + 36, 40,
				20, "Submit"));
		buttonList.add(new GuiButton(CLOSE_BUTTON, posX + 182, posY, 30, 20,
				"Close"));
		buttonList.add(new GuiButton(BACK_BUTTON, posX + 182, posY + 22, 30,
				20, "Back"));
		buttonList.add(new GuiButton(IMBUE_BUTTON, posX + 182, posY + 120, 40,
				20, "Imbue"));

		NBTTagCompound tags = petCrystal.getTagCompound();
		if (tags == null) {
			petCrystal.setTagCompound(new NBTTagCompound());
		}
		try {
			PetLevel = (short) tags.getInteger("PetLevel");
		} catch (NullPointerException ex) {
			PetLevel = 0;
		}
		try {
			petAtk = (short) tags.getInteger("PetAttack");
		} catch (NullPointerException ex) {
			petAtk = 4;
		}
		try {
			currentHP = (short) tags.getFloat("PetHealth");
		} catch (NullPointerException ex) {
			currentHP = 18;
		}
		try {
			totalHP = (short) tags.getFloat("PetMaxHealth");
		} catch (NullPointerException ex) {
			totalHP = 18;
		}
		try {
			PetName = tags.getString("PetName");
		} catch (NullPointerException ex) {
			PetName = petCrystal.getDisplayName();
		}

		if (petAtk == 0) {
			petAtk = 4;
		}
		if (totalHP == 0) {
			currentHP = totalHP = 18;
		}
		if (currentHP == 0) {
			currentHP = 1;
		}
		if ((PetName == null) || PetName.isEmpty()) {
			PetName = petCrystal.getDisplayName();
		}
		if (PetLevel >= 50) {
			if (tags.hasKey("isSaddled")) {
				if (tags.getBoolean("isSaddled") == true) {
					saddle = PetName + " is saddled.";
				} else {
					saddle = PetName + " is not saddled.";
				}
			} else {
				saddle = PetName + " is not saddled.";
			}
		} else {
			saddle = PetName + " needs lv50 to be ridden.";
		}

		textfield = new GuiTextField(fontRendererObj, posX + 70, posY + 14,
				100, 20);
		textfield.setText(PetName);
		textfield.setMaxStringLength(32);
		playerLevel = (short) Minecraft.getMinecraft().thePlayer.experienceLevel;

		levelInfo = "Imbue to next level : " + ((PetLevel / 2) + 1)
				+ " Player Levels";

		clazz = petType == 1 ? BoarPet.class : petType == 2 ? SpiderPet.class
				: BullPet.class;
		ren = (RenderLiving) RenderManager.instance
				.getEntityClassRenderObject(clazz);
		try {
			fake = (EntityLiving) clazz.getConstructor(World.class)
					.newInstance(p.worldObj);
		} catch (Throwable ex) {
			Logger.getLogger(PetGui.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		textfield.textboxKeyTyped(c, i);
	}

	@Override
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		textfield.mouseClicked(i, j, k);
	}

	@Override
	public void onGuiClosed() {
		sendChanges();
		super.onGuiClosed();
	}

	public void sendChanges() {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			// dos.writeInt(RpgRBPacketHandler.PETGUI);
			// dos.writeUTF(new String(PetName.getBytes("UTF-8"), "UTF-8"));
			// dos.writeShort(PetLevel);
			// dos.writeShort(currentHP);
			// dos.writeShort(totalHP);
			// dos.writeShort(petAtk);
			// dos.writeShort(playerLevelsLost);
			// dos.writeShort(petLevelsAdded);
			// dos.writeShort(petcandyConsumed);

			// TODO

			System.out.println("todo : send packet");

			// PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(
			// "RpgRBPacket", bos.toByteArray()));
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}