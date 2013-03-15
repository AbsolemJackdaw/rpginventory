package RpgInventory.gui.pet;

import RpgInventory.ClientTickHandler;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import RpgInventory.IPet;
import RpgInventory.mod_RpgInventory;
import RpgRB.beastmaster.BMPetImpl;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PetGui extends GuiScreen {

	BMPetImpl thePet;
	int petType; /*Spider 3, bull 2, boar 1*/

	EntityPlayer p;
	ItemStack petCrystal;

	public PetGui(EntityPlayer p1) {
		p = p1;
	}
	private GuiTextField textfield;
	public final int xSizeOfTexture = 181;
	public final int ySizeOfTexture = 166;
	public static String petLevel = "Level";
	public static String petName = "Name";
	public static String petStats = "Stats :";
	public static String info = "Additional Info";
	public static int levelNr;
	public static String petAtk;
	public static int currentHP;
	public static int totalHP;
	public static String saddle;
	public static String levelInfo;
	public static String levelInfo2;

	public void initGui() {
		petCrystal = mod_RpgInventory.proxy.getInventory(p.username).getCrystal();
		if (IPet.playersWithActivePets.containsKey(p.username)) {
			thePet = (BMPetImpl) IPet.playersWithActivePets.get(p.username).getPet();
			if(thePet != null){
				//make sure crystal is updated with the mob info
				petCrystal = thePet.writePetToItemStack();
				mod_RpgInventory.proxy.getInventory(p.username).setInventorySlotContents(6, petCrystal);
			}
		}
		petType = petCrystal.getItemDamage();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_98187_b("/subaraki/petgui.png");

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		buttonList.clear();
		buttonList.add(new GuiButton(1, posX + 70, posY + 36, 40, 20, "Submit"));
		buttonList.add(new GuiButton(4, posX + 182, posY + 120, 40, 20, "Imbue"));
		buttonList.add(new GuiButton(3, posX + 182, posY + 22, 30, 20, "Back"));
		buttonList.add(new GuiButton(2, posX + 182, posY, 30, 20, "Close"));

		textfield = new GuiTextField(fontRenderer, posX + 70, posY + 14, 100, 20);
		textfield.setText(petCrystal.getTagCompound().getString("PetName"));
		textfield.setMaxStringLength(32);
		levelInfo = "";
		levelInfo2 = "";

		NBTTagCompound tags = petCrystal.getTagCompound();

		levelNr = tags.getInteger("PetLevel");
		petAtk = String.valueOf(tags.getInteger("PetAttack"));
		currentHP = tags.getInteger("PetHealth");
		totalHP = tags.getInteger("PetMaxHealth");

		if (tags.hasKey("PetLevel")) {

			if (tags.getInteger("PetLevel") >= 50) {
				if (tags.hasKey("isSaddled")) {
					if (tags.getBoolean("isSaddled") == true) {
						saddle = tags.getString("PetName") + " is saddled.";
					}
					if (tags.getBoolean("isSaddled") == false) {
						saddle = tags.getString("PetName") + " needs a Saddle to be ridden.";
					}
				}
			} else {
				saddle = "Needs level 50 to be ridden.";
			}
		}
	}

	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.id == 1) {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutput out;
			try {
				out = new ObjectOutputStream(bytes);
				System.out.println(textfield.getText());
				out.writeUTF(textfield.getText());
				out.close();
				Packet250CustomPayload packet = new Packet250CustomPayload("RpgRBPacket", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("nameSendFailed");

			}
		}
		if (guibutton.id == 2) {
			this.mc.thePlayer.closeScreen();
		}
		if (guibutton.id == 3) {
			int i = 1;
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutput out;
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(i);
				Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);
				//System.out.println("Packet send");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (guibutton.id == 4) {
			levelInfo = "";
			levelInfo2 = "";
			if (Minecraft.getMinecraft().thePlayer.experienceLevel < petCrystal.getTagCompound().getInteger("PetLevel") / 2) {
				levelInfo = "You need " + MathHelper.floor_float(petCrystal.getTagCompound().getInteger("PetLevel") / 2) + " levels to";
				levelInfo2 = "level your pet.";
			} else if (Minecraft.getMinecraft().thePlayer.experienceLevel >= petCrystal.getTagCompound().getInteger("PetLevel") / 2) {
				levelInfo = "Added 1 pet Level";
				//So first levelup is not free
				levelInfo2 = "Cost: " + MathHelper.floor_float(petCrystal.getTagCompound().getInteger("PetLevel") / 2 + 1) + " player levels;";
				Minecraft.getMinecraft().thePlayer.addExperienceLevel(-(MathHelper.floor_float(petCrystal.getTagCompound().getInteger("PetLevel") / 2)));
				levelNr += 1;
				currentHP = totalHP;
				int i = 10;
				int levelPetIsNow = petCrystal.getTagCompound().getInteger("PetLevel") + 1;
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				ObjectOutput out;
				DataOutputStream outputStream = new DataOutputStream(bytes);
				try {
					outputStream.writeInt(i);
					outputStream.writeInt(levelPetIsNow);
					Packet250CustomPayload packet = new Packet250CustomPayload("RpgInv", bytes.toByteArray());
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		textfield.textboxKeyTyped(c, i);
	}

	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		textfield.mouseClicked(i, j, k);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		initGui();
		try {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.func_98187_b("/subaraki/petgui.png");
			int posX = (this.width - xSizeOfTexture) / 2;
			int posY = (this.height - ySizeOfTexture) / 2;
			drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		} finally {
			textfield.drawTextBox();
		}
		drawString(fontRenderer, petName, this.width / 2 - 20, this.height / 2 - 79, 0xffffff);
		drawString(fontRenderer, petStats, this.width / 2 - 85, this.height / 2 - 10, 0x00ffff);
		drawString(fontRenderer, "Lvl : " + levelNr, this.width / 2 - 80, this.height / 2, 0xffffff);
		drawString(fontRenderer, "Atk : " + petAtk, this.width / 2 - 80, this.height / 2 + 10, 0xffffff);
		drawString(fontRenderer, "HP : " + currentHP + "/" + totalHP, this.width / 2 - 80, this.height / 2 + 20, 0xffffff);

		drawString(fontRenderer, info, this.width / 2 - 85, this.height / 2 + 45, 0xff00ff);
		drawString(fontRenderer, saddle, this.width / 2 - 80, this.height / 2 + 55, 0xffffff);

		drawString(fontRenderer, levelInfo, this.width / 2 + 95, this.height / 2 + 65, 0xffffff);
		drawString(fontRenderer, levelInfo2, this.width / 2 + 95, this.height / 2 + 75, 0xffffff);
		super.drawScreen(i, j, f);
		//TODO: Add Pet rendering
		//remind me to add this
	}
}